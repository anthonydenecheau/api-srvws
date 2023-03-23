package com.scc.icad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@RefreshScope
@ComponentScan("com.scc")
public class Application {

   public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
   }

}
