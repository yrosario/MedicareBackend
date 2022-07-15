package com.medicare.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Long uid;
	private String firstname;
	private String lastname;
	private String email;
	private String password;

	@ManyToMany(cascade = { CascadeType.ALL})
	@JoinTable(
			name="o_order",
			joinColumns = { @JoinColumn(name = "uid", nullable = false )},
			inverseJoinColumns = { @JoinColumn(name = "pid", nullable = false ) }
			)

	private Set<Product> products = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;

	public User() {
		this.uid = null;
	}
	
	public User(String firstname, String lastname, String email, Set<Product> products) {
		this();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.products = products;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Product product) {
		if(product != null) {
		     this.products.add(product);
		}
	}
	
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [uid=");
		builder.append(uid);
		builder.append(", firstname=");
		builder.append(firstname);
		builder.append(", lastname=");
		builder.append(lastname);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", products=");
		builder.append(products);
		builder.append(", role=");
		builder.append(role);
		builder.append("]");
		return builder.toString();
	}


	
	


	
	
	
	
	

}
