package com.javarush.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    private int id;
    private String textUk;
    private String textEn;
    private List<Answer> answers;
    private String gameState;
}
