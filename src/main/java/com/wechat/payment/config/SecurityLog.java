package com.wechat.payment.config;

import com.wechat.payment.emus.OperationType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 安全日志注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface SecurityLog {

  /**
   * 操作类型&接口描述
   */
  OperationType operationType() default OperationType.DEFAULT_OPERATION;

}
