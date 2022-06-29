package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	Connection con;
	String url="jdbc:mysql://localhost:3306/evalti";
	String user = "root";
	String pass = "";
	
	public Connection getConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			return con;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
}
