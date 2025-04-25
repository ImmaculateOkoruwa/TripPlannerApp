<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - TripPlanner</title>
    <!-- Bootstrap CSS for styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Login to TripPlanner</h2>

        <!-- Display error message if passed from the servlet -->
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>

        <!-- Login form -->
        <form action="LoginServlet" method="post" class="mt-3" id="loginForm">
            <!-- Username input -->
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" name="username" id="username" class="form-control" required minlength="3" maxlength="20" pattern="^[a-zA-Z0-9_]+$" title="Username must be 3-20 characters long and can only contain letters, numbers, and underscores.">
            </div>
            <!-- Password input -->
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" name="password" id="password" class="form-control" required minlength="6" maxlength="20" title="Password must be 6-20 characters long.">
            </div>
            <!-- Submit button -->
            <button type="submit" class="btn btn-primary">Login</button>
            <!-- Link to registration page -->
            <a href="register.jsp" class="btn btn-link">Register</a>
        </form>
    </div>
</body>
</html>
