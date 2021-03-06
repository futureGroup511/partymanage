<%@page import="cn.edu.hist.partymanage.entity.Article"%>
<%@page import="cn.edu.hist.partymanage.entity.Department"%>
<%@page import="java.util.List"%>
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
		<li><a href="#">文章管理</a></li>
		<li class="active">编辑文章</li>
	</ol>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<span class="remind">${remind }</span> <br> <span
					class="warning">${warning }</span>
			</div>
		</div>
		<form action="changeDo" method="post" onsubmit="return formSubmit()">
		<input type="hidden" name="id" value="${article.id }">
			<div class="row">
				<div class="col-sm-5">
					<label>类型：</label> <select name="type" class="form-control">
						<c:forEach var="type" items="${articleTypes }">
							<c:if test="${article.type == type.id }">
								<option value="${type.id }" selected="selected">${type.name }</option>
							</c:if>
							<c:if test="${article.type != type.id }">
								<option value="${type.id }">${type.name }</option>
							</c:if>

						</c:forEach>
					</select>
				</div>
				<div class="col-sm-1"></div>
				<div class="col-sm-5">
					<label>作者：</label>
					<input type="text" class="form-control" value="${article.userName}" disabled="disabled">
				</div>
			</div>

			<div class="row">
				<div class="col-sm-12">
				<label>可看部门：</label>
					<div>
						<label class="checkbox-inline">
							<c:if test="${article.allDepartment}">
								<input type="checkbox" name="canSeeDep" value="0" checked="checked">所有
							</c:if>
							<c:if test="${!article.allDepartment}">
								<input type="checkbox" name="canSeeDep" value="0">所有
							</c:if>
						</label>

								<%
								Article article = (Article)request.getAttribute("article");
								List<Department> deppartments =(List<Department>) request.getAttribute("deppartments");
								for(Department dep:deppartments){
									pageContext.setAttribute("dep", dep);
									if(article.isDepartmentIn(dep.getId())){
								%>
										<label class="checkbox-inline">
											<input type="checkbox" name="canSeeDep" value="${dep.id }" checked="checked">${dep.name }
										</label>
								<%
									}else{
								%>
										<label class="checkbox-inline">
											<input type="checkbox" name="canSeeDep" value="${dep.id }">${dep.name }
										</label>
								<%
									}
								}
								%>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
				<label>可看人员类型：</label>
					<div>
						<label class="checkbox-inline">
						<c:if test="${article.allRole }">
							<input type="checkbox" name="canSeeUser" value="0" checked="checked">所有
						</c:if>
						<c:if test="${!article.allRole }">
							<input type="checkbox" name="canSeeUser" value="0">所有
						</c:if>
						</label>
								<%
								String[] role={"组织部人员","党委书记","党支部书记","党员","积极分子"};
								for(int x=1;x<=5;x++){
									pageContext.setAttribute("roleId", x);
									pageContext.setAttribute("roleName", role[x-1]);
									if(article.isRoleIn(x)){
								%>
										<label class="checkbox-inline">
											<input type="checkbox" name="canSeeUser" value="${roleId }" checked="checked">${roleName }
										</label>
								<%
									}else{
								%>
										<label class="checkbox-inline">
											<input type="checkbox" name="canSeeUser" value="${roleId }">${roleName }
										</label>
								<%
									}
								}
								%>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-12">
					<label>标题</label>
				</div>
				<div class="col-sm-12">
					<input type="text" class="form-control" name="title" maxlength="255" value="${article.title }">
				</div>

			</div>
			<div class="row">
				<div class="col-sm-12">
					<label>内容</label>
				</div>

			</div>
			<div class="row">
				<div class="col-sm-12">
					<textarea id="content" name="content" style="height: 400px;">
						${article.content }
					</textarea>
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
