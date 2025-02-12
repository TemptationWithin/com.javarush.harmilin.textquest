package org.quest.textquest.service;

import lombok.Getter;
import lombok.Setter;
import org.quest.textquest.entity.Question;
import org.quest.textquest.repository.QuestionRepository;

import java.io.IOException;

@Getter
@Setter
public class QuestionService {
    private QuestionRepository questionRepository;

    public QuestionService(){
        questionRepository = new QuestionRepository();
    }

    public void loadQuestions(){
        try {
            questionRepository.loadQuestions();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Question loadQuestionById(Long id){
        return questionRepository.getQuestionById(id);
    }
}
