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
	   
	
// todo
  });
});
</script>
<script>
function googleLibLoad() {
     gapi.load('client:auth2',googleAuth);
}
function googleAuth(){
	
        console.log("googleLibLoad()...");
        
        var googleAuth = gapi.auth2.init({ 
        	
             client_id: '467969851194-d927d4s9ugp8jeu4tdng7a3ioftknufu.apps.googleusercontent.com',
             scope: 'email profile openid',
             }).then(function(){
            console.log("00.google lib load")
               },function(){
            console.log("00.google lib load faild")
       });
        gapi.auth2.authorize({
        	  client_id: 'CLIENT_ID.apps.googleusercontent.com',
        	  scope: 'email profile openid',
        	  response_type: 'id_token permission'
        	}, function(response) {
        	  if (response.error) {
        	    // An error happened!
        	    return;
        	  }
        	  // The user authorized the application for the scopes requested.
        	  var accessToken = response.access_token;
        	  var idToken = response.id_token;
        	  // You can also now use gapi.client to perform authenticated requests.
        	});
         //var googleAuth = gapi.auth2.getAuthInstance()
       //로드된 lib을 사용해 로그인

      
      //googleUser.isSignedIn()
      //googleAuth.isSignedIn.get() //true//false//
      
      
       //로드된 lib를 사용해 로그아웃
       //googleAuth.signOut() 
   
}
      function googlesignin() {
    	  var googleAuth = gapi.auth2.getAuthInstance();
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
           gapi.auth2.authorize(authResponse, function(response) {
        	   if (response.error) {
        	   
        	     console.log("An error happened!");
        	     return;
        	   }
        	   // The user authorized the application for the scopes requested.
        	   var accessToken = response.access_token;
        	   var idToken = response.id_token;
        	   
        	   console.log("accessToken:"+accessToken);
        	   // You can also now use gapi.client to perform authenticated requests.
        	 });
        }
        ,function(){
           console.log("google login faild");
        });      
   }   

</script>

</head>
<body>
Google signin Test <hr>
<input type="button" id="googleAuthBtn" value="login1">
<input type="button" onclick="googlesignin()" value="login2">
<script src="https://apis.google.com/js/platform.js?onload=googleLibLoad" async defer></script>
</body>
</html>