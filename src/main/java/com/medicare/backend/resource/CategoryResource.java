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

import com.medicare.backend.model.Category;
import com.medicare.backend.service.GenericService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryResource {
	
	@Autowired
	private GenericService<Category,Long> categoryService;
	
	@PostMapping()
	public ResponseEntity<Category> addCategory(@RequestBody Category u){
		Category Category = categoryService.save(u);
		if(Category != null) {
			return new ResponseEntity<Category>(Category, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<Category>(Category, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping()
	public ResponseEntity<List<Category>> getCategoryById(){
		return new ResponseEntity<List<Category>>(categoryService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable Long id){
		Category Category = categoryService.findById(id);
		
		if(Category != null) {
			return new ResponseEntity<>(Category, HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id){
		Category Category = categoryService.findById(id);
		if(Category != null) {
			categoryService.delete(id);
			return new ResponseEntity<>("Category was deleted", HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Category was not found", HttpStatus.NOT_FOUND);
	}

}
