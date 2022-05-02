package com.liam.photoappuserservice.services;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liam.photoappuserservice.models.User;
import com.liam.photoappuserservice.repositories.UserRepository;
import com.liam.photoappuserservice.shared.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	
	public UserServiceImpl() {}
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDTO createUser(UserDTO newUser) {
		User user = new User();
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setEmail(newUser.getEmail());
		user.setPassword(newUser.getPassword());
		
//		UserDTO userDTO = UserMapper.INSTANCE.tDto(null);

		System.out.println(ReflectionToStringBuilder.toString(user));
		System.out.println(ReflectionToStringBuilder.toString(newUser));

		return userRepo.save(newUser);
	}

}
