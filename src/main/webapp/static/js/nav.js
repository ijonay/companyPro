var tempData=tempData||{};
getHotPred(getNowDate(new Date()));
//热点预测日历
$(".pnl-calendar").calendar({
    width: 272,
    height: 277,
    data: [],
    onSelected: function(view, date, data) {
        var day=parseInt($(this).data("index"));
        $("a.abled").siblings("div").removeClass("active");
        $(this).siblings("div").each(function(){
            var id=$(this).data("id");
            $("a.abled").siblings("div[data-id='"+id+"']").addClass("active");
        });
        dispList(tempData.result,getNowDate(date),"single");
    }
});
$(document).delegate(".hots-list>li","click",function(){//查看预告详情
    if($(this).find(".desc").css("display")!="none"){
        $(this).find(".desc").css("display","none");
    }else{
        $(this).siblings("li").find(".desc").css("display","none");
        $(this).find(".desc").css("display","block");
    }
});

/*邮箱开关按钮*/
$(".lb-email").on("click",function(){
    if($(this).hasClass("active")){//关闭
        $(this).removeClass("active").css("background-color","#d8d8d8").find(".lb-circle").animate({"left":"1px"},100);
        $(".relate-title").css("opacity","0.5");
    }else{//打开
        $(this).addClass("active").css("background-color","#29d5da").find(".lb-circle").animate({"left":"21px"},100);
        $(".relate-title").css("opacity","1");
    }
});

