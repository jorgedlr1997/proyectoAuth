package com.springboot.auth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.auth.entities.User;

@Repository
public interface UserRespository extends JpaRepository<User, Long>{

	boolean existsByUsername(String username);
	Optional<User> findByUsername(String username);
}
