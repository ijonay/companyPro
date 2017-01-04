<%@page language="java" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp" />
<link rel="stylesheet" href="css/hotsystem.css">
<link rel="stylesheet" href="css/hotComponents.css">
<link rel="stylesheet" type="text/css" href="css/pop.css">
<title>热点分析</title>
</head>
<body>
 
   <jsp:include page="nav.jsp" />
  
   <!-- 用户引导 开始-->
   	   <div class="hot-user-guide">
   	   <div style="width:100%;height:100%;position:relative;">
   	   		<div class="hot-user-tep1 pos pointer"></div>
   	   		<div class="hot-user-tep2 pos pointer"></div>
   	   		<div class="hot-user-tep3 pos pointer"></div>
   	   		<div class="hot-user-tep4 pos pointer"></div>
   	   		<div class="hot-user-tep5 pos pointer"></div>
   	   		<div class="hot-user-tep6 pos pointer"></div>
   	   		<div class="hot-user-tep7 pos pointer"></div>
   	   	</div>	
   	   </div>
   <!-- 用户引导 结束-->
   
	<!-- 更新记录 开始-->
	<div class="record-div">
		<div class="record-con1">
			<div class="record-nav">
				<p>更新通知</p>
			</div>
			<div id="record-ul-con">
<!-- 				<ul class="record-ul"> -->
<!-- 					<div> -->
<!-- 						<p>更新通知名称</p> -->
<!-- 						<p>2016.11.30  13:01</p> -->
<!-- 					</div> -->
<!-- 					<li> -->
<!-- 						<p>1.功能名称</p> -->
<!-- 						<p>更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容 -->
<!-- 						        更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容； -->
<!-- 						</p> -->
<!-- 					</li> -->
<!-- 				</ul> -->
			</div>
			
			
			<div class="record-btn-b">
				<div class="record-btn-b-l" id="record-btn-log">查看更新记录</div>
				<div class="record-btn-b-r" onclick='updateStateChange()'>我知道啦</div>
			</div>
		</div>
		
		<div class="record-con2">
			<div class="record-nav">
				<p>更新记录</p>
			</div>
			
			<ul class="record-ul" id="record-ul-2">
<!-- 				<div> -->
<!-- 					<p>更新通知名称</p> -->
<!-- 					<p>2016.11.30  13:01</p> -->
<!-- 				</div> -->
			<!--	<li>
					<p>1.功能名称<i class="fr f14 corlor4a">2016.11.30  13:01</i></p>
					<p>更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容
					        更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容；
					</p>
				</li>
				<li>
					<p>2.功能名称<i class="fr f14 corlor4a">2016.11.30  13:02</i></p>
					<p>更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容
					        更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容；
					</p>
				</li>
				<li>
					<p>3.功能名称<i class="fr f14 corlor4a">2016.11.30  13:03</i></p>
					<p>更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容
					        更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容；
					</p>
				</li>
				<li>
					<p>4.功能名称<i class="fr f14 corlor4a">2016.11.30  13:04</i></p>
					<p>更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容
					        更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容；
					</p>
				</li>
				<li>
					<p>5.功能名称<i class="fr f14 corlor4a">2016.11.30  13:05</i></p>
					<p>更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容
					        更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容更新内容；
					</p>
				</li>-->
				
			</ul>
			
			<div class="record-btn-b">
				<div class="record-btn-b-l" id="record-btn-near">最近更新</div>
				<div class="record-btn-b-r" onclick='updateStateChange()'>关闭</div>
			</div>
		</div>
		
	</div>
	<!-- 更新记录 结束-->
   <div class="ser_section" id="ser_section">
   		<!-- 更新记录 按钮-->
   		<div id="record-btn-index"></div>
   		<!-- 更新记录 按钮-->
   		
   		<div class="ser_con row pst">
			 <input type="text" placeholder="请输入探索关键字" maxlength="20" class="ser_text f16" id="ser_text">

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
   			<p style="height:40px;font-size:16px;color:#4a4a4a;padding-left:20px;line-height:40px;font-weight:500;"><b style="color:#389b9f">事件标签</b>(按照热点事件标签筛选)</p>
   			<ul class="dialog_tab eventDialogTab f14 corlor4a">
   				
   			</ul>
   			

   			<div class="dislog_inp_con eventTab dialog_tab_event">
   			
   			</div>
   			
   			<ul class="dialog_tab eventDialogTab2 f14 corlor4a">
   				
   			</ul>
   			
   			<div class="dislog_inp_con eventTab2 dialog_tab_event">
   				
   			</div>
   			
   			<p style="height:40px;font-size:16px;color:#4a4a4a;margin:0px 20px;line-height:40px;border-top:1px solid #eee;font-weight:500;"><b style="color:#009944">受众特征</b>(按照热点受众特征筛选)</p>

   			
   			<ul class="userDialog_tag" id="userDialog_tag">
