<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<!-- 헤더영역 -->
<%@ include file="/include/header.jsp" %>
<script src="/modules/jquery.min.js"></script>
<script>
$(document).ready(function(){
	  if (navigator.geolocation) { // GPS를 지원하면
	    navigator.geolocation.getCurrentPosition(function(position) {
	    	var mylat=position.coords.latitude;
	    	var mylng=position.coords.longitude;
	    	var str={"lat":mylat,"lng":mylng};
	    	var fystr=JSON.stringify(str);
	    	console.log(fystr);
	    	
	    	
	
	    	
	    	$.ajax({
				url:"/shop",
				type:"post",
				data:"key="+fystr,
				success:function(res){
					console.log(res);
					
					var reshtml="";
					 $.each(res, function(index, vv){
   						reshtml+="<li class=\"media\">";
   						reshtml+="<img class=\"mr-3 rounded-circle\" width=\"50\" src=\"/cdir/"+vv.pname+"\" alt=\"avatar\">";
   						reshtml+="<div class=\"media-body\">";
   						reshtml+="<div class=\"float-right\"><small>"+vv.distance+"  km</small></div>";
   					    reshtml +="<div class=\"media-title\"><a href=/shop_detail?sseq="+vv.sseq+">"+vv.sname+"</a></div> ";
   						reshtml+="<small>"+vv.sinfo+"</small>";
   						reshtml+="</div>";
   						reshtml+="</li>";
				 });

					$(".list-unstyled.list-unstyled-border").html(reshtml);
					
				}
			})
	    }
	    , function(error) {
	      console.error(error);
	    }, {
	      enableHighAccuracy: false,
	      maximumAge: 0,
	      timeout: Infinity
	    });
	  } 
	  else {
	    alert('GPS를 지원하지 않습니다');
	  }
	  //------------------------위는 등록된 맛집 정보 불러오기
	  //아래는 크롤링관련
	  
	  	$.ajax({
				url:"/Manggo",
				type:"post",
				resultType:"json",
				success:function(res){
					console.log(res);
					console.log(res);
					var reshtml="";
					 $.each(res, function(index, vv){
				reshtml+="<tr>";
				reshtml+="<td>"+vv.title+"<div class='table-links'><a href='#'></a></div></td>";
                reshtml+="<td><a target='_blank' href='"+vv.url+"'><img src='"+vv.img+"' alt='avatar' width='30' class='rounded-circle mr-1'>"+vv.place+"</a></td>"; 
				reshtml+="<td><a class='btn btn-primary btn-action mr-1' data-toggle='tooltip' title='Edit'><i class='ion ion-edit'></i></a>";
			  	reshtml+="<a class='btn btn-danger btn-action' data-toggle='tooltip' title='Delete'><i class='ion ion-trash-b'></i></a></td>";
				reshtml+="</tr>";
				if(index==3){
			        return false; 
			    } 
				 });
					 
					$("#top").html(reshtml);		
				}
			})

  $("#viewAllBtn").click(function(){   
		$.ajax({
			url:"/Manggo",
			type:"post",
			resultType:"json",
			success:function(res){
				console.log(res);
				console.log(res);
				var reshtml="";
				 $.each(res, function(index, vv){
					 
			reshtml+="<tr>";
            reshtml+="<td>"+vv.title+"<div class='table-links'><a href='#'></a></div></td>";
            reshtml+="<td><a target='_blank' href='"+vv.url+"'><img src='"+vv.img+"' alt='avatar' width='30' class='rounded-circle mr-1'>"+vv.place+"</a></td>"; 
			reshtml+="<td><a class='btn btn-primary btn-action mr-1' data-toggle='tooltip' title='Edit'><i class='ion ion-edit'></i></a>";
		  	reshtml+="<a class='btn btn-danger btn-action' data-toggle='tooltip' title='Delete'><i class='ion ion-trash-b'></i></a></td>";
			reshtml+="</tr>";			
			 });
				$("#top").html(reshtml);
				
			}
		})
    });
			
})
</script>

</head>

