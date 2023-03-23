package com.scc.licence.template;

import io.swagger.annotations.ApiModelProperty;

public class LicenceObject {

   @ApiModelProperty(notes = "Holder id", position = 1, allowEmptyValue = true)
   private int id;

   @ApiModelProperty(notes = "Holder civility", position = 2, allowEmptyValue = true)
   private String civility;

   @ApiModelProperty(notes = "Holder lastname", position = 3, allowEmptyValue = true)
   private String lastname;

   @ApiModelProperty(notes = "Holder firstname", position = 4, allowEmptyValue = true)
   private String firstname;

   @ApiModelProperty(notes = "Holder Address", position = 5, allowEmptyValue = true)
   private String address;

   @ApiModelProperty(notes = "Holder additional Address", position = 6, allowEmptyValue = true)
   private String additionalAddress;

   @ApiModelProperty(notes = "Holder zipCode", position = 7, allowEmptyValue = true)
   private String zipCode;

   @ApiModelProperty(notes = "Holder city", position = 8, allowEmptyValue = true)
   private String city;

   @ApiModelProperty(notes = "Holder email contact", position = 9, allowEmptyValue = true)
   private String email;

   @ApiModelProperty(notes = "Holder country", position = 10, allowEmptyValue = true)
   private String country;

   @ApiModelProperty(notes = "Holder number", position = 11, allowEmptyValue = true)
   private String number;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getCivility() {
      return civility;
   }

   public void setCivility(String civility) {
      this.civility = civility;
   }

   public String getLastname() {
      return lastname;
   }

   public void setFirstname(String firstname) {
      this.firstname = firstname;
   }

   public String getFirstname() {
      return firstname;
   }

   public void setLastname(String lastname) {
      this.lastname = lastname;
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

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getNumber() {
      return number;
   }

   public void setNumber(String number) {
      this.number = number;
   }
   
   public String getAdditionalAddress() {
      return additionalAddress;
   }

   public void setAdditionalAddress(String additionalAddress) {
      this.additionalAddress = additionalAddress;
   }   
   
   public LicenceObject withId(int id) {
      this.setId(id);
      return this;
   }

   public LicenceObject withCivility(String civility) {
      this.setCivility(civility);
      return this;
   }

   public LicenceObject withLastname(String lastname) {
      this.setLastname(lastname);
      return this;
   }

   public LicenceObject withFirstname(String firstname) {
      this.setFirstname(firstname);
      return this;
   }

   public LicenceObject withAddress(String address) {
      this.setAddress(address);
      return this;
   }

   public LicenceObject withZipCode(String zipCode) {
      this.setZipCode(zipCode);
      return this;
   }

   public LicenceObject withCity(String city) {
      this.setCity(city);
      return this;
   }

   public LicenceObject withCountry(String country) {
      this.setCountry(country);
      return this;
   }

   public LicenceObject withEmail(String email) {
      this.setEmail(email);
      return this;
   }

   public LicenceObject withNumber(String number) {
      this.setNumber(number);
      return this;
   }

   public LicenceObject withAdditionalAddress(String additionalAddress) {
      this.setAdditionalAddress(additionalAddress);
      return this;
   }

}
