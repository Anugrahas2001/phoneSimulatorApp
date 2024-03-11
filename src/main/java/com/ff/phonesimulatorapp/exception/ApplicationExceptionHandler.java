package com.ff.phonesimulatorapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ff.phonesimulatorapp.dto.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler {
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ResponseStructure<String>> catchApplicationException(ApplicationException exception) {

		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
		rs.setMessage("BAD Request");
		rs.setMessage(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.BAD_REQUEST);

	}

}
