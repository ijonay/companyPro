<%@page language="java" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp" />
<link rel="stylesheet" href="css/hotsystem.css">
<link rel="stylesheet" type="text/css" href="css/pop.css">

<title>热点分析</title>
</head>
<body>
    <jsp:include page="nav.jsp" />
   <div class="ser_section" id="ser_section">
   		<div class="ser_con row pst">
			 <input type="text" placeholder="请输入搜索关键字" maxlength="20" class="ser_text f16" id="ser_text">
			 <div class="ser_hint pos f14 cor389b9f hidecommon" id="ser_hint">
			 	<span>请输入汉字、英文或数字</span>
			 </div>
			 <div class="pointer  f16 ac fr" id="ser_btn_high">高级探索</div>
			 <div class="pointer ser_btn f16 ac" id="ser_btn">探索</div>
			 <div class="pos favorite_set_btn pointer hidecommon f14" id="favorite_set_btn">设为常用</div>			 
		</div>
		
		<div class="favorite_div f12">
			<span class="favorite_set_text">常用搜索:</span>
			<ul class="favorite_ul" id="favorite_ul">
				<li title="1手手机手机手机手机手机手机手机手机机1">手机机1<span></span></li>
				<li title="苹果苹果苹果苹果苹果苹果苹果苹果苹果苹果1">果苹果苹果苹果苹果1<span></span></li>
				<li title="三星三星三星三星三星三星三星三星三星三星1">星三星三星三星三星1<span></span></li>
				<li title="华为华为华为华为华为华为华为华为华为华为华为华为1">为华为华为华为华为华为华为华为1<span></span></li>
				<li title="华为华为华为华为华为华为华为华为华为华为华为华为1">为华为华为华为华为华为1<span></span></li>
			</ul>
		</div>
   </div>
   <div class="ser_dialog hidecommon" id="ser_dialog">
   		<div class="dialog_area corlor4a f18">
   			<div class="dialog_title">高级探索<span class="ser_dialog_close pointer"></span></div>
   			<p style="height:40px;font-size:16px;color:#000;padding-left:20px;line-height:40px;">事件标签筛选</p>
   			<ul class="dialog_tab eventDialogTab f14 corlor4a">
   				<li class="pst">政治经济<span class="pos dialog_inp_num">0</span></li>
   				<li class="pst">政治经济<span class="pos dialog_inp_num">0</span></li>
   				<li class="pst">政治经济<span class="pos dialog_inp_num">0</span></li>
   			</ul>
   			

   			<div class="dislog_inp_con eventTab dialog_tab_event">
   				<ul class="hidecommon">
   					<li class="inp_ch_list fl">
   						<label><input type="checkbox" data-id="1">医药安全1</label>
   						<label><input type="checkbox" data-id="2">医药安全2</label>
   						<label><input type="checkbox" data-id="3">医药安全3</label>
   					</li>
   					<li class="inp_select_all fr">
   						<label><input type="checkbox">全选</label>
   					</li>
   				</ul>
   				<ul class="hidecommon">
   					<li class="inp_ch_list fl">
   						<label><input type="checkbox" data-id="4">医药安全4</label>
   						<label><input type="checkbox" data-id="5">医药安全5</label>
   						<label><input type="checkbox" data-id="6">医药安全6</label>
   						
   					</li>
   					<li class="inp_select_all fr">
   						<label><input type="checkbox">全选</label>
   					</li>
   				</ul>
   				<ul class="hidecommon">
   					<li class="inp_ch_list fl">
   						
   					</li>
   					<li class="inp_select_all fr">
   						<label><input type="checkbox">全选</label>
   					</li>
   				</ul>
   			</div>
   			
   			<ul class="dialog_tab eventDialogTab2 f14 corlor4a">
   				<li class="pst">政治经济<span class="pos dialog_inp_num">0</span></li>
   				<li class="pst">政治经济<span class="pos dialog_inp_num">0</span></li>
   				<li class="pst">政治经济<span class="pos dialog_inp_num">0</span></li>
   			</ul>
   			
   			<div class="dislog_inp_con eventTab2 dialog_tab_event">
   				<ul class="hidecommon">
   					<li class="inp_ch_list fl">
   						
   						
   					</li>
   					<li class="inp_select_all fr">
   						<label><input type="checkbox">全选</label>
   					</li>
   				</ul>
   				<ul class="hidecommon">
   					<li class="inp_ch_list fl">
   						
   						
   					</li>
   					<li class="inp_select_all fr">
   						<label><input type="checkbox">全选</label>
   					</li>
   				</ul>
   				<ul class="hidecommon">
   					<li class="inp_ch_list fl">
   						
   						
   					</li>
   					<li class="inp_select_all fr">
   						<label><input type="checkbox">全选</label>
   					</li>
   				</ul>
   			</div>
   			
   			<p style="height:40px;font-size:16px;color:#000;padding-left:20px;line-height:40px;">人群标签筛选</p>
   			
   			
   			<ul class="dialog_tab userDialogTab f14 corlor4a">
   				<li class="pst">政治经济<span class="pos dialog_inp_num">0</span></li>
   				<li class="pst">政治经济<span class="pos dialog_inp_num">0</span></li>
   				<li class="pst">政治经济<span class="pos dialog_inp_num">0</span></li>
   			</ul>
   			
   			<div class="dislog_inp_con personTab dialog_tab_person">
   				<ul class="hidecommon">
   					<li class="inp_ch_list fl">
   						<label><input type="checkbox" data-id="7">医药安全fff</label>
   						<label><input type="checkbox" data-id="8">医药安全fff</label>
   						<label><input type="checkbox" data-id="9">医药安全ddd</label>
   						
   					</li>
   					<li class="inp_select_all fr">
   						<label><input type="checkbox">全选</label>
   					</li>
   				</ul>
   				<ul class="hidecommon">
   					<li class="inp_ch_list fl">
   						<label><input type="checkbox" data-id="10">医药安全aa</label>
   						<label><input type="checkbox" data-id="11">医药安全ss</label>
   						<label><input type="checkbox" data-id="12">医药安全cc</label>
   						
   					</li>
   					<li class="inp_select_all fr">
   						<label><input type="checkbox">全选</label>
   					</li>
   				</ul>
   				<ul class="hidecommon">
   					<li class="inp_ch_list fl">
   						
   						
   					</li>
   					<li class="inp_select_all fr">
   						<label><input type="checkbox">全选</label>
   					</li>
   				</ul>
   			</div>
   			
   			<div class="dialog_inp_c f12 hidecommon">
   				<ol class="fl dialog_inp_c_data">
   					<li id="inp_data_event" class="hidecommon"><span></span></li>
   					<li id="inp_data_person" class="hidecommon"><span></span></li>
   				</ol>
   				
   				<div class="fl dialog_inp_del cor389b9f pointer" id="dialog_inp_del">清空已选标签</div>
   				
   			</div>
   			
   			<div class="dialog_ser">
   				<input type="text" placeholder="请输入搜索关键字" maxlength="20" class="f16">
			 	<div class="pointer f16 ac fr" >探索</div>
   			</div>
   		
   		</div>
   </div>
   <div style="width:100%;height:160px;bottom:0;">
       <div id="papersvg"></div>
   </div>
   <div class="allHot pointer" id="allHot">全部热点</div>	
   
   <div class="all_hot hidecommon" id="all_hot">
   		<div class="all_hot_section" id="all_hot_section">
   			<div class="prf comeback_hot pointer" id="comeback_hot">返回首页</div>
   			<ul class="hot_ifo_title f18">
   			    <li class="hot_ifo_title_img fl"></li>
   				<li class="fl">全部热点</li>
   			</ul>
   			<ul class="all_hot_bar">
   				<li>排名</li>
   				<li>热点名称</li>
   				<li>来源</li>
   				<li>热度</li>
   				<li>操作</li>
   			</ul>
   			<ul class="all_hot_list">
   				<li>
   					<ul class="all_hot_list_top">
   						<li>
   							<span  class="hot_circle_num">1</span>
   						</li>
   						<li class="all_hot_top_topic">
   							<p>热地名称热地名称</p>
   							<div>执法</div>
   							<div>刑事犯罪</div>
   						</li>
   						<li class="all_hot_list_top_source">
   							<div></div><div></div><div></div><div></div>
   						</li>
   						<li>99</li>
   						<li class="hot_relation">关联此热点</li>
   						<li class="hot_arrow"></li>
   					</ul>
   					<ul class="all_hot_list_bot">
   					    <li class="li-first">
   					        <div class="type-img fl"><div style="width:48px;height:48px;background:#fff;margin-top:18px;"></div></div>
   					        <div class="type-content fl">
   					            <div style="line-height:18px;"><span class="content-article">河北邢台-商贩捅伤3城管致1死，警方正全力追捕。</span><span class="content-sum">阅读量：<b>30004万</b></span></div>
   					            <div class="content-desc">河北邢台-商贩捅伤3城管致1死，警方正全力追捕。</div>
   					        </div>
   					    </li>
   						<li>
   						   <div class="type-icon fl"></div>
   						   <span class="type-title weixin-color fl">微信</span>
   						   <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
   						   <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
   						</li>
   						<li>
                           <div class="type-icon fl" style="background-image:url(img/hot_baidu.png)"></div>
                           <span class="type-title baidu-color fl">百度</span>
                           <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
                           <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
                        </li>
                        <li>
                           <div class="type-icon fl" style="background-image:url(img/hot_zhihu.png)"></div>
                           <span class="type-title zhihu-color fl">知乎</span>
                           <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
                           <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
                        </li>
                        <li>
                           <div class="type-icon fl" style="background-image:url(img/hot_weibo.png)"></div>
                           <span class="type-title weibo-color fl">微博</span>
                           <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
                           <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
                        </li>
   					</ul>
   				</li>
   				<li>
   					<ul class="all_hot_list_top">
   						<li>
   							<span  class="hot_circle_num">1</span>
   						</li>
   						<li class="all_hot_top_topic">
   							<p>热地名称热地名称</p>
   							<div>执法</div>
   							<div>刑事犯罪</div>
   						</li>
   						<li class="all_hot_list_top_source">
   							<div></div><div></div><div></div><div></div>
   						</li>
   						<li>99</li>
   						<li class="hot_relation">关联此热点</li>
   						<li class="hot_arrow"></li>
   					</ul>
					<ul class="all_hot_list_bot">
   					    <li class="li-first">
   					        <div class="type-img fl"><div style="width:48px;height:48px;background:#fff;margin-top:18px;"></div></div>
   					        <div class="type-content fl">
   					            <div style="line-height:18px;"><span class="content-article">河北邢台-商贩捅伤3城管致1死，警方正全力追捕。</span><span class="content-sum">阅读量：<b>30004万</b></span></div>
   					            <div class="content-desc">河北邢台-商贩捅伤3城管致1死，警方正全力追捕。</div>
   					        </div>
   					    </li>
   						<li>
   						   <div class="type-icon fl"></div>
   						   <span class="type-title weixin-color fl">微信</span>
   						   <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
   						   <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
   						</li>
   						<li>
                           <div class="type-icon fl" style="background-image:url(img/hot_baidu.png)"></div>
                           <span class="type-title baidu-color fl">百度</span>
                           <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
                           <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
                        </li>
                        <li>
                           <div class="type-icon fl" style="background-image:url(img/hot_zhihu.png)"></div>
                           <span class="type-title zhihu-color fl">知乎</span>
                           <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
                           <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
                        </li>
                        <li>
                           <div class="type-icon fl" style="background-image:url(img/hot_weibo.png)"></div>
                           <span class="type-title weibo-color fl">微博</span>
                           <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
                           <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
                        </li>
   					</ul>
   				</li>
   				<li>
   					<ul class="all_hot_list_top">
   						<li>
   							<span  class="hot_circle_num">1</span>
   						</li>
   						<li class="all_hot_top_topic">
   							<p>热地名称热地名称</p>
   							<div>执法</div>
   							<div>刑事犯罪</div>
   						</li>
   						<li class="all_hot_list_top_source">
   							<div></div><div></div><div></div><div></div>
   						</li>
   						<li>99</li>
   						<li class="hot_relation">关联此热点</li>
   						<li class="hot_arrow"></li>
   					</ul>
   					<ul class="all_hot_list_bot">
   					    <li class="li-first">
   					        <div class="type-img fl"><div style="width:48px;height:48px;background:#fff;margin-top:18px;"></div></div>
   					        <div class="type-content fl">
   					            <div style="line-height:18px;"><span class="content-article">河北邢台-商贩捅伤3城管致1死，警方正全力追捕。</span><span class="content-sum">阅读量：<b>30004万</b></span></div>
   					            <div class="content-desc">河北邢台-商贩捅伤3城管致1死，警方正全力追捕。</div>
   					        </div>
   					    </li>
   						<li>
   						   <div class="type-icon fl"></div>
   						   <span class="type-title weixin-color fl">微信</span>
   						   <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
   						   <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
   						</li>
   						<li>
                           <div class="type-icon fl" style="background-image:url(img/hot_baidu.png)"></div>
                           <span class="type-title baidu-color fl">百度</span>
                           <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
                           <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
                        </li>
                        <li>
                           <div class="type-icon fl" style="background-image:url(img/hot_zhihu.png)"></div>
                           <span class="type-title zhihu-color fl">知乎</span>
                           <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
                           <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
                        </li>
                        <li>
                           <div class="type-icon fl" style="background-image:url(img/hot_weibo.png)"></div>
                           <span class="type-title weibo-color fl">微博</span>
                           <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
                           <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
                        </li>
   					</ul>
   				</li>
   				<li>
   					<ul class="all_hot_list_top">
   						<li>
   							<span  class="hot_circle_num">1</span>
   						</li>
   						<li class="all_hot_top_topic">
   							<p>热地名称热地名称</p>
   							<div>执法</div>
   							<div>刑事犯罪</div>
   						</li>
   						<li class="all_hot_list_top_source">
   							<div></div><div></div><div></div><div></div>
   						</li>
   						<li>99</li>
   						<li class="hot_relation">关联此热点</li>
   						<li class="hot_arrow"></li>
   					</ul>
   					<ul class="all_hot_list_bot">
   					    <li class="li-first">
   					        <div class="type-img fl"><div style="width:48px;height:48px;background:#fff;margin-top:18px;"></div></div>
   					        <div class="type-content fl">
   					            <div style="line-height:18px;"><span class="content-article">河北邢台-商贩捅伤3城管致1死，警方正全力追捕。</span><span class="content-sum">阅读量：<b>30004万</b></span></div>
   					            <div class="content-desc">河北邢台-商贩捅伤3城管致1死，警方正全力追捕。</div>
   					        </div>
   					    </li>
   						<li>
   						   <div class="type-icon fl"></div>
   						   <span class="type-title weixin-color fl">微信</span>
   						   <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
   						   <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
   						</li>
   						<li>
                           <div class="type-icon fl" style="background-image:url(img/hot_baidu.png)"></div>
                           <span class="type-title baidu-color fl">百度</span>
                           <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
                           <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
                        </li>
                        <li>
                           <div class="type-icon fl" style="background-image:url(img/hot_zhihu.png)"></div>
                           <span class="type-title zhihu-color fl">知乎</span>
                           <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
                           <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
                        </li>
                        <li>
                           <div class="type-icon fl" style="background-image:url(img/hot_weibo.png)"></div>
                           <span class="type-title weibo-color fl">微博</span>
                           <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
                           <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
                        </li>
   					</ul>
   					
   				</li>
   				
   			</ul>
   		</div>
   </div>
   <div class="alertCon">
    <div class="portrait"></div>
    <div class="info">
        <div class="infoTop">
            <div class="infoTitle">张雨绮结婚</div>
            <div class="infoConnect">关联此热点</div>
        </div>
        <div class="hotInfo">
            <div class="infoText">
                张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚
            </div>            
        </div>
        <div class="infoBottom">
            <img class="hotIcon" src="img/hotIcon.png">
            <span class="hotValue">99</span><span class="planText">/热度</span><span class="infoSpace"></span><span class="planText font14">来源:</span><img class="infoIcon" style="right:66px" src="img/weibo.png" alt="微博">
            <img class="infoIcon" style="right:44px" src="img/wechat.png" alt="微信">
            <img class="infoIcon" style="right:22px" src="img/zhihu.png" alt="知乎">
            <img class="infoIcon" style="right:0" src="img/baidu.png" alt="百度">
        </div>
    </div>
    <div class="triangle"></div>
</div>
<ul class="cook_ul pos hidecommon" id="cook_ul">
   			<li>手机2<span></span></li>
			<li>苹果2<span></span></li>
			<li>三星2<span></span></li>
			<li>华为2<span></span></li>			
   		 </ul>
<script src="lib/jquery-1.12.0.min.js"></script>
<script src="lib/jsrender.min.js"></script>
<script src="lib/bootstrap.min.js"></script>
<script src="lib/underscore-min.js"></script>
<script src="js/common.js"></script>
<script src="lib/jquery.cookie.js"></script>
<script src="js/raphael.js"></script>
<script src="js/templates.js"></script>
<script src="js/util.js"></script>
<script src="js/hotsystem.js"></script>
<script src="js/calendar.js"></script>
<script src="js/nav.js"></script>
<script src="js/pop.js"></script>

</body>
</html>