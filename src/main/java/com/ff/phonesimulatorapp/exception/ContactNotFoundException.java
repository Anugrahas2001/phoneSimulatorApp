package com.ff.phonesimulatorapp.exception;

public class ContactNotFoundException extends RuntimeException {

	String message = "Contact not found";

	public ContactNotFoundException(String message) {
		this.message = message;
	}

	public ContactNotFoundException() {

	}

	@Override
	public String getMessage() {
		return message;
	}
}
