package com.skypro.questionexam.controller;

import com.skypro.questionexam.exeption.LackQuestionException;
import com.skypro.questionexam.model.Question;
import com.skypro.questionexam.service.ExaminerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("exam/get")
public class ExamController{

    private final ExaminerServiceImpl examinerServiceImpl;


    public ExamController(ExaminerServiceImpl examinerServiceImpl) {
        this.examinerServiceImpl = examinerServiceImpl;
    }


    @ExceptionHandler(LackQuestionException.class)
    public ResponseEntity <String> handleException(){
        return ResponseEntity.badRequest().body("Недостаточно вопросов");
    }


    @GetMapping("/{amount}")
    public Collection<Question> getQuestions (@PathVariable("amount") int amount){
        return examinerServiceImpl.getQuestions(amount);
    }
}
