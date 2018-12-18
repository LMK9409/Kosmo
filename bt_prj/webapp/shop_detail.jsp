<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
 <%@ include file="/include/header.jsp" %>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
     $("#sendBtn").click(function(){ 
    	 var str={"reply":$("#reply").val(),"sseq":"${SVO.sseq}"};
    	console.log(str);
	    	var replyStr=JSON.stringify(str);
    	 $.ajax({
				url:"/Reply",
				type:"post",
				resultType:"json",
				data:"key="+replyStr,
				success:function(res){
					console.log(res);
					var reshtml="";
					 $.each(res, function(index, vv){
  						reshtml+=vv.regid+" : "+vv.reply+"   "+vv.regdate+"<br>"
  						
				 });

					$("#replyList").html(reshtml);
					
						
				}
			})
    });
});    
</script>
</head>

<body>
  <div id="app">
    <div class="main-wrapper">
      <div class="navbar-bg"></div>
      <%@ include file="/include/top.jsp" %>
      <%@ include file="/include/left.jsp"%>
      <div class="main-content">
        <section class="section">
          <h1 class="section-header">
            <div>맛집 상세보기</div>
          </h1>

          <div class="section-body">
          
            <div class="jumbotron">
              <h1 class="display-5">${SVO.sname}</h1>
              <p class="lead">${SVO.sinfo}</p>
              <hr class="my-4">
              <p class="lead">
            </p>
            </div>
            <h2 class="section-title">Shop Detail Info</h2>
            
            <div class="row">
              
              <div class="col-12 col-md-6 col-lg-6">
                <div class="card">
                  <div class="card-header">
                    <div class="float-right">
  
                    </div>
                    <h4>Shop Picture</h4>
                  </div>
                  <div class="card-body">
                    <div class="active" data-tab-group="carousel" id="carousel-simple">
                      <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
 
                        <div class="carousel-inner">
                        
                        
                
                            
					<c:forEach var="vo" items="${SVO.plist}" varStatus="status">
                        <div class="carousel-item <c:if test="${status.index==0}">active</c:if>">
                            <img class="d-block w-100" src="/cdir/${vo.pname}" >
                            </div>
                      </c:forEach>
         
                          
                        </div>
                        
                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                          <span class="sr-only">Previous</span>
                        </a>
                        
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                          <span class="carousel-control-next-icon" aria-hidden="true"></span>
                          <span class="sr-only">Next</span>
                        </a>
                        
                      </div>                              
                    </div>
                  </div>
                </div>
                </div>
                
                <div class="col-12 col-md-6 col-lg-6">
                <div class="card">
                  <div class="card-header">
                    <h4>Shop location</h4>
                  </div>
                  <div class="card-body">
             
                    <div id="simple-map" style="height:450px;"></div>
                  </div>
                </div>
              </div>
              </div>
              
               <div class="row" id="replyList" style="width:450px;">
              
               
            	<c:forEach var="vo" items="${SVO.rlist}" varStatus="status">
            	  ${vo.regid}&emsp;&emsp;&emsp;&nbsp; ${vo.reply} &emsp;&emsp;&emsp; ${vo.regdate}<br>
            	</c:forEach>
               
              </div>
              
              <div class="row" >
              
              	<input type="text" name="reply" id="reply" style="width:800px;">
                <div class="col-12 col-md-6 col-lg-6">
              	<input type="button" name="sendBtn" id="sendBtn" value="댓글입력">
                </div>
              </div>
              
              
              </div>
              
            </div>
        </section>
          </div>
      </div>
      </div>
      
      
      <div>
      <%@ include file="/include/footer.jsp"%>
    </div>


   <%@ include file="/include/script.jsp"%>
   
     <script src="http://maps.google.com/maps/api/js?key=AIzaSyDWnF3ONfPsUtJqoV-RZGwNm_abXeRkcQk&amp;sensor=true"></script> 
     <script src="/modules/gmaps.js"></script> 
     <script>
       // init map
       var simple_map = new GMaps({
            div: '#simple-map',
            lat: ${SVO.lat},
            lng: ${SVO.lng},
          
       });
       
       simple_map.addMarker({
           lat: ${SVO.lat},
           lng: ${SVO.lng},
          
           
           click: function(e){
             if(console.log)
               console.log(e);
             alert('You clicked in this marker');
           },
           mouseover: function(e){
             if(console.log)
               console.log(e);
           }
       });
     </script>
</body>
</html>