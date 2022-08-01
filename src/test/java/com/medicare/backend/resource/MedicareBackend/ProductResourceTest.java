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

import com.medicare.backend.model.Product;
import com.medicare.backend.resource.ProductResource;
import com.medicare.backend.service.GenericService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Autowired
	private ProductResource productResource;
	
	@Autowired
	private GenericService<Product, Long> productService;
	
	@Test
	public void contextLoad() throws Exception{
		assertThat(productResource).isNotNull();
	}
	
	
	@Test
	public void getProduct_test() throws Exception {

		
		Product product = new Product();
		product.setName("TestProduct");
		product.setPrice(23f);
	
		product = productService.save(product);
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/api/v1/product/"+product.getPid()).accept(MediaType.APPLICATION_JSON);
		 
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();	
		
	
	}
	
	@Test
	public void saveProduct_test() throws Exception {

		
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/api/v1/product")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Vitamin TEST\",\"price\":8.0,\"category\":{\"id\":1}}")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andReturn();
	}
	
	
	@Test
	public void deleteRole_test() throws Exception {
		
		Product product = new Product();
		product = productService.save(product);
		
		RequestBuilder request = MockMvcRequestBuilders
				.delete("/api/v1/product/"+product.getPid())
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();

	}
}
