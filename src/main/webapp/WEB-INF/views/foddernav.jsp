<%@page import="com.zc.model.usermodel.UserSessionModel" %>
<%@page import="com.zc.utility.Constants" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<%@ include file="taglibs.jsp" %>
<div class="header"><!-- head start -->
    <div class="header-logo fl"></div>
    <ul class="header-left fl">
        <li><a href="hotsystem">热点探索</a></li>
        <li><a href="javascript:;">账号分析</a></li>
        <li><a href="fodder">素材探索</a></li>
    </ul>
    <ul class="header-right fr">
        <li class="head-usericon"><a></a></li>
        
        <li class="head-userinfo">
            <shiro:authenticated>
                <div class="userName">
                    <!--<div class="name" title="${current_user.nickName}">${current_user.nickName}</div>-->
                    <div class="name"></div>
                    <div class="company"></div>
                </div>
            </shiro:authenticated>
        </li>
    </ul>
    <div class="pnl-user"><!-- 账号信息弹窗 开始-->
        <div class="top">
            <div class="top-icon"></div>
            <div class="top-info">
                <div class="name"></div>
                <div class="company"></div>
            </div>
        </div>
        <div class="user-btn user-set">账号设置</div>
        <div class="user-btn user-logout" onclick="logOut()">退出系统</div>
    </div><!-- 账号信息弹窗 结束-->
</div><!-- head end -->
