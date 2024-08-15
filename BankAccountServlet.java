package com.crowd.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crowd.Bean.BankAccountBean;
import com.crowd.Implementation.CrowdImplementation;
import com.crowd.Interface.CrowdInterface;



/**
 * Servlet implementation class BankAccountServlet
 */
@WebServlet("/BankAccountServlet")
public class BankAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankAccountServlet() {
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
		String number = request.getParameter("number");
		String address = request.getParameter("address");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String pincode = request.getParameter("pincode");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String bankname = request.getParameter("bankname");
		String amount = request.getParameter("amount");
		String accountnumber = request.getParameter("accountnumber");
		
		System.out.println(name+" "+username+" "+password+" "+cpassword+" "+email+" "+number+" "+address+" "+state+" "+country+" "
		+" "+pincode+" "+age+" "+gender+" "+bankname+" "+amount+" "+accountnumber);
		
		if(password.equals(cpassword)){
			System.out.println("YES");
			
			BankAccountBean ab = new BankAccountBean();
			ab.setName(name);
			ab.setUsername(username);
			ab.setPasswrord(password);
			ab.setEmail(email);
			ab.setNumber(number);
			ab.setAddress(address);
			ab.setState(state);
			ab.setCountry(country);
			ab.setPincode(pincode);
			ab.setAge(age);
			ab.setGender(gender);
			ab.setBankname(bankname);
			ab.setAmount(amount);
			ab.setAccountnumber(accountnumber);
			
			CrowdInterface oi = new CrowdImplementation();
			int result = oi.bankaccount(ab);
			System.out.println("The value of result is: "+result);
			if(result == 1){
				response.sendRedirect("AccountCreated.jsp");
			}else{
				response.sendRedirect("Error.jsp");
			}
			
		}
		
	}

}
