package com.tripplanner.dao;

import com.tripplanner.models.Car;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarDAOTest {

    private CarDAO carDAO;

    @BeforeAll
    void setup() {
        // Initialize the DAO
        carDAO = new CarDAO();
    }

    @BeforeEach
    void populateDatabase() {
        // Populate the database with the provided data
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {

            // Clear the cars table
            stmt.execute("DELETE FROM cars");

            // Insert the provided data
            stmt.execute("INSERT INTO cars (id, brand, model, price_per_day, description) VALUES " +
                    "(1, 'Toyota', 'Corolla', 100.00, 'Reliable and fuel-efficient.'), " +
                    "(2, 'Ford', 'Mustang', 70.00, 'Sporty and fast.'), " +
                    "(3, 'Hyundai', 'Tucson', 20.00, 'Reliable and fuel-efficient')");
        } catch (Exception e) {
            fail("Failed to populate database: " + e.getMessage());
        }
    }

    @Test
    void testCreateReadUpdateDelete() {
        // Create: Add a new car
        Car newCar = new Car(0, "Tesla", "Model S", 150.00, "Electric and luxurious.");
        boolean createResult = carDAO.addCar(newCar);
        assertTrue(createResult, "New car should be added successfully");

        // Read: Retrieve all cars and verify the new car is present
        List<Car> cars = carDAO.getAllCars();
        assertEquals(4, cars.size(), "There should be four cars in the database after adding a new car");
        assertEquals("Tesla", cars.get(3).getBrand(), "The brand of the new car should match");

        // Update: Update the newly added car
        newCar.setId(cars.get(3).getId()); // Set the ID of the new car for updating
        newCar.setBrand("Tesla Updated");
        boolean updateResult = carDAO.updateCar(newCar);
        assertTrue(updateResult, "Car should be updated successfully");

        // Read: Retrieve all cars and verify the updated car details
        cars = carDAO.getAllCars();
        assertEquals(4, cars.size(), "There should still be four cars in the database");
        assertEquals("Tesla Updated", cars.get(3).getBrand(), "The brand of the updated car should match");

        // Delete: Delete the car
        boolean deleteResult = carDAO.deleteCar(cars.get(3).getId());
        assertTrue(deleteResult, "Car should be deleted successfully");

        // Read: Verify that the car has been deleted
        cars = carDAO.getAllCars();
        assertEquals(3, cars.size(), "There should be three cars in the database after deletion");

        // Restore original cars
        restoreOriginalCars();
    }

    private void restoreOriginalCars() {
        // Restore the original cars to the database
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute("DELETE FROM cars");
            stmt.execute("INSERT INTO cars (id, brand, model, price_per_day, description) VALUES " +
                    "(1, 'Toyota', 'Corolla', 100.00, 'Reliable and fuel-efficient.'), " +
                    "(2, 'Ford', 'Mustang', 70.00, 'Sporty and fast.'), " +
                    "(3, 'Hyundai', 'Tucson', 20.00, 'Reliable and fuel-efficient')");
        } catch (Exception e) {
            fail("Failed to restore original cars: " + e.getMessage());
        }
    }
}
