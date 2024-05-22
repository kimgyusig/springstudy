<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<script src="${path}/js/jquery-3.5.1.js"></script>

    <title>WEHUB</title>

    <link rel="stylesheet" href="${path}/css/LoginForm.css">
	</head>
	<body>
    	<div class="wrapper">
        <div class="container">

            <img src="${path}/images/WEHUB_LogoW.png"/>

            <form class="form" action="login" method="POST" id="loginForm" onsubmit="loginForm();">

                <input type="text" name="userId" placeholder="Username ">
                <input type="password" name="userPwd" placeholder="Password">
                <button type="submit" id="login-button">Log in</button>
                <p id="findPwdAndSignUp">
                    <a onclick="location.href='${path}/member/findIDorPwd'" id="fpas1">Forgot ID / Password?</a>

                    <a href="${path}/member/BeforeSignUp" id="fpas2">Sign Up</a>
                </p>
            </form>
        </div>
    </div>
</body>

<script>

  	$("#login-button").click(function(event){
	
		$('form').fadeOut(300);
		$('.wrapper').addClass('form-success');
		setTimeout(function() {
		}, 2800);
	      
		return;
		
		$('#loginForm').submit();
	}); 
  	
  	function loginForm() {
	  	setTimeout("loginForm()", 3000);
  		console.log("될려나..ㅎㅎ");
  	}
  	

</script>
</html>
