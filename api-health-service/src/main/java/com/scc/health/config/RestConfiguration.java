package com.scc.health.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.scc.health.exception.RestTemplateResponseErrorHandler;

@Configuration
public class RestConfiguration {

   @Value("${myPacs.accessToken}")
   private String accessToken;

   @Bean
   @Qualifier("customRestTemplateCustomizer")
   public CustomRestTemplateCustomizer customRestTemplateCustomizer() {
      return new CustomRestTemplateCustomizer();
   }

   @Bean
   @DependsOn(value = { "customRestTemplateCustomizer" })
   public RestTemplateBuilder restTemplateBuilder() {
      return new RestTemplateBuilder(customRestTemplateCustomizer());
   }

   @Bean
   public RestTemplate restTemplate(RestTemplateBuilder builder) {
      // Do any additional configuration here
      return builder.additionalInterceptors((ClientHttpRequestInterceptor) (request, body, execution) -> {
         request.getHeaders().add("Authorization", "Bearer " + accessToken);
         return execution.execute(request, body);
      }).errorHandler(new RestTemplateResponseErrorHandler()).build();
   }
}
