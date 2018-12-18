<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
$(document).ready(function(){
	 alert("top");
});    
</script>
      <nav class="navbar navbar-expand-lg main-navbar"><!-- 상단검색창 -->
        <form class="form-inline mr-auto">
          <ul class="navbar-nav mr-3">
            <li><a href="#" data-toggle="sidebar" class="nav-link nav-link-lg"><i class="ion ion-navicon-round"></i></a></li>
            <li><a href="#" data-toggle="search" class="nav-link nav-link-lg d-sm-none"><i class="ion ion-search"></i></a></li>
          </ul>
          <div class="search-element">
            <input class="form-control" type="search" placeholder="Search" aria-label="Search">
            <button class="btn" type="submit"><i class="ion ion-search"></i></button>
          </div>
        </form>
        <ul class="navbar-nav navbar-right">
          <li class="dropdown dropdown-list-toggle"><a href="#" data-toggle="dropdown" class="nav-link notification-toggle nav-link-lg beep"><i class="ion ion-ios-bell-outline"></i></a>
            <div class="dropdown-menu dropdown-list dropdown-menu-right">
              <div class="dropdown-header">Notifications
                <div class="float-right">
                  <a href="#">View All</a>
                </div>
              </div>
              <div class="dropdown-list-content">
                <a href="#" class="dropdown-item dropdown-item-unread">
                  <img alt="image" src="/img/avatar/avatar-1.jpeg" class="rounded-circle dropdown-item-img">
                  <div class="dropdown-item-desc">
                    <b>Kusnaedi</b> has moved task <b>Fix bug header</b> to <b>Done</b>
                    <div class="time">10 Hours Ago</div>
                  </div>
                </a>
                <a href="#" class="dropdown-item dropdown-item-unread">
                  <img alt="image" src="/img/avatar/avatar-2.jpeg" class="rounded-circle dropdown-item-img">
                  <div class="dropdown-item-desc">
                    <b>Ujang Maman</b> has moved task <b>Fix bug footer</b> to <b>Progress</b>
                    <div class="time">12 Hours Ago</div>
                  </div>
                </a>
                <a href="#" class="dropdown-item">
                  <img alt="image" src="/img/avatar/avatar-3.jpeg" class="rounded-circle dropdown-item-img">
                  <div class="dropdown-item-desc">
                    <b>Agung Ardiansyah</b> has moved task <b>Fix bug sidebar</b> to <b>Done</b>
                    <div class="time">12 Hours Ago</div>
                  </div>
                </a>
                <a href="#" class="dropdown-item">
                  <img alt="image" src="/img/avatar/avatar-4.jpeg" class="rounded-circle dropdown-item-img">
                  <div class="dropdown-item-desc">
                    <b>Ardian Rahardiansyah</b> has moved task <b>Fix bug navbar</b> to <b>Done</b>
                    <div class="time">16 Hours Ago</div>
                  </div>
                </a>
                <a href="#" class="dropdown-item">
                  <img alt="image" src="/img/avatar/avatar-5.jpeg" class="rounded-circle dropdown-item-img">
                  <div class="dropdown-item-desc">
                    <b>Alfa Zulkarnain</b> has moved task <b>Add logo</b> to <b>Done</b>
                    <div class="time">Yesterday</div>
                  </div>
                </a>
              </div>
            </div>
          </li>
			<li class="dropdown">
				<a href="#" data-toggle="dropdown" class="nav-link dropdown-toggle nav-link-lg">
            		<i class="ion ion-android-person d-lg-none"></i>
            	<div class="d-sm-none d-lg-inline-block">hi, ${sessionScope.SESS_NAME}</div></a>
             <c:choose>
               <c:when test = "${sessionScope.SESS_GUBUN == 'a' }"><b>관 리 자</b></c:when>
               <c:when test = "${sessionScope.SESS_GUBUN == 'u' }"><b>사 용 자</b></c:when>
               <c:when test = "${sessionScope.SESS_GUBUN == null }"><b>게 스 트</b></c:when>
            </c:choose>
            
            <div class="dropdown-menu dropdown-menu-right">
            <c:if test = "${sessionScope.SESS_GUBUN != null}">
            
              <a href="pwcheck.jsp" class="dropdown-item has-icon">
                <i class="ion ion-android-person"></i> Profile
              </a>
              <a href="/login" class="dropdown-item has-icon">
                <i class="ion ion-log-out"></i> Logout
              </a>
              </c:if>
              <c:if test="${sessionScope.SESS_GUBUN == null}">
                 <a href="login.jsp" class="dropdown-item has-icon">
                <i class="ion ion-log-out"></i>Login
              </a>
              </c:if>
            </div>

          </li>
        </ul>
      </nav>  
      
      <script>
      function gologinpage(){
    	  alert("회원만 이용 가능합니다.");
    	  location.href("/index.jsp");
      }
      </script>