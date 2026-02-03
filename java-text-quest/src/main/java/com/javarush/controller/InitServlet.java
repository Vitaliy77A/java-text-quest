package com.javarush.controller;

import com.javarush.service.GameService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {

    private final GameService gameService = new GameService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String nickname = req.getParameter("nickname");

        if (gameService.isValidNickname(nickname)) {
            session.setAttribute("nickname", nickname);

            Integer currentCount = (Integer) session.getAttribute("gameCount");
            int newCount = gameService.updateGameCounter(currentCount);

            session.setAttribute("gameCount", newCount);
            session.setAttribute("currentQuestionId", gameService.getStartQuestionId());

            resp.sendRedirect("game");

        } else {

            resp.sendRedirect(req.getContextPath() + "/index.jsp?error=true");
        }

    }

}
