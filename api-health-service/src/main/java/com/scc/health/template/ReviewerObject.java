package com.scc.health.template;

import io.swagger.annotations.ApiModelProperty;

public class ReviewerObject {

   @ApiModelProperty(notes = "Reviewer CSOV identification", position = 1, allowEmptyValue = true)
   private int ordinalNumber;

   @ApiModelProperty(notes = "Reviewer lastname", position = 2, allowEmptyValue = true)
   private String lastName;

   @ApiModelProperty(notes = "Reviewer firstname", position = 3, allowEmptyValue = true)
   private String firstName;

   public ReviewerObject withOrdinalNumber(int ordinalNumber) {
      this.setOrdinalNumber(ordinalNumber);
      return this;
   }

   public ReviewerObject withLastName(String lastName) {
      this.setLastName(lastName);
      return this;
   }

   public ReviewerObject withFirstName(String firstName) {
      this.setFirstName(firstName);
      return this;
   }

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

}
