<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/include/taglib.jspf" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Study 2017</title>
	<link rel="stylesheet" type="text/css" href="/css/common.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript">
		$(window).load(function() {
			<c:if test="${not empty param.fail}">
				$( "#warningTxt" ).html( "${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}" );
			</c:if>
		});

		$(document).ready(function() {
			$("input[name='loginIdentity']").focus();

			$("input[name='loginIdentity']").val( "admin" );
			$("input[name='loginPassword']").val( "1234" );
			
			$( ".title" ).click(function(){
				window.location.href = "/login.do";
			});
		});
		
		
	</script>
</head>

<body>
	<form method="POST" action="/j_spring_security_check">
		<section class="loginWrapper">
			<div class="loginContainer">
				<fieldset class="loginWrap">
					<p class="title">Study 2017</p>
					<p class="inputs userName"><input type="text" name="loginIdentity" placeholder="아이디" value="${loginIdentity}"></p>
					<p class="inputs password"><input type="password" name="loginPassword" placeholder="비밀번호" value="${loginPassword}"></p>
					<p class="submit"><input type="submit" value="LOGIN"></p>
					<p class="login_info">
						<span class="blc"><a href="#">회원가입</a></span>
						<span class="blc"><a href="#">아이디찾기</a></span>
						<span class="blc"><a href="#">비밀번호찾기</a></span>
					</p>
				</fieldset>
			</div>

			<div class="warning" style="color: red; text-align:center;">
				<p id="warningTxt"></p>
			</div>

		</section>
	</form>
</body>
</html>
