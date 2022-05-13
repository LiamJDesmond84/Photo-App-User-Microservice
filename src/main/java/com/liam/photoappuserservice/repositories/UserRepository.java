package com.liam.photoappuserservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import com.liam.photoappuserservice.models.User;
import com.liam.photoappuserservice.models.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	List<UserEntity> findAll();
	
	UserEntity findByEmail(String email);
}
