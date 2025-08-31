package com.vinay.MedReach;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import condb.connectiondbDemo;

/**
 * Servlet implementation class ChangePassPet
 */
public class ChangePassPet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassPet() {
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
		
		
		String pemail = request.getParameter("email");
		String oldpass = request.getParameter("oldpass");
		String newpass = request.getParameter("newpass");

		try {
			Connection con = connectiondbDemo.getConnect();

			PreparedStatement ps1 = con.prepareStatement("SELECT ppassword FROM patientdetails WHERE pemail = ?");
			ps1.setString(1, pemail);
			ResultSet rs = ps1.executeQuery();

			if (rs.next()) {
				String currentPass = rs.getString("ppassword");
				if (currentPass.equals(oldpass)) {
					PreparedStatement ps2 = con.prepareStatement("UPDATE patientdetails SET ppassword = ? WHERE pemail = ?");
					ps2.setString(1, newpass);
					ps2.setString(2, pemail);
					int i = ps2.executeUpdate();
					if (i > 0) {
						response.sendRedirect("patientDash.html?msg=passchanged");
					} else {
						response.sendRedirect("changePassword.jsp?error=updatefail");
					}
				} else {
					response.sendRedirect("changePassword.jsp?error=wrongpass");
				}
			} else {
				response.sendRedirect("changePassword.jsp?error=noemail");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("changePassword.jsp?error=exception");
		}
	}
}
