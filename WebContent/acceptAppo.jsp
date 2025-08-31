<%@page import="condb.connectiondbDemo"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String aid = request.getParameter("aid"); 

try {
	Connection con = connectiondbDemo.getConnect();
	PreparedStatement ps = con.prepareStatement("UPDATE appointments SET astatus = ? WHERE aid = ?");
	ps.setString(1, "Agree");
	ps.setString(2, aid);

	int i = ps.executeUpdate();
	if (i > 0) {
		System.out.println("Appointment ID " + aid + " accepted.");
		response.sendRedirect("viewAppointments.jsp"); 
	} else {
		System.out.println("Appointment not found.");
		response.sendRedirect("error.html");
	}
} catch (Exception e) {
	e.printStackTrace();
	response.sendRedirect("error.html");
}
%>

</body>
</html>