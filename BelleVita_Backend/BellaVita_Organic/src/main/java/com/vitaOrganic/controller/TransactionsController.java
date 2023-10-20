package com.vitaOrganic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitaOrganic.services.TransactionsService;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

 private TransactionsService tservices;

 @Autowired
public void setTservices(TransactionsService tservices) {
	this.tservices = tservices;
}
 
 
 
	
}
