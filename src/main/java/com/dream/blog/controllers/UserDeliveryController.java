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

import com.dream.blog.exceptionhandling.ResourceNotFoundException;
import com.dream.blog.payloads.SignUpDto;
import com.dream.blog.payloads.User_DeliveryDto;
import com.dream.blog.services.User_DeliveryService;
import com.dream.blog.services.Impl.User_DeliveryServiceImplException;

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
		public ResponseEntity<List<User_DeliveryDto>> getAllUsers() throws ResourceNotFoundException, User_DeliveryServiceImplException {

			System.out.println("Controller :: Inside Method getAllUsers()");
			
			List<User_DeliveryDto> allUserMsgDeliveryDetail = this.user_DeliveryService.getAllUserMsgDeliveryDetail();
			
			if (allUserMsgDeliveryDetail == null) {
				throw new ResourceNotFoundException("User Details Not Found");
			}
			
			return ResponseEntity.ok(allUserMsgDeliveryDetail);

		}
		

		@GetMapping("/{userId}")
		public ResponseEntity<User_DeliveryDto> getSingleUsersMsgDeliveryDetail(@PathVariable("userId") Integer userId) throws User_DeliveryServiceImplException, ResourceNotFoundException {

			System.out.println("Controller :: Inside Method getSingleUsersMsgDeliveryDetail()");
			
			User_DeliveryDto detailById = this.user_DeliveryService.getuserMsgDeliveryDetailById(userId);
			
			if(detailById==null) {
				
				throw new ResourceNotFoundException("User Details Not Found For User_Id +" +userId+ "+");
			}

			return ResponseEntity.ok(detailById);

		}
}
