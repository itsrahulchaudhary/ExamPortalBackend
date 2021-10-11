package com.examportal.service;

import java.util.List;
import java.util.Set;

import com.examportal.model.exam.Category;
import com.examportal.model.exam.Quiz;

public interface QuizService {
	
	public Quiz addQuiz(Quiz quiz);
	
	public Quiz updateQuiz(Quiz quiz);
	
	public Set<Quiz> getQuizzes();
	
	public Quiz getQuiz(Long quizId);
	
	public void deleteQuiz(Long quiz);

	public List<Quiz> getQuizzesofCategory(Category category);

}
