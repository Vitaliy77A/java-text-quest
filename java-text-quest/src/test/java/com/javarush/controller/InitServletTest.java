package com.javarush.controller;

import com.javarush.service.GameService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class InitServletTest {
    private final GameService gameService = new GameService();
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private final InitServlet initServlet = new InitServlet();

    @Test
    void doPost_ShouldRedirectWithError_WhenNicknameIsEmpty() throws ServletException, IOException {

        when(request.getParameter("nickname")).thenReturn("");
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("/quest");

        initServlet.doPost(request, response);

        verify(response).sendRedirect(contains("?error=true"));
        verify(session, never()).setAttribute(eq("nickname"), anyString());

    }

    @Test
    void doPost_ShouldStartGame_WhenNicknameIsValid() throws ServletException, IOException {

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nickname")).thenReturn("Hero");
        when(session.getAttribute("gameCount")).thenReturn(null);


        initServlet.doPost(request, response);

        verify(session).setAttribute("nickname", "Hero");
        verify(session).setAttribute(eq("gameCount"), anyInt());
        verify(response).sendRedirect("game");
    }
}