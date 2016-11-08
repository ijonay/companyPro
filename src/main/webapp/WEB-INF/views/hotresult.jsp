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
    
    <div class="result-content">
        <div class="hot-prev"></div>
        <div class="hot-next"></div>
        <div class="result-section">
            <div id="canvas">
                <div class="alertCon">
                    <div class="portrait"></div>
                    <div class="info">
                        <div class="infoTop">
                            <div class="infoTitle">张雨绮结婚</div>
                            <div class="infoConnect">关联此热点</div>
                        </div>
                        <div class="hotLabel">
                            <div>明星娱乐</div>
                            <div>明星结婚</div>
                        </div>
                        <div class="hotInfo">
                            <div class="infoText">张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚</div>            
                        </div>
                        <div class="infoBottom">
                            <img class="hotIcon" src="img/hotIcon.png">
                            <span class="hotValue">99</span><span class="planText">/热度</span><span class="infoSpace"></span><span class="planText font14">来源:</span>
                            <div class="iconCon">
                                <img class="infoIcon" src="img/weibo.png" alt="微博">
                                <img class="infoIcon" src="img/wechat.png" alt="微信">
                                <img class="infoIcon" src="img/zhihu.png" alt="知乎">
                                <img class="infoIcon" src="img/baidu.png" alt="百度">
                            </div>
                        </div>
                    </div>
                </div><!-- 弹窗end -->
            </div><!-- 画图区域end -->
        </div><!-- 画布背景end -->
    </div>

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