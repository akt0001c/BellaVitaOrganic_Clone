package com.vitaOrganic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitaOrganic.entity.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer> {

}
