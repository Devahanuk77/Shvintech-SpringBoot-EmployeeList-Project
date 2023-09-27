package com.demo.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.exception.EmptyInputException;
import com.demo.exception.ErrorResponse;
import com.demo.exception.InvalidSortOrderException;
import com.demo.exception.MissingFieldsException;
import com.demo.exception.PageNotFoundException;

@RestControllerAdvice
public class EmployeeControllerAdvice  {
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }
	
	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException){
		return new ResponseEntity<String>("Input field is empty, please change that ", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleEmptyInput(NoSuchElementException exception){
		return new ResponseEntity<>("No value present in database, Please change your request", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MissingFieldsException.class)
    public ResponseEntity<ErrorResponse> handleMissingFieldsException(MissingFieldsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
	 
	 
	 @ExceptionHandler(PageNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handlePageNotFoundException(PageNotFoundException ex) {
	        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }
	 
	 @ExceptionHandler(InvalidSortOrderException.class)
	    public ResponseEntity<ErrorResponse> handleInvalidSortOrderException(InvalidSortOrderException ex) {
	        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }

	

	
	

}
