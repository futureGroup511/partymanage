<%@page import="cn.edu.hist.partymanage.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>a</title> ${jquery } ${bootstrapCss }
<link rel="stylesheet" type="text/css"
	href="${rootPath }plugin/wangeditor/css/wangEditor.min.css">
<style type="text/css">
*{
	font-family: Microsoft YaHei,微软雅黑 !important;
}
</style>
<style type="text/css">
.row {
	margin-bottom: 20px;
}

#loading {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.6);
}

#loading-content {
	width: 200px;
	margin: 100px auto;
	color: #FFF;
	font-size: 24px;
}

.remind {
	color: green;
	font-size: 1.5em;
}

.warning {
	color: red;
	font-size: 1.5em;
}
</style>
</head>

<body>
	<ol class="breadcrumb">
		<li><a href="#">文章管理</a></li>
		<li class="active">文章类型管理</li>
	</ol>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<span class="remind">${remind }</span> <br> <span
					class="warning">${warning }</span>
			</div>
		</div>
		<form action="typeManageDo" method="post"
			onsubmit="return formSubmit()">
			
			<div class="row">
				<div class="col-xs-12">
					<p class="help-block">所有类型长度必须为4个字</p>
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-5">
					<label>类型1：</label> <input type="text" name="one"
						class="form-control" value="${one.name }">
				</div>
			</div>

			<div class="row">
				<div class="col-sm-5">
					<label>类型2：</label> <input type="text" name="two"
						class="form-control" value="${two.name }">
				</div>
			</div>

			<div class="row">
				<div class="col-sm-5">
					<label>类型3：</label> <input type="text" name="three"
						class="form-control" value="${three.name }">
				</div>
			</div>

			<div class="row">
				<div class="col-sm-5">
					<button class="btn btn-success">修改</button>
				</div>
			</div>

		</form>
</body>
</html>