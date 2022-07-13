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

import com.medicare.backend.model.User;
import com.medicare.backend.service.GenericService;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {
	
	@Autowired
	private GenericService<User,Long> userService;
	
	@PostMapping()
	public ResponseEntity<User> addUser(@RequestBody User u){
		User user = userService.save(u);
		if(user != null) {
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping()
	public ResponseEntity<List<User>> getUserById(){
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id){
		User user = userService.findById(id);
		
		if(user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		User user = userService.findById(id);
		if(user != null) {
			return new ResponseEntity<>("User was deleted", HttpStatus.OK);
		}
		
		return new ResponseEntity<>("User was not found", HttpStatus.NOT_FOUND);
	}

}
