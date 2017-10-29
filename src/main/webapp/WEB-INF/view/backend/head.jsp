<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel="stylesheet" href="${rootPath }css/bootstrap.css" />
<link rel="stylesheet" href="${rootPath }css/head.css" />
</head>
<body>
<div class="head">
	<div class="row">
    	<div class="logol col-lg-1 col-lg-offset-1 col-md-1 col-md-offset-1  col-xs-2 col-xs-offset-1">
        	<img src="${rootPath }images/header-logo.png" />
        </div>
        <div class="col-lg-6 col-md-6 col-xs-7">
        	<div class="row">
            	<div class="col-lg-12 col-md-12 col-xs-12">
                <a href="${rootPath }toFront" target="_top">
                <h3 class="first">河南科技学院信息工程学院党委主办</h2></a>
                </div>
            </div>
            <div class="row">
            	<div class="col-lg-12 col-md-12 col-xs-12">
                <h2 class="second">河南科技学院信息工程学院星火在线功能后台系统</h2>
                </div>
            </div>
        </div>
        <div class="dangqian col-lg-3 col-lg-offset-1 col-md-3 col-md-offset-1 col-xs-3 col-xs-offset-1">
        	<p>&ensp;&ensp;&ensp;&ensp;&ensp;当前部门：${userDepartment }</p>
            <p>&ensp;&ensp;&ensp;&ensp;&ensp;当前人员：${userRole },${user.name }</p>
        </div>
    </div>
    <div class="background navbar-fixed-top"  style="position:absonlute; z-index:-1;width:100%;">
    	<img class="img-responsive" width="100%;" src="${rootPath }images/head.png" />
    </div>
</div>
<script type="text/javascript">
$(function(){
    $('#background').height($(window).height());
    $('#background').width($(window).width());
});
</script>
</body>
</html>
