package com.dream.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.blog.entities.Login;


public interface LoginRepo extends JpaRepository<Login, Integer>{

	
	Optional<Login> findByUserName(String userName);
}
