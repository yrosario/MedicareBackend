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
	

}
