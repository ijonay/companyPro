<%@page language="java" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>素材探索</title>
<link  rel="stylesheet" href="css/bootstrap.min.css">
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
    	<div style="width:100%;height:200px;background:#0f0;"></div>
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
        <div class="filter-list" style="height:204px;background:green;"></div>
    </div>
    
    <!-- 搜索结果区域 -->
    <div class="section-filter"  style="height:204px;background:pink;display:none;">
    </div>
    
    <script src="lib/jquery-1.12.0.min.js"></script>
    <script src="js/util.js"></script>
    <script src="js/dialog.js"></script>
    <script src="js/foddernav.js"></script>
    <script src="js/common.js"></script>
    <script src="js/fodder.js"></script>
</body>
</html>