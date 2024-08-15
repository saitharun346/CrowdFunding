package com.crowd.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crowd.Blockchain.Block;
import com.crowd.Blockchain.NoobChain;
import com.crowd.Database.*;
import com.crowd.Database.DatabaseConnection;
/**
 * Servlet implementation class PaymentProcessServlet
 */
@WebServlet("/PaymentProcessServlet")
public class PaymentProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentProcessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String bankusername = session.getAttribute("bankusername").toString();
		System.out.println("Bank User Name For Send OTP Through E-Mail: "+bankusername);
		String pusername = request.getParameter("pusername");
		String filename = request.getParameter("filename");
		String productname = request.getParameter("productname");
		
		String productprice = request.getParameter("productprice");
		int productprice1 = Integer.parseInt(productprice);
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		
		System.out.println(pusername+" "+filename+" "+productname+" "+productprice+" "+category+" "+description);
		
		//SendMail mailSender;
        //mailSender = new SendMail(rs.getString("email"),"Shamir's Secret key Verification" ,"Outside the network Secret key :   "+filekey+"");
                                            
		Connection con;
		int ans = 0;
		int result = 0;
		int ans1 = 0;
		String username = null;
		String email = null;
		String bankname = null;
		String accountnumber = null;
		String amount = null;
		
		String number = null;
		try{
			con = DatabaseConnection.createConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from bankaccount where email='"+bankusername+"'");
			while(rs.next()){
				username = rs.getString("username");
				email = rs.getString("email");
				bankname = rs.getString("bankname");
				accountnumber = rs.getString("accountnumber");
				amount = rs.getString("amount");
				number = rs.getString("number");
			}
			
			//PreparedStatement ps = con.prepareStatement("INSERT INTO paymentprocess VALUES()");
		}catch(Exception e){
			e.printStackTrace();
		}
		int amount1 = Integer.parseInt(amount);
		if(amount1<=productprice1 || amount1!=0){
			
			try{
				con = DatabaseConnection.createConnection();
				PreparedStatement ps = con.prepareStatement("INSERT INTO paymentprocess VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, pusername);
				ps.setString(2, filename);
				ps.setString(3, productname);
				ps.setString(4, productprice);
				ps.setString(5, category);
				ps.setString(6, description);
				ps.setString(7, username);
				ps.setString(8, email);
				ps.setString(9, bankname);
				ps.setString(10, accountnumber);
				ps.setString(11, amount);
				ps.setString(12, number);
				//ps.setInt(13, code);
				ans = ps.executeUpdate();
				
				
				int newbalance = amount1 - productprice1;
				
				if(ans>0 && newbalance>0){
					response.sendRedirect("PaymentCompleted.jsp");
				}else if(newbalance<=0){
					response.sendRedirect("InsufficientBalance.jsp");
				}
				
				System.out.println("The Balance After Payment "+newbalance);
				
				try{
					
					
					con = DatabaseConnection.createConnection();
					PreparedStatement ps1 = con.prepareStatement("update bankaccount set amount='"+newbalance+"' where email='"+email+"'and accountnumber='"+accountnumber+"'");
					result = ps1.executeUpdate();
					
				}catch(Exception e){
					e.printStackTrace();
				}
			
				
				ArrayList<Block> blockchain = new ArrayList<Block>();
				
				
				String[] packetdata = new String[6];
				packetdata[0] = productname;
				packetdata[1] = productprice;
				packetdata[2] = bankname;
				packetdata[3] = accountnumber;
				packetdata[4] = amount;
				packetdata[5] = description;

				NoobChain nc = new NoobChain();
				
					blockchain = nc.doblockchain(packetdata);
				
				
				
				try {
				    con = DatabaseConnection.createConnection();
					PreparedStatement ps2 = con.prepareStatement("INSERT INTO blockchain(requestby,block1,block2,block3,block4,block5,block6) VALUES(?,?,?,?,?,?,?)");
					ps2.setString(1, pusername);
					ps2.setString(2, blockchain.get(0).hash);
					ps2.setString(3, blockchain.get(1).hash);
					ps2.setString(4, blockchain.get(2).hash);
					ps2.setString(5, blockchain.get(3).hash);
					ps2.setString(6, blockchain.get(4).hash);
					ps2.setString(7, blockchain.get(5).hash);
						ans1 = ps2.executeUpdate();
						//con.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}

			
		}else if(amount1<productprice1){
			response.sendRedirect("InsufficientBalance.jsp");
		}else if(amount1>productprice1){
			response.sendRedirect("InsufficientBalance.jsp");
		}
		

		
	}

}
