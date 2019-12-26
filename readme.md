# Getting Started

### 微信PC网页端登录-后端API封装（参考结合[微信开放平台文档](https://developers.weixin.qq.com/doc/oplatform/Website_App/WeChat_Login/Wechat_Login.html)）

依赖于本项目的SpringBoot工程项目中properties或yaml文件中的配置:
* wechat.open.appid=xxx
* wechat.open.appsecret=xxx
* wechat.open.redirectUri=xxx

依赖于本项目的SpringBoot工程项目中引入组件：
```
@Autowired 
private WechatLoginService wechatLoginService;
```
* 有多个登录辅助相关操作，如：获取登录二维码链接/获取接口调用凭证/刷新接口调用凭证/校验接口调用凭证/获取微信用户信息;

