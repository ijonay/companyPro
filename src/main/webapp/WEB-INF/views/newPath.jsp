<%@page language="java" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp" />
<link rel="stylesheet" href="css/newPath.css">
<link rel="stylesheet" href="css/pop.css">
<style>
div#canvas {
  width: 100%;
  height: 100%;
  margin: 0 auto;
}
</style>
<title>路径方案</title>
</head>
<body>
    <jsp:include page="nav.jsp" />
    <div class="nav-banner">

        <div class="fl nav-banner_left">
            <span style="color:#9c9c9c;cursor:pointer;"  onClick="window.location.href='hotsystem'">首页 </span>
            <span>&lt;</span>
            <span  class="color9c"> 探索结果</span>
            <span class="nav-banner_left_icon"></span>
            <span style="font-size:16px;">路径方案</span>
        </div>
        <ul class="fr nav-banner-ul">
        <li class="pointer color9c">
                	<span id="prev-path" class="disstate">上一页</span>
                	<span id="prev-next" class="disstate">下一页</span>
                </li>
       <!-- <li class="pointer color9c save-path-num pst"><span>已保存路径</span>
            	<div class="pos save-p-n ac f12">5</div> 
         </li> -->
        </ul>
    </div>
    <div class="result-section">
	        <div id="canvas" style="width:100%;height:100%;margin:0 auto;">
			    <div class="load-con ac" id="load-con">
		        	<div class="load-container load3 fl">
						<div class="loader"></div>
					</div>
					<p class="fl load-word">正在计算路径...</p>
				</div>	
			</div>
        
        <div class="path-dialog">
            <div class="dialog-head">
                <div>节点</div>
                <div class="link-icon"></div>
                <div>节点节点</div>
                <div class="path-close"></div>
            </div>
            <div class="dialog-body">
                <div class="pnl-appear">同时出现：
                    <span class="color1"><span class="count">231</span>次</span>
                </div>
                <div class="pnl-info pnl-weibo">
                    <div class="head">
                        <div class="head-left fl">微博<span class="count color2">120</span>条</div>
                        <ul class="head-right fr">
                            <li class="all"><a href="javascript:;">查看全部</a></li>
                            <li class="view"><a href="javascript:;">收起</a></li>
                        </ul>
                    </div>
                    <div class="content">
                        <div class="content-head">
                            <div class="head-left fl"></div>
                            <div class="head-middle fl">
                                <span><span class="color2">三联生活周刊</span></span><br>
                                <span class="desc">8-13 11:13 来自微博 weibo.com</span>
                            </div>
                            <div class="head-right fr">36万<br>阅读</div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="content-body">文字某某文字某某<span class="color2">节点</span>文字某某文字某某文字某某文字某某<span class="color2">节点节点</span>文字某某文字某某</div>
                    </div>
                </div>
                <div class="pnl-info pnl-weixin">
                    <div class="head">
                        <div class="head-left fl">微信<span class="count color3">111</span>条</div>
                        <ul class="head-right fr">
                            <li class="all"><a href="javascript:;">查看全部</a></li>
                            <li class="view" style="background-image: url(img/path-down.png);"><a href="javascript:;">预览</a></li>
                        </ul>
                    </div>
                    <div class="content" style="display:none;">
                        <div class="content-head">
                            <div class="head-left fl"></div>
                            <div class="head-middle fl">
                                <span><span class="color3">三联生活周刊</span></span><br>
                                <span class="desc">8-13 11:13 来自微博 weibo.com</span>
                            </div>
                            <div class="head-right fr">36万<br>阅读</div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="content-body">文字某某文字某某<span class="color3">节点</span>文字某某文字某某文字某某文字某某<span class="color3">节点节点</span>文字某某文字某某</div>
                    </div>
                </div>
            </div>
        </div>
        
   <!-- <div class="arrowCon">
	        <div class="arrowUp pointer"></div>
	        <div class="arrowDown pointer"></div>
	    </div> --> 
        
    </div>
    <ul class="bottom-choice f14">
       	<li class="fl" style="color:#000">当前路径&nbsp;:&nbsp;</li>
       	<li class="fl mr5 choice-keyword"><span></span>关键词</li>
       	<li class="fl libordercenter mr5"></li>
       	<li class="fl mr5"><span></span>节点</li>
       	<li class="fl libordercenter mr5"></li>
       	<li class="fl mr5"><span></span>节点</li>
       	<li class="fl libordercenter mr5"></li>
       	<li class="fl mr5"><span></span>节点</li>
       	<li class="fl libordercenter mr5"></li>
       	<li class="fl mr5 choice-hot"><span></span>热点</li>
       	<li class="fr computer-ok pointer">保存路径</li>
    </ul>
    <div class="pathName disno">
        选择路径<span class="pathNum">1</span>
    </div>
    
<script src="lib/jquery-1.12.0.min.js"></script>
<SCRIPT>
    if (!$.support.leadingWhitespace) {
         alert("浏览器版本太低，请下载chrome或者IE10以上版本浏览器")
    }
</SCRIPT>
<script src="lib/jsrender.min.js"></script>
<script src="lib/bootstrap.min.js"></script>
<script src="lib/underscore-min.js"></script>
<script src="js/common.js"></script>
<script src="js/pop.js"></script>
<script src="js/templates.js"></script>
<script src="js/util.js"></script>
<script src="js/raphael.js"></script>
<script src="js/newPath.js"></script>
<script src="js/calendar.js"></script>
<script src="js/nav.js"></script>
<script src="lib/jquery.mousewheel.js"></script>
<script src="lib/perfect-scrollbar.js"></script>
</body>
</html>