package com.crowd.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crowd.Bean.RegisterBean;
import com.crowd.Implementation.CrowdImplementation;
import com.crowd.Interface.CrowdInterface;

/**
 * Servlet implementation class OutsourcingRegisterServlet
 */
@WebServlet("/EventHosterRegisterServlet")
public class EventHosterRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventHosterRegisterServlet() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		System.out.println(name+" "+username+" "+password+" "+cpassword+" "+email+" "+address);
		
		if(password.equals(cpassword)){
			System.out.println("YES");
			RegisterBean rb = new RegisterBean();
			rb.setName(name);
			rb.setUsername(username);
			rb.setPassword(password);
			rb.setEmail(email);
			rb.setAddress(address);
			
			CrowdInterface oi = new CrowdImplementation();
			int i = oi.register(rb);
			System.out.println("The value of i is "+i);
			if(i == 1){
				response.sendRedirect("EventHosterLogin.jsp");
			}else{
				response.sendRedirect("Error.jsp");
			}
		}
	}

}
