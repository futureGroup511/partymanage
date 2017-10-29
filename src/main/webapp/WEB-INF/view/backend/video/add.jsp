<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>a</title>
${jquery } ${bootstrapCss }
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
		<li><a href="#">视频管理</a></li>
		<li class="active">添加视频</li>
	</ol>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<span class="remind">${remind }</span> <br> <span
					class="warning">${warning }</span>
			</div>
		</div>
		<form action="addDo" method="post" onsubmit="return formSubmit()" enctype="multipart/form-data">
			<div class="row">
				<div class="col-sm-5">
					<label>类型：</label> <select name="type" class="form-control">
						<c:forEach var="type" items="${videoTypes }">
							<option value="${type.id }">${type.name }</option>
						</c:forEach>
					</select>
				</div>
				
			</div>

			<div class="row">
				<div class="col-sm-12">
				<label>可看部门：</label> 
					<div>
						<label class="checkbox-inline">
							<input type="checkbox" name="canSeeDep" value="0" checked="checked">所有
						</label>
						<c:forEach var="dep" items="${deppartments }">
							<label class="checkbox-inline">
								<input type="checkbox" name="canSeeDep" value="${dep.id }">${dep.name }
							</label>
						</c:forEach>
						
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
				<label>可看人员类型：</label> 
					<div>
						<label class="checkbox-inline">
							<input type="checkbox" name="canSeeUser" value="0" checked="checked">所有
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" name="canSeeUser" value="1">组织部人员
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" name="canSeeUser" value="2">党委书记
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" name="canSeeUser" value="3">党支部书记
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" name="canSeeUser" value="4">党员
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" name="canSeeUser" value="5">积极分子
						</label>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-12">
					<div class="form-group">
					    <label for="inputvideo">视频</label>
					    <input type="file" name="videoFile" id="inputvideo">
					    <p class="help-block">请上传MP4视频文件</p>
					  </div>
				</div>
				
				<div class="col-sm-6">
					<div class="form-group">
					    <label for="inputvideo">外部视频链接(外部视频与视频文件必须二选一)</label>
					    <input type="text" name="videoUrl" class="form-control">
	
					    <p class="help-block"></p>
					  </div>
				</div>
				
				<div class="col-sm-6">
					<div class="form-group">
					    <label for="inputvideo">外部视频名字：</label>
					    <input type="text" name="name" class="form-control">
					    <p class="help-block"></p>
					  </div>
				</div>
				
				<div class="col-sm-12">
					<div class="form-group">
					    <label for="inputvideo">视频图片</label>
					    <input type="file" name="videoImg" id="inputvideoImg" required="required">
					    <p class="help-block">用于展示视频</p>
					  </div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="form-group">
					    <label for="name">视频简介:</label>
						<textarea name="summary" class="form-control" rows="3"></textarea>
					</div>
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