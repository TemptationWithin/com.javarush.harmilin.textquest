package org.quest.textquest.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quest.textquest.entity.Answer;
import org.quest.textquest.entity.Question;
import org.quest.textquest.service.QuestService;
import org.quest.textquest.service.QuestionService;

import java.io.IOException;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class QuestServletTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private HttpSession session;
    @Mock private RequestDispatcher dispatcher;
    @Mock private QuestService questService;
    @Mock private QuestionService questionService;

    @InjectMocks QuestServlet questServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/views/quest.jsp")).thenReturn(dispatcher);
        when(questService.getQuestionService()).thenReturn(questionService);
    }

    @Test
    void testDoGet() throws ServletException, IOException {

        Question mockQuestion = new Question(1L, "What is your name?", "imageLink", Collections.emptyList());
        when(request.getParameter("questionId")).thenReturn("1");
        when(questionService.loadQuestionById(1L)).thenReturn(mockQuestion);

        questServlet.doGet(request, response);

        verify(request).setAttribute("question", mockQuestion);
        verify(dispatcher).forward(request, response);
    }

    //Create test objects
    //Set mock objects
    //Use doPost()
    //check adding Cookie
    //check attribute setting
    //check forward method
    @Test
    void testDoPost() throws ServletException, IOException {
        Long questionId = 1L;
        Long answerId = 2L;
        Answer selectedAnswer = new Answer(answerId, "Yes", 2L, "Knight survived");
        Question currentQuestion = new Question(questionId, "What is your name?", "imageLink", Collections.singletonList(selectedAnswer));
        Question nextQuestion = new Question(2L, "Next question?", "imageLink2", Collections.emptyList());

        when(request.getParameter("questionId")).thenReturn(String.valueOf(questionId));
        when(request.getParameter("selectedAnswer")).thenReturn(String.valueOf(answerId));
        when(questionService.loadQuestionById(questionId)).thenReturn(currentQuestion);
        when(questionService.loadQuestionById(2L)).thenReturn(nextQuestion);
        when(request.getParameter("questionId")).thenReturn("1");

        questServlet.doPost(request, response);

        verify(response).addCookie(argThat(cookie ->
                "currentQuestionId".equals(cookie.getName()) &&
                        "1".equals(cookie.getValue())
        ));

        verify(request).setAttribute("reasonIfKnightDies", "Knight survived");
        verify(request).setAttribute("question", nextQuestion);

        verify(dispatcher).forward(request, response);
    }
}