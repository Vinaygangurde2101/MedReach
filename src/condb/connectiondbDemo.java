package condb;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectiondbDemo {
	
	 static Connection con = null;
	 public static Connection getConnect(){
		 
		 try {
		 Class.forName("com.mysql.jdbc.Driver");
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/meddb","root","");
		 
		 }catch (Exception e) {
			System.err.println("Failed to Connect!.........");
			e.printStackTrace();
		}
		 return con;
		
	}

}
