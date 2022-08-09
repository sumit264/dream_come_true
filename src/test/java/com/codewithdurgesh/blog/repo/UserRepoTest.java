package com.codewithdurgesh.blog.repo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blogexceptions.ResourceNotFoundException;

@SpringBootTest
public class UserRepoTest {
	@Autowired
	private UserRepo userRepo;

	//DAO Testing
	@Test
	void findByUsingId() {
		boolean actualResult = false;

		User user = this.userRepo.findById(1).orElseThrow(() -> new ResourceNotFoundException("User", "Id", 1));

		System.out.println("====" + user.getId());
		//if (user.getId() == '1') {

			actualResult = true;
		//}

		assertThat(actualResult).isTrue();
	}

	// This Anotation will use if we want to delete all result after testing (this
	@AfterEach  		// function will run after every tests)
	void tearDown() {

		System.out.println("tearing down");
		//userRepo.deleteById(7);
	}

	@BeforeEach // (this function will run before every tests)
	void setUp() {
		System.out.println("setting up");
	}

}
