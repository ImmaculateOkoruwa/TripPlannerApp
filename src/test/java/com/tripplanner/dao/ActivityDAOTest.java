package com.tripplanner.dao;

import com.tripplanner.models.Activity;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ActivityDAOTest {

    private ActivityDAO activityDAO;

    @BeforeAll
    void setup() {
        // Initialize the DAO
        activityDAO = new ActivityDAO();
    }

    @BeforeEach
    void populateDatabase() {
        // Populate the database with the provided data
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {

            // Clear the activities table
            stmt.execute("DELETE FROM activities");

            // Insert the provided data
            stmt.execute("INSERT INTO activities (id, name, location, price, description) VALUES " +
                    "(1, 'City Tour', 'New York', 50.00, 'Explore the city highlights.'), " +
                    "(2, 'Wine Tasting', 'California', 100.00, 'Enjoy local wines and scenic views.'), " +
                    "(6, 'Hiking', 'Des Moines', 75.00, 'Fun and adventure')");
        } catch (Exception e) {
            fail("Failed to populate database: " + e.getMessage());
        }
    }

    @Test
    void testCreateReadUpdateDelete() {
        // Create: Add a new activity
        Activity newActivity = new Activity(0, "Skydiving", "Nevada", 200.00, "Experience the thrill of freefall.");
        boolean createResult = activityDAO.addActivity(newActivity);
        assertTrue(createResult, "New activity should be added successfully");

        // Read: Retrieve all activities and verify the new activity is present
        List<Activity> activities = activityDAO.getAllActivities();
        assertEquals(4, activities.size(), "There should be four activities in the database after adding a new activity");
        assertEquals("Skydiving", activities.get(3).getName(), "The name of the new activity should match");

        // Update: Update the newly added activity
        newActivity.setId(activities.get(3).getId()); // Set the ID of the new activity for updating
        newActivity.setName("Skydiving Adventure");
        boolean updateResult = activityDAO.updateActivity(newActivity);
        assertTrue(updateResult, "Activity should be updated successfully");

        // Read: Retrieve all activities and verify the updated activity details
        activities = activityDAO.getAllActivities();
        assertEquals(4, activities.size(), "There should still be four activities in the database");
        assertEquals("Skydiving Adventure", activities.get(3).getName(), "The name of the updated activity should match");

        // Delete: Delete the activity
        boolean deleteResult = activityDAO.deleteActivity(activities.get(3).getId());
        assertTrue(deleteResult, "Activity should be deleted successfully");

        // Read: Verify that the activity has been deleted
        activities = activityDAO.getAllActivities();
        assertEquals(3, activities.size(), "There should be three activities in the database after deletion");

        // Restore original activities
        restoreOriginalActivities();
    }

    private void restoreOriginalActivities() {
        // Restore the original activities to the database
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute("DELETE FROM activities");
            stmt.execute("INSERT INTO activities (id, name, location, price, description) VALUES " +
                    "(1, 'City Tour', 'New York', 50.00, 'Explore the city highlights.'), " +
                    "(2, 'Wine Tasting', 'California', 100.00, 'Enjoy local wines and scenic views.'), " +
                    "(6, 'Hiking', 'Des Moines', 75.00, 'Fun and adventure')");
        } catch (Exception e) {
            fail("Failed to restore original activities: " + e.getMessage());
        }
    }
}
