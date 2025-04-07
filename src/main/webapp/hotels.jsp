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
    <% for (int i = 1; i <= 6; i++) { %>
    <div class="col-md-4 mb-3">
        <div class="card">
            <img src="images/hotel<%= i %>.jpg" class="card-img-top" alt="Hotel <%= i %>">
            <div class="card-body">
                <h5 class="card-title">Hotel <%= i %></h5>
                <p class="card-text">Located at Location <%= i %>. $100 per night.</p>
            </div>
        </div>
    </div>
    <% } %>
</div>

<h4 class="mt-5">Add or Update Hotel</h4>
<form action="HotelServlet" method="post" class="mb-4">
    <input type="hidden" name="id" value="<%= editHotel != null ? editHotel.getId() : "" %>">
    <div class="row g-2">
        <div class="col">
            <select name="name" class="form-select" required>
                <option value="">Select Hotel</option>
                <% for (int i = 1; i <= 6; i++) { %>
                <option value="Hotel <%= i %>" <%= editHotel != null && editHotel.getName().equals("Hotel " + i) ? "selected" : "" %>>Hotel <%= i %></option>
                <% } %>
            </select>
        </div>
        <div class="col">
            <select name="location" class="form-select" required>
                <option value="">Select Location</option>
                <% for (int i = 1; i <= 6; i++) { %>
                <option value="Location <%= i %>" <%= editHotel != null && editHotel.getLocation().equals("Location " + i) ? "selected" : "" %>>Location <%= i %></option>
                <% } %>
            </select>
        </div>
        <div class="col"><input type="number" step="0.01" name="price" class="form-control" placeholder="Price/Night" value="<%= editHotel != null ? editHotel.getPricePerNight() : "" %>" required></div>
        <div class="col"><input type="text" name="description" class="form-control" placeholder="Description" value="<%= editHotel != null ? editHotel.getDescription() : "" %>" required></div>
        <div class="col"><button type="submit" class="btn btn-primary"><%= editHotel != null ? "Update" : "Add" %> Hotel</button></div>
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
