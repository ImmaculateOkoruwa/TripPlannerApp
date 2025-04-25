package com.tripplanner.servlets;

import com.tripplanner.dao.UserDAO;
import com.tripplanner.models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    // Handles POST requests for user login
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve username and password from the login form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate input (server-side validation)
        if (username == null || username.trim().isEmpty() || username.length() < 3 || username.length() > 20) {
            request.setAttribute("errorMessage", "Invalid username. Please ensure it is 3-20 characters long.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (password == null || password.trim().isEmpty() || password.length() < 6 || password.length() > 20) {
            request.setAttribute("errorMessage", "Invalid password. Please ensure it is 6-20 characters long.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Interact with the database to validate credentials
        UserDAO userDAO = new UserDAO();
        User user = userDAO.loginUser(username, password);

        if (user != null) {
            // Login successful - store user in session and redirect to dashboard
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("dashboard.jsp");
        } else {
            // Login failed - send error message back to login page
            request.setAttribute("errorMessage", "Invalid username or password. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
