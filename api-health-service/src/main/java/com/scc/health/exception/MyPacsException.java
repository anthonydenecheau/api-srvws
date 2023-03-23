package com.scc.health.exception;

public class MyPacsException {
   
   public MyPacsException(String statusCode, String message) {
      super();
      StatusCode = statusCode;
      Message = message;
   }
   public MyPacsException() {
      super();
   }

   String StatusCode;
   String Message;
   
   public String getStatusCode() {
      return StatusCode;
   }
   public void setStatusCode(String statusCode) {
      StatusCode = statusCode;
   }
   public String getMessage() {
      return Message;
   }
   public void setMessage(String message) {
      Message = message;
   }
}
