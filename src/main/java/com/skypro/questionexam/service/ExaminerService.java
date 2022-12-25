package com.skypro.questionexam.service;
import com.skypro.questionexam.model.Question;
import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
    }

