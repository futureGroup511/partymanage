<%@page import="cn.edu.hist.partymanage.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>a</title> ${jquery } ${bootstrapCss }
<style type="text/css">
*{
	font-family: Microsoft YaHei,微软雅黑 !important;
}
</style>
<link rel="stylesheet" type="text/css"
	href="${rootPath }plugin/wangeditor/css/wangEditor.min.css">
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
		<li><a href="#">交费记录</a></li>
		<li class="active">添加记录</li>
	</ol>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<span class="remind">${remind }</span> <br> <span
					class="warning">${warning }</span>
			</div>
		</div>
		<form action="addDo" method="post" onsubmit="return formSubmit()">
			<div class="row">
				<div class="col-sm-12">
					<label>部门：</label>
					<input type="text" class="form-control"
						value="${user.departmentName }" disabled="disabled">
				</div>
			</div>

			<div class="row">
				<div class="col-sm-6">
				<label>人员(只能管理本部门人员): </label>
					<select name="userId" class="form-control">
						<option value="0">--请选择--</option>
						<c:forEach var="user1" items="${users }">
							<option value="${user1.id }">(${user1.id})${user1.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6">
					<label>交钱数目：</label>
					<input type="number" name="payNum" class="form-control">
				</div>
			</div>

			<div class="row">
				<div class="col-sm-6">
					<label>剩余：</label>
					<input type="number" name="lastNum" class="form-control">
				</div>
			</div>

			<div class="row">
				<div class="col-sm-3">
					<button type="submit" class="btn btn-success" style="width: 100%;">确定</button>
				</div>
			</div>
		</form>
	</div>

	<div id="loading">
		<div id="loading-content">操作中....</div>
	</div>
	<script type="text/javascript"
		src="${rootPath }plugin/wangeditor/js/wangEditor.min.js"></script>
	<script type="text/javascript">
		function formSubmit() {
			//var allInput = $('input,select');
			//allInput.attr('disabled','disabled');
			$("#loading").css('display', 'block');
			return true;
		}
		$(function() {
			var editor = new wangEditor('content');
			editor.config.uploadImgUrl = '${rootPath}backend/article/uploadImg';
			editor.create();
		});
	</script>
</body>
</html>
