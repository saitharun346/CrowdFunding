package com.crowd.Servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.crowd.Database.DatabaseConnection;

/**
 * Servlet implementation class AddEventServlet
 */
@WebServlet("/AddEventServlet")
public class AddEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "C:/Users/Mugilan/workspace/Crowd_Funding/WebContent/pimages/";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEventServlet() {
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
		System.out.println("Inside The doPost Method");
		
		if(ServletFileUpload.isMultipartContent(request)){
			try{
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				String productname = null;
				String productprice = null;
				String category = null;
				String filename = null;
				String description = null;
				
				for(FileItem item : multiparts){
					if(!item.isFormField()){
						
						FileItem pname = (FileItem) multiparts.get(0);
						productname = pname.getString();
						System.out.println("The Product name is "+productname);
						
						FileItem pprice = (FileItem) multiparts.get(1);
						productprice = pprice.getString();
						System.out.println("The Product price is "+productprice);
						
						FileItem pcat = (FileItem) multiparts.get(2);
						category = pcat.getString();
						System.out.println("The product category is "+category);
						
						filename = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator + filename));
						System.out.println("The product File name is "+filename);
						
						FileItem pdes = (FileItem) multiparts.get(4);
						description = pdes.getString();
						System.out.println("The Product Description is "+description);
					}
				}
				try{
					int id = 0;
					
					String imagepath = UPLOAD_DIRECTORY + filename;
					Connection con;
					Class.forName("com.mysql.jdbc.Driver");
					con = DatabaseConnection.createConnection();
					PreparedStatement ps = con.prepareStatement("INSERT INTO event VALUES (?,?,?,?,?,?,?)");
					ps.setInt(1, id);
					ps.setString(2, productname);
					ps.setString(3, productprice);
					ps.setString(4, category);
					ps.setString(5, filename);
					ps.setString(6, description);
					ps.setString(7, imagepath);
					
					ps.executeUpdate();
				}catch(Exception e){
					e.printStackTrace();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			response.sendRedirect("EventAddedSuccess.jsp");
		}else {
			response.sendRedirect("Error.jsp");
		}
	}

}
