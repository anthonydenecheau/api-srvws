package com.scc.health.template;

import io.swagger.annotations.ApiModelProperty;

public class OwnerObject {

   @ApiModelProperty(notes = "Owner name", position = 1, required = true)
   private String name;

   @ApiModelProperty(notes = "Owner phoneNumber", position = 2, required = true)
   private String phoneNumber;

   @ApiModelProperty(notes = "Owner mail", position = 3, required = true)
   private String mail;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getMail() {
      return mail;
   }

   public void setMail(String mail) {
      this.mail = mail;
   }

   @Override
   public String toString() {
      return "OwnerObject [name=" + name + ", phoneNumber=" + phoneNumber + ", mail=" + mail + "]";
   }

}
