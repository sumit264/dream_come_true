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
import com.dream.blog.payloads.User_DeliveryDto;
import com.dream.blog.services.User_DeliveryService;

@RestController
@RequestMapping("/api/userInfoDelivery")
public class UserDeliveryController {


	@Autowired
	User_DeliveryService user_DeliveryService;
	
	@PostMapping("/messageInfo")
	public ResponseEntity<User_DeliveryDto> userMsgDelivery(@Valid @RequestBody User_DeliveryDto user_DeliveryDto) {

		System.out.println("Controller :: Inside Method userMsgDelivery()y");

		User_DeliveryDto deliveryDto = this.user_DeliveryService.createuserMsgDeliveryDetail(user_DeliveryDto);
		return new ResponseEntity<>(deliveryDto, HttpStatus.CREATED);

	}
	
	// get- get All User
		@GetMapping("/")
		public ResponseEntity<List<User_DeliveryDto>> getAllUsers() {

			System.out.println("Controller :: Inside Method getAllUsers()");
			return ResponseEntity.ok(this.user_DeliveryService.getAllUserMsgDeliveryDetail());

		}
		

		@GetMapping("/{userId}")
		public ResponseEntity<User_DeliveryDto> getSingleUsersDetail(@PathVariable("userId") Integer userId) {

			System.out.println("Controller :: Inside Method getSingleUsersDetail()");

			return ResponseEntity.ok(this.user_DeliveryService.getuserMsgDeliveryDetailById(userId));

		}
}
