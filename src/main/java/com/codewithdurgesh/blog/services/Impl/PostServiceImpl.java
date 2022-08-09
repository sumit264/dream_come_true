package com.codewithdurgesh.blog.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;
import com.codewithdurgesh.blog.repositories.CategoryRepo;
import com.codewithdurgesh.blog.repositories.PostRepo;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.PostService;
import com.codewithdurgesh.blogexceptions.ResourceNotFoundException;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Not Found with Id", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Not Found with Id", userId));

		Post post = this.modelMapper.map(postDto, Post.class);

		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setCategory(category);
		post.setUser(user);

		Post newPost = this.postRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	@CachePut(cacheNames = "posts", key = "#postId")
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		System.out.println("===========Update Post===========");
		
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post-Id", postId));
 
		post.setTitle(postDto.getTitle());
	//	post.setAddDate(postDto.getAddDate());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
			
		
		Post updatedPost=this.postRepo.save(post);
		
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	@CacheEvict(cacheNames = "posts",key = "#postId")
	public void deletePost(Integer postId) {
		
		System.out.println("==================Hitting Count for delete Post=======================");
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post-Id", postId));

		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		//Sorting in Ascending or decending order
		
		
		  Sort sort=null;
		  if(sortDir.equalsIgnoreCase("asc")) {
		  
		  sort=Sort.by(sortBy).ascending();
		  }else {
		  
		  
		  sort=Sort.by(sortBy).descending(); 
		  }
		 

		//used of terminary operator for if - else
		//Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		
		//String sortBy is adding for Sorting purpose
		//Implementation of Pagination video -23
		
		//int pageSize=5;

		//int pageNumber=1;
		
		//create the object of Pageable
		//Pageable p=PageRequest.of(pageNumber, pageSize);//for Pagination Only
		//Pageable p=PageRequest.of(pageNumber, pageSize,Sort.by(sortBy)); //for Sorting along with pagination
		Pageable p=PageRequest.of(pageNumber, pageSize,sort); //for Sorting along with pagination in descending order

		Page<Post> pagePost=this.postRepo.findAll(p);
		List<Post> allPosts=	pagePost.getContent();  //method for Pagination Record fetching
				
		//List<Post> allPosts=	this.postRepo.findAll(); //method for All Record fetching
		
		List<PostDto> postDtos=allPosts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLagtPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
    @Cacheable(cacheNames = "posts",key = "#postId")
	public PostDto getPostById(Integer postId) {
		System.out.println("=================hitting count=================");
		Post post=this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		System.out.println("===========" + category.getCategoryDescription() + "=========="+ category.getCategoryTitle() + "=====" + category.getCategoryId());
		List<Post> posts = this.postRepo.findByCategory(category);

		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts =this.postRepo.findByUser(user);
		List<PostDto> postDtos=null; 

		
		  /*for(Post post:posts) {
		  
		  postDtos.add(this.modelMapper.map(post, PostDto.class)); //Old Approch
		  
		  }
		  
		  return dtoList;*/
		 
		
		postDtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		
		//List<Post> posts = this.postRepo.searchBtTitle("%"+keyword+"%");
		
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
