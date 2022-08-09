package com.codewithdurgesh.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blogexceptions.ResourceNotFoundException;
@Service
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	private UserRepo userRepo;

	@Override //UserDetailService contain loadUserByUsername method that verified the User
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//loading User from DB by UserName
		User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("userName", "not Found for email ", 0));
		
		return user;
	}

	
}
