<%@page language="java" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp" />
<link rel="stylesheet" href="css/hotresult.css">
<link rel="stylesheet" type="text/css" href="css/pop.css">

<title>热点分析</title>
</head>
<body>
    <jsp:include page="nav.jsp" />
    <div class="result_top">
	    <div class="result_t_l fl">
	    	<p>
	    		<span class="corlor4a f14"></span>
	    		<span class="corlor4a f14"><a href="hotsystem">首页</a></span>
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
        <div class="hot-prev"></div>
        <div class="hot-next"></div>
        <div class="result-section">
            <div id="canvas">
                <div class='edit-word'></div>
                <div class="alertCon">
                    <div class="portrait"></div>
                    <div class="info">
                        <div class="infoTop">
                            <div class="infoTitle">张雨绮结婚</div>
                            <div class="infoConnect">关联此热点</div>
                        </div>
                        <div class="hotLabel">
                            <div class="hotLabel0"></div>
                            <div class="hotLabel1"></div>
                            <div class="hotLabel2"></div>
                        </div>
                        <div class="hotInfo">
                            <div class="infoText">张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚张雨绮结婚</div>            
                        </div>
                        <div class="infoBottom">
                            <div class="hotIcon fl"></div>
                            <div class="hotValue fl"></div><div class="planText fl">/热度</div>
                            <div class="iconCon fr">
                                <a target=" _blank" class="weibo-link" href="#" style="background-image:url(img/hot_weibo.png);"></a>
                                <a target=" _blank" class="weixin-link" href="#" style="background-image:url(img/hot_weixin.png);display:none;"></a>
                                <a target=" _blank" class="zhihu-link" href="#" style="background-image:url(img/hot_zhihu.png);display:none;"></a>
                                <a target=" _blank" class="baidu-link" href="#" style="background-image:url(img/hot_baidu.png);display:none;"></a>
                            </div>
                            <div class="planText font14 fr">来源:</div>
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
    
<script src="lib/jquery-1.12.0.min.js"></script>
<script src="lib/jsrender.min.js"></script>
<script src="lib/bootstrap.min.js"></script>
<script src="lib/underscore-min.js"></script>
<script src="js/common.js"></script>
<script src="lib/jquery.cookie.js"></script>
<script src="js/raphael.js"></script>
<script src="js/templates.js"></script>
<script src="js/util.js"></script>
<script src="js/hotresult.js"></script>
<script src="js/calendar.js"></script>
<script src="js/nav.js"></script>
<script src="js/pop.js"></script>
<script src="lib/jquery.mousewheel.js"></script>
<script src="lib/perfect-scrollbar.js"></script>
</body>
</html>