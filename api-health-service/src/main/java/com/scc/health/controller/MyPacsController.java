package com.scc.health.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.scc.health.exception.DocumentStudyException;
import com.scc.health.exception.RegisterStudyException;
import com.scc.health.service.HealthService;
import com.scc.health.service.DocumentService;
import com.scc.health.template.ActivateEventObject;
import com.scc.health.template.RegisterEventObject;
import com.scc.health.template.ReviewerMyPacsObject;
import com.scc.health.template.StudyObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "v1/myPacs")
@Api(value = "MyPacs selection", description = "myPacs apis")
public class MyPacsController {

   @Value("${myPacs.url.api}")
   String urlMyPacsApi;

   @Value("${myPacs.url.report}")
   String urlMyPacsReport;

   @Autowired
   private RestTemplate restTemplate;

   @Autowired
   private HealthService healthService;

   @Autowired
   private DocumentService DocumentService;

   @ApiOperation(value = "Register study MyPacs", response = ResponseEntity.class)
   @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved readers"),
         @ApiResponse(code = 400, message = "Bad request. Check first diseases sent"),
         @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
         @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
         @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Object> registerStudy(@RequestBody RegisterEventObject event) throws RegisterStudyException {
      healthService.registerMyPacsStudy(event);
      return new ResponseEntity<>(null, HttpStatus.OK);
   }

   @ApiOperation(value = "Tracking study MyPacs", response = StudyObject.class)
   @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved status of the sudy"),
         @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
         @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
         @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
   @RequestMapping(value = "/study/{reference}", method = RequestMethod.GET)
   public StudyObject getTracking(@PathVariable("reference") String reference) {
      ResponseEntity<StudyObject> response = restTemplate.getForEntity(urlMyPacsApi + "/study/" + reference,
            StudyObject.class);
      return response.getBody();
   }

   @ApiOperation(value = "Activate study MyPacs", response = StudyObject.class)
   @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully activation of the sudy"),
         @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
         @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
         @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
   @RequestMapping(value = "/study/{reference}/activate", method = RequestMethod.PATCH)
   public StudyObject activateStudy(@PathVariable("reference") String reference,
         @RequestBody ActivateEventObject event) {
      HttpEntity<ActivateEventObject> request = new HttpEntity<ActivateEventObject>(
            new ActivateEventObject(event.getIsPaid(), event.getReviewerOrdinalNumbers()));
      ResponseEntity<StudyObject> response = restTemplate.exchange(urlMyPacsApi + "/study/" + reference + "/payment",
            HttpMethod.POST, request, StudyObject.class);
      return (StudyObject) response.getBody();
   }

   @ApiOperation(value = "Read reviewer MyPacs", response = ReviewerMyPacsObject.class)
   @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved information of the reviewer"),
         @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
         @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
         @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
   @RequestMapping(value = "/reviewer/{ordinalNumber}", method = RequestMethod.GET)
   public ReviewerMyPacsObject getReviewer(@PathVariable("ordinalNumber") String ordinalNumber) {
      ResponseEntity<ReviewerMyPacsObject> response = restTemplate
            .getForEntity(urlMyPacsApi + "/reviewer/" + ordinalNumber, ReviewerMyPacsObject.class);
      return response.getBody();
   }

   @ApiOperation(value = "Register reviewer MyPacs", response = ReviewerMyPacsObject.class)
   @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully register the reviewer"),
         @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
         @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
         @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
   @RequestMapping(value = "/reviewer", method = RequestMethod.POST)
   public ReviewerMyPacsObject createReviewer(@RequestBody ReviewerMyPacsObject event) {
      HttpEntity<ReviewerMyPacsObject> request = new HttpEntity<ReviewerMyPacsObject>(new ReviewerMyPacsObject(
            event.getOrdinalNumber(), event.getFirstName(), event.getLastName(), event.getMail()));
      ResponseEntity<ReviewerMyPacsObject> response = restTemplate.exchange(urlMyPacsApi + "/reviewer", HttpMethod.POST,
            request, ReviewerMyPacsObject.class);
      return (ReviewerMyPacsObject) response.getBody();
   }

   @ApiOperation(value = "Integrate results of study MyPacs", response = StudyObject.class)
   @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved results of the sudy"),
         @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
         @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
         @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
   @RequestMapping(value = "/study/{reference}/results", method = RequestMethod.GET)
   public StudyObject getResults(@PathVariable("reference") String reference) {
      ResponseEntity<StudyObject> response = restTemplate.getForEntity(urlMyPacsApi + "/study/" + reference,
            StudyObject.class);
      return response.getBody();
   }

   @ApiOperation(value = "Donwload and push report of study MyPacs", response = StudyObject.class)
   @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully download report of the sudy"),
         @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
         @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
         @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
   @RequestMapping(value = "/study/{reference}/report", method = RequestMethod.GET)
   public StudyObject downloadAndPushReport(@PathVariable("reference") String reference,
         @ApiParam(value = "Document Endpoint", required = true) @RequestParam String endpoint) {
     
      try {
         File response = restTemplate.execute(urlMyPacsReport + endpoint, HttpMethod.GET, null, clientHttpResponse -> {
            File ret = File.createTempFile(reference, ".pdf");
            StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
            return ret;
         });
         DocumentService.save(response, reference);
      } catch (Exception e) {
         throw new DocumentStudyException(StudyObject.class, "reference",
               String.valueOf(reference) + "[" + String.valueOf(endpoint) + "] : " + e.getMessage());
      }
      return getTracking(reference);
   }

}
