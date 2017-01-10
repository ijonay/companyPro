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
        <div class="filter-list" style="height:204px;background:green;"></div>
    </div>
    
    <!-- 搜索结果区域 -->
    <div class="section-filter"  style="height:204px;background:pink;display:none;">
    </div>
  
    <script src="js/fodder.js"></script>
</body>
</html>