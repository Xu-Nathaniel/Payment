package com.wechat.payment.emus;


public enum SuccessEnum {
  //状态
  SUCCESS(200, "请求成功");

  private int code;
  private String message;

  SuccessEnum(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.message;
  }
}