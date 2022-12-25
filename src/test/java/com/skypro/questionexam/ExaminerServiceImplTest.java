package com.skypro.questionexam;

import com.skypro.questionexam.model.Question;
import com.skypro.questionexam.service.ExaminerServiceImpl;
import com.skypro.questionexam.service.JavaQuestionService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.skypro.questionexam.exeption.LackQuestionException;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    JavaQuestionService javaQuestionService;
    @InjectMocks
    ExaminerServiceImpl examinerService;
    @Test
    void NoLackOFQuestions(){

        List <Question> q = List.of(
                new Question("q1", "a1"),
                new Question("q2", "a2"),
                new Question("q3", "a3")
        );
        when(javaQuestionService.getAll()).thenReturn(q);
        when(javaQuestionService.getRandomQuestion()).thenReturn(
                q.get(0),
                q.get(1)
        );
        assertThat(examinerService.getQuestions(2)).containsOnly(q.get(0),q.get(1));
    }
    @Test
    void LackOFQuestions(){
        when(javaQuestionService.getAll()).thenReturn(Collections.emptyList());
        assertThatThrownBy(()-> examinerService.getQuestions(10)).isInstanceOf(LackQuestionException.class);
    }


}
