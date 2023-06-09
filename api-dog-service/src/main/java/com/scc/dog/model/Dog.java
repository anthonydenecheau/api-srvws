package com.scc.dog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ws_dog")
public class Dog {

   @Id
   @Column(name = "id", nullable = false)
   private int id;

   @Column(name = "nom")
   private String nom;

   @Column(name = "affixe")
   private String affixe;

   @Column(name = "sexe")
   private String sexe;

   @Column(name = "date_naissance")
   private String dateNaissance;

   @Column(name = "date_deces")
   private String dateDeces;

   @Column(name = "pays")
   private String pays;

   @Column(name = "tatouage")
   private String tatouage;

   @Column(name = "transpondeur")
   private String transpondeur;

   @Column(name = "codefci")
   private String codeFci;

   @Column(name = "idrace")
   private int idRace;

   @Column(name = "idvariete")
   private int idVariete;

   @Column(name = "race")
   private String race;

   @Column(name = "variete")
   private String variete;

   @Column(name = "couleur")
   private String couleur;

   @Column(name = "couleur_abr")
   private String couleurAbr;

   @Column(name = "code_inscription")
   private String inscriptionCode;

   @Column(name = "id_etalon")
   private int idEtalon;

   @Column(name = "id_lice")
   private int idLice;

   @Column(name = "date_maj")
   private Timestamp timestamp;

   @Column(name = "on_travail_nat")
   private String onTravailNational;

   @Column(name = "on_travail_int")
   private String onTravailInternational;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getNom() {
      return nom;
   }

   public void setNom(String nom) {
      this.nom = nom;
   }

   public String getAffixe() {
      return affixe;
   }

   public void setAffixe(String affixe) {
      this.affixe = affixe;
   }

   public String getSexe() {
      return sexe;
   }

   public void setSexe(String sexe) {
      this.sexe = sexe;
   }

   public String getDateNaissance() {
      return dateNaissance;
   }

   public void setDateNaissance(String dateNaissance) {
      this.dateNaissance = dateNaissance;
   }

   public String getDateDeces() {
      return dateDeces;
   }

   public void setDateDeces(String dateDeces) {
      this.dateDeces = dateDeces;
   }

   public String getPays() {
      return pays;
   }

   public void setPays(String pays) {
      this.pays = pays;
   }

   public String getTatouage() {
      return tatouage;
   }

   public void setTatouage(String tatouage) {
      this.tatouage = tatouage;
   }

   public String getTranspondeur() {
      return transpondeur;
   }

   public void setTranspondeur(String transpondeur) {
      this.transpondeur = transpondeur;
   }

   public String getCodeFci() {
      return codeFci;
   }

   public void setCodeFci(String codeFci) {
      this.codeFci = codeFci;
   }

   public int getIdRace() {
      return idRace;
   }

   public void setIdRace(int idRace) {
      this.idRace = idRace;
   }

   public int getIdVariete() {
      return idVariete;
   }

   public void setIdVariete(int idVariete) {
      this.idVariete = idVariete;
   }

   public String getRace() {
      return race;
   }

   public void setRace(String race) {
      this.race = race;
   }

   public String getVariete() {
      return variete;
   }

   public void setVariete(String variete) {
      this.variete = variete;
   }

   public String getCouleur() {
      return couleur;
   }

   public void setCouleur(String couleur) {
      this.couleur = couleur;
   }

   public String getCouleurAbr() {
      return couleurAbr;
   }

   public void setCouleurAbr(String couleurAbr) {
      this.couleurAbr = couleurAbr;
   }

   public String getInscriptionCode() {
      return inscriptionCode;
   }

   public void setInscriptionCode(String inscriptionCode) {
      this.inscriptionCode = inscriptionCode;
   }

   public int getIdEtalon() {
      return idEtalon;
   }

   public void setIdEtalon(int idEtalon) {
      this.idEtalon = idEtalon;
   }

