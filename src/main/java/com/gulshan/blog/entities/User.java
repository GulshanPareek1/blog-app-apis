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



// so jab bhi hame User ka object banana hoga ham NoArgsConstructor use karenege and getters and setters get and set karne ke liye 
@Entity      // is se ye wali jo user class h vo java entity create ho jayegi 
@Table(name="users") // this will create table named with users 
@NoArgsConstructor      // using constructor by lombok 
@Getter
@Setter
public class User {
	// this is basically for creating users table 	
	
	
	// now for deciding which one will be primary key create ID
	@Id
	// and for auto incremanting this id column we have this generated 
	// and ye karne se alag se user_seq.. or hibernate_seque.. table banegi jis se primary key decide hogi 
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)      // how to use this
	private int id;
	
	//here in case of id  we have used strategy = GenerationType.AUTO that's simply auto increasing the id primary key 
	// but it uses some more queries in hibernate so we can replace this with GenerationType.IDENTITY both are same 
	// and are useful for MYSQL 
	
	@Column(name="user_name" , nullable = false , length = 100)   // for changing any column name and specifying field properties 
	private String name;
	
	private String email;
	
	private String password;
	
	private String about;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
	

	
	
}
