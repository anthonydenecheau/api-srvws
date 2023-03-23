package com.scc.licence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.scc.licence.config.ServiceConfig;
import com.scc.licence.model.Holder;
import com.scc.licence.repository.LicenceRepository;
import com.scc.licence.template.LicenceObject;
import com.scc.licence.template.ResponseObjectList;
import com.scc.licence.utils.CommissionEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LicenceService {

   private static final Logger logger = LoggerFactory.getLogger(LicenceService.class);

   @Autowired
   private Tracer tracer;

   @Autowired
   private LicenceRepository licenceRepository;

   @Autowired
   ServiceConfig config;

   @HystrixCommand(fallbackMethod = "buildFallbackLicenceList", threadPoolKey = "holdersLicencesThreadPool", threadPoolProperties = {
         @HystrixProperty(name = "coreSize", value = "30"),
         @HystrixProperty(name = "maxQueueSize", value = "10") }, commandProperties = {
               @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
               @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
               @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
               @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
               @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") })
   public ResponseObjectList<LicenceObject> getLicencesHolders(CommissionEnum commission) {

      Span newSpan = tracer.createSpan("getLicencesHolders");
      logger.debug("In the licenceService.getLicencesHolders() call, trace id: {}",
            tracer.getCurrentSpan().traceIdString());
      try {

         List<Holder> list = new ArrayList<Holder>();
         list = licenceRepository.findByIdCommission(Integer.parseInt(commission.getValue()));

         List<LicenceObject> results = new ArrayList<LicenceObject>();
         results = buildResponseObjectLicenceHolder(list);

         return new ResponseObjectList<LicenceObject>(results.size(), results);

      } finally {
         newSpan.tag("peer.service", "postgres");
         newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
         tracer.close(newSpan);
      }
   }

   public List<LicenceObject> buildResponseObjectLicenceHolder(List<Holder> list) {
      return list.stream()
            .map(_licence -> new LicenceObject().withId(_licence.getId()).withCivility(_licence.getCivility())
                  .withLastname(_licence.getLastname()).withFirstname(_licence.getFirstname())
                  .withAddress(_licence.getAddress()).withAdditionalAddress(_licence.getAdditionalAddress())
                  .withCity(_licence.getCity()).withZipCode(_licence.getZipCode()).withEmail(_licence.getEmail())
                  .withCountry(_licence.getCountry()).withNumber(_licence.getNumber()))
            .collect(Collectors.toList());
   }

   @SuppressWarnings("unused")
   private ResponseObjectList<LicenceObject> buildFallbackLicenceList(CommissionEnum commission) {

      List<LicenceObject> list = new ArrayList<LicenceObject>();
      list.add(new LicenceObject().withId(0));
      return new ResponseObjectList<LicenceObject>(list.size(), list);
   }

}
