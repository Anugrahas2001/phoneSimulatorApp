package com.ff.phonesimulatorapp.exception;

public class ContactNotFoundException extends RuntimeException {
	
	String message = "Contact not found";

	@Override
	public String getMessage() {
		return message;
	}
}
