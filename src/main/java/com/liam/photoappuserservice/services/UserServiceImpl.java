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
		user = UserMapper.INSTANCE.destinationToSource(newUser);
		
		UserDTO userDTO = UserMapper.INSTANCE.sourceToDestination(user);

		System.out.println(ReflectionToStringBuilder.toString(user));
		System.out.println(ReflectionToStringBuilder.toString(userDTO));
		userRepo.save(user);
//		UserDTO user = UserMapper.INSTANCE.destinationToSource(userDTO);

		return userDTO;
	}

}
