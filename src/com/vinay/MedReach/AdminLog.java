package com.vinay.MedReach;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import condb.connectiondbDemo;

/**
 * Servlet implementation class AdminLog
 */
public class AdminLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLog() {
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
		
		try {
			
			String email = request.getParameter("ademail");
			String password = request.getParameter("adpassword");
			
			Connection con = connectiondbDemo.getConnect();
			PreparedStatement ps = con.prepareStatement("select * from admindetails where ademail = ? and  adpassword = ?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			{
				System.out.println("Login Successful...!");
				response.sendRedirect("adminDash.html");
			}else {
				System.err.println("Login Failed...!");
				response.sendRedirect("adminLogin.html");
			}

			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}


