package com.javarush.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.mockito.Mockito.*;

class LanguageServletTest {

    private LanguageServlet languageServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    void setUp() {
        languageServlet = new LanguageServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("/quest-app");
    }

    @Test
    void doGet_ShouldSetLanguageInSession_WhenLanguageIsValid() throws ServletException, IOException {
        when(request.getParameter("lang")).thenReturn("en");
        when(request.getHeader("Referer")).thenReturn("http://localhost:8080/quest-app/game");

        languageServlet.doGet(request, response);

        verify(session, times(1)).setAttribute("language", "en");
        verify(response, times(1)).sendRedirect("http://localhost:8080/quest-app/game");
    }

    @Test
    void doGet_ShouldNotSetLanguage_WhenLanguageIsInvalid() throws ServletException, IOException {
        when(request.getParameter("lang")).thenReturn("qwerty");
        when(request.getHeader("Referer")).thenReturn("http://localhost:8080/quest-app/start");

        languageServlet.doGet(request, response);

        verify(session, never()).setAttribute(eq("language"), anyString());
        verify(response, times(1)).sendRedirect("http://localhost:8080/quest-app/start");
    }

    @Test
    void doGet_ShouldRedirectToRoot_WhenRefererIsNull() throws ServletException, IOException {
        when(request.getParameter("lang")).thenReturn("uk");
        when(request.getHeader("Referer")).thenReturn(null);

        languageServlet.doGet(request, response);

        verify(session, times(1)).setAttribute("language", "uk");
        verify(response, times(1)).sendRedirect("/quest-app/");
    }

}