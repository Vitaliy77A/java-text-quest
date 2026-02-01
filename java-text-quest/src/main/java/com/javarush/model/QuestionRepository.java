package com.javarush.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.exception.InitGameException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionRepository {
    private final Map<Integer, Question> questions = new HashMap<>();

    public QuestionRepository() {
        loadQuestions();
    }

    private void loadQuestions() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
           InputStream inputStream =  getClass().getClassLoader().getResourceAsStream("questions.json");
           if (inputStream == null) {
               throw new InitGameException("questions.json not found");
           }
           List<Question> questionsList = objectMapper.readValue(inputStream, new TypeReference<List<Question>>() {});
           for (Question question : questionsList) {
               questions.put(question.getId(), question);
           }
        } catch (IOException e) {
            throw new InitGameException("Failed to load questions", e);
        }

    }
    public Question getQuestionById(int id) {
        return questions.get(id);
    }
}
