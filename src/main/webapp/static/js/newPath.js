$('#search-btn').click(function(){
	//$('#load-con').removeClass('hidecommon');
	pathSer();
});
$('#search-input').keyup(function(event){
	if (event.keyCode == "13") {
		pathSer();
	};
});
function pathSer(){
	var word = $.trim($('#search-input').val());
	if(!word){
		return;
	}
	$('#search-btn').attr("disabled","disabled");
	$('.choice-keyword').text(word);
	getPath(word);
};
$(document).ready(function(){
	var a = location.search;
	if(a){		
	}else{
		$(".explorePage").css("display","inline");
	}
    getPath(false);
})
var Pages = [];
var pageNum = 0;
var isLessClick = true;
var timeOut;
var circleTimeout;
var isShow = false;
var prevNum = 0;
function getPath(explore){	
	var hash = decodeURIComponent(location.hash);
	hash = hash.substr(1)
	var pathInfo = {};
	var infoArray = hash.split("&");
	$.each(infoArray,function(index,item){
		var temp = item.split("=");
		pathInfo[temp[0]] = temp[1];
	})
//    var topicId= GetRequest('word').topicId;//GetRequest('dsId').topicId,
//    var query = GetRequest('query').query;
//    var hotTopic = GetRequest('query').hotTopic;
    var topicId = pathInfo.topicId;
    var query = pathInfo.query;
    var hotTopic = pathInfo.hotTopic;
    if(explore){
        query = explore;
    }
    $.ajax({
        type:"get",
        url:dataUrl.util.getNewPath(topicId,query),
        success:function(data){
        	$("#prev-next").removeClass("disstate");
            if(data.data.length == 0){
            	$('#canvas').css('height','200px');
                $('#canvas').html('<div style="margin:0px auto ;width:500px;text-align:center;font-size:17px;padding-top:176px;">对不起,没有找到合适的语义路径.请更换关键词或者热点</div>');
                return;
            }
            if(data.error.code == 2004){
                console.log(data.error.message);
                //$('#canvas').html('');
                $('#canvas').css('height','200px');
                $('#canvas').html('对不起,没有找到合适的语义路径.请换个关键词或者热点');
                $('#canvas').html('<div style="margin:0px auto;width:500px;text-align:center;font-size:17px;padding-top:176px;">对不起,没有找到合适的语义路径.请更换关键词或者热点</div>');
                return;
            }else if(data.data == null){
                console.log("数据为空");
                return;
            };
//            $('#canvas').css('height','500px');
            var dataStr = JSON.stringify(data.data);
            Pages = JSON.parse(dataStr);
            if(Pages.length <= 4){
                isLessClick = false;
            }else{
                isLessClick = true;
            }
            var lineArray = [];
            var nodeList = [];
            var List = data.data;
            if(List.length>5){
            	List.length = 5;
            }else{
            	$('#prev-next').addClass('disstate');
            }
            
            $.each(List,function(index,item){
                var tempNode = [];
                var num = Object.keys(item.nodes).length-1;
                if(num>=0){
                    lineArray.push(num)
                }
                $.each(item.nodes,function(index1,item1){
                    if(index1 == 0){                        
                    }else{
                        tempNode.push(item1.name);  
                    }                    
                })
                nodeList.push(tempNode);
            });
           $('#load-con').addClass('hidecommon');
            raphealDraw(lineArray,nodeList,query,hotTopic); 
            $('#search-btn').removeAttr("disabled"); 
        },
        error:function(){
    
        }
});
}
function pageChange(up){
    if(!isLessClick){
        return;
    }
    if(up){
//        console.log("pageNum"+pageNum);
        if(pageNum == Math.floor(Pages.length/5)-1){
            $("#prev-next").addClass("disstate");
        }
        if(pageNum >= Math.floor(Pages.length/5)){
            $("#prev-path").removeClass("disstate");
            $("#prev-next").addClass("disstate");
            return;
        }else{
            $("#prev-path").removeClass("disstate");
            pageNum += 1;
        }
        
    }else{
        if(pageNum == 1){
            $("#prev-path").addClass("disstate");
        }
        if(pageNum>0){
            pageNum -= 1;
            $("#prev-next").removeClass("disstate");
        }else{
            pageNum = 0; 
            $("#prev-path").addClass("disstate");
            $("#prev-next").removeClass("disstate");
            return;
        }
    }
    var lineArray = [];
    var nodeList = [];
//    var topicId= GetRequest('word').topicId;//GetRequest('dsId').topicId,
//    var query = GetRequest('query').query;
//    var hotTopic = GetRequest('query').hotTopic;
    var hash = decodeURIComponent(location.hash);
	hash = hash.substr(1)
	var pathInfo = {};
	var infoArray = hash.split("&");
	$.each(infoArray,function(index,item){
		var temp = item.split("=");
		pathInfo[temp[0]] = temp[1];
	})
    var topicId = pathInfo.topicId;
    var query = pathInfo.query;
    var hotTopic = pathInfo.hotTopic;
    var List = [];
    for(var i=0;i<5;i++){
        var ListItem = Pages[pageNum*5+i];
        if(ListItem){
            List.push(ListItem);
        }
    }
    $.each(List,function(index,item){
        var tempNode = [];
        var num = Object.keys(item.nodes).length-1;
        if(num>0){
            lineArray.push(num)
        }
        $.each(item.nodes,function(index1,item1){
            if(index1 == 0){
            }else{
                tempNode.push(item1.name);  
            } 
        })
        nodeList.push(tempNode);
    });
   $('#load-con').addClass('hidecommon');   
   if(lineArray.length == 0){
       return;
   }
   $('#canvas').html('');
    raphealDraw(lineArray,nodeList,query,hotTopic); 
}
function raphealDraw(lineArray,nodeList,keyWord,hotTopic){
    $('#canvas').html('');
	var height = $("#canvas").height();
	var width = $("#canvas").width();
	var canvasRect = $("#canvas").get(0).getBoundingClientRect();
	height = canvasRect.bottom - canvasRect.top;
//	alert(height)
	var bodyHeight = $("body").height();
	var leftRightSpace = height*0.2 + 35;
    var paper = Raphael("canvas", '100%', '100%');
//    var lineArray = [8,6,4,5,5];
    var lineArray = lineArray;
    var nodeList = nodeList;
    var theta = "";//角度
    var thetaArray = [];
    var startPoint = [leftRightSpace,height/2];
    var endPoint = [width-leftRightSpace,height/2];
    var lines = [];
    var cycles = [];
    var linkImg = "";
    //y轴点
    var hei = parseInt((height-25)/(lineArray.length+1));
    var st = paper.set();//画圆
    //文字超出处理
//    var keyWord1 = "";
//    var hotTopic1 = "";
//    if(keyWord.length > 3){
//        keyWord1 = keyWord.slice(0,2);
//        keyWord1 += "..."
//    }else{
//        keyWord1 = keyWord; 
//    }
//    if(hotTopic.length > 3){
//        hotTopic1 = hotTopic.slice(0,2);
//        hotTopic1 += "..."
//    }else{
//        hotTopic1 = hotTopic;
//    }
    if(lineArray.length == 0){
        var str = "M"+startPoint[0]+" "+startPoint[1]+"L"+endPoint[0]+" "+endPoint[1];
        var line0 = paper.path(str).animate({fill:"#9B9B9B",stroke: "#9B9B9B", "stroke-width": 1,cursor:"pointer"}, 200);
        line0.attr({"title":"对不起，没有找到合适的语义路径"});
        paper.circle(startPoint[0], startPoint[1], 35).animate({fill: "#23A095","stroke-width":0}, 300);
        var textStart = paper.text(startPoint[0],startPoint[1],keyWord1).attr({"font-size":"20px","font-family":'微软雅黑',"fill":"#fff"});
        textStart.attr({"cursor":"pointer","title":keyWord}).data("keyWord",keyWord);
        
        paper.circle(endPoint[0],endPoint[1], 35).animate({fill: "#69C668","stroke-width":0}, 300);
        var textEnd = paper.text(endPoint[0],endPoint[1],hotTopic1).attr({"font-size":"20px","font-family":'微软雅黑',"fill":"#fff"});
        textEnd.attr({"cursor":"pointer","title":hotTopic}).data("hotTopic",hotTopic);
        
        return;
    }
    for(var i=0,j=lineArray.length;i<j;i++){
        var lineLen = parseInt(endPoint[0]/(lineArray[i]+1.5));//线长
        var lineList = [];
        for(var a=0,b=lineArray[i];a<b;a++){
            lineList.push([startPoint[0]+lineLen*(a+1),hei*(i+1)]);
        }
        
        for(var a =0,b=lineList.length;a<b;a++){
            var x=0,
            y = 0;
            if(a == 0){
                Math.random()>1?lineList[a][0] += Math.random()*50:lineList[a][0] += +30*Math.random();
                Math.random()>1?lineList[a][1] += Math.random()*50:lineList[a][1] += +30*Math.random(); 
            }else if(i == j-1){
                Math.random()>1?lineList[a][0] += Math.random()*50:lineList[a][0] += -30*Math.random();
                Math.random()>1?lineList[a][1] += Math.random()*50:lineList[a][1] += 40*Math.random(); 
            }else{
                Math.random()>1?lineList[a][0] += Math.random()*50:lineList[a][0] += -30*Math.random();
                Math.random()>1?lineList[a][1] += Math.random()*50:lineList[a][1] += -30*Math.random(); 
            }
            
       }
       var currentLine = [];
       var currentCycle = [];
        for(var k=0;k<lineArray[i]+1;k++){
            if(lineArray[i] == 0){
                var str = "M"+startPoint[0]+" "+startPoint[1]+"L"+endPoint[0]+" "+endPoint[1];
            }else{
                if(k == 0){
                    var str = "M"+startPoint[0]+" "+startPoint[1]+"L"+lineList[k][0]+" "+lineList[k][1];
                }else if(k<lineArray[i]){
                    var str = "M"+lineList[k-1][0]+" "+lineList[k-1][1]+"L"+lineList[k][0]+" "+lineList[k][1];
                }else{
                    var str = "M"+lineList[k-1][0]+" "+lineList[k-1][1]+"L"+endPoint[0]+" "+endPoint[1];
                }
            }

          if(k<lineArray[i]){
            var cycle = paper.circle(lineList[k][0],lineList[k][1],7).attr({cursor:"pointer"}).animate({fill: "#9B9B9B", stroke: "#D8D8D8", "stroke-width": 4}, 200).data("lineNum",i);
            st.push(cycle);
            currentCycle.push(cycle);
            paper.text(lineList[k][0],lineList[k][1]+20,nodeList[i][k]).attr({"font-family":'微软雅黑',"font-size":"12px"});
            cycle.click(function(e){
              e?e.stopPropagation():event.cancelBubble = true;
          	  var lineNum = this.data("lineNum");
          	  $(".pathName").click();
            })
           //节点hover高亮
            cycle.hover(function(e){
                var _this_ = this;
                var lineNum = this.data("lineNum");
                	hideHightLight(prevNum,lines,cycles);
                	clearTimeout(circleTimeout);
                	clearTimeout(timeOut);
                    isShow = true;
                    prevNum = lineNum;
//                    setTimeout(function(){
                    getAlert(e.pageX,e.pageY - 5);
                    showHightLight(e,lineNum,lines,cycles,lineArray);
//                    },0)
                var str = '<li class="fl" style="color:#000">当前路径&nbsp;:&nbsp;</li>';
            	str += '<li class="fl mr5 choice-keyword"><span></span>'+ keyWord +'</li>'; 
            	$.each(lines[lineNum],function(index,item){
            		str += '<li class="fl libordercenter mr5"></li>';
            		str += '<li class="fl mr5 choice-keyword"><span></span>'+item.data("endText")+'</li>';        		
            	})
            	
//            	str += '<li class="fr computer-ok pointer">保存路径</li>';
            	$(".pathName").attr("data-str",str);
            	$(".pathNum").html(pageNum*5+lineNum+1);
              },function(){
                var _this_ = this;
                isShow = false;
                var lineNum = _this_.data("lineNum");
                circleTimeout = setTimeout(function(){
                	hideHightLight(prevNum,lines,cycles);
                },5000)
              });
        }          
        var scaleNum = 0;
        switch(lineArray[i]){
            case 0:scaleNum = "s0.97";break;
            case 1: scaleNum = "s0.96";break;
            case 2: scaleNum = "s0.93";break;
            case 3: scaleNum = "s0.89";break;
            case 4: scaleNum = "s0.85";break;
            case 5: scaleNum = "s0.82";break;
            case 6: scaleNum = "s0.79";break;
            case 7: scaleNum = "s0.75";break;
            default: scaleNum = "s0.72";break;
        }
          var startText = "";
          var endText = "";
          if(k == 0){
              startText = keyWord;
              endText = endText = nodeList[i][k];
          }else if(k == lineArray[i]){
              startText = endText = nodeList[i][k-1];
              endText = hotTopic;
          }else{
              startText = nodeList[i][k-1];
              endText = nodeList[i][k]
          }
            if(lineArray[i] == 0){
                endText = hotTopic;
            }
          var line = paper.path(str).animate({fill:"#9B9B9B",stroke: "#9B9B9B", "stroke-width": 1,cursor:"pointer"}, 200).transform(scaleNum).data("lineNum",i).data("itemNum",k).data("startText",startText).data("endText",endText);
          line.attr("cursor","pointer");
          line.click(function(e){
        	e?e.stopPropagation():event.cancelBubble = true;
        	var lineNum = this.data("lineNum");        	
//        	$(".bottom-choice").html();
//        	console.log(lineNum);
//            getAlert(e.pageX,e.pageY);
        	$(".bottom-choice").html($(".pathName").attr("data-str"));
            $(".bottom-choice").show();
//            var startText = this.data("startText");
//            var endText = this.data("endText");
//            $(document).find(".dialog-head div:eq(0)").text(startText);
//            $(document).find(".dialog-head div:eq(2)").text(endText);
//            getPathInfo(startText,endText);
          });
          line.hover(function(e){
            var _this_ = this;            
            var lineNum = this.data("lineNum");
//            if(prevNum == lineNum && isShow){
////            	getAlert(e.pageX,e.pageY);
//            	showHightLight(e,lineNum,lines,cycles,lineArray);
//            	clearTimeout(timeOut);
//            }else{
            	hideHightLight(prevNum,lines,cycles);
            	clearTimeout(timeOut);
            	clearTimeout(circleTimeout);
                isShow = true;
                prevNum = lineNum;
//                setTimeout(function(){
                getAlert(e.pageX,e.pageY);
                showHightLight(e,lineNum,lines,cycles,lineArray);
//                },0)                
//            }
                var str = '<li class="fl" style="color:#000">当前路径&nbsp;:&nbsp;</li>';
            	str += '<li class="fl mr5 choice-keyword"><span></span>'+ keyWord +'</li>'; 
            	$.each(lines[lineNum],function(index,item){
            		str += '<li class="fl libordercenter mr5"></li>';
            		str += '<li class="fl mr5 choice-keyword"><span></span>'+item.data("endText")+'</li>';        		
            	})
//            	str += '<li class="fr computer-ok pointer">保存路径</li>';
            	$(".pathName").attr("data-str",str);
            	$(".pathNum").html(pageNum*5+lineNum+1);
          },function(){
            var _this_ = this;
            isShow = false;
            var lineNum = _this_.data("lineNum");
            timeOut = setTimeout(function(){
            	hideHightLight(prevNum,lines,cycles);
//                $.each(lines[lineNum],function(index,item){
//                    item.animate({stroke: "#9B9B9B","stroke-width": 1},200);
//                });
//                $.each(cycles[lineNum],function(index,item){
//                    item.animate({fill: "#9B9B9B", stroke: "#D8D8D8", "stroke-width": 4},200);
//                });
//                $(".pathName").hide();
            },5000)
          });
          currentLine.push(line);
       }
       lines.push(currentLine);
       cycles.push(currentCycle);
       
    }
    
    var startCircle = paper.circle(startPoint[0], startPoint[1], 25).animate({fill: "#23A095","stroke-width":0}, 300);
    var imageStart = paper.image("img/keyWord.png",startPoint[0] - 11,startPoint[1] - 11,22,22).attr({title:keyWord});
    var textStart = paper.text(startPoint[0],startPoint[1]  + 43,keyWord).attr({"font-size":"20px","font-family":'微软雅黑',"fill":"#000"}).attr({title:keyWord});
    var editKeyWord;
    textStart.attr({title:keyWord}).data("keyWord",keyWord);
    startCircle.hover(function(){
    	editKeyWord = paper.image("img/editKeyWord.png",startPoint[0] - 25,startPoint[1] - 25,50,50);
    	editKeyWord.hover(function(){},function(){
    		this.remove()
    	})
    	editKeyWord.click(function(){
        	var content = $("<input type='text' class='txt-word' placeholder='请输入关键词' value="+keyWord+">");
        	var pop = new Pop({
    	        width:"422px",
    	        header:"编辑关键词",
    	        content:content,
    	        buttons:[{
    	            type:"popCancle",
    	            text:"取消"
    	        },{
    	            type:"popOk",
    	            text:"确定",
    	            callback:function(){
//    	                $('#nav_ser').val($('.txt-word').val());
    	            	
//    	                $('#nav_ser').val($('.txt-word').val());
    	            	var hash = decodeURIComponent(location.hash);
    	            	hash = hash.substr(1)
    	            	var pathInfo = {};
    	            	var infoArray = hash.split("&");
    	            	$.each(infoArray,function(index,item){
    	            		var temp = item.split("=");
    	            		pathInfo[temp[0]] = temp[1];
    	            	})
//    	            	var word = $('.txt-word').val();    	            	
//    	            	 $(".bottom-choice").hide();
//    	            	 pageNum = 0;
//    	            	 var tempHash = decodeURIComponent(location.hash);
//    	            	 location.hash = tempHash.replace(/keyWord/,word);
    	            	
    	            	var word = $('.txt-word').val();    	            	
    	            	 $(".bottom-choice").hide();
    	            	 pageNum = 0;
    	            	 var tempHash = decodeURIComponent(location.hash);
    	            	 pathInfo.query = word;
    	            	 location.hash = "query="+pathInfo.query+"&topicId="+pathInfo.topicId+"&hotTopic="+pathInfo.hotTopic;
    	            	 getPath();
    	            	 $("#prev-path").addClass("disstate");
    	            	 $("#prev-next").addClass("disstate");
    	                 $(".popMask").remove();
    	            }
    	        }]
    	    });
        	$(".txt-word").focus();
        })
    },function(){
    });
    if(editKeyWord){
    	editKeyWord.click(function(){
        	var content = $("<input type='text' class='txt-word' placeholder='请输入关键词' value="+111+">");
        	var pop = new Pop({
    	        width:"422px",
    	        header:"编辑关键词",
    	        content:content,
    	        buttons:[{
    	            type:"popCancle",
    	            text:"取消"
    	        },{
    	            type:"popOk",
    	            text:"确定",
    	            callback:function(){
    	                $('#nav_ser').val($('.txt-word').val());
    	                $(".popMask").hide();
    	            }
    	        }]
    	    });
        })
    }    
    var endCircle = paper.circle(endPoint[0],endPoint[1], 25).animate({fill: "#69C668","stroke-width":0}, 300);
    var imageEnd = paper.image("img/hotspot.png",endPoint[0] - 10,endPoint[1] - 12,20,27).attr({title:hotTopic});
    var textEnd = paper.text(endPoint[0],endPoint[1] + 43,hotTopic).attr({"font-size":"20px","font-family":'微软雅黑',"fill":"#000"}).attr({title:hotTopic});
    textEnd.attr({title:hotTopic}).data("hotTopic",hotTopic);
    textEnd.hover(function(){
    },function(){});    
}
function getAlert(x,y){
    $(".pathName").css({"position":"absolute","left":x - 30,"top":y - 33});
    $(".pathName").show();
}
$(".pathName").on("click",function(e){
	e?e.stopPropagation():event.cancelBubble = true;
	$(".bottom-choice").html($(this).attr("data-str"));
    $(".bottom-choice").show();
//    getPathInfo(startText,endText);
  });
