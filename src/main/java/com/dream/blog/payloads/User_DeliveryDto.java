package com.dream.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor   
@Getter  
@Setter
public class User_DeliveryDto {
	private int id;
	private String name;
	private String address;
	private String country;
	private String state;
	private String city;
	private String email;//Autofieled in frontend
	private String verifyemailotp;
    private String phoneNo;
	private String verifyphoneotp;
	
	//Message Delivery Details
	
	private String nameOfReciever;
	private String emailOfReciever;
	private String postalAddress;//Text Area field
	private String messageToBeDelivery;
    private String attachments;//This will be file Type
	private String deliveryDate;
}
