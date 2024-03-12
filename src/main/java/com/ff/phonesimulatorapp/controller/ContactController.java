package com.ff.phonesimulatorapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.phonesimulatorapp.dto.ResponseStructure;
import com.ff.phonesimulatorapp.entity.Contact;
import com.ff.phonesimulatorapp.service.ContactService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@Operation(description = "To Create Contact info", summary = "Contact will be saved in the database")
	@ApiResponses(value = { @ApiResponse(description = "Contact Created", responseCode = "201"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PostMapping("/add")
	public ResponseEntity<ResponseStructure<Contact>> saveContact(@RequestBody Contact contact) {
		return contactService.saveContact(contact);
	}

	@Operation(description = "To get all contact info", summary = "Contacts will be retrived from the database")
	@ApiResponses(value = { @ApiResponse(description = "All Contacts retrieved", responseCode = "201"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@GetMapping("/getAllContacts")
	public ResponseEntity<ResponseStructure<List<Contact>>> getAllContacts() {
		return contactService.getAllContacts();
	}

	@Operation(description = "To Update Contact info", summary = "Contact Details will be updated into the database")
	@ApiResponses(value = { @ApiResponse(description = "Contact Details Updated", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PutMapping("/edit/{contactName}")
	public ResponseEntity<ResponseStructure<Contact>> editContact(@PathVariable String contactName,
			@RequestBody Contact contact) {
		return contactService.editContact(contactName, contact);
	}

	@Operation(description = "To Delete Contact Info ", summary = "Contact will be deleted from the database")
	@ApiResponses(value = { @ApiResponse(description = "Contact Deleted", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "404") })
	@DeleteMapping("/delete/{contactName}")
	public ResponseEntity<ResponseStructure<Contact>> deleteContact(@PathVariable String contactName) {
		return contactService.deleteContact(contactName);
	}

}
