package com.tripplanner.servlets;

import com.tripplanner.dao.ActivityDAO;
import com.tripplanner.models.Activity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ActivityServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActivityDAO activityDAO = new ActivityDAO();
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            activityDAO.deleteActivity(id);
            response.sendRedirect("ActivityServlet");
            return;
        }

        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            for (Activity activity : activityDAO.getAllActivities()) {
                if (activity.getId() == id) {
                    request.setAttribute("editActivity", activity);
                    break;
                }
            }
        }

        List<Activity> activities = activityDAO.getAllActivities();
        request.setAttribute("activities", activities);
        RequestDispatcher dispatcher = request.getRequestDispatcher("activities.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActivityDAO activityDAO = new ActivityDAO();
        int id = request.getParameter("id") != null && !request.getParameter("id").isEmpty()
                 ? Integer.parseInt(request.getParameter("id")) : 0;

        String name = request.getParameter("name");
        String location = request.getParameter("location");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");

        Activity activity = new Activity(id, name, location, price, description);
        boolean success = (id > 0) ? activityDAO.updateActivity(activity) : activityDAO.addActivity(activity);

        if (success) {
            response.sendRedirect("ActivityServlet");
        } else {
            request.setAttribute("errorMessage", "Failed to save activity");
            request.getRequestDispatcher("activities.jsp").forward(request, response);
        }
    }
}
