package com.javarush.service;

import com.javarush.model.Question;
import com.javarush.model.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class QuestServiceImpTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestServiceImp questService;

    @Test
    void getQuestionById_ShouldReturnQuestion_WhenExists() {
        Question mockQuestion = Question.builder()
                .id(1)
                .text("Question 1")
                .build();

        when(questionRepository.getQuestionById(1)).thenReturn(mockQuestion);
        Question result = questService.getQuestionById(1);

        assertNotNull(result);
        assertEquals("Test question to check the service", result.getText());

        verify(questionRepository, times(1)).getQuestionById(1);
    }

}