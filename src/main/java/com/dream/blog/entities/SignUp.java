package com.dream.blog.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "signUp_Tb")
@NoArgsConstructor
@Getter
@Setter
public class SignUp {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int id;
	private String email;
	private String reEnter_email;
	private String phoneNo;
	@Column(name = "userName", nullable = false, length = 100) 
	private String userName;
	private String password;
	private String gender;
	private String dob;
	//private String otp;
}
