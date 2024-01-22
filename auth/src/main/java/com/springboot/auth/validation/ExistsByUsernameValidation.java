package com.springboot.auth.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.auth.services.IUserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String>{

	@Autowired
	private IUserService service;
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		
		if(service==null) {
			return true;
		}
		
		return !service.existsByUsername(username);
	}

}
