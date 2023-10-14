package com.vitaOrganic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vitaOrganic.dto.OrdersDto;
import com.vitaOrganic.entity.Orders;
import com.vitaOrganic.services.OrdersServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrdersController {

private OrdersServices oservice;

@Autowired
public void setOservice(OrdersServices oservice) {
	this.oservice = oservice;
}
	
@PostMapping("/placeOrder")	
public ResponseEntity<Orders> placeOrder(@Valid Authentication auth, @RequestBody  OrdersDto ob){
  Orders order= oservice.placeOrder(auth.getName(), ob);
  return new ResponseEntity<>(order,HttpStatus.CREATED);
  
}

@PatchMapping("/updateStatus/{oid}")
public ResponseEntity<Orders> changeOrderStatus(@PathVariable("oid") Integer oid,@RequestParam("status") String status){
	Orders order= oservice.changeOrderStatus(oid, status);
	return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
}


@GetMapping("/order/{oid}")
public ResponseEntity<Orders> getOrderDetail(@PathVariable("oid") Integer oid){
	Orders res= oservice.getOrderDetailById(oid);
	return new ResponseEntity<>(res,HttpStatus.FOUND);
}

@GetMapping("/orders")
public ResponseEntity<List<Orders>> getAllOrders(){
	List<Orders> res= new ArrayList<>();
	return new ResponseEntity<>(res,HttpStatus.FOUND);
}

@GetMapping("/ordersForOne")
public ResponseEntity<List<Orders>> getOrdersForOneUser(Authentication auth){
	List<Orders> res= oservice.getOrdersForOneUser(auth.getName());
	return new ResponseEntity<>(res,HttpStatus.FOUND);
}


}
