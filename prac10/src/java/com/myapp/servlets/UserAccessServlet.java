package com.myapp.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UserAccessServlet")
public class UserAccessServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Retrieve active users count from ServletContext
        Integer activeUsers = (Integer) getServletContext().getAttribute("activeUsers");

        if (activeUsers == null) {
            activeUsers = 0;
        }

        // If session is new and user is not already counted
        if (session.getAttribute("userActive") == null) {
            if (activeUsers >= 3) {
                response.sendRedirect("views/error.jsp");  // Redirect if limit exceeded
                return;
            }
            activeUsers++;
            session.setAttribute("userActive", true);
            getServletContext().setAttribute("activeUsers", activeUsers);
        }

        response.sendRedirect("views/welcome.jsp"); // Redirect to the main page
    }
}
