package com.gulshan.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gulshan.blog.entities.User;

public interface UserRepo extends JpaRepository<User , Integer> {
	// here we need repository for input and output of User data  and this will provide us all functionalities for database operations 
	//JpaRepository me 1st parameter vo h jis entity ke sath hame kaam karna h and second vo kis type ki h 
	

}
