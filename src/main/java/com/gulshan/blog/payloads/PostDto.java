package com.gulshan.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.gulshan.blog.entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// data get and set tabhi hoga jab ham getter and setter banayenge to kabhi nahi bhule inko nahi error aayega data rakhte time in postman 

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	// here what fields are available those we'll be seeing in postman output
	
	private int postId;

	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	// yaha hame User and Category lene ke bajay CategoryDto and UserDto lena h bcz phir recursion nahi chalega
	// bcz hamare pass CategoryDto and UserDto ke pass koi posts name se field nahi h 
	
	private UserDto user;
	
	private CategoryDto category;
	
	private Set<CommentDto> comments = new HashSet<>();
	
	// rest of the fields will consider direct in service class 
	// id will automatic generate 
	// category_id and user will include in url 
}
