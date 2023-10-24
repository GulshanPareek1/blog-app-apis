package com.gulshan.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gulshan.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
