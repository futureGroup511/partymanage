<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线测试</title>
<link rel="stylesheet" href="${rootPath}css/front/head2.css">
<link rel="stylesheet" href="${rootPath}css/front/bootstrap.css">
<link rel="stylesheet" href="${rootPath}css/front/online learning.css">
<script src="${rootPath}js/jquery-2.2.3.min.js"></script>
<script src="${rootPath}js/bootstrap.js"></script>

</head>

<body>
	<%@ include file="head.jsp"%>

	<div id="body">
		<div id="body-one">
			<p>
				<img src="${rootPath}images/topimg.png"> <span>您的位置：<a
					href="${rootPath}party/toFront/toIndex">首页</a>&nbsp;&nbsp;>&nbsp;&nbsp;
					<a href="${rootPath}party/question/examType">在线测试</a>&nbsp;&nbsp;>&nbsp;&nbsp;
					开始测试
				</span>
			</p>
		</div>
		<c:choose>
			<c:when test="${NoQuestion!=null}">
				<div id="body-two1">
					<p>${NoQuestion}</p>
				</div>
			</c:when>
			<c:otherwise>
				<div id="body-three">
					<p>共 ${testNum} 题，总分 ${testScore} 分。</p>
				</div>
				<form id="form1" action="${rootPath}party/question/examResult"
					method="post">
					<c:forEach items="${questionList}" var="q" varStatus="status">
						<c:if test="${q.duoxuan }">
							<div id="body-five">
								<p>
									【多选】${status.index+1}.${q.title}<span> * 分值:${q.score}</span>
								</p>
								<div class="radio">
									<label> 
										<c:choose>
											<c:when test="${q.aCheck }">
												<input type="checkbox" name="answer_${q.id}" value="A" checked="checked"> A. ${q.aAnswer}
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="answer_${q.id}" value="A"> A. ${q.aAnswer}
											</c:otherwise>
										</c:choose>
									</label>
								</div>
								<div class="radio">
									<label>
										<c:choose>
											<c:when test="${q.bCheck }">
												<input type="checkbox" name="answer_${q.id}" value="B" checked="checked"> B. ${q.bAnswer}
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="answer_${q.id}" value="B"> B. ${q.bAnswer}
											</c:otherwise>
										</c:choose>
									</label>
								</div>
								<div class="radio">
									<label>
										<c:choose>
											<c:when test="${q.cCheck }">
												<input type="checkbox" name="answer_${q.id}" value="C" checked="checked"> C. ${q.cAnswer}
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="answer_${q.id}" value="C"> C. ${q.cAnswer}
											</c:otherwise>
										</c:choose>
									</label>
								</div>
								<div class="radio">
									<label> 
										<c:choose>
											<c:when test="${q.dCheck }">
												<input type="checkbox" name="answer_${q.id}" value="D" checked="checked"> D. ${d.dAnswer}
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="answer_${q.id}" value="D"> D. ${q.dAnswer}
											</c:otherwise>
										</c:choose>
									</label>
								</div>
							</div>
						</c:if>
						<c:if test="${! q.duoxuan }">
							<div id="body-five">
								<p>
									【单选】${status.index+1}.${q.title}<span> * 分值:${q.score}</span>
								</p>
								<div class="radio">
									<label>
										<c:choose>
										
											<c:when test="${q.aCheck }">
												<input type="radio" name="answer_${q.id}" value="a" checked="checked"> A. ${q.aAnswer}
											</c:when>
											<c:otherwise>
												<input type="radio" name="answer_${q.id}" value="a"> A. ${q.aAnswer}
											</c:otherwise>
										</c:choose>
									</label>
								</div>
								<div class="radio">
									<label>
										<c:choose>
											<c:when test="${q.bCheck }">
												<input type="radio" name="answer_${q.id}" value="b" checked="checked"> B. ${q.bAnswer}
											</c:when>
											<c:otherwise>
												<input type="radio" name="answer_${q.id}" value="b"> B. ${q.bAnswer}
											</c:otherwise>
										</c:choose>
									</label>
								</div>
								<div class="radio">
									<label>
										<c:choose>
											<c:when test="${q.cCheck }">
												<input type="radio" name="answer_${q.id}" value="c" checked="checked"> C. ${q.cAnswer}
											</c:when>
											<c:otherwise>
												<input type="radio" name="answer_${q.id}" value="c"> C. ${q.cAnswer}
											</c:otherwise>
										</c:choose>
									</label>
								</div>
								<div class="radio">
									<label>
										<c:choose>
											<c:when test="${q.dCheck }">
												<input type="radio" name="answer_${q.id}" value="d" checked="checked"> D. ${q.dAnswer}
											</c:when>
											<c:otherwise>
												<input type="radio" name="answer_${q.id}" value="d"> D. ${q.dAnswer}
											</c:otherwise>
										</c:choose>
									</label>
								</div>
								
							</div>
						</c:if>

					</c:forEach>
					<div class="submit">
							<input id="istemp" type="hidden" name="istemp" value="" />
							<button type="submit" class="btn btn-success" onclick="document.getElementById('istemp').value='y';">暂存</button>
							<button type="submit" class="btn btn-primary">交卷</button>
					</div>

				</form>
			</c:otherwise>
		</c:choose>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>