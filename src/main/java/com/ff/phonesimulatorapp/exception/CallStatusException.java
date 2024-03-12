package com.ff.phonesimulatorapp.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CallStatusException extends RuntimeException{

	private String message;
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
}
