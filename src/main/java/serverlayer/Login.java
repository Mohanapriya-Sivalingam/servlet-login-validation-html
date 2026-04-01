package serverlayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DB_URL="jdbc:mysql://localhost:3306/myappdb";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root123";
	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //JDBC Driver Connection
			Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String query = "select * from signup where email=? and password=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
			    String name = rs.getString("name");
			    out.println("<h3>You are Successfully Logged in! Welcome Back " + name + "!</h3>");
			} 
			else {
			    out.println("<h3>Invalid Email or Password! Try Again.</h3>");
			}
			rs.close();
			pst.close();
			con.close();
		
		}
		catch(Exception e) {
			e.printStackTrace();
			out.println("<h3>Error in Login!" + e.getMessage()+"</h3>");
		}
		
		
	}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


}
