 package com.medicare.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="product")
public class Product {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private final Long pid;
	private String name;
	private float price;
	@Column(name="imageurl")
	private String imageUrl;
	@JsonIgnore
	@ManyToMany(mappedBy = "products")
	private Set<User> users = new HashSet<>();
	@ManyToOne
	private Category category;
	
	public Product() {
		this.pid = null;
	}

	public Product(String name, float price, String imageUrl) {
		this();
		this.name = name;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getPid() {
		return pid;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(User user) {
		if(user != null) {
		     this.users.add(user);
		}
	}
	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [pid=");
		builder.append(pid);
		builder.append(", name=");
		builder.append(name);
		builder.append(", category=");
		builder.append(category);
		builder.append(", price=");
		builder.append(price);
		builder.append(", imageUrl=");
		builder.append(imageUrl);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
}
