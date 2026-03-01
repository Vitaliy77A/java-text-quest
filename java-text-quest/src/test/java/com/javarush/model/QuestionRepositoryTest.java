package com.javarush.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionRepositoryTest {

    private final QuestionRepository questionRepository = new QuestionRepository();

    @Test
    void checkStartQuestionExists() {
        Question question = questionRepository.getQuestionById(1);
        assertNotNull(question, "Question with id 1 should exist");
        assertNotNull(question.getAnswers(), "Question with id 1 should have answers");
    }

    @Test
    void checkNonExistentId() {
        assertNull(questionRepository.getQuestionById(9999), "A non-existent question must be null");
    }

}