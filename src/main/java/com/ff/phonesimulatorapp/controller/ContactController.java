package com.ff.phonesimulatorapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
=======
import org.springframework.web.bind.annotation.PathVariable;
>>>>>>> 7e1242cf7c8006f3fbfee93346c8d1dc13811bda
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
<<<<<<< HEAD

@GetMapping("/getAllContacts")
public ResponseEntity<ResponseStructure<List<Contact>>> getAllContacts(){
	return contactService.getAllContacts();
}





=======
@PutMapping("/edit/{contactId}")
public ResponseEntity<ResponseStructure<Contact>>editContact(@PathVariable int id, @RequestBody Contact contact){
	 
	return contactService.editContact(id,contact);
>>>>>>> 7e1242cf7c8006f3fbfee93346c8d1dc13811bda


}
}