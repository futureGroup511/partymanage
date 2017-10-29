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
		<li>考试结果</li>
	</ol>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<span class="remind">${remind }</span> <span class="warning">${warning }</span>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<form action="report">
				<label>问题类型</label>
				<select class="form-control" name="typeId">
					<option value="0">全部</option>
					<c:forEach var="t" items="${types }">
						<c:if test="${t.id == nowTypeId }">
							<option value="${t.id }" selected="selected">${t.name }</option>
						</c:if>
						<c:if test="${t.id != nowTypeId }">
							<option value="${t.id }">${t.name }</option>
						</c:if>
					</c:forEach>
				</select>
				<button class="btn btn-success" type="submit">筛选</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<p class="text-info">共找到&nbsp;<span class="text-danger">${nums }</span>&nbsp;个结果</p>
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
								用户名
							</td>
							<td>
								问题类型
							</td>
							<td>
								成绩
							</td>
							<td>
								总分
							</td>
							<td>
								答题时间
							</td>
							<td>
								删除
							</td>
						</tr>
					</thead>
					<tbody>
						
						<c:forEach var="log" items="${examLogs }">
							<tr>
								<td>
									${log.id }
								</td>
								<td>
									${log.userName }
								</td>
								<td>
									${log.questionType }
								</td>
								<td>
									${log.userScore }
								</td>
								<td>
									${log.totalScore }
								</td>
								<td>
									${log.finishedTime }
								</td>
								<td>
									<a href="deleteReport?id=${log.id }">删除</a>
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