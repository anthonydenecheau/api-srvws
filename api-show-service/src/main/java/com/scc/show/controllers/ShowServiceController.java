package com.scc.show.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scc.show.services.ShowService;
import com.scc.show.template.ResponseObjectList;
import com.scc.show.template.ShowObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping(value = "v1/shows")
@Api(value = "shows selection", description = "Return shows data")
public class ShowServiceController {

   @Autowired
   private ShowService showService;

   @ApiOperation(value = "View shows information", response = ResponseObjectList.class)
   @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved shows"),
         @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
         @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
         @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
   @RequestMapping(method = RequestMethod.GET)
   public ResponseObjectList<ShowObject> getShows() {
      return showService.getShows();
   }

}
