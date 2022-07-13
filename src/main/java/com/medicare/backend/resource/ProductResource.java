package com.medicare.backend.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.backend.model.Product;
import com.medicare.backend.service.GenericService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {
	
	@Autowired
	private GenericService<Product,Long> productService;
	
	@PostMapping()
	public ResponseEntity<Product> addProduct(@RequestBody Product u){
		Product Product = productService.save(u);
		if(Product != null) {
			return new ResponseEntity<Product>(Product, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<Product>(Product, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping()
	public ResponseEntity<List<Product>> getProductById(){
		return new ResponseEntity<List<Product>>(productService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id){
		Product Product = productService.findById(id);
		
		if(Product != null) {
			return new ResponseEntity<>(Product, HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id){
		Product Product = productService.findById(id);
		if(Product != null) {
			productService.delete(id);
			return new ResponseEntity<>("Product was deleted", HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Product was not found", HttpStatus.NOT_FOUND);
	}

}
