package com.ff.phonesimulatorapp.service;

import java.util.List;

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
			response.setMessage("Success");
			return new ResponseEntity<ResponseStructure<Contact>>(response, HttpStatus.CREATED);

		}
		
	 else {
			ResponseStructure<Contact> response = new ResponseStructure<Contact>();
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Contact not saved...!");
			return new ResponseEntity<ResponseStructure<Contact>>(response, HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<ResponseStructure<List<Contact>>> getAllContacts() {
		List<Contact> contactList=contactDao.getAllContacts();
		
			ResponseStructure<List<Contact>> responseStructure=new ResponseStructure<List<Contact>>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Contact details retrieved successfully");
			responseStructure.setData(contactList);
			
			return new ResponseEntity<ResponseStructure<List<Contact>>>(responseStructure,HttpStatus.OK);
	}

// edit contact details
	public ResponseEntity<ResponseStructure<Contact>> editContact(String name, Contact contact) {
		Contact receivedContact = contactDao.findContactbyName(name);

		if (receivedContact != null) {
			if (contact.getContactName() != null) {
				receivedContact.setContactName(contact.getContactName());
			}

			if (contact.getContactnum() != null) {
				receivedContact.setContactnum(contact.getContactnum());
			}
			if (contact.getContactGroup() != null) {
				receivedContact.setContactGroup(contact.getContactGroup());
			}

			contactDao.saveContact(receivedContact);

			ResponseStructure<Contact> response = new ResponseStructure<>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setData(receivedContact);
			response.setMessage("OK");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new ContactNotFoundException();
		}
	}

//delete contact
	public ResponseEntity<ResponseStructure<Contact>> deleteContact(String contactName) {
		Contact recievedContact = contactDao.findContactbyName(contactName);
		if (recievedContact != null) {
			contactDao.deleteContact(recievedContact);
			return new ResponseEntity<ResponseStructure<Contact>>(HttpStatus.OK);

		} else {
			throw new ContactNotFoundException();
		}

	}

}
