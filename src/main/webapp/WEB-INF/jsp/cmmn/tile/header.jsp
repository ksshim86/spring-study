<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/include/taglib.jspf" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="com.sks.study.cmmn.vo.UserInfoVO" %>

<%
Authentication auth = SecurityContextHolder.getContext().getAuthentication();

Object principal = auth.getPrincipal();
String name = "";
String userId = "";
if( principal != null && principal instanceof UserInfoVO ) {
	name = ((UserInfoVO)principal).getUserName();
	userId = ((UserInfoVO)principal).getUserId();
	
}
%>
<script type="text/javascript">
	$(document).ready(function() {
		
		$( ".title" ).click(function(){
			window.location.href = "/main.do";
		});
	});
	
	
</script>

<div class="st-header">
	<div class="st-header-left">
		<h1>Study 2017</h1>
	</div>
	<div class="st-header-right">
		<ul>
			<li><%=name%>님 환영합니다.</li>
			<li class="btn_logout"><a href="/j_spring_security_logout">로그아웃</a></li>
		</ul>
	</div>
</div>

