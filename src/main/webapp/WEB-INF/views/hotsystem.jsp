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
   <div style="width: 100%;height:100px;background: #f00;">
   </div>
  
   <div style="width:100%;height:300px;background:#ff0;">搜索</div>
   
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