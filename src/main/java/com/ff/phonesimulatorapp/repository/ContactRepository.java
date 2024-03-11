package com.ff.phonesimulatorapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.phonesimulatorapp.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
