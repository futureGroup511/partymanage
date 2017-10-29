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
		<li>视频记录</li>
	</ol>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<span class="remind">${remind }</span> <span class="warning">${warning }</span>
			</div>
		</div>

		<div class="row">
			<form action="seeLog" method="post">

				<div class="col-sm-4">
					<label>用户名字:</label> <input class="form-control" type="text"
						name="userName" value="${userName }" placeholder="输入姓名搜索">
				</div>
				<div class="col-sm-4">
					<label>视频名字:</label> <input class="form-control" type="text"
						name="videoName" value="${videoName }" placeholder="视频名字">
				</div>
				<%-- 
				<div class="col-sm-6">
					<label>部门名字:</label>
					<select>
						<option value="">全部</option>
						<c:forEach var="dep" items="${deps }">
							<option value="dep">${dep.name }</option>
						</c:forEach>
					</select>
					 <input class="form-control" type="text"
						name="departmentName" value="${userName }" placeholder="部门名字">
				</div> --%>

				<div class="col-sm-4">
					<button type="submit" class="btn btn-success">搜索</button>
				</div>

			</form>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<p class="text-info">共找到&nbsp;<span class="text-danger">${logsNum }</span>&nbsp;个结果</p>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<table class="table table-hover table-striped table-bordered">
					<thead>
						<tr>
							<td>
								用户名
							</td>
							<td>
								视频
							</td>
							<td>
								观看时长
							</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="log" items="${logs }">
						<tr>
							<td>
								${log.userName}
							</td>
							<td>
								${log.videoName}
							</td>
							<td>
								${log.watchSecond }
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>

</html>
