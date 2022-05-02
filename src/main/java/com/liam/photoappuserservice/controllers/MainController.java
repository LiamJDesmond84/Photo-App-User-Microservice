package com.liam.photoappuserservice.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liam.photoappuserservice.models.User;
import com.liam.photoappuserservice.services.UserService;

@RestController
@RequestMapping("/api/users")
public class MainController {
	
	@Autowired
	private UserService userServ;
	
	@Value("${spring.application.name}")
	private String appName;

	
	@Autowired
	private Environment env;
	
	@GetMapping("/status/check")
	public String status() {
		return "Working on port " + env.getProperty("local.server.port") + " & App name is: " + appName;
	}
	
	
	
	@PostMapping("/create")
	public User createUser(@Valid @RequestBody User newUser) {
		
		return userServ.createUser(newUser);
	}

}
