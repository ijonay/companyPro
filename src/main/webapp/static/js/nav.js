var tempData=tempData||{};
getHotPred(getNowDate(new Date()));
//热点预测日历
$(".pnl-calendar").calendar({
    width: 272,
    height: 264,
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
            var loc=checkLine(startD,startD);
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
            var loc=checkLine(startD,startD);
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
    if(data){
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
                $container.find("li[data-index='"+currDate+"']").append("<span class='content'><span class='title'>"+item.name+"</span></span>");
            }else{
                $("<li data-index="+currDate+"><span class='content'><span class='title'>"+item.name+"</span><span class='date'>"+currDate+"</span></span></li>").appendTo($container);
            }
        });
    }else{
        $container.html("<li class='error'>暂无热点预告</li>");
    }
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