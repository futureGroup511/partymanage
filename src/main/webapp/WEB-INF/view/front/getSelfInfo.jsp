<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看个人信息</title>
<link rel="stylesheet" href="${rootPath}css/front/head2.css">
<link rel="stylesheet" href="${rootPath}css/front/bootstrap.css">
<link rel="stylesheet" href="${rootPath}css/front/getPartyMemberInfo.css">
<script src="${rootPath}js/jquery-2.2.3.min.js"></script>
<script src="${rootPath}js/bootstrap.js"></script>
</head>

<body>
	<%@ include file="head.jsp" %>
<div id="body">
 <div id="body-one">
   <p><img src="${rootPath}images/topimg.png"> <span>您的位置：<a href="${rootPath}party/toFront/toIndex">首页</a>&nbsp;&nbsp;>&nbsp;&nbsp;个人中心&nbsp;&nbsp;>&nbsp;&nbsp;个人信息</span></p>
 </div>
 <div id="body-three">
   <p><strong>个人信息</strong></p>
    <ol>
      <li class="one"><li><span>账   号：</span><span>${user.phone}</span></li>
	 <li><span>姓   名：</span><span>${user.name}</span></li>
	 <li><span>性   别：</span><span>${user.sex}</span></li>
	 <li><span>年   龄：</span><span>${user.age}</span></li>
	 <li><span>民   族：</span><span>${user.nation}</span></li>
	 <li><span>出生日期：</span><span>${user.birthday}</span></li>
	 <li><span>籍   贯：</span><span>${user.nativePlace}</span></li>
	 <c:if test="${user.education!=null}">
	 	 <li><span>学 历：</span><span>${user.education}</span></li>
	 	 <li><span>学 位：</span><span>${user.academicDegree}</span></li>
	 	 <li><span>职 称：</span><span>${user.title}</span></li>
	 	 <li><span>职   务：</span><span>${user.nowJob}</span></li>
	 </c:if>
	 <c:if test="${user.className!=null}">		 
	 	 <li><span>班 级：</span><span>${user.className}</span></li>
	 	 <li><span>现任职务：</span><span>${user.nowJob}</span></li>
	 	 <li><span>入学时间：</span><span>${user.joinSchoolDate}</span></li>
	 </c:if>	 	
	 <li><span>入党日期：</span><span>${user.joinPartyDate}</span></li>	 	
	 <li><span>所在党支部：</span><span>${user.departmentName}</span></li>
	 <li><span>学习时间：</span><span>${user.studyTime}</span></li>
	 <li><span>手 机 号：</span><span>${user.phone}</span></li>
    </ol>
 </div>
 </div>
 <%@ include file="footer.jsp" %>
</body>
</html>