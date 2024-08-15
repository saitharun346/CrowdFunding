package com.crowd.Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.crowd.Implementation.CrowdImplementation;
import com.crowd.Interface.CrowdInterface;

/**
 * Servlet implementation class Download1
 */
@WebServlet("/Download1")
public class Download1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String values = request.getParameter("check");
		System.out.println("The values is"+values);
		
		String filename = request.getParameter("Filename");
		System.out.println("The file name is:" +filename);
		
		String filekey = request.getParameter("filekey");
		System.out.println("The file key is:" +filekey);
		
		CrowdInterface dii = new CrowdImplementation();
		String filekey1 = dii.getpublickey(filename);
		System.out.println("The filekey111"+filekey1);
		
//		JOptionPane jpane = new JOptionPane("Enter the secret key");
//		JDialog jdialog = jpane.createDialog("Alert");
//		jdialog.setAlwaysOnTop(true);
//		jdialog.show();
//		
//		String typingprivatekey  = JOptionPane.showInputDialog("Enter the secret key");
		if(filekey1.equalsIgnoreCase(filekey)) {
			System.out.println("Matched");
			PrintWriter out = null;
			
			try {
				out = response.getWriter();
				String filepath = "C:\\Users\\ST-0009\\workspace5\\Crowd_Funding\\WebContent\\LOCAL\\";
				String filename1 = request.getParameter("Filename");
				System.out.println("The file name is:"+filename1);
				
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", "attachment; filename=\"" +filename1+ "\"");
				java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filepath+filename1);
				
				int i;
				while((i=fileInputStream.read()) != -1) {
					out.write(i);
				}
				fileInputStream.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			

		} else {
			System.out.println("Failed");
			JOptionPane.showMessageDialog(null, "Sorry your key is wrong");
			response.sendRedirect("Error.jsp");
		}
		
	
		
	}

}
