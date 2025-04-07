<%@ page import="com.tripplanner.models.User" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - TripPlanner</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">TripPlanner</a>
        <div class="d-flex">
            <a class="btn btn-outline-light me-2" href="HotelServlet">Hotels</a>
            <a class="btn btn-outline-light me-2" href="CarServlet">Cars</a>
            <a class="btn btn-outline-light me-2" href="ActivityServlet">Activities</a>
            <a class="btn btn-danger" href="logout.jsp">Logout</a>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <h3>Welcome, <%= user.getUsername() %>!</h3>
    <p>Select an option above to manage your trip details.</p>

    <div class="row mt-4">
        <h4>Featured Hotels</h4>
        <% for (int i = 1; i <= 3; i++) { %>
        <div class="col-md-4 mb-3">
            <div class="card">
                <img src="images/hotel<%= i %>.jpg" class="card-img-top" alt="Hotel <%= i %>">
                <div class="card-body">
                    <h5 class="card-title">Hotel <%= i %></h5>
                    <p class="card-text">Sample description for hotel <%= i %>. Located in XYZ. $150/night.</p>
                </div>
            </div>
        </div>
        <% } %>
    </div>

    <div class="row mt-4">
        <h4>Featured Cars</h4>
        <% for (int i = 1; i <= 3; i++) { %>
        <div class="col-md-4 mb-3">
            <div class="card">
                <img src="images/car<%= i %>.jpg" class="card-img-top" alt="Car <%= i %>">
                <div class="card-body">
                    <h5 class="card-title">Car <%= i %></h5>
                    <p class="card-text">Brand Model <%= i %>. $50/day. Comfortable and reliable.</p>
                </div>
            </div>
        </div>
        <% } %>
    </div>

    <div class="row mt-4">
        <h4>Featured Activities</h4>
        <% for (int i = 1; i <= 3; i++) { %>
        <div class="col-md-4 mb-3">
            <div class="card">
                <img src="images/activity<%= i %>.jpg" class="card-img-top" alt="Activity <%= i %>">
                <div class="card-body">
                    <h5 class="card-title">Activity <%= i %></h5>
                    <p class="card-text">Exciting experience at location <%= i %>. $75/person.</p>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>

</body>
</html>
