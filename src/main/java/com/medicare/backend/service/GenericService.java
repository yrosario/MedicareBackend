package com.medicare.backend.service;

import java.util.List;

import com.medicare.backend.model.User;

public interface GenericService<T,ID> {

	public User addUser(T t);
	public List<T> findAll();
	public T findById(ID id);
	public T update(T t);
	public boolean delete(ID id);
}
