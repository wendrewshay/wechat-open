package com.wql.wechat.open.exception;

 /**
  * 获取access_token异常
  *
  * @author Joney
  * @program wechat-open
  * @class AccessTokenException
  * @date 2019/12/11 11:00
  **/
public class AccessTokenException extends Exception {

    public AccessTokenException() {
        super();
    }

    public AccessTokenException(String message) {
        super(message);
    }

    public AccessTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessTokenException(Throwable cause) {
        super(cause);
    }
}
