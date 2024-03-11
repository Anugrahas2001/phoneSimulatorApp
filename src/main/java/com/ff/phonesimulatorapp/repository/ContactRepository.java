package com.ff.phonesimulatorapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ff.phonesimulatorapp.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

//	@Query("Select c from Contact c")
//	List<Contact> findAll();
}
