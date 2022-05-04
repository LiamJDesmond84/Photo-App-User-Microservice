package com.liam.photoappuserservice.services;

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;


import com.liam.photoappuserservice.models.UserEntity;
import com.liam.photoappuserservice.shared.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	
//	User sourceToDestination(UserDTO userDTO);
//	UserDTO destinationToSource(User user);
	
	UserDTO sourceToDestination(UserEntity user);
	UserEntity destinationToSource(UserDTO userDTO);
//	UserDTO tDto(User user);

}
