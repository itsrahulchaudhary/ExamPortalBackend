package com.examportal.controller;

import java.util.List;

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

import com.examportal.model.exam.Category;
import com.examportal.model.exam.Quiz;
import com.examportal.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

	@Autowired
	QuizService quizService;

	// add Quiz
	@PostMapping("/")
	public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz) {
		Quiz addQuiz = this.quizService.addQuiz(quiz);
		return ResponseEntity.ok(addQuiz);

	}

	// get Quiz
	@GetMapping("/{quizId}")
	public Quiz getQuiz(@PathVariable("quizId") Long quizId) {
		return this.quizService.getQuiz(quizId);

	}
	
	// get All Quiz
	@GetMapping("/")
	public ResponseEntity<?> getQuizzes(){
		return ResponseEntity.ok(this.quizService.getQuizzes());
	}
	
	// update Quiz
	@PutMapping("/")
	public ResponseEntity<Quiz>  updateQuiz(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
		
	}
	
	// delete Quiz
	@DeleteMapping("/{quizId}")
	public void deleteCategory(@PathVariable("quizId") Long quizId) {
		this.quizService.deleteQuiz(quizId);
	}
	
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizzesofCategory(@PathVariable("cid") Long cid){
		Category category = new Category();
		category.setCid(cid);
		return this.quizService.getQuizzesofCategory(category);
	}

}
