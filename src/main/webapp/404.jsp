<%@ page language="java" contentType="text/html; charset=GBK" isErrorPage="true" pageEncoding="GBK"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>404</title>
<link rel="stylesheet" media="screen" href="${rootPath}css/404/style.css">
		<script language="JavaScript">
			function dosearch() {
			var sf=document.searchform;
			var submitto = sf.sengine.value + escape(sf.searchterms.value);
			window.location.href = submitto;
			return false;
			}
		</script>
</head>
<body>
		<div class="main">
		<div class="header">
		  <span>
		   <img src="${rootPath}images/header-logo.png"style="width:80px; height:80px;">
			<p>河南科技学院信息工程学院党委办</p>
		  </span>
		</div>
		<div class="content">
			<h2>SORRY</h2>
			<p class="text">
			  <Center>您所要访问的页面找不到了！。。。。。</Center>
				<form>
				   <center><a href="${rootPath }index"  target="top">
				   <img src="${rootPath}images/zhuye.png"/></a>
				   </center>
				</form>
			</p>
		</div>
	</div>
	</body>
</html>