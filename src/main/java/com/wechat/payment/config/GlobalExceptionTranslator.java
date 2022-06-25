package com.wechat.payment.config;

import com.wechat.payment.common.ApiException;
import com.wechat.payment.controller.resp.ApiResponse;
import com.wechat.payment.emus.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionTranslator {

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ApiResponse handleError(MissingServletRequestParameterException e) {
    log.warn("缺少请求参数", e);
    String message = String.format("缺少请求参数: %s", e.getParameterName());
    return ApiResponse
        .builder()
        .code(ErrorEnum.ERROR_400.getCode())
        .message(message)
        .build();
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ApiResponse handleError(MethodArgumentTypeMismatchException e) {
    log.warn("参数类型不匹配", e);
    String message = String.format("参数类型不匹配: %s", e.getName());
    return ApiResponse
        .builder()
        .code(ErrorEnum.ERROR_400.getCode())
        .message(message)
        .build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @SuppressWarnings("Duplicates")
  public ApiResponse handleError(MethodArgumentNotValidException e) {
    log.warn("方法参数无效", e);
    BindingResult result = e.getBindingResult();
    FieldError error = result.getFieldError();
    String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
    return ApiResponse
        .builder()
        .code(ErrorEnum.ERROR_400.getCode())
        .message(message)
        .build();
  }

  @ExceptionHandler(BindException.class)
  @SuppressWarnings("Duplicates")
  public ApiResponse handleError(BindException e) {
    log.warn("参数校验错误", e);
    FieldError error = e.getFieldError();
    String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
    return ApiResponse
        .builder()
        .code(ErrorEnum.ERROR_400.getCode())
        .message(message)
        .build();
  }


  @ExceptionHandler(NoHandlerFoundException.class)
  public ApiResponse handleError(NoHandlerFoundException e) {
    log.error("找不到请求", e);
    return ApiResponse
        .builder()
        .code(ErrorEnum.ERROR_404.getCode())
        .message(ErrorEnum.ERROR_404.getMessage())
        .build();
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ApiResponse handleError(HttpRequestMethodNotSupportedException e) {
    log.error("不支持的请求类型", e);
    return ApiResponse
        .builder()
        .code(ErrorEnum.ERROR_405.getCode())
        .message(ErrorEnum.ERROR_405.getMessage())
        .build();
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ApiResponse handleError(HttpMediaTypeNotSupportedException e) {
    log.error("不支持的媒体类型", e);
    return ApiResponse
        .builder()
        .code(ErrorEnum.ERROR_415.getCode())
        .message(ErrorEnum.ERROR_415.getMessage())
        .build();
  }


  @ExceptionHandler(Exception.class)
  public ApiResponse handleError(Exception e) {
    log.error("未知异常", e);
    return ApiResponse
        .builder()
        .code(ErrorEnum.ERROR_500.getCode())
        .message(e.getMessage())
        .build();
  }


  @ExceptionHandler(HttpServerErrorException.class)
  @SuppressWarnings("Duplicates")
  public ApiResponse handleError(HttpServerErrorException e) {
    log.error(ErrorEnum.ERROR_500.getMessage(), e);
    return ApiResponse
        .builder()
        .code(ErrorEnum.ERROR_500.getCode())
        .message(ErrorEnum.ERROR_500.getMessage())
        .build();
  }

  @ExceptionHandler(HttpClientErrorException.class)
  public ApiResponse handleError(HttpClientErrorException e) {
    log.error(ErrorEnum.ERROR_400.getMessage(), e);
    return ApiResponse
        .builder()
        .code(ErrorEnum.ERROR_400.getCode())
        .message(ErrorEnum.ERROR_400.getMessage())
        .build();
  }

  @ExceptionHandler(RuntimeException.class)
  @SuppressWarnings("Duplicates")
  public ApiResponse handleError(RuntimeException e) {
    log.error(ErrorEnum.ERROR_500.getMessage(), e);
    return ApiResponse
        .builder()
        .code(ErrorEnum.ERROR_500.getCode())
        .message(ErrorEnum.ERROR_500.getMessage())
        .build();
  }

  @ExceptionHandler(ApiException.class)
  public ApiResponse handleError(ApiException e) {
    ErrorEnum errorCode = e.getErrorCode();
    return ApiResponse
        .builder()
        .code(errorCode.getCode())
        .message(e.getMessage())
        .build();
  }
}
