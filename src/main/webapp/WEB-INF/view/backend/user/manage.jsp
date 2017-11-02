<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>a</title>
${bootstrapCss }
<style type="text/css">
*{
	font-family: Microsoft YaHei,微软雅黑 !important;
}
</style>
<style type="text/css">
.row {
	margin-bottom: 20px;
}

.remind {
	color: green;
	font-size: 1.5em;
}

.warning {
	color: red;
	font-size: 1.5em;
}

.form-control {
	display: inline-block;
	width: 50%;
}
</style>
</head>

<body>
	<ol class="breadcrumb">
		<li>用户</li>
		<li class="active">用户管理</li>
	</ol>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<span class="remind">${remind }</span> <span class="warning">${warning }</span>
			</div>
		</div>

		<div class="row">
			<form action="manage" method="post">

				<div class="col-sm-3">
					<label>所属部门：</label>
					<input type="hidden" name="department" value="${userDepartment }">
					<input type="text" name="department" class="form-control" value="${userDepartment }" disabled="disabled">

					<%--
					<select name="belongId" class="form-control">
						<option value="">全部</option>
						<c:forEach var="dep" items="${departments }">
							<c:if test="${dep.id == belongId }">
								<option value="${dep.id }" selected>${dep.name }</option>
							</c:if>
							<c:if test="${dep.id != belongId }">
								<option value="${dep.id }">${dep.name }</option>
							</c:if>
						</c:forEach>
					</select>
					 --%>
				</div>

				<div class="col-sm-3">
					<label>姓名：</label> <input class="form-control" type="text"
						name="name" value="${name }">
				</div>
				<div class="col-sm-3">
					<label>手机号：</label> <input class="form-control" type="text"
						name="phone" value="${phone }">
				</div>

				<div class="col-sm-3">
					<input type="hidden" name="currPage" value="${currPage }">
					<button type="submit" class="btn btn-success">搜索</button>
				</div>

			</form>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<p class="text-info">共找到&nbsp;<span class="text-danger">${users.count }</span>&nbsp;个结果</p>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<table class="table table-hover table-striped table-bordered">
					<thead>
						<tr>
							<td>
								名字
							</td>
							<td>
								所属部门
							</td>
							<td>
								类型
							</td>
							<td>
								手机号
							</td>
							<td>
								编辑信息
							</td>
							<td>
								删除
							</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${users.data }">
						<tr>
							<td>
								${user.name }
							</td>
							<td>
								${user.departmentName }
							</td>
							<td>
								(${user.type })${user.typeName }
							</td>
							<td>
								${user.phone }
							</td>
							<td>
								<a href="${rootPath }backend/user/change?id=${user.id}">编辑信息</a>
							</td>
							<td>
								<a href="${rootPath }backend/user/delete?id=${user.id}">删除</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<ul class="pagination">
			    <li><a href="manage?${users.prePage }">&laquo;</a></li>
			    <li class="active"><a href="#">${users.currentPage} / ${users.pageNum}</a></li>
			    <li><a href="manage?currPage=${users.nextPage}">&raquo;</a></li>
			</ul>
			</div>
		</div>

	</div>

</body>

</html>
