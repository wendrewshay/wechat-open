package com.wql.wechat.open.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信开放平台应用配置
 *
 * @author Joney
 * @program wechat-open
 * @class AppConfigProps
 * @date 2019/12/10 17:14
 **/
@Getter
@Setter
@ConfigurationProperties(prefix = "wechat.open")
public class AppConfigProps {
    /**
     * The assigned wechat application ID.
     */
    private String appid;
    /**
     * The assigned wechat application secret.
     */
    private String appsecret;
    /**
     * Redirect to the target uri after logining wechat successfully.
     */
    private String redirectUri;
}
