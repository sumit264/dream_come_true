package com.dream.blog.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dream.blog.payloads.SignUpDto;
import com.dream.blog.services.SignUpService;

@Controller
public class HomeController {
	

	@Autowired
	SignUpService signUpService;

	@RequestMapping(value = "/login_success_handler", method = RequestMethod.POST)
	public String index() {
		System.out.println("=======login_success_handler==========");
		return "login";
	}

	@RequestMapping(value = "/login_failure_handler", method = RequestMethod.POST)
	public String admin() {
		System.out.println("=======login_failure_handler==========");
		return "login";
	}
	

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
		System.out.println("=======signup==========");
		return "signup";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout() {
		System.out.println("=======logout==========");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		System.out.println("====Login Page===");
		/*
		 * if (error != null) model.addAttribute("errorMsg",
		 * "Your username and password are  invalid.");
		 * 
		 * if (logout != null) model.addAttribute("msg",
		 * "You have been logged out successfully.");
		 */

		return "login";
	}
}