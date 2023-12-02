package com.bellavita.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bellavita.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer>,PagingAndSortingRepository<Orders,Integer>{

	@Query("select o from Orders o where o.user= ?1 order by o.OrderDateTime desc")
	public List<Orders> findByUser(Integer userid);
}
