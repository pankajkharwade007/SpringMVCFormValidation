package com.nt.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nt.commands.User;

public class LoginValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(User.class);
	}

	@Override
	public void validate(Object cmd, Errors errors) {
		// TODO Auto-generated method stub
		User ucmd=(User)cmd;
		
		if(ucmd.getUser()==null || ucmd.getUser().equals("") || ucmd.getUser().length()==0) {
			errors.rejectValue("user", "user.required");
		}
		
		if(ucmd.getPwd()==null || ucmd.getPwd().equals("") || ucmd.getPwd().length()==0) {
			errors.rejectValue("pwd", "pwd.required");
		}
		
		
	}

	
	
}
