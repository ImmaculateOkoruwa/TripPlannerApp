package com.tripplanner.models;

public class Hotel {
    private int id;
    private String name;
    private String location;
    private double pricePerNight;
    private String description;

    // Constructors
    public Hotel() {}

    public Hotel(int id, String name, String location, double pricePerNight, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.pricePerNight = pricePerNight;
        this.description = description;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

