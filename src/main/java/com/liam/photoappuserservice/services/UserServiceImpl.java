package com.liam.photoappuserservice.services;

import java.util.ArrayList;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.liam.photoappuserservice.models.UserEntity;
import com.liam.photoappuserservice.repositories.UserRepository;
import com.liam.photoappuserservice.shared.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	BCryptPasswordEncoder bCrypt;
	
	public UserServiceImpl() {}
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDTO createUser(UserDTO newUser) {
		UserEntity user = new UserEntity();
		user = UserMapper.INSTANCE.destinationToSource(newUser);
		
		// Encrypting password
		user.setPassword(bCrypt.encode(user.getPassword()));


//		System.out.println(ReflectionToStringBuilder.toString(userDTO));
		UserEntity createdUser = userRepo.save(user);

//		UserDTO user = UserMapper.INSTANCE.destinationToSource(userDTO);
		UserDTO userDTO = UserMapper.INSTANCE.sourceToDestination(createdUser);
		System.out.println(ReflectionToStringBuilder.toString(userDTO));

		return userDTO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = userRepo.findByEmail(username);
		
		if(user == null) throw new UsernameNotFoundException(username);
		
		
		return new User(user.getEmail(), user.getPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDTO getUserDetailsByEmail(String email) {
		UserEntity user = userRepo.findByEmail(email);
		
		if(user == null) throw new UsernameNotFoundException(email);
		
		UserDTO userDTO = UserMapper.INSTANCE.sourceToDestination(user);
		
		return userDTO;
	}

	@Override
	public UserDTO getUser(Long userId) {
		UserEntity user = userRepo.findById(userId).orElseGet(null);
		
		UserDTO userDTO = UserMapper.INSTANCE.sourceToDestination(user);
		return userDTO;
	}
	
	
	

}
