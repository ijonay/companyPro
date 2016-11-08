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
    <div style="height:60px;background:#fff;"></div>
    <div class="result-section">
        <div id="canvas"></div>
    </div>
    <div class="hot-prev"></div>
    <div class="hot-next"></div>
    <div class="result-bottom">
        <div class="fl">当前：1-20<span class="hot-count">/689</span></div>
        <div class="fr">
            <span class="fl">热度：高</span>
            <div class="circle1 fl"></div>
            <div class="circle2 fl"></div>
            <div class="circle3 fl"></div>
            <span class="fl">低</span>
        </div>
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