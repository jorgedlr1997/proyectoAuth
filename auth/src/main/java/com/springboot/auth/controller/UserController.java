package com.springboot.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.auth.entities.User;
import com.springboot.auth.services.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult result){
		
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.save(user));
		
	}
	
	
	private ResponseEntity<?> validation(BindingResult result){
		Map<String, String> errors = new HashMap<>();
		
		result.getFieldErrors().forEach(err -> {
			errors.put(err.getField(), "Error en el campo " + err.getField()+ "{" + err.getDefaultMessage()+"}");
		});
		
		return ResponseEntity.badRequest().body(errors);
	}

}
