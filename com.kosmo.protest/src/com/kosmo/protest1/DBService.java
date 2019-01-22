package com.kosmo.protest1;

import java.util.ArrayList;

public interface DBService {
	public ArrayList<MemberVO> memberList();
	public int memberInsert(String id,String pw,String name,String mail,String birth,String q,String a);
	public ArrayList<RoomVO> roomList();
	public int roomInsert(String b_title,String master);
	public boolean idpwck(String pid,String ppw);
	public boolean idck(String pid);
	public boolean roomck(String ptitle);
	public int getBseq(String ptitle);
}
