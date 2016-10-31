<%@page import="com.zc.model.usermodel.UserSessionModel" %>
<%@page import="com.zc.utility.Constants" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<%@ include file="taglibs.jsp" %>
<div class="header">
    <div class="header-left fl">
        
    </div>
    <span>热点营销决策支持系统</span>
    <ul class="header-right fr">
        <li data-type="search"><img src="img/nav_search_normal.png"/></li>
        <li data-type="message" class="talk-weixin"><a
                href="javascript:;"><img src="img/nav_massage_normal.png"/></a></li>
        <li data-type="set"><img src="img/nav_set_normal.png"/></li>
        <li class="linkuser" data-type="linkuser"><img src="img/touxiang.png"/>
            <shiro:authenticated>
                <div class="userName">
                    <div class="name" title="${current_user.nickName}">${current_user.nickName}</div>
                    <div class="company" title="知藏">知藏</div>
                </div>
            </shiro:authenticated>
        </li>
        <li class="overview" data-type="overview"><img src="img/overview_unfold_down.png"/></li>
    </ul>
    <ul class="user-operate">
        <li><span><em></em></span></li>
        <li><a href="javascript:;">个人资料</a></li>
        <li class="recyclebin">回收站</li>
        <li><a href="${ctx}/logout">退出</a></li>
    </ul>
</div>