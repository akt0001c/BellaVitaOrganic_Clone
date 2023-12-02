package com.bellavita.service;

import java.util.List;

import com.bellavita.dto.OrdersDto;
import com.bellavita.entity.Orders;
import com.bellavita.exceptions.OrderException;
import com.bellavita.exceptions.OrderNotFoundException;
import com.bellavita.exceptions.UserNotLoggedInException;


public interface OrdersServices {
	
/**
 * This method uses OrdersDto class to collect order details from the user and create and add the order in the database 
 * Billing adderss is mandatory so it will throw an exception without address in OrdersDto
 * Here this method is using Runtime Exception
 * 
 * OrdersDto {
 *             List<ProductDto> list ,
 *             Address adderss
 *           }
 *           
 * ProductDto{
 *            Integer productId,
 *            Integer quantity;
 *           }
 *           
 * @author Ankit Choubey
 * @return Orders object
 * @param String email , OrdersDto odto;
 * @exception UserNotLoggedExcepiton,ProductNotFoundException,TransactionMethodNotFoundException
 */
public Orders placeOrder(String email,OrdersDto ordersProduct,Integer transactionmethodId);

/**
 * It is used to change the status of an order by using orderId and given status
 * Here this method is using Runtime Exception
 * @author Ankit Choubey
 * @param Integer orderId, String status
 * @exception OrderException,OrderNotFoundException
 * @return Orders
 */
public Orders changeOrderStatus(Integer orderId ,String status);

/**
 * It is used to get order detals of one particular order by using its id
 * Here this method is using Runtime Exception
 * @author Ankit choubey
 * @param Integer orderId
 * @exception OrderNotFoundException,OrderException
 * @return Orders res 
 */
public Orders getOrderDetailById(Integer orderId);

/**
 * This method is used to get all the orders in the sorted list format which is sorted  with respect to order date by default 
 * @param  String field,String direction,Integer pageno,Integer records 
 * asc= ascending, desc= descending
 * default parameter values : field="orderDateTime",direction="desc"(ascending),pageno=1,records(no of recorder per page)=10;
 * Here this method is using Runtime Exception
 * @author Ankit choubey
 * @exception OrderException
 * @return List<orders> orders
 */
public List<Orders> getAllOrders(String field,String direction,Integer pageno,Integer records);

/**
 * This method is used to get all orders for the current  logged user in descending format with respect to order datetime
 * This method will only work for logged user 
 * Here this method is using Runtime Exception    
 * @author Ankit choubey
 * @exception UserNotLoggedInException,OrderException
 * @param String user_email
 * @return List<orders> orders
 */
public List<Orders> getOrdersForOneUser(String email);

}
