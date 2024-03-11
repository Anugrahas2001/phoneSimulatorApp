package com.ff.phonesimulatorapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.phonesimulatorapp.dao.ContactDao;
import com.ff.phonesimulatorapp.dto.ResponseStructure;
import com.ff.phonesimulatorapp.entity.Contact;
import com.ff.phonesimulatorapp.exception.ContactNotFoundException;

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
		} else {
			ResponseStructure<Contact> response = new ResponseStructure<Contact>();
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Contact not saved...!");
			return new ResponseEntity<ResponseStructure<Contact>>(response, HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<ResponseStructure<Contact>> editContact(int id, Contact contact) {
		Contact recievedContact = contactDao.findContact(id);
		if (recievedContact != null) {
			Contact updatedContact = new Contact();
			updatedContact.setContactName(contact.getContactName());
			updatedContact.setContactnum(contact.getContactnum());
			updatedContact.setContactGroup(contact.getContactGroup());
			contactDao.saveContact(updatedContact);
			ResponseStructure<Contact> response = new ResponseStructure<Contact>();
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setData(updatedContact);
			response.setMessage("Contact Saved Suceesfully...!");
			return new ResponseEntity<ResponseStructure<Contact>>(response, HttpStatus.CREATED);

		} else {
			throw new ContactNotFoundException();
		}
	}

}
