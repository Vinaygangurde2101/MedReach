<%@page import="condb.connectiondbDemo"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>View Appointments</title>
<style>
  body {
    background: radial-gradient(ellipse at top left, #0a1628, #05060b);
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    color: #fff;
    padding: 50px;
  }

  h1 {
    text-align: center;
    color: #00fff7;
    margin-bottom: 30px;
  }

  table {
    width: 100%;
    border-collapse: collapse;
    background: rgba(255, 255, 255, 0.03);
    backdrop-filter: blur(10px);
    border-radius: 12px;
    overflow: hidden;
    box-shadow: 0 0 20px rgba(0, 255, 255, 0.08);
  }

  th, td {
    padding: 14px 20px;
    text-align: center;
    border-bottom: 1px solid rgba(255, 255, 255, 0.05);
  }

  th {
    background: rgba(0, 255, 255, 0.08);
    color: #00fff7;
    font-weight: bold;
    text-transform: uppercase;
    font-size: 14px;
  }

  tr:hover {
    background-color: rgba(0, 255, 255, 0.05);
    box-shadow: 0 0 15px rgba(0, 255, 255, 0.2);
    transition: 0.3s ease;
  }

  a {
    color: #ff4f4f;
    text-decoration: none;
    font-weight: bold;
    background: rgba(255, 0, 0, 0.1);
    padding: 6px 12px;
    border-radius: 8px;
    transition: 0.3s ease;
  }

  a:hover {
    background: #ff4f4f;
    color: #fff;
    box-shadow: 0 0 10px rgba(255, 79, 79, 0.6);
  }

  table tr:last-child td {
    border-bottom: none;
  }
</style>
</head>
<body>

<h1>All Appointments</h1>

<table>
  <tr>
    <th>Appointment ID</th>
    <th>Patient ID</th>
    <th>Doctor ID</th>
    <th>Doctor Name</th>
    <th>Doctor Contact</th>
    <th>Date</th>
    <th>Time</th>
    <th>Status</th>
    <th>Delete Appointment</th>
  </tr>

  <%
  try {
    Connection con = connectiondbDemo.getConnect();
    PreparedStatement ps = con.prepareStatement(
      "SELECT a.aid, a.pid, a.did, a.adate, a.atime, a.astatus, d.dname, d.dcontact " +
      "FROM appointments a " +
      "JOIN doctordetails d ON a.did = d.did"
    );
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
  %>
  <tr>
    <td><%= rs.getInt("aid") %></td>
    <td><%= rs.getInt("pid") %></td>
    <td><%= rs.getInt("did") %></td>
    <td><%= rs.getString("dname") %></td>
    <td><%= rs.getString("dcontact") %></td>
    <td><%= rs.getString("adate") %></td>
    <td><%= rs.getString("atime") %></td>
    <td><%= rs.getString("astatus") %></td>
    <td>
      <a href="deleteAppointment.jsp?aid=<%= rs.getInt("aid") %>">Delete</a>
    </td>
  </tr>
  <%
    }
    rs.close();
    ps.close();
    con.close();
  } catch(Exception e){
    e.printStackTrace();
  }
  %>
</table>

</body>
</html>
