package com.codewithdurgesh.blog.Service;

import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.UserService;
import com.codewithdurgesh.blog.services.Impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	UserRepo userRepo;
	//@Autowired
	UserService userService;
	
	@BeforeEach
	void setUp() {
		
		this.userService=new UserServiceImpl(this.userRepo);
		
	}
	
	@Test
	void getAllUser() {
		
		List<UserDto> allUser = userService.getAllUser();
		
		verify(userRepo).findAll();
	}
	
	
}
