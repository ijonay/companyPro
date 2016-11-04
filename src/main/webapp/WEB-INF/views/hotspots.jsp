<%@page language="java" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp" />
<link rel="stylesheet" href="css/hotspots.css">
<link rel="stylesheet" href="css/calendar.css" />
<link rel="stylesheet" type="text/css" href="css/pop.css">
	<meta name="keywords" content="热点营销,舆情营销">
	<meta name="description" content="专业数据搜集,语义分析技术,提供热点监控、热点关联、热点分析、热点研判等智力支持，热点数据可视化方式展现，更好的应对营销策略">
<!-- <base target="_blank" /> -->
<title>热点分析</title>
</head>
<body>
    <jsp:include page="nav.jsp" />
    <div class="hotspots-section">
<!--     搜索 -->
    	<div class="hotspots-t-title"></div>
    	<div  class="search-t-div">
    			<ul class="search-t-ul">
    				<li class="fl pointer">综合</li>
    				<li class="fl color9c pointer">最热</li>
    				<li class="fl color9c pointer">最新</li>
    				<li class="fr color9c pointer">搜索设置</li>
    			</ul>
    			<div class="search-con pst">
    				<input type="text" class="search-input" placeholder="请输入关键词" maxlength="25" id="search-input">
    					<div id="set-favorite">
		    				<div class="pos set-favorite">设置为常用</div>
		    				<div class="arrow-left pos"></div>
		    			</div>	
    				<div class="search-btn pointer ac" id="search-btn">
    				<span>探&nbsp;索</span></div>
    				
    				<div class="pos search-hint hidecommon" id="search-hint">
    					<h4 class="ac pst">提示<img src="img/close_icon.png" class="pos close_hint"></h4>
    					<p class="f18 ac">暂时没有符合条件的热点</p>
    					<div class="c-word ac f12">请修改<span> 关键词</span>或<span> 探索设置</span>后进行探索</div>
    				</div>
    				
    				
    				<div class="pos dialog-alert f14 hidecommon" id="dialog-alert">
    					<div class="hidecommon" id="showlabel">没有常用搜索已经搜索历史</div>
    					<ul class="favorite_ul" id="favorite_ul">
    						
    					</ul>
    					<ul class="cook_ul" id="cook_ul">
    						
    					</ul>
    				</div>
    			</div>
		</div>
<!--     主体 -->
		
		<div style="width:960px;float:left">
		<ul class="recommendTitle">
			<li class="recommend-hot fl">实时热点推荐</li>
			<li class="my-fouce fl opa04">我的关注</li>
			<li class="change-c fr opa04" id="change-c">换一批</li>
		</ul>
	<div id="hotspotsMain">	
	</div>
		</div>
<!--     热点预测日历 -->
		<div class="pnl-predict">
		    <div class="predict-title">热点预测</div>
		    <div class="pnl-calendar"></div>
		    <div class="pnl-hots">
		        <div class="hots-title">2016.8.22 热点预期</div>
		        <ul class="hots-list">
		            <li><a href="javascript:void(0)">奥运闭幕式</a></li>
		            <li><a href="javascript:void(0)">冠军</a></li>
		            <li><a href="javascript:void(0)">东京</a></li>
		        </ul>
		    </div>
		</div>
	</div>
<script src="lib/jquery-1.12.0.min.js"></script>

<SCRIPT>
    if (!$.support.leadingWhitespace) {
         alert("浏览器版本太低，请下载chrome或者IE10以上版本浏览器")
    }
</SCRIPT>

<script src="lib/jsrender.min.js"></script>
<script src="lib/bootstrap.min.js"></script>
<script src="lib/underscore-min.js"></script>
<script src="js/common.js"></script>
<script src="lib/jquery.cookie.js"></script>
<script src="js/templates.js"></script>
<script src="js/util.js"></script>
<script src="js/calendar.js" ></script>
<script src="js/pop.js"></script>
<script src="js/hotspots.js" ></script>

</body>
</html>