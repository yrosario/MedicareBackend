package com.medicare.backend.resource.MedicareBackend;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org. mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.medicare.backend.model.Cart;
import com.medicare.backend.model.User;
import com.medicare.backend.service.CartServiceImpl;
import com.medicare.backend.service.GenericService;



@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest 
public class CartResourceTest {
	
	@Autowired
	private MockMvc mockMvc;

	
	@MockBean
	private CartServiceImpl cartService;
	
	
	@Test
	public void getCartItem_no_user_test() throws Exception {
		

		when(cartService.findById(1l)).thenReturn(null);
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/api/v1/cart/user/-1").accept(MediaType.APPLICATION_JSON);
		        
	    MvcResult result = mockMvc.perform(request)
			.andExpect(status().isNotFound())
			.andReturn();	
	
	    assertEquals("{User:Not_Found}", result.getResponse().getContentAsString());
	
	}
	
	@Test
	public void getCartItem_user_test() throws Exception {
		
		User user = new User();
		user.setFirstname("John");
		user.setLastname("William");
		user.setUid(1l);
		
		
		Cart cart = new Cart(user);
		
        user.setCart(cart);;
        cart.setCartUser(user);
        
        
		when(cartService.findById(Mockito.anyLong())).thenReturn(cart);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cart/user/1"))
				.andExpect(status().isOk());
		

	}
	

//	@Test
//	public void saveCart_test() throws Exception {
//
//		RequestBuilder request = MockMvcRequestBuilders
//				.post("/api/v1/cart/user/1")
//				.accept(MediaType.APPLICATION_JSON)
//				.content("{\"productId\": 1}")
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON);
//		
//		MvcResult result = mockMvc.perform(request)
//				.andExpect(status().isCreated())
//				.andReturn();
//	}
//	
//	
//	@Test
//	public void deleteItem_test() throws Exception {
//		
//		RequestBuilder request = MockMvcRequestBuilders
//				.delete("/api/v1/cart/user/1/item/1")
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON);
//		
//		MvcResult result = mockMvc.perform(request)
//				.andExpect(status().isOk())
//				.andReturn();
//		
//		setUpUserBefore();
//	}
	
	
//	@BeforeEach
//	public void setUpUserBefore() throws Exception {
//		RequestBuilder request = MockMvcRequestBuilders
//				.post("/api/v1/cart/user/1")
//				.accept(MediaType.APPLICATION_JSON)
//				.content("{\"productId\": 1}")
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON);
//		
//		MvcResult result = mockMvc.perform(request)
//				.andExpect(status().isCreated())
//				.andReturn();
//	}
//	
//	
}
