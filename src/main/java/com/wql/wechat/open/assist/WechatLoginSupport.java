package com.wql.wechat.open.assist;

import com.alibaba.fastjson.JSONObject;
import com.wql.wechat.open.config.AppConfigProps;
import com.wql.wechat.open.domain.WechatQrInfo;
import com.wql.wechat.open.domain.WechatTokenInfo;
import com.wql.wechat.open.domain.WechatUserInfo;
import com.wql.wechat.open.exception.AccessTokenException;
import com.wql.wechat.open.exception.RefreshTokenException;
import com.wql.wechat.open.exception.WechatUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

 /**
  * 微信登录逻辑实现
  *
  * @author Joney
  * @program wechat-open
  * @class WechatLoginSupport
  * @date 2019/12/10 20:04
  **/
 @Slf4j
public class WechatLoginSupport implements WechatLoginService{
     /**
      * 应用ID
      */
    private String appId;
     /**
      * 应用secret
      */
    private String appSecret;
     /**
      * 跳转链接
      */
    private String redirectUri;

    public WechatLoginSupport(AppConfigProps configProps) {
        this.appId = configProps.getAppid();
        this.appSecret = configProps.getAppsecret();
        this.redirectUri = configProps.getRedirectUri();
    }

    /**
     * 登录二维码引导链接
     */
    private static final String QR_CONNECT_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";
     /**
      * 获取token链接
      */
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
     /**
      * 刷新token链接
      */
    private static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";
     /**
      * token有效性校验链接
      */
    private static final String TOKEN_AUTH_URL = "https://api.weixin.qq.com/sns/auth?access_token=%s&openid=%s";
     /**
      * 获取用户链接
      */
    private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
     /**
      * 请求客户端工具
      */
    private static WebClient.Builder builder = WebClient.builder();

    /**
     * 获取二维码登录引导信息
     *
     * @return "java.lang.String"
     * @author Joney
     * @date 2019/12/10 20:07
     */
    public WechatQrInfo qrConnect() {
        String state = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            String qrConnectUrl = String.format(QR_CONNECT_URL, appId, URLEncoder.encode(redirectUri, "UTF-8"), state);
            return new WechatQrInfo(state, qrConnectUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取接口调用凭证
     *
     * @return WechatTokenInfo
     * @param code 用户授权码
     * @author Joney
     * @date 2019/12/10 21:28
     */
    public WechatTokenInfo accessToken(String code) throws AccessTokenException {
        JSONObject jsonObject = builder.baseUrl(String.format(ACCESS_TOKEN_URL, appId, appSecret, code))
                .build().get().retrieve().bodyToMono(JSONObject.class).block();
        String resultInfo = jsonObject.toJSONString();
        if (StringUtils.isEmpty(jsonObject.getString("access_token"))) {
            log.error(">>> Get access_token error: {}", resultInfo);
            throw new AccessTokenException(resultInfo);
        }
        return JSONObject.parseObject(resultInfo, WechatTokenInfo.class);
    }

    /**
     * 刷新接口调用凭证
     *
     * @return WechatTokenInfo
     * @param refresh_token 刷新凭证
     * @author Joney
     * @date 2019/12/11 11:36
     */
    public WechatTokenInfo refreshToken(String refresh_token) throws RefreshTokenException {
        JSONObject jsonObject = builder.baseUrl(String.format(REFRESH_TOKEN_URL, appId, refresh_token))
                .build().get().retrieve().bodyToMono(JSONObject.class).block();
        String resultInfo = jsonObject.toJSONString();
        if (StringUtils.isEmpty(jsonObject.getString("access_token"))) {
            log.error(">>> Refresh access_token error: {}", resultInfo);
            throw new RefreshTokenException(resultInfo);
        }
        return JSONObject.parseObject(resultInfo, WechatTokenInfo.class);
    }

    /**
     * 校验接口调用凭证有效性
     *
     * @return boolean
     * @param access_token 调用凭证
     * @param openid 用户标识
     * @author Joney
     * @date 2019/12/11 12:02
     */
    public boolean tokenAuth(String access_token, String openid) {
        JSONObject result = builder.baseUrl(String.format(TOKEN_AUTH_URL, access_token, openid))
                .build().get().retrieve().bodyToMono(JSONObject.class).block();
        log.info(">>> Auth access_token result: {}", result);
        return result.getInteger("errcode") == 0;
    }

    /**
     * 获取用户信息
     *
     * @return WechatUserInfo
     * @param access_token 调用凭证
     * @param openid 用户标识
     * @author Joney
     * @date 2019/12/11 9:42
     */
    public WechatUserInfo getUserInfo(String access_token, String openid) throws WechatUserException {
        JSONObject jsonObject = builder.baseUrl(String.format(USER_INFO_URL, access_token, openid))
                .build().get().retrieve().bodyToMono(JSONObject.class).block();
        String resultInfo = jsonObject.toJSONString();
        if (StringUtils.isEmpty(jsonObject.getString("openid"))) {
            log.error(">>> Get wechat user error: {}", resultInfo);
            throw new WechatUserException(resultInfo);
        }
        return JSONObject.parseObject(resultInfo, WechatUserInfo.class);
    }
}
