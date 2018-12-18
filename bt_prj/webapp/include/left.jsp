<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
$(document).ready(function(){
	// alert("left");
});    
</script>
  <div class="main-sidebar"><!-- 레프트영역 -->
        <aside id="sidebar-wrapper">
          <div class="sidebar-brand">
            <a href="index.jsp">홈  으  로!</a>
          </div>
          <div class="sidebar-user">
            <div class="sidebar-user-picture">
              <img alt="세션이름" src="/img/aaaa.png">
            </div>
            <div class="sidebar-user-details">
              <div class="user-name">${sessionScope.SESS_NAME}</div>
              <div class="user-role">
              <c:if test="${sessionScope.SESS_GUBUN=='a'}">권한 :관리자</c:if>
              <c:if test="${sessionScope.SESS_GUBUN=='u'}">권한 :사용자</c:if>
              <c:if test="${sessionScope.SESS_GUBUN==null}">권한 :게스트</c:if>
              
              </div>
            </div>
          </div>
          <ul class="sidebar-menu">
            <li class="menu-header">Dashboard</li>
            <li class="active">
              <a href="index.jsp"><i class="ion ion-speedometer"></i><span>Dashboard</span></a>
            </li>

            <li class="menu-header">Components</li>
            <li>
              <a href="#" class="has-dropdown"><i class="ion ion-ios-albums-outline"></i><span>Components</span></a>
              <ul class="menu-dropdown">
                <li><a href="general.jsp"><i class="ion ion-ios-circle-outline"></i> Basic</a></li>
                <li><a href="components.jsp"><i class="ion ion-ios-circle-outline"></i> Main Components</a></li>
                <li><a href="buttons.jsp"><i class="ion ion-ios-circle-outline"></i> Buttons</a></li>
                <li><a href="toastr.jsp"><i class="ion ion-ios-circle-outline"></i> Toastr</a></li>
              </ul>
            </li>
            <li>
              <a href="#" class="has-dropdown"><i class="ion ion-flag"></i><span>Icons</span></a>
              <ul class="menu-dropdown">
                <li><a href="ion-icons.jsp"><i class="ion ion-ios-circle-outline"></i> Ion Icons</a></li>
                <li><a href="fontawesome.jsp"><i class="ion ion-ios-circle-outline"></i> Font Awesome</a></li>
                <li><a href="flag.jsp"><i class="ion ion-ios-circle-outline"></i> Flag</a></li>
              </ul>
            </li>
            <li>
              <a href="table.jsp"><i class="ion ion-clipboard"></i><span>Tables</span></a>
            </li>
            <li>
              <a href="chartjs.jsp"><i class="ion ion-stats-bars"></i><span>Chart.js</span></a>
            </li>
            <li>
              <a href="simple.jsp"><i class="ion ion-ios-location-outline"></i><span>Google Maps</span></a>
            </li>
            <li>
              <a href="#" class="has-dropdown"><i class="ion ion-ios-copy-outline"></i><span>Examples</span></a>
              <ul class="menu-dropdown">
                <li><a href="login.jsp"><i class="ion ion-ios-circle-outline"></i> Login</a></li>
                <li><a href="register.jsp"><i class="ion ion-ios-circle-outline"></i> Register</a></li>
                <li><a href="forgot.jsp"><i class="ion ion-ios-circle-outline"></i> Forgot Password</a></li>
                <li><a href="reset.jsp"><i class="ion ion-ios-circle-outline"></i> Reset Password</a></li>
                <li><a href="404.jsp"><i class="ion ion-ios-circle-outline"></i> 404</a></li>
              </ul>
            </li>

            <li class="menu-header">More</li>
            <li>
              <a href="#" class="has-dropdown"><i class="ion ion-ios-nutrition"></i> Click Me</a>
              <ul class="menu-dropdown">
                <li><a href="#"><i class="ion ion-ios-circle-outline"></i> Menu 1</a></li>
                <li><a href="#" class="has-dropdown"><i class="ion ion-ios-circle-outline"></i> Menu 2</a>
                  <ul class="menu-dropdown">
                    <li><a href="#"><i class="ion ion-ios-circle-outline"></i> Child Menu 1</a></li>
                    <li><a href="#"><i class="ion ion-ios-circle-outline"></i> Child Menu 2</a></li>
                    <li><a href="#" class="has-dropdown"><i class="ion ion-ios-circle-outline"></i> Child Menu 3</a>
                      <ul class="menu-dropdown">
                        <li><a href="#"><i class="ion ion-ios-circle-outline"></i> Child Menu 1</a></li>
                        <li><a href="#"><i class="ion ion-ios-circle-outline"></i> Child Menu 2</a></li>
                        <li><a href="#"><i class="ion ion-ios-circle-outline"></i> Child Menu 3</a></li>
                      </ul>
                    </li>
                    <li><a href="#"><i class="ion ion-ios-circle-outline"></i> Child Menu 4</a></li>
                  </ul>
                </li>
              </ul>
            </li>
            <li>
              <a href="#"><i class="ion ion-heart"></i> Badges <div class="badge badge-primary">10</div></a>
            </li>
            <li>
              <a href="credits.jsp"><i class="ion ion-ios-information-outline"></i> Credits</a>
            </li>          </ul>
          <div class="p-3 mt-4 mb-4">
            <a href="http://stisla.multinity.com/" class="btn btn-danger btn-shadow btn-round has-icon has-icon-nofloat btn-block">
              <i class="ion ion-help-buoy"></i> <div>Go PRO!</div>
            </a>
          </div>
        </aside>
      </div>