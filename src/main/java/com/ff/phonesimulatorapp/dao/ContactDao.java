package com.ff.phonesimulatorapp.dao;

<<<<<<< HEAD
<<<<<<< HEAD
import java.util.List;
=======
import java.util.Optional;
>>>>>>> 7e1242cf7c8006f3fbfee93346c8d1dc13811bda
=======
import java.util.Optional;
>>>>>>> 7e1242cf7c8006f3fbfee93346c8d1dc13811bda

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

<<<<<<< HEAD
<<<<<<< HEAD
	public List<Contact> getAllContacts() {
		List<Contact> contactList=contactRepository.findAll();
		return contactList;
	}
=======
=======
>>>>>>> 7e1242cf7c8006f3fbfee93346c8d1dc13811bda
//find the contact by id
	public Contact findContact(int id) {
		Optional<Contact> option = contactRepository.findById(id);
		if (option.isPresent()) {
			return option.get();
		} else
			return null;

	}
	
	
	
	
	
<<<<<<< HEAD
>>>>>>> 7e1242cf7c8006f3fbfee93346c8d1dc13811bda
=======
>>>>>>> 7e1242cf7c8006f3fbfee93346c8d1dc13811bda

}