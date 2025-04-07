package com.tripplanner.servlets;

import com.tripplanner.dao.CarDAO;
import com.tripplanner.models.Car;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class CarServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarDAO carDAO = new CarDAO();
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            carDAO.deleteCar(id);
            response.sendRedirect("CarServlet");
            return;
        }

        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            for (Car car : carDAO.getAllCars()) {
                if (car.getId() == id) {
                    request.setAttribute("editCar", car);
                    break;
                }
            }
        }

        List<Car> cars = carDAO.getAllCars();
        request.setAttribute("cars", cars);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cars.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarDAO carDAO = new CarDAO();
        int id = request.getParameter("id") != null && !request.getParameter("id").isEmpty()
                 ? Integer.parseInt(request.getParameter("id")) : 0;

        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");

        Car car = new Car(id, brand, model, price, description);
        boolean success = (id > 0) ? carDAO.updateCar(car) : carDAO.addCar(car);

        if (success) {
            response.sendRedirect("CarServlet");
        } else {
            request.setAttribute("errorMessage", "Failed to save car");
            request.getRequestDispatcher("cars.jsp").forward(request, response);
        }
    }
}
