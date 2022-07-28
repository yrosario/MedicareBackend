package com.medicare.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Long id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User cartUser;
	
	@OneToMany(mappedBy="cart", fetch=FetchType.LAZY)
    @JsonIgnore
	private Set<CartItem> cartItems = new HashSet<CartItem>();

	public Cart() {
		this.id = null;
	}


	public Cart(User cartUser, Set<CartItem> cartItems) {
		this();
		this.cartUser = cartUser;
		this.cartItems = cartItems;
	}


	public User getCartUser() {
		return cartUser;
	}


	public void setCartUser(User cartUser) {
		this.cartUser = cartUser;
	}


	public Set<CartItem> getCartItems() {
		return cartItems;
	}


	public void setCartItems(CartItem cartItem) {
		this.cartItems.add(cartItem);
	}


	public Long getId() {
		return id;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cart [id=");
		builder.append(id);
		builder.append(", cartUser=");
		builder.append(cartUser);
		builder.append(", cartItems=");
		builder.append(cartItems);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
}
