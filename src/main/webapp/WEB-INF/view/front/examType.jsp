<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线测试</title>
<link rel="stylesheet" href="${rootPath}css/front/head2.css">
<link rel="stylesheet" href="${rootPath}css/front/bootstrap.css">
<link rel="stylesheet" href="${rootPath}css/front/examType.css">
<script src="${rootPath}js/jquery-2.2.3.min.js"></script>
<script src="${rootPath}js/bootstrap.js"></script>

</head>

<body >
	<%@ include file="head.jsp" %>	
	<div id="body">
		<div id="body-one">
			<p>
				<img src="${rootPath}images/topimg.png"> <span>您的位置：<a href="${rootPath}party/toFront/toIndex">首页</a>&nbsp;&nbsp;>&nbsp;&nbsp;在线测试</span>
			</p>
		</div>
		<div class="contant">
			<img alt="" width="100%" src="${rootPath }images/19big.jpg">
			<div class="tupian">
				<%-- 
				<ul>
					<li><a href="${rootPath}party/question/onlineExam?id=0">所有类型</a></li>
				</ul>
				 --%>
				<ul>
					<li><a href="http://www.71.cn/2017/0921/966196.shtml" style="color:#FFF;">点击进入十九大考试学习资料</a></li>
					<li><a href="http://www.xinhuanet.com/politics/leaders/xijinping/zyjh.htm" style="color:#FFF;">点击查看习近平总书记重要讲话</a></li>
					<c:forEach items="${ questionTypes}" var="q">
						<li><a href="${rootPath}party/question/onlineExam?id=${q.id}" style="color:#FFF;">点击进入${q.name}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>