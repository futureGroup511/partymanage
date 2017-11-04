<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="header">
     <div class="header-left">
          <img src="${rootPath}images/header-logo.png">
      </div>
      <div class="header-center">
              <p class="top"><b><strong>河南科技学院信息工程学院党委主办</strong></b></p>
              <p class="bottom"><b><strong>河南科技学院信息工程学院星火在线</strong></b></p>
       </div>
    <p style="margin-top:-10px;position:relative;float:right;margin-right:40px;">
        欢迎 <span style="color: #23527c">${user.name}</span> 登录
        <span style="padding-left: 10px"><a href="${rootPath}logout">退出登录</a>
        </span>
    </p>
       <div class="header-right" style="margin-top: -10px;">
             <div>
             	<form action="${rootPath}party/toFront/select" method="post">
    		     	<select name="tab">
							<option value="1">学习园地</option>
							<option value="2">影音资料</option>
				 	</select>
                  	<input type="text" name="search"  placeholder=" 请输入您要搜索的内容">
                   	<button class="btn"><img src="${rootPath }images/sousuo.png" width="22" height="22"></button>
             	</form>    		    
             </div>
       </div>
</div>

<div class="nav">
     <div class="row">
       <div class="col-lg-11 col-lg-offset-1 col-md-11 col-md-offset-1 col-xs-11 col-xs-offset-1">
         <ul class="nav navbar-nav">
              <li style="margin-left:55px"><a href="${rootPath}party/toFront/toIndex">网站首页</a></li>
              <li style="margin-left:33px"><a href="${rootPath }party/article/toLearnGarden">学习园地</a></li>
              <li style="margin-left:33px"><a href="${rootPath}party/video/toVideoMaterials">影音资料</a></li>
              <li style="margin-left:33px"><a href="${rootPath}party/article/list?type=4">活动记录</a></li>
              <li style="margin-left:33px"><a href="${rootPath}party/question/examType">在线测试</a></li>
              <li class="dropdown" style="margin-left:30px"><a href="#" data-toggle="dropdown" class="dropdown-toggle">个人中心<span class="caret"></span></a>
                   <ul class="dropdown-menu">
                         <li><a href="${rootPath}party/toFront/getSelfInfo">个人信息</a></li>
                         <li><a href="${rootPath}party/toFront/payLog">党费记录</a></li>
                         <li><a href="${rootPath}party/toFront/toUpdateInfo">修改密码</a></li>                  
                   </ul>
               </li>
              <li style="margin-left:33px"><a href="${rootPath}party/inform/list">通知公告</a></li>
              <li style="margin-left:33px"><a href="${rootPath}party/toFront/intro">党委简介</a></li>

         </ul>
        </div>
     </div>
</div>