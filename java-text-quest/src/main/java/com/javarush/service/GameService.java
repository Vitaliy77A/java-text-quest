package com.javarush.service;

public class GameService  {

    public boolean isValidNickname(String nickname) {
        return nickname != null && !nickname.trim().isEmpty();
    }

    public int updateGameCounter(Integer currentCount) {
        return (currentCount == null) ? 1 : currentCount + 1;
    }

    public int getStartQuestionId() {
        return 1;
    }
}
