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

import java.io.IOException;

@WebServlet(name = "QuestServlet", value = "/game")
public class QuestServlet extends HttpServlet {

    private final QuestService questService = new QuestServiceImp();

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
            resp.sendRedirect("start");
            return;
        }

        try {

            int nextId = Integer.parseInt(nextIdStr);
            session.setAttribute("currentQuestionId", nextId);

            doGet(req, resp);

        } catch (NumberFormatException e) {

            resp.sendRedirect("start");
        }
    }
}