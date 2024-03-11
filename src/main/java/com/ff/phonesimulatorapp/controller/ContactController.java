package com.ff.phonesimulatorapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.phonesimulatorapp.dto.ResponseStructure;
import com.ff.phonesimulatorapp.entity.Contact;
import com.ff.phonesimulatorapp.service.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController {
@Autowired
private ContactService contactService;

@PostMapping("/add")
public ResponseEntity<ResponseStructure<Contact>>saveContact(@RequestBody Contact contact){
	return contactService.saveContact(contact);
}







}
