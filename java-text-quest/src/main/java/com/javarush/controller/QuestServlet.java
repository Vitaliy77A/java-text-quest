package com.javarush.controller;

import com.javarush.model.Question;
import com.javarush.service.QuestService;
import com.javarush.service.QuestServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "QuestServlet", value = "/game")
public class QuestServlet extends HttpServlet {

    private final QuestService questService = new QuestServiceImp();
    private static final Logger LOGGER = LogManager.getLogger(QuestServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("nickname") == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        Integer currentId = (Integer) session.getAttribute("currentQuestionId");
        if (currentId == null) {
            currentId = 1;
        }

        Question question = questService.getQuestionById(currentId);

        req.setAttribute("question", question);
        req.getRequestDispatcher("/jsp/quest.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String nextIdStr = req.getParameter("nextQuestionId");

        if (nextIdStr == null) {
            LOGGER.info("User '{}' restarted the game.", session.getAttribute("nickname"));
            resp.sendRedirect("start");
            return;
        }

        try {

            int nextId = Integer.parseInt(nextIdStr);
            session.setAttribute("currentQuestionId", nextId);
            Question question = questService.getQuestionById(nextId);
            if (question.getGameState() != null) {
                LOGGER.info("Game Finished. User: '{}', Result: '{}'", session.getAttribute("nickname"), question.getGameState());
            }

            doGet(req, resp);

        } catch (NumberFormatException e) {
            LOGGER.error("INVALID nextQuestionId received from user '{}': {}", session.getAttribute("nickname"), nextIdStr, e);
            resp.sendRedirect("start");
        }
    }
}