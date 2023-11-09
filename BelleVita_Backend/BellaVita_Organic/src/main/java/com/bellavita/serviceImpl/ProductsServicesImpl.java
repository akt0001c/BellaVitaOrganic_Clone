package com.bellavita.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bellavita.dto.ProductsUpdatedFieldsDto;
import com.bellavita.entity.ProductStatus;
import com.bellavita.entity.Products;
import com.bellavita.exceptions.ProductException;
import com.bellavita.exceptions.ProductNotFoundException;
import com.bellavita.repository.ProductsRepository;
import com.bellavita.service.ProductsServices;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductsServicesImpl  implements ProductsServices{
	
	private ProductsRepository prepo;

	@Autowired
	public void setPrepo(ProductsRepository prepo) {
		this.prepo = prepo;
	}

	/**
	 * It is used to add product product into the database 
	 * @author Ankit choubey
	 * @param Products
	 * @return Products
	 */
	@Override
	public Products addProduct(Products ob) {
		if(ob.getQuantity()>0)
			  ob.setStatus(ProductStatus.Available);
		else
			ob.setStatus(ProductStatus.Unavailable);
		ob.setProductAddTime(LocalDateTime.now());
		
		
		
		return prepo.save(ob);
	}

	/**
	 * It is used to update product name , price and image url 
	 * @author Ankit choubey
	 * @param Integer productid, ProductsUpadatedFieldsDto ob
	 * @exception ProductNotFoundException
	 * @return Products
	 */
	@Override
	public Products updateProduct(Integer pid, ProductsUpdatedFieldsDto ob) {
		Products prod= prepo.findById(pid).orElseThrow(()->new ProductNotFoundException("Product not found or invalid product details provided"));
		prod.setProductName(ob.getProductName());
		prod.setProductPrice(ob.getProductPrice());
		prod.setProductUrl(ob.getProductUrl());
		
		return prepo.save(prod);
	}

	/**
	 * It is used to remove product from the database using product id
	 * @author Ankit choubey
	 * @param Integer productId
	 * @exception ProductNotFoundException
	 * @return Products 
	 */
	@Override
	public Products removeProduct(Integer pid) {
		Products prod = prepo.findById(pid).orElseThrow(()-> new ProductNotFoundException("Product not found or invalid product details provided"));
		
	    prepo.delete(prod);
	    return prod;
	}

	/**
	 * It is used to get all the product in list format using productName
	 * @param String productName
	 * @exception ProductNotFoundException
	 * @author Ankit Choubey
	 * @return List<products>
	 */
	@Override
	public List<Products> getProductByName(String pname) {
		List<Products> res= prepo.findByProductName(pname);
		if(res.isEmpty())
			  throw new ProductNotFoundException("Product not found");
		return res;
	}
	
	/**
	 * It is used to get  a product details using product id
	 * @author Ankit choubey
	 * @exception ProductException , ProductNotFoundException
	 * @param Integer productID
	 * @return Products
	 */
	@Override
	public Products getProductById(Integer pid) {
		if(pid==null)
			 throw new ProductException("Invalid product details given");
		
		Products prod = prepo.findById(pid).orElseThrow(()-> new ProductNotFoundException("Product not found or invalid product details provided"));
		return prod;
	}
	
	
    /**
     * It is used to get all the products
     * @author Ankit choubey
     * @exception ProductNotFoundExcepiton
     * @return List<Products>
     */
	@Override
	public List<Products> getAllProducts() {
		List<Products> res= prepo.findAll();
		if(res.isEmpty())
			throw new ProductNotFoundException("No product found");
		return res;
	}
	
	
    /**
     * It is used to change the status of the product using product id and new status 
     * @author Ankit Choubey
     * @param Integer productId,String status
     * @exception ProductException
     * @return Products
     */
	@Override
	public Products changeStatus(Integer pid, String status) {
		Products product= prepo.findById(pid).orElseThrow(()-> new ProductNotFoundException("Product not found or invalid product details given"));
		try {
		product.setStatus(ProductStatus.valueOf(ProductStatus.class, status));
		}catch(IllegalArgumentException  | NullPointerException  exp) {
			throw new ProductException("New Product status is not acceptable");
		}
		
		return prepo.save(product);
	}

	 /**
     * It is used to update the quantity of a particular product 
     * @author Ankit Choubey
     * @param Integer productId,Integer quantity
     * @exception ProductNotFoundException
     * @return void
     */
	@Override
	public void updateProductQuantity(Integer pid, Integer quantity) {
		Products product= prepo.findById(pid).orElseThrow(()-> new ProductNotFoundException("Product not found or invalid product details given"));
		if(quantity>0 && quantity!=null)
		    product.setQuantity(quantity);
          		
		
		prepo.save(product);
		
	}
   
}
