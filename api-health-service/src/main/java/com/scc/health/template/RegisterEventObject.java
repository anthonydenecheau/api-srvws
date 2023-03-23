package com.scc.health.template;


import java.util.Arrays;

import com.scc.health.utils.DiseaseEnum;

import io.swagger.annotations.ApiModelProperty;

public class RegisterEventObject {

   @ApiModelProperty(notes = "Identifier study myPacs", position = 1, required = true)
   private String reference;
   @ApiModelProperty(notes = "Dog Id", position = 2, required = true)
   private int dogId;
   @ApiModelProperty(notes = "The diseases included in the study", position = 3, required = true)
   private DiseaseEnum[] studiesDiseases;
   @ApiModelProperty(notes = "Status of the study", position = 4, required = false)
   private String status;
   @ApiModelProperty(notes = "Owne", position = 5, required = false)
   private OwnerObject owner;
   
   public String getReference() {
      return reference;
   }

   public void setReference(String reference) {
      this.reference = reference;
   }

   public int getDogId() {
      return dogId;
   }

   public void setDogId(int dogId) {
      this.dogId = dogId;
   }

   public DiseaseEnum[] getStudiesDiseases() {
      return studiesDiseases;
   }

   public void setStudiesDiseases(DiseaseEnum[] studiesDiseases) {
      this.studiesDiseases = studiesDiseases;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public OwnerObject getOwner() {
      return owner;
   }

   public void setOwner(OwnerObject owner) {
      this.owner = owner;
   }

   @Override
   public String toString() {
      return "RegisterEventObject [reference=" + reference + ", dogId=" + dogId + ", studiesDiseases="
            + Arrays.toString(studiesDiseases) + ", status=" + status + ", owner=" + owner + "]";
   }

}
