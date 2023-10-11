package com.vitaOrganic.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vitaOrganic.dto.ProductsUpdatedFieldsDto;
import com.vitaOrganic.entity.Products;
import com.vitaOrganic.services.ProductsServices;

@Service
public class ProductsServicesImpl  implements ProductsServices{

	@Override
	public Products addProduct(Products ob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Products updateProduct(Integer pid, ProductsUpdatedFieldsDto ob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Products removeProduct(Integer pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Products> getProductByName(String pname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Products getProductById(Integer pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Products> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Products changeStatus(Integer pid, String status) {
		// TODO Auto-generated method stub
		return null;
	}
   
}
