<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学习园地</title>
<link rel="stylesheet" href="${rootPath}css/front/head1.css">
<link rel="stylesheet" href="${rootPath}css/front/bootstrap.css">
<link rel="stylesheet" href="${rootPath}css/front/current news.css">
<script src="${rootPath}js/jquery-2.2.3.min.js"></script>
<script src="${rootPath}js/bootstrap.js"></script>
</head>
<body>
<%@ include file="head.jsp" %>

<div id="banner">
 <div class="col-lg-12 col-md-12 col-xs-12">
   <div id="slidershow" class="carousel slide" data-ride="carousel">
   <!--设置图片轮番的顺序-->
    <ol class="carousel-indicators">
       <li class="active" data-target="#slidershow" data-slide-to="0"></li>
       <li data-target="#slidershow" data-slide-to="1"></li>
       <li data-target="#slidershow" data-slide-to="2"></li>
       <li data-target="#slidershow" data-slide-to="3"></li>
    </ol>
   <!--设置轮播图片-->
    <div class="carousel-inner" style="width:91%!important;height:300px!important;margin-top:10px;">
      <c:forEach items="${indexImages}" var="indexImage" varStatus="status">
    		<c:choose>
    			<c:when test="${status.index==0}">
    				<div class="item active">
            			<a href="${indexImage.url }"><img src="${rootPath}${indexImage.imgUrl }" width="100%" height="300"></a>
     				 </div>
    			</c:when>
    			<c:otherwise>
    				<div class="item ">
            			<a href="${indexImage.url }"><img src="${rootPath}${indexImage.imgUrl }" width="100%" height="300"></a>
     				 </div>
    			</c:otherwise>
    		</c:choose>   	   		
    	</c:forEach>   
      <a class="left carousel-control" href="#slidershow" role="button" data-slide="prev">
           <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#slidershow" role="button" data-slide="next">
         <span class="sr-only">Next</span>
      </a>
    </div>
  </div>
 </div>
</div>
<div id="body">
 <div id="body-one">
   <p><img src="${rootPath}images/topimg.png"> <span>您的位置：<a href="${rootPath}party/toFront/toIndex">首页</a>
   &nbsp;&nbsp;>&nbsp;&nbsp;<a href="${rootPath }party/article/toLearnGarden">
   学习园地</a></span></p>
 </div>
 <div id="body-two">
   <div class="body-two-left">
       <p><span>${fn:substring(partyBuilding[0].typeName, 0, 2)}</span> ${fn:substring(partyBuilding[0].typeName, 2, 4)}</p>
       <p><a href="${rootPath }party/article/list?type=${partyBuilding[0].type}">更多</a></p>
   </div>  
   <div class="body-two-center">
       <p><span>${fn:substring(universityCounseling[0].typeName, 0, 2)}</span> ${fn:substring(universityCounseling[0].typeName, 2, 4)}</p>
       <p><a href="${rootPath }party/article/list?type=${universityCounseling[0].type}">更多</a></p>
   </div>  
   <div class="body-two-right">
       <p><span>${fn:substring(xinxiangStyle[0].typeName, 0, 2)}</span> ${fn:substring(xinxiangStyle[0].typeName, 2, 4)}</p>
       <p><a href="${rootPath }party/article/list?type=${xinxiangStyle[0].type}">更多</a></p>
   </div>  
 </div>
 <div id="body-three">
    <div class="body-three-left">
    	<c:forEach var="article" items="${partyBuilding}">
					<p>					
						<a
							href="${rootPath}party/article/lookArticle?
							id=${article.id}&&type=${partyBuilding[0].type}"><span>》&nbsp;&nbsp;&nbsp;${article.title }</span><span>${article.createDate}</span></a>
					</p>
		</c:forEach>     
    </div>
    <div class="body-three-center">
       <c:forEach var="article" items="${universityCounseling}" >
					<p>					
						<a
							href="${rootPath}party/article/lookArticle?
							id=${article.id}&&type=${universityCounseling[0].type}"><span>》&nbsp;&nbsp;&nbsp;${article.title }</span><span>${article.createDate}</span></a>
					</p>
		</c:forEach> 
    </div>
    <div class="body-three-right">
       <c:forEach var="article" items="${xinxiangStyle}" >
					<p>					
						<a
							href="${rootPath}party/article/lookArticle?
							id=${article.id}&&type=${xinxiangStyle[0].type}"><span>》&nbsp;&nbsp;&nbsp;${article.title }</span><span>${article.createDate}</span></a>
					</p>
		</c:forEach> 
    </div>
 </div>

</div>
<%@ include file="footer.jsp" %>	
</body>
</html>