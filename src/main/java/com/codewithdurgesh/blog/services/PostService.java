package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;

public interface PostService {

	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	

	PostDto updatePost(PostDto postDto,Integer postId);
	

	void deletePost(Integer postId);
	

	//List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);//old
	//PostResponse getAllPost(Integer pageNumber,Integer pageSize);//old second for pagination only
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);//for Sorting
	PostDto getPostById(Integer postId);
	

	List<PostDto> getPostByCategory(Integer categoryId);
	

	List<PostDto> getPostByUser(Integer userId);
	

	List<PostDto> searchPosts(String keyword);
}
