<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- Set the content type and encoding for the page -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"> <!-- Define character encoding for the page -->
    <title>TripPlanner - Home</title> <!-- Title of the page -->
    <!-- Bootstrap CSS for responsive design and styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Navigation bar with links to Login and Register pages -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">TripPlanner</a> <!-- Branding for the website -->
            <div class="d-flex">
                <a class="btn btn-outline-light me-2" href="login.jsp">Login</a> <!-- Link to login page -->
                <a class="btn btn-outline-light" href="register.jsp">Register</a> <!-- Link to register page -->
            </div>
        </div>
    </nav>

    <!-- Main content area centered on the page -->
    <div class="container text-center mt-5">
        <h1>Welcome to TripPlanner</h1> <!-- Main heading for the page -->
        <p class="lead">Plan your perfect trip with hotels, cars, and activities!</p> <!-- Description of the site functionality -->
    </div>
</body>
</html>
