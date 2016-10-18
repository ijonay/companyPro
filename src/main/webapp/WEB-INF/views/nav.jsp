<%@page import="com.zc.model.usermodel.UserSessionModel"%>
<%@page import="com.zc.util.Constants"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<div class="header">
	<div class="header-left fl">
		<img src="img/logoHeptax.png" />
	</div>
	<ul class="header-right fr">
		<li data-type="search"><img src="img/nav_search_normal.png" /></li>
		<li data-type="message" class="talk-weixin"><a
			href="javascript:;"><img src="img/nav_massage.png" /></a></li>
		<li data-type="set"><img src="img/nav_set.png" /></li>
		<li class="linkuser" data-type="linkuser"><img src="img/touxiang.png" />
            <shiro:authenticated>
				<div class="userName">
					<div class="name" title="${current_user.userName}">${current_user.userName}</div>
					<div class="company" title="知藏">知藏</div>
				</div>
			</shiro:authenticated>
		</li>
		<li class="overview" data-type="overview"><img src="img/overview_unfold_down.png" /></li>
	</ul>
	<ul class="user-operate">
        <li><span><em></em></span></li>
        <li><a href="#">个人资料</a></li>
        <li class="recyclebin" onclick="window.location.href='recyclebin';">回收站</li>
        <li><a href="${ctx}/logout">退出</a></li>
    </ul>
</div>
<!--导航nav--------------------------------------------------------------------------------->
<div class="nav pst">
	<ul class="nav-menu-ul">
		<li><a href="home">一览表</a></li>
		<li><a href="warningcenter">预警中心</a></li>
		<li><a href="" onclick="return false;">人力资源</a></li>
		<li><a href="" onclick="return false;">财务</a></li>
		<li><a href="" onclick="return false;">市场营销</a></li>
		<li><a href="" onclick="return false;">销售</a></li>
		<li><a href="" onclick="return false;" >定制中心</a></li>
		<li><a href="datacenter">数据中心</a></li>
		<li><a href="" onclick="return false;">收藏</a></li>
		<li><a href="" onclick="return false;">分享</a></li>
		<li><a href="hotspots" class="hotspotsNav">热点分析</a></li>
	</ul>

	<div class="nav-right pos" style="z-index: 5999;top:50px;right:0;">
		<div class="nav-sidebar fl">
			<img src="img/global_unfold_right.png" />
		</div>
		<div class="nav-sideimg fl">
                <img src="img/talk_window.png" />
        </div>
	</div>
</div>