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

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

import com.medicare.backend.model.Role;
import com.medicare.backend.resource.CategoryResource;
import com.medicare.backend.service.GenericService;







@SpringBootTest
@AutoConfigureMockMvc
public class RoleResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Autowired
	private CategoryResource categoryResource;
	
	@Autowired
	private GenericService<Role, Long> roleService;
	
	@Test
	public void contextLoad() throws Exception{
		assertThat(categoryResource).isNotNull();
	}
	
	
	@Test
	public void getRole_test() throws Exception {

		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/api/v1/role/1").accept(MediaType.APPLICATION_JSON);
		 
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();	
		
	
	}
	
	@Test
	public void role_test() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders
				.post("/api/v1/role")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"i\": 1, \"name\": \"test\"}")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andReturn();
	}
	
	@Test
	public void updateCart_test() throws Exception {
		
		Role role = new Role("test2");
		role = roleService.save(role);

		RequestBuilder request = MockMvcRequestBuilders
				.put("/api/v1/role")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"id\":"+role.getId()+",\"name\":\"test\"}")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andReturn();
		
	}
	
	@Test
	public void deleteRole_test() throws Exception {
		List<Role> roles = roleService.findAll();
		
		Role role=null;
		for(Role r : roles) {
			if(r.getName().equals("test")) {
				role = r;
			}
		}
		
		RequestBuilder request = MockMvcRequestBuilders
				.delete("/api/v1/role/"+role.getId())
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();

	}
}
