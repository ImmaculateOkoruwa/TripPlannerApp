package com.tripplanner.servlets;

import com.tripplanner.dao.UserDAO;
import com.tripplanner.models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    // Handles POST requests when the login form is submitted
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the submitted username and password from the login form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Interact with the database to validate credentials
        UserDAO userDAO = new UserDAO();
        User user = userDAO.loginUser(username, password);

        // If login is successful, store user info in session and redirect to dashboard
        if (user != null) {
            HttpSession session = request.getSession(); // Create or retrieve the current session
            session.setAttribute("user", user); // Store the logged-in user in the session
            response.sendRedirect("dashboard.jsp"); // Redirect user to the dashboard
        } else {
            // If login fails, set an error message and return to login page
            request.setAttribute("errorMessage", "Invalid username or password. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response); // Keep user on the login page with the error message
        }
    }
}
