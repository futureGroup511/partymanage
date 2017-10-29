<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<footer id="footer-warp"><!--页脚开始-->
		<p class="footer"><a href="">网站声明</a>
		 &nbsp; &nbsp;&nbsp; &nbsp;		 
		 <a href="${rootPath}backend/index">关于我们 </a>		 	
		&nbsp; &nbsp;&nbsp; &nbsp;
		<a href="">联系我们</a></p>
		<p>技术支持：河南科技学院未来软件工作室　　 河南艾未特网络技术有限公司</p>
</footer><!--页脚结束-->	
<script type="text/javascript">
var notice="${notice}"
if(notice==""){
	
}else{
	alert(notice);
}
</script>
