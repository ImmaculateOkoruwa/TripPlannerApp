package com.tripplanner.dao;

import com.tripplanner.models.Activity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAO {

    // Add an activity
    public boolean addActivity(Activity activity) {
        String query = "INSERT INTO activities (name, location, price, description) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, activity.getName());
            pst.setString(2, activity.getLocation());
            pst.setDouble(3, activity.getPrice());
            pst.setString(4, activity.getDescription());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all activities
    public List<Activity> getAllActivities() {
        List<Activity> activities = new ArrayList<>();
        String query = "SELECT * FROM activities";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                activities.add(new Activity(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getDouble("price"),
                    rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    // Update an activity
    public boolean updateActivity(Activity activity) {
        String query = "UPDATE activities SET name=?, location=?, price=?, description=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, activity.getName());
            pst.setString(2, activity.getLocation());
            pst.setDouble(3, activity.getPrice());
            pst.setString(4, activity.getDescription());
            pst.setInt(5, activity.getId());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an activity
    public boolean deleteActivity(int activityId) {
        String query = "DELETE FROM activities WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, activityId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
