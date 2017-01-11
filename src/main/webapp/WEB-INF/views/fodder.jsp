<%@page language="java" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>素材探索</title>
<link  rel="stylesheet" href="css/common.css">
<link  rel="stylesheet" href="css/fodder.css">
</head>
<body>
    <!-- 顶部导航 -->
    <div class="nav" style="height:60px;width:100%;background:red;"></div>
    
    <!-- 搜索框 -->
    <div class="section-search" style="height:40px;margin-top:156px;background:blue;"></div>
    
    <!-- 热门文章区域 -->
    <div class="section-filter" >
        <!-- 筛选热门文章 -->
        <div class="filter-label" style="height:204px;background:yellow;margin-bottom:60px;"></div>
        
        <!-- 热门列表 -->
        <div class="filter-list" style="height:204px;background:green;border-top-left-radius:4px;border-top-right-radius:4px;">
            <div class="list-header">
	        	<span>推送名称</span><span>所属自媒体</span><span>标题结构</span><span>内容关键词</span><span>相关话题</span><span>领域</span><span>推送时间</span><span>阅读量</span><span>综合评价</span>
	        </div>
	        <ul class="listCon">
	        	<li>
	        		<div class="topicTitle"><span class="topicNum">1</span><span class="currentTitle">文章名称</span></div>
	        		<div><span>中国南方航空</span><span>已关注</span></div>
	        		<div><span class="titleStruct colorOrg">强吸引型</span></div>
	        		<div><span class="contentKeyWord">春节、回家、机票、春运、亲情</span></div>
	        		<div><span class="correlatedTopic">#春运一票难求#</span></div>
	        		<div><span class="colorGray">旅行</span></div>
	        		<div><span class="colorGray">2小时前</span></div>
	        		<div><span class="colorGray">247万</span></div>
	        		<div><p class="processBar"></p></div>
	        	</li>
	        	<li>
	        		<div class="topicTitle"><span class="topicNum">1</span><span>文章名称</span></div>
	        		<div><span>中国南方航空</span><span>已关注</span></div>
	        		<div><span class="titleStruct colorOrg">强吸引型</span></div>
	        		<div><span class="contentKeyWord">春节、回家、机票、春运、亲情</span></div>
	        		<div><span>#春运一票难求#</span></div>
	        		<div><span>2小时前</span></div>
	        		<div><span>247万</span></div>
	        		<div><span></span></div>
	        	</li>
	        </ul>
        </div>
    </div>
    
    <!-- 搜索结果区域 -->
    <div class="section-filter"  style="height:204px;background:pink;display:none;">
    </div>
  
    <script src="js/fodder.js"></script>
</body>
</html>