package com.codewithdurgesh.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blogexceptions.*;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		
		User savedUser=this.userRepo.save(user);
		
		
		return this.userToDto(savedUser);  
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId)  {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		

		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
		User updatedUser=this.userRepo.save(user);
		
		UserDto userDto1=this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		//List<UserDto> dtoList=null; //define for first Approch
		
		List<User> users=this.userRepo.findAll();
		
		
		/*
		 * for(User user:users) {
		 * 
		 * dtoList.add(this.userToDto(user)); //Old Approch
		 * 
		 * }
		 * 
		 * return dtoList;
		 */
		List<UserDto> userDtos=users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {

		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		this.userRepo.delete(user);
		
	}
	
	// User se aaye data ko entity mein convert karenge kyunki database se entity baat karti h ya comunicate karti h
	private User dtoToUser(UserDto userDto) {
		
		User user=this.modelMapper.map(userDto, User.class);
		/*
		 * user.setId(userDto.getId()); user.setEmail(userDto.getEmail());
		 * user.setName(userDto.getName()); user.setAbout(userDto.getAbout());
		 * user.setPassword(userDto.getPassword());
		 */
		return user;
		
	}
	
	// Database se aaye data ko DTO mein convert karenge kyunki user se dto baat karta h ya comunicate karta h
		private UserDto userToDto(User user) {
			
			UserDto userDto=this.modelMapper.map(user, UserDto.class);
			/*
			 * userDto.setId(user.getId()); userDto.setEmail(user.getEmail());
			 * userDto.setName(user.getName()); userDto.setAbout(user.getAbout());
			 * userDto.setPassword(user.getPassword());
			 */
			return userDto;
			
		}

		//Register User
		@Override
		public UserDto registerNewUser(UserDto userDto) {
			
			User user = this.modelMapper.map(userDto, User.class);
			//set the password in encoded form
			
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
			
			
			return null;
		}
		
	
	
	//Creating Constructor for Mockito Testing Only
		
		public UserServiceImpl(UserRepo userRepo) {
			
			this.userRepo=userRepo;
		}
	

}
