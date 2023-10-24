package com.gulshan.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gulshan.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
 
}
