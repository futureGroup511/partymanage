<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站首页</title>
<link rel="stylesheet" href="${rootPath}css/front/bootstrap.css">
<link rel="stylesheet" href="${rootPath}css/front/index-two.css">
<link rel="stylesheet" href="${rootPath}css/front/head1.css">
<script src="${rootPath}js/jquery-2.2.3.min.js"></script>
<script src="${rootPath}js/bootstrap.js"></script>
</head>

<body>
<%@ include file="head.jsp" %>
<div id="banner">
 <div class="col-lg-12 col-md-12 col-xs-12">
   <div id="slidershow" class="carousel slide" data-ride="carousel"> 
   <!--设置图片轮番的顺序-->
    <ol class="carousel-indicators">
       <li class="active" data-target="#slidershow" data-slide-to="0"></li>
       <li data-target="#slidershow" data-slide-to="1"></li>
       <li data-target="#slidershow" data-slide-to="2"></li>
       <li data-target="#slidershow" data-slide-to="3"></li>
    </ol>
   <!--设置轮播图片-->
    <div class="carousel-inner" style="width:90%!important;">
    	<c:forEach items="${indexImages}" var="indexImage" varStatus="status">
    		<c:choose>
    			<c:when test="${status.index==0}">
    				<div class="item active">
            			<a href="${indexImage.url }"><img src="${rootPath}${indexImage.imgUrl }" width="100%" height="300"></a>
     				 </div>
    			</c:when>
    			<c:otherwise>
    				<div class="item ">
            			<a href="${indexImage.url }"><img src="${rootPath}${indexImage.imgUrl }" width="100%" height="300"></a>
     				 </div>
    			</c:otherwise>
    		</c:choose>   	   		
    	</c:forEach>   
      <a class="left carousel-control" href="#slidershow" role="button" data-slide="prev">
           <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#slidershow" role="button" data-slide="next">
         <span class="sr-only">Next</span>
      </a>
    </div>
  </div>
 </div>
</div>
<div id="banner-one" style="width:90%;margin:0 auto;">
   <div class="row">
       <div class="col-lg-9 col-md-9 col-xs-9 ">
       	<img src="${rootPath}images/tongzhi.png"> 通知公告 
       		<c:forEach items="${informList}" var="inform">
       			<img src="${rootPath}images/jiantou.png"><a href="${rootPath }party/inform/lookInform?id=${inform.id}"> ${inform.title}  ${inform.createDate}</a> 
       		</c:forEach>
         
       </div>
   </div>
</div>
<div id="paper-warp"><!--文章区开始-->
			   <div class="paper-one">
                    <img src="${rootPath}images/zxbt.png">
                    <span class="paper-img">最新文章</span>
					<ol> 
						<c:forEach items="${newPaperList}" var="newPaper">
							<li><a href="${rootPath}party/article/lookArticle?
							id=${newPaper.id}&&type=${type}&&search=${search}"><span>${newPaper.title }</span><span>${newPaper.createDate}</span></a></li>
						</c:forEach>						
                     </ol>
               </div>
				<div class="paper-two">
                    <img src="${rootPath}images/zxbt.png">
					<span class="paper-img" id="pager-img-two">热门文章</span> 
					<ol> 
						<c:forEach items="${newPaperList}" var="hotPaper">
							<li><a href="${rootPath}party/article/lookArticle?
							id=${hotPaper.id}&&type=${type}&&search=${search}"><span>${hotPaper.title }</span><span>${hotPaper.createDate}</span></a></li>
						</c:forEach>						
					</ol>
				</div>
