<%@page import="com.zc.model.usermodel.UserSessionModel" %>
<%@page import="com.zc.utility.Constants" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<%@ include file="taglibs.jsp" %>
<div class="header">
    <div class="header-logo fl"></div>
    <ul class="header-right fr">
        <li class="head-usericon"><a></a></li>
        <li class="head-userinfo">
            <shiro:authenticated>
                <div class="userName">
                    <div class="name" title="${current_user.nickName}">${current_user.nickName}</div>
                    <div class="company" title="知藏">知藏</div>
                </div>
            </shiro:authenticated>
        </li>
        <li class="head-down"></li>
        <li class="head-pred"><a></a></li>
        <li class="head-notify"><a><span class="notify-count">6</span></a></li>
    </ul>
    <ul class="notify-list">
        <li><a>● <span class="hot-word">特斯拉－</span><span class="hot-spot">加油站掺水</span><span class="time">10min</span><span class="notify-close">&times;</span></a></li>
        <li><a>● <span class="hot-word">空气净化器－</span><span class="hot-spot">棉纱堵采样器造假</span><span class="time">18:00</span><span class="notify-close">&times;</span></a></li>
        <li><a>● <span class="hot-word">手机－</span><span class="hot-spot">冤狱男子结婚</span><span class="time">昨天</span><span class="notify-close">&times;</span></a></li>
    </ul>
</div>
<div class="right-bar">
    <ul class="bar-tabs">
        <li class="pred-tab active">热点预告</li>
        <li class="notify-tab">探索通知<span class="notify-count">6</span></li>
        <li class="right-bar-close">&times;</li>
    </ul>
    <div class="pnl-pred-tab">
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
    <ul class="pnl-notify-tab">
        <li><a>● <span class="hot-word">特斯拉－</span><span class="hot-spot">加油站掺水</span><span class="time">10min</span></a></li>
        <li><a>● <span class="hot-word">空气净化器－</span><span class="hot-spot">棉纱堵采样器造假</span><span class="time">18:00</span></a></li>
        <li><a>● <span class="hot-word">手机－</span><span class="hot-spot">冤狱男子结婚</span><span class="time">昨天</span></a></li>
    </ul>
</div>