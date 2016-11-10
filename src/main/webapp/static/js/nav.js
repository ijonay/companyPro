//热点预测日历
$(".pnl-calendar").calendar({
    width: 272,
    height: 264,
    data: [{
        date: '2016/11/13',
        value: '1113'
    },
    {
        date: '2016/11/14',
        value: '1114'
    },{
        date: '2016/11/15',
        value: '1115'
    }],
    onSelected: function(view, date, data) {
        console.log('date:' + date);
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
        $(".pnl-pred-tab").css("display","block");
    }else{//探索通知tab
        $(this).addClass("active").siblings(".pred-tab").removeClass("active");
        $(".pnl-notify-tab").css("display","block");
        $(".pnl-pred-tab").css("display","none");
    }
});
/*头部菜单栏*/
$(".header-right>li").on("click",function(){
    if($(this).hasClass("head-pred")){//热点预告
        $(".bar-tabs>li.pred-tab").addClass("active").siblings(".notify-tab").removeClass("active");
        $(".pnl-notify-tab").css("display","none");
        $(".pnl-pred-tab").css("display","block");
        $(".right-bar").animate({"right":"0px"},500);
    }else if($(this).hasClass("head-notify")){//探索通知
        $(".bar-tabs>li.notify-tab").addClass("active").siblings(".pred-tab").removeClass("active");
        $(".pnl-notify-tab").css("display","block");
        $(".pnl-pred-tab").css("display","none");
        $(".right-bar").animate({"right":"0px"},500);
    }
});
/*关闭通知*/
$(".notify-list").delegate("li .notify-close","click",function(){
    $(this).parents("li").remove();
    if($(".notify-list").find("li").length==0){
        $(".notify-list").css("display","none");
    }
});
//点击任意地方关闭弹窗
$(document).on('click',function(e){
    if($(".right-bar").css("right") == "0px"){
        if($(window).width() - e.clientX > 277)
        $(".right-bar-close").trigger("click");
    }
});