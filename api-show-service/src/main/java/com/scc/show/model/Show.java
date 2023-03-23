package com.scc.show.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ws_show_expos")
public class Show {

   @Id
   @Column(name = "id", nullable = false)
   private int id;

   @Column(name = "code")
   private String typeCode;

   @Column(name = "description")
   private String description;

   @Column(name = "annee")
   private int year;

   @Column(name = "date_debut")
   private String startDate;

   @Column(name = "date_fin")
   private String endDate;

   @Column(name = "adresse")
   private String address;

   @Column(name = "code_postal")
   private String zipCode;

   @Column(name = "ville")
   private String city;

   @Column(name = "id_club")
   private int organizingClubId;

   @Column(name = "date_maj")
   private Timestamp timestamp;

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

   public Timestamp getTimestamp() {
      return timestamp;
   }

   public void setTimestamp(Timestamp timestamp) {
      this.timestamp = timestamp;
   }

   public Show withId(int id) {
      this.setId(id);
      return this;
   }

   public Show withTypeCode(String typeCode) {
      this.setTypeCode(typeCode);
      return this;
   }

   public Show withDescription(String description) {
      this.setDescription(description);
      return this;
   }

   public Show withYear(int year) {
      this.setYear(year);
      return this;
   }

   public Show withStartDate(String startDate) {
      this.setStartDate(startDate);
      return this;
   }

   public Show withEndDate(String endDate) {
      this.setEndDate(endDate);
      return this;
   }

   public Show withAddress(String address) {
      this.setAddress(address);
      return this;
   }

   public Show withZipCode(String zipCode) {
      this.setZipCode(zipCode);
      return this;
   }

   public Show withCity(String city) {
      this.setCity(city);
      return this;
   }

   public Show withOrganizingClubId(int organizingClubId) {
      this.setOrganizingClubId(organizingClubId);
      return this;
   }

   public Show withTimestamp(Timestamp timestamp) {
      this.setTimestamp(timestamp);
      return this;
   }

}
