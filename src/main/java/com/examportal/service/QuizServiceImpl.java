package com.examportal.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.model.exam.Quiz;
import com.examportal.repository.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	QuizRepository quizRepository;

	@Override
	public Quiz addQuiz(Quiz quiz) {
		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		return this.quizRepository.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizzes() {
		return new LinkedHashSet<>(this.quizRepository.findAll());
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		return this.quizRepository.findById(quizId).get();
	}

	@Override
	public void deleteQuiz(Long quiz) {
		Quiz quizz = new Quiz();
		quizz.setQid(quiz);
		this.quizRepository.delete(quizz);
	}

}
