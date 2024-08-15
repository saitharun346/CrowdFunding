package com.crowd.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crowd.Database.DatabaseConnection;

/**
 * Servlet implementation class ValidateForRegisterEventServlet
 */
@WebServlet("/ValidateForRegisterEventServlet")
public class ValidateForRegisterEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateForRegisterEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		

		String filename = request.getParameter("ffilename");
		String fileowner = request.getParameter("ffileowner");
		String status = request.getParameter("fstatus");
		
		
		
		String accept = "";
		try {
			Connection con = DatabaseConnection.createConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM fairpayment.filerequest where filename='"+filename+"' and fileowner='"+fileowner+"' and status='"+status+"'");
			System.out.println("ps:"+ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				accept = rs.getString("status");
			
				
				
			}
			System.out.print("Accept::"+accept);
			if(accept.equals("Accepted")){
				
				response.sendRedirect("EventRegister.jsp");
			}
			else{
				response.sendRedirect("Error.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
