package com.codewithdurgesh.blogexceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codewithdurgesh.blog.payloads.ApiResponse;

//@ControllerAdvice // This anotation make this class exception handler class/
// with the help of this anotation we does global exceptional handling of
// controller.
@RestControllerAdvice // we will use this if we are working with rest API else @ControllerAdvice tag
						// will use.
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class) // @ExceptionHandler anotation define that which type of
														// Exception we are handling
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {

		System.out.println("===================================");
		String message = ex.getMessage();// Resouenotfound class throwing the message to super class of exception and we
											// are getting that message from super class

		System.out.println("==============" + message);
		ApiResponse apiResponse = new ApiResponse(message, false);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {

		System.out.println("=============");
		Map<String, String> resp = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {

			String fieldName = ((FieldError) error).getField();

			String message = error.getDefaultMessage();

			resp.put(fieldName, message);
		});

		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(ApiException.class)
	// Exception we are handling
	public ResponseEntity<ApiResponse> handleApiException(ApiException ex) {

		String message = ex.getMessage();
// are getting that message from super class

		ApiResponse apiResponse = new ApiResponse(message, true);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
	}

}
