<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <!-- 헤더 css / jquery cdn -->
  <%@ include file="/include/header.jsp" %>
  
<script>
$(document).ready(function(){
	var input = document.getElementById("pname");
	
	$("#pname").change(function(){
		var plistStr="";
		
		for (var i = 0; i < input.files.length; i++) {
			var li = document.createElement("li");
			plistStr += input.files[i].name+"<br>";
		}
		
		console.log(plistStr);
		$("#file-list").html(plistStr);
	});
	
	
	  $("#saveButton").click(function(){
		  console.log($("#sinfo").val());	
	});
	  
});
	  
	  
   

</script>

<style>
.pac-container{
  z-index: 1500 !important;
}
</style>
</head>

<body>
  <div id="app">
    <div class="main-wrapper">
      <div class="navbar-bg"></div>
      
      <!-- 상단 검색창 -->
      <%@ include file="/include/top.jsp" %>
      
      <!-- 레프트 메뉴 영역 -->
      <%@ include file="/include/left.jsp" %>
      
      <!-- 컨텐츠 영영 -->
      <div class="main-content">
        <section class="section">
          <h1 class="section-header">
            <div>맛집 등록하기 --- <a href="/shop_form.jsp">[글쓰기]</a></div>
          </h1>

          <div class="section-body">
            
             <div class="row">
             
              <div class="col-12 col-md-6 col-lg-6">
              
              <!-- enctype="multipart/form-data" -->
              <!--  ------------------------------------------------- -->
              <!--  ------------------등록폼--------------- -->
              <!--  ------------------------------------------------- -->
              <form id="myform" method="post" action="/Shop_insert" class="needs-validation" enctype="multipart/form-data" >
                <div class="card">
                  <div class="card-header">
                    <h4>Shop Register Form</h4>
                  </div>
                  <div class="card-body">
	                  <div class="alert alert-primary">
	                      For maps, we use <a href="http://maps.google.com/" target="_blank">Google Map</a> created.
	                  </div>
                  
                    <div class="form-group">
                      <label>맛집 상호명</label>
                      <input type="text" name="sname" class="form-control" required>
                      <div class="invalid-feedback">
                        	상호명을 입력하세요.
                      </div>
                    </div>
                    <div class="form-group">
                      <label>사진업로드</label>
                      <input type="file" id="pname" name="pname[]"  multiple class="form-control" required>
            			<div id="file-list">
            			
            			</div>
            
            
            
                      <div class="invalid-feedback">
                        	대표 사진 한장을 등록해주세요.
                      </div>
                    </div>
                    </div>
                    <div class="form-group">
                      <label>※선택 : 맛집 지도위치 </label>
                      <a href="http://maps.google.com/" target="_blank">Google Map</a>
                      
                      
<div id="locationField">
  <input id="placename" placeholder="검색할 상호명을 입력하세요" name="placename" type="text">
</div>
<input class="field" name="lat" id="lat" />
<input class="field" name="lng" id="lng" />


                    </div>
                    <div class="form-group">
                      <label>맛집 소개글</label>
                      <textarea id="sinfo" name="sinfo" class="summernote-simple"></textarea>
                    </div>
                  </div>
                  <div class="card-footer">
                    <button  id="saveButton"  class="btn btn-primary">Save Shop Info</button>
                  </div>
                </div>
              </form>
           
							<!--  ------------------------------------------------- -->
							<!--  ------------------등록폼--------------- -->
							<!--  ------------------------------------------------- -->
               </div>
               
               
               
                  <div class="col-12 col-md-6 col-lg-6">
                    <div id="simple-map" style="height:450px;"></div>
                  </div>
                
              </div>
              
            </div>	<!-- end of rows -->
             
             
              </section>
            
          </div>		<!-- end of section-body -->
          
    
      </div>				<!-- end of main-content -->
      
    	<!-- 푸터 영역 -->
      <%@ include file="/include/footer.jsp" %>
      
    </div>
  

  <!-- 스크립트 영역 -->
  <%@ include file="/include/script.jsp" %>
  
  <script src="/modules/summernote/summernote-lite.js"></script>
  <script src="http://maps.google.com/maps/api/js?key=AIzaSyDWnF3ONfPsUtJqoV-RZGwNm_abXeRkcQk&libraries=places&callback=initAutocomplete" async defer></script>
  <script src="/modules/gmaps.js"></script>
  <script>
  //https://fatc.club/2017/06/05/1949
  //https://developers.google.com/maps/documentation/javascript/examples/geocoding-simple?hl=ko
	//	  
  var placeSearch, autocomplete;
  var placename=document.getElementById('placename');
  function initAutocomplete() {
    // Create the autocomplete object, restricting the search to geographical
    // location types.
    autocomplete = 
    	new google.maps.places.Autocomplete(
					(document.getElementById('placename')),{types: ['establishment']}
					//setupClickListener('changetype-establishment', ['establishment']);
			
			);
    // When the user selects an address from the dropdown, populate the address
    // fields in the form.
    autocomplete.addListener('place_changed',   function () {
    	// Get the place details from the autocomplete object.
    	var place = autocomplete.getPlace();
      document.getElementById("lat").value=place.geometry.location.lat();
      document.getElementById("lng").value=place.geometry.location.lng();
      
      var simple_map = new GMaps({
          div: '#simple-map',
          lat: place.geometry.location.lat(),
          lng: place.geometry.location.lng(),
          
     });
      
     simple_map.addMarker({
         lat: place.geometry.location.lat(),
         lng: place.geometry.location.lng(),
        
         
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
      
  });
  }

  </script>
  

  
</body>
</html>