package com.scc.health.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scc.health.service.HealthService;
import com.scc.health.template.ReviewerObject;
import com.scc.health.template.ResponseObjectList;
import com.scc.health.utils.DiseaseEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "v1/reviewers")
@Api(value = "health selection", description = "Return health information")
public class HealthController {

   @Autowired
   private HealthService healthService;

   @ApiOperation(value = "Reviewer information about disease (MyPacs) by dogId (Optional)", response = ResponseObjectList.class)
   @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved readers"),
         @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
         @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
         @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
   @RequestMapping(value = "/{disease}", method = RequestMethod.GET)
   public ResponseObjectList<ReviewerObject> getReaderMyPacsByIdDog(
         @ApiParam(value = "Disease type code", required = true) @PathVariable("disease") DiseaseEnum disease,
         @ApiParam(value = "Dog Id", required = false) @RequestParam Optional<Integer> dogId) {

      int _dogId = 0;
      if (dogId.isPresent())
         _dogId = dogId.get();
      else
         _dogId = 0;
      
      return healthService.getReaderMyPacsByIdDog(_dogId, disease);
   }

}
