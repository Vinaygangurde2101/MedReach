package com.vinay.MedReach;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vinay.meth.User;

import condb.connectiondbDemo;

/**
 * Servlet implementation class DoctorLog
 */
public class DoctorLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorLog() {
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
			
			String demail = request.getParameter("demail");
			String dpassword = request.getParameter("dpassword");
			
			Connection con = connectiondbDemo.getConnect();
			PreparedStatement ps = con.prepareStatement("select * from doctordetails where demail = ? and  dpassword = ?");
			ps.setString(1, demail);
			ps.setString(2, dpassword);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			{
				HttpSession session = request.getSession();
				session.setAttribute("did", rs.getInt("did"));  

				User.setDemail(rs.getString("demail"));
				response.sendRedirect("doctorDash.html");
			}else {
				response.sendRedirect("doctorsLogin.html");
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}