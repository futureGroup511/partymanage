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
		
		<div class="row">
			<div class="col-sm-12">
				<h3>修改试题:</h3>
			</div>
			<form action="changeDo" method="post">
			<input type="hidden" name="id" value="${question.id }">
				<div class="col-xs-12">
						<div class="form-group" style="width:50%;">
						    <label for="inputvideo">试题类型:</label>
						    <select class="form-control" name="type" required="required">
						    	<c:forEach var="t" items="${types }">
						    		<c:if test="${t.id == question.type }">
						    			<option value="${t.id }" selected="selected">${t.name }</option>
						    		</c:if>
						    		<c:if test="${t.id != question.type }">
						    			<option value="${t.id }">${t.name }</option>
						    		</c:if>
						    	</c:forEach>
						    </select>
						</div>
				</div>
				<div class="col-xs-12">
						<div class="form-group" >
						    <label for="inputvideo">题目:</label>
						    <textarea class="form-control" name="title" required="required">${question.title }</textarea>
						</div>
				</div>
				<div class="col-xs-12">
						<div class="form-group" >
						    <label for="inputvideo">A答案:</label>
						    <textarea class="form-control" name="aAnswer" required="required">${question.aAnswer }</textarea>
						</div>
				</div>
				<div class="col-xs-12">
						<div class="form-group" >
						    <label for="inputvideo">B答案:</label>
						    <textarea class="form-control" name="bAnswer" required="required">${question.bAnswer }</textarea>
						</div>
				</div>
				<div class="col-xs-12">
						<div class="form-group" >
						    <label for="inputvideo">C答案:</label>
						    <textarea class="form-control" name="cAnswer" required="required">${question.cAnswer }</textarea>
						</div>
				</div>
				<div class="col-xs-12">
						<div class="form-group" >
						    <label for="inputvideo">D答案:</label>
						    <textarea class="form-control" name="dAnswer" required="required">${question.dAnswer }</textarea>
						</div>
				</div>
				
				<div class="col-xs-12">
						<div class="form-group" style="width:50%;">
						    <label for="inputvideo">答案:</label>
						    <input class="form-control" name="answer" value="${question.answer }" required="required" />
						    <%-- 
						    <select class="form-control" name="answer" required="required">
						    	<c:if test="${question.answer == 'a' }">
						    		<option value="a" selected="selected">A</option>
						    		<option value="b">B</option>
						    		<option value="c">C</option>
						    		<option value="d">D</option>
						    	</c:if>
						    	<c:if test="${question.answer == 'b' }">
						    		<option value="a">A</option>
						    		<option value="b" selected="selected">B</option>
						    		<option value="c">C</option>
						    		<option value="d">D</option>
						    	</c:if>
						    	<c:if test="${question.answer == 'c' }">
						    		<option value="a">A</option>
						    		<option value="b">B</option>
						    		<option value="c" selected="selected">C</option>
						    		<option value="d">D</option>
						    	</c:if>
						    	<c:if test="${question.answer == 'd' }">
						    		<option value="a">A</option>
						    		<option value="b">B</option>
						    		<option value="c">C</option>
						    		<option value="d" selected="selected">D</option>
						    	</c:if>
						    	
						    </select> --%>
						</div>
				</div>
				
				<div class="col-xs-12">
						<div class="form-group" >
						    <label for="inputvideo">解析</label>
						    <textarea class="form-control" name="analyse" required="required">${question.analyse }</textarea>
						</div>
				</div>
				
				<div class="col-xs-12">
					<button type="submit" class="btn btn-success" style="width: 50%;">确定</button>
				</div>
			</form>
		</div>
		
		
		
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
	</script>
</body>
</html>
