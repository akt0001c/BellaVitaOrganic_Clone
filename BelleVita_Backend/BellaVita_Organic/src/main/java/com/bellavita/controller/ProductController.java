package com.bellavita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bellavita.dto.ProductsUpdatedFieldsDto;
import com.bellavita.entity.Products;
import com.bellavita.service.ProductsServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/Products")
public class ProductController {
	
private ProductsServices pservices;

@Autowired
public void setPservices(ProductsServices pservices) {
	this.pservices = pservices;
}

@PostMapping("/add")
public ResponseEntity<Products> addProduct(@Valid @RequestBody Products prod){
	Products res= pservices.addProduct(prod);
	return new ResponseEntity<>(res,HttpStatus.CREATED);
}

@PatchMapping("/update/{pid}")
public ResponseEntity<Products> updateProduct(  @PathVariable("pid")Integer pid ,@RequestBody ProductsUpdatedFieldsDto ob){
	Products res= pservices.updateProduct(pid, ob);
	return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
}

@DeleteMapping("/delete/{pid}")
public ResponseEntity<Products> removeProduct( @PathVariable("pid") Integer pid){
	Products res= pservices.removeProduct(pid);
	return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
}

@GetMapping("/product/{pid}")
public ResponseEntity<Products> getProductById(@PathVariable("pid") Integer id){
	Products res= pservices.getProductById(id);
	return new ResponseEntity<>(res,HttpStatus.OK);
}

@GetMapping("/products/productName")
public ResponseEntity<List<Products>> getProductByName(@RequestParam("pname") String name){
	List<Products> res= pservices.getProductByName(name);
	return new ResponseEntity<>(res,HttpStatus.OK);
} 
 
@GetMapping("/products")
public ResponseEntity<List<Products>> getAllProducts(){
	List<Products> res= pservices.getAllProducts();
	return new ResponseEntity<>(res,HttpStatus.OK);
}

@PatchMapping("/update/status/{pid}")
public ResponseEntity<Products> updateStatus(@PathVariable("pid") Integer pid,@RequestParam("pstatus") String status){
	Products res= pservices.changeStatus(pid, status);
	return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
}


  

}
