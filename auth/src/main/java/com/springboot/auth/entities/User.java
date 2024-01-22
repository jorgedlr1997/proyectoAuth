package com.springboot.auth.entities;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.springboot.auth.validation.ExistsByUsername;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ExistsByUsername
	@NotBlank
	@Size(min = 4, max = 13)
	@Column(unique = true)
	private String username;

	@NotEmpty
	private String password;
	
	private boolean enabled;
	
	@Transient
	private boolean admin;
	
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id_user"),
	inverseJoinColumns = @JoinColumn(name = "id_role"), uniqueConstraints = {
			@UniqueConstraint(columnNames = {"id_user", "id_role"})}
	)
	private List<Roles> roles;
	

	@PrePersist
	public void prePersist() {
		this.enabled=true;
	}
	
	public User() {
		roles = new ArrayList<>();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id) && Objects.equals(username, other.username);
	}
	
	

}
