package com.tripplanner.dao;

import com.tripplanner.models.User;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDAOTest {

    private UserDAO userDAO;

    @BeforeAll
    void setup() {
        // Initialize the DAO
        userDAO = new UserDAO();
    }

    @BeforeEach
    void populateDatabase() {
        // Populate the database with the provided data
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {

            // Clear the users table
            stmt.execute("DELETE FROM users");

            // Insert the provided data
            stmt.execute("INSERT INTO users (id, username, password, email) VALUES " +
                    "(1, 'john_doe', 'password123', 'john@example.com'), " +
                    "(2, 'jane_doe', 'securepass', 'jane@example.com'), " +
                    "(4, 'john_doe', 'password123', 'jsmith@gmail.com'), " +
                    "(9, 'ImmaculateChukwu', 'Joelimma', 'immachukwuemeka@gmail.com')");
        } catch (Exception e) {
            fail("Failed to populate database: " + e.getMessage());
        }
    }

    @Test
    void testCreateReadDelete() {
        // Create: Add a new user
        User newUser = new User(0, "new_user", "newpassword", "newuser@example.com");
        boolean createResult = userDAO.registerUser(newUser);
        assertTrue(createResult, "New user should be added successfully");

        // Read: Retrieve all users and verify the new user is present
        List<User> users = userDAO.getAllUsers();
        assertEquals(5, users.size(), "There should be five users in the database after adding a new user");
        assertTrue(users.stream().anyMatch(user -> user.getUsername().equals("new_user")), "The new user should exist in the database");

        // Delete: Delete the new user
        int userIdToDelete = users.stream().filter(user -> user.getUsername().equals("new_user")).findFirst().get().getId();
        System.out.println("Deleting user with ID: " + userIdToDelete);
        boolean deleteResult = userDAO.deleteUser(userIdToDelete);
        assertTrue(deleteResult, "User should be deleted successfully");

        // Read: Verify that the user has been deleted
        users = userDAO.getAllUsers();
        System.out.println("Users after deletion: " + users);
        assertEquals(4, users.size(), "There should be four users in the database after deletion");

        // Restore original users
        restoreOriginalUsers();
    }

    private void restoreOriginalUsers() {
        // Restore the original users to the database
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute("DELETE FROM users");
            stmt.execute("INSERT INTO users (id, username, password, email) VALUES " +
                    "(1, 'john_doe', 'password123', 'john@example.com'), " +
                    "(2, 'jane_doe', 'securepass', 'jane@example.com'), " +
                    "(4, 'john_doe', 'password123', 'jsmith@gmail.com'), " +
                    "(9, 'ImmaculateChukwu', 'Joelimma', 'immachukwuemeka@gmail.com')");
        } catch (Exception e) {
            fail("Failed to restore original users: " + e.getMessage());
        }
    }
}
