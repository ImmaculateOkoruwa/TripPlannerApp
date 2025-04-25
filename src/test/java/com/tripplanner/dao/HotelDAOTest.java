package com.tripplanner.dao;

import com.tripplanner.models.Hotel;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HotelDAOTest {

    private HotelDAO hotelDAO;

    @BeforeAll
    void setup() {
        // Initialize the DAO
        hotelDAO = new HotelDAO();
    }

    @BeforeEach
    void clearDatabase() {
        // Clear the hotels table before each test
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute("DELETE FROM hotels");
        } catch (Exception e) {
            fail("Failed to clear database: " + e.getMessage());
        }
    }

    @Test
    void testCreateReadUpdateDelete() {
        // Create: Add a new hotel
        Hotel newHotel = new Hotel(0, "Ocean Breeze", "Miami", 200.00, "A relaxing beachfront hotel.");
        boolean createResult = hotelDAO.addHotel(newHotel);
        assertTrue(createResult, "New hotel should be added successfully");

        // Read: Retrieve all hotels and verify the new hotel is present
        List<Hotel> hotels = hotelDAO.getAllHotels();
        assertEquals(1, hotels.size(), "There should be one hotel in the database after adding a new hotel");
        assertEquals("Ocean Breeze", hotels.get(0).getName(), "The name of the new hotel should match");

        // Update: Update the newly added hotel
        newHotel.setId(hotels.get(0).getId()); // Set the ID of the new hotel for updating
        newHotel.setName("Ocean View Resort");
        boolean updateResult = hotelDAO.updateHotel(newHotel);
        assertTrue(updateResult, "Hotel should be updated successfully");

        // Read: Retrieve all hotels and verify the updated hotel details
        hotels = hotelDAO.getAllHotels();
        assertEquals(1, hotels.size(), "There should still be one hotel in the database");
        assertEquals("Ocean View Resort", hotels.get(0).getName(), "The name of the updated hotel should match");

        // Delete: Delete the hotel
        boolean deleteResult = hotelDAO.deleteHotel(hotels.get(0).getId());
        assertTrue(deleteResult, "Hotel should be deleted successfully");

        // Read: Verify that the hotel has been deleted
        hotels = hotelDAO.getAllHotels();
        assertTrue(hotels.isEmpty(), "There should be no hotels in the database after deletion");

        // Restore original hotels
        restoreOriginalHotels();
    }

    private void restoreOriginalHotels() {
        // Restore the original hotels to the database
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute("INSERT INTO hotels (id, name, location, price_per_night, description) VALUES " +
                    "(1, 'The Grand Hotel', 'New York', 150.00, 'Luxury hotel in the heart of the city.'), " +
                    "(2, 'Beachside Inn', 'California', 180.00, 'Relax and enjoy the ocean view.'), " +
                    "(4, 'Marriot', 'Des Moines', 258.00, 'Five Star Hotel')");
        } catch (Exception e) {
            fail("Failed to restore original hotels: " + e.getMessage());
        }
    }
}
