package com.dream.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dream.blog.security.CustomUserDetailService;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	public static final String[] PUBLIC_URLS= {
			"/api/login/",
			"/signup/",
			"/api/login/userLogin",
			"/v3/api-docs",
			"/v2/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"
			};
	
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
   System.out.println("==========3========");
	
	  http. csrf().disable() .authorizeHttpRequests()
	  .antMatchers(PUBLIC_URLS).permitAll()
	  .antMatchers(HttpMethod.GET).permitAll() .anyRequest() .authenticated()
	  .and() .formLogin() .loginPage("/login") .and() .httpBasic();
	 
	/*
	 * http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/welcome")
	 * .hasAnyRole("USER", "ADMIN") .antMatchers("/getEmployees").hasAnyRole("USER",
	 * "ADMIN").antMatchers("/addNewEmployee")
	 * .hasAnyRole("ADMIN").anyRequest().authenticated()
	 * .and().formLogin().loginPage("/login").permitAll()
	 * .and().logout().permitAll();
	 * 
	 * http.csrf().disable();
	 */
		
		http.formLogin()
		    .successForwardUrl("/login_success_handler");
		
		
		http.formLogin()
		    .failureForwardUrl("/login_failure_handler");
		
		
		
		
		 System.out.println("==========4========");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 System.out.println("==========5========  ");
		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());;
		 System.out.println("==========6========");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
		
	}
	
	

	
	
	
}
