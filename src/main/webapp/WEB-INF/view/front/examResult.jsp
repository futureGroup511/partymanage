<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试结果</title>
<link rel="stylesheet" href="${rootPath}css/front/head2.css">
<link rel="stylesheet" href="${rootPath}css/front/bootstrap.css">
<link rel="stylesheet"
	href="${rootPath}css/front/the questionnaire results.css">
<script src="${rootPath}js/jquery-2.2.3.min.js"></script>
<script src="${rootPath}js/bootstrap.js"></script>
</head>

<body>
	<%@ include file="head.jsp" %>
	<div id="body">
		<div id="body-one">
			<p>
				<img src="${rootPath}images/topimg.png"> 
				<span>您的位置：<a href="${rootPath}party/toFront/toIndex">
				首页</a>&nbsp;&nbsp;>&nbsp;&nbsp;
				<a href="${rootPath}party/question/examType">
				在线测试</a>&nbsp;&nbsp;>&nbsp;&nbsp;测试结果</span>
			</p>
		</div>		
		<div id="body-big">			
			<div id="body-three">
				<p>前言</p>
			</div>
			<div id="body-four">
				<p>考试结束，请查看你的成绩</p>
			</div>
			<div id="body-five">
				<p>成绩单</p>
			</div>
			<div id="body-six">
				<table class="table table-bordered table-hover">
					<tbody>
						<tr>
							<td>测试题数：${questionNum } </td>
							<td>总分：${examLog.totalScore} 分</td>
							<td>你的分数：${examLog.userScore} 分</td>
							<td>考试时间：${examLog.finishedTime} 分</td>
						</tr>
						<tr>${remind }</tr>
					</tbody>
				</table>
			</div>
			<%-- 
			<div id="body-seven">
				<p>答案辨析</p>
			</div>
			<div id="body-eight">
				<p>单选题</p>
			</div>
			<c:forEach items="${questionList}" var="q" varStatus="status">
				<div id="body-nine">
					<p>${status.index+1}.${q.title}&nbsp;&nbsp;&nbsp;(分值:${q.score})
						</p>
					<p class="six">A. ${q.aAnswer}.</p>
					<p class="six">B. ${q.bAnswer}.</p>
					<p class="six">C. ${q.cAnswer}.</p>
					<p class="six">D. ${q.dAnswer}.</p>
					<c:choose>
						<c:when test="${q.myAnswer!=null}">
							<p class="three">
								您的回答为：${q.myAnswer}.<img src="${rootPath}images/cuohao.png">
							</p>
							<P class="four">正确答案为：${q.answer}.</P>
							<p class="four-two">解析：${q.analyse}</p>					
						</c:when>
						<c:otherwise>
							<p class="five">
								您的回答为：${q.answer}.<img src="${rootPath}images/duihao.png"><span>&nbsp;&nbsp;&nbsp;(得分：${q.score})</span>
							</p>							
							<p class="four-two">解析：${q.analyse}</p>						
						</c:otherwise>
					</c:choose>					
				</div>
			</c:forEach>		
			 --%>			
		</div>
		
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>