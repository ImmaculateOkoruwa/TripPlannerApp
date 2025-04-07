package com.tripplanner.dao;

import com.tripplanner.models.Car;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    // Add a car
    public boolean addCar(Car car) {
        String query = "INSERT INTO cars (brand, model, price_per_day, description) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, car.getBrand());
            pst.setString(2, car.getModel());
            pst.setDouble(3, car.getPricePerDay());
            pst.setString(4, car.getDescription());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all cars
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM cars";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                cars.add(new Car(
                    rs.getInt("id"),
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getDouble("price_per_day"),
                    rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    // Update a car
    public boolean updateCar(Car car) {
        String query = "UPDATE cars SET brand=?, model=?, price_per_day=?, description=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, car.getBrand());
            pst.setString(2, car.getModel());
            pst.setDouble(3, car.getPricePerDay());
            pst.setString(4, car.getDescription());
            pst.setInt(5, car.getId());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a car
    public boolean deleteCar(int carId) {
        String query = "DELETE FROM cars WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, carId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
