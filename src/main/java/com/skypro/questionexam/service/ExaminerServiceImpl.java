package com.skypro.questionexam.service;

import com.skypro.questionexam.exeption.LackQuestionException;
import com.skypro.questionexam.model.Question;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService{

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    private final QuestionService questionService;

    @Override
    public Collection<Question> getQuestions(int amount) {
       Set<Question> questions = new HashSet<>();
       if (questionService.getAll().size()<amount){
           throw new LackQuestionException();
       }
       while (questions.size()<amount)
           questions.add(questionService.getRandomQuestion());
        return questions;
    }
}
