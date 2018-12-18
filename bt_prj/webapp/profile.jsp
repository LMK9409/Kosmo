<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<head>
<!-- 헤더영역 -->
<%@ include file="/include/header.jsp" %>
<script src="/modules/jquery.min.js"></script>
<script>
$(document).ready(function(){
	//jQuery 지원 : 첨부파일 미리보기 ---------------------
	$("#pname").on("change", handleImgFileSelect);
	
	function handleImgFileSelect(e) {
			$("#prev-img-div").empty();
			var files = e.target.files;  			//[object FileList]
			//FileList into an array 
			//var fileArr = Array.prototype.slice.call(files);			
			var fileArr = Array.from(files); //[object File],[object File],[object File]
				
			if(fileArr.length > 3) {  //files.length
					alert("이미지 첨부는 최대 3개만 가능합니다.");
					$("#pname").val("");
					return false;
			}
			
			var fileSize = 0;
			fileArr.forEach(function(f) {
					fileSize += f.size;
			});
			if(fileSize > 10*1024*1024) {
					alert("이미지 첨부는 최대 10MB만 가능합니다.");
					$("#pname").val("");
					return false;
			}
			
			fileArr.forEach(function(f) {
					if(!f.type.match("image.*")) {
							alert("이미지 첨부만 가능합니다.");
							$("#pname").val("");
							return false;
					} 
					
					var reader = new FileReader();
					var htmlStr = "";
					reader.onload = function(e) {
							htmlStr += "<img src='"+e.target.result+"' style='height:80px;width:80px;'> ";
							$("#prev-img-div").append(htmlStr);
							//alert(htmlStr)
					}
					//reader.onload = function(e) { 
					//	$("#prev-img").attr("src", e.target.result); 
					//} 
					reader.readAsDataURL(f); 
			});
			
			
	}
	$("#regBtn").click(function(){
        var id = $("#user_id").val();
        var pw = $("#user_pw").val();
        var pw2 =  $("#user_pw2").val();
        var agree = $("[id='agree']:checked").val();
        if(id == ""){
           alert("아이디를 입력하세요");
           $("#user_id").focus();
           return false;
        }
        if(pw == ""){
           alert("비밀번호를 입력해주세요.");
           $("#user_pw").focus();
           return false;
        }
        if(pw != pw2){
           alert("비밀번호가 다릅니다.");
           $("#user_pw").focus();
           return false;
        } 
        
        $("#regform").submit();
    });
});    
</script>
</head>

<body>
  <div id="app">
    <section class="section">
      <div class="container mt-5">
        <div class="row">
          <div class="col-12 col-sm-10 offset-sm-1 col-md-8 offset-md-2 col-lg-8 offset-lg-2 col-xl-8 offset-xl-2">
            <div class="login-brand">
             Member profile edit
            </div>

            <div class="card card-primary">
              <div class="card-header"><h4>Register</h4></div>

              <div class="card-body">
              
              
                <form method="POST" id="regform" action="/edit" class="needs-validation" enctype="multipart/form-data">
                  <div class="row">
                    <div class="form-group col-6">
                      <label for="user_id">아이디</label>
                      <input id="user_id" type="text" class="form-control" name="user_id"  value="${KEY_MVO.userId}" required autofocus>
                      <div class="invalid-feedback">
                    올바른 이메일을 입력하세요
                    </div>
                    </div>
                    <div class="form-group col-6">
                      <label for="user_name">이름</label>
                      <input id="user_name" type="text" class="form-control" name="user_name" value="${KEY_MVO.userName}">
                    </div>
                  </div>

                  <div class="form-group">
                    <label for="user_email">이메일</label>
                    <input id="user_email" type="email" class="form-control" name="user_email" value="${KEY_MVO.userEmail}" required autofocus>
                    <div class="invalid-feedback">
                    올바른 이메일을 입력하세요
                    </div>
                  </div>

                  <div class="row">
                    <div class="form-group col-6">
                      <label for="user_pw" class="d-block">바꿀 비밀번호</label>
                      <input id="user_pw" type="password" class="form-control" name="user_pw" required autofocus>
                    </div>
                    <div class="form-group col-6">
                      <label for="user_pw2" class="d-block">이전 비밀번호</label>
                      <input id="user_pw2" type="password" class="form-control" name="user_pw2" required autofocus>
                    </div>
                  </div>
                   

                 <div class="row">
                  <div class="form-group col-6">
                          <label for="email">프로필사진</label>
                          <input id="pname" type="file" class="form-control" name="pname" value="${KEY_MVO.pname}" >
                          <div class="invalid-feedback">
                                사진 등록은 필수 입니다.
                          </div>
                        </div>
                        <div id="prev-img-div" class="form-group col-6">
                               <!-- <img id="prev-img" style="height:100px;width:100px;"> -->
                        </div>
               </div>
                  <!-- <div class="form-divider">
                    Your Home
                  </div>
                  <div class="row">
                    <div class="form-group col-6">
                      <label>City</label>
                      <input type="text" class="form-control">
                    </div>
                    <div class="form-group col-6">
                      <label>Postal Code</label>
                      <input type="text" class="form-control">
                    </div>
                  </div>
 -->
                  

                  <div class="form-group">
                    <button type="button" id="regBtn" class="btn btn-primary btn-block">
                      수정
                    </button>
                  </div>
                </form>
                
                
                
              </div>
            </div>
            <div class="simple-footer">
              Copyright &copy; LMK 2018
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>



<%@ include file="/include/script.jsp" %>

</body>
</html>