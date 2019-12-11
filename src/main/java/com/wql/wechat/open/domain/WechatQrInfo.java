package com.wql.wechat.open.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 微信登录二维码链接信息
 *
 * @author Joney
 * @program wechat-open
 * @class WechatQrInfo
 * @date 2019/12/11 11:52
 **/
@Getter
@Setter
@ToString
public class WechatQrInfo {
    /**
     * 用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
     */
    private String state;
    /**
     * 二维码访问链接
     */
    private String qrConnectUrl;

    public WechatQrInfo() {

    }

    public WechatQrInfo(String state, String qrConnectUrl) {
        this.state = state;
        this.qrConnectUrl = qrConnectUrl;
    }
}
