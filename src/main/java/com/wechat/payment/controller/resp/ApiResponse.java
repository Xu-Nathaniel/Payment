package com.wechat.payment.controller.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wechat.payment.emus.ErrorEnum;
import com.wechat.payment.emus.SuccessEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Data;


@ApiModel(value = "ApiResponse", description = "返回参数")
@Builder
@Data
public class ApiResponse<T> {

  @ApiModelProperty(value = "返回码")
  private int code;

  @ApiModelProperty(value = "返回信息")
  private String message;

  @ApiModelProperty(value = "返回的数据")
  @JsonInclude(Include.NON_NULL)
  private T data;

  public ApiResponse() {
  }

  public ApiResponse(int code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  /**
   * successful response , not exits data
   */
  public static <T> ApiResponse<T> ok() {
    return ok(null);
  }

  /**
   * Build a successful response.
   *
   * @param data Response data.
   */
  public static <T> ApiResponse<T> ok(T data) {
    ApiResponse<T> response = new ApiResponse<>();
    response.setCode(SuccessEnum.SUCCESS.getCode());
    response.setData(data);
    return response;
  }

  /**
   * Build a serverError response.
   *
   * @param errMsg Response errMsg.
   */
  public static <T> ApiResponse<T> error(String errMsg) {
    ApiResponse<T> response = new ApiResponse<>();
    response.setCode(ErrorEnum.ERROR_500.getCode());
    response.setMessage(errMsg);
    return response;
  }

  /**
   * Build a authFailed response.
   */
  public static <T> ApiResponse<T> forbidden() {
    ApiResponse<T> response = new ApiResponse<>();
    response.setCode(HttpServletResponse.SC_FORBIDDEN);
    response.setMessage("无权访问");
    return response;
  }

  /**
   * 认证失败
   */
  public static <T> ApiResponse<T> unauthorized() {
    ApiResponse<T> response = new ApiResponse<>();
    response.setCode(ErrorEnum.ERROR_401.getCode());
    response.setMessage(ErrorEnum.ERROR_401.getMessage());
    return response;
  }
}
