<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
      prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	
   $("#googleAuthBtn").click(function(){
	   var googleAuth = gapi.auth2.getAuthInstance();
	   if(googleAuth.isSignedIn.get()==true)
	   {
		   $("#googleAuthBtn").val("logout");
       googleAuth.signIn().then(
        function(){
           console.log("1.signIn() ok");
            //로드된 lib을 사용해 로그인한 사용자 정보 가져오기
             var googleUser = googleAuth.currentUser.get()
           console.log("user uniq.ID:"+googleUser.getId());
             
            //googleUser.getHostDomain()
            //googleUser.getGrantedScopes()
           var basicProfile = googleUser.getBasicProfile()
            
           console.log("profile ID:"+basicProfile.getId());
           console.log("profile name:"+basicProfile.getName());
           console.log("profile gname:"+basicProfile.getGivenName());
           console.log("profile fname:"+basicProfile.getFamilyName());
           console.log("profile img:"+basicProfile.getImageUrl());
           console.log("profile email:"+basicProfile.getEmail());
           
           var authResponse =googleUser.getAuthResponse();
           console.log(authResponse);
           console.log(authResponse.access_token);
           
          
        }
        ,function(){
           console.log("google login faild");
        });  
	   }else
	   {
		   googleAuth.signOut();
	   }
  });//로그인 관련 끝
   
  $("#googleCalendarBtn").click(function(){
	  gapi.client.calendar.events.list({
          'calendarId': 'primary',
          'timeMin': (new Date()).toISOString(),
          'showDeleted': false,
          'singleEvents': true,
          'maxResults': 10,
          'orderBy': 'startTime'
        }).then(function(response) {
          var events = response.result.items;
          if (events.length > 0) {
           console.log(response);
            
          } else {
        	  console.log("등록된게 업습니다");
          }
        });
  });//캘린더 관련 끝
   
});
</script>
<script>
function googleLibLoad() {
     gapi.load('client:auth2',googleAuth);
}
function googleAuth(){
	
        console.log("googleLibLoad()...");
        var CLIENT_ID = '467969851194-d927d4s9ugp8jeu4tdng7a3ioftknufu.apps.googleusercontent.com';
        var API_KEY = 'AIzaSyDmubPngLb1Im7cyNyDdDMyGRJkDyXBxYA';
        var DISCOVERY_DOCS = ["https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest"];
        var SCOPES = "https://www.googleapis.com/auth/calendar.readonly";
        var googleAuth = gapi.client.init({ 
        	
             apiKey: API_KEY,
             discoveryDocs: DISCOVERY_DOCS,
             client_id:CLIENT_ID,
             scope: SCOPES,
             }).then(function(){
            console.log("00.google lib load")
               },function(){
            console.log("00.google lib load faild")
       });
   
         //var googleAuth = gapi.auth2.getAuthInstance()
       //로드된 lib을 사용해 로그인

      
      //googleUser.isSignedIn()
      //googleAuth.isSignedIn.get() //true//false//
      
      
       //로드된 lib를 사용해 로그아웃
       //googleAuth.signOut() 
   
}
 

</script>

</head>
<body>
Google signin Test <hr>
<input type="button" id="googleAuthBtn" value="로그인1">
<input type="button" onclick="googlesignin()" value="로그인2">
<input type="button" id="googleCalendarBtn" value="일정가져오기">
<script src="https://apis.google.com/js/api.js?onload=googleLibLoad" async defer></script>
</body>
</html>