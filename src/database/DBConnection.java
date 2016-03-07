package database;
/**
 * DBConnection
 * Description: create and close connection
 * 
 * @version 02/05/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
//STEP 1. Import required packages
import java.sql.*;

public class DBConnection {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/car";

   //  Database credentials
	static final String USER = "root";
	static final String PASS = "root";
	
	// connection and statement
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement statement;
	
	public Connection getConn() {
		return conn;
	}

	public boolean createConnection() {
		boolean success = true;
		try {
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException | SQLException e) {
			success = false;
			e.printStackTrace();
		}
		return success;
	}
	
	public void exeQuery(String query) {
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void exeUpdate(String sql) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void exeUpdate(String sql, String[] arr) {
		try {
			statement = conn.prepareStatement(sql);
			for (int i = 0; i <arr.length; i++) {
				statement.setString(i+1, arr[i]);
			}       
		    statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int showAll(String tableName, String[] arr) {
		System.out.println("=====================================================");
		System.out.println("data in " + tableName + " table is dislayed as below: ");
		ResultSet rs = null;
		int sum = 0;
		try {
			String query = "SELECT * FROM " + tableName;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < arr.length; i++) {
					sb.append(arr[i]);
					sb.append(": ");
					sb.append(rs.getString(arr[i]));
					sb.append(";  ");
				}
				System.out.println(sb.toString());
				sum++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("----------------------------------------------");
		return sum;
	}
	
	public boolean closeConnection() {
		boolean success = true;
			
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e1) {
			success = false;
			e1.printStackTrace();
		}
		
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}	
		
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}
		
		return success;
	}
	
//	public static void main(String[] args) {
//		DBConnection db = new DBConnection();
//		if (db.createConnection()) {
//			String query = "CREATE DATABASE STUDENTS";
//			db.exeUpdate(query);
//			query = "USE STUDENTS";
//			db.exeUpdate(query);
//			query = "CREATE TABLE profile(status varchar(36), loggedin BOOLEAN, username VARCHAR(255), statusTime TEXT)";
//			db.exeUpdate(query);
//			query = "insert into profile (status, loggedin, username, statusTime) values ()";
//			db.exeQuery(query);
//			db.closeConnection();
//		}
//	}

}
