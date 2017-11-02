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
		<li><a href="#">个人中心</a></li>
		<li class="active">个人信息</li>
	</ol>
	<div class="container">

		<div class="row">
			<div class="col-xs-12">
				<span class="remind">${remind}</span>
				<span class="warning">${warning}</span>
			</div>
		</div>

		<form action="changeDo" method="post">

			<div class="row">
				<div class="col-xs-6">
					<label>姓名:(不可修改)</label>
					<input type="text" class="form-control" disabled="disabled" value="${user.name}">
				</div>
				<div class="col-xs-6">
					<label>手机号:(登录账号,若修改,请联系所属部门部门的书记)</label>
					<input type="text" class="form-control" disabled="disabled" value="${user.phone}">
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6">
					<label>我的身份:(若修改,请联系所属部门部门的书记)</label>
					<input type="text" class="form-control" disabled="disabled"  value="${user.typeName}">
				</div>
				<div class="col-xs-6">
					<label>所属组织部:(若修改,请联系所属部门部门的书记)</label>
					<input type="text" class="form-control" disabled="disabled"  value="${user.organizationName}">
				</div>

			</div>

			<div class="row">
				<div class="col-xs-6">
					<label>所属党委:(若修改,请联系所属部门部门的书记)</label>
					<input type="text" class="form-control" disabled="disabled"  value="${user.partyName}">
				</div>
				<div class="col-xs-6">
					<label>所属党支部:(若修改,请联系所属部门部门的书记)</label>
					<input type="text" class="form-control" disabled="disabled" value="${user.branchName}">
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6">
					<label>性别:</label>
					<input type="text" class="form-control" name="sex" value="${user.sex}">
				</div>
				<div class="col-xs-6">
					<label>生日:</label>
					<input type="number" class="form-control" name="birthday" value="${user.birthday}">
				</div>
			</div>

			<div class="row">
				<div class="col-xs-6">
					<label>年龄:</label>
					<input type="number" class="form-control" name="age" value="${user.age}">
				</div>
				<div class="col-xs-6">
					<label>民族:</label>
					<input type="text" class="form-control" name="nation" value="${user.nation}">
				</div>
			</div>

			<div class="row">
				<div class="col-xs-6">
					<label>籍贯:</label>
					<input type="text" class="form-control" name="nativePlace" vaulue="${user.nativePlace}">
				</div>
				<div class="col-xs-6">
					<label>学历:</label>
					<input type="text" class="form-control" name="education" value="${user.education}">
				</div>
			</div>

			<div class="row">
				<div class="col-xs-6">
					<label>学位:</label>
					<input type="text" class="form-control" name="academicDegree" value="${user.academicDegree}">
				</div>
				<div class="col-xs-6">
					<label>职称:</label>
					<input type="text" class="form-control" name="title" value="${user.title}">
				</div>
			</div>

			<div class="row">
				<div class="col-xs-6">
					<label>入职时间:</label>
					<input type="number" class="form-control" name="joinWorkDate" value="${user.joinWorkDate}" placeholder="8位数字,如20171230代表2017年12月30日">
				</div>
				<div class="col-xs-6">
					<label>入党时间:</label>
					<input type="number" class="form-control" name="joinPartyDate" value="${user.joinPartyDate}" placeholder="8位数字,如20171230代表2017年12月30日">
				</div>
			</div>


			<div class="row">
				<div class="col-xs-12">
					<label>以下为学生属性,若您不是学生,请忽略,是学生,请填写!</label>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6">
					<label>年级:</label>
					<input type="number" name="grade" class="form-control" value="${user.grade}" placeholder="4位数字,如2017代表2017年">
				</div>
				<div class="col-xs-6">
					<label>班级:</label>
					<input type="text" name="className" class="form-control" value="${user.className}">
				</div>
			</div>

			<div class="row">
				<div class="col-xs-6">
					<label>现任职务:</label>
					<input type="text" name="nowJob" class="form-control" value="${user.nowJob}">
				</div>
				<div class="col-xs-6">
					<label>入学时间:</label>
					<input type="number" name="joinSchoolDate" class="form-control" value="${user.joinSchoolDate}"  placeholder="8位数字,如20171230代表2017年12月30日">
				</div>
			</div>
			<div class="row">
				<div class="col-sm-3 col-sm-offset-4">
					<button type="submit" class="btn btn-success" style="width:100%;">更新资料</button>
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
