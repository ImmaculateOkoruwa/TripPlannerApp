<%@ page import="com.tripplanner.models.Hotel" %>
<%@ page import="java.util.List" %>
<%
    List<Hotel> hotels = (List<Hotel>) request.getAttribute("hotels");
    Hotel editHotel = (Hotel) request.getAttribute("editHotel");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hotels - TripPlanner</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

<h2>Available Hotels</h2>
<div class="row">
    <div class="row mt-4">
        <h4>Featured Hotels</h4>
        <div class="col-md-4 mb-3">
            <div class="card">
                <img src="images/dsmrd-exterior-0006.jpg" class="card-img-top" alt="Hotel 1">
                <div class="card-body">
                    <h5 class="card-title">Residence Inn Des Moines Downtown</h5>
                    <p class="card-text">100 SW Water St, Des Moines, IA 50309 <br>$127/night<br> Central Location </p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-3">
            <div class="card">
                <img src="images/IA103exterior01_1.jpg" class="card-img-top" alt="Hotel 2">
                <div class="card-body">
                    <h5 class="card-title">Hampton Inn & Suites Des Moines</h5>
                    <p class="card-text">120 SW Water Street, Des Moines, Iowa, 50309<br>$131/night<br> Central Location </p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-3">
            <div class="card">
                <img src="images/the-ac-hotel-des-moines-ia-1.jpg" class="card-img-top" alt="Hotel 3">
                <div class="card-body">
                    <h5 class="card-title">AC Hotel Des Moines East Village</h5>
                    <p class="card-text"> 401 E Grand Ave, Des Moines, IA 50309<br>$188/night<br> Central Location </p>
                </div>
            </div>
        </div>
    </div>
</div>

<h4 class="mt-5">Add or Update Hotel</h4>
<form action="HotelServlet" method="post" class="mb-4">
    <input type="hidden" name="id" value="<%= editHotel != null ? editHotel.getId() : "" %>">
    <div class="row g-2">
        <div class="col">
            <input type="text" name="name" class="form-control" placeholder="Hotel Name" value="<%= editHotel != null ? editHotel.getName() : "" %>" required>
        </div>
        <div class="col">
            <input type="text" name="location" class="form-control" placeholder="Location" value="<%= editHotel != null ? editHotel.getLocation() : "" %>" required>
        </div>
        <div class="col">
            <input type="number" step="0.01" name="price" class="form-control" placeholder="Price/Night" value="<%= editHotel != null ? editHotel.getPricePerNight() : "" %>" required>
        </div>
        <div class="col">
            <input type="text" name="description" class="form-control" placeholder="Description" value="<%= editHotel != null ? editHotel.getDescription() : "" %>" required>
        </div>
        <div class="col">
            <button type="submit" class="btn btn-primary"><%= editHotel != null ? "Update" : "Add" %> Hotel</button>
        </div>
    </div>
</form>

<table class="table table-striped">
    <thead>
        <tr><th>Name</th><th>Location</th><th>Price/Night</th><th>Description</th><th>Actions</th></tr>
    </thead>
    <tbody>
    <% for (Hotel hotel : hotels) { %>
        <tr>
            <td><%= hotel.getName() %></td>
            <td><%= hotel.getLocation() %></td>
            <td>$<%= hotel.getPricePerNight() %></td>
            <td><%= hotel.getDescription() %></td>
            <td>
                <a href="HotelServlet?action=edit&id=<%= hotel.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                <a href="HotelServlet?action=delete&id=<%= hotel.getId() %>" class="btn btn-danger btn-sm">Delete</a>
            </td>
        </tr>
    <% } %>
    </tbody>
</table>
<a href="dashboard.jsp" class="btn btn-primary">Back to Dashboard</a>
</body>
</html>
