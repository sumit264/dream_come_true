package com.dream.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.blog.entities.Login;
import com.dream.blog.exceptionhandling.ResourceNotFoundException;
import com.dream.blog.repositories.LoginRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired 
	private LoginRepo loginRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user from database
		
		System.out.println("================1=================== ");

		Login login1 = null;
		try {

			System.out.println("======== =======2====================");
			Login login = this.loginRepo.findByUserName(username).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

			System.out.println("=======login details=======" + login.getId() +"=="+login.getPassword()+"=="+login.getUsername());
			//login.setPassword("$2a$10$EzRGBjjtI1p0n2rGTA2naeDYT00AiWd/hXGVXX9f6ZDHHwTogYvGm");
			
			return login;
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return login1;
	}

}
