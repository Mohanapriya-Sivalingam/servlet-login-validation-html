package serverlayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String DB_URL="jdbc:mysql://localhost:3306/myappdb";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root123";
	
	


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>Hello</h1>");
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
//		String cpassword = request.getParameter("cpassword");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //JDBC Driver Connection
			Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			String checkQuery = "select * from signup where email = ?";
			PreparedStatement checkpst = con.prepareStatement(checkQuery);
			checkpst.setString(1, email);
			ResultSet rs = checkpst.executeQuery(); //executeQuery is used for select sql query; it doesn't affect the database
			while(rs.next()) {
				out.println("<h3> Email already Registered! Try another email!");
			}
			String query = "insert into signup (name, email, password) values (?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, email);
			pst.setString(3, password);
			int result = pst.executeUpdate(); //executeUpdate is used for insert sql queies. it affect the database
			
			if(result>0) {
				out.println("<h2>Your Account Created Successfully!</h2>");
				
				
				response.sendRedirect("login.html");
				
			
			}
			else
				out.println("<h3>Signup Failed! Try Again....  </h3>");
			
			
			
			con.close();
			
			
			
		}
		catch(Exception e){
			e.printStackTrace(); //gives output in console
			out.println("<h3> Error Occured" + e.getMessage()+"</h3?");
		}
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.sendRedirect("signup.html");
	}


}
