package com.crowd.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crowd.Bean.EventBean;
import com.crowd.Implementation.CrowdImplementation;
import com.crowd.Interface.CrowdInterface;

/**
 * Servlet implementation class EventRegisterServlet
 */
@WebServlet("/EventRegisterServlet")
public class EventRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		System.out.println("Control inside the dopost method");
		String username = request.getParameter("pusername");
		String filename = request.getParameter("filename");
		String productname = request.getParameter("productname");
		String productprice = request.getParameter("productprice");
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		System.out.println(username+" "+filename+" "+productname+" "+productprice+" "+category+" "+description);
		
		EventBean ob = new EventBean();
		ob.setUsername(username);
		ob.setFilename(filename);
		ob.setProductname(productname);
		ob.setProductprice(productprice);
		ob.setCategory(category);
		ob.setDescription(description);
		
		CrowdInterface oi = new CrowdImplementation();
		int result = oi.eventregister(ob);
		System.out.println("The Value of result is: "+result);
		if(result!=0){
			response.sendRedirect("EventRegistered.jsp");
		}else{
			response.sendRedirect("Error.jsp");
		}
	}

}
