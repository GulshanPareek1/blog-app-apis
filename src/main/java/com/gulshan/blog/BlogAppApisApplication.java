package com.gulshan.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
	
	// we can use ModelMapper class here also bcz @SpringBootApllication annotation also provides the 
	// feature  of @Configuration annotation 
	//@Configuraton tells spring for creating beans and configure beans 
	//@Bean make that class supplier for object whenever need in our application called by @Autowired
	
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

}
