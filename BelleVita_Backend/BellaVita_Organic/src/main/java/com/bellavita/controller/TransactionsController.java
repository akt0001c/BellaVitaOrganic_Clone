package com.bellavita.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bellavita.entity.Transactions;
import com.bellavita.service.TransactionsService;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

 private TransactionsService tservices;

 @Autowired
public void setTservices(TransactionsService tservices) {
	this.tservices = tservices;
}
 
@PatchMapping("/update/{tid}")
public ResponseEntity<Transactions> changeStatus(@PathVariable("tid") Integer tid, @RequestParam("status") String status){
	Transactions res= tservices.changeStatus(tid, status);
	return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
}

@GetMapping("/transactions/{tid}")
public ResponseEntity<Transactions> getTranansactionById(@PathVariable("tid") Integer tid){
	Transactions res= tservices.getTransactionById(tid);
	return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
}

@GetMapping("/user/transactions")
public ResponseEntity<List<Transactions>> getAllUserTransactions(Authentication auth,@RequestParam(value="startDate",required=false) LocalDate date1,@RequestParam(value="endDate",required=false) LocalDate date2){
	String uemail= auth.getName();
	List<Transactions> res=null;
	if(date1==null || date2==null) 
	    res= tservices.getTransactionsForOneUser(uemail);
	else
		res= tservices.getTransactionsForPeriod(uemail, date1, date2);
	return new ResponseEntity<>(res,HttpStatus.OK);
}



@GetMapping("/transactions")
public ResponseEntity<List<Transactions>> getTransactions(){
	List<Transactions> res= tservices.getAllTransactions();
	return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
}



	
}
