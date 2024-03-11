package com.ff.phonesimulatorapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ff.phonesimulatorapp.dao.ContactDao;

@Service
public class ContactService {
@Autowired
private ContactDao contactDao;

}
