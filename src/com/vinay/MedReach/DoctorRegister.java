package com.vinay.MedReach;

import java.io.IOException;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import condb.connectiondbDemo;

/**
 * Servlet implementation class DoctorRegister
 */
public class DoctorRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorRegister() {
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
		//doGet(request, response);
		
		try {
			int did = 0;
			String dname = request.getParameter("dname");
			String specialization = request.getParameter("specialization");
			String dcontact = request.getParameter("dcontact");
			String demail = request.getParameter("demail");
			String dpassword = request.getParameter("dpassword");
			
			Connection con = connectiondbDemo.getConnect();
			
			PreparedStatement ps = con.prepareStatement("select * from doctordetails where demail = ?");
			ps.setString (1, demail);
		
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				System.err.println("Account is Already Present...!! ");
				response.sendRedirect("error.html");
			}else {
				PreparedStatement ps1 = con.prepareStatement("insert into doctordetails values(?,?,?,?,?,?)");
				ps1.setInt(1, did);
				ps1.setString(2, dname);
				ps1.setString(3, specialization);
				ps1.setString(4, dcontact);
				ps1.setString(5, demail);
				ps1.setString(6, dpassword);

				int i = ps1.executeUpdate();
				
				if (i>0) {
					System.out.println("Account Added Successfully...!!");
					response.sendRedirect("successful.html");
				} else {
					System.err.println("Failed to Create Account...!");
					response.sendRedirect("error.html");
				}
			}
			
			}catch (Exception e) {
				
			System.out.println("Failed to Insert Data....!! ");
			}
	}

}
