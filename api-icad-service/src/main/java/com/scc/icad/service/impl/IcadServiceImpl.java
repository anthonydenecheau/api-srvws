package com.scc.icad.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.scc.icad.dao.IcadDao;
import com.scc.icad.domain.Dog;
import com.scc.icad.service.IcadService;

@Service
public class IcadServiceImpl implements IcadService {

   private static final Logger log = LoggerFactory.getLogger(IcadServiceImpl.class);

    @Autowired
    IcadDao icadDao;

    @HystrixCommand(commandKey = "icadservice",
          fallbackMethod = "buildFallbackIcadDog",
          threadPoolKey = "IcadDogByTokenThreadPool",
          threadPoolProperties =
                  {@HystrixProperty(name = "coreSize",value="30"),
                   @HystrixProperty(name="maxQueueSize", value="10")},
          commandProperties={
                   @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
                   @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
                   @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
                   @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
                   @HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")}
    )
    @Override
    public Dog getIcadDogByToken(String token) {
        return icadDao.getIcadDogByToken(token);
    }
    
    @SuppressWarnings("unused")
    private Dog buildFallbackIcadDog(String token){
       log.warn("In the dogService.buildFallbackDogList() call");
      return new Dog().withValidity("0");
    }
    
}
