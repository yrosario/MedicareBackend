package com.medicare.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare.backend.model.CartItem;
import com.medicare.backend.repository.CartItemRepository;

@Service
public class CartItemService implements GenericService<CartItem, Long>{

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Override
	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}

	@Override
	public List<CartItem> findAll() {
		return cartItemRepository.findAll();
	}

	@Override
	public CartItem findById(Long id) {
		Optional<CartItem> oCartItem = cartItemRepository.findById(id);
		return oCartItem.isPresent() ? oCartItem.get() : null;
	}

	@Override
	public CartItem update(CartItem cart) {
		return cartItemRepository.save(cart);
	}

	@Override
	public boolean delete(Long id) {
		if(cartItemRepository.findById(id).isPresent()) {
			cartItemRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
