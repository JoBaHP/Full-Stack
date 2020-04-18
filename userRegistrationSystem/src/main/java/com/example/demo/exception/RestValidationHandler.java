/**
 * 
 */
package com.example.demo.exception;

import java.awt.TrayIcon.MessageType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author jovan https://github.com/JoBaHP
 *                 https://gitlab.com/JoBaHP
 */
/* @ControllerAdvice annotation is used to define a global exception handler
 * for an exception handle method annotated using the @ExceptionHandler annotation
 * This class will be applicable to all controllers 
 * So, any exception thrown by any controller class in that application will be handled by this
 * annotated class having a method annotated with the @ExceptionHandler annotation
 */
@ControllerAdvice
public class RestValidationHandler {
	
	//read messages from the properties file and use them during
	//FieldValidationErrorinstance creation
	private MessageSource messageSource;
	//To read the properties file, autowire the MessageSource
	//by passing the argument in the constructor
	@Autowired
	public RestValidationHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	// this method handle validation error
	//populating it with appropriate information by calling the setter method for different fields
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<FieldValidationErrorDetails> handleValidationError(
	                     MethodArgumentNotValidException mNotValidException,
	                     HttpServletRequest request) {
		
		FieldValidationErrorDetails fErrorDetails = new FieldValidationErrorDetails();
	
	
	fErrorDetails.setError_timeStamp(new Date().getTime());
    fErrorDetails.setError_status(HttpStatus.BAD_REQUEST.value());
    fErrorDetails.setError_title("Field Validation Error");
    fErrorDetails.setError_detail("Inut Field Validation Failed");
    fErrorDetails.setError_dev_msg( mNotValidException.getClass().getName());
    fErrorDetails.setError_path(request.getRequestURI());
    BindingResult result = mNotValidException.getBindingResult();
    List<FieldError> fieldErrors = result.getFieldErrors();
    
    for (FieldError error : fieldErrors) {
        FieldValidationError fError = processFieldError(error);
        List<FieldValidationError> fValidationErrorsList =
                fErrorDetails.getErrors().get(error.getField());
        if (fValidationErrorsList == null) {
                fValidationErrorsList =
                        new ArrayList<FieldValidationError>();
        }
        fValidationErrorsList.add(fError);
        fErrorDetails.getErrors().put(
                        error.getField(), fValidationErrorsList);
}
    return new ResponseEntity<FieldValidationErrorDetails>(
                        fErrorDetails, HttpStatus.BAD_REQUEST);
	}

    // method to process field error
    private FieldValidationError processFieldError(final FieldError error) {
            FieldValidationError fieldValidationError =
                                      new FieldValidationError();
            if (error != null) {
            	// using getMessage to retrieve messages from the properties
            	// file based on currentLocale
            	 Locale currentLocale = LocaleContextHolder.getLocale();
                 String msg = messageSource.getMessage(
                         error.getDefaultMessage(), null, currentLocale);
                    fieldValidationError.setField(error.getField());
                    fieldValidationError.setType(MessageType.ERROR);
                    fieldValidationError.setMessage(error.getDefaultMessage());
            }
            return fieldValidationError;
    }



}
