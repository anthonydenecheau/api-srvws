package com.scc.health.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.scc.health.dao.HealthDao;
import com.scc.health.domain.Reviewer;
import com.scc.health.exception.RegisterStudyException;
import com.scc.health.service.HealthService;
import com.scc.health.template.ReviewerObject;
import com.scc.health.template.RegisterEventObject;
import com.scc.health.template.ResponseObjectList;
import com.scc.health.utils.DiseaseEnum;

@Service
public class HealthServiceImpl implements HealthService {

   private static final Logger logger = LoggerFactory.getLogger(HealthServiceImpl.class);

   @Autowired
   private Tracer tracer;

   @Autowired
   HealthDao healthDao;

   @HystrixCommand(commandKey = "healthservice", fallbackMethod = "buildFallbackReaderMyPacs", threadPoolKey = "HealthByTokenThreadPool", threadPoolProperties = {
         @HystrixProperty(name = "coreSize", value = "30"),
         @HystrixProperty(name = "maxQueueSize", value = "10") }, commandProperties = {
               @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
               @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
               @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
               @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
               @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") })
   @Override
   public ResponseObjectList<ReviewerObject> getReaderMyPacsByIdDog(int idDog, DiseaseEnum disease) {

      Span newSpan = tracer.createSpan("getReaderMyPacsByIdDog");
      logger.debug("In the healthService.getReaderMyPacsByIdDog() call, trace id: {}",
            tracer.getCurrentSpan().traceIdString());
      try {

         List<Reviewer> list = new ArrayList<Reviewer>();
         list = healthDao.getReaderMyPacsByIdDog(idDog, Integer.parseInt(disease.getValue()));

         List<ReviewerObject> results = new ArrayList<ReviewerObject>();
         results = buildResponseObjectReader(list);

         return new ResponseObjectList<ReviewerObject>(results.size(), results);

      } finally {
         newSpan.tag("peer.service", "oracle");
         newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
         tracer.close(newSpan);
      }
   }

   @SuppressWarnings("unused")
   private ResponseObjectList<ReviewerObject> buildFallbackReaderMyPacs(int idDog, DiseaseEnum disease) {
      logger.warn("In the healthService.buildFallbackReaderMyPacs() call");
      List<ReviewerObject> list = new ArrayList<ReviewerObject>();
      list.add(new ReviewerObject().withOrdinalNumber(0));
      return new ResponseObjectList<ReviewerObject>(list.size(), list);
   }

   private List<ReviewerObject> buildResponseObjectReader(List<Reviewer> list) {
      return list.stream()
            .map(_reader -> new ReviewerObject().withOrdinalNumber(_reader.getOrdinalNumber())
                  .withLastName(_reader.getLastName()).withFirstName(_reader.getFirstName()))
            .collect(Collectors.toList());
   }

   @HystrixCommand(commandKey = "healthservice", threadPoolKey = "HealthByTokenThreadPool", threadPoolProperties = {
         @HystrixProperty(name = "coreSize", value = "30"),
         @HystrixProperty(name = "maxQueueSize", value = "10") }, commandProperties = {
               @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
               @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
               @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
               @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
               @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") })
   @Override
   public void registerMyPacsStudy(RegisterEventObject event) throws RegisterStudyException {

      Span newSpan = tracer.createSpan("registerMyPacsStudy");
      logger.debug("In the healthService.registerMyPacsStudy() call, trace id: {}",
            tracer.getCurrentSpan().traceIdString());

      try {

         // On enregistre la study
         healthDao.registerMyPacsStudy(event);

      } finally {
         newSpan.tag("peer.service", "oracle");
         newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
         tracer.close(newSpan);
      }

   }
}
