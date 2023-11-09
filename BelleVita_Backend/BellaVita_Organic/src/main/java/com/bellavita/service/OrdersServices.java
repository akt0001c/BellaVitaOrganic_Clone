package com.bellavita.service;

import java.util.List;

import com.bellavita.dto.OrdersDto;
import com.bellavita.entity.Orders;

public interface OrdersServices {
public Orders placeOrder(String email,OrdersDto ordersProduct,Integer transactionmethodId);
public Orders changeOrderStatus(Integer orderId ,String status);
public Orders getOrderDetailById(Integer orderId);
public List<Orders> getAllOrders();
public List<Orders> getOrdersForOneUser(String email);

}
