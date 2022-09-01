package com.dream.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.blog.entities.SignUp;
import com.dream.blog.payloads.SignUpDto;
import com.dream.blog.repositories.SignUpRepo;
import com.dream.blog.services.SignUpService;

@Service
public class SignUpServiceImpl implements SignUpService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SignUpRepo signUpRepo;

	@Override
	public SignUpDto updateSignUp(SignUpDto user, Integer userId) {
		return null;

	}

	@Override
	public SignUpDto getSignUpUserDetailById(Integer userId) {

		SignUp signUpUserDetail = this.signUpRepo.findById(userId).orElseThrow(() -> new NullPointerException("Id Not Found Exception"));

		SignUpDto signUpDto = this.userToDto(signUpUserDetail);

		return signUpDto;
	}

	@Override
	public List<SignUpDto> getSignUpAllUser() {

		System.out.println("SignUp Service :: Inside the getSignUpAllUser() method");

		List<SignUp> signUpuserDetails = this.signUpRepo.findAll();

		List<SignUpDto> userDetails = signUpuserDetails.stream().map((signUpUsers -> this.userToDto(signUpUsers))).collect(Collectors.toList());

		return userDetails;
	}

	@Override
	public void deleteSignUpUser(Integer userId) {

	}

	@Override
	public SignUpDto userSignUp(SignUpDto signUpDto) {

		System.out.println("SignUp Service :: Inside the user Login");

		SignUp signUp = this.dtoToUser(signUpDto);

		SignUp savedSignUpUser = this.signUpRepo.save(signUp);

		return this.userToDto(savedSignUpUser);
	}

	private SignUpDto userToDto(SignUp signUp) {

		// LoginDto userDto=this.modelMapper.map(user, LoginDto.class);

		SignUpDto signUpDto = new SignUpDto();

		signUpDto.setId(signUp.getId());
		signUpDto.setUserName(signUp.getUserName());

		signUpDto.setPassword(signUp.getPassword());

		signUpDto.setEmail(signUp.getEmail());
		signUpDto.setReEnter_email(signUp.getReEnter_email());
		signUpDto.setPhoneNo(signUp.getPhoneNo());
		signUpDto.setGender(signUp.getEmail());
		signUpDto.setDob(signUp.getDob());

		return signUpDto;

	}

	private SignUp dtoToUser(SignUpDto signUpDto) {

		SignUp signUp = this.modelMapper.map(signUpDto, SignUp.class);
		/*
		 * user.setId(userDto.getId()); user.setEmail(userDto.getEmail());
		 * user.setName(userDto.getName()); user.setAbout(userDto.getAbout());
		 * user.setPassword(userDto.getPassword());
		 */
		return signUp;

	}

}
