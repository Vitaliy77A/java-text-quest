package com.javarush.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String nickname = req.getParameter("nickname");
        if (nickname != null && !nickname.isEmpty()) {
            session.setAttribute( "nickname", nickname);
        }
        Integer gameCount = (Integer) session.getAttribute("gameCount");

        if (gameCount == null) {
            gameCount = 0;
        }
        session.setAttribute("gameCount", gameCount + 1);
        session.setAttribute("currentQuestionId", 1);

        resp.sendRedirect("game");
    }

}
