package com.crowd.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crowd.Database.DatabaseConnection;

/**
 * Servlet implementation class ContactUsServlet
 */
@WebServlet("/ContactUsServlet")
public class ContactUsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactUsServlet() {
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
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String message = request.getParameter("message");
		
		System.out.println(name+" "+email+" "+message);
		
		Connection con;
		con = DatabaseConnection.createConnection();
		int ans = 0;
		try{
			PreparedStatement ps = con.prepareStatement("INSERT INTO contact VALUES(?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, message);
			ans = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(ans == 1){
			response.sendRedirect("index.jsp");
		}else {
			response.sendRedirect("Error.jsp");
		}
		
	}

}
