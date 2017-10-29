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
		<li><a href="#">通知</a></li>
		<li class="active">添加通知</li>
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
					<label>作者：</label> <input type="text" class="form-control"
						value="${user.departmentName } : ${user.name}" disabled="disabled">
				</div>
			</div>

			<div class="row">
				<div class="col-sm-12">
				<label>可看部门：</label>
					<div>
						<label class="checkbox-inline">
							<input type="checkbox" name="canSeeDep" value="0" checked="checked">所有
						</label>
						<c:forEach var="dep" items="${deppartments }">
							<label class="checkbox-inline">
								<input type="checkbox" name="canSeeDep" value="${dep.id }">${dep.name }
							</label>
						</c:forEach>

					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
				<label>可看人员类型：</label>
					<div>
						<label class="checkbox-inline">
							<input type="checkbox" name="canSeeUser" value="0" checked="checked">所有
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" name="canSeeUser" value="1">组织部人员
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" name="canSeeUser" value="2">党委书记
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" name="canSeeUser" value="3">党支部书记
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" name="canSeeUser" value="4">党员
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" name="canSeeUser" value="5">积极分子
						</label>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-12">
					<label>标题</label>
				</div>
				<div class="col-sm-12">
					<input type="text" class="form-control" name="title" maxlength="255">
				</div>

			</div>
			<div class="row">
				<div class="col-sm-12">
					<label>内容</label>
				</div>

			</div>
			<div class="row">
				<div class="col-sm-12">
					<textarea id="content" name="content" style="height: 400px;"></textarea>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-3 col-sm-offset-4">
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
