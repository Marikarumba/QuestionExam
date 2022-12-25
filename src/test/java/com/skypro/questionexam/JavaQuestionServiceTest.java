package com.skypro.questionexam;

import com.skypro.questionexam.exeption.LackQuestionException;
import com.skypro.questionexam.model.Question;
import com.skypro.questionexam.service.JavaQuestionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JavaQuestionServiceTest {

    private JavaQuestionService javaQuestionService;
    private List <Question> questions = List.of(
            new Question("q1", "a1"),
            new Question("q2", "a2"),
            new Question("q3", "a3"),
            new Question("q4", "a4"),
            new Question("q5", "a5"));

    @BeforeEach
    void setUp(){
        this.javaQuestionService= new JavaQuestionService();
        questions.forEach(javaQuestionService::add);
    }

    @Test
    void addQuestion(){
        Question question = new Question("q6", "a6");
        Question actual = javaQuestionService.add(question);
        Assertions.assertThat(actual).isEqualTo(question);
        Assertions.assertThat(javaQuestionService.getAll()).hasSize(6).contains(question);
    }
    @Test
    void removeQuestion(){
        Question actual = javaQuestionService.remove(questions.get(0));
        Assertions.assertThat(javaQuestionService.getAll()).hasSize(4).doesNotContain(questions.get(0));
    }
    @Test
    void getRandomQuestion(){
        Question actual = javaQuestionService.getRandomQuestion();
        Assertions.assertThat(actual).isIn(questions);
    }
    @Test
    void getAll(){
        Assertions.assertThat(javaQuestionService.getAll()).hasSize(5).containsAll(questions);
    }
    @Test
    void exceptionEmptyList(){
        javaQuestionService = new JavaQuestionService();
        Assertions.assertThatThrownBy(()->javaQuestionService.getRandomQuestion())
                .isInstanceOf(LackQuestionException.class);
    }
}