<body>
  <div id="app">
    <div class="main-wrapper">
      <div class="navbar-bg"></div>
      
      
      <!-- 상단검색 -->
     <%@ include file="/include/top.jsp" %>
     
     
     
    <!-- left -->     
     <%@ include file="/include/left.jsp" %>
      
      
      <div class="main-content"><!-- 메인영역 -->
        <section class="section">
          <h1 class="section-header">
            <div>맛집 종합 정보
           <!--   ${sessionScope.SESS_NAME}-->
            <%
         //   Cookie[] carr=request.getCookies();
         //   if(carr!=null){
         //   for(int i = 0; i <carr.length;i++)
         //   {
         //   	Cookie c =carr[i];
         //   	out.println(c.getValue());
         //   	out.println(c.getName());
         //   }
         //   }
            %>
            
            
            </div>
          </h1>
          <div class="row">
            <div class="col-lg-3 col-md-6 col-12">
              <div class="card card-sm-3">
                <div class="card-icon bg-primary">
                  <i class="ion ion-person"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>오늘 방문횟수</h4>
                  </div>
                  <div class="card-body">
                    10
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-3 col-md-6 col-12">
              <div class="card card-sm-3">
                <div class="card-icon bg-danger">
                  <i class="ion ion-ios-paper-outline"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>등록된 맛집</h4>
                  </div>
                  <div class="card-body">
                    42
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-3 col-md-6 col-12">
              <div class="card card-sm-3">
                <div class="card-icon bg-warning">
                  <i class="ion ion-paper-airplane"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>총 방문횟수</h4>
                  </div>
                  <div class="card-body">
                    1,201
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-3 col-md-6 col-12">
              <div class="card card-sm-3">
                <div class="card-icon bg-success">
                  <i class="ion ion-record"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>총 회원수</h4>
                  </div>
                  <div class="card-body">
                    47
                  </div>
                </div>
              </div>
            </div>                  
          </div>
          <div class="row">
            <div class="col-lg-4 col-md-12 col-12 col-sm-12">
              <div class="card">
              
                <div class="card-header">
                  <h4>Recent Activities</h4>
                </div>
                
                
                
                <div class="card-body" id="respage"> 
                <ul class="list-unstyled list-unstyled-border"> 
                      <!-- 이안 -->
                      
                      
                  
                  </ul>
					  <div class="text-center">
					      <a href="#" class="btn btn-primary btn-round">
					       View All
					       </a>
					      </div>    
					      
					      
					      
                </div>
                
                
                
                
              </div>
              
              
            </div>
             <div class="col-lg-4 col-md-12 col-12 col-sm-12">
              <div class="card">
              <div class="card-header">
                  <div class="float-right">
                    <input type="button" id="viewAllBtn" class="btn btn-primary" value="View All">
                  </div>
                  <h4>맛집 추천 TOP</h4>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th>상호명</th>
                          <th>주소명</th>
                          <th>수정/삭제</th>
                        </tr>
                      </thead>
                      <tbody id='top'>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
              
              
            </div>
          </div>
          <div class="row">
            <div class="col-lg-5 col-md-12 col-12 col-sm-12">
              <form method="post" class="needs-validation" novalidate="">
                <div class="card">
                  <div class="card-header">
                    <h4>Quick Draft</h4>
                  </div>
                  <div class="card-body">
                    <div class="form-group">
                      <label>Title</label>
                      <input type="text" name="title" class="form-control" required>
                      <div class="invalid-feedback">
                        Please fill in the title
                      </div>
                    </div>
                    <div class="form-group">
                      <label>Content</label>
                      <textarea class="summernote-simple"></textarea>
                    </div>
                  </div>
                  <div class="card-footer">
                    <button class="btn btn-primary">Save Draft</button>
                  </div>
                </div>
              </form>
            </div>
            <div class="col-lg-7 col-md-12 col-12 col-sm-12">
              <div class="card">
               
              </div>
            </div>
            <div class="col-lg-7 col-md-12 col-12 col-sm-12">
              <div class="card">
               
              </div>
            </div>
          </div>
        </section>
      </div>
      
      </div></div>
      
<!-- 하단 -->
      
      <%@ include file="/include/footer.jsp" %>
      
      
      <%@ include file="/include/script.jsp" %>
      
       <script src="/modules/chart.min.js"></script>
  <script src="/modules/summernote/summernote-lite.js"></script>
  
  <script>
  var ctx = document.getElementById("myChart").getContext('2d');
  var myChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
      datasets: [{
        label: 'Statistics',
        data: [460, 458, 330, 502, 430, 610, 488],
        borderWidth: 2,
        backgroundColor: 'rgb(87,75,144)',
        borderColor: 'rgb(87,75,144)',
        borderWidth: 2.5,
        pointBackgroundColor: '#ffffff',
        pointRadius: 4
      }]
    },
    options: {
      legend: {
        display: false
      },
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: true,
            stepSize: 150
          }
        }],
        xAxes: [{
          gridLines: {
            display: false
          }
        }]
      },
    }
  });
  </script>
</body>
</html>