<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Prescription Added</title>
  <style>
    body {
      background-color: #0f172a;
      color: #ffffff;
      font-family: Arial, sans-serif;
      padding: 40px;
      text-align: center;
    }

    .box {
      background-color: #1e293b;
      padding: 30px;
      border-radius: 10px;
      display: inline-block;
      box-shadow: 0 0 15px rgba(0,255,255,0.2);
    }

    h1 {
      color: #22d3ee;
    }

    p {
      margin: 10px 0;
      font-size: 18px;
    }

    a {
      display: inline-block;
      margin-top: 20px;
      padding: 10px 20px;
      background-color: #22d3ee;
      color: #000;
      text-decoration: none;
      border-radius: 6px;
    }

    a:hover {
      background-color: #0ea5e9;
    }
  </style>
</head>
<body>
  <div class="box">
    <h1>Prescription Added Successfully!</h1>
    <p><strong>Appointment ID:</strong> <%= request.getAttribute("aid") %></p>
    <p><strong>Doctor ID:</strong> <%= request.getAttribute("did") %></p>
    <p><strong>Prescription:</strong> <%= request.getAttribute("presc") %></p>
    <p><strong>Date:</strong> <%= request.getAttribute("pdate") %></p>
    <a href="doctorDash.html">Back to Dashboard</a>
  </div>
</body>
</html>
