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
 * Servlet implementation class PatientRegister
 */
public class PatientRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientRegister() {
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
			int pid = 0;
			String pname = request.getParameter("pname");
			String pcontact = request.getParameter("pcontact");
			String paddress = request.getParameter("paddress");
			String pemail = request.getParameter("pemail");
			String ppassword = request.getParameter("ppassword");
			
			Connection con = connectiondbDemo.getConnect();
			
			PreparedStatement ps = con.prepareStatement("select * from patientdetails where pemail = ?");
			ps.setString (1, pemail);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				System.err.println("Account is Already Present...!! ");
				response.sendRedirect("error.html");
			}else {
				PreparedStatement ps1 = con.prepareStatement("insert into patientdetails values(?,?,?,?,?,?)");
				ps1.setInt(1, pid);
				ps1.setString(2, pname);
				ps1.setString(3, pcontact);
				ps1.setString(4, paddress);
				ps1.setString(5, pemail);
				ps1.setString(6, ppassword);

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
