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

import com.ff.phonesimulatorapp.dto.ContactDto;
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

	@Operation(description = "Contact will be saved in the database", summary = "To Create Contact info")
	@ApiResponses(value = { @ApiResponse(description = "Created", responseCode = "201"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PostMapping("/add")
	public ResponseEntity<ResponseStructure<Contact>> saveContact(@RequestBody Contact contact) {
		return contactService.saveContact(contact);
	}

	@Operation(description = "Contacts will be retrived from the database", summary = "To get all contact info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@GetMapping("/getAllContacts")
	public ResponseEntity<ResponseStructure<List<Contact>>> getAllContacts() {
		return contactService.getAllContacts();
	}

	@Operation(description = "Contact Details will be updated into the database", summary = "To Update Contact info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PutMapping("/edit/{contactName}")
	public ResponseEntity<ResponseStructure<Contact>> editContact(@PathVariable String contactName,
			@RequestBody Contact contact) {
		return contactService.editContact(contactName, contact);
	}

	@Operation(description = "Contact will be deleted from the database", summary = "To Delete Contact Info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "404") })
	@DeleteMapping("/delete/{contactName}")
	public ResponseEntity<ResponseStructure<Contact>> deleteContact(@PathVariable String contactName) {
		return contactService.deleteContact(contactName);
	}

	@Operation(description = "A Contact will be called and the status will be in progress", summary = "Call given contactCall given contact")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@GetMapping("/call/{contactName}")
	public ResponseEntity<ResponseStructure<Contact>> callContact(@PathVariable String contactName) {
		return contactService.callContact(contactName);
	}

	@Operation(description = "Call in progress will be ended", summary = "End the Call")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@GetMapping("/endCall/{contactName}")
	public ResponseEntity<ResponseStructure<Contact>> endCall(@PathVariable String contactName) {
		return contactService.endCall(contactName);
	}

	@Operation(description = "Send a message to a given contact", summary = "Send a message")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@GetMapping("/message/{contactName}")
	public ResponseEntity<ResponseStructure<ContactDto>> messageContact(@PathVariable String contactName,
			@RequestBody ContactDto contactDto) {
		return contactService.messageContact(contactName, contactDto);
	}

}
