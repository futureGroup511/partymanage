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
		<li class="active">部门管理</li>
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
					<label>类型：</label>

					<select name="type" class="form-control">
						<c:choose>
							<c:when test="${type == 0 }">
								<option value="0" selected>全部</option>
								<option value="1">组织部</option>
								<option value="2">党委</option>
								<option value="3">党支部</option>
							</c:when>

							<c:when test="${type == 1 }">
								<option value="0">全部</option>
								<option value="1" selected>组织部</option>
								<option value="2">党委</option>
								<option value="3">党支部</option>
							</c:when>

							<c:when test="${type == 2 }">
								<option value="0">全部</option>
								<option value="1">组织部</option>
								<option value="2" selected>党委</option>
								<option value="3">党支部</option>
							</c:when>

							<c:when test="${type == 3 }">
								<option value="0">全部</option>
								<option value="1">组织部</option>
								<option value="2">党委</option>
								<option value="3" selected>党支部</option>
							</c:when>

							<c:otherwise>
								<option value="0">全部</option>
								<option value="1">组织部</option>
								<option value="2">党委</option>
								<option value="3">党支部</option>
							</c:otherwise>

						</c:choose>
					</select>
				</div>
				<div class="col-sm-3">
					<label>所属部门：</label>

					<input type="hidden" name="belongId" value="${userDepartmentId }">
					<input type="text" class="form-control" value="${userDepartment }" disabled="disabled">

					<%--
					<select name="belongId" class="form-control">
						<option value="0">全部</option>
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
					<label>名字：</label> <input class="form-control" type="text"
						name="name" value="${name }">
				</div>

				<div class="col-sm-3">
					<input type="hidden" name="currPage" value="${currPage }">
					<button type="submit" class="btn btn-success">搜索</button>
				</div>

			</form>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<p class="text-info">共找到&nbsp;<span class="text-danger">${pageCut.count }</span>&nbsp;个结果</p>
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
								所在单位
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
						<c:forEach var="dep" items="${pageCut.data }">
						<tr>
							<td>
								${dep.name }
							</td>
							<td>
								${dep.belongName }
							</td>
							<td>
								(${dep.type })${dep.typeName }
							</td>
							<td>
								${dep.company }
							</td>
							<td>
								<a href="${rootPath }backend/department/change?departmentId=${dep.id}">编辑信息</a>
							</td>
							<td>
								<a href="${rootPath }backend/department/delete?departmentId=${dep.id}">删除</a>
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
			    <li><a href="manage?${pageCut.prePage}">&laquo;</a></li>
			    <li class="active"><a href="#">${pageCut.currentPage} / ${pageCut.pageNum}</a></li>
			    <li><a href="manage?currPage=${pageCut.nextPage}">&raquo;</a></li>
			</ul>
			</div>
		</div>

	</div>

</body>

</html>
