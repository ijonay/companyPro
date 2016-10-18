<%@page language="java" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <jsp:include page="head.jsp" />
        <link rel="stylesheet" href="css/result.css">
        <title>探索结果</title>
        
    </head>

    <body>
        <jsp:include page="nav.jsp" />
        <div class="result-section">
            <div class="result-section-img fl"></div>
            <div class="search-con fr pst">
                <input type="text" class="search-input" placeholder="请输入关键词" maxlength="25" id="search-input">
				<div class="search-btn pointer ac" id="search-btn">
                    <span>探&nbsp;索</span>
                </div>
                <div class="pos search-hint hidecommon" id="search-hint">
    					<h4 class="ac pst">提示<img src="img/close_icon.png" class="pos close_hint"></h4>
    					<p class="f18 ac">暂时没有符合条件的热点</p>
    					<div class="c-word ac f12">请修改<span> 关键词</span>或<span> 探索设置</span>后进行探索</div>
    			</div>
            </div>
            <div class="clearfix"></div>

            <div class="nav-banner">
                <div class="fl">
                    <span style="color:#9c9c9c;cursor:pointer;" onclick="window.location.href='hotspots';">&lt; 热点分析 </span>
                    <span>  &lt;&nbsp;探索结果</span>
                </div>
                <ul class="fr nav-banner-ul">
                    <li class="correlation pointer color9c">关联度</li>
                    <li class="color9c">热点趋势:
                        <span class="pointer hotup">上升</span>
                        <span class="pointer hotdown">下降</span>
                    </li>
                    <li class="filtrate pointer color9c">筛选</li>
                    <li class="changeviewpoints pointer color9c" id="changeviewpoints">换一批</li>
                </ul>
            </div>

            <div id="canvas">
                <div class="cardContainer hidecommon">
                    <div style="position:relative;">
                        <div class="cardTitle">
                            <div class="hotImg"></div>
                            <div class="hotTitle" title="八达岭野生动物园老虎袭人">八达岭野生动物园老虎袭人</div>
                        </div>
                        <div class="cardTag"></div>
                        <div class="hotInfo">
                            <div class="talkTime">
                                <img src="img/progress.png"> 话题量:
                                <span>2105万</span>
                                <img class="tag" src="img/arrowdown-result.png">
                            </div>
                            <div class="relevance" class="opa04">
                                <img src="img/link1.png" class="opa04"> 关联度:
                                <span>83%</span>
                            </div>
                            <div class="relevance">
                                <img src="img/findhot.png"> 查看热点源:
                                <a href="#" class="hot-source" target="_blank">点击</a>
                            </div>
                        </div>
                        <div class="hotOp">

                            <div class="viewSource opa04">
                                <img src="img/relationhot.png"> 关联热点
                            </div>
                            <div class="concern opa04" data-id="" data-name="">
                                <img src="img/star.png"> 关注热点
                            </div>
                            <div class="discard opa04">
                                <img src="img/resultalertdel.png" style="vertical-align: baseline;"> 关闭弹窗
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tip-bottom ac">
                <p>点击感兴趣的热点与您输入的关键信息进行关联</p>
            </div>
        </div>
            <script src="lib/jquery-1.12.0.min.js"></script>
            <SCRIPT>
                if(!$.support.leadingWhitespace) {
                    alert("浏览器版本太低，请下载chrome或者IE10以上版本浏览器")
                }
            </SCRIPT>
            <script src="lib/jsrender.min.js"></script>
            <script src="lib/bootstrap.min.js"></script>
            <script src="lib/underscore-min.js"></script>
            <script src="js/common.js"></script>
            <script src="js/templates.js"></script>
            <script src="js/util.js"></script>
            <script type="text/javascript" src="js/raphael.js"></script>
            <script src="js/result.js"></script>

    </body>

</html>