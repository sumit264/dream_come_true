package com.dream.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.blog.entities.Login;


public interface LoginRepo extends JpaRepository<Login, Integer>{

}
