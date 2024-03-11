package com.ff.phonesimulatorapp.service;

import org.springframework.beans.factory.annotation.Autowired;
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
public ResponseEntity<ResponseStructure<Contact>>saveContact(Contact contact){
	contactDao.saveContact(contact);
	return null;
}









}
