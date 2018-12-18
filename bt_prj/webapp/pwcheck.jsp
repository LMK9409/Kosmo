<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <!-- 스크립트 부분 복사 -->
  <%@ include file="/include/header.jsp" %>
<script>
$(document).ready(function(){
     //$("#btn").click(function(){   
    //});
});    
</script>
</head>
<body>
  <div id="app">
    <section class="section">
      <div class="container mt-5">
        <div class="row">
          <div class="col-12 col-sm-8 offset-sm-2 col-md-6 offset-md-3 col-lg-6 offset-lg-3 col-xl-4 offset-xl-4">
            <div class="login-brand">
            </div>
            <div class="card card-primary">
              <div class="card-header"><h4>본인확인</h4></div>
              <div class="card-body">
                <form method="POST" action="/pwcheck" class="needs-validation">
                  <div class="form-group">
                    <label for="email">아이디와 비밀번호를 입력하세요</label>
                    <input id="userid" type="text" class="form-control" 
                    name="userid" tabindex="1" required autofocus>
                    <div class="invalid-feedback">
                           아이디를 입력하세요
                    </div>
                  </div>

                  <div class="form-group">
                    <input id="userpw" type="password" class="form-control" 
                    name="userpw" tabindex="2" required>
                    <div class="invalid-feedback">
                            비밀번호를 입력하세요
                    </div>
                  </div>
                  <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block" tabindex="4">
                      확인
                    </button>
                  </div>
                </form>
              </div>
            </div>
            
          </div>
        </div>
      </div>
    </section>
  </div>
     <%@ include file="/include/script.jsp" %>
</body>
</html>