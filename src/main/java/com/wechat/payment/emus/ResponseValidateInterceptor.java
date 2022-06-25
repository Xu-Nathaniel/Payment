package com.wechat.payment.emus;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.AntPathMatcher;

/**
 * response type 验证拦截器
 */
@Slf4j
public enum ResponseValidateInterceptor implements ClientHttpRequestInterceptor {
  INSTANCE;

  public static AntPathMatcher pathMatcher = new AntPathMatcher();

  public static List<String> excludePathPatterns = Lists.newArrayList("");


  public static ResponseValidateInterceptor getInstance() {

    return INSTANCE;
  }

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
      throws IOException {
    if (log.isDebugEnabled()) {
      log.debug("request method [{}],request url [{}]", request.getMethod(),
          request.getURI());
    }
    ClientHttpResponse response = execution.execute(request, body);

    for (String excludePathPattern : excludePathPatterns) {
      if (pathMatcher.match(excludePathPattern, request.getURI().getPath())) {
        return response;
      }
    }
    if (HttpStatus.OK.value() != response.getRawStatusCode()) {
      throw new IllegalStateException(
          "请求URL[" + request.getURI() + "]失败，响应状态码:"
              + response.getRawStatusCode() + "，响应消息:" + response.getStatusText());
    }
    return response;
  }
}
