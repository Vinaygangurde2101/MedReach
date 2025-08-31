<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Appointment Booked</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: 'Segoe UI', sans-serif;
      background: linear-gradient(to right, #0f2027, #203a43, #2c5364);
      height: 100vh;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .popup {
      background-color: #fff;
      padding: 40px 30px;
      border-radius: 15px;
      box-shadow: 0 0 30px rgba(0, 255, 255, 0.2);
      text-align: center;
      animation: popIn 0.6s ease;
      position: relative;
      max-width: 420px;
    }

    .popup h2 {
      color: #00c2c2;
      font-size: 26px;
      margin-bottom: 10px;
    }

    .popup p {
      font-size: 16px;
      color: #333;
      margin-bottom: 10px;
    }

    .popup button {
      margin-top: 20px;
      padding: 10px 20px;
      background: #00c2c2;
      color: #fff;
      border: none;
      border-radius: 6px;
      font-weight: bold;
      cursor: pointer;
      transition: background 0.3s ease;
    }

    .popup button:hover {
      background: #009999;
    }

    @keyframes popIn {
      from {
        opacity: 0;
        transform: scale(0.5);
      }
      to {
        opacity: 1;
        transform: scale(1);
      }
    }

    .icon-check {
      font-size: 60px;
      color: #00ffcc;
      margin-bottom: 15px;
    }

    .details {
      text-align: left;
      background: #f2f2f2;
      padding: 15px;
      border-radius: 8px;
      margin-top: 10px;
    }

    .details span {
      font-weight: bold;
      color: #000;
    }
  </style>
</head>
<body>

  <%
    String pid = request.getParameter("pid");
    String did = request.getParameter("did");
    String adate = request.getParameter("adate");
    String atime = request.getParameter("atime");
  %>

  <div class="popup">
    <div class="icon-check">✅</div>
    <h2>Appointment Booked</h2>
    <p>Your appointment has been successfully scheduled!</p>

    <div class="details">
      <p><span>Patient ID:</span> <%= pid %></p>
      <p><span>Doctor ID:</span> <%= did %></p>
      <p><span>Date:</span> <%= adate %></p>
      <p><span>Time:</span> <%= atime %></p>
    </div>

    <button onclick="goToDashboard()">Go to Dashboard</button>
  </div>

  <script>
    function goToDashboard() {
      window.location.href = 'patientDash.html';
    }
  </script>

</body>
</html>