function showHightLight(e,lineNum,lines,cycles,lineArray){	
	var colorList = new gradientColor('#23A095','#69C668',lineArray[lineNum]+1);    
    $.each(cycles[lineNum],function(index,item){
        item.attr({fill: colorList[index],stroke: "#c1e0cd","stroke-width":4,});
    });
    $.each(lines[lineNum],function(index,item){
        item.attr({stroke: colorList[index],"stroke-width": 4});
    });
}
function hideHightLight(lineNum,lines,cycles){
    $.each(lines[lineNum],function(index,item){
        item.attr({stroke: "#9B9B9B","stroke-width": 1});
    });
    $.each(cycles[lineNum],function(index,item){
        item.attr({fill: "#9B9B9B", stroke: "#D8D8D8", "stroke-width": 4});
    });
    $(".pathName").hide();
}

function gradientColor(startColor,endColor,step){
   startRGB = this.colorRgb(startColor);//转换为rgb数组模式
   startR = startRGB[0];
   startG = startRGB[1];
   startB = startRGB[2];

   endRGB = this.colorRgb(endColor);
   endR = endRGB[0];
   endG = endRGB[1];
   endB = endRGB[2];

   sR = (endR-startR)/step;//总差值
   sG = (endG-startG)/step;
   sB = (endB-startB)/step;

   var colorArr = [];
   for(var i=0;i<step;i++){
   //计算每一步的hex值 
       var hex = this.colorHex('rgb('+parseInt((sR*i+startR))+','+parseInt((sG*i+startG))+','+parseInt((sB*i+startB))+')');
       colorArr.push(hex);
   }
   return colorArr;
}

