package com.dream.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.blog.entities.Login;
import com.dream.blog.payloads.LoginDto;
import com.dream.blog.repositories.LoginRepo;
import com.dream.blog.services.LoginService;
@Service
public class LoginServiceImpl implements LoginService {


	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LoginRepo loginRepo;

	@Override
	public LoginDto createLogin(LoginDto user) {
		return null;
	}
	
	@Override
	public LoginDto userLogin(LoginDto user) {
		
		System.out.println("Service :: Inside the user Login");
		Login login=this.dtoToUser(user);
		
		Login saveUser=this.loginRepo.save(login);
					
		return this.userToDto(saveUser);
	}

	@Override
	public LoginDto updateLogin(LoginDto user, Integer userId) {
		return null;
	}

	@Override
	public LoginDto getLoginUserDetailById(Integer userId) {
		System.out.println("Service :: getLoginById()");
		
		Login userdetailsById=loginRepo.findById(userId).orElseThrow(() -> new NullPointerException("Id not Found"));
		
		LoginDto userDetail=this.userToDto(userdetailsById);
		
		
		return userDetail;
	}

	@Override
	public List<LoginDto> getAllUser() {
		
		System.out.println("Service :: Inside the getAllUser() method");
		
		List<Login> users = this.loginRepo.findAll();
		/*
		 * for (Login user : users) {
		 * 
		 * // dtoList.add(this.userToDto(user)); //Old Approch
		 * 
		 * System.out.println("===============" + user.getUserName());
		 * 
		 * }
		 */

		// return dtoList;

		List<LoginDto> userDtos=users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

		return userDtos;

	}

	@Override
	public void deleteUser(Integer userId) {

	}
	
	private LoginDto userToDto(Login login) {
		
		//LoginDto userDto=this.modelMapper.map(user, LoginDto.class);
		
		LoginDto loginDto=new LoginDto();
		
		 loginDto.setId(login.getId());
		 loginDto.setUserName(login.getUsername());
		 
		 loginDto.setPassword(login.getPassword());
		
		return loginDto;
		
	}
	
	private Login dtoToUser(LoginDto loginDto) {
		
		Login login=this.modelMapper.map(loginDto, Login.class);
		/*
		 * user.setId(userDto.getId()); user.setEmail(userDto.getEmail());
		 * user.setName(userDto.getName()); user.setAbout(userDto.getAbout());
		 * user.setPassword(userDto.getPassword());
		 */
		return login;

}
}