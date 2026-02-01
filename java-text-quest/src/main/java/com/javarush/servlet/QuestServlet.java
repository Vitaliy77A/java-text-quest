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

@WebServlet(name = "QuestServlet", value = "/quest")
public class QuestServlet extends HttpServlet {
    private final QuestionRepository questionRepository = new QuestionRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
      HttpSession session = req.getSession();
      Integer questionId = (Integer) session.getAttribute("currentQuestionId");
      if (questionId == null) {
          questionId = 1;
        session.setAttribute("currentQuestionId", questionId );
      }
      Question question = questionRepository.getQuestionById(questionId);
      req.setAttribute("question", question);
      getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
        String nextIdString = req.getParameter("nextQuestionId");
        if (nextIdString == null) {
         req.getSession().invalidate();
         resp.sendRedirect(req.getContextPath() + "/quest");
         return;
        }
        try {
            int nextId = Integer.parseInt(nextIdString);
            HttpSession session = req.getSession();
            session.setAttribute("currentQuestionId", nextId);
        } catch (NumberFormatException e) {
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + "/quest");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/quest");

    }

}
