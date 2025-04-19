<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- Set the content type and encoding for the page -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"> 
    <title>Login - TripPlanner</title> 
    <!-- Bootstrap CSS for responsive design and styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Login to TripPlanner</h2> 

        <!-- Check if there is an error message passed by the servlet -->
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger"> <!-- Display error message if present -->
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>

        <!-- Login form -->
        <form action="LoginServlet" method="post" class="mt-3"> <!-- POST to LoginServlet for authentication -->
            <div class="mb-3">
                <label for="username" class="form-label">Username</label> 
                <input type="text" name="username" class="form-control" required> 
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label> 
                <input type="password" name="password" class="form-control" required> 
            </div>
            <button type="submit" class="btn btn-primary">Login</button> 
            <a href="register.jsp" class="btn btn-link">Register</a> <!-- Link to the register page -->
        </form>
    </div>
</body>
</html>
