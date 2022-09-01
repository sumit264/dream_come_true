package com.dream.blog.services;

import java.util.List;

import com.dream.blog.payloads.User_DeliveryDto;

public interface User_DeliveryService {

	User_DeliveryDto createuserMsgDeliveryDetail(User_DeliveryDto user);

	User_DeliveryDto updateuserMsgDeliveryDetail(User_DeliveryDto user,Integer userId);

	User_DeliveryDto getuserMsgDeliveryDetailById(Integer userId);

	List<User_DeliveryDto> getAllUserMsgDeliveryDetail();
	

	void userMsgDeliveryDetailInfo(Integer userId);
	

	User_DeliveryDto userMsgDeliveryDetail(User_DeliveryDto user);
}
