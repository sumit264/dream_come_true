package com.dream.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.blog.entities.SignUp;
import com.dream.blog.entities.User_Delivery;
import com.dream.blog.payloads.SignUpDto;
import com.dream.blog.payloads.User_DeliveryDto;
import com.dream.blog.repositories.User_DeliveryRepo;
import com.dream.blog.services.User_DeliveryService;

@Service
public class User_DeliveryServiceImpl implements User_DeliveryService{
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private User_DeliveryRepo user_DeliveryRepo;

	@Override
	public User_DeliveryDto createuserMsgDeliveryDetail(User_DeliveryDto user) {
		
		User_Delivery user_Delivery=this.dtoToUser(user);
		
		User_Delivery savedDeliverDetails=user_DeliveryRepo.save(user_Delivery);
		
		return this.userToDto(savedDeliverDetails);
	}

	@Override
	public User_DeliveryDto updateuserMsgDeliveryDetail(User_DeliveryDto user, Integer userId) {
		return null;
	}

	@Override
	public User_DeliveryDto getuserMsgDeliveryDetailById(Integer userId) {
		
		User_Delivery user_Delivery = this.user_DeliveryRepo.findById(userId).orElseThrow(() -> new NullPointerException("Id Not Found Exception"));

		User_DeliveryDto deliveryDto = this.userToDto(user_Delivery);

		return deliveryDto;
	}

	@Override
	public List<User_DeliveryDto> getAllUserMsgDeliveryDetail() {
		System.out.println("SignUp Service :: Inside the getAllUserMsgDeliveryDetail() method");

		List<User_Delivery> allUserDetails = this.user_DeliveryRepo.findAll();

		List<User_DeliveryDto> userDetails = allUserDetails.stream().map((deliveryDetails -> this.userToDto(deliveryDetails))).collect(Collectors.toList());

		return userDetails;

	}

	@Override
	public void userMsgDeliveryDetailInfo(Integer userId) {
		
	}

	@Override
	public User_DeliveryDto userMsgDeliveryDetail(User_DeliveryDto user) {
		return null;
	}

	private User_DeliveryDto userToDto(User_Delivery user_Delivery) {

		User_DeliveryDto user_DeliveryDto=this.modelMapper.map(user_Delivery, User_DeliveryDto.class);



		return user_DeliveryDto;

	}

	private User_Delivery dtoToUser(User_DeliveryDto user_DeliveryDto) {

		User_Delivery user_Delivery = this.modelMapper.map(user_DeliveryDto, User_Delivery.class);
		/*
		 * user.setId(userDto.getId()); user.setEmail(userDto.getEmail());
		 * user.setName(userDto.getName()); user.setAbout(userDto.getAbout());
		 * user.setPassword(userDto.getPassword());
		 */
		return user_Delivery;

	}
}
