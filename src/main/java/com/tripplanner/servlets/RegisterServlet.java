package com.tripplanner.servlets;

import com.tripplanner.dao.UserDAO;
import com.tripplanner.models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    // Handles POST requests for user registration
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Validate input
        if (username == null || username.trim().isEmpty() || username.length() < 3 || username.length() > 20 || !username.matches("^[a-zA-Z0-9_]+$")) {
            request.setAttribute("errorMessage", "Invalid username. It must be 3-20 characters long and can only contain letters, numbers, and underscores.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (password == null || password.trim().isEmpty() || password.length() < 6 || password.length() > 20) {
            request.setAttribute("errorMessage", "Invalid password. It must be 6-20 characters long.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (email == null || email.trim().isEmpty() || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            request.setAttribute("errorMessage", "Invalid email format. Please enter a valid email address.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Create a new user object
        User user = new User(0, username, password, email);
        UserDAO userDAO = new UserDAO();

        // Attempt to register the user
        if (userDAO.registerUser(user)) {
            // Registration successful - redirect to login page
            response.sendRedirect("login.jsp");
        } else {
            // Registration failed - send error message back to registration page
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }
    }
}
