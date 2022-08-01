package com.medicare.backend.resource.MedicareBackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import static org.assertj.core.api.Assertions.assertThat;

import com.medicare.backend.model.Role;
import com.medicare.backend.model.User;
import com.medicare.backend.resource.UserResource;
import com.medicare.backend.service.GenericService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Autowired
	private UserResource userResource;
	
	@Autowired
	private GenericService<User, Long> userService;
	
	@Test
	public void contextLoad() throws Exception{
		assertThat(userResource).isNotNull();
	}
	
	
	@Test
	public void getUser_test() throws Exception {

		
		User user = new User();
		
		user = userService.save(user);
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/api/v1/user/"+user.getUid()).accept(MediaType.APPLICATION_JSON);
		 
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();	

	}
	
	@Test
	public void saveProduct_test() throws Exception {

		
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/api/v1/user")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"firstname\":\"tim\",\"lastname\":\"asdf\",\"email\":\"ad@gmail.com\",\"password\":\"password\",\"username\":\"tim\",\"products\":[],\"role\":{\"id\":1,\"name\":\"Admin\"}}")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andReturn();
	}
	
	@Test
	public void updateUser_test() throws Exception {
		
		User user = new User();
		user.setFirstname("Johny");
		user.setLastname("Smith");
		user.setEmail("jsmit@gmail.com");
		user.setPassword("password");
		user.setRole(new Role("TEST3"));
		
		user = userService.save(user);


		RequestBuilder request = MockMvcRequestBuilders
				.put("/api/v1/user")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"uid\":"+user.getUid() +",\"firstname\":\"tim\",\"lastname\":\"asdf\",\"email\":\"ad@gmail.com\",\"password\":\"password\",\"username\":\"test\",\"products\":[],\"role\":{\"id\":1,\"name\":\"Admin\"}}")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andReturn();
		
	}
	
	@Test
	public void deleteRole_test() throws Exception {
		
		User user = new User();
		user = userService.save(user);
		
		RequestBuilder request = MockMvcRequestBuilders
				.delete("/api/v1/user/"+user.getUid())
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();

	}
}
