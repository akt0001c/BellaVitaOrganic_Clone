package com.bellavita.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bellavita.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer>{

	@Query("select o from Orders o where o.user= ?1 ")
	public List<Orders> findByUser(Integer userid);
}
