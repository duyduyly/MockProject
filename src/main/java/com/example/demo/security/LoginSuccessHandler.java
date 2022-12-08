package com.example.demo.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.service.SecurityService;
import com.example.demo.service.UserService;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		UserDetails userDetails = userService.loadUserByUsername(securityService.findLoggedInUsername());
		
		userService.logUserIntoSession(userDetails, request.getSession());

		response.sendRedirect(getDefaultTargetUrl());
	}
}
