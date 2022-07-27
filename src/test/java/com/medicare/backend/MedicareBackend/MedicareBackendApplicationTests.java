package com.medicare.backend.MedicareBackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.medicare.backend.model.Product;
import com.medicare.backend.model.User;
import com.medicare.backend.repository.UserRepository;

@SpringBootTest
class MedicareBackendApplicationTests {

	@Autowired
	private UserRepository userRepository;
	
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void createUser() {
		User user = new User();
		user.setFirstname("Bob");
		user.setLastname("Smith");
		user.setPassword("testpassword");
		user.setEmail("asdf@gmail.com");
		
		Product product = new Product();
		product.setName("Vitamin c");
		product.setPrice(7.45f);
		user.setProducts(product);
		
		userRepository.save(user);
	}

}
