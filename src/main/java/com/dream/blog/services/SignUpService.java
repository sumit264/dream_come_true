package com.dream.blog.services;

import java.util.List;

import com.dream.blog.payloads.SignUpDto;

public interface SignUpService {
	

	SignUpDto updateSignUp(SignUpDto user,Integer userId);

	SignUpDto getSignUpUserDetailById(Integer userId);

	List<SignUpDto> getSignUpAllUser();
	

	void deleteSignUpUser(Integer userId);
	

	SignUpDto userSignUp(SignUpDto user);

}
