package com.javarush.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    private final GameService gameService = new GameService();

    @Test
    void isValidNickname_ShouldReturnTrue_ForValidNickname() {
        assertTrue(gameService.isValidNickname("Vitaly"));
        assertTrue(gameService.isValidNickname("User123"));
    }
    @Test
    void isValidNickname_ShouldReturnFalse_ForBadInput() {
        assertFalse(gameService.isValidNickname(""));
        assertFalse(gameService.isValidNickname(" "));
        assertFalse(gameService.isValidNickname(null));
    }

    @Test
    void updateGameCounter_LogicCheck() {
        assertEquals(1, gameService.updateGameCounter(null));
        assertEquals(2, gameService.updateGameCounter(1));
    }

    @Test
    void getStartQuestionId_ShouldReturn1() {
        assertEquals(1, gameService.getStartQuestionId());
    }

}