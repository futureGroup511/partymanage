<%@page import="cn.edu.hist.partymanage.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>a</title>
${jquery }
${bootstrapCss }
<style type="text/css">
*{
	font-family: Microsoft YaHei,微软雅黑 !important;
}
</style>
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
		<li><a href="#">人员管理</a></li>
		<li class="active">增加人员</li>
	</ol>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<span class="remind">${remind }</span>
				<br>
				<span class="warning">用户密码默认为手机号</span>
				<span class="warning">${warning }</span>
			</div>
		</div>
		<form action="addDo" method="post" onsubmit="return formSubmit()">
		<div class="row">
			<div class="col-sm-5">
				<label>类型：</label>
				<select name="type" class="form-control">
					<c:if test="${user.type ==1 }">
						<option value="1">组织部</option>
					</c:if>
					<option value="2">党委书记</option>
					<option value="3">党支部书记</option>
					<option value="4">党员</option>
					<option value="5">积极分子</option>
				</select>
			</div>
			<div class="col-sm-1"></div>
			<div class="col-sm-5">
				<label>所属部门：</label>
				<%!
					String temp1="aaa";
					String temp2="aaa";
				%> 
					<%
						User user = (User)session.getAttribute("user");
						switch(user.getType()){
							case 1:
								temp1="organizationId";
								temp2="organizationName";
								break;
							case 2:
								temp1="partyId";
								temp2="partyName";
								break;
							case 3:
								temp1="branchId";
								temp2="branchName";
								break;
							default:
								break;
						}
					
					%>
					<input type="hidden" class="form-control" name="<%=temp1 %>" value="${userDepartmentId }">
					<input type="hidden" class="form-control" name="<%=temp2 %>" value="${userDepartment }">
					<input type="text" class="form-control" value="${userDepartment }(只能添加本部门人员)" disabled="disabled">
					
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-5">
				<label>姓名：</label>
				<input type="text" name="name" class="form-control" required="required">
			</div>
			
			<div class="col-sm-1"></div>
			
			<div class="col-sm-5">
				<label>手机号：</label>
				<input type="text" name="phone" class="form-control" required="required" maxlength="11">
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

<script type="text/javascript">

	function formSubmit(){
		//var allInput = $('input,select');
		//allInput.attr('disabled','disabled');
		$("#loading").css('display','block');
		return true;
	}

</script>
</body>
</html>