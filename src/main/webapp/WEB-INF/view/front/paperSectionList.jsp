<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学习园地</title>
<link rel="stylesheet" href="${rootPath}css/front/head2.css">
<link rel="stylesheet" href="${rootPath}css/front/bootstrap.css">
<link rel="stylesheet" href="${rootPath}css/front/the party.css">
<script src="${rootPath}js/jquery-2.2.3.min.js"></script>
<script src="${rootPath}js/bootstrap.js"></script>
</head>
<body>
	<%@ include file="head.jsp" %>	
	<div id="body">
		<div id="body-one">
			<p>
				<img src="${rootPath}images/topimg.png"> <span>您的位置：
				<a href="${rootPath}party/toFront/toIndex">首页</a>
				<c:if test="${type!=4 }">
				&nbsp;&nbsp;>&nbsp;&nbsp;
				<a href="${rootPath }party/article/toLearnGarden">学习园地</a>
				</c:if>

				&nbsp;&nbsp;>&nbsp;&nbsp;${flag}</span>
			</p>
		</div>
		<c:choose>
			
		</c:choose>
		<div id="body-two">
			<ol>
				<c:forEach items="${pc.data }" var="paper">
					<p>
						<a
							href="${rootPath}party/article/lookArticle?
							id=${paper.id}&&type=${type}&&search=${search}">${paper.title }</a><span>${ paper.createDate}</span></a>
					</p>
				</c:forEach>
			</ol>
			<div id="pagecut" style="margin-right: 100px; text-align: right;">
				<ul class="pagination">
					<li><a
						href="${rootPath}party/article/list?
						page=${pc.prePage}&&type=${type}&&search=${search}">上一页</a></li>
					<c:if test="${1 < pc.currentPage -3}">
						<li><a
							href="${rootPath}party/article/list?page=1&&type=${type}
							&&search=${search}">1</a></li>
					</c:if>

					<c:forEach var="i"
						begin="${pc.currentPage-3>0?pc.currentPage-3:1 }"
						end="${pc.currentPage+3>pc.pageNum?pc.pageNum:pc.currentPage+3  }">
						<c:choose>
							<c:when test="${i>0 && i == pc.currentPage }">
								<li class="active"><a
									href="${rootPath}party/article/list?
									page=${i }&&type=${type}&&search=${search}">${i}</a></li>
							</c:when>

							<c:when test="${i>0 && i != postPS.currentPage }">
								<li><a
									href="${rootPath}party/article/list?
									page=${i }&&type=${type}&&search=${search}">${i}</a></li>
							</c:when>
						</c:choose>
					</c:forEach>
					<li><a
						href="${rootPath}party/article/list?
						page=${pc.nextPage}&&type=${type}&&search=${search}">下一页</a></li>
				</ul>
			</div>
		</div>
	</div>
<%@ include file="footer.jsp" %>
	<!--页脚结束-->
</body>
</html>


