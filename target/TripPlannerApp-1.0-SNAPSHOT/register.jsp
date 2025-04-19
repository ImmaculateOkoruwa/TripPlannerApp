<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- Set the content type and encoding for the page -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"> <!-- Define character encoding for the page -->
    <title>Register - TripPlanner</title> <!-- Title of the page -->
    <!-- Bootstrap CSS for responsive design and styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Create an Account</h2> <!-- Heading for the registration form -->

        <!-- Check if there is an error message passed by the servlet -->
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger"> <!-- Display error message if present -->
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>

        <!-- Registration form -->
        <form action="RegisterServlet" method="post" class="mt-3"> <!-- POST to RegisterServlet to create a new user -->
            <div class="mb-3">
                <label for="username" class="form-label">Username</label> 
                <input type="text" name="username" class="form-control" required> 
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label> 
                <input type="password" name="password" class="form-control" required> 
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label> 
                <input type="email" name="email" class="form-control" required> 
            </div>
            <button type="submit" class="btn btn-primary">Register</button> <!-- Submit button for registration -->
        </form>
    </div>
</body>
</html>
