<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%
    /*
      브라우저의 뒤로가기 버튼을 클릭할 때 POST 방식으로 웹서버가 호출되는 경우
      발생되는 호출에 대한 보장을 할 수 없기 때문에 브라우저는 만료된 페이지를 띄우게 된다.
      따라서 뒤로가기 버튼 클릭 시 브라우저의 페이지 캐쉬를 사용하도록 처리한다.
    */
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader("Expires",0);
    if (request.getProtocol().equals("HTTP/1.1"))
    {
        response.setHeader("Cache-Control","no-cache");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><tiles:insertAttribute name="title" /></title>
    <link rel="stylesheet" type="text/css" href="/css/common.css">
	<link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
	<link href='http://fonts.googleapis.com/css?family=Droid+Serif:700,400,400italic,700italic' rel='stylesheet' type='text/css'>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</head>

<body>
		
    <div class="st-container">
        <header id="header">
            <tiles:insertAttribute name="header" />
        </header>
        <div id="contentWrap">
            <nav id="lnb">
                <tiles:insertAttribute name="navigation" />
            </nav>
            <div id="container" class="sub-cont">
                <tiles:insertAttribute name="content" />
            </div>
        </div>
    </div>
</body>
</html>
