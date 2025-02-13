package org.quest.textquest.service;

import lombok.Getter;
import lombok.Setter;
import org.quest.textquest.entity.Question;
import org.quest.textquest.repository.QuestionRepository;

import java.io.IOException;
import java.util.List;

@Getter
@Setter
public class QuestionService {
    private QuestionRepository questionRepository;
    private List<Question> questions;

    public QuestionService() {
        questionRepository = new QuestionRepository();
    }

    public void loadQuestions() {
        try {
            questionRepository.loadQuestions();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Question loadQuestionById(Long id) {
        return questionRepository.getQuestionById(id);
    }
}
