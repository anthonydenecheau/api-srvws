package com.scc.health.utils;

import com.scc.health.exception.EnumValidationException;

public enum DiseaseEnum {

   HD("16"), ED("13");

   public String value;

   private DiseaseEnum(String value) {
      this.value = value;
   }

   public String getValue() {
      return value;
   }

   public static DiseaseEnum fromValue(String value) throws EnumValidationException {

      if (value == "") {
         throw new EnumValidationException(value, "DiseaseEnum");
      }

      for (DiseaseEnum category : values()) {
         if (category.value.equalsIgnoreCase(value)) {
            return category;
         }
      }
      throw new EnumValidationException(value, "DiseaseEnum");
   }

}
