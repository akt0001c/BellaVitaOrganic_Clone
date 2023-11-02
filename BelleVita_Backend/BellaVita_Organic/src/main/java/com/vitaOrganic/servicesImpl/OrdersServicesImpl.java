package com.vitaOrganic.servicesImpl;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitaOrganic.dto.OrdersDto;
import com.vitaOrganic.dto.ProductDto;
import com.vitaOrganic.entity.OrderDetail;
import com.vitaOrganic.entity.OrderStatus;
import com.vitaOrganic.entity.Orders;
import com.vitaOrganic.entity.Products;
import com.vitaOrganic.entity.TransactionStatus;
import com.vitaOrganic.entity.TransactionType;
import com.vitaOrganic.entity.Transactions;
import com.vitaOrganic.entity.Users;
import com.vitaOrganic.execptions.OperationFaliureException;
import com.vitaOrganic.execptions.OrderException;
import com.vitaOrganic.execptions.OrderNotFoundException;
import com.vitaOrganic.execptions.ProductNotFoundException;
import com.vitaOrganic.execptions.UserNotLoggedInException;
import com.vitaOrganic.repository.OrdersRepository;
import com.vitaOrganic.repository.ProductsRepository;
import com.vitaOrganic.repository.UsersRepository;
import com.vitaOrganic.services.OrdersServices;

import jakarta.transaction.Transactional;

@Service
public class OrdersServicesImpl implements OrdersServices {
	
	
	private UsersRepository urepo;
	private OrdersRepository orepo;
	private ProductsRepository prepo;
	
	
	
	

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
	 * @author Ankit Choubey
	 * @return Orders
	 * @param email,OrdersDto
	 * @exception UserNotLoggedExcepiton,ProductNotFoundException
	 */
	@Override
	@Transactional
	public Orders placeOrder(String email, OrdersDto ordersProduct,String transactionmethod) {
		Users user = urepo.findByEmail(email).orElseThrow(()->new UserNotLoggedInException("User should be logged in for placing order"));
		Orders order= new Orders();
		double order_amount= 0.0;
		Set<ProductDto> orderlist = ordersProduct.getOrdersList();
		for(ProductDto ob:orderlist)
		{
			Products product= prepo.findById(ob.getId()).orElseThrow(()-> new ProductNotFoundException("Orderd prduct  is not avaliable rigth now so you can't place right now ,please update order details"));
			if(product.getQuantity()<ob.getQuantity())
				  throw new ProductNotFoundException("Product in ordered quantity is not avaliable");
			
			OrderDetail od = new OrderDetail();
			product.setQuantity(product.getQuantity()- ob.getQuantity());
			od.setProduct(product);
			od.setQuantity(ob.getQuantity());
			double amount= product.getProductPrice()*ob.getQuantity();
			order_amount= order_amount + amount;
			od.setOrder(order);
			order.getOrderDetails().add(od); 
			
		
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
		try {
		transaction.setTtype(TransactionType.valueOf(TransactionType.class, transactionmethod));
		}catch(IllegalArgumentException | NullPointerException exp) {
			throw new OperationFaliureException("Operation Fallure");
		}
		transaction.setStatus(TransactionStatus.Completed);
		
		order.setTransaction(transaction);
	    
		
		
		
		
		
		
		
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
		if(orderId==null || status==null)
			    throw new OrderException("Invalid Order detalis found");
		Orders order= orepo.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order cannot be found for this orderid:"+ orderId+" "));
		order.setStatus(OrderStatus.valueOf(OrderStatus.class,status));
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
		if(orderId==null )
		    throw new OrderException("Invalid Order detalis found");
		return orepo.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order cannot be found for this orderid:"+ orderId+" "));
	}

	
	/**
	 * It is used to get all the orders in list format 
	 * @author Ankit choubey
	 * @exception OrderException
	 * @return List<orders>
	 */
	@Override
	public List<Orders> getAllOrders() {
		List<Orders> res= orepo.findAll();
		if(res.isEmpty())
			  throw new OrderException("NO orders Found");
		
		return res;
	}
	
	
    /**
     * It is used to get all orders for logged user  
     * @author Ankit choubey
     * @exception UserNotLoggedInException,OrderException
     * @param user_email
     * @return List<orders> 
     */
	@Override
	public List<Orders> getOrdersForOneUser(String email) {
		
		if(email==null)
			throw new UserNotLoggedInException("User should login in first");
		Users user = urepo.findByEmail(email).orElseThrow(()->new UserNotLoggedInException("User not found or not loggedIn"));
		
		
		List<Orders> res= orepo.findByUser(user.getUserID());
		
		if(res.isEmpty())
			  throw new OrderException("No orders found");
		
		return res;
	}

}
