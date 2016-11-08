<%@page language="java" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp" />
<link rel="stylesheet" href="css/hotresult.css">
<link rel="stylesheet" type="text/css" href="css/pop.css">

<title>热点分析</title>
</head>
<body>
    <jsp:include page="nav.jsp" />
    <div class="result_top">
	    <div class="result_t_l fl">
	    	<p>
	    		<span class="corlor4a f14">&lt;</span>
	    		<span class="corlor4a f14">首页</span>
	    		<span class="black f16 pst"></span>
	    		<span class="black f16">探索结果</span>
	    	</p>
	    </div>
	    <div class="result_t_r fr">111</div>
	</div>    
    <div class="clearfix"></div>
    
    <div class="result-section">
        <div id="canvas"></div>
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
<script src="js/hotresult.js"></script>
<script src="js/calendar.js"></script>
<script src="js/nav.js"></script>
<script src="js/pop.js"></script>

</body>
</html>