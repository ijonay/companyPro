<%@page language="java" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp" />
<link rel="stylesheet" href="css/hotresult.css">
<link rel="stylesheet" href="css/hotComponents.css">
<link rel="stylesheet" type="text/css" href="css/pop.css">

<title>热点分析</title>
</head>
<body>
    <jsp:include page="nav.jsp" />
    <div class="result_top">
	    <div class="result_t_l fl">
	    	<p>
	    		<span class="corlor4a f14"></span>
	    		<span class="corlor4a f14 pointer" onClick="window.location.href='hotsystem'">首页</span>
	    		<span class="black f16 pst"></span>
	    		<span class="black f16">探索结果</span>
	    	</p>
	    </div>
	    <div class="result_t_r fr f12" id="result_t_r">
	    	<div class="result_evet_con hidecommon" id="result_evet_con">
	    		<div class="cor389b9f">事件标签:</div>
	    		<div class="corlor4a result_evet_div label_border width158 pst ac word-ellipsis" title="" id="result_label_even"><span class="lable_c-d"></span></div>
	    	</div>
	    	
	    	<div class="result_evet_persn hidecommon" id="result_evet_persn">
	    		<div class="cor094" style="margin-right:10px;">受众特征:</div>
	    		
	    		<ul class="corlor4a">
	    			<p class="ac fl" id="result_label_newtag"></p>
	    			<li class="label_border labele_Gender width74 pst ac word-ellipsis" id="result_label_gender"><span class="lable_c-d"></span></li>
	    			<li class="label_border labele_Age width74 pst ac word-ellipsis" id="result_label_age"><span class="lable_c-d"></span></li>
	    			<li class="label_border labele_Education width74 pst ac word-ellipsis" id="result_label_education"><span class="lable_c-d"></span></li>
	    			<li class="label_border labele_Area width74 pst ac word-ellipsis" id="result_label_area"><span class="lable_c-d"></span></li>
	    			<li class="label_border labele_UserClass width74 pst ac word-ellipsis" id="result_label_userClass"><span class="lable_c-d"></span></li>
	    		</ul>
		    </div>
	    	<div class="result_filter pointer" id="result_filter">热点筛选</div>
	    </div>
	</div>    
    <div class="clearfix"></div>
    <div class="result-content">
        <div class="hot-prev disabled"><div style="background:url(img/hot-prev.png);"></div></div>
        <div class="hot-next"><div style="background:url(img/hot-next.png);"></div></div>
        <div class="result-section">
            <div id="canvas">
                <div class='edit-word'></div>
                <div class="alertCon">
                    <div class="portrait"></div>
                    <div class="info">
                        <div class="infoTop">
                            <div class="infoTitle"></div>
                            <div class="infoConnect">关联此热点</div>
                        </div>
                        <div class="hotLabel">
                            
                        </div>
                        <div class="hotInfo">
                            <div class="infoText"></div>            
                        </div>
                        <div class="infoBottom">
                            <div class="hotLeft fl">  
                                <div class="hotIcon fl"></div>
                                <div class="font14 fl">热度:</div>
                                <div class="hotValue fl"></div>
                                <div class="hotTrend fl"></div>
                            </div>
                            <div class="iconCon font14 fr">受众画像 </div>
                            <div class="planText font14 fl">热点详情</div>
                        </div>
                    </div>
                </div><!-- 弹窗end -->
            </div><!-- 画图区域end -->
        </div><!-- 画布背景end -->
    </div>
    <div class="result-error">
        <div class="error-content">
            <div class="content-icon"></div>
            <div class="content-title"></div>
        </div>
    </div>
    <div class="ser_dialog hidecommon" id="ser_dialog">
   		<div class="dialog_area corlor4a f18">
   			<div class="dialog_title">高级探索<span class="ser_dialog_close pointer"></span></div>
   			<p style="height:40px;font-size:16px;color:#4a4a4a;padding-left:20px;line-height:40px;"><b style="color:#389b9f">事件标签</b>(按照热点事件标签筛选)</p>
   			<ul class="dialog_tab eventDialogTab f14 corlor4a">
   				
   			</ul>
   			

   			<div class="dislog_inp_con eventTab dialog_tab_event">
   				
   			</div>
   			
   			<ul class="dialog_tab eventDialogTab2 f14 corlor4a">
   				
   			</ul>
   			
   			<div class="dislog_inp_con eventTab2 dialog_tab_event">
   				
   			</div>
   			
   			<p style="height:40px;font-size:16px;color:#4a4a4a;margin:0 20px;line-height:40px;border-top:1px solid #eee;"><b style="color:#009944">受众特征</b>(按照热点受众特征筛选)</p>
   			
   			<ul class="userDialog_tag" id="userDialog_tag">
	   				<li><label>宅男腐女<input type="checkbox" data-id="1"></label></li>
	   				<li><label>职场新人<input type="checkbox" data-id="2"></label></li>
	   				<li><label>家庭主妇<input type="checkbox" data-id="3"></label></li>
	   				<li><label>时尚达人<input type="checkbox" data-id="4"></label></li>
	   				<li><label>互联网精英<input type="checkbox" data-id="5"></label></li>
	   				<li><label>体育迷<input type="checkbox" data-id="6"></label></li>
	   				<li><label>背包客<input type="checkbox" data-id="7"></label></li>
   			</ul>
   			<ul class="dialog_tab userDialogTab f14 corlor4a">
   				
   			</ul>
   			
   			<div class="dislog_inp_con personTab dialog_tab_person">
   				
   			</div>
   			<div class="clearfix"></div>
   		<div style="height:74px;">	
   			<div class="dialog_inp_c f12 hidecommon fl">
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
   				
   				<div class="fl dialog_inp_del cor389b9f pointer" id="dialog_inp_del">清空已选</div>
   			</div>
   			
   		<div class="dislog_btn_sure ac pointer f14 fr" id="dislog_btn_sure">确定</div>
   	</div>		
   		</div>
   </div>

    <div class="result-bottom">
        <div class="fl">当前：1-20<span class="hot-count">/689</span></div>
        <div class="fr">
            <span class="fl">热度：高</span>
            <div class="circle1 fl"></div>
            <div class="circle2 fl"></div>
            <div class="circle3 fl"></div>
            <span class="fl">低</span>
        </div>
    </div>
<div class="result-loading">

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
	 <p class="title">探索结果加载中，请稍候……</p>
</div>	 
    
	
</div>
	<ul class="all_hot_list"></ul>
	<jsp:include page="foot.jsp" />
	<script src="js/dialog.js"></script>
    <script src="js/hotresult.js"></script>
</body>
</html>