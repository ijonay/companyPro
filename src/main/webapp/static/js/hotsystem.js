//头部。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。



//搜索。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
	//设置为常用
	$('#ser_text').on('input',function(e){
		e ? e.stopPropagation() : event.cancelBubble = true;	
		$('#favorite_set_btn').removeClass('hidecommon');
		$('#cook_ul').addClass('hidecommon');
	});
	$(document).on('click',function(){
		$('#cook_ul').addClass('hidecommon');
		$('#favorite_set_btn').addClass('hidecommon');
	});

	$('#favorite_set_btn').on('click',function(){
		var val = $.trim($('#ser_text').val());
		var len = $('#favorite_ul li').length;
		var arrCon = [];
		$('#favorite_ul li').each(function(i,item){
			arrCon.push($(item).text());
			return arrCon;
		});
		Array.prototype.contains = function (obj) {  
		    var i = this.length;  
		    while (i--) {  
		        if (this[i] === obj) {  
		            return true;  
		        }  
		    }  
		    return false;  
		}  
		if(arrCon.contains(val)==true ){
			return;
		};
		
		if(len>=5){
			$('#favorite_ul').find('li').eq(4).remove();
			$('#favorite_ul').prepend('<li>'+val+'<span></span></li>');
		}else{
			$('#favorite_ul').prepend('<li>'+val+'<span></span></li>');
		};
	});
	$('#favorite_ul').delegate('li','click',function(){
		var val = $(this).text();
		$('#ser_text').val(val);
	});
	
	$('#favorite_ul').delegate('li span','click',function(e){
		e ? e.stopPropagation() : event.cancelBubble = true;
		$(this).parent().remove();
	});
	
	//设置为历史记录
	$('#ser_text').focus(function(e){
		e ? e.stopPropagation() : event.cancelBubble = true;	
		$('#cook_ul').removeClass('hidecommon');
	});
	$('#ser_text').blur(function(){
		//$('#cook_ul').addClass('hidecommon');
	});
	
	$('#ser_btn').on('click',function(){
		var val = $.trim($('#ser_text').val());
		var len = $('#cook_ul li').length;
		var arrCon = [];
		$('#cook_ul li').each(function(i,item){
			arrCon.push($(item).text());
			return arrCon;
		});
		Array.prototype.contains = function (obj) {  
		    var i = this.length;  
		    while (i--) {  
		        if (this[i] === obj) {  
		            return true;  
		        }  
		    }  
		    return false;  
		}  
		if(arrCon.contains(val)==true ){
			return;
		};
		
		if(!val){
			return;
		}else{
			if(len>=5){
				$('#cook_ul li').eq(4).remove();
				$('#cook_ul').prepend('<li>'+val+'<span></span></li>');
			}else{
				$('#cook_ul').prepend('<li>'+val+'<span></span></li>');
			};
		};
		
	});

	$('#ser_text').on('click',function(e){
		e ? e.stopPropagation() : event.cancelBubble = true;
		//$('#cook_ul').addClass('hidecommon');
	});
	$('#cook_ul').delegate('li','click',function(e){
		e ? e.stopPropagation() : event.cancelBubble = true;
		$('#cook_ul').addClass('hidecommon');
		var val = $(this).text();
		$('#ser_text').val(val);
	});
	
	$('#cook_ul').delegate('li span','click',function(e){
		e ? e.stopPropagation() : event.cancelBubble = true;
		$(this).parent().remove();
	});
	
//高级搜索弹窗。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
	$('#ser_btn_high').on('click',function(){
		$('#ser_dialog').removeClass('hidecommon');
	});
	
	$('.dialog_area span').on('click',function(){
		$('#ser_dialog').addClass('hidecommon');
	})
	
