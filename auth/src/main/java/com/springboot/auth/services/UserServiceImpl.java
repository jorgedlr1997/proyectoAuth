package com.springboot.auth.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.auth.entities.Roles;
import com.springboot.auth.entities.User;
import com.springboot.auth.repositories.RoleRepository;
import com.springboot.auth.repositories.UserRespository;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	UserRespository userRespository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> findAll() {
		return userRespository.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRespository.findById(id);
	}

	@Override
	public User save(User user) {
		
		List<Roles> roles = new ArrayList<>();
		Roles userRole = roleRepository.findByName("USER_ROLE");
		roles.add(userRole);
		
		if (user.isAdmin()) {
		Roles adminRole = roleRepository.findByName("ADMIN_ROLE");
		roles.add(adminRole);
		}
		
		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRespository.save(user);
	}

	@Override
	public void delete(Long id) {
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRespository.existsByUsername(username);
	}

}
