/*顶部导航*/
$(".header-left li:last a").css("color","#fff").attr("href","javascript:;");

/*返回顶部*/
$(window).scroll(function(){

    if($(window).scrollTop()>540){
        $('.list-header').addClass('list_header_scroll');
    }else{
        $('.list-header').removeClass('list_header_scroll');
    };

    if($(window).scrollTop()>610){
        $('.sidebar').removeClass('hidecommon');
    }else{
        $('.sidebar').addClass('hidecommon');
    }
});
$('#comeback_body_top').on('click',function(){
    $('body').animate({scrollTop:"0px"},500)
});
$("#filterarea").on('click',function(){
    $(".filter-list>li.area").trigger("click");
});
$("#filtertype").on('click',function(){
    $(".filter-list>li.type").trigger("click");
});
$("#filtertime").on('click',function(){
    $(".filter-list>li.time").trigger("click");
});
/*搜索框*/
$('#btn-search').click(function(){
	$('.section-filter').hide();
	$('.section-filter-ser').show();
	
})
//回到热门文章
$('#ser-back-home').click(function(){
	$('.section-filter-ser').hide();
	$('.section-filter').show();
})

/*热门文章区域*/
var metrialList = $.templates(templates.design["tmplMetrialList"]);
var returnData = {
		data:[{},{},{}]
}
$(".listCon").append(metrialList.render(returnData))

/*筛选热门文章*/
/*区域弹窗*/
$(".filter-list>li.area").click(function(){
    var result=$(this).data("selected");
    var _this=$(this);
    var labArr=["时事","民生","美体","百科","健康","财富","科技","创业","时尚","美食","乐活","教育",
                "楼市","职场","体娱","幽默","情感","企业","学术","政务","文摘","汽车","旅行","文化","其他"]
    var $content=$("<ul class='areaList'></ul>");
    $.each(labArr,function(idx,item){
        var id=idx+1;
        $item=$("<li data-id='"+id+"'>"+item+"</li>");
        if(result&&result.length>0&&_.indexOf(result, id)!=-1){
            $item.addClass("active");
        }
        $content.append($item);
    })
    var $head=$("<div style='background:url(img/headArea.png) no-repeat left center;'>所属领域筛选<div>");
    var pop = new PopFodder({
        width:"706px",
        header:$head,
        content:$content,
        buttons:[{
            type:"fodderCancle",
            text:"取消"
        },{
            type:"fodderOk",
            text:"确定",
            callback:function(){
                var $activeItem=$(".areaList>li.active");
                var selected=[];
                if($activeItem.length>0){
                    _this.addClass("active").find(".selCount").text($activeItem.length).css("display","inline-block");
                    if($(".clearFilter").css("display","none")){
                        $(".clearFilter").css("display","block");
                    }
                }else{
                    _this.removeClass("active").find(".selCount").text(0).css("display","none");
                }
                $activeItem.each(function(){
                    selected.push(parseInt($(this).data("id")))
                });
                _this.data("selected",selected);
                $(".fodderMask").remove();
            }
        }]
    })
});     
/*文章类型弹窗*/
$(".filter-list>li.type").click(function(){
    var result=$(this).data("selected");
    var _this=$(this);
    var labArr=["互动类","视频类","音频类","图文类"];
    var $content=$("<ul class='typeList'></ul>");
    $.each(labArr,function(idx,item){
        var id=idx+1;
        if(item==="互动类"){
            className="hd";
        }else if(item==="视频类"){
            className="sp";
        }else if(item==="音频类"){
            className="yp";
        }else{
            className="tw";
        }
        $item=$("<li class='"+className+"' data-id='"+id+"'><div class='typeIcon'><div class='typeSel'></div></div><div class='typeTitle'>"+item+"</div></li>");
        if(result&&result.length>0&&_.indexOf(result, id)!=-1){
            $item.addClass("active");
        }
        $content.append($item);
    })
    var $head=$("<div style='background:url(img/wenzhang.png) no-repeat left center;'>文章类型筛选<div>");
    var pop = new PopFodder({
        width:"706px",
        height:"234px",
        header:$head,
        content:$content,
        buttons:[{
            type:"fodderCancle",
            text:"取消"
        },{
            type:"fodderOk",
            text:"确定",
            callback:function(){
                var $activeItem=$(".typeList>li.active");
                var selected=[];
                if($activeItem.length>0){
                    _this.addClass("active").find(".selCount").text($activeItem.length).css("display","inline-block");
                    if($(".clearFilter").css("display","none")){
                        $(".clearFilter").css("display","block");
                    }
                }else{
                    _this.removeClass("active").find(".selCount").text(0).css("display","none");
                }
                $activeItem.each(function(){
                    selected.push(parseInt($(this).data("id")))
                });
                _this.data("selected",selected);
                $(".fodderMask").remove();
            }
        }]
    })
});
/*推送时间弹窗*/
$(".filter-list>li.time").click(function(){
    var result=$(this).data("selected");
    var _this=$(this);
    var labArr=["今日","近7天","近30天"]
    var $content=$("<ul class='timeList'></ul>");
    $.each(labArr,function(idx,item){
        var id=idx+1;
        if(item==="今日"){
            className="jr";
        }else if(item==="近7天"){
            className="j7t";
        }else{
            className="j30t";
        }
        $item=$("<li class='"+className+"' data-id='"+id+"'><div class='typeIcon'><div class='typeSel'></div></div><div class='typeTitle'>"+item+"</div></li>");
        if(result&&result.length>0&&_.indexOf(result, id)!=-1){
            $item.addClass("active");
        }
        $content.append($item);
    })
    var $head=$("<div style='background:url(img/shijian.png) no-repeat left center;'>推送时间筛选<div>");
    var pop = new PopFodder({
        width:"706px",
        height:"234px",
        header:$head,
        content:$content,
        buttons:[{
            type:"fodderCancle",
            text:"取消"
        },{
            type:"fodderOk",
            text:"确定",
            callback:function(){
                var $activeItem=$(".timeList>li.active");
                var selected=[];
                if($activeItem.length>0){
                    _this.addClass("active").find(".selCount").text($activeItem.length).css("display","inline-block");
                    if($(".clearFilter").css("display","none")){
                        $(".clearFilter").css("display","block");
                    }
                }else{
                    _this.removeClass("active").find(".selCount").text(0).css("display","none");
                }
                $activeItem.each(function(){
                    selected.push(parseInt($(this).data("id")))
                });
                _this.data("selected",selected);
                $(".fodderMask").remove();
            }
        }]
    })
});
/*搜索按钮*/
$(".filter-list>li.ser .btn-search").click(function(){
    var txt=$.trim($(this).siblings("input").val());
    if(txt!=""){
        $(".filter-list>li").removeClass("active").data("selected","").find(".selCount").text("0").css("display","none");
    }
});
/*搜索框*/
$(".filter-list>li.ser input").click(function(e){
    e ? e.stopPropagation() : event.cancelBubble = true;
    $(this).parents("li").css("width","220px");
    $(this).parents("ul").css("width","598px");
}).change(function(e){
    e ? e.stopPropagation() : event.cancelBubble = true;
    if($(".clearFilter").css("display","none")){
        $(".clearFilter").css("display","block");
    }
});
$(document).click(function(){
    $("ul.filter-list>li.ser").css("width","110px");
    $("ul.filter-list").css("width","488px");
})
/*清空筛选条件*/
$(".clearFilter").click(function(){
    $(".filter-list>li").removeClass("active").data("selected","").find(".selCount").text("0").css("display","none");
    $(".filter-list>li.ser input").val("");
    $(this).css("display","none");
});
$(document).delegate(".areaList>li,.typeList>li,.timeList>li","click",function(e){
    if($(this).hasClass("active")){
        $(this).removeClass("active");
        $selAll=$(this).parents(".fodderWin").find(".selAll");
        if($selAll.hasClass("active")){
            $selAll.removeClass("active");
        }
    }else{
        $(this).addClass("active");
        var allLen=$(this).parents("ul").find("li").length;
        var activeLen=$(this).parents("ul").find("li.active").length;
        if(allLen==activeLen){
            $selAll=$(this).parents(".fodderWin").find(".selAll");
            if(!$selAll.hasClass("active")){
                $selAll.addClass("active");
            }
        } 
    }
}).delegate(".selAll","click",function(e){
    if($(this).hasClass("active")){
        $(this).removeClass("active");
        $(this).parents(".fodderWin").find("li.active").removeClass("active");
    }else{
        $(this).addClass("active");
        $(this).parents(".fodderWin").find("li").addClass("active");
    }
})
/*热门文章列表*/
    

