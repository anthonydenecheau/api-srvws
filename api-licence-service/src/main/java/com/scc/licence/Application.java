package com.scc.licence;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

import com.scc.licence.utils.UserContextFilter;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@RefreshScope
public class Application {

    @Bean
    public Filter userContextFilter() {
        UserContextFilter userContextFilter = new UserContextFilter();
        return userContextFilter;
    }
    
    @Bean
    public Sampler defaultSampler() {
        return new AlwaysSampler();
    }
    
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
	
}
