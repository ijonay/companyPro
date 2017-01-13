<%@page language="java" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>素材探索</title>
<link  rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/hotresult.css">
<link  rel="stylesheet" href="css/common.css">
<link  rel="stylesheet" href="css/foddernav.css">
<link  rel="stylesheet" href="css/fodder.css">
<link  rel="stylesheet" href="css/pop.css">
</head>
<body>
    <!-- 顶部导航 -->
    <jsp:include page="foddernav.jsp" />
    
    <!-- 搜索框 -->
    <div class="section-search">
        <input type="text" class="txt-search" placeholder="请输入运营需求关键词，多个词用顿号隔开">
        <div class="btn-search"></div>
    </div>
    <!-- 搜索文章区域 -->
    <div class="section-filter-ser" >
    	<p class="tile-suggest ac">标题结构建议</p>
    	<p class="tile-suggest-n ac">最符合运营需求的“结构化标题类型推荐”及“文章参考”</p>
    	<div style="width:100%;overflow:hidden;">
    		<div class="fl jiegou-con"></div>
    		<div class="fl jiegou-con jiegou-con-mar"></div>
    		<div class="fl jiegou-con"></div>
    	</div>
    	<p class="tile-suggest ac">相关热点推荐</p>
    	<p class="tile-suggest-n ac">与“运营需求相关关键词”相匹配的“热点话题”推荐</p>
    	<div style="position:relative;width:100%;height:320px;background:#fff;text-align:center;border-radius:4px;-moz-box-shadow:0px 2px 5px #383838; -webkit-box-shadow:0px 2px 5px #383838; box-shadow:0px 2px 5px #383838;">
    		<div id="wordCon"></div>
    		<div  id="canvas" style="display:inline-block;height:320px;width:1076px;background-image:url(./img/metrailBackground.png);background-position:center center;background-repeat:no-repeat;background-size:cover">
    		     <div class="alertCon" style="color:#000;text-align:left">
                    <div class="portrait"></div>
                    <div class="info">
                        <div class="infoTop">
                            <div class="infoTitle"></div>
                            <div class="hotLeft" style="float:right;line-height:24px;">  
                                <div class="hotIcon fl"></div>
                                <div class="font14 fl">热度:</div>
                                <div class="hotValue fl"></div>
                                <div class="hotTrend fl"></div>
                            </div>
                        </div>
                        <div class="hotLabel">
                            
                        </div>
                        <div class="hotInfo">
                            <div class="infoText"></div>            
                        </div>
                        <div class="infoBottom">
                            <!-- <div class="hotLeft fl">  
                                <div class="hotIcon fl"></div>
                                <div class="font14 fl">热度:</div>
                                <div class="hotValue fl"></div>
                                <div class="hotTrend fl"></div>
                            </div> -->
                            <div class="font14 fl">来源： </div>
                            <div class="font14 fl">
	                            <a target="_blank" href=""><div class="weiboIcon"></div></a>
	                            <a target="_blank" href=""><div class="weixinIcon"></div></a>
	                            <a target="_blank" href=""><div class="baiduinIcon"></div></a>
	                            <a target="_blank" href=""><div class="zhihuIcon"></div></a>
                            </div>
                        </div>
                    </div>
                </div><!-- 弹窗end -->
    		</div>
    	</div>
    	<div class="ser-back-home ac">首页</div>
    </div>
    <!-- 搜索文章区域end -->
    
    
    <!-- 热门文章区域 -->
    <div class="section-filter" >
        <!-- 筛选热门文章 -->
        <div class="filter-label">
            <div class="tip-title">没有灵感？看看热门文章聚合榜</div>
            <div class="tip-icon"></div>
            <ul class="filter-list">
                <li class="attention">竞品关注</li>
                <li class="area">所属领域</li>
                <li class="type">文章类型</li>
                <li class="time">推送时间</li>
                <li class="ser">
                    <input type="text" placeholder="搜索文章">
                    <div class="btn-search"></div>
                </li>
            </ul>
        </div>
        
        <!-- 热门列表 -->
        <div class="filter-list" style="background:#fff;border-top-left-radius:4px;border-top-right-radius:4px;">
            <div class="list-header">
	        	<span>推送名称</span><span>所属自媒体</span><span>标题结构</span><span>内容关键词</span><span>相关话题</span><span>领域</span><span>推送时间</span><span>阅读量</span><span>综合评价</span>
	        </div>
	        <ul class="listCon">
	        	<li>
	        		<div class="topicTitle"><span class="topicNum">1</span><span class="currentTitle">文章名称</span></div>
	        		<div><span class="mediaOrg">中国南方航空</span><span class="cared">已关注</span></div>
	        		<div><span class="titleStruct colorOrg">强吸引型</span></div>
	        		<div><span class="contentKeyWord">春节、回家、机票、春运、亲情</span></div>
	        		<div><span class="correlatedTopic">#春运一票难求#</span></div>
	        		<div><span class="colorGray">旅行</span></div>
	        		<div><span class="colorGray">2小时前</span></div>
	        		<div><span class="colorGray">247万</span></div>
	        		<div><p class="processBar"><span class="processBarInner"> </span></p></div>
	        	</li>
	        	<li>
	        		<div class="topicTitle"><span class="topicNum">2</span><span class="currentTitle">文章名称</span></div>
	        		<div><span class="mediaOrg">中国南方航空</span><span class="careLess">+ 关注</span></div>
	        		<div><span class="titleStruct colorGreen">强吸引型</span></div>
	        		<div><span class="contentKeyWord">春节、回家、机票、春运、亲情</span></div>
	        		<div><span class="correlatedTopic">#春运一票难求#</span></div>
	        		<div><span class="colorGray">旅行</span></div>
	        		<div><span class="colorGray">2小时前</span></div>
	        		<div><span class="colorGray">247万</span></div>
	        		<div><p class="processBar"><span class="processBarInner"> </span></p></div>
	        	</li>
	        	<li>
	        		<div class="topicTitle"><span class="topicNum">3</span><span class="currentTitle">文章名称</span></div>
	        		<div><span class="mediaOrg">中国南方航空</span><span class="cared">已关注</span></div>
	        		<div><span class="titleStruct colorBlue">强吸引型</span></div>
	        		<div><span class="contentKeyWord">春节、回家、机票、春运、亲情</span></div>
	        		<div><span class="correlatedTopic">#春运一票难求#</span></div>
	        		<div><span class="colorGray">旅行</span></div>
	        		<div><span class="colorGray">2小时前</span></div>
	        		<div><span class="colorGray">247万</span></div>
	        		<div><p class="processBar"><span class="processBarInner"> </span></p></div>
	        	</li>
	        </ul>
        </div>
    </div>
    
    <!-- 搜索结果区域 -->
    <div class="section-filter"  style="height:204px;background:pink;display:none;">
    </div>
    
    <script src="lib/jquery-1.12.0.min.js"></script>
    <script src="lib/echarts.js"></script>
    <script src="lib/echarts-wordcloud.min.js"></script>
    <script src="js/util.js"></script>
    <script src="js/dialog.js"></script>
    <script src="js/foddernav.js"></script>
    <script src="js/common.js"></script>
    <script src="js/fodder.js"></script>    
</body>
</html>