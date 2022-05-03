package com.liam.photoappuserservice.services;

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

import com.liam.photoappuserservice.models.User;
import com.liam.photoappuserservice.shared.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	
//	User sourceToDestination(UserDTO userDTO);
//	UserDTO destinationToSource(User user);
	
	UserDTO sourceToDestination(User user);
	User destinationToSource(UserDTO userDTO);
//	UserDTO tDto(User user);

}
