package com.scc.health.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * interceptor to log incoming requests
 */
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

   private static final Logger logger = LoggerFactory.getLogger(CustomClientHttpRequestInterceptor.class);

   @Override
   public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
         throws IOException {

      if (request.getURI().toString().indexOf("attachment") > 0)
         request.getHeaders().setContentType(MediaType.APPLICATION_PDF);
      else
         request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
      logRequestDetails(request, body);
      return execution.execute(request, body);
   }

   private void logRequestDetails(HttpRequest request, byte[] body) {
      logger.info("Request Headers: {}", request.getHeaders());
      logger.info("Request Method: {}", request.getMethod());
      logger.info("Request URI: {}", request.getURI());
      logger.info("Request Body: {}", new String(body));
   }
}