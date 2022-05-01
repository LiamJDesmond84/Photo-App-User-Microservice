package com.liam.photoappuserservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class MainController {
	
	@GetMapping("/status/check")
	public String status() {
		return "Working";
	}

}
