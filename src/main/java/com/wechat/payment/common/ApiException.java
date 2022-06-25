package com.wechat.payment.common;


import com.wechat.payment.emus.ErrorEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends RuntimeException {

  private static final long serialVersionUID = -7073882031673528601L;
  /**
   * 参数错误；400：服务器端错误 500
   */
  private ErrorEnum errorCode;

  /**
   * 错误消息
   */
  private String errorMsg;

  public ApiException(ErrorEnum errorCode, String errorMsg) {
    super(errorMsg);
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }

  public ApiException(ErrorEnum errorCode, String errMsg, Throwable t) {
    super(errMsg, t);
    this.errorCode = errorCode;
    this.errorMsg = errMsg;
  }

  public ErrorEnum getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(ErrorEnum errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }
}
