<%@page language="java" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp" />
<link rel="stylesheet" href="css/hotsystem.css">
<link rel="stylesheet" type="text/css" href="css/pop.css">

<title>热点分析</title>
</head>
<body>
    <jsp:include page="nav.jsp" />
   <div class="ser_section">
   		<div class="ser_con row pst">
			 <input type="text" placeholder="请输入搜索关键字" maxlength="20" class="ser_text f16" id="ser_text">
			 
			 <div class="pointer ser_btn f16 ac fl" id="ser_btn_high">高级探索</div>
			 <div class="pointer ser_btn f16 ac" id="ser_btn">探索</div>
			 <div class="pos favorite_set_btn pointer hidecommon f14" id="favorite_set_btn">设为常用</div>
			 <ul class="cook_ul pos hidecommon" id="cook_ul">
    			<li>手机2<span></span></li>
				<li>苹果2<span></span></li>
				<li>三星2<span></span></li>
				<li>华为2<span></span></li>			
    		 </ul>
		</div>
		
		<div class="favorite_div f12">
			<span class="favorite_set_text">常用搜索:</span>
			<ul class="favorite_ul" id="favorite_ul">
				<li>手机1<span></span></li>
				<li>苹果1<span></span></li>
				<li>三星1<span></span></li>
				<li>华为1<span></span></li>
			</ul>
		</div>
   </div>
   <div class="ser_dialog hidecommon" id="ser_dialog">
   		<div class="dialog_area">
   			高级搜索
   			<span style="float:right;">X</span>
   		</div>
   </div>
   <div style="width:100%;height:230px;bottom:0;">
       <div id="papersvg"></div>
   </div>
   <div class="allHot">全部热点</div>	
   
   <div class="all_hot">
   		<div class="all_hot_section">全部热点</div>
   </div>
   <div class="alertCon">
    <div class="portrait"></div>
    <div class="info">
        <div class="infoTop">
            <div class="infoTitle">张雨绮结婚</div>
            <div class="infoConnect">关联此热点</div>
        </div>
        <div class="hotInfo">
            <div class="infoText">
                张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚
            </div>            
        </div>
        <div class="infoBottom">
            <img class="hotIcon" src="img/hotIcon.png">
            <span class="hotValue">99</span><span class="planText">/热度</span><span class="infoSpace"></span><span class="planText font14">来源:</span><img class="infoIcon" style="right:66px" src="img/weibo.png" alt="微博">
            <img class="infoIcon" style="right:44px" src="img/wechat.png" alt="微信">
            <img class="infoIcon" style="right:22px" src="img/zhihu.png" alt="知乎">
            <img class="infoIcon" style="right:0" src="img/baidu.png" alt="百度">
        </div>
    </div>
    <div class="triangle"></div>
</div>
<script src="lib/jquery-1.12.0.min.js"></script>
<script src="lib/jsrender.min.js"></script>
<script src="lib/bootstrap.min.js"></script>
<script src="lib/underscore-min.js"></script>
<script src="js/common.js"></script>
<script src="lib/jquery.cookie.js"></script>
<script src="js/raphael.js"></script>
<script src="js/templates.js"></script>
<script src="js/util.js"></script>
<script src="js/hotsystem.js"></script>
<script src="js/pop.js"></script>

</body>
</html>