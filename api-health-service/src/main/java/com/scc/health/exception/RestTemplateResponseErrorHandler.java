package com.scc.health.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scc.health.template.ActivateEventObject;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

   private static final Logger logger = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);

   @Override
   public boolean hasError(ClientHttpResponse httpResponse) throws IOException {

      return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
            || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
   }

   @Override
   public void handleError(ClientHttpResponse httpResponse) throws IOException {

      logger.error("RestTemplate Error {} ", httpResponse.getStatusCode().series());
      if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
         //Handle SERVER_ERROR
         InputStreamReader isr = new InputStreamReader(httpResponse.getBody(), StandardCharsets.UTF_8);
         String body = new BufferedReader(isr).lines().collect(Collectors.joining("\n"));

         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
         objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
         MyPacsException e = objectMapper.readValue(body, MyPacsException.class);
         
         throw new RestTemplateException(ActivateEventObject.class, "erreur", e.getMessage());

      } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
         //Handle CLIENT_ERROR
         InputStreamReader isr = new InputStreamReader(httpResponse.getBody(), StandardCharsets.UTF_8);
         String body = new BufferedReader(isr).lines().collect(Collectors.joining("\n"));

         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
         objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
         MyPacsException e = objectMapper.readValue(body, MyPacsException.class);
         
         throw new RestTemplateException(ActivateEventObject.class, "erreur", e.getMessage());
      }
   }

}