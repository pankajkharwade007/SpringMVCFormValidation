package com.nt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nt.commands.User;
import com.nt.dto.UserDto;
import com.nt.service.LoginService;
import com.nt.validator.LoginValidator;

public class LoginController {

	@Autowired
	private LoginService service;
	
	@Autowired
	private LoginValidator validator;
	
	@RequestMapping(value="/login.htm",method=RequestMethod.GET)
	public String showForm(Model model) {
		User user=new User();
		model.addAttribute("userCmd",user);
		return "login";
	}
	
	@RequestMapping(value="/login.htm",method=RequestMethod.POST)
	public String processForm(Model model,@ModelAttribute("userCmd")User cmd,BindingResult errors) {
		UserDto udto=new UserDto();
		udto.setUser(cmd.getUser());
		udto.setPwd(cmd.getPwd());
		
		if(validator.supports(User.class)) {
			validator.validate(cmd, errors);
			if(errors.hasErrors()) {
				return "login";
			}
		}
		
		String result=service.authenticate(udto);
		
		model.addAttribute("result",result);
		
		return "login";
	}
	
	@ModelAttribute("domains")
	public List<String> populateDomains(){
		List<String> domainsList=new ArrayList<String>();
		
		domainsList.add("gmail.com");
		domainsList.add("yahoo.com");
		domainsList.add("rediff.com");
		return domainsList;
	}

	@InitBinder
	public void myInitBinder(WebDataBinder binder) {
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf,true));
	}
}
