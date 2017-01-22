<%@page import="com.zc.model.usermodel.UserSessionModel" %>
<%@page import="com.zc.utility.Constants" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<%@ include file="taglibs.jsp" %>
<div class="header"><!-- head start -->
    <div class="header-logo fl"></div>
    <ul class="header-right fr">
    	<li class="nav_ser hidecommon">
    		<input type="text" placeholder="请输入探索关键字" maxlength="10" class="f16" id="nav_ser">
    		<div class="head-search" id="nav-head-search"></div>
    	</li>
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
        <li class="head-pred"><a></a></li>
        <li class="head-notify"><a><span class="notify-count">6</span></a></li>
    </ul>
    <ul class="notify-list"><!-- 通知列表 -->
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

<div class="right-bar"><!-- right-bar start -->
    <div class="bar-content"><!-- bar-content start -->
        <ul class="bar-tabs"><!-- bar-tabs start -->
            <li class="pred-tab active">热点预告</li>
            <li class="notify-tab">探索通知<span class="notify-count">6</span></li>
            <li class="right-bar-close">收起</li>
        </ul><!-- bar-tabs end -->
        <div class="pnl-pred-tab"><!-- pnl-pred-tab start -->
            <div class="pnl-calendar"></div>
            <div class="pnl-hots"><!-- pnl-hots start -->
                <div class="hots-content"><!-- hots-content start -->
                    <div class="hots-title">
                        <span class="hots-date"></span>
                        <span class="hots-add">&#43;</span>
                    </div>
                    <ul class="hots-list"></ul>
                </div><!-- hots-content end -->
                <div class="hots-detail"><!-- hots-detail start -->
                    <div class="detail-title">
                        <span class="detail-date"></span>
                        <span class="detail-return">查看当月</span>
                    </div>
                    <ul class="detail-list"></ul>
                </div><!-- hots-detail end -->
            </div><!-- pnl-hots end -->
        </div><!-- pnl-pred-tab end -->
        <div class="pnl-notify-tab">
            <ul class="notify-tab-list">
            
            </ul>
        </div>
    </div><!-- bar-content start -->
    <div class="notify-operate"><!-- notify-operate start -->
        <div class="relate-email fl">
            <input type="checkbox" id="chk-email" class="chk-email" /><label for="chk-email" class="lb-email"><span class="lb-circle"></span></label>
            <span class="relate-title">通知发送至邮箱</span>
        </div>
        <div class="clear-notify fr">清空通知</div>
    </div><!-- notify-operate end -->
</div><!-- right-bar start -->

<div class="pred-detail"><!-- pred-detail start -->
    <div class="detail-top">
        <div class="title">北京2022年冬季奥运会</div>
        <div class="desc" style="display:none;">描述信息描述信息描述信息描述信息描述信息描述信息描述信息描述信息描述信息描述信息描述信息描述信息息描述信息描述信息息描述信息描述信息</div>
    </div>
    <ul class="source-list">
        <li class="weibo"><!-- weibo start -->
            <div class="top" style="background-image:url(img/hot_weibo.png)">
                <div class="fl">推荐微博：</div>
                <div class="fl title"><a target='_blank' href="javascript:;">北京2022年冬季奥运会</a></div>
            </div>
            <div class="bottom">
                <div class="left fl">
                    <div class="fl icon" style="background-image:url(img/weibo-share.png)"></div>
                    <div class="fl count share-count">1000</div>
                </div>
                <div class="middle fl">
                    <div class="fl icon" style="background-image:url(img/weibo-coment.png)"></div>
                    <div class="fl count coment-count">2890</div>
                </div>
                <div class="right fr" style="margin-right:0px;">
                    <div class="fl icon" style="background-image:url(img/weibo-zan.png)"></div>
                    <div class="fl count zan-count">9800</div>
                </div>
            </div>
        </li><!-- weibo end -->
        <li class="weixin"><!-- weixin start -->
            <div class="top" style="background-image:url(img/hot_weixin.png)">
                <div class="fl">推荐文章：</div>
                <div class="fl title"><a target='_blank' href="javascript:;">北京2022年冬季奥运会</a></div>
            </div>
            <div class="bottom">
                <div class="right fr" style="margin-right:0px;">
                    <div class="fl icon" style="background-image:url(img/weixin-view.png)"></div>
                    <div class="fl count view-count">9800</div>
                </div>
            </div>
        </li><!-- weixin start -->
        <li class="baidu"><!-- baidu start -->
            <div class="top" style="background-image:url(img/hot_baidu.png)">
                <div class="fl">搜索结果：</div>
                <div class="fl title"><a target='_blank' href="javascript:;">北京2022年冬季奥运会</a></div>
            </div>
            <div class="bottom">
                <div class="right fr" style="margin-right:0px;">
                    <div class="fl icon" style="background-image:url(img/baidu-search.png)"></div>
                    <div class="fl count search-count">9800</div>
                </div>
            </div>
        </li><!-- baidu end -->
        <li class="zhihu"><!-- zhihu start -->
            <div class="top" style="background-image:url(img/hot_zhihu.png)">
                <div class="fl">推荐回答：</div>
                <div class="fl title"><a target='_blank' href="javascript:;">北京2022年冬季奥运会</a></div>
            </div>
            <div class="bottom">
                <div class="right fr" style="margin-right:0px;">
                    <div class="fl icon" style="background-image:url(img/weibo-coment.png)"></div>
                    <div class="fl count answer-count">9800</div>
                </div>
            </div>
        </li><!-- zhihu end -->
    </ul>
</div><!-- pred-detail end -->