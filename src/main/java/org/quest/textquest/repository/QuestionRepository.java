package org.quest.textquest.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.quest.textquest.entity.Question;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionRepository {
    private Map<Long, Question> questions = new HashMap<>();

    public void loadQuestions() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("questions.json");
        if (inputStream == null) {
            throw new FileNotFoundException("File not found in resources");
        }
        ObjectMapper mapper = new ObjectMapper();
        List<Question> loadedQuestions = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(List.class, Question.class));
        for (Question question : loadedQuestions) {
            questions.put(question.getId(), question);
        }
    }

    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions.values());
    }

    public Question getQuestionById(Long id) {
        return questions.get(id);
    }
}
