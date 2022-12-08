package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping(path = {"/login" }, method = RequestMethod.GET)
	public String getLoginPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString) {
		model.addAttribute("errorString", errorString);
		return "login";
	}
}
