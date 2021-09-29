package com.examportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.model.exam.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
