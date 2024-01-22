package com.springboot.auth.services;

import java.util.List;
import java.util.Optional;

import com.springboot.auth.entities.User;

public interface IUserService {

	List<User> findAll();
	
	Optional<User> findById(Long id);
	
	User save(User user);
	
	void delete(Long id);
	
	boolean existsByUsername(String username);
	
}
