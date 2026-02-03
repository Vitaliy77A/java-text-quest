package com.javarush.controller;

import com.javarush.service.GameService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet  {

    private final GameService gameService = new GameService();
    private static final Logger LOGGER = LogManager.getLogger(InitServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String nickname = req.getParameter("nickname");

        if (gameService.isValidNickname(nickname)) {
            LOGGER.info("New game started for user: {}", nickname);
            session.setAttribute("nickname", nickname);

            Integer currentCount = (Integer) session.getAttribute("gameCount");
            int newCount = gameService.updateGameCounter(currentCount);

            session.setAttribute("gameCount", newCount);
            session.setAttribute("currentQuestionId", gameService.getStartQuestionId());

            resp.sendRedirect("game");

        } else {
            LOGGER.error("Invalid nickname attempt detected");
            resp.sendRedirect(req.getContextPath() + "/index.jsp?error=true");
        }

    }

}
