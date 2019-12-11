package com.wql.wechat.open.assist;

import com.wql.wechat.open.domain.WechatQrInfo;
import com.wql.wechat.open.domain.WechatTokenInfo;
import com.wql.wechat.open.domain.WechatUserInfo;
import com.wql.wechat.open.exception.AccessTokenException;
import com.wql.wechat.open.exception.RefreshTokenException;
import com.wql.wechat.open.exception.WechatUserException;

 /**
  * 微信登录逻辑接口
  *
  * @author Joney
  * @program wechat-open
  * @class WechatLoginService
  * @date 2019/12/11 14:30
  **/
public interface WechatLoginService {
     /**
      * 获取二维码登录引导信息
      *
      * @return "java.lang.String"
      * @author Joney
      * @date 2019/12/10 20:07
      */
    WechatQrInfo qrConnect();
     /**
      * 获取接口调用凭证
      *
      * @return WechatTokenInfo
      * @param code 用户授权码
      * @author Joney
      * @date 2019/12/10 21:28
      */
    WechatTokenInfo accessToken(String code) throws AccessTokenException;
     /**
      * 刷新接口调用凭证
      *
      * @return WechatTokenInfo
      * @param refresh_token 刷新凭证
      * @author Joney
      * @date 2019/12/11 11:36
      */
    WechatTokenInfo refreshToken(String refresh_token) throws RefreshTokenException;
     /**
      * 校验接口调用凭证有效性
      *
      * @return boolean
      * @param access_token 调用凭证
      * @param openid 用户标识
      * @author Joney
      * @date 2019/12/11 12:02
      */
    boolean tokenAuth(String access_token, String openid);
     /**
      * 获取用户信息
      *
      * @return WechatUserInfo
      * @param access_token 调用凭证
      * @param openid 用户标识
      * @author Joney
      * @date 2019/12/11 9:42
      */
    WechatUserInfo getUserInfo(String access_token, String openid) throws WechatUserException;

}
