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
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<span class="remind">${remind }</span>
				<span class="warning">${warning }</span>
			</div>
		</div>
	</div>
</body>

</html>