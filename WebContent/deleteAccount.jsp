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
	try{
		
		String did = request.getParameter("did");
				
		Connection con = connectiondbDemo.getConnect();
				
		PreparedStatement ps = con.prepareStatement("delete from doctordetails where did = ?");
		ps.setString(1, did);
		int i = ps.executeUpdate();
				
				while (i>0) {
					System.out.println("Account Deleted Successfully.....!!");
					response.sendRedirect("viewAllDoctors.jsp");
				}
		
	}catch(Exception e){
		e.printStackTrace();
	}
%>

</body>
</html>