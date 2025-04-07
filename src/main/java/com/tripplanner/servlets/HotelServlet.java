package com.tripplanner.servlets;

import com.tripplanner.dao.HotelDAO;
import com.tripplanner.models.Hotel;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class HotelServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HotelDAO hotelDAO = new HotelDAO();
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            hotelDAO.deleteHotel(id);
            response.sendRedirect("HotelServlet");
            return;
        }

        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            for (Hotel hotel : hotelDAO.getAllHotels()) {
                if (hotel.getId() == id) {
                    request.setAttribute("editHotel", hotel);
                    break;
                }
            }
        }

        List<Hotel> hotels = hotelDAO.getAllHotels();
        request.setAttribute("hotels", hotels);
        RequestDispatcher dispatcher = request.getRequestDispatcher("hotels.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HotelDAO hotelDAO = new HotelDAO();
        int id = request.getParameter("id") != null && !request.getParameter("id").isEmpty()
                 ? Integer.parseInt(request.getParameter("id")) : 0;

        String name = request.getParameter("name");
        String location = request.getParameter("location");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");

        Hotel hotel = new Hotel(id, name, location, price, description);
        boolean success = (id > 0) ? hotelDAO.updateHotel(hotel) : hotelDAO.addHotel(hotel);

        if (success) {
            response.sendRedirect("HotelServlet");
        } else {
            request.setAttribute("errorMessage", "Failed to save hotel");
            request.getRequestDispatcher("hotels.jsp").forward(request, response);
        }
    }
}
