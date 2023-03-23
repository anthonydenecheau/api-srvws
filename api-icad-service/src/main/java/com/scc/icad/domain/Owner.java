package com.scc.icad.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Owner {

    @ApiModelProperty(notes = "Owner lastname", position = 1, allowEmptyValue = true)
    private String lastName;

    @ApiModelProperty(notes = "Owner firstname", position = 2, allowEmptyValue = true)
    private String firstName;

    @ApiModelProperty(notes = "Owner flat building", position = 3, allowEmptyValue = true)
    private String flat;

    @ApiModelProperty(notes = "Owner building", position = 4, allowEmptyValue = true)
    private String building;

    @ApiModelProperty(notes = "Owner address", position = 5, allowEmptyValue = true)
    private String address;

    @ApiModelProperty(notes = "Owner post box", position = 6, allowEmptyValue = true)
    private String postbox;

    @ApiModelProperty(notes = "Owner zip code", position = 7, allowEmptyValue = true)
    private String zipCode;

    @ApiModelProperty(notes = "Owner town", position = 8, allowEmptyValue = true)
    private String town;

    @ApiModelProperty(notes = "Owner country", position = 9, allowEmptyValue = true)
    private String country;
}
