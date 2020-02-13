package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.bo.UserBo;
import com.nt.dao.LoginDao;
import com.nt.dto.UserDto;

@Service
public class LoginService {

	@Autowired
	private LoginDao dao;
	
	public String authenticate(UserDto dto) {
		UserBo bo=new UserBo();
		bo.setUser(dto.getUser());
		bo.setPwd(dto.getPwd());
		
		int cnt=dao.validate(bo);
		
		if(cnt!=0) {
			return "InValid Credentials";
		}else {
			return "Valid Crederntials";
		}
		
	}
	
}
