package com.ff.phonesimulatorapp.service;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.phonesimulatorapp.configuration.TwilioConfig;
import com.ff.phonesimulatorapp.dao.ContactDao;
import com.ff.phonesimulatorapp.dto.ContactDto;
import com.ff.phonesimulatorapp.dto.ResponseStructure;
import com.ff.phonesimulatorapp.entity.Contact;
import com.ff.phonesimulatorapp.exception.CallStatusException;
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
		List<Contact> contactList = contactDao.getAllContacts();

		ResponseStructure<List<Contact>> responseStructure = new ResponseStructure<List<Contact>>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Contact details retrieved successfully");
		responseStructure.setData(contactList);

		return new ResponseEntity<ResponseStructure<List<Contact>>>(responseStructure, HttpStatus.OK);
	}

// edit contact details
	public ResponseEntity<ResponseStructure<Contact>> editContact(String name, Contact contact) {
		Contact receivedContact = contactDao.findContactbyName(name);

		if (receivedContact != null) {
			if (contact.getContactName() != null) {
				receivedContact.setContactName(contact.getContactName());
			}

			if (contact.getContactNumber() != null) {
				receivedContact.setContactNumber(contact.getContactNumber());
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

	@Autowired
	private TwilioConfig twilioConfig;

	@Autowired
	private TwilioService twilioService;

	public ResponseEntity<ResponseStructure<Contact>> callContact(String contactName) throws URISyntaxException {
		Contact contact = contactDao.findContactbyName(contactName);

		if (contact != null) {
			if (!(contact.isCallInProgress())) {
				// the logic to connect to an actual number requires third party api which
				// should be
				// written here
				twilioService.makeCall(twilioConfig.getToPhoneNumber());

				contact.setCallInProgress(true);
				contactDao.saveContact(contact);
				
				ResponseStructure<Contact> structure = new ResponseStructure<Contact>();
				structure.setStatusCode(HttpStatus.OK.value());
				structure.setMessage("Calling ...");
				structure.setData(contact);

				return new ResponseEntity<ResponseStructure<Contact>>(structure, HttpStatus.OK);
			} else
				throw new CallStatusException("Call already in Progress");
		} else
			throw new ContactNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Contact>> endCall(String contactName) throws URISyntaxException {
		Contact contact = contactDao.findContactbyName(contactName);

		if (contact != null) {
			if (contact.isCallInProgress()) {
				contact.setCallInProgress(false);
				contactDao.saveContact(contact);

				// the logic to end a call requires third party api which should be
				// written here
				twilioService.endCall();

				ResponseStructure<Contact> structure = new ResponseStructure<Contact>();
				structure.setStatusCode(HttpStatus.OK.value());
				structure.setMessage("Call Ended ...");
				structure.setData(contact);

				return new ResponseEntity<ResponseStructure<Contact>>(structure, HttpStatus.OK);
			} else
				throw new CallStatusException("Call not in Progress");
		} else
			throw new ContactNotFoundException();
	}

	public ResponseEntity<ResponseStructure<ContactDto>> messageContact(String contactName, ContactDto contactDto) {

		Contact contact = contactDao.findContactbyName(contactName);

		if (contact != null) {

			// set a remaining fields of ContactDto object to provide as response
			contactDto.setContactName(contact.getContactName());
			contactDto.setContactNumber(contact.getContactNumber());

			// the logic to send a message requires third party api which should be
			// written here
			twilioService.sendSms(contactDto.getMessage());

			ResponseStructure<ContactDto> structure = new ResponseStructure<ContactDto>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Message sent");
			structure.setData(contactDto);

			return new ResponseEntity<ResponseStructure<ContactDto>>(structure, HttpStatus.OK);
		} else
			throw new ContactNotFoundException();
	}

}
