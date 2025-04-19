<%@ page import="com.tripplanner.models.Activity" %>
<%@ page import="java.util.List" %>

<%
    // Retrieve the list of activities and the activity being edited (if any) from request attributes
    List<Activity> activities = (List<Activity>) request.getAttribute("activities");
    Activity editActivity = (Activity) request.getAttribute("editActivity");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Activities - TripPlanner</title>
    <!-- Load Bootstrap CSS for styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="container mt-4">

<h2>Available Activities</h2>

<!-- Display 3 featured, static activity cards with images and details -->
<div class="row mt-4">
    <h4>Featured Activities</h4>

    <div class="col-md-4 mb-3">
        <div class="card">
            <img src="images/hiking.jpg" class="card-img-top" alt="Activity 1">
            <div class="card-body">
                <h5 class="card-title">Hiking</h5>
                <p class="card-text">Great Western Trail Head<br>4333 Park Ave, Des Moines, IA 50321<br> $75/person.</p>
            </div>
        </div>
    </div>

    <div class="col-md-4 mb-3">
        <div class="card">
            <img src="images/volley.jpg" class="card-img-top" alt="Activity 2">
            <div class="card-body">
                <h5 class="card-title">Beach Volleyball</h5>
                <p class="card-text">Xtreme Beach Volleyball Club<br>750 SE Frontier Ave, Waukee, IA 50263<br> $30/person.</p>
            </div>
        </div>
    </div>

    <div class="col-md-4 mb-3">
        <div class="card">
            <img src="images/snorkeling.jpg" class="card-img-top" alt="Activity 3">
            <div class="card-body">
                <h5 class="card-title">Snorkeling</h5>
                <p class="card-text">Strac Scuba Shack<br>3821 6th Ave Unit C, Des Moines, IA 50313<br> $50/person.</p>
            </div>
        </div>
    </div>
</div>

<!-- Form for adding or editing an activity -->
<h4 class="mt-5">Add or Update Activity</h4>
<form action="ActivityServlet" method="post" class="mb-4">
    <!-- Hidden field to store ID (used for editing) -->
    <input type="hidden" name="id" value="<%= editActivity != null ? editActivity.getId() : "" %>">

    <!-- Activity input fields, pre-filled if editing -->
    <div class="row g-2">
        <div class="col">
            <input type="text" name="name" class="form-control" placeholder="Activity Name"
                   value="<%= editActivity != null ? editActivity.getName() : "" %>" required>
        </div>
        <div class="col">
            <input type="text" name="location" class="form-control" placeholder="Location"
                   value="<%= editActivity != null ? editActivity.getLocation() : "" %>" required>
        </div>
        <div class="col">
            <input type="number" step="0.01" name="price" class="form-control" placeholder="Price"
                   value="<%= editActivity != null ? editActivity.getPrice() : "" %>" required>
        </div>
        <div class="col">
            <input type="text" name="description" class="form-control" placeholder="Description"
                   value="<%= editActivity != null ? editActivity.getDescription() : "" %>" required>
        </div>

        <!-- Submit button dynamically displays "Add" or "Update" -->
        <div class="col">
            <button type="submit" class="btn btn-primary">
                <%= editActivity != null ? "Update" : "Add" %> Activity
            </button>
        </div>
    </div>
</form>

<!-- Dynamic activity table with Edit/Delete options -->
<table class="table table-striped">
    <thead>
        <tr><th>Name</th><th>Location</th><th>Price</th><th>Description</th><th>Actions</th></tr>
    </thead>
    <tbody>
    <% for (Activity activity : activities) { %>
        <tr>
            <td><%= activity.getName() %></td>
            <td><%= activity.getLocation() %></td>
            <td>$<%= activity.getPrice() %></td>
            <td><%= activity.getDescription() %></td>
            <td>
                <!-- Edit and Delete buttons with activity ID as a query parameter -->
                <a href="ActivityServlet?action=edit&id=<%= activity.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                <a href="ActivityServlet?action=delete&id=<%= activity.getId() %>" class="btn btn-danger btn-sm">Delete</a>
            </td>
        </tr>
    <% } %>
    </tbody>
</table>

<!-- Link to return to dashboard -->
<a href="dashboard.jsp" class="btn btn-primary">Back to Dashboard</a>

</body>
</html>
