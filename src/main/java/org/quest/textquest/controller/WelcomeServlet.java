package org.quest.textquest.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/startQuest")
public class WelcomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("username", request.getParameter("username"));
        Cookie usernameCookie = new Cookie("username", request.getParameter("username"));
        usernameCookie.setMaxAge(60 * 60 * 24);
        response.addCookie(usernameCookie);
        request.getRequestDispatcher("/quest?questionId=1").forward(request, response);

    }
}
