<%@ page import="com.tripplanner.models.Car" %>
<%@ page import="java.util.List" %>
<%
    List<Car> cars = (List<Car>) request.getAttribute("cars");
    Car editCar = (Car) request.getAttribute("editCar");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cars - TripPlanner</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

<h2>Available Cars</h2>
<div class="row">
    <% for (int i = 1; i <= 6; i++) { %>
    <div class="col-md-4 mb-3">
        <div class="card">
            <img src="images/car<%= i %>.jpg" class="card-img-top" alt="Car <%= i %>">
            <div class="card-body">
                <h5 class="card-title">Brand Model <%= i %></h5>
                <p class="card-text">Reliable car for city and long travel. $50/day</p>
            </div>
        </div>
    </div>
    <% } %>
</div>

<h4 class="mt-5">Add or Update Car</h4>
<form action="CarServlet" method="post" class="mb-4">
    <input type="hidden" name="id" value="<%= editCar != null ? editCar.getId() : "" %>">
    <div class="row g-2">
        <div class="col">
            <select name="brand" class="form-select" required>
                <option value="">Select Brand</option>
                <% for (int i = 1; i <= 6; i++) { %>
                <option value="Brand <%= i %>" <%= editCar != null && editCar.getBrand().equals("Brand " + i) ? "selected" : "" %>>Brand <%= i %></option>
                <% } %>
            </select>
        </div>
        <div class="col">
            <select name="model" class="form-select" required>
                <option value="">Select Model</option>
                <% for (int i = 1; i <= 6; i++) { %>
                <option value="Model <%= i %>" <%= editCar != null && editCar.getModel().equals("Model " + i) ? "selected" : "" %>>Model <%= i %></option>
                <% } %>
            </select>
        </div>
        <div class="col"><input type="number" step="0.01" name="price" class="form-control" placeholder="Price/Day" value="<%= editCar != null ? editCar.getPricePerDay() : "" %>" required></div>
        <div class="col"><input type="text" name="description" class="form-control" placeholder="Description" value="<%= editCar != null ? editCar.getDescription() : "" %>" required></div>
        <div class="col"><button type="submit" class="btn btn-primary"><%= editCar != null ? "Update" : "Add" %> Car</button></div>
    </div>
</form>

<table class="table table-striped">
    <thead>
        <tr><th>Brand</th><th>Model</th><th>Price/Day</th><th>Description</th><th>Actions</th></tr>
    </thead>
    <tbody>
    <% for (Car car : cars) { %>
        <tr>
            <td><%= car.getBrand() %></td>
            <td><%= car.getModel() %></td>
            <td>$<%= car.getPricePerDay() %></td>
            <td><%= car.getDescription() %></td>
            <td>
                <a href="CarServlet?action=edit&id=<%= car.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                <a href="CarServlet?action=delete&id=<%= car.getId() %>" class="btn btn-danger btn-sm">Delete</a>
            </td>
        </tr>
    <% } %>
    </tbody>
</table>
<a href="dashboard.jsp" class="btn btn-primary">Back to Dashboard</a>
</body>
</html>