package com.gulshan.blog.services;

import java.util.List;

import com.gulshan.blog.payloads.UserDto;

public interface UserService {

	//interface pe hame public , private lagane ki jarurat nahi h  
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user , Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	
}
