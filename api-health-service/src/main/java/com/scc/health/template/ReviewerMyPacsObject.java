package com.scc.health.template;

import io.swagger.annotations.ApiModelProperty;

public class ReviewerMyPacsObject {

   public ReviewerMyPacsObject() {
      super();
   }

   public ReviewerMyPacsObject(int ordinalNumber, String lastName, String firstName, String mail, boolean isReviewer,
         String createdAt, String updatedAt) {
      super();
      this.ordinalNumber = ordinalNumber;
      this.lastName = lastName;
      this.firstName = firstName;
      this.mail = mail;
      this.isReviewer = isReviewer;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
   }

   public ReviewerMyPacsObject(int ordinalNumber, String lastName, String firstName, String mail) {
      super();
      this.ordinalNumber = ordinalNumber;
      this.lastName = lastName;
      this.firstName = firstName;
      this.mail = mail;
   }

   @ApiModelProperty(notes = "Reviewer CSOV identification", position = 1, allowEmptyValue = true)
   private int ordinalNumber;

   @ApiModelProperty(notes = "Reviewer lastname", position = 2, allowEmptyValue = true)
   private String lastName;

   @ApiModelProperty(notes = "Reviewer firstname", position = 3, allowEmptyValue = true)
   private String firstName;

   @ApiModelProperty(notes = "Reviewer mail", position = 4, allowEmptyValue = true)
   private String mail;

   @ApiModelProperty(notes = "Reviewer myPacs rights", position = 5, allowEmptyValue = true)
   private boolean isReviewer;

   @ApiModelProperty(notes = "Creation date", position = 6, allowEmptyValue = false)
   private String createdAt;

   @ApiModelProperty(notes = "Creation date", position = 7, allowEmptyValue = false)
   private String updatedAt;

   public int getOrdinalNumber() {
      return ordinalNumber;
   }

   public void setOrdinalNumber(int ordinalNumber) {
      this.ordinalNumber = ordinalNumber;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getMail() {
      return mail;
   }

   public void setMail(String mail) {
      this.mail = mail;
   }

   public boolean isReviewer() {
      return isReviewer;
   }

   public void setReviewer(boolean isReviewer) {
      this.isReviewer = isReviewer;
   }

   public String getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(String createdAt) {
      this.createdAt = createdAt;
   }

   public String getUpdatedAt() {
      return updatedAt;
   }

   public void setUpdatedAt(String updatedAt) {
      this.updatedAt = updatedAt;
   }


}
