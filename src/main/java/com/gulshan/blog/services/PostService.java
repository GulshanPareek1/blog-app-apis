package com.gulshan.blog.services;

import java.util.List;

import com.gulshan.blog.entities.Post;
import com.gulshan.blog.payloads.PostDto;
import com.gulshan.blog.payloads.PostResponse;

public interface PostService {
	
	// here we can include methods to perform actions we want 
	//create post 
	PostDto createPost(PostDto postDto ,Integer userId , Integer categoryId);
	
	// update post 
	PostDto updatePost(PostDto postDto , Integer postId);
	
	// delete post 
	
	void deletePost(Integer postId);
	
	// get all posts 
	
	// adding these parameters for pagination purpoose 
	PostResponse getAllPosts(Integer pageNumber , Integer pageSize ,String sortBy, String sortDir);
	
	// get single post 
	PostDto getPostById(Integer postId);
	
	// get all posts by category 
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	// get all post by user 
	
	List<PostDto> getPostsByUser(Integer userId);
	
	// search posts 
	List<PostDto> searchPosts(String keyword);

}
