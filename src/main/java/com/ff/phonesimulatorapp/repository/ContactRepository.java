package com.ff.phonesimulatorapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ff.phonesimulatorapp.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

	Contact findByContactName(String contactName);

}