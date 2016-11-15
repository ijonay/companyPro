<%@page import="com.zc.model.usermodel.UserSessionModel" %>
<%@page import="com.zc.utility.Constants" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<%@ include file="taglibs.jsp" %>
<div class="header">
    <div class="header-logo fl"></div>
    <ul class="header-right fr">
    	<li class="nav_ser hidecommon">
    		<input type="text" placeholder="请输入搜索关键字" maxlength="10" class="f16" id="nav_ser">
    		<div class="head-search" id="nav-head-search"></div>
    	</li>
        <li class="head-usericon"><a></a></li>
        <li class="head-userinfo">
            <shiro:authenticated>
                <div class="userName">
                    <!--<div class="name" title="${current_user.nickName}">${current_user.nickName}</div>-->
                    <div class="name">123456@heptax.com</div>
                    <div class="company">公司名称</div>
                </div>
            </shiro:authenticated>
        </li>
        <li class="head-pred"><a></a></li>
        <li class="head-notify"><a><span class="notify-count">6</span></a></li>
    </ul>
    <ul class="notify-list">
    </ul>
    <div class="pnl-user">
        <div class="top">
            <div class="top-icon"></div>
            <div class="top-info">
                <div class="name">123456@heptax.com</div>
                <div>公司名称</div>
            </div>
        </div>
        <div class="user-btn user-set">账号设置</div>
        <div class="user-btn user-logout" onclick="logOut()">退出系统</div>
    </div>
</div>

<div class="right-bar">
    <div class="bar-content">
        <ul class="bar-tabs">
        <li class="pred-tab active">热点预告</li>
        <li class="notify-tab">探索通知<span class="notify-count">6</span></li>
        <li class="right-bar-close">收起</li>
    </ul>
    <div class="pnl-pred-tab">
        <div class="pnl-calendar"></div>
        <div class="pnl-hots">
            <div class="hots-content">
                <div class="hots-title">
                    <span class="hots-date">2016.11.21</span>
                    <span class="hots-add">&#43;</span>
                </div>
                <ul class="hots-list"></ul>
            </div>
            <div class="hots-detail">
                <div class="detail-title">
                    <span class="detail-date">2016.11.21</span>
                    <span class="detail-return">查看当月</span>
                </div>
                <ul class="detail-list"></ul>
            </div>
        </div>
    </div>
    <div class="pnl-notify-tab">
        <ul class="notify-tab-list">
            
        </ul>
    </div>
    </div>
    <div class="notify-operate">
    <div class="relate-email fl">
        <input type="checkbox" id="chk-email" class="chk-email" /><label for="chk-email" class="lb-email"><span class="lb-circle"></span></label>
        <span class="relate-title">通知发送至邮箱</span>
    </div>
    <div class="clear-notify fr">清空通知</div>
</div>
</div>