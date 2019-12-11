package com.wql.wechat.open.config;

import com.wql.wechat.open.assist.WechatLoginService;
import com.wql.wechat.open.assist.WechatLoginSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
  * 登录配置
  *
  * @author Joney
  * @program wechat-open
  * @class LoginConfiguration
  * @date 2019/12/10 20:00
  **/
@Configuration
@ConditionalOnClass(WechatLoginService.class)
@EnableConfigurationProperties(value = AppConfigProps.class)
public class WechatOpenAutoConfiguration {

    @Autowired
    private AppConfigProps appConfigProps;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "wechat.open",name ="enabled",havingValue = "true", matchIfMissing = true)
    public WechatLoginService wechatLoginService() {
        return new WechatLoginSupport(appConfigProps);
    }
}
