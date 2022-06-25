package com.wechat.payment.emus;

public enum ErrorEnum {

  //找不到指定内容
  ERROR_404(404, "找不到指定内容"),

  //找不到指定内容
  ERROR_401(401, "Token错误"),

  //找不到指定内容
  ERROR_500(500, "系统错误"),

  //参数错误
  ERROR_400(400, "参数错误"),

  //不支持的请求方式
  ERROR_405(405, "不支持的请求方式"),

  //不支持的媒体类型
  ERROR_415(415, "不支持的媒体类型"),

  //时候用户名或者密码错误
  ERROR_905(905, "用户名或者密码错误");

  private int code;
  private String message;

  ErrorEnum(int code, String message) {
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