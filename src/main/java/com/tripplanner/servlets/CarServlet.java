package com.tripplanner.servlets;

import com.tripplanner.dao.CarDAO;
import com.tripplanner.models.Car;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class CarServlet extends HttpServlet {

    // Handles GET requests for viewing, editing, or deleting cars
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarDAO carDAO = new CarDAO();
        String action = request.getParameter("action"); // Check for any action like delete or edit

        // If the action is "delete", remove the car with the given ID
        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            carDAO.deleteCar(id);
            response.sendRedirect("CarServlet"); // Redirect to refresh the car list
            return;
        }

        // If the action is "edit", find the car and set it for editing in the UI
        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            for (Car car : carDAO.getAllCars()) {
                if (car.getId() == id) {
                    request.setAttribute("editCar", car); // Set the selected car as a request attribute
                    break;
                }
            }
        }

        // Load all cars and forward the data to cars.jsp
        List<Car> cars = carDAO.getAllCars();
        request.setAttribute("cars", cars);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cars.jsp");
        dispatcher.forward(request, response); // Forward the request to the JSP page
    }

    // Handles POST requests for adding or updating car details
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarDAO carDAO = new CarDAO();

        // Retrieve ID if available (used for update); if not, assume new car
        int id = request.getParameter("id") != null && !request.getParameter("id").isEmpty()
                 ? Integer.parseInt(request.getParameter("id")) : 0;

        // Collect form input values
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");

        // Create a Car object from the form inputs
        Car car = new Car(id, brand, model, price, description);

        // Decide whether to update or add a new car based on ID
        boolean success = (id > 0) ? carDAO.updateCar(car) : carDAO.addCar(car);

        // If operation successful, redirect to refresh list; otherwise, return to form with error
        if (success) {
            response.sendRedirect("CarServlet");
        } else {
            request.setAttribute("errorMessage", "Failed to save car");
            request.getRequestDispatcher("cars.jsp").forward(request, response);
        }
    }
}
