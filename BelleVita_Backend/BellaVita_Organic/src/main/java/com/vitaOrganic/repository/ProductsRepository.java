package com.vitaOrganic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitaOrganic.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {

}
