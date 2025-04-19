package com.tripplanner.servlets;

import com.tripplanner.dao.ActivityDAO;
import com.tripplanner.models.Activity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ActivityServlet extends HttpServlet {

    // Handles GET requests from activities.jsp
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActivityDAO activityDAO = new ActivityDAO();
        String action = request.getParameter("action");

        // Delete action: handles activity deletion
        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            activityDAO.deleteActivity(id);
            response.sendRedirect("ActivityServlet");
            return;
        }

        // Edit action: retrieves activity for editing
        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            for (Activity activity : activityDAO.getAllActivities()) {
                if (activity.getId() == id) {
                    request.setAttribute("editActivity", activity);
                    break;
                }
            }
        }

        // Fetch all activities and forward to activities.jsp
        List<Activity> activities = activityDAO.getAllActivities();
        request.setAttribute("activities", activities);
        RequestDispatcher dispatcher = request.getRequestDispatcher("activities.jsp");
        dispatcher.forward(request, response);
    }

    // Handles POST requests for adding or updating activities
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActivityDAO activityDAO = new ActivityDAO();

        // Retrieve activity details from form inputs
        int id = request.getParameter("id") != null && !request.getParameter("id").isEmpty()
                 ? Integer.parseInt(request.getParameter("id")) : 0;
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");

        // Create Activity object with retrieved details
        Activity activity = new Activity(id, name, location, price, description);

        // Add or update activity in the database
        boolean success = (id > 0) ? activityDAO.updateActivity(activity) : activityDAO.addActivity(activity);

        // Redirect to ActivityServlet if successful; handle errors otherwise
        if (success) {
            response.sendRedirect("ActivityServlet");
        } else {
            request.setAttribute("errorMessage", "Failed to save activity");
            request.getRequestDispatcher("activities.jsp").forward(request, response);
        }
    }
}
