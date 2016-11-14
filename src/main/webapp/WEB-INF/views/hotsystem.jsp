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
			 	<p>请输入汉字、英文或数字</p>
			 </div>
			 <div class="pointer  f16 ac fr borderShadow" id="ser_btn_high">高级探索</div>
			 <div class="pointer ser_btn f16 ac" id="ser_btn">探索</div>

			 <div class="pos favorite_set_btn pointer hidecommon f14" id="favorite_set_btn">设为常用</div>			 
		</div>
		
		<div class="favorite_div f12">
			<span class="favorite_set_text">常用搜索:</span>
			<ul class="favorite_ul" id="favorite_ul">
				
			</ul>
		</div>
   </div>
   <div class="ser_dialog hidecommon" id="ser_dialog">
   		<div class="dialog_area corlor4a f18">
   			<div class="dialog_title">高级探索<span class="ser_dialog_close pointer"></span></div>
   			<p style="height:40px;font-size:16px;color:#000;padding-left:20px;line-height:40px;">事件标签(按照热点事件标签筛选)</p>
   			<ul class="dialog_tab eventDialogTab f14 corlor4a">
   				
   			</ul>
   			

   			<div class="dislog_inp_con eventTab dialog_tab_event">
   			
   			</div>
   			
   			<ul class="dialog_tab eventDialogTab2 f14 corlor4a">
   				
   			</ul>
   			
   			<div class="dislog_inp_con eventTab2 dialog_tab_event">
   				
   			</div>
   			
   			<p style="height:40px;font-size:16px;color:#000;padding-left:20px;line-height:40px;">受众特征(按照热点受众特征筛选)</p>
   			
   			
   			<ul class="dialog_tab userDialogTab f14 corlor4a">
   				
   			</ul>
   			
   			<div class="dislog_inp_con personTab dialog_tab_person">
   				
   			</div>
   			
   			<div class="dialog_inp_c f12 hidecommon">
   				<ol class="fl dialog_inp_c_data">
   					<li id="inp_data_event" class="hidecommon"><span></span></li>
   					<li id="inp_data_person1" class="hidecommon">
   						<div class="person_sec pst hidecommon"><span></span></div>
   						<div class="person_education pst hidecommon"><span></span></div>
   						<div class="person_area pst hidecommon"><span></span></div>
   						<div class="person_interest pst hidecommon"><span></span></div>
   					</li>
   				</ol>
   				
   				<div class="fl dialog_inp_del cor389b9f pointer" id="dialog_inp_del">清空已选标签</div>
   				
   			</div>
   			
   			<div class="dialog_ser">
   				<input type="text" placeholder="请输入搜索关键字" maxlength="20" class="f16" id="dialog_ser_text">
			 	<div class="pointer f16 ac fr" id="dialog_ser_to">探索</div>
   			</div>
   		
   		</div>
   </div>
   <div style="width:100%;height:160px;bottom:0;">
       <div id="papersvg"></div>
   </div>
   <div class="allHot pointer" id="allHot">更多热点</div>	
   
   <div class="all_hot hidecommon" id="all_hot">
   		<div class="all_hot_section" id="all_hot_section">
   			<div class="prf comeback_hot pointer" id="comeback_hot">返回首页</div>
   			<!-- <ul class="hot_ifo_title f18">
   			    <li class="hot_ifo_title_img fl"></li>
   				<li class="fl">全部热点</li>
   			</ul> -->
   			<ul class="all_hot_bar">
   				<li>排名</li>
   				<li>热点名称</li>
   				<li>热点来源</li>
   				<li>热度</li>
   				<li>热点受众画像</li>
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
   						<li>点击查看</li>
   						<li class="hot_relation">关联此热点</li>
   						
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
        <div class="hotAlertTag"></div>
        <div class="hotInfo">
            <div class="infoText">
                张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚
            </div>            
        </div>
        <div class="infoBottom">
            <img class="hotIcon" src="img/hotIcon.png">
            <span class="hotValue">99</span><span class="planText">/热度</span><span class="infoSpace"></span><span class="planText font14">来源:</span>
            <div class="iconCon">
	            <img class="infoIcon" id="iconweibo" src="img/hot_weibo.png" alt="微博">
	            <img class="infoIcon" id="iconwechat" src="img/hot_weixin.png" alt="微信">
	            <img class="infoIcon" id="iconzhihu" src="img/hot_zhihu.png" alt="知乎">
	            <img class="infoIcon" id="iconbaidu" src="img/hot_baidu.png" alt="百度">
            </div>
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
<script src="lib/echarts.js"></script>
<script>
$.get('js/china.json', function (chinaJson) {
    echarts.registerMap('china', chinaJson);
});
</script>
<script src="js/hotsystem.js"></script>
<script src="js/calendar.js"></script>
<script src="js/nav.js"></script>
<script src="js/pop.js"></script>
<script id="allHotTmpl" type="text/x-jsrender">

</script>
<script src="lib/jquery.mousewheel.js"></script>
<script src="lib/perfect-scrollbar.js"></script>
</body>
</html>