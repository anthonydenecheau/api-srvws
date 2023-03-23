package com.scc.show.template;

import io.swagger.annotations.ApiModelProperty;

public class ClubObject {

   @ApiModelProperty(notes = "Club id", position = 1, allowEmptyValue = true)
   private int id;

   @ApiModelProperty(notes = "Club name", position = 2, allowEmptyValue = true)
   private String name;

   @ApiModelProperty(notes = "Club address", position = 3, allowEmptyValue = true)
   private String address;

   @ApiModelProperty(notes = "Club zip code", position = 4, allowEmptyValue = true)
   private String zipCode;

   @ApiModelProperty(notes = "Club city", position = 5, allowEmptyValue = true)
   private String city;

   @ApiModelProperty(notes = "Club inscription email", position = 6, allowEmptyValue = true)
   private String inscriptionEmail;

   @ApiModelProperty(notes = "Club information email", position = 7, allowEmptyValue = true)
   private String infoEmail;

   @ApiModelProperty(notes = "Club telephone", position = 8, allowEmptyValue = true)
   private String telephone;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getZipCode() {
      return zipCode;
   }

   public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getInscriptionEmail() {
      return inscriptionEmail;
   }

   public void setInscriptionEmail(String inscriptionEmail) {
      this.inscriptionEmail = inscriptionEmail;
   }

   public String getInfoEmail() {
      return infoEmail;
   }

   public void setInfoEmail(String infoEmail) {
      this.infoEmail = infoEmail;
   }

   public String getTelephone() {
      return telephone;
   }

   public void setTelephone(String telephone) {
      this.telephone = telephone;
   }

   public ClubObject withId(int id) {
      this.setId(id);
      return this;
   }

   public ClubObject withName(String name) {
      this.setName(name);
      return this;
   }

   public ClubObject withAddress(String address) {
      this.setAddress(address);
      return this;
   }

   public ClubObject withZipCode(String zipCode) {
      this.setZipCode(zipCode);
      return this;
   }

   public ClubObject withCity(String city) {
      this.setCity(city);
      return this;
   }

   public ClubObject withInscriptionEmail(String inscriptionEmail) {
      this.setInscriptionEmail(inscriptionEmail);
      return this;
   }

   public ClubObject withInfoEmail(String infoEmail) {
      this.setInfoEmail(infoEmail);
      return this;
   }

   public ClubObject withTelephone(String telephone) {
      this.setTelephone(telephone);
      return this;
   }

}
