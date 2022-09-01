package com.dream.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.blog.entities.SignUp;

public interface SignUpRepo extends JpaRepository<SignUp, Integer>{

}
