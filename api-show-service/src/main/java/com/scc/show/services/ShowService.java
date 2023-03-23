package com.scc.show.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.scc.show.config.ServiceConfig;
import com.scc.show.repository.ShowRepository;
import com.scc.show.template.ResponseObjectList;
import com.scc.show.template.ShowObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ShowService {

   private static final Logger logger = LoggerFactory.getLogger(ShowService.class);

   @Autowired
   private Tracer tracer;

   @Autowired
   private ShowRepository showRepository;

   @Autowired
   ServiceConfig config;

   @HystrixCommand(fallbackMethod = "buildFallbackShowList", threadPoolKey = "getShowsThreadPool", threadPoolProperties = {
         @HystrixProperty(name = "coreSize", value = "30"),
         @HystrixProperty(name = "maxQueueSize", value = "10") }, commandProperties = {
               @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
               @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
               @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
               @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
               @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") })
   public ResponseObjectList<ShowObject> getShows() {

      Span newSpan = tracer.createSpan("getShows");
      logger.debug("In the showService.getShows() call, trace id: {}", tracer.getCurrentSpan().traceIdString());
      try {

         List<ShowObject> results = new ArrayList<ShowObject>();

         results = showRepository.findAll().stream()
               .map(_show -> new ShowObject().withId(_show.getId()).withTypeCode(convertTypeCode(_show.getTypeCode()))
                     .withDescription(_show.getDescription()).withYear(_show.getYear())
                     .withStartDate(_show.getStartDate()).withEndDate(_show.getEndDate())
                     .withAddress(_show.getAddress()).withCity(_show.getCity()).withZipCode(_show.getZipCode())
                     .withCity(_show.getCity()).withOrganizingClubId(_show.getOrganizingClubId())
                     .withFrenchChampionship(isFrenchChampionship(_show.getTypeCode())))
               .collect(Collectors.toList());

         return new ResponseObjectList<ShowObject>(results.size(), results);

      } finally {
         newSpan.tag("peer.service", "postgres");
         newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
         tracer.close(newSpan);
      }
   }

   @SuppressWarnings("unused")
   private ResponseObjectList<ShowObject> buildFallbackShowList() {

      List<ShowObject> list = new ArrayList<ShowObject>();
      list.add(new ShowObject().withId(0));
      return new ResponseObjectList<ShowObject>(list.size(), list);
   }

   private String convertTypeCode(String typeCode) {
      /*
       * Association Type Evenement SCC
       */
      String val = "";
      switch (typeCode) {
      case "SHOWS":
      case "EXPOCH":
      case "CGA":
      case "EXPOIB":
         val = "ESIN";
         break;
      case "EXPOSP":
      case "EXPOCS":
         val = "ESNA";
         break;
      case "EXPORE":
         val = "ESRE";
         break;
      case "EXPONE":
      case "SCBA":
         val = "RANA";
         break;
      case "CONF":
         val = "CONF";
         break;
      default:
         val = "";
      }
      return val;
   }

   private int isFrenchChampionship(String typeCode) {
      return ((typeCode != null && typeCode.equals("EXPOCH")) ? 1 : 0);
   }
}
