<%@page import="com.vinay.meth.User"%>
<%@page import="condb.connectiondbDemo"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>💊 Prescriptions | MedReach</title>
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
      font-size: 36px;
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

    .empty-row {
      text-align: center;
      padding: 40px;
      color: #94a3b8;
      font-size: 1rem;
      font-style: italic;
    }

    table tr:last-child td {
      border-bottom: none;
    }
  </style>
</head>

<body>
  <h1>Today's Prescriptions</h1>

  <table>
    <thead>
      <tr>
        <th>PEID</th>
        <th>Patient Name</th>
        <th>Contact</th>
        <th>AID</th>
        <th>DID</th>
        <th>Prescription</th>
        <th>Date</th>
        <th>Delete</th>
      </tr>
    </thead>
    <tbody>
      <%
        boolean hasData = false;
        try {
          String pemail = User.getPemail(); 
          Connection con = connectiondbDemo.getConnect();

          String sql = "SELECT p.peid, p.aid, p.did, p.presc, p.pdate, pd.pname, pd.pcontact " +
                       "FROM prescription p " +
                       "JOIN appointments a ON p.aid = a.aid " +
                       "JOIN patientdetails pd ON a.pid = pd.pid " +
                       "WHERE pd.pemail = ?";
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, pemail);
          ResultSet rs = ps.executeQuery();

          while (rs.next()) {
            hasData = true;
      %>
      <tr>
        <td><%= rs.getInt("peid") %></td>
        <td><%= rs.getString("pname") %></td>
        <td><%= rs.getString("pcontact") %></td>
        <td><%= rs.getInt("aid") %></td>
        <td><%= rs.getInt("did") %></td>
        <td><%= rs.getString("presc") %></td>
        <td><%= rs.getDate("pdate") %></td>
        <td>
          <a href="deleteprescription.jsp?peid=<%= rs.getInt("peid") %>">Delete</a>
        </td>
      </tr>
      <%
          }

          if (!hasData) {
      %>
      <tr>
        <td colspan="8" class="empty-row">🎉 You have no prescriptions for today!</td>
      </tr>
      <%
          }
          rs.close();
          ps.close();
          con.close();
        } catch (Exception e) {
          e.printStackTrace();
      %>
      <tr>
        <td colspan="8" class="empty-row" style="color: red;">⚠️ Error loading prescriptions.</td>
      </tr>
      <%
        }
      %>
    </tbody>
  </table>
</body>
</html>
