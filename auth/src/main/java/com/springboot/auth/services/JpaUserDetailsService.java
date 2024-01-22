package com.springboot.auth.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.auth.entities.User;
import com.springboot.auth.repositories.UserRespository;

public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRespository userRespository;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userOptional = userRespository.findByUsername(username);
		
		if (userOptional.isEmpty()) {
			throw new UsernameNotFoundException(
					String.format("El username %s no existe en la base de datos", username));
		}
		
		User user = userOptional.orElseThrow();
		
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(roles -> new SimpleGrantedAuthority(roles.getName())).collect(Collectors.toList());
		
		
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);
	}

}
