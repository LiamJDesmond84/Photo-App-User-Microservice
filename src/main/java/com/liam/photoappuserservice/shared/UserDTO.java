package com.liam.photoappuserservice.shared;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;




@Entity
@Table(name="users")
public class UserDTO implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2167092540681747836L;


	
//	@Id
//	@GeneratedValue(generator = "UUID")
//	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//	@Type(type = "org.hibernate.type.UUIDCharType")
//	@Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
//	private UUID id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	

	@NotBlank
	@Size(min=2, message="First Name Required")
	@Column(nullable = false, length = 50)
	private String firstName;
	
	@NotBlank
	@Size(min=2, message="Last Name Required")
	@Column(nullable = false, length = 50)
	private String lastName;
	
	@Email
	@NotBlank(message="Email Required")
	@Column(nullable = false, length = 120, unique = true)
	private String email;
	
	@NotBlank
	@Size(min=2, message="Password Required")
	@Column(nullable = false, length = 120, unique = true)
	private String password;
	





	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
