package com.gulshan.blog.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	// we are taking data from here while creating / updating user so need to validate here 
	
	private int id;
	
	@NotEmpty
	@Size(min=3 , message = "UserName must have atleast three characters")
	private String name ;
	
//	@Email(message = "Email address is not valid")   i'm not using this normal @Email annotation here 
	
	@Pattern(regexp = "^.+@.+\\.(com|in)$", message = "Invalid email address format. Please use ___@___.com or ___@___.in.")
	private String email;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$", message = "Password must have at least 4 characters, one uppercase letter, one lowercase letter, one digit, and one special character.")
	private String password;
	
	@NotNull
	private String about;
	
}
