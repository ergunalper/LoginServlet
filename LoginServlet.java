import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	
	  
	 
	 String dbDriver = "com.mysql.cj.jdbc.Driver"; 
     String dbURL = "jdbc:mysql://localhost/"; 
     // Database name to access 
     String dbName = "students"; 
     String dbUsername = "root"; 
     String dbPassword = ""; 

     
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
		
		
		
		req.setCharacterEncoding("UTF-8"); // TÜRKÇE  YAPIYOR
		resp.setContentType("text/html; charset=UTF-8");//TÜRKÇE  YAPIYOR


		// Formdan gönderilen verileri al

		String firstName = req.getParameter("firstName");

		String lastName = req.getParameter("lastName");

		int age = Integer.parseInt(req.getParameter("age"));

		String email = req.getParameter("email");

		String password = req.getParameter("password");

		Connection connection = null;

		PreparedStatement statement = null;
		ResultSet resultSet;

		try {

		Class.forName(dbDriver); 
		connection = DriverManager.getConnection(dbURL + dbName, 
                dbUsername,  
                dbPassword); 

		// Öğrenci bilgilerini veritabanına ekle

		String sql = "INSERT INTO students (firstName, lastName, age, email, password) VALUES (?, ?, ?, ?, ?)";

		statement = connection.prepareStatement(sql);

		statement.setString(1, firstName);

		statement.setString(2, lastName);

		statement.setInt(3, age);

		statement.setString(4, email);

		statement.setString(5, password);

		statement.executeUpdate();

		System.out.println("Kayıt eklendi");
		
		 resp.setContentType("text/html");
		 PrintWriter out = resp.getWriter(); //Ekrana Yazdırma 
		 out.println("Kayıt eklendi :)");

		} catch (Exception e) {

	

		e.printStackTrace();
		System.out.println("Kayıt olmadı, Bir Sorun VAR!!!");

		}
		
		
	}
	
	
	
}
