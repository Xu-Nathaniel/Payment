package com.wechat.payment.emus;


/**
 * 状态
 */
public enum CommonState {
  VALID("1", "valid"),
  INVALID("2", "invalid");

  private final String code;
  private final String value;

  CommonState(String code, String value) {
    this.code = code;
    this.value = value;
  }

  public String getCode() {
    return code;
  }

  public Integer getIntCode() {
    return Integer.valueOf(code);
  }

  public String getValue() {
    return value;
  }
}
