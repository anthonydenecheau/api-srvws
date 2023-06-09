package com.scc.health.exception;

import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import javax.validation.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

   private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

   /**
    * Handle MissingServletRequestParameterException. Triggered when a 'required'
    * request parameter is missing.
    *
    * @param ex
    *           MissingServletRequestParameterException
    * @param headers
    *           HttpHeaders
    * @param status
    *           HttpStatus
    * @param request
    *           WebRequest
    * @return the ApiError object
    */
   @Override
   protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
         HttpHeaders headers, HttpStatus status, WebRequest request) {
      String error = ex.getParameterName() + " parameter is missing";
      return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
   }

   /**
    * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is
    * invalid as well.
    *
    * @param ex
    *           HttpMediaTypeNotSupportedException
    * @param headers
    *           HttpHeaders
    * @param status
    *           HttpStatus
    * @param request
    *           WebRequest
    * @return the ApiError object
    */
   @Override
   protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
         HttpHeaders headers, HttpStatus status, WebRequest request) {
      StringBuilder builder = new StringBuilder();
      builder.append(ex.getContentType());
      builder.append(" media type is not supported. Supported media types are ");
      ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
      return buildResponseEntity(
            new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex));
   }

   /**
    * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid
    * validation.
    *
    * @param ex
    *           the MethodArgumentNotValidException that is thrown when @Valid
    *           validation fails
    * @param headers
    *           HttpHeaders
    * @param status
    *           HttpStatus
    * @param request
    *           WebRequest
    * @return the ApiError object
    */
   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
         HttpHeaders headers, HttpStatus status, WebRequest request) {
      ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
      apiError.setMessage("Validation error");
      apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
      apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
      return buildResponseEntity(apiError);
   }

   /**
    * Handles javax.validation.ConstraintViolationException. Thrown when @Validated
    * fails.
    *
    * @param ex
    *           the ConstraintViolationException
    * @return the ApiError object
    */
   @ExceptionHandler(javax.validation.ConstraintViolationException.class)
   protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
      ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
      apiError.setMessage("Validation error");
      apiError.addValidationErrors(ex.getConstraintViolations());
      return buildResponseEntity(apiError);
   }

   /**
    * Handles EntityNotFoundException. Created to encapsulate errors with more
    * detail than javax.persistence.EntityNotFoundException.
    *
    * @param ex
    *           the EntityNotFoundException
    * @return the ApiError object
    */
   @ExceptionHandler(EntityNotFoundException.class)
   protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
      ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
      apiError.setMessage(ex.getMessage());
      return buildResponseEntity(apiError);
   }

   /**
    * Handles EnumValidationException.
    * 
    * @param ex
    *           the EnumValidationException
    * @return the ApiError object
    */
   @ExceptionHandler(EnumValidationException.class)
   protected ResponseEntity<Object> handleEnumValidation(EnumValidationException ex) {
      ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
      apiError.setMessage(ex.getMessage());
      return buildResponseEntity(apiError);
   }

   @ExceptionHandler(RegisterStudyException.class)
   protected ResponseEntity<Object> handleRegisterStudyException(RegisterStudyException ex) {
      ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
      apiError.setMessage(ex.getMessage());
      return buildResponseEntity(apiError);
   }

   @ExceptionHandler(DocumentStudyException.class)
   protected ResponseEntity<Object> handleDocumentStudyException(DocumentStudyException ex) {
      ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
      apiError.setMessage(ex.getMessage());
      return buildResponseEntity(apiError);
   }
   
   @ExceptionHandler(RestTemplateException.class)
   protected ResponseEntity<Object> handleActivateStudyException(RestTemplateException ex) {
      ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
      apiError.setMessage(ex.getMessage());
      return buildResponseEntity(apiError);
   }

   @ExceptionHandler({ Exception.class })
   public ResponseEntity<Object> handleAll(Exception ex) {
      ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
      apiError.setMessage(ex.getMessage());
      return buildResponseEntity(apiError);
   }

   /**
    * Handle javax.persistence.EntityNotFoundException
    */
   @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
   protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
      return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex));
   }

   /**
    * Handle HttpMessageNotReadableException. Happens when request JSON is
    * malformed.
    *
    * @param ex
    *           HttpMessageNotReadableException
    * @param headers
    *           HttpHeaders
    * @param status
    *           HttpStatus
    * @param request
    *           WebRequest
    * @return the ApiError object
    */
   @Override
   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
         HttpHeaders headers, HttpStatus status, WebRequest request) {
      ServletWebRequest servletWebRequest = (ServletWebRequest) request;
      logger.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
      String error = "Malformed JSON request";
      return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
   }

   /**
    * Handle HttpMessageNotWritableException.
    *
    * @param ex
    *           HttpMessageNotWritableException
    * @param headers
    *           HttpHeaders
    * @param status
    *           HttpStatus
    * @param request
    *           WebRequest
    * @return the ApiError object
    */
   @Override
   protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
         HttpHeaders headers, HttpStatus status, WebRequest request) {
      String error = "Error writing JSON output";
      return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
   }

   /**
    * Handle DataIntegrityViolationException, inspects the cause for different DB
    * causes.
    *
    * @param ex
    *           the DataIntegrityViolationException
    * @return the ApiError object
    */
   @ExceptionHandler(DataIntegrityViolationException.class)
   protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
         WebRequest request) {
      if (ex.getCause() instanceof ConstraintViolationException) {
         return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, "Database error", ex.getCause()));
      }
      return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
   }

   /**
    * Handle Exception, handle generic Exception.class
    *
    * @param ex
    *           the Exception
    * @return the ApiError object
    */
   @ExceptionHandler(MethodArgumentTypeMismatchException.class)
   protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
         WebRequest request) {
      ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);

      Class<?> type = ex.getRequiredType();
      String message;
      if (type.isEnum()) {
         message = "The parameter " + ex.getName() + " must have a value among : "
               + StringUtils.join(type.getEnumConstants(), ", ");
      } else {
         message = "The parameter " + ex.getName() + " must be of type " + type.getTypeName();
      }

      apiError.setMessage(message);
      apiError.setDebugMessage(ex.getMessage());
      return buildResponseEntity(apiError);
   }

   @Override
   protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
         HttpStatus status, WebRequest request) {
      return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex));
   }

   private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
      return new ResponseEntity<>(apiError, apiError.getStatus());
   }
}
