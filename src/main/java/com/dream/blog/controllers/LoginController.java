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

import com.dream.blog.payloads.LoginDto;
import com.dream.blog.services.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {
	@Autowired
	LoginService loginService;

	@PostMapping("/userLogin")
	public ResponseEntity<LoginDto> userLogin(@Valid @RequestBody LoginDto userDto) {
		
		System.out.println("Controller :: Inside Method userEntr()y");

		LoginDto userLogin = this.loginService.userLogin(userDto);
		return new ResponseEntity<>(userLogin, HttpStatus.CREATED);

	}

	// get- get All User 
	@GetMapping("/")
	public ResponseEntity<List<LoginDto>> getAllUsers() {

		System.out.println("Controller :: Inside Method getAllUsers()");
		return ResponseEntity.ok(this.loginService.getAllUser());

	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<LoginDto> getSingleUsersDetail(@PathVariable ("userId") Integer userId){

		System.out.println("Controller :: Inside Method getSingleUsersDetail()");
		
		return ResponseEntity.ok(this.loginService.getLoginUserDetailById(userId));
		
		
	}

}
