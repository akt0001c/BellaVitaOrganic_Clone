package com.vitaOrganic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitaOrganic.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders,Integer>{

}
