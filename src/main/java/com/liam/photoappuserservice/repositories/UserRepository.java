package com.liam.photoappuserservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liam.photoappuserservice.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAll();
}
