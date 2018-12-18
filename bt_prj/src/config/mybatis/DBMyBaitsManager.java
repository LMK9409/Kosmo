package config.mybatis;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.biz.member.MemberVO;



public class DBMyBaitsManager {
	public SqlSession mbConn() {
		String path2 = "config/mybatis/config-mybatis.xml";
		Reader reader;
		SqlSessionFactory factory = null;
		SqlSession conn = null;
		try {
			reader = Resources.getResourceAsReader(path2);
			factory = new SqlSessionFactoryBuilder().build(reader);
			conn = factory.openSession();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void mbClose(SqlSession conn) {
		if(conn != null) conn.close();
	}

	public static void main(String[] args) {
		DBMyBaitsManager mb = new DBMyBaitsManager();
		SqlSession conn =mb.mbConn();

		MemberVO vo = new MemberVO();
		vo.setUserId("admin");
		vo.setUserPw("111");
		MemberVO res = new MemberVO();
		
		res=conn.selectOne("login",vo);
		System.out.println(res.getUserName());
//		memberRegister
		MemberVO vo2 = new MemberVO();
		vo2.setUserId("asdzxc");
		vo2.setUserPw("123");
		vo2.setUserName("이문겨");
		int res2=conn.insert("memberRegister",vo2);
		System.out.println(res2);
		
		vo.setUserId("kim");
	      vo.setUserPw("333");
	      vo.setUserName("김씨");
	      vo.setUserEmail("ssstyuik222@daum.net");
	      
	      
	      int res3 = conn.update("member_update",vo);
	      System.out.println(res3);
	      
	      vo.setUserId("kim");
	      int res4 = conn.update("member_delete", vo);
	      System.out.println("삭제" + " : " + res4);
	     
	      List list = conn.selectList("member_list");
	      for (int i = 0; i < list.size(); i++) {
	         MemberVO mvo = (MemberVO) list.get(i);
	         System.out.println(mvo.getUserId() + " : " + mvo.getUserPw() + " : " + mvo.getUserEmail());
	      }

	     conn.rollback();
		mb.mbClose(conn);
		if(conn==null) {System.out.println("nop!");}else {System.out.println("yeah!");}
		//DI Call Test
//		GenericXmlApplicationContext ctx
//		 = new GenericXmlApplicationContext("classpath:test.xml");
//		TestDAO dao = (TestDAO)ctx.getBean("aaaa");
//		ArrayList<BoardVO> list = dao.boardList(1, 10);
//		System.out.println(list.size() + "건 출력");
		
		
	}

}
