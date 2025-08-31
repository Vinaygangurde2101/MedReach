<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="condb.connectiondbDemo"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Patient Accounts</title>
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

<h1>Patient Accounts</h1>

<table>
  <tr>
    <th>Patient ID</th>
    <th>Name</th>
    <th>Contact</th>
    <th>Address</th>
    <th>Email</th>
    <th>Delete Account</th>
  </tr>

  <%
  try {
    Connection con = connectiondbDemo.getConnect();
    PreparedStatement ps = con.prepareStatement("select * from patientdetails");
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
  %>
  <tr>
    <td><%=rs.getInt(1)%></td>
    <td><%=rs.getString(2)%></td>
    <td><%=rs.getString(3)%></td>
    <td><%=rs.getString(4)%></td>
    <td><%=rs.getString(5)%></td>
    <td>
      <a href="deletePatientAccount.jsp?pid=<%= rs.getInt(1) %>">Delete</a>
    </td>
  </tr>
  <%
    }
  } catch(Exception e){
    e.printStackTrace();
  }
  %>
</table>
</body>
</html>
