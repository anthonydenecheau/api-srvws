package com.scc.icad.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dog {

   @ApiModelProperty(notes = "Dog registration", position = 1, allowEmptyValue = true)
   private String validity;

   /*
   @ApiModelProperty(notes = "Dog name", position = 2, allowEmptyValue = true)
   private String name;

   @ApiModelProperty(notes = "Dog gender", position = 3, allowEmptyValue = true)
   private String gender;

   @ApiModelProperty(notes = "Dog Date of Birth", position = 4, allowEmptyValue = true)
   private String birthDate;

   @ApiModelProperty(notes = "Dog Date of Deceased", position = 5, allowEmptyValue = true)
   private String deceasedDate;

   @ApiModelProperty(notes = "Dog Disabled", position = 6, allowEmptyValue = true)
   private String disable;

   @ApiModelProperty(notes = "Dog Tatoo", position = 7, allowEmptyValue = true)
   private String tatoo;

   @ApiModelProperty(notes = "Dog Chip", position = 8, allowEmptyValue = true)
   private String chip;

   @ApiModelProperty(notes = "Dog breed", position = 9, allowEmptyValue = true)
   private String breed;

   @ApiModelProperty(notes = "Dog renewal identification", position = 10, allowEmptyValue = true)
   private String renewalIdentification;

   @ApiModelProperty(notes = "Dog owner", position = 11, allowEmptyValue = true)
   private Owner owner;
   */
   
   public Dog withValidity(String validity) {
      this.setValidity(validity);
      return this;
   }   
}
