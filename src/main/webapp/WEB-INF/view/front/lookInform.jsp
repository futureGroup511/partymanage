<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通知正文</title>
<link rel="stylesheet" href="${rootPath}css/front/head2.css">
<link rel="stylesheet" href="${rootPath}css/front/bootstrap.css">
<link rel="stylesheet" href="${rootPath}css/front/lookInform.css">

<script src="${rootPath }js/jquery-2.2.3.min.js"></script>
<script src="${rootPath }js/bootstrap.js"></script>
</head>

<body>

<%@ include file="head.jsp"%>

<!--以上为头部-->

<div id="body">
 <div id="body-one">
   <p><img src="${rootPath}images/topimg.png"> <span>您的位置：
   <a href="${rootPath }party/toFront/toIndex">
   首页</a>><a href="${rootPath}party/inform/list">通知公告
   </a>>正文</span></p>
 </div>
 <div id="body-two">
   <h4>${inform.title }</h4>
   <p>作者：${inform.autherName }&nbsp;&nbsp;&nbsp;&nbsp;日期：${inform.createTime }</p>
 </div>
 <div id="body-three">
 	${inform.content }
 </div>
</div>
<%@ include file="footer.jsp" %>
</body>

</html>
