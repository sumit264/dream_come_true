package com.dream.blog.jsp;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dream.blog.entities.Login;
import com.dream.blog.services.SignUpService;

@Controller
public class HomeController {
	
	@Autowired 
	 private HttpSession httpSession;
	@Autowired
	SignUpService signUpService;

	@RequestMapping(value = "/login_success_handler", method = RequestMethod.POST)
	public String index() {
		System.out.println("=======login_success_handler==========");
		return "/UserInfo";
	}

	@RequestMapping(value = "/login_failure_handler", method = RequestMethod.POST)
	public String admin() {
		System.out.println("=======login_failure_handler==========");
		return "login";
	}
	
	@RequestMapping(value = "/UserInfo", method = RequestMethod.GET)
	public String UserInfo() {
		
		Login login=new Login();
		System.out.println("=======UserInfo==========");
		
		login=(Login) httpSession.getAttribute("logindetails");
		
		System.out.println("Login Details :: "+login.getUsername()+"  "+login.getPassword());
		return "UserInfo";
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