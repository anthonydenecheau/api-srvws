package com.scc.licence.utils;

import com.scc.licence.exceptions.EnumValidationException;

public enum CommissionEnum {
	
	CUNCA("2160"), ALL("");
	
	private String value;
	
	private CommissionEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
      return value;
   }

   public static CommissionEnum fromValue(String value) throws EnumValidationException {

        if(value == "") {
            throw new EnumValidationException(value, "CommissionEnum");
        }
        
		for (CommissionEnum category : values()) {
			if (category.value.equalsIgnoreCase(value)) {
				return category;
			}
		}
		throw new EnumValidationException(value, "CommissionEnum");
	}

}
