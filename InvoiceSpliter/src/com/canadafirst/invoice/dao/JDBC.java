package com.canadafirst.invoice.dao;

import java.sql.*;

public class JDBC {
	private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/canadafirst";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "811024";
	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	static{
		try{
			Class.forName(DRIVERCLASS).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		Connection conn = threadLocal.get();
		if (conn == null){
			try{
				conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				threadLocal.set(conn);
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public static boolean closeConnection(){
		boolean isClosed= true;
		Connection conn = threadLocal.get();
		threadLocal.set(null);
		if (conn != null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return isClosed;
	}
}
