package com.tripplanner.dao;

import com.tripplanner.models.Hotel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {

    // Add a hotel
    public boolean addHotel(Hotel hotel) {
        String query = "INSERT INTO hotels (name, location, price_per_night, description) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, hotel.getName());
            pst.setString(2, hotel.getLocation());
            pst.setDouble(3, hotel.getPricePerNight());
            pst.setString(4, hotel.getDescription());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all hotels
    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM hotels";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                hotels.add(new Hotel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getDouble("price_per_night"),
                    rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    // Update a hotel
    public boolean updateHotel(Hotel hotel) {
        String query = "UPDATE hotels SET name=?, location=?, price_per_night=?, description=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, hotel.getName());
            pst.setString(2, hotel.getLocation());
            pst.setDouble(3, hotel.getPricePerNight());
            pst.setString(4, hotel.getDescription());
            pst.setInt(5, hotel.getId());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a hotel
    public boolean deleteHotel(int hotelId) {
        String query = "DELETE FROM hotels WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, hotelId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
