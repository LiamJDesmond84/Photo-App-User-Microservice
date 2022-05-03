package com.liam.photoappuserservice.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liam.photoappuserservice.models.User;
import com.liam.photoappuserservice.services.UserService;
import com.liam.photoappuserservice.shared.UserDTO;

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
	public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO newUser) {
		userServ.createUser(newUser);
		
		return new ResponseEntity<User>(HttpStatus.CREATED);
	}

}
