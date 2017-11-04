<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通知公告</title>

<link rel="stylesheet" href="${rootPath}css/front/bootstrap.css">
<link rel="stylesheet" href="${rootPath}css/front/index-two.css">
<link rel="stylesheet" href="${rootPath}css/front/head2.css">
<link rel="stylesheet" href="${rootPath}css/front/notification message.css">
<script src="${rootPath}js/jquery-2.2.3.min.js"></script>
<script src="${rootPath}js/bootstrap.js"></script>
</head>
<body>
	<%@ include file="head.jsp" %>
	
	<div id="body">
		<div id="body-one">
			<p>
				<img src="${rootPath}images/topimg.png"> 
				<span>您的位置：<a href="${rootPath}party/toFront/toIndex">首页</a>
				&nbsp;&nbsp;>&nbsp;&nbsp;<a href="${rootPath}party/inform/list">通知公告</a></span>
			</p>
		</div>
		<c:if test="${not empty pc.data }">
			<div id="body-two">
				<c:forEach items="${pc.data }" var="i">
					<p>
						<a href="${rootPath }party/inform/lookInform?id=${i.id}">${i.title }<span>${i.createTime}</span></a>
					</p>
				</c:forEach>
			</div>
			
			<div id="pagecut">
				<ul class="pagination">
					<li><a
						href="${rootPath }party/inform/list?page=${pc.prePage}">上一页</a></li>
					<c:if test="${1 < pc.currentPage -3}">
						<li><a
							href="${rootPath }party/inform/list?page=1">1</a></li>
					</c:if>

					<c:forEach var="i"
						begin="${pc.currentPage-3>0?pc.currentPage-3:1 }"
						end="${pc.currentPage+3>pc.pageNum?pc.pageNum:pc.currentPage+3  }">
						<c:choose>
							<c:when test="${i>0 && i == pc.currentPage }">
								<li class="active"><a
									href="${rootPath }party/inform/list?page=${i }">${i}</a></li>
							</c:when>

							<c:when test="${i>0 && i != postPS.currentPage }">
								<li><a
									href="${rootPath }party/inform/list?page=${i }">${i}</a></li>
							</c:when>
						</c:choose>
					</c:forEach>
					<li><a
						href="${rootPath }party/inform/list?page=${pc.nextPage}">下一页</a></li>
				</ul>

			</div>
			
		</c:if>
		
		<c:if test="${ empty pc.data}">
			<h3 style="height: 280px;text-align:center; color:red;">暂时没有通知！</h3>
		</c:if>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
<script type="text/javascript">
	var notice = "${notice}";
	if (notice === "") {

	} else {
		alert(notice);
	}
</script>