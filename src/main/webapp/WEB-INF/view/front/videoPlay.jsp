<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${video.name}</title>
<link rel="stylesheet" href="${rootPath}css/front/head2.css">
<link rel="stylesheet"
	href="${rootPath}css/front/bootstrap.css">
<link rel="stylesheet" href="${rootPath}css/front/video.css">
<script src="${rootPath}js/jquery-2.2.3.min.js"></script>
<script src="${rootPath}js/bootstrap.js"></script>
<script type="text/javascript" src="${rootPath}js/learn_video_date.js"></script>
</head>

<body onbeforeunload="onbeforeunload_handler()">
	<%@ include file="head.jsp" %>
	<div id="body">
		<div id="body-one">
			<p>
				<img src="${rootPath}images/topimg.png"> <span>您的位置：<a href="${rootPath}party/toFront/toIndex">首页</a>&nbsp;&nbsp;>&nbsp;&nbsp;<a href="${rootPath}party/video/toVideoMaterials">影音资料</a>&nbsp;&nbsp;>&nbsp;&nbsp;${video.name}</span>
			</p>
		</div>
		<div id="body-two">
			<p>${video.name}</p>
			<ul>
				<li>类型：${video.typeName}</li>
			 	<li>观看量：${video.watchNum} 次</li>
			 	<li>上传时间：${video.createDate}</li>
			</ul>			
		</div>
		<div id="body-three">
			<input type="hidden" value="${video.id}" name="videoId" id="videoId" />
			<input type="hidden" value="${currentTime}" name="oldcurrentTime" id="oldcurrentTime" />
			<input type="hidden" value="${rootPath}" id="rootPath" />
			<video id="videos" width="320" height="240" controls
				onplay="startVideo()">
			<source src="${filePath}${video.path}" type="video/mp4">
			<h3>您的浏览器不支持 HTML5 video 标签。</h3>
	</video>
		</div>
		<div id="body-four">
			<p>	
						
				<span>上一部：
				<c:choose>
					<c:when test="${prev!=null}">
						<a href="${rootPath}party/video/videoPlay?id=${prev.id}&search=${search}&type=${type}">${prev.name }</a>	
					</c:when>
					<c:otherwise>
						没有了
					</c:otherwise>
				</c:choose>
				</span>
				<span>下一部：
				<c:choose>
					<c:when test="${next!=null}">
				        <a href="${rootPath}party/video/videoPlay?id=${next.id}&search=${search}&type=${type}">${next.name}</a>
				</c:when>
					<c:otherwise>
						没有了
					</c:otherwise>
				</c:choose>
				</span>			
			</p>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>