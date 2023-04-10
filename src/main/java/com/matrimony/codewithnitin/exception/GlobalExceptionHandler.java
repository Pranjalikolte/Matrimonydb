package com.matrimony.codewithnitin.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.matrimony.codewithnitin.payload.ApiResponce;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {

		String message = ex.getMessage();
		ApiResponce apiResponce = new ApiResponce(message, false, HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<ApiResponce>(apiResponce, HttpStatus.NOT_FOUND);
	}

//	@ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
//        String errorMessage = ex.getConstraintViolations().stream()
//                .map(cv -> cv.getPropertyPath() + " " + cv.getMessage())
//                .collect(Collectors.joining(", "));
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
//	} 

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiResponce> handleConstraintViolationException(ConstraintViolationException ex) {
		String errorMessage = ex.getConstraintViolations().stream()
				.map(cv -> cv.getPropertyPath() + " " + cv.getMessage()).collect(Collectors.joining(", "));
		ApiResponce errorResponse = new ApiResponce(errorMessage, false, HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

//	 @ExceptionHandler(MethodArgumentNotValidException.class)
//	    public ResponseEntity<ApiResponce> handleValidationException(MethodArgumentNotValidException ex) {
//	        String errorMessage = ex.getBindingResult().getAllErrors().stream()
//	                .map(ObjectError::getDefaultMessage)
//	                .collect(Collectors.joining(", "));
//	        ApiResponce errorResponse = new ApiResponce(HttpStatus.BAD_REQUEST, errorMessage, false);
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//	    }

}
