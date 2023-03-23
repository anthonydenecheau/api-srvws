package com.scc.health.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.scc.health.service.impl.DocumentServiceImpl;

@Component
public class FtpClient {

   private static final Logger logger = LoggerFactory.getLogger(FtpClient.class);
   
   @Value("${base.repository.server}")
   String server;

   @Value("${base.repository.login}")
   String login;

   @Value("${base.repository.password}")
   String password;
   
   private FTPClient ftp;

   // constructor
   public void open() throws IOException {
       
      ftp = new FTPClient();
       //ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

       ftp.connect(server, 21);
       
       int reply = ftp.getReplyCode();
       if (!FTPReply.isPositiveCompletion(reply)) {
           ftp.disconnect();
           throw new IOException("Exception in connecting to FTP Server");
       }

       ftp.login(login, password);
       ftp.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
       ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);      

   }

   public void putFileToPath(File file, String path) throws IOException {
      boolean completed = ftp.storeFile(path, new FileInputStream(file));
      if (completed) {
          logger.info("{} uploaded successfully.",path);
      }      
   }
   
   public void close() throws IOException {
      if (ftp.isConnected()) {
         ftp.logout();
         ftp.disconnect();
     }
   }
   
}
