package com.bellavita.controller;

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

import com.bellavita.dto.OrdersDto;
import com.bellavita.entity.Orders;
import com.bellavita.exceptions.UserNotLoggedInException;
import com.bellavita.service.OrdersServices;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/orders")
public class OrdersController {

private OrdersServices oservice;

@Autowired
public void setOservice(OrdersServices oservice) {
	this.oservice = oservice;
}
/**
 * This method act as a controller for placing order by accepting three parameter Authentication ,OrdersDto,transactionMethodid.
 * This method can only called by a logged user 
 *  	OrdersDto {
 *               Set<ProductDto> sets,
 *               Address address
 *              }
 *              
 *      ProductDto{
 *               Integer productId,
 *               Integer quantity
 *              }
 * @param Authentication auth,OrdersDto ob, Integer tmethodId
 * @exception UserNotLoggedInException
 * @author Ankit choubey
 * @return Returns orders object.
 */
@PostMapping("/placeOrder")	
public ResponseEntity<Orders> placeOrder(@Valid Authentication auth, @RequestBody  OrdersDto ob, @RequestParam("method") Integer tmethodId)
{
 log.info("Order controller for placing order invoked");
  String uemail= auth.getName();
  if(uemail==null)
	    throw new UserNotLoggedInException("User should be logged in for placing order");
  
  Orders order= oservice.placeOrder(uemail, ob,tmethodId);
  
  log.info("Order controller responce ,order placed successfully ");
  return new ResponseEntity<>(order,HttpStatus.CREATED);
  
}


/**
 * This method act as a controller for changing the order status 
 * Here first parameter act as a pathVariable(oid) and second is a RequestParam(status)
 * @param Integer orderId,String status
 * @author Ankit Choubey
 * @return Returns updated order object
 */
@PatchMapping("/update/{oid}")
public ResponseEntity<Orders> changeOrderStatus(@PathVariable("oid") Integer oid,@RequestParam("status") String status){
	log.info("Order cotroller  for changing order status  started...");
	Orders order= oservice.changeOrderStatus(oid, status);
	log.info("Order controller  responce ,order status has been changed successfully");
	return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
}


@GetMapping("/order/{oid}")
public ResponseEntity<Orders> getOrderDetail(@PathVariable("oid") Integer oid){
	log.info("Order controller for getting and order details started");
	Orders res= oservice.getOrderDetailById(oid);
	log.info("Order controller responce, order details found successfully");
	return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
}


/**
 * This method act as a controller for getting all the orders and all four parameter are not mandatory 
 * Here direction can accept two values which are asc and desc  . asc= ascending, desc=descending
 * @param String field=orderDateTime,String direction=desc ,Integer pageno=1,Integer noOfRecords=10
 * @author Ankit choubey
 * @return Returns list of orders  (List<Orders>) 
 */
@GetMapping("/orders")
public ResponseEntity<List<Orders>> getAllOrders(@RequestParam( value= "field",required=false) String field, @RequestParam( value= "dir" ,required=false) String direction , @RequestParam( value="pageno", required=false) Integer pageno, @RequestParam( value="records" , required=false) Integer records){
	log.info("Order controller for getting all the orders started...");
	List<Orders> res= oservice.getAllOrders(field, direction, pageno, records);
	log.info("Order controller response ,Orders detail found successfully");
	return new ResponseEntity<>(res,HttpStatus.OK);
}



/**
 * This method act as controller for getting all the orders for only current logged user
 * @author Ankit choubey
 * @param Authentication auth
 * @return Returns list of  orders (List<orders>)
 */
@GetMapping("/orders/logged/user")
public ResponseEntity<List<Orders>> getOrdersForOneUser(Authentication auth){
	log.info("Order controller for getting all order only for current logged user started...");
	List<Orders> res= oservice.getOrdersForOneUser(auth.getName());
	log.info("Order controller response ,Orders for logged user found successfully ");
	return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
}


}
