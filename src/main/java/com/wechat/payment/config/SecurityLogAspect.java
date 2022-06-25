package com.wechat.payment.config;

import java.lang.reflect.Method;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * security log
 *
 * @see SecurityLog
 */
@Aspect
@Component
@ConditionalOnProperty(name = "security-log-aspect.enable", havingValue = "true", matchIfMissing = true)
public class SecurityLogAspect {

  private static final Logger log = LoggerFactory.getLogger(
      SecurityLogAspect.class);


  @Pointcut("execution(public * com.wechat.payment.controller..*.*(..))")
  public void specificMethod() {
  }

  @Before("specificMethod()")
  public void doBefore(JoinPoint joinPoint) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    assert attributes != null;
    HttpServletRequest request = attributes.getRequest();
    log.info("URL : " + request.getRequestURL().toString());
    log.info("URI : " + request.getRequestURI());
    log.info("HTTP_METHOD : " + request.getMethod());
    log.info("IP : " + request.getRemoteAddr());
    Signature signature = joinPoint.getSignature();
    log.info(
        "operation_method : " + signature.getDeclaringTypeName() + "." + signature.getName());
    log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    MethodSignature methodSignature = (MethodSignature) signature;
    Method method = methodSignature.getMethod();
    SecurityLog securityLog = method.getAnnotation(SecurityLog.class);
    if (null != securityLog) {
      log.info("operation_type : " + securityLog.operationType().getCode());
      log.info("operation_desc : " + securityLog.operationType().getDesc());
    }
  }

}