//曲线。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
var paper;
var base;
var change1;
var change2;
var rectArray = [];
var hotArray = [];
var textArray = [];
var alertCon = $(".alertCon");
var idArray = [1,2,3,4,5,6,7,8,9,10];
var triangleStep = 35;
function loadSvg(){
        var width = $("#papersvg").css("width");
    width = width.split("px")[0];
    paper = Raphael("papersvg",width,200);
    //paper.clear()
    var xArray = [];
    var yArray = [];
    var step = width/11;
    for(var i=1;i<11;i++){
        xArray.push(step*i)
    };
    var jsonstr = "[{'title':'测试','weight':10},{'title':'我的1111111111112222','weight':80},{'title':'123','weight':70},{'title':'你的','weight':60},{'title':'热点','weight':20},{'title':'123','weight':90},{'title':'话题','weight':30},{'title':'abc','weight':90},{'title':'123','weight':40},{'title':'123','weight':50}]";
    var returndata = eval('('+jsonstr+')');
    console.log(returndata.length)
    for(var i=0;i<10;i++){
        console.log(returndata[i].weight)
        yArray.push(100-returndata[i].weight)
    }
    var baseLine = "M 0 100 R ";
    for(var i=0;i<xArray.length;i++){
        baseLine += xArray[i] + " 100 ";
    }
    baseLine += " " + width + " 100"; 
    base = paper.path(baseLine).attr({stroke:"#22c0c6","stroke-width":4,"opacity":0.7});
    var changeLine = "M 0 100 R ";
    for(var i=0;i<xArray.length;i++){
        changeLine += xArray[i] + " " + yArray[i] + " ";            
    }
    changeLine += " " + width + " 100";
    base.animate({path:changeLine},700,"ease",shadom);
    
    function shadom(){
//          var cloneEle = base.clone().attr({"opacity":0});
//          var cloneEle2 = base.clone().attr({"opacity":0});            
//          cloneEle.attr({"opacity":0.5}).animate({transform:"T 0 15"});
//          cloneEle2.attr({"opacity":0.3}).animate({transform:"T 0 30"})
        var changeLine1 = "M 0 109.5 R ";
        var changeLine2 = "M 0 118 R ";
        for(var i=0;i<xArray.length;i++){
            changeLine1 += xArray[i] + " " + (yArray[i] + 9.5 + Math.random()*1) + " ";
            changeLine2 += xArray[i] + " " + (yArray[i] + 18 + Math.random()*1) + " "; 
        }
        changeLine1 += " " + width + " 109.5";
        changeLine2 += " " + width + " 118";

        change1 = paper.path(changeLine1).attr({stroke:"#22c0c6","stroke-width":3,opacity:0.5});
        change2 = paper.path(changeLine2).attr({stroke:"#22c0c6","stroke-width":2,opacity:0.3});

        for(var i=0;i<xArray.length;i++){
//           paper.image("img/apple.png", xArray[i]-10, yArray[i]-10, 20, 20).attr({"opacity":0}).animate({"opacity":1,r:10},700,"easeInOut").click(function(){
//                alert("aaa")
//            });
        	var textArrayItem = paper.text(xArray[i],yArray[i]+30,returndata[i].title).attr({"fill":'#fff',"font-size":"14",opacity:0}).data("index",i).animate({opacity:1},700,"ease").click(function(e){nodeClick(e,this)});
           
            var rectArrayItem = paper.rect(xArray[i] - 12,yArray[i] - 12,0,0).attr({fill:"#389b9f",opacity:0,transform:"r45",width:24,height:24,"stroke-width":0,r:2,opacity:0}).data("index",i).animate({"opacity":1,transform:"r45"},700,"ease").click(function(e){nodeClick(e,this)});
        	var hotArrayItem = paper.text(xArray[i],yArray[i],returndata[i].weight).attr({"fill":'#fff',"font-size":"16",opacity:0}).data("index",i).animate({opacity:1},700,"ease").click(function(e){nodeClick(e,this)});
        	textArray.push(textArrayItem);
        	rectArray.push(rectArrayItem);
        	hotArray.push(hotArrayItem);
        }
        rectArray.forEach(function(item,index){
        	item.hover(function(){
        		rectArray[index].animate({transform:"r45s1.2"})
        		textArray[index].animate({transform:"s1.2"})
        		hotArray[index].animate({transform:"s1.2"})
        	},function(){
        		rectArray[index].animate({transform:"r45"})
        		textArray[index].animate({transform:"s1"})
        		hotArray[index].animate({transform:"s1"})
        	})
        })
        textArray.forEach(function(item,index){
        	item.hover(function(){
        		rectArray[index].animate({transform:"r45s1.2"})
        		textArray[index].animate({transform:"s1.2"})
        		hotArray[index].animate({transform:"s1.2"})
        	},function(){
        		rectArray[index].animate({transform:"r45"})
        		textArray[index].animate({transform:"s1"})
        		hotArray[index].animate({transform:"s1"})
        	})
        })
        hotArray.forEach(function(item,index){
        	item.hover(function(){
        		rectArray[index].animate({transform:"r45s1.2"});
        		textArray[index].animate({transform:"s1.2"});
        		hotArray[index].animate({transform:"s1.2"});
        	},function(){
        		rectArray[index].animate({transform:"r45"})
        		textArray[index].animate({transform:"s1"})
        		hotArray[index].animate({transform:"s1"})
        	})
        })
//        for(var i=0;i<rectArray.length;i++){
//        	rectArray[i].hover(function(){
//        		textArray[i].animate({"font-size":"18"},700,"ease");
//        		rectArray[i].animate({width:"30",height:"30"},700,"ease");
//        		hotArray[i].animate({"font-size":"16"},700,"ease");
//        	},function(){
//        		textArray[i].animate({"font-size":"16"},700,"ease");
//        		rectArray[i].animate({width:"24",height:"24"},700,"ease");
//        		hotArray[i].animate({"font-size":"14"},700,"ease");
//        	});
//        }
}
};
    loadSvg();
    var setTime;
    window.onresize=function(){
    	clearTimeout(setTime);
    	setTime = setTimeout(function(){    		
    		$("#papersvg").html('');
            loadSvg();
    	},500)
        
    };
    function nodeClick(e,t){
    	e ? e.stopPropagation() : event.cancelBubble = true;
        alertCon.show();
        var index = t.data("index");
        var X = rectArray[index].node.getBoundingClientRect().left + document.documentElement.scrollLeft;
        var Y = rectArray[index].node.getBoundingClientRect().top + document.documentElement.scrollTop;
        var trianglePos = triangleStep * (index + 1);
        $(".triangle").css("left",trianglePos);
        alertCon.css({left:X - trianglePos + 12,top:Y - 144});
    }
    $(document).on("click",".infoConnect",function(){
        // var topicId = $(this).find("span").data("id");
        // var topic = $(this).prev().text();
        // topic = topic.split("#");
        // topic = topic[1]
    	var topic = "热点热点热点";
        var content = "";
        content += '<input class="releateTag" placeholder="请输入关键字" />';
        content += '<div style="display:inline-block;width:52px;height: 40px;background: url(img/link3.png) center center no-repeat;"></div>';
        content += '<div class="selectTag" title='+topic+'>'+topic+'</div>';
        var pop = new Pop({
            width:"422px",
            header:"请补充探索关键字",
            content:content,
            buttons:[{
                type:"popCancle",
                text:"取消"
            },{
                type:"popOk",
                text:"确定",
                callback:function(){
                    var query = $(document).find(".popWin").find(".releateTag").val();
                    var hotTopic = $(document).find(".popWin").find(".selectTag").text();
                    if(query.trim() == ""){                    
                    }else{
                        window.location.href="path?query="+escape(query)+"&topicId="+topicId+"&hotTopic="+escape(topic);
                    }               
                }
            }]
        })
    })
    $(document).on("click",function(){
    	alertCon.hide();
    })
//热点详细信息。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
   $('#allHot').on('click',function(){
	   $('#allHot').addClass('hidecommon');
	   $('#all_hot').removeClass('hidecommon');
	   $('#all_hot').animate({
		   opacity:1
	   },500);
	   $('#ser_section').animate({
		   height:0
	   },500);
	   $('.notify-list').addClass('hidecommon');
	   $('#nav_ser').delay("fast").fadeIn();
   }); 
