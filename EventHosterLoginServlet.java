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
 * Servlet implementation class OutsourcingLoginServlet
 */
@WebServlet("/EventHosterLoginServlet")
public class EventHosterLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventHosterLoginServlet() {
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
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username+""+password);
		
		CrowdInterface oi = new CrowdImplementation();
		int i = oi.login(username, password);
		System.out.println("The value of i is "+i);
		if(i == 1){
			HttpSession session = request.getSession();
			session.setAttribute("dprovider", username);
			response.sendRedirect("EventHosterHome.jsp");
		}else {
			response.sendRedirect("Error.jsp");
		}
	}

}
