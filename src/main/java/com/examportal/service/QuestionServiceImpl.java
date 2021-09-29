package com.examportal.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.model.exam.Question;
import com.examportal.model.exam.Quiz;
import com.examportal.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	QuestionRepository questionRepository;

	@Override
	public Question addQuestion(Question question) {
		return this.questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		return this.questionRepository.save(question);
	}

	@Override
	public Set<Question> getQuestions() {
		return new LinkedHashSet<>(this.questionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long questionId) {
		return this.questionRepository.findById(questionId).get();
	}

	@Override
	public Set<Question> getQuestionOfQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.questionRepository.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long questionId) {
		Question question = new Question();
		question.setQuesId(questionId);
		this.questionRepository.save(question);
		
	}

}
