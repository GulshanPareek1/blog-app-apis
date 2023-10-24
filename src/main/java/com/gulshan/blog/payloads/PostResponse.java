package com.gulshan.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

	// this we're desiging a specific class for our get all posts result including pagination 
	// so we need all these fields 
	// we're setting these in implementation class (business logic)
	private List<PostDto> content;
	
	private Integer pageNumber;
	
	private Integer pageSize;
	
	private long totalElements;
	
	private Integer totalPages;
	
	private Boolean lastPage;
}
