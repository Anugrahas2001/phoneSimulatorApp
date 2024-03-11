package com.ff.phonesimulatorapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.phonesimulatorapp.dao.ContactDao;
import com.ff.phonesimulatorapp.dto.ResponseStructure;
import com.ff.phonesimulatorapp.entity.Contact;

@Service
public class ContactService {
	@Autowired
	private ContactDao contactDao;

//save the contact details
	public ResponseEntity<ResponseStructure<Contact>> saveContact(Contact contact) {
		Contact receivedContact = contactDao.saveContact(contact);
		if (receivedContact != null) {
			ResponseStructure<Contact> response = new ResponseStructure<Contact>();
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setData(receivedContact);
			response.setMessage("Contact Saved Suceesfully...!");
			return new ResponseEntity<ResponseStructure<Contact>>(response, HttpStatus.CREATED);
		} else
			throw new ApplicationContextException("Contact not saved");

	}

}