/*tab切换*/
$(".bar-tabs>li").on("click",function(){
    if($(this).hasClass("right-bar-close")){//关闭弹窗
        $(".right-bar").animate({"right":"-272px"},500);
    }else if($(this).hasClass("pred-tab")){//热点预告tab
        $(this).addClass("active").siblings(".notify-tab").removeClass("active");
        $(".pnl-notify-tab").css("display","none");
        $(".notify-operate").css("display","none");
        $(".bar-content").css("max-height","100%");
        $(".pnl-pred-tab").css("display","block");
        $(".right-bar").css("background","#fff");
    }else{//探索通知tab
        $(this).addClass("active").siblings(".pred-tab").removeClass("active");
        $(".pnl-notify-tab").css("display","block");
        $(".notify-operate").css("display","block");
        $(".bar-content").css("max-height",$(window).height()-70);
        $(".pnl-pred-tab").css("display","none");
        $(".right-bar").css("background","#e8ebed");
    }
});
/*头部菜单栏*/
$(".header-right>li").on("click",function(){
    if($(this).hasClass("head-pred")){//热点预告
        $(".bar-tabs>li.pred-tab").addClass("active").siblings(".notify-tab").removeClass("active");
        $(".pnl-notify-tab").css("display","none");
        $(".notify-operate").css("display","none");
        $(".bar-content").css("max-height","100%");
        $(".pnl-pred-tab").css("display","block");
        $(".right-bar").animate({"right":"0px"},500).css("background","#fff");
    }else if($(this).hasClass("head-notify")){//探索通知
        $(".bar-tabs>li.notify-tab").addClass("active").siblings(".pred-tab").removeClass("active");
        $(".pnl-notify-tab").css("display","block");
        $(".notify-operate").css("display","block");
        $(".bar-content").css("max-height",$(window).height()-70);
        $(".pnl-pred-tab").css("display","none");
        $(".right-bar").animate({"right":"0px"},500).css("background","#e8ebed");
    }
});
/*关闭通知*/
$(document).delegate(".notify-list>li .notify-close","click",function(e){
    e ? e.stopPropagation() : event.cancelBubble = true;
    $(this).parents("li").remove();
    if($(".notify-list").find("li").length==0){
        $(".notify-list").css("display","none");
    }
}).delegate(".notify-list>li","click",function(e){/*查看通知详情*/
    var id=$(this).data("id");
    $(".bar-tabs>li.notify-tab").addClass("active").siblings(".pred-tab").removeClass("active");
    $(".pnl-notify-tab").css("display","block");
    $(".notify-operate").css("display","block");
    $(".bar-content").css("max-height",$(window).height()-70);
    $(".pnl-pred-tab").css("display","none");
    $(".right-bar").animate({"right":"0px"},500).css("background","#e8ebed");
    $(".notify-tab-list").find("li[data-id="+id+"]").trigger("click");
}).delegate(".notify-tab-list>li","mouseover",function(e){/*查看通知详情*/
    $(this).find(".notify-close").css("display","inline-block");
    $(this).find(".time").css("display","none");
}).delegate(".notify-tab-list>li","mouseout",function(e){/*查看通知详情*/
    $(this).find(".time").css("display","inline-block");
    $(this).find(".notify-close").css("display","none");
}).delegate(".notify-tab-list>li .notify-close","click",function(e){//删除通知
    e ? e.stopPropagation() : event.cancelBubble = true;
    var id=$(this).parents("li").data("id");
    var _this=$(this);
    $.ajax({
        type: "delete",
        url: dataUrl.util.delNotify(id),
        success: function(returnData) {
            if(returnData.error.code==0){
                _this.parents("li").remove();
                if($(".notify-tab-list").find("li").length==0){
                    $(".notify-tab-list").css("display","none");
                }
                var count=parseInt($(".notify-count").data("count"));
                if(count<=1){
                    $(".notify-count").data("count",0).text("").css("display","none");
                }else if(count<=10){
                    $(".notify-count").data("count",count-1).text(count-1).css("display","block");
                }else{
                    $(".notify-count").data("count",count-1).text("···").css("display","block");
                }
            }
        },
        error: function() {
            console.log('删除探索通知失败');
        }
    });
}).delegate(".clear-notify","click",function(e){//清空通知
    e ? e.stopPropagation() : event.cancelBubble = true;
    var ids=[];
    $(".notify-tab-list>li").each(function(){
        ids.push(parseInt($(this).data("id")));
    })
    if(ids.length>0){
        $.ajax({
            type: "delete",
            url: dataUrl.util.delAllNotify(ids.join(",")),
            success: function(returnData) {
                $(".notify-tab-list>li").remove();
            },
            error: function() {
                console.log('清空探索通知失败');
            }
        });
    }
});
//点击任意地方关闭弹窗
$(document).on('click',function(e){
    if($(".right-bar").css("right") == "0px"){
        if($(window).width() - e.clientX > 277)
        $(".right-bar-close").trigger("click");
    }
});
//获取当前日期热点预告
function getHotPred(date){
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: dataUrl.util.getHotPred(date),
        success: function(returnData) {
            tempData.result=returnData.data;
            dispList(tempData.result,date,"all");//显示所有预告
            dispBottom(tempData.result);//做下标
        },
        error: function() {
            console.log('获取热点预告失败');
        }
    });
}
//日期做下标
function dispBottom(data){
    $('.date-items>li').eq(1).find("a.abled").siblings("div").remove();
    var tempDate={};
    var singleDate=[];
    var rangeDate=[];
    $.each(data,function(idx,item){
        var startDate=item.startDate;
        var endDate=item.endDate;
        var name=item.name;
        var id=item.id;
        var startDateArr=startDate.split("-");
        var endDateArr=endDate.split("-");
        if(startDateArr.length==3&&endDateArr.length==3){
            var y=startDateArr[0];
            var m=startDateArr[1];
            var startD=startDateArr[2];
            var endD=endDateArr[2];
            var currDate="";
            if(startD==endD){
                currDate = m+"."+startD;
                
            }else if(startD<endD){
                currDate = m+"."+startD+"-"+endD;
            }
            if(!tempDate[currDate]){
                tempDate[currDate]=[];
            }
            tempDate[currDate].push({id:item.id,name:name,start:startD,end:endD});
        }
    });
    $.each(tempDate,function(key,val){
        if(key.indexOf("-")!=-1){
            rangeDate.push(val);
        }else{
            singleDate.push(val);
        }
    });
    $.each(rangeDate,function(idx,res){
        $.each(res,function(subIdx,subRes){
            var startD=parseInt(subRes.start);
            var endD=parseInt(subRes.end);
            var loc=checkLine(startD,endD);
            if(loc!=0){
                for(i=startD;i<=endD;i++){
                    var $date=$('.date-items>li').eq(1).find("a.abled[data-index="+i+"]");
                    var divCount=$date.siblings("div").length;
                    var width=60;
                    var left=0;
                    if(i==startD){
                        width=80;
                        left=20;
                    }else if(i==endD){
                        width=80;
                        left=0;
                    }else{
                        width=100;
                        left=0;
                    }
                    if(divCount<3){
                        $("<div class='line"+loc+"' data-id="+subRes.id+"></div>").css({"width":width+"%","left":left+"%"}).appendTo($date.parents("li"));
                    }
                }
            }
        })
        
    });
    $.each(singleDate,function(idx,item){
        $.each(item,function(subIdx,subRes){
            var startD=parseInt(subRes.start);
            var endD=parseInt(subRes.end);
            var loc=checkLine(startD,endD);
            var $date=$('.date-items>li').eq(1).find("a.abled[data-index="+startD+"]");
            var divCount=$date.siblings("div").length;
            if(divCount<3){
                $date.parents("li").append("<div data-id="+subRes.id+" style='width:60%;left:20%;' class='line"+loc+"'></div>")
            }
        });
    });
}
//显示预告列表data:数据，date:日期,type:当月还是当天
function dispList(data,date,type){
    var dateArr=date.split("-");
    if(type=="all"){
        var txt=dateArr[0]+"年"+dateArr[1]+"月份热点预告";
        $(".pnl-hots").find(".hots-date").text(txt);
        $container=$(".pnl-hots").find(".hots-list").html("");
        $(".pnl-hots").find(".hots-detail").css("display","none");
        $(".pnl-hots").find(".hots-content").css("display","block");
    }else{
        var txt=dateArr[0]+"."+dateArr[1]+"."+dateArr[2];
        $(".pnl-hots").find(".detail-date").text(txt);
        $container=$(".pnl-hots").find(".detail-list").html("");
        $(".pnl-hots").find(".hots-detail").css("display","block");
        $(".pnl-hots").find(".hots-content").css("display","none");
    }
        $.each(data,function(idx,item){
            var startDate=item.startDate;
            var endDate=item.endDate;
            var name=item.name;
            var id=item.id;
            var startDateArr=startDate.split("-");
            var endDateArr=endDate.split("-");
            var y=startDateArr[0];
            var m=startDateArr[1];
            var startD=startDateArr[2];
            var endD=endDateArr[2];
            var currDate="";
            if(type!="all"&&(parseInt(dateArr[2])<parseInt(startD)||parseInt(dateArr[2])>parseInt(endD))) return;
            if(startD==endD){
                currDate = m+"."+startD;
                
            }else if(startD<endD){
                currDate = m+"."+startD+"-"+endD;
            }
            if($container.find("li[data-index='"+currDate+"']").length>0){
                if(item.note){
                    $container.find("li[data-index='"+currDate+"']").append("<span class='content'><span class='title'>"+item.name+"</span></span><span class='content desc'>"+item.note+"</span>");
                }else{
                    $container.find("li[data-index='"+currDate+"']").append("<span class='content'><span class='title'>"+item.name+"</span></span>");
                }
            }else{
                if(item.note){
                    $("<li data-index="+currDate+"><span class='content'><span class='title'>"+item.name+"</span><span class='date'>"+currDate+"</span></span><span class='content desc'>"+item.note+"</span></li>").appendTo($container);
                }else{
                    $("<li data-index="+currDate+"><span class='content'><span class='title'>"+item.name+"</span><span class='date'>"+currDate+"</span></span></li>").appendTo($container);
                }
            }
        });
    if($container.find("li").length<1)$container.html("<li class='error'>暂无热点预告</li>");
}

