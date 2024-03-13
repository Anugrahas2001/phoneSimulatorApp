package com.ff.phonesimulatorapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.phonesimulatorapp.entity.Contact;
import com.ff.phonesimulatorapp.repository.ContactRepository;

@Repository
public class ContactDao {

	@Autowired
	private ContactRepository contactRepository;

//save Contact details
	public Contact saveContact(Contact contact) {
		return contactRepository.save(contact);
	}

//find the contact by name
	public Contact findContactbyName(String name) {
		Contact contact = contactRepository.findByContactName(name);
		if (contact != null) {
			return contact;
		}
		return null;
	}

	public List<Contact> getAllContacts() {
		List<Contact> contactList = contactRepository.findAll();
		return contactList;
	}

//find the contact by id
	public Contact findContactbyId(int id) {
		Optional<Contact> option = contactRepository.findById(id);
		if (option.isPresent()) {
			return option.get();

		} else
			return null;

	}

	public void deleteContact(Contact contact) {
		contactRepository.delete(contact);
	}

}