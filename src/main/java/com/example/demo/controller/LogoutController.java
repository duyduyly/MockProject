package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
	
	@RequestMapping(path = { "/logout" }, method = RequestMethod.GET)
	public String getViewLogoutPage(HttpSession session) {
		session.invalidate();
		System.out.println("Logged out.");
		return "redirect:/login";
	}
}
