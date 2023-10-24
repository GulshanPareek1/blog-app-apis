package com.gulshan.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
 
	// decide what we wanna return here 
	private String messege;
	private boolean success;
}
