package com.ff.phonesimulatorapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ff.phonesimulatorapp.dto.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ContactNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> catchContactNotFoundException(ContactNotFoundException exception) {

		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setMessage("BAD Request");
		response.setMessage(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.BAD_REQUEST);

	}

}
