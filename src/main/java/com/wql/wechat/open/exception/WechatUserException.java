package com.wql.wechat.open.exception;

 /**
  * 获取微信用户信息异常
  *
  * @author Joney
  * @program wechat-open
  * @class WechatUserException
  * @date 2019/12/11 11:04
  **/
public class WechatUserException extends Exception {

    public WechatUserException() {
        super();
    }

    public WechatUserException(String message) {
        super(message);
    }

    public WechatUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public WechatUserException(Throwable cause) {
        super(cause);
    }
}
