package com.vitaOrganic.services;

import com.vitaOrganic.dto.OrdersDto;
import com.vitaOrganic.entity.Orders;

public interface OrdersServices {
public Orders placeOrder(String email,OrdersDto ordersProduct);

}
