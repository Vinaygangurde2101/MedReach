package com.vinay.MedReach;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vinay.meth.User;

import condb.connectiondbDemo;

/**
 * Servlet implementation class BookAppointment
 */
public class BookAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookAppointment() {
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
	    
	    try {
	        int aid = 0; 
	        String pid = User.getPid(); 
	        String did = request.getParameter("did");
	        String adate = request.getParameter("adate");
	        String atime = request.getParameter("atime");
	        String astatus = "Disagree";

	        Connection con = connectiondbDemo.getConnect();
	        PreparedStatement ps = con.prepareStatement("insert into appointments values (?,?,?,?,?,?)");
	        ps.setInt(1, aid);
	        ps.setString(2, pid);
	        ps.setString(3, did);
	        ps.setString(4, adate);  
	        ps.setString(5, atime);  
	        ps.setString(6, astatus);

	        int i = ps.executeUpdate();
	        if (i > 0) {
	            
	            User.setAid(String.valueOf(aid)); 
	            System.out.println("Appointment Added Successfully!");
	            response.sendRedirect("bookSuccessful.jsp?pid=" + pid + "&did=" + did + "&adate=" + adate + "&atime=" + atime);

	        } else {
	            System.out.println("Appointment Not Added!");
	            response.sendRedirect("error.html");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
