package com.biz.shop;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.ibatis.session.SqlSession;

import com.biz.common.MyBatisFactory;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.Part;

@WebServlet("/shop_insert")
public class ShopInsertServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      ShopDAO dao = new ShopDAO();   
      ShopVO svo = new ShopVO();
      ArrayList<ShopPicVO> pvolist = new ArrayList<ShopPicVO>();
      ShopPicVO pvo = new ShopPicVO();
      SqlSession conn = MyBatisFactory.getFactory().openSession();
      String sname = null;
      double lat=0;
      double lng=0;
      String placename=null;
      String sinfo=null;
      
//      boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//      if (isMultipart) {
//         FileItemFactory factory = new DiskFileItemFactory();
//         ServletFileUpload upload = new ServletFileUpload(factory);
//         try {
//            List items = upload.parseRequest(request);
//            System.out.println(items);
//            Iterator iterator = items.iterator();
//            while (iterator.hasNext()) {
//               FileItem item = (FileItem) iterator.next();
//               if (!item.isFormField()) {
//                  String fieldName = item.getFieldName();
//                  String fileName = item.getName();
//                  
//                  System.out.println(fieldName + "---" + fileName);
//                  
//                  //String root = getServletContext().getRealPath("/");
//                  File path = new File("c:/uploads");
//                  if (!path.exists()) {
//                     boolean status = path.mkdirs();
//                  }
//
//                  File uploadedFile = new File(path + "/" + fileName);
//                  System.out.println(uploadedFile.getAbsolutePath());
//                  item.write(uploadedFile);
//               }
//            }
//         } catch (FileUploadException e) {
//            e.printStackTrace();
//         } catch (Exception e) {
//            e.printStackTrace();
//         }
//      }
      
      try {
         String saveDirectory = "C:/uploads";
         int maxPostSize = 1000000;
         String encoding = "UTF-8";
         FileRenamePolicy policy = new DefaultFileRenamePolicy();
         MultipartRequest mrequest = null;
         try {
            mrequest = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
            //1. 파일 copy : in/output stream
            //2. 중복파일 rename         
         } catch (Exception e) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('파일 첨부 문제 발생 잠시 후 다시 시도 해주세요.');</script>");
            //redirect
         }
         sname = mrequest.getParameter("sname");
         lat = Double.parseDouble(mrequest.getParameter("lat"));
         placename = mrequest.getParameter("placename");
         sinfo = mrequest.getParameter("sinfo");   
      
         //-------------------------
         //*file multipul
         //--------------------------
         //          MultipartParser parser = new MultipartParser(request, 1024 * 1024 * 10, false, false, "UTF-8"); 
         //          
         //            Part part= null;
         //            while ((part = parser.readNextPart()) != null) {
         //               String str = part.getName();
         //               System.out.println(str);
         //                if (part.isFile()) {
         //                    FilePart fPart = (FilePart) part;  
         //                    String name = fPart.getFileName();
         //                    if (name != null) {
         //                        long fileSize = fPart.writeTo(new File("c:/uploads"));
         //                        System.out.println(fPart.getFilePath() + "," + fileSize );
         //                    } else {
         //                        System.out.println("error");
         //                    }
         //                }
         //            }// end while 


         //------------file-----------
         //pname1,pname2
         /*File pfile = mrequest.getFile("pname");
         String filePname = pfile.getName(); //File Object 이용한 파일명
         long attachFileSize = pfile.length(); //File Size
          */

                  Enumeration formName = mrequest.getFileNames();
                  while (formName.hasMoreElements()) {
                     String fnames = (String)formName.nextElement();
                     String origpnames = mrequest.getOriginalFileName(fnames);
                     System.out.println(origpnames);
                     
                     if(origpnames != null) {            
                        String sysnames = mrequest.getFilesystemName(fnames);
                        File pfiles = mrequest.getFile(fnames);
                        long attachFileSizes = pfiles.length(); //File Size
                        
                        pvo.setPname(origpnames);
                        pvo.setSysname(sysnames);
                        if(pvolist.size() == 0) {
                           pvo.setPchk_yn("y");               
                        }else {
                           pvo.setPchk_yn("n");
                        }
                        pvolist.add(pvo);
                     }
                  }
                  mrequest.getContentType("pname");
         //파일 확장자 처리
//                  String attachFileExt ="jpg";
//                  if(origpnames.lastIndexOf(".") != -1) {
//                     attachFileExt = origpnames.substring(origpnames.lastIndexOf(".")+1);
//                  }
//                  
//                  if(!attachFileExt.toUpperCase().equals("JPG") &&
//                        !attachFileExt.toUpperCase().equals("PNG") &&
//                        !attachFileExt.toUpperCase().equals("GIF") &&
//                        !attachFileExt.toUpperCase().equals("JPEG")) {
//                     response.setContentType("text/html; charset=UTF-8");
//                     PrintWriter out = response.getWriter();
//                     out.println("<script>alert('이미지 파일만 첨부');</script>");
//                     //response.sendRedirect("shop_form.jsp");
//                  }
         
   
         
         
         /*try {
            conn.setAutoCommit(false);
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         } // 사용자가 임의로 커밋하겠다.*/
                  
         
         //conn.rollback(); 정상입력이 안된경우 되돌리기 위해서 ...
         //--------------------------------------------
         // 1. CURRVAL+1 SSEQ 가져오기
         //--------------------------------------------
         int next_sseq = dao.selectNextSseq(conn);

         if(next_sseq > 0) {
            //--------------------------------------------
            // 2. DB저장작업
            //    SHOP_INFO 테이블 정보 입력 : 1번 입력
            //--------------------------------------------
            svo.setSseq(next_sseq);
            svo.setSname(sname);
            svo.setSinfo(sinfo);
            svo.setLat(lat);
            svo.setLng(lng);
            svo.setPlacename(placename);
            int infoInsertRes = dao.insertShopInfo(svo, conn);

            //--------------------------------------------
            // 3. DB저장작업
            //    SHOP_PIC 테이블 정보 입력 : pvolist.size()번 입력
            //--------------------------------------------
            if(infoInsertRes > 0) {
               pvo.setSseq(next_sseq);
               int shopPicInsertRes = 0;
               for(int i=0; i<pvolist.size(); i++) {
                  shopPicInsertRes = dao.insertShopPic(pvolist.get(i), conn);
               }
            }
         }

      }catch(Exception e) {
         try {
            e.printStackTrace();
            conn.rollback();
         } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
      } finally {
         try {
            conn.commit();
         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         //--------------------------------------------
         // * DB close
         //--------------------------------------------
         conn.close();
      }
      response.sendRedirect("index.jsp");
   }   
}