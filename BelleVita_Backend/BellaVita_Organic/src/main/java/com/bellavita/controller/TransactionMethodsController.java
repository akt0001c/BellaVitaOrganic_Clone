package com.bellavita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bellavita.entity.TransactionMethod;
import com.bellavita.service.TransactionsMethodServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/TransactionMethods")
public class TransactionMethodsController {

	private TransactionsMethodServices tmservices;

	@Autowired
	public void setTmservices(TransactionsMethodServices tmservices) {
		this.tmservices = tmservices;
	}
	
	@PostMapping("/add")
	public ResponseEntity<TransactionMethod> addMethod(@Valid @RequestBody TransactionMethod ob){
		TransactionMethod res= tmservices.addTransactionMethod(ob);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/remove/{tmid}")
	public ResponseEntity<TransactionMethod> removeMethod(@PathVariable("tmid") Integer tmid){
		TransactionMethod res= tmservices.removeTransctionMethod(tmid);
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<TransactionMethod>> getAllMethods(){
		List<TransactionMethod> res= tmservices.getAllTransactionMethods();
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PatchMapping("/update/{tmid}")
	public ResponseEntity<TransactionMethod> updateMethod(@PathVariable("tmid") Integer tmid){
		TransactionMethod res= tmservices.changeStatus(tmid);
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
	
}
