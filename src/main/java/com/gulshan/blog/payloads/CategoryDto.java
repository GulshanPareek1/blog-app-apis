package com.gulshan.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	@NotBlank
	@Size(min = 4 , message = "Minimum size of Category Title should be 4.")
	private String categoryTitle;
	@NotBlank
	@Size(min = 10 , message = "Minimum no of 10 characters should be in description.")
	private String categoryDescription;
	
}
