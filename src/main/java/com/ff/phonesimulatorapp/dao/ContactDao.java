package com.ff.phonesimulatorapp.dao;

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

}