package com.example.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
		      HttpServletResponse response,
		      AuthenticationException exception) 
		      throws IOException, ServletException {
		String errorString = exception.getMessage().toString();
		System.out.println(errorString);
		System.out.println("Authentication failed");
		request.setAttribute("errorString", errorString);
		response.sendRedirect("/login?errorString=" +errorString);
		System.out.println("Forwarded from login failure.");
	}
}
