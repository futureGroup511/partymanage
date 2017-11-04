<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>党委简介</title>
<link rel="stylesheet" href="${rootPath}css/front/bootstrap.css">
<link rel="stylesheet" href="${rootPath }css/front/head2.css">
<link rel="stylesheet" href="${rootPath}css/front/connect us.css">
<script src="${rootPath}js/jquery-2.2.3.min.js"></script>
<script src="${rootPath}js/bootstrap.js"></script>
</head>

<body>
<%@ include file="head.jsp"%>
<!--以上为头部-->
<div id="body">
	 <div id="body-one">
	   <p><img src="${rootPath}images/topimg.png"> 
	   <span>您的位置：<a href="${rootPath}party/toFront/toIndex">首页
	   </a>&nbsp;&nbsp;>&nbsp;&nbsp;党委简介</span></p>
	 </div>
	<div id="body-two">
		<c:if test="${ not empty intro }">
			${intro}
		</c:if>
		<c:if test="${empty intro}">
			<h3 style="height: 240px; text-align:center; color:red">暂无简介</h3>
		</c:if>
	</div>
</div>
<%@ include file = "footer.jsp" %>
</body>
</html>
