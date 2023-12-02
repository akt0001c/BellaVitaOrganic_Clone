package com.bellavita.serviceImpl;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bellavita.dto.OrdersDto;
import com.bellavita.dto.ProductDto;
import com.bellavita.entity.OrderDetail;
import com.bellavita.entity.OrderStatus;
import com.bellavita.entity.Orders;
import com.bellavita.entity.Products;
import com.bellavita.entity.TransactionMethod;
import com.bellavita.entity.TransactionStatus;
import com.bellavita.entity.Transactions;
import com.bellavita.entity.Users;
import com.bellavita.exceptions.OrderException;
import com.bellavita.exceptions.OrderNotFoundException;
import com.bellavita.exceptions.ProductNotFoundException;
import com.bellavita.exceptions.TransactionMethodNotFoundException;
import com.bellavita.exceptions.UserNotLoggedInException;
import com.bellavita.repository.OrdersRepository;
import com.bellavita.repository.ProductsRepository;
import com.bellavita.repository.TransactionMethodRepository;
import com.bellavita.repository.UsersRepository;
import com.bellavita.service.OrdersServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrdersServicesImpl implements OrdersServices {
	
	
	private UsersRepository urepo;
	private OrdersRepository orepo;
	private ProductsRepository prepo;
	private TransactionMethodRepository tmrepo;
	
	
	
	
	@Autowired
	public void setTmrepo(TransactionMethodRepository tmrepo) {
		this.tmrepo = tmrepo;
	}

	@Autowired
	public void setPrepo(ProductsRepository prepo) {
		this.prepo = prepo;
	}

	@Autowired
    public void setOreop(OrdersRepository oreop) {
		this.orepo = oreop;
	}

	@Autowired
	public void setUrepo(UsersRepository urepo) {
		this.urepo = urepo;
	}

	/**
	 * This method uses OrdersDto class to collect order details from the user and create and add the order in the database
	 * Billing adderss is mandatory so it will throw an exception without address in OrdersDto
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
	 * @return Orders
	 * @param email,OrdersDto
	 * @exception UserNotLoggedExcepiton,ProductNotFoundException,TransactionMethodNotFoundException,OrderException
	 */
	@Override
	public Orders placeOrder(String email, OrdersDto ordersProduct,Integer transactionMethodId) {
		log.info("Service for order placing has been invoked");
		
		if(ordersProduct.getAddress()==null)
		      throw new OrderException("Billing address is mandatory so please try again with a proper adderss");
		
		Users user = urepo.findByEmail(email).orElseThrow(()->new UserNotLoggedInException("User should be logged in for placing order or user not found"));
		log.info("Order placing has been processed");
		Orders order= new Orders();
		double order_amount= 0.0;
		Set<ProductDto> orderlist = ordersProduct.getOrdersList();
		
		log.info("Checking the ordred product avaliability and fetching the order details");
		
		for(ProductDto ob:orderlist)
		{
			Products product= prepo.findById(ob.getId()).orElseThrow(()-> new ProductNotFoundException("Orderd prduct  is not avaliable rigth now so you can't place right now ,please update order details"));
			if(product.getQuantity()<ob.getQuantity())
				  throw new ProductNotFoundException("Product in ordered quantity is not avaliable");
			
			OrderDetail od = new OrderDetail();
			product.setQuantity(product.getQuantity()- ob.getQuantity());
			od.setQuantity(ob.getQuantity());
			od.setProduct(product);
						
			
			double amount= product.getProductPrice()*ob.getQuantity();
			order_amount= order_amount + amount;
			
			
			od.setOrder(order);
			order.getOrderDetails().add(od);
			product.getOrderDetails().add(od);
					
		}
		order.setTotal_order_value(order_amount);
		order.setOrderDateTime(LocalDateTime.now());
		order.setUser(user);
		order.setStatus(OrderStatus.Placed);
		order.setDeliveryDate(LocalDate.now().plusDays(7));
		
		
		
		Transactions transaction = new Transactions();
		transaction.setOrder(order);
		transaction.setTamount(order_amount);
		
		transaction.setTransactionTime(LocalTime.now());
		transaction.setTransactionDate(LocalDate.now());
		 TransactionMethod tmethod = tmrepo.findById(transactionMethodId).orElseThrow(()->new TransactionMethodNotFoundException("Transaction Methods not found or invalid transaction method used"));
		transaction.setStatus(TransactionStatus.Completed);
		tmethod.getTransactions().add(transaction);
		order.setTransaction(transaction);
	    
		
		
		
		
		
		tmrepo.save(tmethod);
		
		log.info("Order has been placed successfully");
		
		return orepo.save(order);
	}

	/**
	 * It is used to change the status of an order by using orderId and given status
	 * @author Ankit Choubey
	 * @param orderId,status
	 * @exception OrderException,OrderNotFoundException
	 * @return Orders
	 */
	@Override
	public Orders changeOrderStatus( Integer orderId, String status) {
		log.info("Service for changing order status has been invoked");
		
		if(orderId==null || status==null)
			    throw new OrderException("Invalid Order detalis found");
		Orders order= orepo.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order cannot be found for this orderid:"+ orderId+" "));
		order.setStatus(OrderStatus.valueOf(OrderStatus.class,status));
		log.info("Order status has been changed successfully");
	    return	orepo.save(order);
		
	}
	
	
    /**
     * It is used to get order detals of one particular order by using its id
     * @author Ankit choubey
     * @param orderId
     * @exception OrderNotFoundException,OrderException
     * @return Orders 
     */
	@Override
	public Orders getOrderDetailById(Integer orderId) {
		log.info("Service for getting order details using its id has been invoked");
		
		if(orderId==null )
		    throw new OrderException("Invalid Order detalis found");
		
		
			  Orders res=  orepo.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order cannot be found for this orderid:"+ orderId+" "));
			  log.info("Order details found successfully");
			  return res;
	}

	
	/**
	 * This method is used to get all the orders in the sorted list format which is sorted  with respect to order date by default 
	 * @param  String field,String direction,Integer pageno,Integer records 
	 * asc= ascending, desc= descending
	 * default parameter values : field="orderDateTime",direction="desc"(ascending),pageno=1,records(no of recorder per page)=10;
	 * @author Ankit choubey
	 * @exception OrderException
	 * @return List<orders> orders
	 */
	@Override
	public List<Orders> getAllOrders(String field,String direction,Integer pageno,Integer records) {
		log.info(" Service getAllOrders has been invoked");
		
		if(field==null)
			 field="OrderDateTime";
		
		if(direction ==null)
			 direction= "desc";
		
		if(pageno==null)
			 pageno=1;
		
		if(records==null)
			records=10;
		
		log.info("Service getAllOrder is working");
		
		Sort sort= direction.equalsIgnoreCase("asc")?Sort.by(field).ascending():Sort.by(field).descending();
		
		Pageable pb= PageRequest.of(pageno-1, records, sort);
		
		Page<Orders> page= orepo.findAll(pb);
		
		List<Orders> res=page.getContent();
		if(res.isEmpty())
			  throw new OrderException("NO orders Found");
		
		
		log.info("Service getAllOrders worked successfully and all orders found");
		
		return res;
	}
	
	
    /**
     * This method is used to get all orders for the current  logged user in descending format with respect to order datetime
     * This method will only work for logged user     
     * @author Ankit choubey
     * @exception UserNotLoggedInException,OrderException
     * @param String user_email
     * @return List<orders>  orders
     */
	@Override
	public List<Orders> getOrdersForOneUser(String email) {
		log.info("Service getOrdersForOneUser has been invoked");
		if(email==null)
			throw new UserNotLoggedInException("User should login in first");
		Users user = urepo.findByEmail(email).orElseThrow(()->new UserNotLoggedInException("User not found or not loggedIn"));
		
		log.info("Service getOrderForOneUser is working and feteching required details");
		List<Orders> res= orepo.findByUser(user.getUserID());
		
		if(res.isEmpty())
			  throw new OrderException("No orders found");
		
		log.info("Service getOrderForOneUser worked successfully ");
		
		return res;
	}

}
