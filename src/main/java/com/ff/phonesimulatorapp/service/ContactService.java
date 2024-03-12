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

// edit contact details
	public ResponseEntity<ResponseStructure<Contact>> editContact(String name, Contact contact) {
	    Contact receivedContact = contactDao.findContact(name);
	    
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
	        response.setStatusCode(HttpStatus.CREATED.value());
	        response.setData(receivedContact);
	        response.setMessage("Contact Saved Successfully...!");
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    } else {
	        throw new ContactNotFoundException();
	    }
	}


//delete contact
	public ResponseEntity<ResponseStructure<Contact>> deleteContact(String contactName) {
		Contact recievedContact = contactDao.findContact(contactName);
		if (recievedContact != null) {
			boolean deletedContact = contactDao.deleteContact(contactName);
			if (deletedContact == true) {
				ResponseStructure<Contact> response = new ResponseStructure<Contact>();
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("Contact deleted Suceesfully...!");
				return new ResponseEntity<ResponseStructure<Contact>>(response, HttpStatus.OK);

			} else {
				throw new ContactNotFoundException();
			}

		} else {
			throw new ContactNotFoundException();
		}

	}


}
