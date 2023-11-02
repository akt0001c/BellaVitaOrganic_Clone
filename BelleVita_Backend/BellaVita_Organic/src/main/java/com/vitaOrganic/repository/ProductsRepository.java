package com.vitaOrganic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vitaOrganic.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
	
 @Query("select p from Products p where p.productName= ?1" )
 List<Products> findByProductName(String name);
}
