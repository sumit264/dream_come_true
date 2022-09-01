package com.dream.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.blog.payloads.SignUpDto;
import com.dream.blog.services.SignUpService;

@RestController
@RequestMapping("/api/signUp")
public class SignUpController {

	@Autowired
	SignUpService signUpService;

	@PostMapping("/userSignUp")
	public ResponseEntity<SignUpDto> userSignUp(@Valid @RequestBody SignUpDto userDto) {

		System.out.println("Controller :: Inside Method userSignUp()y");

		SignUpDto userSignUp = this.signUpService.userSignUp(userDto);
		return new ResponseEntity<>(userSignUp, HttpStatus.CREATED);

	}

	// get- get All User
	@GetMapping("/")
	public ResponseEntity<List<SignUpDto>> getAllUsers() {

		System.out.println("Controller :: Inside Method getAllUsers()");
		return ResponseEntity.ok(this.signUpService.getSignUpAllUser());

	}

	@GetMapping("/{userId}")
	public ResponseEntity<SignUpDto> getSingleUsersDetail(@PathVariable("userId") Integer userId) {

		System.out.println("Controller :: Inside Method getSingleUsersDetail()");

		return ResponseEntity.ok(this.signUpService.getSignUpUserDetailById(userId));

	}

}
