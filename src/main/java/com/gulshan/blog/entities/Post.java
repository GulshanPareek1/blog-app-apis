package com.gulshan.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	
	@Column(name = "post_title" , length = 100 , nullable = false)
	private String title;
	
	@Column(length = 1000)
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	// these two are for mapping the entities bcz post has link with which category and which user 
	
	
	 @JoinColumn(name = "category_id")
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
	
	// 
	@OneToMany(mappedBy = "post" , cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();
		
	
	
}
