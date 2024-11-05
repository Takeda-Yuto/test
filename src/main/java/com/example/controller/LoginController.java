package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.form.UsersForm;


@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String showLogin(@ModelAttribute UsersForm usersForm) {
		usersForm.setUsername("guest");
		usersForm.setPassword("guest");
		return "login";
	}

}
