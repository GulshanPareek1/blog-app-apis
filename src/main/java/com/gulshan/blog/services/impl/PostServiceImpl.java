package com.gulshan.blog.services.impl;

import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.OffsetMapping.Sort;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//import org.springframework.data.util.Streamable;
import org.springframework.data.domain.*;
import com.gulshan.blog.entities.Category;
import com.gulshan.blog.entities.Post;
import com.gulshan.blog.entities.User;
import com.gulshan.blog.exceptions.ResourceNotFoundException;
import com.gulshan.blog.payloads.PostDto;
import com.gulshan.blog.payloads.PostResponse;
import com.gulshan.blog.repositories.CategoryRepo;
import com.gulshan.blog.repositories.PostRepo;
import com.gulshan.blog.repositories.UserRepo;
import com.gulshan.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	// database operations perform karne ke liye chaiye hoga postRepo 
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	// these two we're doing for fetching user and category for post 
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private UserRepo userRepo;
	private List<Post> findByCategory;
	
	@Override
	public PostDto createPost(PostDto postDto , Integer userId , Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		// ab hamne jo postDto ko post me convert kiya h to hamare pass 2 hi field 
		// title and content aaye h baki ke hame set karne honge ok!!
		
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		this.postRepo.delete(post);		

	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber , Integer pageSize,String sortBy , String sortDir) {
		// do it yourself
		// add pagination in this method 
		org.springframework.data.domain.Sort sort = (sortDir.equalsIgnoreCase("asc"))? org.springframework.data.domain.Sort.by(sortBy).ascending():org.springframework.data.domain.Sort.by(sortBy).descending();
				//by(sortBy).ascending():Sort.by(sortBy).descending();

		
		// Pageable ka object create karo and us se Pagerequest.of() method 
		//Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Pageable p = PageRequest.of(pageNumber,pageSize, sort);
		// ye hame page of posts provide karega 
		Page<Post> pagepost = this.postRepo.findAll(p);
		// is pagePost ke pass sari information hogi sare methods 
		// yaha se hame getcontent method se list of posts mil jayegi 
		List<Post> allPosts = pagepost.getContent();
		
		// yaha hamne apne general way se thoda hi change kiya h you can notice that 
		
		List<PostDto> postDtos = allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagepost.getNumber());
		postResponse.setPageSize(pagepost.getSize());
		postResponse.setTotalElements(pagepost.getTotalElements());
		postResponse.setLastPage(pagepost.isLast());
		postResponse.setTotalPages(pagepost.getTotalPages());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// do it yourself
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		List<Post> posts = this.postRepo.findByCategory(cat);
		
		// now convert this list of type Post into list type of PostDto
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		
		// first find the user 
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		
		// we have list of posts but in Post type so convert it into PostDto type
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
			List<Post> posts = this.postRepo.findByTitleContaining(keyword);
			// here change only method name and then use this like searchByTitle("%"+keywords+"%")  that's all
			List<PostDto> postDtos  = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
