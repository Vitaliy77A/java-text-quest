package com.javarush.servlet;

import com.javarush.model.Question;
import com.javarush.model.QuestionRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "QuestServlet", value = "/game")
public class QuestServlet extends HttpServlet {

    private final QuestionRepository questionRepository = new QuestionRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("nickname") == null) {
            resp.sendRedirect("/jsp/quest.jsp");
            return;
        }

        Integer currentId = (Integer) session.getAttribute("currentQuestionId");
        if (currentId == null) currentId = 1;

        showQuestion(currentId, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String nextIdStr = req.getParameter("nextQuestionId");

        if (nextIdStr == null) {
            resp.sendRedirect("start");
            return;
        }

        int nextId = Integer.parseInt(nextIdStr);
        session.setAttribute("currentQuestionId", nextId);

        showQuestion(nextId, req, resp);
    }

    private void showQuestion(int id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Question question = questionRepository.getQuestionById(id);
        req.setAttribute("question", question);
        req.getRequestDispatcher("/jsp/quest.jsp").forward(req, resp);
    }
}