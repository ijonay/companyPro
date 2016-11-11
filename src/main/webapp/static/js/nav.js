getHotPred(new Date());
//热点预测日历
function initCalendar(dateInfo){
    $(".pnl-calendar").calendar({
        width: 272,
        height: 264,
        data: [],
        onSelected: function(view, date, data) {
            if(data){
                $(".pnl-hots").find(".hots-date").text(getNowDate(date,"."));
                $container=$(".pnl-hots").find(".hots-list").html("");
                $.each(data,function(idx,item){
                    $("<li data-id="+item.id+"><a>"+item.name+"</a></li>").appendTo($container);
                    $(".pnl-hots").find(".hots-alert").css("display","none");
                    $(".pnl-hots").find(".hots-content").css("display","block");
                    $(".pnl-hots").css("display","block");
                });
            }else{
                $(".pnl-hots").find(".hots-alert").css("display","block");
                $(".pnl-hots").find(".hots-content").css("display","none");
                $(".pnl-hots").css("display","block");
            }
        }
    });
}

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

//获取当前日期热点预告
function getHotPred(date){
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: dataUrl.util.getHotPred(getNowDate(date)),
        success: function(returnData) {
            if(returnData.error.code == 0) {
                if(returnData.data){
                    initCalendar(getInfo(returnData.data));
                    var rel=formatterData(returnData.data)
                    dispList(rel,getNowDate(date));
                }
            } else {
                console.log('没有热点预告');
            }
        },
        error: function() {
            console.log('获取热点预告失败');
        }
    });
}

//处理获取到的热点预告数据
function getInfo(data){
    var dataDate=[];
    var tempDate={};
    $.each(data,function(idx,item){
        var startDate=item.startDate;
        var endDate=item.endDate;
        var name=item.name;
        var id=item.id;
        var startDateArr=startDate.split("-");
        var endDateArr=endDate.split("-");
        if(startDateArr.length==3&&endDateArr.length==3){
            var startDay=parseInt(startDateArr[2]);
            var endDay=parseInt(endDateArr[2]);
            var startI=startDay<=endDay?startDay:1;
            for(i=startI;i<=endDateArr[2];i++){
                var currDate = endDateArr[0]+"/"+endDateArr[1]+"/"+i;
                if(!tempDate[currDate]){
                    tempDate[currDate]=[];
                }
                tempDate[currDate].push({id:item.id,name:name});
            }
        }
    });
    $.each(tempDate,function(key,val){
        var sub={
            date: key,
            value: val
        };
        dataDate.push(sub);
    });
    return dataDate;
}
//处理当前月的数据
function formatterData(data){
    var tempDate={};
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
            tempDate[currDate].push({id:item.id,name:name});
        }
    });
    return tempDate;
}
//显示当月预告列表
function dispList(data,date){
    if(data){
        var dateArr=date.split("-");
        var txt=dateArr[0]+"年"+dateArr[1]+"月份热点预告";
        $(".pnl-hots").find(".hots-date").text(txt);
        $container=$(".pnl-hots").find(".hots-list").html("");
        $.each(data,function(key,val){
            $.each(val,function(idx,item){
                $li=$("<li data-index="+key+"></li>").appendTo($container);
                $("<span>"+item.name+"</span>").appendTo($li);
            });
            $(".pnl-hots").find(".hots-alert").css("display","none");
            $(".pnl-hots").find(".hots-content").css("display","block");
        });
    }else{
        $(".pnl-hots").find(".hots-alert").css("display","block");
        $(".pnl-hots").find(".hots-content").css("display","none");
    }
}

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