<!--    				<li><label>宅男腐女<input type="checkbox" data-id="1"></label></li> -->
<!--    				<li><label>职场新人<input type="checkbox" data-id="2"></label></li> -->
<!--    				<li><label>家庭主妇<input type="checkbox" data-id="3"></label></li> -->
<!--    				<li><label>时尚达人<input type="checkbox" data-id="4"></label></li> -->
<!--    				<li><label>互联网精英<input type="checkbox" data-id="5"></label></li> -->
<!--    				<li><label>体育迷<input type="checkbox" data-id="6"></label></li> -->
<!--    				<li><label>背包客<input type="checkbox" data-id="7"></label></li> -->
   			</ul>

   			<ul class="dialog_tab userDialogTab f14 corlor4a">
   				
   			</ul>
   			
   			<div class="dislog_inp_con personTab dialog_tab_person" id="dialog-bottom-new">
   				
   			</div>
   			
   			<div class="dialog_inp_c f12 hidecommon">
   				<ol class="fl dialog_inp_c_data">
   					<li id="inp_data_event" class="hidecommon"><span></span></li>
   					
   					<li id="inp_data_person1" class="hidecommon">
   						<p class="person_new_tag hidecommon" id="person_new_tag"></p>

   						<div class="person_sec pst hidecommon"><span></span></div>
   						<div class="person_education pst hidecommon"><span></span></div>
   						<div class="person_area pst hidecommon"><span></span></div>
   						<div class="person_interest pst hidecommon"><span></span></div>
   					</li>
   				</ol>
   				
   				<div class="fl dialog_inp_del  pointer" id="dialog_inp_del">清空已选</div>
   				
   			</div>
   			
   			<div class="dialog_ser">
   				<input type="text" placeholder="请输入探索关键字" maxlength="20" class="f16" id="dialog_ser_text">
			 	<div class="pointer f16 ac fr" id="dialog_ser_to">探索</div>
   			</div>
   		
   		</div>
   </div>
   <div style="width:100%;height:160px;bottom:0;position:relative">
       <div id="papersvg"></div>
   </div>
   <div class="allHot pointer" id="allHot">更多热点</div>	
   <div class="all_hot hidecommon" id="all_hot">
      <div class="circleChange">
         <div class="all_hot_btn all-circle-active">总和热度榜</div>
         <div class="circle_btn">圈层热点榜</div>
         <span class="upPage"></span>
      </div>
      <div class="prf pointer hidecommon" id="comeback_hot_home"><span></span></div>
            <div class="prf comeback_hot pointer" id="comeback_hot">返回首页</div>
   		<div class="all_hot_section" id="all_hot_section">
   			
   			<!-- <ul class="hot_ifo_title f18">
   			    <li class="hot_ifo_title_img fl"></li>
   				<li class="fl">全部热点</li>
   			</ul> -->
   			<ul class="all_hot_bar" id="all_hot_bar">
   				<li>排名</li>
   				<li>热点名称</li>
   				<li>热度</li>
				<li>热点来源</li> 
   				
<!--    				<li>热点受众画像</li> -->
   				<li>操作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>   				
   			</ul>
   			<ul class="all_hot_list">
   			<!-- <li>
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
   						<li>查看画像</li>
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
   				-->
   			</ul>
   		</div>
   		<div class="circle_hot_section" id="circle_hot_section">
   		 <ul class="circleTagCon"></ul>
   		</div>
   </div>
   <div class="alertCon">
    <div class="portrait"></div>
    <div class="info">
        <div class="infoTop">
            <div class="infoTitle"></div>
            <div class="infoConnect">关联此热点</div>
        </div>
        <div class="hotAlertTag"></div>
        <div class="hotInfo">
            <div class="infoText">
                     
            </div>            
        </div>
        <div class="infoBottom">
            <div class="hotLeft fl">  
                <div class="hotIcon fl"></div>
                <div class="font14 fl">热度:</div>
                <div class="hotValue fl"></div>
                <div class="hotTrend fl"></div>
            </div>
            <div class="iconCon font14 fr userProfile">受众画像 </div>
            <div class="planText font14 fl hotDetailInfo">热点详情</div>
        </div>
    </div>
    <div class="triangle"></div>
</div>
<ul class="cook_ul pos hidecommon" id="cook_ul">
  			
</ul>
<div class="loadingcon">
 	<div class="result-loading1">
	        <span></span>
	        <span></span>
	        <span></span>
	        <span></span>
	        <span></span>
	        <span></span>
	        <span></span>
	        <span></span>
	        <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
           
	</div>
	 <p class="title" style="font-size:14px;">受众画像加载中，请稍候……</p>
</div>
<div class="circleCon" style="display:none">         
    <div class="circleInfo">
        <div class="circleDes">
            <p class="circleDesTitleP">圈层描述</p>
            <div class="circle_Des_Info">
                <div class="circle_Des_Icon"></div>
                <div class="circleDesCon">
                    <p class="circleDesTitle">职场新人</p>
                    <p class="circleDesText">职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场职场</p>
                </div>
            </div>
        </div>
        <div class="splitLine">&nbsp;</div>
        <div class="circleTag">
            <p class="circleTagTitle">圈层属性</p>
            <div class="circle_Tag_Info">
                <div class="circle_Tag_Circle">
                    <span>职场新人</span>
                    <div class="circleTagLeft">123456</div>
                    <div class="circleTagRight">标签</div>
                 </div>
             </div>
         </div>
    </div>
    <ul class="all_hot_bar circle_hot_bar">
        <li style="height:40px;"></li>
        <li style="text-indent:-50px;">圈层热点</li>
        <li>热度</li>
        <li>热点来源</li> 
        <li>操作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>                 
    </ul>
    <ul class="circle_hot_list"></ul>
</div>
 <!-- TGI解释  -->
   		<div class="tgiInfoDialog">
   			<p>兴趣TGI</p>
   			<span>(该热点受众中某兴趣的人群占比÷全网中该兴趣的人群占比)×100</span>
   		</div>
   <!-- TGI解释 end  -->
<jsp:include page="foot.jsp" />
<script src="js/raphael.js"></script>
<script src="js/hotsystem.js"></script>
<script src="js/dialog.js"></script>
<script id="allHotTmpl" type="text/x-jsrender"></script>

</body>
</html>