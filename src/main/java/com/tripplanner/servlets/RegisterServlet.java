package com.tripplanner.servlets;

import com.tripplanner.dao.UserDAO;
import com.tripplanner.models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    // Handles POST requests from the registration form
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form input values for username, password, and email
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Create a new User object using the form inputs (ID is 0 for new user)
        User user = new User(0, username, password, email);
        UserDAO userDAO = new UserDAO();

        // Attempt to register the user using the DAO
        if (userDAO.registerUser(user)) {
            // Registration successful - redirect user to the login page
            response.sendRedirect("login.jsp");
        } else {
            // Registration failed - send the user back to the registration page with an error
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response); // Preserve form data and display the error
        }
    }
}
