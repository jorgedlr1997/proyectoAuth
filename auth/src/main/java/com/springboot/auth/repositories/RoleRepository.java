package com.springboot.auth.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.auth.entities.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long>{

	Roles findByName(String name);
}
