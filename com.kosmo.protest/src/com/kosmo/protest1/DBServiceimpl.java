package com.kosmo.protest1;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DBServiceimpl implements DBService{
	public ArrayList<MemberVO> memberList(){
		JDBCManager db = new JDBCManager();
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn=db.dbConn();
			String sql="select * from member_VO";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				MemberVO mvo = new MemberVO();
				mvo.setMember_id(rs.getString("member_id"));
				mvo.setMember_pw(rs.getString("member_pw"));
				mvo.setMember_name(rs.getString("member_name"));
				mvo.setMember_email(rs.getString("member_email"));
				mvo.setMember_birth(rs.getInt("member_birth"));
				mvo.setMember_q(rs.getString("member_q"));
				mvo.setMember_a(rs.getString("member_a"));
				list.add(mvo);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			db.dbClose(conn, pstmt, rs);
		}
		return list;
	}
	public int memberInsert(String id,String pw,String name,String mail,String string,String q,String a) {
		JDBCManager db = new JDBCManager();
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int res=0;
		try {
			conn=db.dbConn();
			String sql="insert into member_vo values(?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,pw);
			pstmt.setString(3,name);
			pstmt.setString(4,mail);
			pstmt.setString(5,string);
			pstmt.setString(6,q);
			pstmt.setString(7,a);
			res=pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			db.dbClose(conn, pstmt, rs);
		}
		return res;
	}
	public ArrayList<RoomVO> roomList(){
		JDBCManager db = new JDBCManager();
		ArrayList<RoomVO> list = new ArrayList<RoomVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			conn=db.dbConn();
			String sql="select * from chat";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				RoomVO rvo = new RoomVO();
				rvo.setB_seq(rs.getInt("b_seq"));
				rvo.setB_title(rs.getString("b_title"));
				rvo.setMaster(rs.getString("master"));
				list.add(rvo);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			db.dbClose(conn, pstmt, rs);
		}
		return list;
	}
	public int roomInsert(String b_title,String master) {
		JDBCManager db = new JDBCManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int res=0;
		try {
			conn=db.dbConn();
			String sql="insert into chat values(b_seq.nextval, ?, ?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,b_title);
			pstmt.setString(2,master);
			res=pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			db.dbClose(conn, pstmt, rs);
		}
		return res;
	}
	public boolean idpwck(String pid,String ppw)
	{
		HashMap<String,String> map = new HashMap<String,String>();
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		boolean res=false;
		list=memberList();
		for(MemberVO i:list)
		{
			map.put(i.getMember_id(), i.getMember_pw());
		}
		if(map.get(pid).equals(ppw))
		{
			res=true;
		}
		return res;
	}
	public boolean idck(String pid)
	{
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		boolean res=true;
		list=memberList();
		for(MemberVO i:list)
		{
			if(i.getMember_id()==pid)
			{
				res=false;
			}
		}

		return res;
	}
	public boolean roomck(String ptitle)
	{
		ArrayList<RoomVO> list = new ArrayList<RoomVO>();
		boolean res=true;
		list=roomList();
		for(RoomVO i:list)
		{
			if(i.getB_title().equals(ptitle))
			{
				res=false;
			}
		}

		return res;
	}
	public int getBseq(String ptitle){
		JDBCManager db = new JDBCManager();
		int res=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			conn=db.dbConn();
			String sql="select b_seq from chat where b_title=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,ptitle);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				res=rs.getInt("b_seq");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			db.dbClose(conn, pstmt, rs);
		}
		return res;
	}
}

