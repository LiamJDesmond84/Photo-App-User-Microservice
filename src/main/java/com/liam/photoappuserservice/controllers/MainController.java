package com.liam.photoappuserservice.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.liam.photoappuserservice.models.AlbumResponseModel;
import com.liam.photoappuserservice.models.UserEntity;
import com.liam.photoappuserservice.models.UserResponseModel;
import com.liam.photoappuserservice.services.UserMapper;
import com.liam.photoappuserservice.services.UserService;
import com.liam.photoappuserservice.shared.UserDTO;

@RestController
@RequestMapping("/api/users")
public class MainController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${spring.application.name}")
	private String appName;

	
	@Autowired
	private Environment env;
	
	@GetMapping("/status/check")
	public String status() {
		return "Working on port " + env.getProperty("local.server.port") + " & App name is: " + appName + " Secret Key: " + env.getProperty("token.secret");
	}
	
	
	
	@PostMapping("/create")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO newUser) {
		
		UserDTO createdUser = userServ.createUser(newUser);
		

		System.out.println(ReflectionToStringBuilder.toString(createdUser));
//		return new ResponseEntity<UserDTO>(createdUser, HttpStatus.CREATED);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") Long userId) {
		
		UserDTO userDTO = userServ.getUser(userId);
		
		UserResponseModel user = UserMapper.INSTANCE.destinationToSource1(userDTO);
		
		ResponseEntity<List<AlbumResponseModel>> userAlbums = restTemplate.exchange("http://ALBUM-SERVICE/api/users/" + user.getUserId() + "/users", HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumResponseModel>>() {
			
		});
		
		List<AlbumResponseModel> userAlbumsList = userAlbums.getBody();
		
		
		
		user.setAlbums(userAlbumsList);
		
		
		
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

}
