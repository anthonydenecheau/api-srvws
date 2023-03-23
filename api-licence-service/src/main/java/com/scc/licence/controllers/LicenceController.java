package com.scc.licence.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scc.licence.services.LicenceService;
import com.scc.licence.template.LicenceObject;
import com.scc.licence.template.ResponseObjectList;
import com.scc.licence.utils.CommissionEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "v1/licences")
@Api(value = "licence selection", description = "Return licence data")
public class LicenceController {

   @Autowired
   private LicenceService licenceService;

   @ApiOperation(value = "View licences holders information by commission", response = ResponseObjectList.class)
   @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved licence holders"),
         @ApiResponse(code = 400, message = "You are trying to reach the resource with invalid parameters"),
         @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
         @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
         @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
   @RequestMapping(value = "/holders/{commission}", method = RequestMethod.GET)
   public ResponseObjectList<LicenceObject> getHoldersByCommission(
         @ApiParam(value = "Commission code", required = true) @PathVariable("commission") CommissionEnum commission) {
      return licenceService.getLicencesHolders(commission);
   }

}