/*搜索结果区域*/


/*热点图区域*/
var Hotdata = "[{\"id\":5298,\"title\":\"我是证人\",\"score\":0.27028504,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"10.7亿\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/1008083721fede92f23dc987bd2bdc7b0c8c7b\",\"topicType\":\"\",\"introduction\":\"由“迷路组合”杨幂、鹿晗主演的电影《我是证人》定档2015年10月30日。\",\"logoImgUrl\":null,\"logoImgUrlLocal\":null,\"prevailingTrend\":\"54\",\"wechatUrl\":\"http://weixin.sogou.com/weixin?oq=&query=%E6%88%91%E6%98%AF%E8%AF%81%E4%BA%BA&_sug_type_=&sourceid=inttime_week&_sug_=n&type=2&ie=utf8&interation=&interV=kKIOkrELjboJmLkElbYTkKIKmbELjbkRmLkElbk%3D_1893302304&tsn=2\",\"wechatTitle\":\"我是证人\",\"wechatAvgReadNum\":1032,\"baiduTitle\":\"我是证人\",\"baiduUrl\":\"http://www.baidu.com/baidu?wd=%E6%88%91%E6%98%AF%E8%AF%81%E4%BA%BA\",\"baiduHitNum\":6630000,\"zhihuTitle\":\"如何评价《我是证人》？\",\"zhihuUrl\":\"https://www.zhihu.com/question/36952208\",\"zhihuAvgAnswerNumber\":57,\"eventClass\":\"影视宣传\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":11717,\"title\":\"叶青疑遭姚笛抢角 \",\"score\":0.26958677,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"1.5亿\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/1008082c0f71d2fc2433b1ad10d8f864764b25\",\"topicType\":\" 明星 内地 \",\"introduction\":\"叶青接受采访否认那条微博是针对姚笛，并表示对经纪人的激烈回应表示抱歉。日前叶青发微博疑怒斥有人抢角色：“人这个东西永远都是厚脸皮的，什么东西都要争什么东西都要抢。”随后经纪人发博点名姚笛。\",\"logoImgUrl\":null,\"logoImgUrlLocal\":null,\"prevailingTrend\":null,\"wechatUrl\":null,\"wechatTitle\":null,\"wechatAvgReadNum\":null,\"baiduTitle\":null,\"baiduUrl\":null,\"baiduHitNum\":null,\"zhihuTitle\":null,\"zhihuUrl\":null,\"zhihuAvgAnswerNumber\":null,\"eventClass\":\"娱乐八卦\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":15637,\"title\":\"不一样的葛大爷\",\"score\":0.26861528,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"426.6万\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/10080882e28719f5bd80a61090d102320f9e3b\",\"topicType\":null,\"introduction\":null,\"logoImgUrl\":null,\"logoImgUrlLocal\":null,\"prevailingTrend\":null,\"wechatUrl\":null,\"wechatTitle\":null,\"wechatAvgReadNum\":null,\"baiduTitle\":null,\"baiduUrl\":null,\"baiduHitNum\":null,\"zhihuTitle\":null,\"zhihuUrl\":null,\"zhihuAvgAnswerNumber\":null,\"eventClass\":\"影视宣传\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":8194,\"title\":\"田亮儿子\",\"score\":0.26642987,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"1407.7万\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/100808559158de8d21e538b12dac6ef1e15f5e\",\"topicType\":\" 明星 内地 \",\"introduction\":\"田亮小儿子田宸宇，小名小亮仔，鲜少出现在大众视野中，但田亮夫妇不经意的微博照片，就让不少网友惊艳于小朋友的超高颜值。小亮仔不仅长相讨喜，暖心的小男子汉气质也初见雏形。期待节目表现~\",\"logoImgUrl\":null,\"logoImgUrlLocal\":null,\"prevailingTrend\":null,\"wechatUrl\":null,\"wechatTitle\":null,\"wechatAvgReadNum\":null,\"baiduTitle\":null,\"baiduUrl\":null,\"baiduHitNum\":null,\"zhihuTitle\":null,\"zhihuUrl\":null,\"zhihuAvgAnswerNumber\":null,\"eventClass\":\"明星宣传\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":1985,\"title\":\"好笑头条君\",\"score\":0.26591727,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"1.3亿\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/10080857f808c7130391f5ee2db5f8952b66de\",\"topicType\":\" 综艺 网络节目 \",\"introduction\":\"优酷自制网综《好笑头条君》第二季来袭。陈赫综艺主持首秀，张全蛋、孔连顺、SNH48陈思倾情加盟。每周二来优酷，我们正经搞事情！\",\"logoImgUrl\":\"http://ww4.sinaimg.cn/thumb180/00612A1Ujw1f1hdffufz8j3050050glw.jpg\",\"logoImgUrlLocal\":null,\"prevailingTrend\":\"16\",\"wechatUrl\":null,\"wechatTitle\":null,\"wechatAvgReadNum\":null,\"baiduTitle\":\"好笑头条君\",\"baiduUrl\":\"http://www.baidu.com/baidu?wd=%E5%A5%BD%E7%AC%91%E5%A4%B4%E6%9D%A1%E5%90%9B\",\"baiduHitNum\":437000,\"zhihuTitle\":\"snh48成员有哪些有实力？\",\"zhihuUrl\":\"https://www.zhihu.com/question/51182749\",\"zhihuAvgAnswerNumber\":340,\"eventClass\":\"综艺节目\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":15625,\"title\":\"绝世高手定档大年...\",\"score\":0.26522037,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"2333.2万\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/1008086c529eb93cd4270f7c4c21b7ec86819a\",\"topicType\":null,\"introduction\":null,\"logoImgUrl\":null,\"logoImgUrlLocal\":null,\"prevailingTrend\":null,\"wechatUrl\":null,\"wechatTitle\":null,\"wechatAvgReadNum\":null,\"baiduTitle\":null,\"baiduUrl\":null,\"baiduHitNum\":null,\"zhihuTitle\":null,\"zhihuUrl\":null,\"zhihuAvgAnswerNumber\":null,\"eventClass\":\"影视宣传\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":5647,\"title\":\"杨幂我是证人\",\"score\":0.26457655,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"3.8亿\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/1008081eb4829f46a1aac2a2ea64e70e42b11f\",\"topicType\":\" 明星 内地 \",\"introduction\":\"电影《我是证人》10月30日上映，讲述了盲女小星在逆境中克服困难自我成长的故事。杨幂饰演盲女路小星，可以过肩摔也可以驾驭细腻的盲人演技。三个多月的专心拍摄，专业的电影团队，诚意十足的电影，盲女路小星不负期待。\",\"logoImgUrl\":\"http://ww3.sinaimg.cn/thumb180/d92e4c8bgw1euv459pyv8j20500500sz.jpg\",\"logoImgUrlLocal\":null,\"prevailingTrend\":\"17\",\"wechatUrl\":\"http://weixin.sogou.com/weixin?oq=&query=%E6%9D%A8%E5%B9%82%E6%88%91%E6%98%AF%E8%AF%81%E4%BA%BA&_sug_type_=&sourceid=inttime_week&_sug_=n&type=2&ie=utf8&interation=&interV=kKIOkrELjboJmLkElbYTkKIKmbELjbkRmLkElbk%3D_1893302304&tsn=2\",\"wechatTitle\":\"杨幂我是证人\",\"wechatAvgReadNum\":2184,\"baiduTitle\":\"杨幂我是证人\",\"baiduUrl\":\"http://www.baidu.com/baidu?wd=%E6%9D%A8%E5%B9%82%E6%88%91%E6%98%AF%E8%AF%81%E4%BA%BA\",\"baiduHitNum\":907000,\"zhihuTitle\":\"如何看待杨幂在我是证人中的演技？\",\"zhihuUrl\":\"https://www.zhihu.com/question/36906695\",\"zhihuAvgAnswerNumber\":4,\"eventClass\":\"明星宣传\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":3534,\"title\":\"范冰冰李晨同框直播\",\"score\":0.26204684,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"466.1万\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/100808bf4942608e08421a1dd276d976a09113\",\"topicType\":\" 明星 内地 \",\"introduction\":\"范冰冰凭借《我不是潘金莲》再获国际影后，李晨、范冰冰庆功宴上同框直播，继“我们”再一次强势秀恩爱，引发无数网友真诚祝福。\",\"logoImgUrl\":null,\"logoImgUrlLocal\":null,\"prevailingTrend\":\"3\",\"wechatUrl\":\"http://mp.weixin.qq.com/s?src=3&timestamp=1478192385&ver=1&signature=n6r4Wiu2V1hYQ7pK6l3qU5hyjJZZABCHHbVNJnHAnEYuNfo77BdTckK9BRVptfBjW1wI-l9ZfRNAL4lgAmRPoibwX*1Pjyd9ZT7Kt9HTCt7hEZ*HsyeapfGVhCn6HdrAjNY6WmO*CN6sFeP4WaUAYTwsfhjJH8UC75qaVmkDwII=\",\"wechatTitle\":\"卓伟再爆猛料：张馨予和李晨分手是因为……\",\"wechatAvgReadNum\":7999,\"baiduTitle\":\"范冰冰李晨同框直播\",\"baiduUrl\":\"http://www.baidu.com/baidu?wd=%E8%8C%83%E5%86%B0%E5%86%B0%E6%9D%8E%E6%99%A8%E5%90%8C%E6%A1%86%E7%9B%B4%E6%92%AD\",\"baiduHitNum\":977000,\"zhihuTitle\":\"如何看待范冰冰李晨公开恋情而张馨予更博祝福事件？\",\"zhihuUrl\":\"https://www.zhihu.com/question/30783613\",\"zhihuAvgAnswerNumber\":21,\"eventClass\":\"娱乐八卦\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":10607,\"title\":\"关晓彤室友呛网友\",\"score\":0.26177025,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/100808b125fe7778f2ae4efd47ede915af8439\",\"topicType\":\" 教育 国内高校 \",\"introduction\":\"【关晓彤室友呛网友：我不是你们看她的途径】关晓彤入学后，室友受到关注，甚至遭网络攻击。盛蕙子发文：“喜欢就喜欢，不喜欢就算了，为什么要攻击呢？…我只是不到18的女孩，想安静度过该有的花季。我不是你们为了看晓彤的途径。”看她微博下的这些评论，心情有点复杂↓↓\",\"logoImgUrl\":null,\"logoImgUrlLocal\":null,\"prevailingTrend\":null,\"wechatUrl\":null,\"wechatTitle\":null,\"wechatAvgReadNum\":null,\"baiduTitle\":null,\"baiduUrl\":null,\"baiduHitNum\":null,\"zhihuTitle\":null,\"zhihuUrl\":null,\"zhihuAvgAnswerNumber\":null,\"eventClass\":\"生活记录,娱乐八卦,明星宣传\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":10988,\"title\":\"佟丽娅我们爱你\",\"score\":0.260516,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"7909.3万\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/10080832c72ac15223719dd4ab4f477876d54c\",\"topicType\":\"\",\"introduction\":\"我们爱的她，是世界上最温暖柔软的人。\",\"logoImgUrl\":null,\"logoImgUrlLocal\":null,\"prevailingTrend\":null,\"wechatUrl\":null,\"wechatTitle\":null,\"wechatAvgReadNum\":null,\"baiduTitle\":null,\"baiduUrl\":null,\"baiduHitNum\":null,\"zhihuTitle\":null,\"zhihuUrl\":null,\"zhihuAvgAnswerNumber\":null,\"eventClass\":\"明星宣传\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":9616,\"title\":\"来吧说做就做\",\"score\":0.25999618,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"882.1万\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/1008080254b5e693857eb86070ac7609e108c8\",\"topicType\":\" 综艺 内地节目 \",\"introduction\":\"由蓝色火焰制作的《来吧说做就做》确定登录湖南卫视四季度周日档！节目将由六位明星嘉宾加一位神秘素人组成星素团\",\"logoImgUrl\":null,\"logoImgUrlLocal\":null,\"prevailingTrend\":null,\"wechatUrl\":null,\"wechatTitle\":null,\"wechatAvgReadNum\":null,\"baiduTitle\":null,\"baiduUrl\":null,\"baiduHitNum\":null,\"zhihuTitle\":null,\"zhihuUrl\":null,\"zhihuAvgAnswerNumber\":null,\"eventClass\":\"综艺节目\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":1996,\"title\":\"模特杜鹃\",\"score\":0.2586594,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"1671.6万\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/10080816ae41d86d4ff817c3ccd6bcf94b1eb2\",\"topicType\":\" 美图 美女帅哥 \",\"introduction\":\"特别关注：@网红发型主编---王俊贤Sam\",\"logoImgUrl\":null,\"logoImgUrlLocal\":null,\"prevailingTrend\":\"4\",\"wechatUrl\":\"http://mp.weixin.qq.com/s?src=3&timestamp=1478409785&ver=1&signature=FduayE8DxidHNXGXSvFHzx2GZEfZmXZhoa9LSCQakvaHgKpUbAykS0WSs20sZLLZ1J4MC2RhIvijveDkQZt6lz1cSfPMiqnVbWqmaYMPgSfOIKSBk3hJfpGsq2cB0mJD6sETJ1AVx4hCnn7weuPnPRrjkHDp2FwhbJuZpJYHmYQ=\",\"wechatTitle\":\"杜鹃：23岁成为世界模特，芭蕾女孩的蜕变之路\",\"wechatAvgReadNum\":14,\"baiduTitle\":\"模特杜鹃\",\"baiduUrl\":\"http://www.baidu.com/baidu?wd=%E6%A8%A1%E7%89%B9%E6%9D%9C%E9%B9%83\",\"baiduHitNum\":1230000,\"zhihuTitle\":\"模特杜鹃的美是一种什么样的美？\",\"zhihuUrl\":\"https://www.zhihu.com/question/21108129\",\"zhihuAvgAnswerNumber\":40,\"eventClass\":\"时尚潮流\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":7023,\"title\":\"马蓉回北京\",\"score\":0.25817302,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"832.3万\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/10080860b7435b336f66ab23a060620247c6db\",\"topicType\":\" 社会 \",\"introduction\":\"马蓉回北京，马蓉回北京\\r\\n              \",\"logoImgUrl\":\"/upload/topic/images/e1e3daecad6045a49d264eaa6cee6152.jpg\",\"logoImgUrlLocal\":null,\"prevailingTrend\":null,\"wechatUrl\":\"http://weibo.com/p/10080860b7435b336f66ab23a060620247c6db\",\"wechatTitle\":null,\"wechatAvgReadNum\":null,\"baiduTitle\":null,\"baiduUrl\":\"http://weibo.com/p/10080860b7435b336f66ab23a060620247c6db\",\"baiduHitNum\":null,\"zhihuTitle\":null,\"zhihuUrl\":\"http://weibo.com/p/10080860b7435b336f66ab23a060620247c6db\",\"zhihuAvgAnswerNumber\":null,\"eventClass\":\"家庭婚姻,娱乐八卦,影视宣传\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":8129,\"title\":\"黄晓明Angelababy\",\"score\":0.25772175,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"1.3亿\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/100808cda91fe5d40c849684f5cdbce71b9a6b\",\"topicType\":\" 明星 内地 \",\"introduction\":\"@黄晓明 @angelababy 恩爱全记录！\",\"logoImgUrl\":null,\"logoImgUrlLocal\":null,\"prevailingTrend\":null,\"wechatUrl\":null,\"wechatTitle\":null,\"wechatAvgReadNum\":null,\"baiduTitle\":null,\"baiduUrl\":null,\"baiduHitNum\":null,\"zhihuTitle\":null,\"zhihuUrl\":null,\"zhihuAvgAnswerNumber\":null,\"eventClass\":\"明星宣传\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":3925,\"title\":\"电影王牌逗王牌\",\"score\":0.25595197,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"1550.7万\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/1008081fb3956523d8d2c5430b82e1ad705ecd\",\"topicType\":\" 电影 \",\"introduction\":\"刘德华、黄晓明领衔主演；王祖蓝、胡然、欧阳娜娜、谢依霖、吴樾、冯宝宝、徐冬冬、毛俊杰主演；特别出演：沈腾、赵英俊、戚薇。导演：王晶。2016年，笑霸国庆档。\",\"logoImgUrl\":\"http://ww2.sinaimg.cn/thumb180/d5185e22jw1f1wkcktwdlj20dh0idabe.jpg\",\"logoImgUrlLocal\":null,\"prevailingTrend\":\"24\",\"wechatUrl\":\"http://mp.weixin.qq.com/s?src=3&timestamp=1478196087&ver=1&signature=gvhf6fsDo0Wkk8rDzPfBZkCsbf4YiouAYDswSMxIWxPOyWPAMYxUs7xseKBmLunKHkSVwRhbD6vPlpSKqZG4n6JKHPVCwPonXwrGYri9bYqFIarmfUGQZK*yMkfIna7O8rZrZbkimFAfnN3D20NZDOHfWs3*OY3imHDRl6hznuI=\",\"wechatTitle\":\"电影《王牌逗王牌 》\",\"wechatAvgReadNum\":37,\"baiduTitle\":\"电影王牌逗王牌\",\"baiduUrl\":\"http://www.baidu.com/baidu?wd=%E7%94%B5%E5%BD%B1%E7%8E%8B%E7%89%8C%E9%80%97%E7%8E%8B%E7%89%8C\",\"baiduHitNum\":11700000,\"zhihuTitle\":\"如何评价电影《王牌逗王牌》？\",\"zhihuUrl\":\"https://www.zhihu.com/question/49896880\",\"zhihuAvgAnswerNumber\":30,\"eventClass\":\"影视宣传\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":39,\"title\":\"赵本山直播\",\"score\":0.25442326,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"5075万\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/100808a3efb28a523a13c432e4f1136ca83f97\",\"topicType\":\" 明星 内地 \",\"introduction\":\"10月1日21:30，@赵本山 在@一直播 平台进行公益直播！直播收入将全部捐给慈善机构，助力贫困地区建学校。\",\"logoImgUrl\":\"http://ww3.sinaimg.cn/thumb180/006lSIh2jw1f82o1ig8raj3050050jrz.jpg\",\"logoImgUrlLocal\":null,\"prevailingTrend\":\"5\",\"wechatUrl\":null,\"wechatTitle\":null,\"wechatAvgReadNum\":null,\"baiduTitle\":\"赵本山直播\",\"baiduUrl\":\"http://www.baidu.com/baidu?wd=%E8%B5%B5%E6%9C%AC%E5%B1%B1%E7%9B%B4%E6%92%AD\",\"baiduHitNum\":1480000,\"zhihuTitle\":\"如何看待十月一号赵本山直播首秀？\",\"zhihuUrl\":\"https://www.zhihu.com/question/51163440\",\"zhihuAvgAnswerNumber\":37,\"eventClass\":\"明星宣传\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":3177,\"title\":\"小沈阳跨界喜剧王\",\"score\":0.24986985,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"1878.2万\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/10080840cf00dfd815b6dfdaf0f857671d4242\",\"topicType\":\" 综艺 内地节目 \",\"introduction\":\"9月3日起每周六20:30锁定北京卫视《跨界喜剧王》，看喜剧召集人小沈阳，如何将跨界进行到底！\",\"logoImgUrl\":\"http://ww4.sinaimg.cn/thumb180/6d7675b7jw1f769vv7xxaj2050050q38.jpg\",\"logoImgUrlLocal\":null,\"prevailingTrend\":\"3\",\"wechatUrl\":null,\"wechatTitle\":null,\"wechatAvgReadNum\":null,\"baiduTitle\":\"小沈阳跨界喜剧王\",\"baiduUrl\":\"http://www.baidu.com/baidu?wd=%E5%B0%8F%E6%B2%88%E9%98%B3%E8%B7%A8%E7%95%8C%E5%96%9C%E5%89%A7%E7%8E%8B\",\"baiduHitNum\":794000,\"zhihuTitle\":\"如何评价《跨界喜剧王》中小沈阳的主持？\",\"zhihuUrl\":\"https://www.zhihu.com/question/51885623\",\"zhihuAvgAnswerNumber\":94,\"eventClass\":\"综艺节目\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":14931,\"title\":\"何检看法\",\"score\":0.2487685,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"1879.3万\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/100808085f66692ebea2f7e92147d7090044fd\",\"topicType\":null,\"introduction\":null,\"logoImgUrl\":null,\"logoImgUrlLocal\":null,\"prevailingTrend\":null,\"wechatUrl\":null,\"wechatTitle\":null,\"wechatAvgReadNum\":null,\"baiduTitle\":null,\"baiduUrl\":null,\"baiduHitNum\":null,\"zhihuTitle\":null,\"zhihuUrl\":null,\"zhihuAvgAnswerNumber\":null,\"eventClass\":\"刑事犯罪,法制问题,执法问题\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":13394,\"title\":\"七分七秒 \",\"score\":0.24812578,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"2377.1万\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/100808ff4206a06d3bbbba7db61acd2451a4f2\",\"topicType\":\"\",\"introduction\":\"\",\"logoImgUrl\":null,\"logoImgUrlLocal\":null,\"prevailingTrend\":null,\"wechatUrl\":null,\"wechatTitle\":null,\"wechatAvgReadNum\":null,\"baiduTitle\":null,\"baiduUrl\":null,\"baiduHitNum\":null,\"zhihuTitle\":null,\"zhihuUrl\":null,\"zhihuAvgAnswerNumber\":null,\"eventClass\":\"娱乐八卦,法制问题,生活记录\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null},{\"id\":689,\"title\":\"全世界最好的井柏然\",\"score\":0.24787918,\"coordinate\":null,\"logoUrl\":null,\"readNum\":\"2.3亿\",\"readNumTrendGrowth\":null,\"relationDesc\":null,\"topicUrl\":\"http://weibo.com/p/100808b05c8bcf84dfc390457cc08a6bba43d6\",\"topicType\":\" 明星 内地 \",\"introduction\":\"全世界最好的井柏然\",\"logoImgUrl\":\"http://ww1.sinaimg.cn/thumb180/ee0ca20ejw1f1ylcggymqj2050050mxh.jpg\",\"logoImgUrlLocal\":null,\"prevailingTrend\":\"13\",\"wechatUrl\":\"http://mp.weixin.qq.com/s?src=3&timestamp=1478167431&ver=1&signature=ffSyLCcqns95AxUwHDs3Pu0x9S-GMJRQaBqZX9H6OUpaHsViSL991d6vrK27Ntq-xN9lT2g7chL5LeWZidpgU4DOpoDm8qDImWC1pmDFI*P7zKI9hhLqAqoJnNfslPSD*hWxOWrEd7naEMeU*u0KzxXQvh7W00NvR9yiTeA39kw=\",\"wechatTitle\":\"看了张一山的字，再也不能说井柏然写字最好看了\",\"wechatAvgReadNum\":7343,\"baiduTitle\":\"全世界最好的井柏然\",\"baiduUrl\":\"http://www.baidu.com/baidu?wd=%E5%85%A8%E4%B8%96%E7%95%8C%E6%9C%80%E5%A5%BD%E7%9A%84%E4%BA%95%E6%9F%8F%E7%84%B6\",\"baiduHitNum\":952000,\"zhihuTitle\":\"我很爱井柏然，我们不撕(づ ●─● )づ?\",\"zhihuUrl\":\"https://www.zhihu.com/question/39652539\",\"zhihuAvgAnswerNumber\":93,\"eventClass\":\"明星宣传\",\"isActive\":null,\"manualPrevailingTrend\":null,\"manualEventClass\":null,\"manualCreateDate\":null,\"manualUpdateDate\":null,\"manualIsApplied\":null,\"manualIntroduction\":null,\"manualTopicUrl\":null,\"manualBaiduUrl\":null,\"manualZhihuUrl\":null,\"manualWechatUrl\":null,\"manualTitle\":null,\"manualLogoImgUrl\":null}]";
var data = JSON.parse(Hotdata);
data.length = 10;
console.log(data)
drawWord(data)
/*显示热点图*/
function drawWord(data) {
    var pointArr = [];
    $.each(data, function(idx, item) {
        if(idx%2!=0){
            var r = 175 + (idx-1) * 25;
        }else{
            var r = 175 + idx * 25;
        }
        var fontClass = "fontClass1",
            sizeClass = "sizeClass1";
        var prevailingTrend=item.prevailingTrend?parseInt(item.prevailingTrend):0;
        if(prevailingTrend<= 50) {
            fontClass = "fontClass1";
            sizeClass = "sizeClass1";
        } else if(prevailingTrend<= 80) {
            fontClass = "fontClass2";
            sizeClass = "sizeClass2";
        } else if(prevailingTrend<= 90) {
            fontClass = "fontClass3";
            sizeClass = "sizeClass3";
        } else if(prevailingTrend<= 100) {
            fontClass = "fontClass4";
            sizeClass = "sizeClass4";
        }
        $item = $("<div class='topic' data-id="+item.id+"></div>")
            .data("info",item).appendTo($("#canvas"));
        $("<span class='icon " + sizeClass + "'><div class='iconCircle'></div></span><span class='link " + fontClass + "'>" + item.title + "</span>").appendTo($item);
        var itemWidth = $item.width();
        var itemHeight = $item.height();
        if(itemWidth > 180) {
            itemWidth = 180;
            $item.find(".link").css({
                "display": "block",
                "width": "180px"
            }).addClass("word-ellipsis");
        }
        var itemPos = checkao(idx, r, itemWidth, itemHeight, pointArr);
        pointArr.push({
            height: itemHeight,
            width: itemWidth,
            left: itemPos.left,
            top: itemPos.top
        });
        $item.css({
            "left": itemPos.left,
            "top": itemPos.top
        }).animate({
            "opacity": 1
        }, 300 + idx * 100);
    });
}
/*获得显示位置*/
function checkao(idx, r, itemWidth, itemHeight, pointArr) {
    var ao = 0;
    var elem = {
        width: itemWidth,
        height: itemHeight,
        left: 0,
        top: 0
    };
    do {
        if(idx % 2 == 0) {
            ao = (Math.floor(Math.random() * 10) % 2 == 0) ? Math.random() * (90 - 0 + 1) + 0 : Math.random() * (360 - 270 + 1) + 270;
        } else {
            ao = Math.random() * (270 - 90 + 1) + 90;
        }
        elem.left = 510 + r * Math.cos(ao * 3.14 / 180) - itemWidth / 2;
        elem.top = 160 + r * Math.sin(ao * 3.14 / 180) - itemHeight / 2;
    } while (hitTest(elem, pointArr) || elem.top + itemHeight > 275 || elem.top < 0)
    return elem;
}
/*检查重叠*/
function hitTest(elem, pointArr) {
    var overlapping = function(a, b) {
        if(Math.abs(2 * a.left + a.width - 2 * b.left - b.width) < a.width + b.width) {
            if(Math.abs(2 * a.top + a.height - 2 * b.top - b.height) < a.height + b.height) {
                return true;
            }
        }
        return false;
    };
    var i = 0;
    for(i = 0; i < pointArr.length; i++) {
        if(overlapping(elem, pointArr[i])) {
            return true;
        }
    }
    return false;
};
//hover事件
$(document).delegate('.topic','mouseover',function(){
    if(!$(this).hasClass("active")){
        $(this).find(".icon").css("background-color","#389b9f").find(".iconCircle").css("display","block");
        $(this).find(".link").css("font-weight","bold");
        if($(this).find('.link').hasClass("word-ellipsis")){
            $(this).css("z-index","9").find('.link').removeClass("word-ellipsis").css({"width":"auto"});
            $(this).css("left",$(this).position().left-($(this).width()-180)/2)
        }
    }
}).delegate('.topic','mouseout',function(){
    if(!$(this).hasClass("active")){
        $(this).find(".icon").css("background-color","#a3a3a3").find(".iconCircle").css("display","none");
        $(this).find(".link").css("font-weight","normal");
        if($(this).find('.link').width()>180){
            $(this).css("left",$(this).position().left+($(this).width()-180)/2)
            $(this).css("z-index","0").find('.link').css("width","180px").addClass("word-ellipsis");
        }
    }
});
$(document).delegate(".topic", "click", function(e) {/*点击显示弹窗*/
    e ? e.stopPropagation() : event.cancelBubble = true;
    var $activeItem=$(this).siblings("div.active");
    if($activeItem.length>0){
        $activeItem.removeClass("active")
        $activeItem.find(".icon").css("background-color","#a3a3a3").find(".iconCircle").css("display","none");
        $activeItem.find(".link").css("font-weight","normal");
        if($activeItem.find('.link').width()>180){
            $activeItem.css("left",$activeItem.position().left+($activeItem.width()-180)/2)
            $activeItem.css("z-index","0").find('.link').css("width","180px").addClass("word-ellipsis");
        }
    }
    $(this).addClass("active");
    var hotInfo=$(this).data("info");
    console.log(hotInfo)
    var _left=$(this).position().left;
    var _top=$(this).position().top;
    var width=$(this).width();
    if(_left+width/2>1070/2){
        left = _left-$(".alertCon").width()-10;
    }else{
        left = _left+width+10;
    }
    var top = (_top+$(this).height()/2) - $(".alertCon").height()/2;
    $(".alertCon").data("info",hotInfo);
    $(".alertCon").find(".infoTitle").text(hotInfo.title?hotInfo.title:"");
//    $(".alertCon").find(".infoConnect").attr("data-id",hotInfo.id?hotInfo.id:"");
    $(".alertCon").find(".infoText").text(hotInfo.introduction?hotInfo.introduction:"").attr("title",hotInfo.introduction?hotInfo.introduction:"");
    $(".alertCon").find(".hotValue").text(hotInfo.prevailingTrend?hotInfo.prevailingTrend:0);
    $(".alertCon").find(".weibo-link").attr("href",hotInfo.topicUrl?hotInfo.topicUrl:"#");
    if(hotInfo.wechatUrl){
        $(".alertCon").find(".weixin-link").attr("href",hotInfo.wechatUrl).css("display","inline-block");
    }else{
        $(".alertCon").find(".weixin-link").css("display","none");
    }
    if(hotInfo.zhihuUrl){
        $(".alertCon").find(".zhihu-link").attr("href",hotInfo.zhihuUrl).css("display","inline-block");
    }else{
        $(".alertCon").find(".zhihu-link").css("display","none");
    }
    if(hotInfo.baiduUrl){
        $(".alertCon").find(".baidu-link").attr("href",hotInfo.baiduUrl).css("display","inline-block");
    }else{
        $(".alertCon").find(".baidu-link").css("display","none");
    }
    if(hotInfo.logoImgUrl){
        $(".alertCon").find(".portrait").css("background-image","url("+hotInfo.logoImgUrl+")");
    }else{
        $(".alertCon").find(".portrait").css("background-image","url(img/defaultIcon.png)");
    }
    var eventClass=hotInfo.eventClass;
    $(".alertCon").find(".hotLabel").html("");
    if(eventClass){
        var typeArr=$.trim(eventClass).split(",");
        $.each(typeArr,function(idx,val){
            if(idx>2) return false;
            if(idx==2){
                $(".alertCon").find(".hotLabel").append("<div style='margin-right:0px;'>"+val+"</div>")
            }else{
                $(".alertCon").find(".hotLabel").append("<div>"+val+"</div>");
            }
        });
    }
    $(".alertCon").css({
        'position': 'absolute',
        'top': top,
        'left': left,
        'z-index': 200,
        'display': 'block'
    });
    $(".planText").css("margin-left",(262-75-72-$(".hotLeft").width())/2);
}).delegate(".alertCon", "click", function(e) {//弹窗内部防止冒泡
    e ? e.stopPropagation() : event.cancelBubble = true;
}).delegate(".all_hot_list", "click", function(e) {//弹窗内部防止冒泡
    e ? e.stopPropagation() : event.cancelBubble = true;
});
//相似热点
function similarHot(data){
	var chart = echarts.init(document.getElementById('wordCon'));
    option = {
		backgroundColor: '#309295',
		series: [{
		    name: '相似热点',
		    type: 'wordCloud',
		    // size: ['9%', '99%'],
		    sizeRange: [14, 18],
		    // textRotation: [0, 45, 90, -45],
		    rotationRange: [-90, 0],
		    rotationStep: 90,
		    textPadding: 0,
		    autoSize: {
		        enable: true,
		        minSize: 6
		    },
		    textStyle: {
		        normal: {
		            color: ["#fff"]
		        },
		        emphasis: {
		            shadowBlur: 10,
		            shadowColor: '#333'
		        }
		    },
		    data: []
		}]
		};
		
		var JosnList = [];
		
		JosnList.push({
			name: "春节",
			value: 450
			}, {
			name: "团聚",
			value: "500"
			}, {
			name: "回家过年",
			value: "400"
			}, {
			name: "过年",
			value: "350"
			}, {
			name: "车票",
			value: "300"
			});
		
	option.series[0].data = JosnList;
	chart.setOption(option)
}
similarHot();