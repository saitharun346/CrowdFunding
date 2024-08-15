package com.crowd.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crowd.Database.*;

/**
 * Servlet implementation class DepositServlet
 */
@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositServlet() {
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
		
		String balance = request.getParameter("balance");
		String email = request.getParameter("email");
		String accountnumber = request.getParameter("accountnumber");
		String depositamt = request.getParameter("depositamt");
		int depositamt2 = Integer.parseInt(depositamt);
		int depositamt3 = Integer.parseInt(balance);
		int depositamt1 = depositamt3 + depositamt2;
		
		System.out.println("THe available Balance "+balance);
		System.out.println("THe value of deposit amt:  "+depositamt2);
		System.out.println("User Email and Account Number "+email+" "+accountnumber);
		System.out.println("The deposited amount is: "+depositamt1);
		
		
		Connection con;
		int ans = 0;
		try{
			con = DatabaseConnection.createConnection();
			PreparedStatement ps = con.prepareStatement("update bankaccount set amount='"+depositamt1+"' where email='"+email+"'and accountnumber='"+accountnumber+"'");
			ans = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(ans == 1){
			response.sendRedirect("DepositSuccess.jsp");
		}else{
			response.sendRedirect("Error.jsp");
		}
		
	}

}
