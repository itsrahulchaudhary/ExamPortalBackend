package com.examportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.model.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long>{

}
