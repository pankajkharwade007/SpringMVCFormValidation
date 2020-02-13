package com.nt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nt.bo.UserBo;

@Repository
public class LoginDao {

	private static final String AUTh_QRY="SELECT COUNT(*) FROM USERLIST WHERE UNAME=? AND PWD=?";
	
	@Autowired
	private JdbcTemplate jt;
	
	public int validate(UserBo bo) {
		
		int cnt=jt.queryForObject(AUTh_QRY,new Object[] {bo.getUser(),bo.getPwd()}, Integer.class);
		return cnt;
	}
	
	
}
