package com.vinay.MedReach;

import java.io.IOException;
import java.sql.*;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import condb.connectiondbDemo;

/**
 * Servlet implementation class ChangePass
 */
public class ChangePass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String demail = request.getParameter("email");
		String oldpass=request.getParameter("oldpass");
		String newpass=request.getParameter("newpass");
		

		try
		{
			Connection con= connectiondbDemo.getConnect();
			PreparedStatement ps1=con.prepareStatement("select * from doctordetails where demail=?  ");
			ps1.setString(1, demail);
			ResultSet rs=ps1.executeQuery();
			if(rs.next())
			{
				String currentPass= rs.getString("dpassword");
				if(currentPass.equals(oldpass))
				{
					PreparedStatement ps2= con.prepareStatement("update doctordetails set dpassword=? where demail=?");
					ps2.setString(1, newpass);
					ps2.setString(2, demail);
					int i=ps2.executeUpdate();
					if(i>0)
					{
						response.sendRedirect("doctorDash.html");
						
					}
					else
					{
						response.sendRedirect("error.html");
					}
				}
				else
				{
					response.sendRedirect("error.html");
				}
			}
			else
			{
				response.sendRedirect("error.html");
			}
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
	}
}