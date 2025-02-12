package org.quest.textquest.service;

import lombok.Getter;

@Getter
public class QuestService {
    private QuestionService questionService;

    public QuestService(){
        questionService = new QuestionService();
    }

    public void startQuest(){
        questionService.loadQuestions();
    }
}
