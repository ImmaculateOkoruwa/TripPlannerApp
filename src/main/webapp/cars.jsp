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
    <div class="row mt-4">
        <h4>Featured Cars</h4>
        <div class="col-md-4 mb-3">
            <div class="card">
                <img src="images/Corolla.jpg" class="card-img-top" alt="Car 1">
                <div class="card-body">
                    <h5 class="card-title">Toyota Corolla</h5>
                    <p class="card-text">2023 Model <br>$50/day<br>Comfortable and reliable.</p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-3">
            <div class="card">
                <img src="images/Ford.jpg" class="card-img-top" alt="Car 2">
                <div class="card-body">
                    <h5 class="card-title">Ford Mustang</h5>
                    <p class="card-text">2024 Model<br> $60/day<br>Perfect for long trips.</p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-3">
            <div class="card">
                <img src="images/Honda.jpg" class="card-img-top" alt="Car 3">
                <div class="card-body">
                    <h5 class="card-title">Honda Civic</h5>
                    <p class="card-text">2025 Model<br> $70/day<br> Luxury and style.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<h4 class="mt-5">Add or Update Car</h4>
<form action="CarServlet" method="post" class="mb-4">
    <input type="hidden" name="id" value="<%= editCar != null ? editCar.getId() : "" %>">
    <div class="row g-2">
        <div class="col">
            <input type="text" name="brand" class="form-control" placeholder="Brand" value="<%= editCar != null ? editCar.getBrand() : "" %>" required>
        </div>
        <div class="col">
            <input type="text" name="model" class="form-control" placeholder="Model" value="<%= editCar != null ? editCar.getModel() : "" %>" required>
        </div>
        <div class="col">
            <input type="number" step="0.01" name="price" class="form-control" placeholder="Price/Day" value="<%= editCar != null ? editCar.getPricePerDay() : "" %>" required>
        </div>
        <div class="col">
            <input type="text" name="description" class="form-control" placeholder="Description" value="<%= editCar != null ? editCar.getDescription() : "" %>" required>
        </div>
        <div class="col">
            <button type="submit" class="btn btn-primary"><%= editCar != null ? "Update" : "Add" %> Car</button>
        </div>
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