</div><!--文章区结束-->
<div id="video-warp"><!--视频列表区开始-->
	<div class="picScroll-left">
		<span class="ma">活动记录</span>
		<div class="hd">
		   <a class="prev" href="javascript:void(0)"><p>&lt;</p></a>
		</div>
		<div class="bd">
             <div class="tempWrap" style="overflow:hidden; 
             position:relative; width: 90%; margin-left:4.5%;">
                 <ul class="picList" style="width:100%; 
                 position: relative; overflow: hidden; padding: 0px; margin: 0px;">

                 	<c:forEach items="${runRecord}" var="article">
              
                     		<li style="float: left; width: 25%;">
							 	<div class="pic"><a href="${rootPath }party/article/lookArticle?id=${article.id}">
							 	<img src="${rootPath}${article.firstImg}"></a></div>
							 	<div class="title">
							 	<a href="${rootPath }party/article/lookArticle?id=${article.id}">${article.title }</a></div>
						 	</li>
                     </c:forEach> 						
					</ul>
                 </div>
             </div>
			 <div class="hd">
			       <a class="next" href="javascript:void(0)"><p>&gt;</p></a>
			</div>
	  </div>
	  
	<div class="picScroll-left second">
		<span class="ma">推荐视频</span>
		<div class="hd">
		   <a class="prev" href="javascript:void(0)"><p>&lt;</p></a>
		</div>
		<div class="bd">
             <div class="tempWrap" style="overflow:hidden; position:relative; width: 90%; margin-left:4.5%;">
                 <ul class="picList" style="width:100%; position: relative; overflow: hidden; padding: 0px; margin: 0px;">
                 	
                 	<c:forEach items="${recommendVideosList}" var="new_video">
                     		<li style="float: left; width: 25%;">
							 	<div class="pic"><a href="${rootPath }party/video/videoPlay?id=${new_video.id}" title="简介：${new_video.summary}"><img src="${filePath}${new_video.path}.jpg"></a></div>
							 	<div class="title"><a href="${rootPath }party/video/videoPlay?id=${new_video.id}">${new_video.name}</a></div>
						 	</li>
                     </c:forEach> 						
					</ul>
                 </div>
             </div>
			 <div class="hd">
			       <a class="next" href="javascript:void(0)"><p>&gt;</p></a>
			</div>
	  </div>

   <div class="picScroll-left third">
			<span>最新视频</span>
			<div class="hd">
				<a class="prev" href="javascript:void(0)"><p>&lt;</p></a>
			</div>
			<div class="bd">
                 <div class="tempWrap" style="overflow:hidden; position:relative; width: 90%; margin-left:4.5%;">
                     <ul class="picList" style="width:100%; position: relative; overflow: hidden; padding: 0px; margin: 0px;">
                     	 <c:forEach items="${newVideosList}" var="new_video">
                     		<li style="float: left; width: 25%;">
							 	<div class="pic"><a href="${rootPath }party/video/videoPlay?id=${new_video.id}" title="简介：${new_video.summary}"><img src="${filePath}${new_video.path}.jpg"></a></div>
							 	<div class="title"><a href="${rootPath }party/video/videoPlay?id=${new_video.id}">${new_video.name}</a></div>
						 	</li>
                     	</c:forEach> 					   
					</ul>
                 </div>
             </div>
			<div class="hd">
			       <a class="next" href="javascript:void(0)"><p>&gt;</p></a>
		   </div>
	 </div>

</div><!--视频列表区结束-->
<%@ include file="footer.jsp" %>
<script src="${rootPath}js/jquery1.42.min.js"></script>
<script src="${rootPath}js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript">
   jQuery(".picScroll-left").slide({
    	titCell:".hd ul",
    	mainCell:".bd ul",
    	autoPage:true,
    	effect:"left",
    	autoPlay:true,
    	vis:4,
    	trigger:"click"
    });

    jQuery(".picScroll-left second").slide({
    	titCell:".hd ul",
    	mainCell:".bd ul",
    	autoPage:true,
    	effect:"left",
    	autoPlay:true,
    	vis:4,
    	trigger:"click"
    });
     jQuery(".picScroll-left third").slide({
    	titCell:".hd ul",
    	mainCell:".bd ul",
    	autoPage:true,
    	effect:"left",
    	autoPlay:true,
    	vis:4,
    	trigger:"click"
    }); 
</script>
</body>
</html>