package com.vitaOrganic.servicesImpl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.vitaOrganic.dto.OrdersDto;
import com.vitaOrganic.entity.Orders;
import com.vitaOrganic.services.OrdersServices;

@Service
public class OrdersServicesImpl implements OrdersServices {

	@Override
	public Orders placeOrder(String email, OrdersDto ordersProduct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders changeOrderStatus( Integer orderId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders getOrderDetailById(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getOrdersForOneUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
