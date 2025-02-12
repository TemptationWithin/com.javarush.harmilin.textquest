<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/textquest_war_exploded/css/main.css">
</head>
<body style="background-image: url('images/knight.jpg'); background-size: cover; background-position: center; height: 100vh; display: flex; justify-content: center; align-items: center;">
<div class="form-container">
    <h1>Welcome to Quest!</h1>
    <p>What is your name?</p>
    <form action="startQuest" method="get">
        <input type="text" name="username" class="form-control" required pattern="[A-Za-z]+\d*"
               title="Name or nickname has to start with letter (may ends with digit)"/>
        <button class="btn btn-primary mt-3" type="submit">Start quest</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>