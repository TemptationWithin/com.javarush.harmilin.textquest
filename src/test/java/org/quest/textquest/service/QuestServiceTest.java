package org.quest.textquest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class QuestServiceTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuestService questService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //Testing steps:
    //1. Use void testStartQuest() +
    //2. Checking usage void loadQuestions() +
    @Test
    void testStartQuest() {
        questService.startQuest();

        verify(questionService, times(1)).loadQuestions();
    }
}
