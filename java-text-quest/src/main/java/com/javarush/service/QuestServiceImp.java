package com.javarush.service;

import com.javarush.model.Question;
import com.javarush.model.QuestionRepository;

public class QuestServiceImp implements QuestService  {
    private final QuestionRepository questionRepository = new QuestionRepository();

    @Override
    public Question getQuestionById(int id) {
        return questionRepository.getQuestionById(id);
    }

}
