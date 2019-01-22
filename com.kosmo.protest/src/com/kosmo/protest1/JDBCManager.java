package com.kosmo.protest1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.SQLException;

public class JDBCManager {
	public Connection dbConn() {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn=DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.106:1521:XE","kosmo","0000");
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
}
