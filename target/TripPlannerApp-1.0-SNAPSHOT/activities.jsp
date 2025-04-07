<%@ page import="com.tripplanner.models.Activity" %>
<%@ page import="java.util.List" %>
<%
    List<Activity> activities = (List<Activity>) request.getAttribute("activities");
    Activity editActivity = (Activity) request.getAttribute("editActivity");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Activities - TripPlanner</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

<h2>Available Activities</h2>
<div class="row">
    <% for (int i = 1; i <= 6; i++) { %>
    <div class="col-md-4 mb-3">
        <div class="card">
            <img src="images/activity<%= i %>.jpg" class="card-img-top" alt="Activity <%= i %>">
            <div class="card-body">
                <h5 class="card-title">Activity <%= i %></h5>
                <p class="card-text">Fun and exciting experience in a great location. $25</p>
            </div>
        </div>
    </div>
    <% } %>
</div>

<h4 class="mt-5">Add or Update Activity</h4>
<form action="ActivityServlet" method="post" class="mb-4">
    <input type="hidden" name="id" value="<%= editActivity != null ? editActivity.getId() : "" %>">
    <div class="row g-2">
        <div class="col">
            <select name="name" class="form-select" required>
                <option value="">Select Activity</option>
                <% for (int i = 1; i <= 6; i++) { %>
                <option value="Activity <%= i %>" <%= editActivity != null && editActivity.getName().equals("Activity " + i) ? "selected" : "" %>>Activity <%= i %></option>
                <% } %>
            </select>
        </div>
        <div class="col">
            <select name="location" class="form-select" required>
                <option value="">Select Location</option>
                <% for (int i = 1; i <= 6; i++) { %>
                <option value="Location <%= i %>" <%= editActivity != null && editActivity.getLocation().equals("Location " + i) ? "selected" : "" %>>Location <%= i %></option>
                <% } %>
            </select>
        </div>
        <div class="col"><input type="number" step="0.01" name="price" class="form-control" placeholder="Price" value="<%= editActivity != null ? editActivity.getPrice() : "" %>" required></div>
        <div class="col"><input type="text" name="description" class="form-control" placeholder="Description" value="<%= editActivity != null ? editActivity.getDescription() : "" %>" required></div>
        <div class="col"><button type="submit" class="btn btn-primary"><%= editActivity != null ? "Update" : "Add" %> Activity</button></div>
    </div>
</form>

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
                <a href="ActivityServlet?action=edit&id=<%= activity.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                <a href="ActivityServlet?action=delete&id=<%= activity.getId() %>" class="btn btn-danger btn-sm">Delete</a>
            </td>
        </tr>
    <% } %>
    </tbody>
</table>
<a href="dashboard.jsp" class="btn btn-primary">Back to Dashboard</a>
</body>
</html>
