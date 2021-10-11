package com.examportal.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.model.exam.Question;
import com.examportal.model.exam.Quiz;
import com.examportal.repository.QuestionRepository;
import com.examportal.service.QuestionService;
import com.examportal.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
	
	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	QuestionService questionService;

	@Autowired
	QuizService quizService;

	// add Question
	@PostMapping("/")
	public ResponseEntity<?> addQuestion(@RequestBody Question question) {
		Question addQuestion = this.questionService.addQuestion(question);
		return ResponseEntity.ok(addQuestion);

	}

	// get Question
	@GetMapping("/{questionId}")
	public Question getQuestion(@PathVariable("questionId") Long questionId) {
		return this.questionService.getQuestion(questionId);

	}

	// get All Question of any quizId
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("quizId") Long quizId) {
		Quiz quiz = this.quizService.getQuiz(quizId);
		Set<Question> questions = quiz.getQuestions();
		List list = new ArrayList(questions);
		if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
		}
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}

	// get All Question of any quizId
		@GetMapping("/quiz/all/{quizId}")
		public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("quizId") Long quizId) {
			Quiz quiz = new Quiz();
			quiz.setQid(quizId);
			Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
			return ResponseEntity.ok(questionOfQuiz);
		}
		
	// get All Question
	@GetMapping("/")
	public ResponseEntity<?> getQuestions() {
		return ResponseEntity.ok(this.questionService.getQuestions());
	}

	// update Question
	@PutMapping("/")
	public Question updateQuestion(@RequestBody Question question) {
		return this.questionService.updateQuestion(question);

	}

	// delete Question
	@DeleteMapping("/{questionId}")
	public void deleteCategory(@PathVariable("questionId") Long questionId) {
		this.questionService.deleteQuestion(questionId);
		//this.questionRepository.deleteById(questionId);

	}

}
