<%@ page import="java.sql.*" %>
<%@ page import="condb.connectiondbDemo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    try {
        String aidParam = request.getParameter("aid");

        if (aidParam != null && !aidParam.isEmpty()) {
            int aid = Integer.parseInt(aidParam);

            // Connect to DB
            Connection con = connectiondbDemo.getConnect();

            // Prepare delete query
            String query = "DELETE FROM appointments WHERE aid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, aid);

            int result = ps.executeUpdate();

            if (result > 0) {
                // Successful deletion, redirect to appointments view
                response.sendRedirect("viewAppoDoctor.jsp");
            } else {
                out.println("<h3 style='color: red;'>No appointment found with ID: " + aid + "</h3>");
            }

            ps.close();
            con.close();
        } else {
            out.println("<h3 style='color: red;'>Invalid appointment ID.</h3>");
        }
    } catch (Exception e) {
        out.println("<h3 style='color: red;'>Error deleting appointment: " + e.getMessage() + "</h3>");
        e.printStackTrace();
    }
%>