//检查划线位置及是否划线
function checkLine(start,end){
    var loc3=true;
    var loc2=true;
    var loc1=true;
    var loc0=true;
    for(i=start;i<=end;i++){
        var $date=$('.date-items>li').eq(1).find("a.abled[data-index="+i+"]");
        var $div=$date.siblings("div");
        if($div.hasClass("line3")){
            loc3=false;
        }
        if($div.hasClass("line2")){
            loc2=false;
        }
        if($div.hasClass("line1")){
            loc1=false;
        }
    }
    if(loc1)return 1;//不添加下划线
    else if(loc2)return 2;//在第三个位置添加
    else if(loc3)return 3;//在第二个位置划线
    else return 0;
}
//返回当月热点预告
$(".detail-return").on("click",function(){
    $("a.abled").removeClass("selected").siblings("div").removeClass("active");
    $(".pnl-hots").find(".hots-detail").css("display","none");
    $(".pnl-hots").find(".hots-content").css("display","block");
});

//获取当前时间
function getNowDate(myDate,type){
    //获取当前年
    var year=myDate.getFullYear();
    //获取当前月
    var month=myDate.getMonth()+1;
    //获取当前日
    var date=myDate.getDate();
    if(type){
        return year+type+month+type+date;
    }else{
        return year+'-'+month+"-"+date;
    }
}

