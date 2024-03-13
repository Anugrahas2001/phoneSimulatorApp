package com.ff.phonesimulatorapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ff.phonesimulatorapp.entity.Contact;
import com.ff.phonesimulatorapp.repository.ContactRepository;

@Configuration
public class SeedConfig implements CommandLineRunner{

	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	public void run(String... args) throws Exception {
		if(contactRepository.count()==0) {
			Contact contact = new Contact();
			contact.setContactName("admin");
			contact.setCallInProgress(false);
			contact.setContactGroup("Self");
			contact.setContactNumber(8080452312l);
			
			contactRepository.save(contact);
		}
	}

}
