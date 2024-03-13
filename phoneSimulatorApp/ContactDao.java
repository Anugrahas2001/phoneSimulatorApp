package com.ff.phonesimulatorapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.phonesimulatorapp.repository.ContactRepository;

@Repository
public class ContactDao {
@Autowired
private ContactRepository contactRepository;



}
