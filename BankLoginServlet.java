package com.crowd.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crowd.Implementation.CrowdImplementation;
import com.crowd.Interface.CrowdInterface;

/**
 * Servlet implementation class BankLoginServlet
 */
@WebServlet("/BankLoginServlet")
public class BankLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankLoginServlet() {
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
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println(email+" "+password);
		
		CrowdInterface oi = new CrowdImplementation();
		int result = oi.banklogin(email, password);
		System.out.println("The value of result is: "+result);
		if(result == 1){
			HttpSession session = request.getSession();
			session.setAttribute("bankusername", email);
			response.sendRedirect("Deposit.jsp");
		}else{
			response.sendRedirect("Error.jsp");
		}
	}

}
