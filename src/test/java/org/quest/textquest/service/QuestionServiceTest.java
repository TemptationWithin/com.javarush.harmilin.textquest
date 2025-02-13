package org.quest.textquest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quest.textquest.entity.Answer;
import org.quest.textquest.entity.Question;
import org.quest.textquest.repository.QuestionRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //Testing steps:
    //1. Create some answers +
    //2. Set mock for repository for void loadQuestions() +
    //3. Use void loadQuestions() +
    //4. Checking usage void loadQuestions() +
    //5. Checking comparing expected question with question in service;
    @Test
    void testLoadQuestions() throws IOException {
        Answer answer1 = new Answer(1L, "Yes", 2L, null);
        Answer answer2 = new Answer(2L, "No", 1L, "Died");
        Question question = new Question(1L, "What is your name?", "imageLink", Arrays.asList(answer1, answer2));
        List<Question> questions = List.of(question);

        doAnswer(invocation -> {
            questionService.setQuestions(questions);
            return null;
        }).when(questionRepository).loadQuestions();
        questionService.loadQuestions();

        verify(questionRepository, times(1)).loadQuestions();
        assertEquals(1, questionService.getQuestions().size());
        assertEquals("What is your name?", questionService.getQuestions().get(0).getText());
    }

    //Testing steps:
    //1. Prepare some data +
    //2. Set mock for void loadQuestionById() +
    //3. Checking method void loadQuestionById() +
    //4. Checking that void loadQuestionById() returning correct object +
    //5. Checking usage of void loadQuestionById()
    @Test
    void testLoadQuestionById() {
        Long questionId = 1L;
        Question mockQuestion = new Question(questionId, "Whats your name", "imageLink", List.of());
        when(questionRepository.getQuestionById(questionId)).thenReturn(mockQuestion);
        Question result = questionService.loadQuestionById(questionId);

        assertNotNull(result);
        assertEquals(questionId, result.getId());
        assertEquals("Whats your name", result.getText());

        verify(questionRepository, times(1)).getQuestionById(questionId);
    }
}