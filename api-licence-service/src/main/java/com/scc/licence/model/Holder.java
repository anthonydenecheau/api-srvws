package com.scc.licence.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ws_licence_licencie")
public class Holder implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "id", nullable = false)
   private int id;

   @Column(name = "num_licence")
   private String number;

   @Column(name = "civilite")
   private String civility;

   @Column(name = "nom")
   private String lastname;

   @Column(name = "prenom")
   private String firstname;

   @Column(name = "date_naissance")
   private String dateOfBirth;

   @Column(name = "adresse")
   private String address;

   @Column(name = "complement_adresse")
   private String additionalAddress;

   @Column(name = "code_postal")
   private String zipCode;

   @Column(name = "ville")
   private String city;

   @Column(name = "pays")
   private String country;

   @Column(name = "mail")
   private String email;

   @Column(name = "id_commission")
   private int idCommission;
   
   @Column(name = "date_maj")
   private Timestamp timestamp;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getNumber() {
      return number;
   }

   public void setNumber(String number) {
      this.number = number;
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

   public void setLastname(String lastname) {
      this.lastname = lastname;
   }

   public String getFirstname() {
      return firstname;
   }

   public void setFirstname(String firstname) {
      this.firstname = firstname;
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

   public String getDateOfBirth() {
      return dateOfBirth;
   }

   public void setDateOfBirth(String dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
   }

   public String getAdditionalAddress() {
      return additionalAddress;
   }

   public void setAdditionalAddress(String additionalAddress) {
      this.additionalAddress = additionalAddress;
   }
   
   public int getIdCommission() {
      return idCommission;
   }

   public void setIdCommission(int idCommission) {
      this.idCommission = idCommission;
   }
   
   public Timestamp getTimestamp() {
      return timestamp;
   }

   public void setTimestamp(Timestamp timestamp) {
      this.timestamp = timestamp;
   }

   public Holder withId(int id) {
      this.setId(id);
      return this;
   }

   public Holder withNumber(String number) {
      this.setNumber(number);
      return this;
   }

   public Holder withCivility(String civility) {
      this.setCivility(civility);
      return this;
   }

   public Holder withLastname(String lastname) {
      this.setLastname(lastname);
      return this;
   }

   public Holder withFirstname(String firstname) {
      this.setFirstname(firstname);
      return this;
   }

   public Holder withAddress(String address) {
      this.setAddress(address);
      return this;
   }

   public Holder withZipCode(String zipCode) {
      this.setZipCode(zipCode);
      return this;
   }

   public Holder withCity(String city) {
      this.setCity(city);
      return this;
   }

   public Holder withCountry(String country) {
      this.setCountry(country);
      return this;
   }

   public Holder withEmail(String email) {
      this.setEmail(email);
      return this;
   }

   public Holder withDateOfBirth(String dateOfBirth) {
      this.setDateOfBirth(dateOfBirth);
      return this;
   }

   public Holder withAdditionalAddress(String additionalAddress) {
      this.setAdditionalAddress(additionalAddress);
      return this;
   }

   public Holder withIdCommission(int idCommission) {
      this.setIdCommission(idCommission);
      return this;
   }

}
