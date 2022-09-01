package com.dream.blog.services;

import java.util.List;

import com.dream.blog.payloads.LoginDto;


public interface LoginService {


	LoginDto createLogin(LoginDto user);

	LoginDto updateLogin(LoginDto user,Integer userId);

	LoginDto getLoginUserDetailById(Integer userId);

	List<LoginDto> getAllUser();
	

	void deleteUser(Integer userId);
	

	LoginDto userLogin(LoginDto user);
	
}
