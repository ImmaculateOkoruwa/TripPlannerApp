package com.tripplanner.servlets;

import com.tripplanner.dao.HotelDAO;
import com.tripplanner.models.Hotel;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class HotelServlet extends HttpServlet {

    // Handles GET requests to view, edit, or delete hotels
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HotelDAO hotelDAO = new HotelDAO();
        String action = request.getParameter("action"); // Check for a specific action like 'edit' or 'delete'

        // If user clicks delete, remove the hotel with the specified ID
        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            hotelDAO.deleteHotel(id);
            response.sendRedirect("HotelServlet"); // Refresh the page to reflect changes
            return; // Stop further processing
        }

        // If user clicks edit, find the hotel and pass it to the JSP for editing
        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            for (Hotel hotel : hotelDAO.getAllHotels()) {
                if (hotel.getId() == id) {
                    request.setAttribute("editHotel", hotel); // Attach hotel to request scope
                    break;
                }
            }
        }

        // Load all hotels and display them in the UI
        List<Hotel> hotels = hotelDAO.getAllHotels();
        request.setAttribute("hotels", hotels);
        RequestDispatcher dispatcher = request.getRequestDispatcher("hotels.jsp");
        dispatcher.forward(request, response); // Send request to JSP
    }

    // Handles POST requests to add or update hotel information
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HotelDAO hotelDAO = new HotelDAO();

        // Check if we're updating an existing hotel or adding a new one
        int id = request.getParameter("id") != null && !request.getParameter("id").isEmpty()
                 ? Integer.parseInt(request.getParameter("id")) : 0;

        // Collect hotel details from the submitted form
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");

        // Create a Hotel object using the form data
        Hotel hotel = new Hotel(id, name, location, price, description);

        // Save to DB: either update or insert based on whether ID is present
        boolean success = (id > 0) ? hotelDAO.updateHotel(hotel) : hotelDAO.addHotel(hotel);

        // If successful, redirect to reload the hotel list; otherwise, show error
        if (success) {
            response.sendRedirect("HotelServlet");
        } else {
            request.setAttribute("errorMessage", "Failed to save hotel");
            request.getRequestDispatcher("hotels.jsp").forward(request, response);
        }
    }
}
