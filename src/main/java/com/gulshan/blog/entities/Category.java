package com.gulshan.blog.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  Integer categoryId;
	
	@Column(name = "title" ,length = 100 , nullable = false)
	private String categoryTitle;
	
	@Column(name = "description")
	private String categoryDescription;
	
	// cascade mean jab parent kuch operation kare to child me apne aap ho jaye 
	// we can add here some more like EAGER , 
	
	@OneToMany(mappedBy = "category" , cascade = CascadeType.ALL )
	private List<Post> posts = new ArrayList<>();
}
