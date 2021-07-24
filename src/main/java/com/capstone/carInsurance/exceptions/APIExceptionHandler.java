package com.capstone.carInsurance.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capstone.carInsurance.utility.APIResponse;

@ControllerAdvice
public class APIExceptionHandler {

	@ExceptionHandler(DriverNotFoundException.class)
	public ResponseEntity<APIResponse> handleDriverNotFoundException(DriverNotFoundException exception) {
		APIResponse errorResponse = new APIResponse("NOT FOUND", exception.getMessage(), null);

		return new ResponseEntity<APIResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<APIResponse> handleInvalidArgumentsException(MethodArgumentNotValidException exception) {
		List<String> errors = exception.getBindingResult().getAllErrors().stream()
				.map(error -> ((FieldError) error).getField() + " : " + error.getDefaultMessage())
				.collect(Collectors.toList());

		APIResponse errorResponse = new APIResponse("INVALID ARGUMENTS", "",
				errors.stream().collect(Collectors.joining(",", "[", "]")));

		return new ResponseEntity<APIResponse>(errorResponse, HttpStatus.BAD_REQUEST);

	}
}
