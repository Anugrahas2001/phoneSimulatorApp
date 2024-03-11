package com.ff.phonesimulatorapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.phonesimulatorapp.service.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController {
@Autowired
private ContactService contactService;










}
