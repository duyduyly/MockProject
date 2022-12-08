package com.example.demo.service.iservice;

import javax.servlet.http.HttpSession;

public interface ISecurityService {
	
	public String findLoggedInUsername();

	void autoLogin(String username, String password, HttpSession session);

}
