package com.gulshan.blog.controllers;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.gulshan.blog.payloads.ApiResponse;
import com.gulshan.blog.payloads.UserDto;
import com.gulshan.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	// ResponseEntity basically here represents the whole http response , status code and body 
	
	// POST - create user 

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		// here @Valid is for using bean validation 
		 UserDto createUserDto = this.userService.createUser(userDto);
		 // any task related to put and post request will be done by service class methods and 
		 
		 return new ResponseEntity<>(createUserDto , HttpStatus.CREATED);
		 
		 // in response entity we are returning the Body (createUserDto ) and the status code 
	}
		
	// PUT - update user (an user with userId )
	// userId here is pathUriVarible
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto , @PathVariable Integer userId)
	{
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		//return new ResponseEntity<>(updatedUser , HttpStatus.OK);
		
		return ResponseEntity.ok(updatedUser);
		
		// both of the return statements are same having values of body as updatedUser and 
		// status code as ok   simple!!
		//
		
	}
	// DELETE  - delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId)
	{
		// when we don't know what to return in that case use ?
		this.userService.deleteUser(userId);
		
		//return new ResponseEntity<>(Map.of("Messege" , " User deleted Successfully") , HttpStatus.OK);
		//return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
	}
	
	// GET - user get 
	// for all user get
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	// for single user get 
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)
	{
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
}
