package com.medicare.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.medicare.backend.model.Cart;
import com.medicare.backend.repository.CartItemRepository;
import com.medicare.backend.repository.CartRepository;

public class CartServiceImpl implements GenericService<Cart, Long> {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemReposiotry;

	@Override
	public Cart save(Cart cart) {
		return this.cartRepository.save(cart);
	}

	@Override
	public List<Cart> findAll() {
		return null;
	}

	@Override
	public Cart findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart update(Cart t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