//获取探索通知
$.ajax({
    type: "get",
    contentType: 'application/json',
    dataType: "json",
    url: dataUrl.util.getNotify(15),
    success: function(returnData) {
        if(returnData.error.code == 0&&returnData.data) {
            $(".notify-list").html("");
            var res=returnData.data
            var count=res.length;
            if(count>0){
                $(".notify-count").attr("data-count",count).text(count>9?"···":count).css("display","block");
                $.each(res,function(idx,item){
                    item.createDate=GetDateDiff(item.createDate);
                    if(idx<3){
                        $('<li data-id='+item.id+'><a><span class="dot-icon"></span>'+
                        '<span class="hot-word">'+item.keyword+'-</span>'+
                        '<span class="hot-spot">'+item.title+'</span><span class="time">'+item.createDate+'</span><span class="notify-close">&times;</span></a></li>')
                        .appendTo($(".notify-list"));
                    }
                });
                if($(".notify-list").find("li").length>0){
                    $(".notify-list").css("display","block");
                }
                $(".notify-tab-list").html($.templates(templates.design["tmplNotifyList"]).render(returnData));
            }else{
                $(".notify-count").attr("data-count",0).text("").css("display","none");
                $(".notify-tab-list").html("<li style='text-align:center;'><a>暂无通知</a></li>");
            }
        }else{
            $(".notify-tab-list").html("<li style='text-align:center;'><a>暂无通知</a></li>");
            $(".notify-operate").css("display","none");
        }
    },
    error: function() {
        console.log('获取探索通知失败');
    }
});
//点击通知查看详情
$(document).delegate(".notify-tab-list>li","click",function(){
    if($(this).find(".notify-info").css("display")!="none"){
        $(this).find(".notify-info").css("display","none");
    }else{
        $(this).siblings("li").find(".notify-info").css("display","none");
        var id=$(this).data("id");
        $detail=$(this).find(".notify-info").css("display","block");
        $.ajax({
            type: "get",
            contentType: 'application/json',
            dataType: "json",
            url: dataUrl.util.getNotifyDetail(id),
            success: function(returnData) {
                if(returnData.error.code == 0&&returnData.data) {
                    var hotInfo=returnData.data;
                    $detail.find(".notify-infoTitle").text(hotInfo.title?hotInfo.title:"");
                    $detail.find(".notify-infoConnect").attr("data-id",hotInfo.id?hotInfo.id:"");
                    $detail.find(".notify-infoText").text(hotInfo.introduction?hotInfo.introduction:"").attr("title",hotInfo.introduction?hotInfo.introduction:"");
                    $detail.find(".notify-hotValue").text(hotInfo.prevailingTrend?hotInfo.prevailingTrend:0);
                    $detail.find(".weibo-link").attr("href",hotInfo.topicUrl?hotInfo.topicUrl:"#");
                    if(hotInfo.wechatUrl){
                        $detail.find(".weixin-link").attr("href",hotInfo.wechatUrl).css("display","inline-block");
                    }else{
                        $detail.find(".weixin-link").css("display","none");
                    }
                    if(hotInfo.zhihuUrl){
                        $detail.find(".zhihu-link").attr("href",hotInfo.zhihuUrl).css("display","inline-block");
                    }else{
                        $detail.find(".zhihu-link").css("display","none");
                    }
                    if(hotInfo.baiduUrl){
                        $detail.find(".baidu-link").attr("href",hotInfo.baiduUrl).css("display","inline-block");
                    }else{
                        $detail.find(".baidu-link").css("display","none");
                    }
                    var eventClass=hotInfo.eventClass;
                    if(eventClass){
                        var typeArr=$.trim(eventClass).split(",");
                        $.each(typeArr,function(idx,val){
                            if(idx>2) return false;
                            $detail.find(".notify-hotLabel"+idx).text(val);
                        });
                    }else{
                        $detail.find(".notify-hotLabel0").text("");
                        $detail.find(".notify-hotLabel1").text("");
                        $detail.find(".notify-hotLabel2").text("");
                    }
                }
            },
            error: function() {
                console.log('获取探索通知失败');
            }
        });
    }
}).delegate(".notify-infoConnect","click",function(){
    var topicId=$(this).parents("li").data("topicId");
    var query=$(this).parents("li").find(".hot-word").text();
    var hotTopic=$(this).parents("li").find(".hot-spot").text();
    window.location.href='newPath#query='+query+'&topicId='+topicId+"&hotTopic="+hotTopic;
});
//获取俩个日期之差
function GetDateDiff(startTime) {
    //将xxxx-xx-xx的时间格式，转换为 xxxx/xx/xx的格式 
    startTime = startTime.replace(/\-/g, "/");

    //将计算间隔类性字符转换为小写
    var sTime = new Date(startTime);      //开始时间
    var eTime = new Date();  //结束时间
    var tempDate=eTime.getTime() - sTime.getTime();
    var info=null;
    if(parseInt(tempDate/(1000 * 3600 * 24))>0){
        var m=sTime.getMonth()+1;
        var d=sTime.getDate();
        info=m+"-"+d;
    }else{
        var h=sTime.getHours();
        var m=sTime.getMinutes();
        if(m<10){
            info=h+":0"+m;
        }else{
            info=h+":"+m;
        }
    }
    return info;
}
//点击logo返回首页
$(".header-logo").on("click",function(){
    var url=window.location.href;
    if(url.indexOf("hotsystem")==-1){
        window.location.href="hotsystem";
    }
});

//滚动条
jQuery(document).ready(function ($) {
    "use strict";
    $('.bar-content').perfectScrollbar({suppressScrollX: true});
});