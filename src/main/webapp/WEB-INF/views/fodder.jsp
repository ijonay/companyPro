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