package com.scc.icad.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.scc.icad.domain.Dog;
import com.scc.icad.service.IcadService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "dog selection", description = "Return ICAD information about dog")
public class IcadController {

    private static final Logger log = LoggerFactory.getLogger(IcadController.class);

    @Autowired
    private IcadService icadService;

    @ApiOperation(value = "View icad information by token", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved dog"),
          @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
          @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/v1/dogs/token/{token}", method = RequestMethod.GET)
    public ResponseEntity<Dog> getIcadDogByToken(@ApiParam(value = "token (chip or tatoo)", required = true) @PathVariable("token") String token) {

       Dog _dog = null;
        _dog = icadService.getIcadDogByToken(token);

        if (_dog != null) {
            return ResponseEntity.ok()
                    .body(_dog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