// 将hex表示方式转换为rgb表示方式(这里返回rgb数组模式)
gradientColor.prototype.colorRgb = function(sColor){
   var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;
   var sColor = sColor.toLowerCase();
   if(sColor && reg.test(sColor)){
       if(sColor.length === 4){
           var sColorNew = "#";
           for(var i=1; i<4; i+=1){
               sColorNew += sColor.slice(i,i+1).concat(sColor.slice(i,i+1));
           }
           sColor = sColorNew;
       }
       //处理六位的颜色值
       var sColorChange = [];
       for(var i=1; i<7; i+=2){
           sColorChange.push(parseInt("0x"+sColor.slice(i,i+2)));
       }
       return sColorChange;
   }else{
       return sColor;
   }
};

// 将rgb表示方式转换为hex表示方式
gradientColor.prototype.colorHex = function(rgb){
   var _this = rgb;
   var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;
   if(/^(rgb|RGB)/.test(_this)){
       var aColor = _this.replace(/(?:(|)|rgb|RGB)*/g,"").split(",");
       var strHex = "#";
       for(var i=0; i<aColor.length; i++){
           var hex = Number(aColor[i]).toString(16);
           hex = hex<10 ? 0+''+hex :hex;// 保证每个rgb的值为2位
           if(hex === "0"){
               hex += hex;
           }
           strHex += hex;
       }
       if(strHex.length !== 7){
           strHex = _this;
       }
       return strHex;
   }else if(reg.test(_this)){
       var aNum = _this.replace(/#/,"").split("");
       if(aNum.length === 6){
           return _this;
       }else if(aNum.length === 3){
           var numHex = "#";
           for(var i=0; i<aNum.length; i+=1){
               numHex += (aNum[i]+aNum[i]);
           }
           return numHex;
       }
   }else{
       return _this;
   }
}   

$(".path-close").click(function(){
   $(this).parents(".path-dialog").hide(); 
});
$(".pnl-info .view").click(function(){
   if($(this).find("a").text()==="收起"){
       $(this).css("background-image","url(img/path-down.png)")
       .find("a").text("预览")
       .parents(".head").next(".content").css("display","none");
   }else{
       $(".pnl-info .view").css("background-image","url(img/path-down.png)")
       .find("a").text("预览");
       $(".pnl-info .content").css("display","none");
       $(this).css("background-image","url(img/path-up.png)")
       .find("a").text("收起")
       .parents(".head").next(".content").css("display","block");
   }
});
$(document).click(function(e){
	$(".bottom-choice").hide();
    if($(".path-dialog").css("display") != "none" && e.target.nodeName != "path" && e.target.nodeName != "circle" && e.target.nodeName != "image"){
    $(".path-dialog").css("display","none")
    }
});
$('.path-dialog,.bottom-choice').on('click',function(e){
	e?e.stopPropagation():event.cancelBubble = true;
});
$('.choice-keyword').text(GetRequest('query').query);
$('.choice-hot').text(GetRequest('query').hotTopic);
$("#prev-path").click(function(){
	$(".pathName").hide();
    if($(this).hasClass("disstate")){
        return;
    }
    pageChange(false);
})
$("#prev-next").click(function(){
	$(".pathName").hide();
    if($(this).hasClass("disstate")){
        return;
    }
    pageChange(true);
})
function getPathInfo(startText,endText){
    $.ajax({
        type:"get",
        url:dataUrl.util.getPathInfo(startText,endText),
        success:function(data){
            console.log(data.data.weiboItemModels);
            var weiBoLen = data.data.weiboItemModels.length;            
            $(".bottom-choice").show();
            $(".path-dialog").find(".count:eq(1)").html(120);
            var weiboContext = "";
            if(weiBoLen > 0){
                weiboContext = data.data.weiboItemModels[0].weiboContent;
                var regExp = new RegExp(startText,'gi');
                weiboContext = weiboContext.replace(regExp, "<span class='color2'>"+startText+"</span>");
                var regExp2 = new RegExp(endText,'gi');
                weiboContext = weiboContext.replace(regExp2, "<span class='color2'>"+endText+"</span>");                
            }
            $(".path-dialog").find(".content-body:eq(0)").html(weiboContext);
        },
        error:function(){
            
        }
    });
}
$("#nav-head-search").on("click",function(){
	var val = $.trim($('#nav_ser').val());
	if(val){
    	if(val.match(/\d+/g)||val.search(/[a-zA-Z]+/)!==-1||/[\u4E00-\u9FA5]/g.test(val)){
    		window.location.href='hotresult?clueWord='+escape(val)+'&pageSize=20&currentPage=1';
    	}else{
    		return;
    	}
	}
})
$('#nav_ser').keyup(function(event) {//搜索框回车
	var val = $.trim($('#nav_ser').val());
    if(event.keyCode == "13") {
    	if(val){
	    	if(val.match(/\d+/g)||val.search(/[a-zA-Z]+/)!==-1||/[\u4E00-\u9FA5]/g.test(val)){
	    		window.location.href='hotresult?clueWord='+escape(val)+'&pageSize=20&currentPage=1';
	    	}else{
	    		return;
	    	}
    	}
    }
})
var setTime;
window.onresize = function(){
	clearTimeout(setTime);
	setTime = setTimeout(function(){
		getPath(false);
	},500)
	
}
window.addEventListener('hashchange', function(e) {
    getPath(false);
}, false);