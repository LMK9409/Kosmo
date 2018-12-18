package com.biz.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;


import com.biz.common.MyBatisFactory;
import com.biz.shop.ShopVO;

public class MemberDAO {
   /**
    * 회원 가입
    * @param mvo
    * @return
    */
   public int insert(MemberVO mvo) {
      SqlSession conn =null;
      int res = 0;
      try { 
         //singleton : factory instance의 중앙 관리 
         //factory instance : private static
         //public getFacoty()를 통해 가져감
         conn = MyBatisFactory.getFactory().openSession();
         res = conn.insert("userNameSpace.member_register", mvo);
         conn.commit();
      } finally {
         conn.close();
      }
      return res;
   }
   
   /**
    * 회원정보 수정
    * @param mvo
    * @return
    */
   public int update(MemberVO mvo) {
      SqlSession conn =null;
      int res = 0;
      try {
         conn = MyBatisFactory.getFactory().openSession();
         res = conn.update("userNameSpace.member_update", mvo);         
      } finally {
         conn.close();
      }
      return res;
   }
   
   
   /**
    * 회원 탈퇴 : USER_GUBUN='0' / USER_DEL='y'
    * @param userId
    * @return
    */
   public int update(String userId) {
      SqlSession conn =null;
      int res = 0;
      try {
         conn = MyBatisFactory.getFactory().openSession();
         res = conn.delete("userNameSpace.member_delete",userId);      
      } finally {
         conn.close();
      }
      return res;
   }
   
   /**
    * 회원 정보 수정을 위한 모든 정보 가져오기
    * @param userId
    * @return
    */
   public MemberVO select(String userId) {
      SqlSession conn =null;
      MemberVO mvo = new MemberVO();
      try {
         conn = MyBatisFactory.getFactory().openSession();
         mvo = conn.selectOne("userNameSpace.member", userId);   
      } finally {
         conn.close();
      }
      return mvo;
   }
   
   /**
    * 로그인 정보 가져오기
    * @param mvo
    * @return
    */
   public MemberVO select(MemberVO mvo) {
      SqlSession conn =null;
      MemberVO vo = new MemberVO();
      try {
         conn = MyBatisFactory.getFactory().openSession();
         vo = conn.selectOne("userNameSpace.login", mvo);
         conn.commit();
      } finally {
         conn.close();
      }
      return vo;
   }
}