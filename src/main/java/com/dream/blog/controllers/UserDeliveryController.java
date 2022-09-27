package com.dream.blog.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dream.blog.exceptionhandling.ResourceNotFoundException;
 import com.dream.blog.payloads.User_DeliveryDto;
import com.dream.blog.services.User_DeliveryService;
import com.dream.blog.services.Impl.User_DeliveryServiceImplException;

@RestController
@RequestMapping("/api/userInfoDelivery")
public class UserDeliveryController {


	@Autowired
	User_DeliveryService user_DeliveryService;
	
	//@PostMapping("/messageInfo") //In Future, We will Use this method

	/*
	 * @RequestMapping(value = "/messageInfo", method = RequestMethod.POST) public
	 * ResponseEntity<User_DeliveryDto> userMsgDelivery(User_DeliveryDto
	 * user_DeliveryDto,HttpServletRequest request, HttpServletResponse response) {
	 * 
	 * System.out.println("Controller :: Inside Method userMsgDelivery()");
	 * 
	 * User_DeliveryDto deliveryDto =
	 * this.user_DeliveryService.createuserMsgDeliveryDetail(user_DeliveryDto);
	 * 
	 * return new ResponseEntity<>(deliveryDto, HttpStatus.CREATED);
	 * 
	 * }
	 */
	
	@RequestMapping(value = "/messageInfo", method = RequestMethod.POST)
	public ModelAndView userMsgDelivery(User_DeliveryDto user_DeliveryDto,HttpServletRequest request, HttpServletResponse response) {
 
		ModelAndView model = new ModelAndView("/status");
		System.out.println("Controller :: Inside Method userMsgDelivery()");

		User_DeliveryDto deliveryDto = this.user_DeliveryService.createuserMsgDeliveryDetail(user_DeliveryDto);
		
		model.addObject("deliverydate", deliveryDto.getDeliveryDate());

		model.addObject("message", deliveryDto.getMessageToBeDelivery());
		
		return model;

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
