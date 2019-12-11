package com.wql.wechat.open.exception;

 /**
  * 刷新access_token异常
  *
  * @author Joney
  * @program wechat-open
  * @class RefreshTokenException
  * @date 2019/12/11 11:00
  **/
public class RefreshTokenException extends Exception {

    public RefreshTokenException() {
        super();
    }

    public RefreshTokenException(String message) {
        super(message);
    }

    public RefreshTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public RefreshTokenException(Throwable cause) {
        super(cause);
    }
}
