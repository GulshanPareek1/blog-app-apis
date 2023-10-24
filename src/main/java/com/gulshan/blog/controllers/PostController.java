package com.gulshan.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gulshan.blog.config.AppConstants;
import com.gulshan.blog.payloads.ApiResponse;
import com.gulshan.blog.payloads.PostDto;
import com.gulshan.blog.payloads.PostResponse;
import com.gulshan.blog.services.PostService;



@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	private PostResponse allPosts;
	
	
	//create post
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto ,
			@PathVariable Integer userId ,
			@PathVariable Integer categoryId)
	{
		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createdPost , HttpStatus.CREATED);
	}
	
	// get post by category
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
	{
		// implement pagination here .. Your Task !!!
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
		return ResponseEntity.ok(posts);
	}
	
	// get post by user 
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId)
	{
		// here also implement pagination 
		List<PostDto> posts = this.postService.getPostsByUser(userId);
		return ResponseEntity.ok(posts);
	}
	
	//get post by id
	
	@GetMapping("/posts/{postId}")
	public	ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		PostDto postDto = this.postService.getPostById(postId);
		return ResponseEntity.ok(postDto);
	}
	
	//get all posts
	// here we have use pagination inn method implementation already so 
	// one new annotation @RequestParam()
	// it's used to extract value from the query parameters of url 
	// ex - http://localhost:9090/api/posts?pageNumber=0&pageSize=5      // here annotation is  useful for extracting pageNumber and pageSize 
	// and we can find that ? means query in url
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber" , defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam(value = "pageSize" , defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy" , defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
			@RequestParam(value = "sortDir" , defaultValue = AppConstants.SORT_DIR , required = false)String sortDir)
	{
		 PostResponse postResponse = this.postService.getAllPosts(pageNumber,pageSize,sortBy ,sortDir);
		
		//return new ResponseEntity<List<PostDto>>(posts , HttpStatus.OK);
		return ResponseEntity.ok(postResponse);
	}
	
	// delete post 
	// controller me ham void return nahi karte atleast some response and for that we made custom class
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post has been successfully deleted.." , true) , HttpStatus.OK);
	}
	
	//update post 
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,
			@PathVariable Integer postId)
	{
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		
		return ResponseEntity.ok(updatePost);
	}
	
	// search for title 
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords)
	{
		List<PostDto> posts = this.postService.searchPosts(keywords);
		
		return ResponseEntity.ok(posts);
	}
}
