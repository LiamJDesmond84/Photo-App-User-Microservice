package com.liam.photoappuserservice.models;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLogin {
	
	@Email
	@NotBlank(message="Email Required")
	private String email;
	
	@NotBlank
	@Size(min=2, message="Password Required")
	private String password;

	public UserLogin() {
	}

	public UserLogin( String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
