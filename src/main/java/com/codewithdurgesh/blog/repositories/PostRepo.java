package com.codewithdurgesh.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	
	List<Post> findByUser(User user);
	

	List<Post> findByCategory(Category category);
	

	List<Post> findByTitleContaining(String title);
	
	
	/*
	 * @Query("SELECT  FROM posts p WHERE p.post_title LIKE : key") List<Post>
	 * searchBtTitle(@Param ("key") String key);
	 */

}
