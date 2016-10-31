<%@page language="java" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp" />
<link rel="stylesheet" href="css/hotsystem.css">

<title>热点分析</title>
</head>
<body>
    <jsp:include page="nav.jsp" />
   <div class="ser_section">
   		<div class="ser_con row pst">
			 <input type="text" placeholder="请输入搜索关键字" maxlength="20" class="ser_text f16" id="ser_text">
			 <div class="pointer ser_btn f16 ac">探索</div>
			 <div class="pos favorite_set_btn pointer hidecommon" id="favorite_set_btn">设置为常用</div>
			 <ul class="cook_ul pos hidecommon" id="cook_ul">
    			<li>手机<span></span></li>
				<li>苹果<span></span></li>
				<li>三星1<span></span></li>
				<li>华为1<span></span></li>			
    		 </ul>
		</div>
		
		<div class="favorite_div f12">
			<span class="favorite_set_text">常用搜索:</span>
			<ul class="favorite_ul" id="favorite_ul">
				<li>手机<span></span></li>
				<li>苹果<span></span></li>
				<li>三星1<span></span></li>
				<li>华为1<span></span></li>
			</ul>
		</div>
   </div>
   
   <div style="width:100%;height:230px;bottom:0;background:ccc">
       <div id="papersvg"></div>
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

</body>
</html>