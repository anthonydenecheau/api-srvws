package com.scc.show.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ws_show_club")
public class Club {

   @Id
   @Column(name = "id", nullable = false)
   private int id;

   @Column(name = "name")
   private String name;

   @Column(name = "adresse")
   private String address;

   @Column(name = "code_postal")
   private String zipCode;

   @Column(name = "ville")
   private String city;

   @Column(name = "mail_inscription")
   private String inscriptionEmail;

   @Column(name = "mail_contact")
   private String infoEmail;

   @Column(name = "telephone_contact")
   private String telephone;

   @Column(name = "date_maj")
   private Timestamp timestamp;

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

   public Timestamp getTimestamp() {
      return timestamp;
   }

   public void setTimestamp(Timestamp timestamp) {
      this.timestamp = timestamp;
   }

   public Club withId(int id) {
      this.setId(id);
      return this;
   }

   public Club withName(String name) {
      this.setName(name);
      return this;
   }

   public Club withAddress(String address) {
      this.setAddress(address);
      return this;
   }

   public Club withZipCode(String zipCode) {
      this.setZipCode(zipCode);
      return this;
   }

   public Club withCity(String city) {
      this.setCity(city);
      return this;
   }

   public Club withInscriptionEmail(String inscriptionEmail) {
      this.setInscriptionEmail(inscriptionEmail);
      return this;
   }

   public Club withInfoEmail(String infoEmail) {
      this.setInfoEmail(infoEmail);
      return this;
   }

   public Club withTelephone(String telephone) {
      this.setTelephone(telephone);
      return this;
   }

   public Club withTimestamp(Timestamp timestamp) {
      this.setTimestamp(timestamp);
      return this;
   }

}
