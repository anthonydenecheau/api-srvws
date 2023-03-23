package com.scc.health.template;

import java.util.HashMap;

import io.swagger.annotations.ApiModelProperty;

public class StudyObject {

   @ApiModelProperty(notes = "Identifier study myPacs", position = 1, allowEmptyValue = false)
   private String reference;

   @ApiModelProperty(notes = "Status's study", position = 2, allowEmptyValue = false)
   private String status;

   @ApiModelProperty(notes = "Dog Id", position = 3, allowEmptyValue = false)
   private int dogId;

   @ApiModelProperty(notes = "Readers", position = 4, allowEmptyValue = false)
   private String[] reviewerOrdinalNumbers;

   @ApiModelProperty(notes = "Creation date", position = 5, allowEmptyValue = false)
   private String createdAt;

   @ApiModelProperty(notes = "Report Url", position = 6, allowEmptyValue = false)
   private String reportUrl;

   @ApiModelProperty(notes = "Tracking dates", position = 7, allowEmptyValue = false)
   private HashMap<String,String> dates;

   @ApiModelProperty(notes = "Veterinary", position = 8, allowEmptyValue = false)
   private String uploaderOrdinalNumber;

   @ApiModelProperty(notes = "reviewer results", position = 9, allowEmptyValue = false)
   private  HashMap<String,String> formData;

   
   public String getReference() {
      return reference;
   }

   public void setReference(String reference) {
      this.reference = reference;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public int getDogId() {
      return dogId;
   }

   public void setDogId(int dogId) {
      this.dogId = dogId;
   }

   public String[] getReviewerOrdinalNumbers() {
      return reviewerOrdinalNumbers ;
   }

   public void setReviewerOrdinalNumbers(String[] reviewerOrdinalNumbers ) {
      this.reviewerOrdinalNumbers  = reviewerOrdinalNumbers ;
   }

   public String getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(String createdAt) {
      this.createdAt = createdAt;
   }

   public String getReportUrl() {
      return reportUrl;
   }

   public void setReportUrl(String reportUrl) {
      this.reportUrl = reportUrl;
   }

   public HashMap<String, String> getDates() {
      return dates;
   }

   public void setDates(HashMap<String, String> dates) {
      this.dates = dates;
   }

   public String getUploaderOrdinalNumber() {
      return uploaderOrdinalNumber;
   }

   public void setUploaderOrdinalNumber(String uploaderOrdinalNumber) {
      this.uploaderOrdinalNumber = uploaderOrdinalNumber;
   }

   public HashMap<String, String> getFormData() {
      return formData;
   }

   public void setFormData(HashMap<String, String> formData) {
      this.formData = formData;
   }

}
