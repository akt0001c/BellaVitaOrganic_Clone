package com.vitaOrganic.services;

import java.util.List;

import com.vitaOrganic.dto.ProductsUpdatedFieldsDto;
import com.vitaOrganic.entity.Products;

public interface ProductsServices {
public Products addProduct(Products ob);
public Products updateProduct(Integer pid,ProductsUpdatedFieldsDto ob);
public Products removeProduct(Integer pid);
public List<Products> getProductByName(String pname);
public Products getProductById(Integer pid);
public List<Products> getAllProducts();
public Products changeStatus(Integer pid,String status);
public void updateProductQuantity(Integer pid, Integer quantity);

}
