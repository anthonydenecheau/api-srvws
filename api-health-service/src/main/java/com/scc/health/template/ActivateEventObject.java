package com.scc.health.template;

import java.util.Arrays;

import io.swagger.annotations.ApiModelProperty;

public class ActivateEventObject {

   public ActivateEventObject() {
      super();
   }
   public ActivateEventObject(boolean isPaid, String[] reviewerOrdinalNumbers) {
      super();
      this.isPaid = isPaid;
      this.reviewerOrdinalNumbers = reviewerOrdinalNumbers;
   }
   @ApiModelProperty(notes = "Payment study myPacs", position = 1, required = true)
   private boolean isPaid;
   @ApiModelProperty(notes = "Readers", position = 2, allowEmptyValue = false)
   private String[] reviewerOrdinalNumbers;
   
   public boolean getIsPaid() {
      return isPaid;
   }
   public void setIsPaid(boolean isPaid) {
      this.isPaid = isPaid;
   }
   public String[] getReviewerOrdinalNumbers() {
      return reviewerOrdinalNumbers;
   }
   public void setReviewerOrdinalNumbers(String[] reviewerOrdinalNumbers) {
      this.reviewerOrdinalNumbers = reviewerOrdinalNumbers;
   }
   @Override
   public String toString() {
      return "ActivateEventObject [isPaid=" + isPaid + ", reviewerOrdinalNumbers="
            + Arrays.toString(reviewerOrdinalNumbers) + "]";
   }

   
}
