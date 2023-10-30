package com.vitaOrganic.services;

import java.util.List;

import com.vitaOrganic.dto.OrdersDto;
import com.vitaOrganic.entity.Orders;

public interface OrdersServices {
public Orders placeOrder(String email,OrdersDto ordersProduct,String transactionmethod);
public Orders changeOrderStatus(Integer orderId ,String status);
public Orders getOrderDetailById(Integer orderId);
public List<Orders> getAllOrders();
public List<Orders> getOrdersForOneUser(String email);

}
