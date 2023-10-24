 package com.gulshan.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gulshan.blog.repositories.UserRepo;

@SpringBootTest
class BlogAppApisApplicationTests {

	@Autowired
	private UserRepo userRepo;
	// we've not created any implementatin class for UserRepo still its injecting its object how??
	// lets check 
	
	@Test
	void contextLoads() {
	}
	
	// @Test annotation tells us that this method should be used as testcase or this method contains a test
	//which will be executed during automated testing    Cool!!
	
	
	// this method we've created for checking about UserRepo implementation class and its object 
	@Test
	public void repoTest()
	{
		// we are doing these with help of reflection api 
		String className = this.userRepo.getClass().getName();
		String packName = this.userRepo.getClass().getPackageName();
		System.out.println(className);
		System.out.println(packName);
	}

}
