package com.dream.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor   
@Getter  
@Setter
public class LoginDto {

    private int id;
    @NotEmpty
    @Size(min = 4,message = "User Name must be minimum 4 charactors !!")
	private String userName;
	@NotEmpty //It check both null and blank both
	@Size(min = 6,max = 10, message = "Password must be minimum of 6 Chars and maximum of 10 Chars !!")
	private String password;
}
