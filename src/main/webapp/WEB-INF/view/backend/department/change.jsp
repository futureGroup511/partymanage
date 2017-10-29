<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>a</title>
<link rel="stylesheet" type="text/css"
	href="${rootPath }plugin/wangeditor/css/wangEditor.min.css">
${jquery }
${bootstrapCss }
<style type="text/css">
.row{
	margin-bottom:20px;
}
#loading{
	display:none;
	position:fixed;

	top:0;
	left:0;
	width:100%;
	height:100%;
	background:rgba(0,0,0,0.6);
}
#loading-content{
	width:200px;
	margin:100px auto;
	color:#FFF;
	font-size:24px;
}
.remind{
	color:green;
	font-size:1.5em;
}
.warning{
	color:red;
	font-size:1.5em;
}
</style>
</head>

<body>
	<ol class="breadcrumb">
		<li><a href="#">部门管理</a></li>
		<li class="active">修改部门</li>
	</ol>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<span class="remind">${remind }</span>
				<span class="warning">${warning }</span>
			</div>
		</div>
		<form action="changeDo" method="post" onsubmit="return formSubmit()">
		<input type="hidden" name="id" value="${department.id }">
		<div class="row">
			<div class="col-sm-5">
				<label>类型：</label> <select name="type" class="form-control">

					<option value="1">组织部</option>
					<!-- 不让添加组织部，若让添加，可打开上面的注释 -->
					<option value="2">党委</option>
					<option value="3">党支部</option>
				</select>
			</div>
			<div class="col-sm-1"></div>
			<div class="col-sm-5">
				<label>所属部门：</label>
				<select name="belongId" class="form-control">
					<c:forEach var="dep" items="${departments }">
						<option value="${dep.id }">${dep.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-5">
				<label>名称：</label> <input type="text" class="form-control" name="name" value="${department.name }" maxlength="30" required>
			</div>
			<div class="col-sm-1"></div>
			<div class="col-sm-5">
				<label>成立时间：</label> <input type="number" class="form-control" name="createDate" value="${department.createDate }" placeholder="如：20161229" min="10000000" max="29991230" required>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-5">
				<label>党员数量：</label> <input type="number" class="form-control" value="${department.memberNum }" name="memberNum" max="999999999" required>
			</div>
			<div class="col-sm-1"></div>
			<div class="col-sm-5">
				<label>支部类型：</label>
				<input type="text" class="form-control" name="branchType" value="${department.branchType }" maxlength="30" placeholder="(普通信息)">
			</div>
		</div>

		<div class="row">
			<div class="col-sm-5">
				<label>所在单位：</label> <input type="text" class="form-control" name="company" value="${department.company }" maxlength="30">
			</div>
			<div class="col-sm-1"></div>
			<div class="col-sm-5">
				<label>备注：</label> <input type="text" class="form-control" name="marks" value="${department.marks }" maxlength="100">
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<label>简介:(没有简介的部门可以不填写)</label>
				<textarea style="height:300px;" id="summary-in" name="summary" maxlength="10000">
					${department.summary }
				</textarea>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-3 col-sm-offset-4">
				<button type="submit" class="btn btn-success" style="width:100%;">确定</button>
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

	function formSubmit(){
		//var allInput = $('input,select');
		//allInput.attr('disabled','disabled');
		$("#loading").css('display','block');
		return true;
	}

	$(function() {
		var editor = new wangEditor('summary-in');
		editor.config.uploadImgUrl = '${rootPath}backend/article/uploadImg';
		editor.create();
	});

</script>

</body>

</html>
