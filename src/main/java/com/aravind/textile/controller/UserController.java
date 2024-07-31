package com.aravind.textile.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aravind.textile.model.User;

import com.aravind.textile.serviceimpl.UserServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:3000")
public class UserController {
	@Autowired
	UserServiceImpl service;

	static final String SUCCESS = "Success";
	static final String FAILURE = "Failure";

	@PostMapping("/register")
	public String addUser(@RequestBody User user) {

		try {
			service.addUser(user);
			return "Registered Successfully";
		} catch (Exception e) {
			return "Register Failure";
		}

	}

	@PostMapping("/login")
	public String login(@RequestBody User user) {
		Optional<User> user1 = service.authenticate(user.getUsername(), user.getPassword());
		if (user1.isPresent()) {
			return "Login successful";
		} else {
			return "Login failed: Invalid username or password";
		}
	}

}
