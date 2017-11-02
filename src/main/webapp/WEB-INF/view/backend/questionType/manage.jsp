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
		<li>问题类型</li>
	</ol>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<span class="remind">${remind }</span> <span class="warning">${warning }</span>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<p class="text-info">共找到&nbsp;<span class="text-danger">${typesNum }</span>&nbsp;个结果</p>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<table class="table table-hover table-striped table-bordered">
					<thead>
						<tr>
							<td>
								ID
							</td>
							<td>
								类型
							</td>
							<td>
								修改/添加
							</td>
							<td>
								刪除
							</td>
						</tr>
					</thead>
					<tbody>
						<form action="addDo" method="post">
							<tr>
								
								<td>
									<input class="form-control" type="text" value="自动生成" readonly="readonly">
								</td>
								<td>
									<input class="form-control" type="text" name="name" value="" placeholder="输入新分类名字">
								</td>
								<td>
									<button type="submit" class="btn btn-success">添加</button>
								</td>
								<td style="vertical-align:middle;">
									
								</td>
							</tr>
						</form>
						<c:forEach var="t" items="${types }">
						<form action="changeDo" method="post">
						<tr>
							
							<td>
								<input class="form-control" type="text" name="id" value="${t.id}" readonly="readonly">
							</td>
							<td>
								<input class="form-control" type="text" name="name" value="${t.name}">
							</td>
							<td>
								<button type="submit" class="btn btn-primary">修改</button>
							</td>
							<td style="vertical-align:middle;">
								<a href="delete?id=${t.id }">刪除</a>
							</td>
						</tr>
						</form>
						</c:forEach>
						
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>

</html>