package com.vinay.MedReach;

import java.io.IOException;
import java.sql.*;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vinay.meth.User;

import condb.connectiondbDemo;

/**
 * Servlet implementation class AddPrescriptions
 */
public class AddPrescriptions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPrescriptions() {
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
			String aid = request.getParameter("aid");
			String demail = User.getDemail();  
			String presc = request.getParameter("presc");
			String pdate = request.getParameter("pdate");

			Connection con = connectiondbDemo.getConnect();

			// Get doctor ID from email
			PreparedStatement ps1 = con.prepareStatement("SELECT did FROM doctordetails WHERE demail = ?");
			ps1.setString(1, demail);
			ResultSet rs1 = ps1.executeQuery();

			String did = null;
			if (rs1.next()) {
				did = rs1.getString("did");
			}

			PreparedStatement ps = con.prepareStatement("INSERT INTO prescription (aid, did, presc, pdate) VALUES (?, ?, ?, ?)");
			ps.setString(1, aid);
			ps.setString(2, did);
			ps.setString(3, presc);
			ps.setString(4, pdate);

			int i = ps.executeUpdate();

			if (i > 0) {
				request.setAttribute("aid", aid);
				request.setAttribute("did", did);
				request.setAttribute("presc", presc);
				request.setAttribute("pdate", pdate);
				request.getRequestDispatcher("prescriptionSuccess.jsp").forward(request, response);
			} else {
				response.sendRedirect("error.html");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
	}
}