package com.codewithdurgesh.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdurgesh.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	//JpaRepository provide us the functionality to perform database operation on user
	// As we have not define any anotation here still we have autowired this anotation why as Spring create the Instance of class.
	//Answer is - JVM ceate the proxy class at runtime if we autowired the interface that implementation is not available
	// we can check it via reflection api.
	
	
	Optional<User> findByEmail(String email);//we are assuming the email as userName
}