   public int getIdLice() {
      return idLice;
   }

   public void setIdLice(int idLice) {
      this.idLice = idLice;
   }

   public String getOnTravailNational() {
      return onTravailNational;
   }

   public void setOnTravailNational(String onTravailNational) {
      this.onTravailNational = onTravailNational;
   }

   public String getOnTravailInternational() {
      return onTravailInternational;
   }

   public void setOnTravailInternational(String onTravailInternational) {
      this.onTravailInternational = onTravailInternational;
   }
   
   public Timestamp getTimestamp() {
      return timestamp;
   }

   public void setTimestamp(Timestamp timestamp) {
      this.timestamp = timestamp;
   }

   public Dog withId(int id) {
      this.setId(id);
      return this;
   }

   public Dog withNom(String nom) {
      this.setNom(nom);
      return this;
   }

   public Dog withAffixe(String affixe) {
      this.setAffixe(affixe);
      return this;
   }

   public Dog withSexe(String sexe) {
      this.setSexe(sexe);
      return this;
   }

   public Dog withDateNaissance(String dateNaissance) {
      this.setDateNaissance(dateNaissance);
      return this;
   }

   public Dog withDateDeces(String dateDeces) {
      this.setDateDeces(dateDeces);
      return this;
   }

   public Dog withPays(String pays) {
      this.setPays(pays);
      return this;
   }

   public Dog withTatouage(String tatouage) {
      this.setTatouage(tatouage);
      return this;
   }

   public Dog withTranspondeur(String transpondeur) {
      this.setTranspondeur(transpondeur);
      return this;
   }

   public Dog withCodeFci(String codeFci) {
      this.setCodeFci(codeFci);
      return this;
   }

   public Dog withIdRace(int idRace) {
      this.setIdRace(idRace);
      return this;
   }

   public Dog withIdVariete(int idVariete) {
      this.setIdVariete(idVariete);
      return this;
   }

   public Dog withRace(String race) {
      this.setRace(race);
      return this;
   }

   public Dog withVariete(String variete) {
      this.setVariete(variete);
      return this;
   }

   public Dog withCouleur(String couleur) {
      this.setCouleur(couleur);
      return this;
   }

   public Dog withCouleurAbr(String couleurAbr) {
      this.setCouleurAbr(couleurAbr);
      return this;
   }

   public Dog withInscriptionCode(String inscriptionCode) {
      this.setInscriptionCode(inscriptionCode);
      return this;
   }

   public Dog withIdEtalon(int idEtalon) {
      this.setIdEtalon(idEtalon);
      return this;
   }

   public Dog withIdLice(int idLice) {
      this.setIdLice(idLice);
      return this;
   }

   public Dog withOnTravailNational(String onTravailNational) {
      this.setOnTravailNational(onTravailNational);
      return this;
   }

   public Dog withOnTravailInternational(String onTravailInternational) {
      this.setOnTravailInternational(onTravailInternational);
      return this;
   }

   public Dog withTimestamp(Timestamp timestamp) {
      this.setTimestamp(timestamp);
      return this;
   }

   @Override
   public String toString() {
      return "Dog [id=" + id + ", nom=" + nom + ", affixe=" + affixe + ", sexe=" + sexe + ", dateNaissance="
            + dateNaissance + ", dateDeces=" + dateDeces  + ", pays=" + pays + ", tatouage=" + tatouage + ", transpondeur=" + transpondeur
            + ", codeFci=" + codeFci + ", idRace=" + idRace + ", idVariete=" + idVariete + ", race=" + race
            + ", variete=" + variete + ", couleur=" + couleur + ", couleurAbr=" + couleurAbr + ", inscriptionCode="
            + inscriptionCode + ", idEtalon=" + idEtalon + ", idLice=" + idLice + ", onTravailNational=" + onTravailNational 
            + ", onTravailInternational=" + onTravailInternational
            + ", timestamp=" + timestamp + "]";
   }

}
