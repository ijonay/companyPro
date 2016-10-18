/*
 *resizable 0.1
 *Copyright (c) 2015 小坏 http://tnnyang.cnblogs.com
 *Dependenc jquery-1.7.1.js
 */
;
(function(a) {
    a.fn.resizable = function(options) {
        var defaults = { //默认参数
            minW: 150,
            minH: 150,
            maxW: 500,
            maxH: 500,
        }
        var opts = a.extend(defaults, options);

        this.each(function() {
            var obj = a(this);
            obj.off("mousedown").bind("mousedown",function(e) { 
                var e = e || event;//区分IE和其他浏览器事件对象
                if (e.stopPropagation) {
                    e.stopPropagation();
                } else if (event) {
                    event.cancelBubble = true; // 兼容IE
                }
                var cardHeight=obj.parent("li").height(),
                	itemWidth=obj.parent("li").width(),
                    itemHeight=obj.parent("li").find(".item").height(),
                    cardId=obj.parent("li").data("index");
                var isRight=$(this).hasClass("item-bottom-right");
                var x = isRight?e.pageX - obj.parent("li").width():e.pageX+obj.parent("li").width(); //获取鼠标距离匹配元素的父元素左侧的距离                       
                var y = e.pageY - obj.position().top; //获取鼠标距离匹配元素的父元素顶端的距离
                $("*:not('.item-top')").off("mousemove").bind("mousemove",function(e) {
                    var e = e || event;
                    if (e.stopPropagation) {
                        e.stopPropagation();
                    } else if (event) {
                        event.cancelBubble = true; // 兼容IE
                    }
                    _x = isRight?e.pageX - x:x - e.pageX; //动态获取匹配元素距离其父元素左侧的宽度
                    _y = e.pageY - y;
                    if(_x>195){
                    	obj.parent("li").attr("data-ss-colspan","2");
                    	placeholderWidth=395;
                    }else{
                    	obj.parent("li").removeAttr("data-ss-colspan");
                    	placeholderWidth=195;
                    }
                    if(_y>220){
                    	placeholderHeight=445;
                    	obj.parent("li").attr("data-ss-rowspan","2");
                    	obj.parent("li").attr("data-ss-colspan","2");
                    	placeholderWidth=395;
                    }else{
                    	placeholderHeight=220;
                    	obj.parent("li").removeAttr("data-ss-rowspan");
                    }
                    obj.parent("li").find(".placeholder").remove();
                    $("<li class='placeholder' style='height: "+placeholderHeight+"px; width: "+placeholderWidth+"px;background: transparent;border: 1px dashed #bfbfbf;position:absolute;top:0;'></li>").appendTo(obj.parent("li"));
                    _x = _x < opts.minW ? opts.minW : _x; //保证匹配元素的最小宽度为150px
                    _x = _x > opts.maxW ? opts.maxW : _x; //保证匹配元素的最大宽度为500px
                    _y = _y < opts.minH ? opts.minH : _y;
                    _y = _y > opts.maxH ? opts.maxH : _y;
                    obj.parent("li").css({
                        width: _x,
                        height: _y
                    });
                    obj.parent("li").find(".item")
                    .css("height",itemHeight+(_y-cardHeight));
                    var myChart=_.findWhere(chartsAttr.templates.myChart,{cardId:cardId});
                    if(myChart){
                    	myChart.chart.resize();
                    }
                    
                    $("#cardList").trigger('ss-rearrange');
                    if(!isRight){
                    	if((_x>195 || _y>220) && !obj.parent("li").hasClass("isBefore")&& itemWidth==195){
                    		if(obj.parent("li").position().top==obj.parent("li").prev().position().top){
                        		obj.parent("li").removeClass("isAfter").addClass("isBefore")
                        		.after(obj.parent("li").prev());
                    		}
                    	}else if(_x<=195 &&_y<=220 && !obj.parent("li").hasClass("isAfter") && obj.parent("li").hasClass("isBefore")){
                    		obj.parent("li").removeClass("isBefore").addClass("isAfter").before(obj.parent("li").next());
                    	};
                    	if(_x>195 || _y>220){
                        	var itemLeft=obj.parent("li").position().left
                        	var increment=_x-itemWidth;
                        	if(itemWidth==195){
                        		obj.parent("li").css("left",(itemLeft+200)-increment).find(".placeholder").css("left",-200+increment);
                        	}else{
                        		obj.parent("li").css("left",itemLeft-increment).find(".placeholder").css("left",increment);
                        	}
                    	}
                    }
                    home.util.isLinkDetail = false;
                }).off("mouseup").bind("mouseup",function(e) {
                	$("*:not('.item-top')").unbind("mousemove"); //当鼠标抬起  删除移动事件   匹配元素宽高变化停止
                	$("*:not('.item-top')").unbind("mouseup");
                	obj.parent("li").removeClass("isBefore").removeClass("isAfter").find(".placeholder").remove();
                	var $item=obj.parent("li"),
                        cardId=$item.data("index"),
                        orgCard=_.findWhere(home.util.cardsInOrder, {cardId: cardId}),
                        newCard={
                            type: orgCard.type,
                            id: orgCard.id,
                            order: orgCard.order ,
                            size: parseInt(orgCard.size)
                        };
                	var myChart=_.findWhere(chartsAttr.templates.myChart,{cardId:cardId});
                	var option=null;
                    if(myChart){
                    	option=myChart.chart.getOption();
                    }
                    if(_x>240&&_y<=270){
                    	obj.parent("li").removeAttr("data-ss-rowspan");
                        $item.css({width:395,height:220})
                        .attr("data-ss-colspan", "2");
                        $item.find(".item").removeClass("item-lg")
                        .removeClass("item-sm")
                        .addClass("item-md")
                        .css("height","120");
                        orgCard.size=2;
                        newCard.size=orgCard.size;
                        if(option){
                        	option.grid[0]=defCharts.util.funSetgrid(false, orgCard.size);
                            if(option.xAxis&&option.xAxis.length>0){
                                option.xAxis[0].axisLine.show=true;
                                option.xAxis[0].axisLabel.show=true;
                            }
                            if(option.yAxis&&option.yAxis.length>0){
                            	option.yAxis[0].axisLine.show=true;
                                option.yAxis[0].axisLabel.show=true;
                            }
                            if(option.series && option.series.length>0 && option.series[0].center){
                            	option.series[0].center = [ '50%', '50%' ]
                            }
                            if(option.series && option.series.length>0 && option.series[0].radius){
                            	option.series[0].radius = '100%';
                            }
                            option.legend[0].show=false;
                        }
                    }else if(_x>240&&_y>270){
                        $item.css({height:445,width:395})
                        .attr("data-ss-rowspan", "2")
                        .attr("data-ss-colspan", "2");
                        $item.find(".item").removeClass("item-md")
                        .removeClass("item-sm")
                        .addClass("item-lg")
                        .css("height","330");
                        orgCard.size=4;
                        newCard.size=orgCard.size;
                        if(option){
                        	option.grid[0]=defCharts.util.funSetgrid(false, orgCard.size);
                            if(option.xAxis&&option.xAxis.length>0){
                                option.xAxis[0].axisLine.show=true;
                                option.xAxis[0].axisLabel.show=true;
                            }
                            if(option.yAxis&&option.yAxis.length>0){
                            	option.yAxis[0].axisLine.show=true;
                                option.yAxis[0].axisLabel.show=true;
                            }
                            if(option.series && option.series.length>0 && option.series[0].center){
                            	option.series[0].center = [ '50%', '57.5%' ]
                            }
                            if(option.series && option.series.length>0 && option.series[0].radius){
                            	option.series[0].radius = '85%';
                            }
                            option.legend[0].show=true;
                        }
                    }else{
                        $item.css({height:220,width:195})
                        .removeAttr("data-ss-rowspan")
                        .removeAttr("data-ss-colspan");
                        $item.find(".item").removeClass("item-lg")
                        .removeClass("item-md")
                        .addClass("item-sm")
                        .css("height","110");
                        orgCard.size=1;
                        newCard.size=orgCard.size;
                        if(option){
                        	option.grid[0]=defCharts.util.funSetgrid(false, orgCard.size);
                            if(option.xAxis&&option.xAxis.length>0){
                                option.xAxis[0].axisLine.show=false;
                                option.xAxis[0].axisLabel.show=false;
                            }
                            if(option.yAxis&&option.yAxis.length>0){
                            	option.yAxis[0].axisLine.show=false;
                                option.yAxis[0].axisLabel.show=false;
                                option.legend[0].show=false;
                            }
                            if(option.series && option.series.length>0 && option.series[0].center){
                            	option.series[0].center = [ '50%', '50%' ]
                            }
                            if(option.series && option.series.length>0 && option.series[0].radius){
                            	option.series[0].radius = '100%';
                            }
                            option.legend[0].show=false;
                        }
                    }
                    if(myChart){
                    	myChart.chart.resize();
                        myChart.chart.setOption(option);
                    }
                    // 将卡片排序保存到数据库
                    $("#cardList").trigger("ss-drop-complete");
                    $("#cardList").trigger('ss-rearrange');
                });
            });
        });
    }
})(jQuery);