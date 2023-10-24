package com.gulshan.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gulshan.blog.entities.User;
import com.gulshan.blog.exceptions.ResourceNotFoundException;
import com.gulshan.blog.payloads.UserDto;
import com.gulshan.blog.repositories.UserRepo;
import com.gulshan.blog.services.UserService;


// anotation for service class and tells spring that should be managed by the Spring Container 
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		
		return this.userToDto(savedUser);
		
		// what basically here is happening is we can't directly expose our User entity through service class to any user
		// so here we use UserDto for taking as input and returning 
		//  and for repository / database related stuff we do it using User entity bcz we know 1st parameter in UserRepo was User Entity 
		// so we can only work with User entity in UserRepo 
		
		// and created methods for changing from dto to user and vice versa 
		// from next time onwards we'll be using Model Mapper for this task(changing)
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User" ," id " , userId));
		// this was for finding that user from database and if its not found then we'll get a custom exception made by us ..
		// and one more thing here we won't use .getById bcz that doesn't have this property of .orElseThrow exception , go and try this 
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User" ,"id" , userId));
		UserDto userDto = this.userToDto(user);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users  = this.userRepo.findAll();
		
		// how to change list User type to List userDto type??
		// we'll use stream api from lambda for changing from users list to userDtos list 
		
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		// keep in mind this how to change a list type to another this is standerd
		// find it over google 
		
		return userDtos ;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User" , "id" , userId));
		this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto)
	{
		// here we need to return User so make an object of User 
		// now do it with help of modelMapper
		User user = this.modelMapper.map(userDto, User.class);
		// these set and get i'm doing with help of getters and setters 
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		// here what i did all there was getting the value from userDto object and setting it into user object 
		return user;
		
	}
	
	public UserDto userToDto(User user)
	{
		// use modelMapper insted doing manually 
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
	}

}
