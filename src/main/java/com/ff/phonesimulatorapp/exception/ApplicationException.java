package com.ff.phonesimulatorapp.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ApplicationException extends RuntimeException {
	String message = "";

	@Override
	public String getMessage() {
		return message;
	}

}
