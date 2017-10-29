<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>caidan</title>
<link rel="stylesheet" href="${rootPath }css/caidan.css">
</head>
<body id="bg">
	<div class="container">

		<div class="leftsidebar_box">
			<div id="box-scroll">
				<div class="line"></div>
				<c:if test="${user.type < 4 }">
					<dl class="system_log">
						<dt onClick="changeImage()">
							首页轮播图<img src="images/select_xl01.png">
						</dt>
						<dd>
							<a target="main" href="${rootPath }backend/indexImage/add">添加</a>
						</dd>
						<dd>
							<a target="main" href="${rootPath }backend/indexImage/manage">管理</a>
						</dd>

					</dl>
					<dl class="system_log">
						<dt onClick="changeImage()">
							部门管理<img src="images/select_xl01.png">
						</dt>
						<dd>
							<a target="main" href="${rootPath }backend/department/add">增加部门</a>
						</dd>
						<dd>
							<a target="main" href="${rootPath }backend/department/manage">管理部门</a>
						</dd>

					</dl>
					<dl class="system_log">
						<dt onClick="changeImage()">
							人员管理<img src="images/select_xl01.png">
						</dt>
						<dd>
							<a target="main" href="${rootPath }backend/user/add">增加人员</a>
						</dd>
						<dd>
							<a target="main" href="${rootPath }backend/user/manage">管理人员</a>
						</dd>
					</dl>
					<dl class="channel">
						<dt>
							视频管理<img src="${rootPath }images/select_xl01.png">
						</dt>
						<dd>
							<a target="main" href="${rootPath }backend/video/add">上传视频</a>
						</dd>
						<dd>
							<a target="main" href="${rootPath }backend/video/manage">管理视频</a>
						</dd>
						<dd>
							<a target="main" href="${rootPath }backend/video/typeManage">分类管理</a>
						</dd>
					</dl>
					<dl class="channel">
						<dt>
							视频记录<img src="${rootPath }images/select_xl01.png">
						</dt>
						<dd>
							<a target="main" href="${rootPath }backend/watchVideoLog/seeLog">记录查看</a>
						</dd>
					</dl>

					<dl class="channel">
						<dt>
							文章管理<img src="images/select_xl01.png">
						</dt>
						<dd>
							<a target="main" href="${rootPath }backend/article/add">添加文章</a>
						</dd>
						<dd>
							<a target="main" href="${rootPath }backend/article/manage">管理文章</a>
						</dd>
						<dd>
							<a target="main" href="${rootPath }backend/article/typeManage">分类管理</a>
						</dd>
					</dl>
					
					<dl class="channel">
						<dt>
							试题类型管理<img src="${rootPath }images/select_xl01.png">
						</dt>
						<dd>
							<a target="main" href="${rootPath }backend/questionType/manage">管理类型</a>
						</dd>
						<dd>
							<a target="main" href="${rootPath }backend/questionType/report">查看成绩</a>
						</dd>
					</dl>
					
					<dl class="channel">
						<dt>
							试题管理<img src="${rootPath }images/select_xl01.png">
						</dt>
						<dd>
							<a target="main" href="${rootPath }backend/question/add">添加试题</a>
						</dd>
						<dd>
							<a target="main" href="${rootPath }backend/question/manage">管理试题</a>
						</dd>
					</dl>

					<dl class="channel">
						<dt>
							通知管理<img src="images/select_xl01.png">
						</dt>
						<dd>
							<a target="main" href="${rootPath }backend/inform/add">增加通知</a>
						</dd>
						<dd>
							<a target="main" href="${rootPath }backend/inform/manage">管理通知</a>
						</dd>
					</dl>

					<dl class="channel">
						<dt>
							交费记录<img src="images/select_xl01.png">
						</dt>
						<dd>
							<a target="main" href="${rootPath }backend/payLog/add">添加记录</a>
						</dd>
						<dd>
							<a target="main" href="${rootPath }backend/payLog/manage">管理记录</a>
						</dd>
					</dl>

				</c:if>

				<dl class="channel">
					<dt>
						个人中心<img src="${rootPath }images/select_xl01.png">
					</dt>
					<dd>
						<a target="main" href="${rootPath }backend/userInfo/index">个人信息</a>
					</dd>
					<dd>
						<a target="_top" href="${rootPath }backend/userInfo/logOut">退出登录</a>
					</dd>
				</dl>
			</div>
		</div>

	</div>


	<script type="text/javascript" src="${rootPath }js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		$(".leftsidebar_box dt").css({
			"background-color" : "#B9160A"/*一级菜单未点击背景颜色*/
		});
		$(".leftsidebar_box dt img").attr("src",
				"${rootPath }images/select_xl01.png");
		$(function() {
			//$("#box-scroll").height=$("#box-scroll").offsetHeight - 1;
			$(".leftsidebar_box dd").hide();
			$(".leftsidebar_box dt").click(
					function() {
						$(".leftsidebar_box dt").css({
							"background-color" : "#B9160A"/*一级菜单非点击状态的菜单颜色*/
						})
						$(this).css({
							"background-color" : "#B9160A"/*一级菜单展开后背景颜色*/
						});
						$(this).parent().find('dd').removeClass(
								("menu_chioce"), 6000);
						$(".leftsidebar_box dt img").attr("src",
								"${rootPath }images/select_xl01.png");
						$(this).parent().find('img').attr("src",
								"${rootPath }images/select_xl02.png");
						$(".menu_chioce").slideUp();
						$(this).parent().find('dd').slideToggle();
						$(this).parent().find('dd').addClass("menu_chioce");
					})
		})
	</script>
</body>
</html>
