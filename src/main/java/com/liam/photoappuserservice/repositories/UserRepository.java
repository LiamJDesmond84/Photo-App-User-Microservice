package com.liam.photoappuserservice.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liam.photoappuserservice.shared.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {

	List<UserDTO> findAll();
}
