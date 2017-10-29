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
		<li class="active">修改人员</li>
	</ol>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<span class="remind">${remind }</span>
				<span class="warning">${warning }</span>
			</div>
		</div>
		<form action="changeDo" method="post" onsubmit="return formSubmit()">
		<input type="hidden" name="id" value="${user.id }">
		<div class="row">
			<div class="col-sm-5">
				<label>类型：</label>
				<select name="type" class="form-control">
					<c:if test="${user.type ==1 }">
						<option value="1" selected="selected">组织部</option>
						<option value="2">党委书记</option>
						<option value="3">党支部书记</option>
						<option value="4">党员</option>
						<option value="5">积极分子</option>
					</c:if>
					<c:if test="${user.type ==2 }">
						<option value="1" selected="selected">组织部</option>
						<option value="2" selected="selected">党委书记</option>
						<option value="3">党支部书记</option>
						<option value="4">党员</option>
						<option value="5">积极分子</option>
					</c:if>
					<c:if test="${user.type ==3 }">
						<option value="1" selected="selected">组织部</option>
						<option value="2">党委书记</option>
						<option value="3" selected="selected">党支部书记</option>
						<option value="4">党员</option>
						<option value="5">积极分子</option>
					</c:if>
					<c:if test="${user.type ==4 }">
						<option value="1" selected="selected">组织部</option>
						<option value="2">党委书记</option>
						<option value="3">党支部书记</option>
						<option value="4" selected="selected">党员</option>
						<option value="5">积极分子</option>
					</c:if>
					<c:if test="${user.type ==5 }">
						<option value="1">组织部</option>
						<option value="2">党委书记</option>
						<option value="3">党支部书记</option>
						<option value="4">党员</option>
						<option value="5" selected="selected">积极分子</option>
					</c:if>

				</select>
			</div>
			<div class="col-sm-1"></div>
			<div class="col-sm-5">
				<label>所属组织部：</label><span>修改部门需慎重,如需要修改,请点击<button type="button" onclick="changeDepartment('organization')">这里</button></span>
					<select id="organization" class="form-control" name="organizationId" disabled="disabled">
						<c:if test="${user.organizationId == 0 || user.organizationName == '' }">
							<option value="0" selected="selected">----</option>
						</c:if>
						<c:forEach var="dep" items="${organizations }">
							<c:if test="${user.departmentName == dep.name }">
								<option value="${dep.id }" selected="selected">${dep.name }</option>
							</c:if>
							<c:if test="${user.departmentName != dep.name }">
								<option value="${dep.id }">${dep.name }</option>
							</c:if>
						</c:forEach>
					</select>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-5">
				<label>所属党委：</label><span>修改部门需慎重,如需要修改,请点击<button type="button" onclick="changeDepartment('party')">这里</button></span>
					<select id="party" class="form-control" name="partyId" disabled="disabled">
						<c:if test="${user.partyId == 0 || user.partyName == '' }">
							<option value="0" selected="selected">----</option>
						</c:if>
						<c:forEach var="dep" items="${partys }">
							<c:if test="${user.partyId == dep.id }">
								<option value="${dep.id }" selected="selected">${dep.name }</option>
							</c:if>
							<c:if test="${user.partyId != dep.id }">
								<option value="${dep.id }">${dep.name }</option>
							</c:if>
						</c:forEach>
					</select>
			</div>

			<div class="col-sm-1"></div>

			<div class="col-sm-5">
				<label>所属党支部：</label><span>修改部门需慎重,如需要修改,请点击<button type="button" onclick="changeDepartment('branch')">这里</button></span>
					<select id="branch" class="form-control" name="branchId" disabled="disabled">
						<c:if test="${user.branchId == 0 || user.branchName == '' }">
							<option value="0" selected="selected">----</option>
						</c:if>
						<c:forEach var="dep" items="${branchs }">
							<c:if test="${user.branchId == dep.id }">
								<option value="${dep.id }" selected="selected">${dep.name }</option>
							</c:if>
							<c:if test="${user.branchId != dep.id }">
								<option value="${dep.id }">${dep.name }</option>
							</c:if>
						</c:forEach>
					</select>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-5">
				<label>姓名(不可修改)：</label>
				<input type="hidden" name="name" value="${user.name }">
				<input type="text" name="name" value="${user.name }" class="form-control" disabled="disabled">
			</div>

			<div class="col-sm-1"></div>

			<div class="col-sm-5">
				<label>手机号：</label>
				<input type="text" name="phone" value="${user.phone }" class="form-control" required="required" maxlength="11">
			</div>
		</div>
		<div class="row">
			<div class="col-sm-5">
				<label>密码：</label>
				<input type="password" name="password" value="${user.password }" class="form-control" required="required">
			</div>

			<div class="col-sm-1"></div>

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
		$("#organization").removeAttr('disabled');
		$("#party").removeAttr('disabled');
		$("#branch").removeAttr('disabled');
		return true;
	}
	function changeDepartment(select){
		$('#'+select).removeAttr('disabled');
	}

</script>
</body>
</html>
