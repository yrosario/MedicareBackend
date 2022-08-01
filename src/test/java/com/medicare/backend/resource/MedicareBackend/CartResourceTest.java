package com.medicare.backend.resource.MedicareBackend;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.assertj.core.api.Assertions.assertThat;

import com.medicare.backend.model.Cart;
import com.medicare.backend.resource.CartResource;
import com.medicare.backend.service.GenericService;




@SpringBootTest
@AutoConfigureMockMvc
public class CartResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private GenericService<Cart, Long> cartService;
	
	@Autowired
	private CartResource cartResource;
	
	@Test
	public void contextLoad() throws Exception{
		assertThat(cartResource).isNotNull();
	}
	
	@Test
	public void getCartItem_no_user_test() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/api/v1/cart/user/-1").accept(MediaType.APPLICATION_JSON);
		        
	MvcResult result = mockMvc.perform(request)
			.andExpect(status().isNotFound())
			.andReturn();	
	
	assertEquals("{User:Not_Found}", result.getResponse().getContentAsString());
	
	}
	
	
	@BeforeEach
	public void setUpUserBefore() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/api/v1/cart/user/1")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"productId\": 1}")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andReturn();
	}
	
	
	@Test
	public void saveCart_test() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders
				.post("/api/v1/cart/user/1")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"productId\": 1}")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andReturn();
	}
	
	@Test
	public void updateCart_test() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders
				.put("/api/v1/cart/user/1")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"id\":1,\"quantity\":0,\"product\":{\"pid\":1,\"name\":\"Vitamin G\",\"brand\":null,\"price\":8.0,\"active\":false,\"numberOfViews\":0,\"qty\":0,\"imageBlob\":null,\"category\":{\"id\":1,\"name\":\"Cold/Flu Medicine\"}}}")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andReturn();
	}
	
	@Test
	public void deleteItem_test() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.delete("/api/v1/cart/user/1/item/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();
		
		setUpUserBefore();
	}
}
