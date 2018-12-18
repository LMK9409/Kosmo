package com.biz.shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.biz.common.MyBatisFactory;


public class ShopDAO {

   public ShopVO selectShopInfo(int sseq) {
      SqlSession conn =null;
      ShopVO svo = new ShopVO();
      try {
         conn = MyBatisFactory.getFactory().openSession();
         svo = conn.selectOne("shop_detail", sseq);
         conn.commit();
      } catch (Exception e) {
         conn.rollback();
         e.printStackTrace();
      } finally {
         conn.close();
      }
      return svo;
   }
   
   public ArrayList<ShopPicVO> selectShopPic(int sseq) {
      SqlSession conn =null;
      ArrayList<ShopPicVO> list = new ArrayList<ShopPicVO>(); 
      try {
         conn = MyBatisFactory.getFactory().openSession();
         list = (ArrayList)conn.selectList("shop_detailPic", sseq);
         conn.commit();
      } catch (Exception e) {
         conn.rollback();
         e.printStackTrace();
      } finally {
         conn.close();
      }
      return list;
   }
   
   public ArrayList<ShopVO> selectTopN(ShopVO vo) {
	   SqlSession conn =null;
      ArrayList<ShopVO> list = new ArrayList<ShopVO>(); 
      try {
    	  conn= MyBatisFactory.getFactory().openSession();
         list = (ArrayList)conn.selectList("shop_topN", vo);
         conn.commit();
      } catch (Exception e) {
         conn.rollback();
         e.printStackTrace();
      } finally {
         conn.close();
      }
      return list;
   }
   
   public ArrayList<ShopVO> select(ShopVO vo) {
      SqlSession conn =null;
      ArrayList list = new ArrayList(); 
      try {
         conn = MyBatisFactory.getFactory().openSession();
         System.out.println(vo.getLat());
         list = (ArrayList)conn.selectList("shopNameSpace.shop_all", vo);
         System.out.println(list);
         conn.commit();
      } catch (Exception e) {
         conn.rollback();
         e.printStackTrace();
      } finally {
         conn.close();
      }
      return list;
   }
   
   public ArrayList<ShopVO> selectAll(ShopVO vo) {
	      SqlSession conn =null;
	      ArrayList list = new ArrayList(); 
	      try {
	         conn = MyBatisFactory.getFactory().openSession();
	         System.out.println(vo.getLat());
	         list = (ArrayList)conn.selectList("shopNameSpace.shop_all2", vo);
	         System.out.println(list);
	         conn.commit();
	      } catch (Exception e) {
	         conn.rollback();
	         e.printStackTrace();
	      } finally {
	         conn.close();
	      }
	      return list;
	   }
   
   public int insertShopInfo(ShopVO svo) {
      SqlSession conn =null;
      ArrayList<ShopVO> list = new ArrayList<ShopVO>(); 
      int res = 0;
      int res2 = 0;
      try {
         conn = MyBatisFactory.getFactory().openSession();;
         svo.setSseq(conn.selectOne("shop_nextVal"));
         conn.commit();
         System.out.println(svo.getSseq());
         res = conn.insert("shop_register", svo);
         System.out.println("res:"+res);
         if(svo.getPlist().size()>0) {
            for(ShopPicVO pvo : svo.getPlist()) {
               res = conn.insert("shopPic_register", pvo);
            }
         }
         conn.commit();
      } catch (Exception e) {
         conn.rollback();
         e.printStackTrace();
      } finally {
         conn.close();
      }
      return res+res2;
   }
   
   //------------------------------------------------------
   public int selectNextSseq(SqlSession PRMCONN) {
      int next_sseq = 0;
      try {
         next_sseq = PRMCONN.selectOne("shop_nextVal");
         PRMCONN.commit();
      } catch (Exception e) {
         PRMCONN.rollback();
         e.printStackTrace();
      }
      return next_sseq;
   }
            
   public int   insertShopInfo(ShopVO svo , SqlSession PRMCONN) {
      int res = 0;
      try {
         res = PRMCONN.insert("shop_register", svo);
         PRMCONN.commit();
      } catch (Exception e) {
         PRMCONN.rollback();
         e.printStackTrace();
      }
      return res;
   }
   
   public int   insertShopPic(ShopPicVO pvo, SqlSession PRMCONN) {
      int res = 0;
      try {
         res = PRMCONN.insert("shopPic_register", pvo);
         PRMCONN.commit();
      } catch (Exception e) {
         PRMCONN.rollback();
         e.printStackTrace();
      } 
      return res;
   }
   //------------------------------------------------------
   
   public int   insertReply(ReplyVO rvo) {
	      int res = 0;
	      SqlSession conn=null;
	      try {
	    	  conn = MyBatisFactory.getFactory().openSession();
	         res = conn.insert("reply_insert", rvo);
	         conn.commit();
	      } catch (Exception e) {
	         conn.rollback();
	         e.printStackTrace();
	      }
	      return res;
	   }
   public ArrayList<ReplyVO> selectReply(ReplyVO rvo) {
	      SqlSession conn =null;
	      ArrayList list = new ArrayList(); 
	      try {
	         conn = MyBatisFactory.getFactory().openSession();
	         
	         list = (ArrayList)conn.selectList("shopNameSpace.reply_select", rvo);
	         conn.commit();
	      } catch (Exception e) {
	         conn.rollback();
	         e.printStackTrace();
	      } finally {
	         conn.close();
	      }
	      return list;
	   }
}