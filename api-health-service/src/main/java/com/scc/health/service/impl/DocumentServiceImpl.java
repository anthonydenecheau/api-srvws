package com.scc.health.service.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import com.scc.health.config.FtpClient;
import com.scc.health.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

   private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

   @Autowired
   private FtpClient ftpClient;
   
   @Autowired
   private Tracer tracer;

   @Override
   public boolean save(File document, String reference) throws Exception {
      Span newSpan = tracer.createSpan("save");
      logger.debug("In the documentService.save() call, trace id: {}",
            tracer.getCurrentSpan().traceIdString());
      
      String path = "./1159";
      try {
         ftpClient.open();
         ftpClient.putFileToPath(document, path+"/"+reference+".pdf");
      } catch (Exception e) {
         throw e;
      } finally {
         newSpan.tag("peer.service", "document");
         newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
         tracer.close(newSpan);
         ftpClient.close();
      }
      
      return false;
   }

}
