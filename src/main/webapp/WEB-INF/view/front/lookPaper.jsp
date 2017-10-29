<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>阅读文章</title>
<link rel="stylesheet" href="${rootPath}css/front/head2.css">
<link rel="stylesheet" href="${rootPath}css/front/bootstrap.css">
<link rel="stylesheet" href="${rootPath}css/front/paper.css">
<script src="${rootPath}js/jquery-2.2.3.min.js"></script>
<script src="${rootPath}js/bootstrap.js"></script>
</head>
<body>
<%@ include file="head.jsp" %>
<div id="body">
 <div id="body-one">
   <p><img src="${rootPath}images/topimg.png"> 
   <span>您的位置：<a href="${rootPath}party/toFront/toIndex">首页
   </a>&nbsp;&nbsp;>&nbsp;&nbsp;
   <c:if test="${paper.type!=4}">
      <a href="${rootPath }party/toLearnGarden">
   	学习园地</a>&nbsp;&nbsp;>&nbsp;&nbsp;
   	<a href="${rootPath}party/article/lookArticle?search=${search}&type=${type}">${flag }</a>
   </c:if>
   <c:if test="${paper.type==4 }">
   <a href="${rootPath}party/article/list?type=4">${flag }</a>
   </c:if>

   
   &nbsp;&nbsp;>&nbsp;&nbsp;正文</span></p>
 </div>
 <div id="body-two">
   <h4>${paper.title}</h4>
   <p>作者：${paper.userName }&nbsp;&nbsp;&nbsp;&nbsp; 日期：${paper.createTime }</p>
 </div>
 <div id="body-three">
 	${paper.content }
 </div>
</div>
<div id="body-four">
  <section class="pre-next">
				<p>
				
				<span>上一篇：
				<c:if test="${not empty pre }">
				<a href="${rootPath}party/article/lookArticle?id=${pre[0].id}&search=${search}&type=${type}">${pre[0].title }
				</a></c:if>
				<c:if test="${empty pre }">
					没有了
				</c:if>
				</span>
				
				<span>下一篇：
				<c:if test="${not empty next }">
				<a href="${rootPath}party/article/lookArticle?id=${next[0].id}&search=${search}&type=${type}">${next[0].title}</a>
				</c:if>
				<c:if test="${ empty next}">
					没有了
				</c:if>
				</span></p>
  </section>
</div>

<%@include file="footer.jsp" %>
</body>
</html>