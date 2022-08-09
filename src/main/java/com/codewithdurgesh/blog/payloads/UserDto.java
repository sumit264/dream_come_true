package com.codewithdurgesh.blog.payloads;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor  // using lombok we can create no argument constructor internal 
@Getter // using lombok we can create getter and setter automatically 
@Setter
public class UserDto {
  
	
    private int id;
	//@NotNull
    @NotEmpty
    @Size(min = 4,message = "User Name must be minimum 4 charactors !!")
	private String name;
	@Email(message = "Your email Address is not valid !!")
	private String email;
	//@NotNull //It check only null
	@NotEmpty //It check both null and blank both
	@Size(min = 3,max = 10, message = "Password must be minimum of 3 Chars and maximum of 10 Chars !!")
	private String password;
	@NotEmpty	
	private String about;
}
