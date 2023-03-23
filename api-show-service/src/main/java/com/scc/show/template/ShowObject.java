package com.scc.show.template;

import io.swagger.annotations.ApiModelProperty;

public class ShowObject {

   @ApiModelProperty(notes = "Show id", position = 1, allowEmptyValue = true)
   private int id;

   @ApiModelProperty(notes = "Show type code", position = 2, allowEmptyValue = true)
   private String typeCode;

   @ApiModelProperty(notes = "Show description", position = 3, allowEmptyValue = true)
   private String description;

   @ApiModelProperty(notes = "Show year", position = 4, allowEmptyValue = true)
   private int year;

   @ApiModelProperty(notes = "Show start date", position = 5, allowEmptyValue = true)
   private String startDate;

   @ApiModelProperty(notes = "Show end date", position = 6, allowEmptyValue = true)
   private String endDate;

   @ApiModelProperty(notes = "Show address", position = 7, allowEmptyValue = true)
   private String address;

   @ApiModelProperty(notes = "Show zip code", position = 8, allowEmptyValue = true)
   private String zipCode;

   @ApiModelProperty(notes = "Show city", position = 9, allowEmptyValue = true)
   private String city;

   @ApiModelProperty(notes = "Show organizing club id", position = 10, allowEmptyValue = true)
   private int organizingClubId;

   @ApiModelProperty(notes = "Show french championship", position = 11, allowEmptyValue = true)
   private int frenchChampionship;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getTypeCode() {
      return typeCode;
   }

   public void setTypeCode(String typeCode) {
      this.typeCode = typeCode;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public int getYear() {
      return year;
   }

   public void setYear(int year) {
      this.year = year;
   }

   public String getStartDate() {
      return startDate;
   }

   public void setStartDate(String startDate) {
      this.startDate = startDate;
   }

   public String getEndDate() {
      return endDate;
   }

   public void setEndDate(String endDate) {
      this.endDate = endDate;
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

   public int getOrganizingClubId() {
      return organizingClubId;
   }

   public void setOrganizingClubId(int organizingClubId) {
      this.organizingClubId = organizingClubId;
   }

   public int getFrenchChampionship() {
      return frenchChampionship;
   }

   public void setFrenchChampionship(int frenchChampionship) {
      this.frenchChampionship = frenchChampionship;
   }

   public ShowObject withId(int id) {
      this.setId(id);
      return this;
   }

   public ShowObject withTypeCode(String typeCode) {
      this.setTypeCode(typeCode);
      return this;
   }

   public ShowObject withDescription(String description) {
      this.setDescription(description);
      return this;
   }

   public ShowObject withYear(int year) {
      this.setYear(year);
      return this;
   }

   public ShowObject withStartDate(String startDate) {
      this.setStartDate(startDate);
      return this;
   }

   public ShowObject withEndDate(String endDate) {
      this.setEndDate(endDate);
      return this;
   }

   public ShowObject withAddress(String address) {
      this.setAddress(address);
      return this;
   }

   public ShowObject withZipCode(String zipCode) {
      this.setZipCode(zipCode);
      return this;
   }

   public ShowObject withCity(String city) {
      this.setCity(city);
      return this;
   }

   public ShowObject withOrganizingClubId(int organizingClubId) {
      this.setOrganizingClubId(organizingClubId);
      return this;
   }

   public ShowObject withFrenchChampionship(int frenchChampionship) {
      this.setFrenchChampionship(frenchChampionship);
      return this;
   }

}
