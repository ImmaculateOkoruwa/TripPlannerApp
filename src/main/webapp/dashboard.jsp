<%@ page import="com.tripplanner.models.User" %> <!-- Import the User model -->
<%@ page session="true" %> <!-- Enable session handling -->
<%
    // Check if user is logged in, if not redirect to login page
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
    <!-- Bootstrap CSS for responsive styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- Navigation bar with links to Hotels, Cars, Activities, and Logout -->
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

<!-- Main container -->
<div class="container mt-4">
    <!-- Welcome message for logged-in user -->
    <h3>Welcome, <%= user.getUsername() %>!</h3>
    <p>Select an option above to manage your trip details.</p>

    <!-- Section: Featured Hotels -->
    <div class="row mt-4">
        <h4>Featured Hotels</h4>
        
        <!-- Hotel 1 card -->
        <div class="col-md-4 mb-3">
            <div class="card">
                <img src="images/dsmrd-exterior-0006.jpg" class="card-img-top" alt="Hotel 1">
                <div class="card-body">
                    <h5 class="card-title">Residence Inn Des Moines Downtown</h5>
                    <p class="card-text">100 SW Water St, Des Moines, IA 50309 <br>$127/night<br> Central Location </p>
                </div>
            </div>
        </div>

        <!-- Hotel 2 card -->
        <div class="col-md-4 mb-3">
            <div class="card">
                <img src="images/IA103exterior01_1.jpg" class="card-img-top" alt="Hotel 2">
                <div class="card-body">
                    <h5 class="card-title">Hampton Inn & Suites Des Moines</h5>
                    <p class="card-text">120 SW Water Street, Des Moines, Iowa, 50309<br>$131/night<br> Central Location </p>
                </div>
            </div>
        </div>

        <!-- Hotel 3 card -->
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

    <!-- Section: Featured Cars -->
    <div class="row mt-4">
        <h4>Featured Cars</h4>

        <!-- Car 1 card -->
        <div class="col-md-4 mb-3">
            <div class="card">
                <img src="images/Corolla.jpg" class="card-img-top" alt="Car 1">
                <div class="card-body">
                    <h5 class="card-title">Toyota Corolla</h5>
                    <p class="card-text">2023 Model <br>$50/day<br>Comfortable and reliable.</p>
                </div>
            </div>
        </div>

        <!-- Car 2 card -->
        <div class="col-md-4 mb-3">
            <div class="card">
                <img src="images/Ford.jpg" class="card-img-top" alt="Car 2">
                <div class="card-body">
                    <h5 class="card-title">Ford Mustang</h5>
                    <p class="card-text">2024 Model<br> $60/day<br>Perfect for long trips.</p>
                </div>
            </div>
        </div>

        <!-- Car 3 card -->
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

    <!-- Section: Featured Activities -->
    <div class="row mt-4">
        <h4>Featured Activities</h4>

        <!-- Activity 1 card -->
        <div class="col-md-4 mb-3">
            <div class="card">
                <img src="images/hiking.jpg" class="card-img-top" alt="Activity 1">
                <div class="card-body">
                    <h5 class="card-title">Hiking</h5>
                    <p class="card-text">Great Western Trail Head<br>4333 Park Ave, Des Moines, IA 50321<br> $75/person.</p>
                </div>
            </div>
        </div>

        <!-- Activity 2 card -->
        <div class="col-md-4 mb-3">
            <div class="card">
                <img src="images/volley.jpg" class="card-img-top" alt="Activity 2">
                <div class="card-body">
                    <h5 class="card-title">Beach Volleyball</h5>
                    <p class="card-text">Xtreme Beach Volleyball Club<br>750 SE Frontier Ave, Waukee, IA 50263<br> $30/person.</p>
                </div>
            </div>
        </div>

        <!-- Activity 3 card -->
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
</div>

</body>
</html>
