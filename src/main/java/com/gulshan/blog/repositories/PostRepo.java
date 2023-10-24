package com.gulshan.blog.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gulshan.blog.entities.Category;
import com.gulshan.blog.entities.Post;
import com.gulshan.blog.entities.User;
import com.gulshan.blog.payloads.PostDto;

public interface PostRepo extends JpaRepository<Post, Integer>{

	// these are custom methods in Jpa repository we can find them over google 
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	// this is for searching for title 
	// search through keyword if that's part of title
	// and yaha field ka name Title h ham kuch or bhi use kar sakte h to be searched
	List<Post> findByTitleContaining(String title);
	
	
	// we can have alternate way to write this searching method using query 
	//@Query("select p from Post p where p.title like :key")
	//List<Post> searchByTitle(@Param("key") String title);
	
	//and like this we can write our own specific queries whatever we dant we want to fetch from entity 
}

