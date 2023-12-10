package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect  {
	
	protected Connection connection;
	public Connection getConnection() { return connection; }

	private static String url = "jdbc:mysql://www.papademas.net:3307/510fp?autoReconnect=true&useSSL=false";
	private static String username = "fp510";
	private static String password = "510";

	public DBConnect() {
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.printlm("Successfully connected to database!!");
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
	}
     }
