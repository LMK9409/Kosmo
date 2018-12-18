package com.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.SQLException;

public class Ch99DBManager {
	public Connection dbConn() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","kosmo","0000");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void dbClose(Connection conn,PreparedStatement pstmt,ResultSet rs) {
		try {
			if(rs!=null)rs.close(); 
			if(pstmt!=null)pstmt.close(); 			
			if(conn!=null)conn.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void dbClose(Connection conn,PreparedStatement pstmt) {
		try {
			if(pstmt!=null)pstmt.close(); 			
			if(conn!=null)conn.close(); 
		} catch (SQLException e) {e.printStackTrace();}
	}
	public void dbClose(Connection conn) {
		try {
					
			if(conn!=null)conn.close(); 
		} catch (SQLException e) {e.printStackTrace();}
	}
}
