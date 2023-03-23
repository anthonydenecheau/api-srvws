package com.scc.health.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reviewer implements Serializable {

   private static final long serialVersionUID = 1L;

   private int id;
   private int ordinalNumber;
   private String lastName;
   private String firstName;

   public Reviewer withId(int id) {
      this.setId(id);
      return this;
   }

   public Reviewer withOrdinalNumber(int ordinalNumber) {
      this.setOrdinalNumber(ordinalNumber);
      return this;
   }

   public Reviewer withLastName(String lastName) {
      this.setLastName(lastName);
      return this;
   }

   public Reviewer withFirstName(String firstName) {
      this.setFirstName(firstName);
      return this;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
      result = prime * result + id;
      result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
      result = prime * result + ordinalNumber;
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Reviewer other = (Reviewer) obj;
      if (firstName == null) {
         if (other.firstName != null)
            return false;
      } else if (!firstName.equals(other.firstName))
         return false;
      if (id != other.id)
         return false;
      if (lastName == null) {
         if (other.lastName != null)
            return false;
      } else if (!lastName.equals(other.lastName))
         return false;
      if (ordinalNumber != other.ordinalNumber)
         return false;
      return true;
   }
}
