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
		<li class="active">文章管理</li>
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
						<option value="">全部</option>
						<c:forEach var="t" items="${types }">
							<c:if test="${t.id == type }">
								<option value="${t.id }" selected="selected">${t.name }</option>
							</c:if>
							<c:if test="${t.id != type }">
								<option value="${t.id }">${t.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>

				<div class="col-sm-3">
					<label>作者：</label> <input class="form-control" type="text"
						name="userName" value="${userName }">
				</div>
				<div class="col-sm-4">
					<label>标题：</label> <input class="form-control" type="text"
						name="title" value="${title }">
				</div>

				<div class="col-sm-2">
					<button type="submit" class="btn btn-success">搜索</button>
				</div>

			</form>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<p class="text-info">共找到&nbsp;<span class="text-danger">${pc.count }</span>&nbsp;个结果</p>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<table class="table table-hover table-striped table-bordered">
					<thead>
						<tr>
							<td>
								类型
							</td>
							<td>
								作者
							</td>
							<td>
								发表时间
							</td>
							<td>
								标题
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
						<c:forEach var="article" items="${pc.data }">
						<tr>
							<td>
								${article.typeName }
							</td>
							<td>
								${article.userName }
							</td>
							<td>
								${article.createTime }
							</td>
							<td>
								${article.title }
							</td>
							<td>
								<a href="${rootPath }backend/article/change?id=${article.id}">编辑信息</a>
							</td>
							<td>
								<a href="${rootPath }backend/article/delete?id=${article.id}">删除</a>
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
			    <li><a href="manage?${pc.prePage}">&laquo;</a></li>
			    <li class="active"><a href="#">${pc.currentPage} / ${pc.pageNum}</a></li>
			    <li><a href="manage?currPage=${pc.nextPage}">&raquo;</a></li>
			</ul>
			</div>
		</div>

	</div>

</body>

</html>
