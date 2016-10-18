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
    getPath(false);
})
var Pages = [];
var pageNum = 0;
var isLessClick = true;
function getPath(explore){
    var localUrl = "http://192.168.1.120:8080/zhicang/";
    var topicId= GetRequest('word').topicId;//GetRequest('dsId').topicId,
    var query = GetRequest('query').query;
    var hotTopic = GetRequest('query').hotTopic;
    if(explore){
        query = explore;
    }
    $.ajax({
        type:"get",
        url:dataUrl.util.getPath(topicId,query),
        success:function(data){
        	
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
            $('#canvas').css('height','500px');
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
           $('#canvas').html('');
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
    var topicId= GetRequest('word').topicId;//GetRequest('dsId').topicId,
    var query = GetRequest('query').query;
    var hotTopic = GetRequest('query').hotTopic;
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
    var paper = Raphael("canvas", '100%', '100%');
//    var lineArray = [8,6,4,5,5];
    var lineArray = lineArray;
    var nodeList = nodeList;
    var theta = "";//角度
    var thetaArray = [];
    var startPoint = [35,250];
    var endPoint = [1000,250];
    var lines = [];
    var cycles = [];
    var linkImg = "";
    var imgHover = false;
    //y轴点
    var hei = parseInt(450/(lineArray.length+1));
    var st = paper.set();//画圆
    //文字超出处理
    var keyWord1 = "";
    var hotTopic1 = "";
    if(keyWord.length > 3){
        keyWord1 = keyWord.slice(0,2);
        keyWord1 += "..."
    }else{
        keyWord1 = keyWord; 
    }
    if(hotTopic.length > 3){
        hotTopic1 = hotTopic.slice(0,2);
        hotTopic1 += "..."
    }else{
        hotTopic1 = hotTopic;
    }
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
        var lineLen = parseInt(endPoint[0]/(lineArray[i]+1));//线长
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
          if(k == 0){
            var str = "M"+startPoint[0]+" "+startPoint[1]+"L"+lineList[k][0]+" "+lineList[k][1];
          }else if(k<lineArray[i]){
            var str = "M"+lineList[k-1][0]+" "+lineList[k-1][1]+"L"+lineList[k][0]+" "+lineList[k][1];
          }else{   
             var str = "M"+lineList[k-1][0]+" "+lineList[k-1][1]+"L"+endPoint[0]+" "+endPoint[1];
          }
          if(k<lineArray[i]){
            var cycle = paper.circle(lineList[k][0],lineList[k][1],7).animate({fill: "#9B9B9B", stroke: "#D8D8D8", "stroke-width": 4}, 200).data("lineNum",i);
            st.push(cycle);
            currentCycle.push(cycle);
            paper.text(lineList[k][0],lineList[k][1]+25,nodeList[i][k]).attr({"font-family":'微软雅黑',"font-size":"12px"});
        }
        var scaleNum = 0;
        switch(lineArray[i]){
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
          var line = paper.path(str).animate({fill:"#9B9B9B",stroke: "#9B9B9B", "stroke-width": 1,cursor:"pointer"}, 200).transform(scaleNum).data("lineNum",i).data("itemNum",k).data("startText",startText).data("endText",endText);
          line.attr("cursor","pointer");
          line.click(function(e){
        	e?e.stopPropagation():event.cancelBubble = true;
            getAlert(e.pageX,e.pageY);
            $(".bottom-choice").show();
            var startText = this.data("startText");
            var endText = this.data("endText");
            $(document).find(".dialog-head div:eq(0)").text(startText);
            $(document).find(".dialog-head div:eq(2)").text(endText);
            getPathInfo(startText,endText);
          });
          line.hover(function(){
            var _this_ = this;
            var lineNum = this.data("lineNum");
            var colorList = new gradientColor('#23A095','#69C668',lineArray[lineNum]+1);
            $.each(lines[lineNum],function(index,item){
                item.animate({stroke: colorList[index],"stroke-width": 4}, 0);
            });
            $.each(cycles[lineNum],function(index,item){
                item.animate({fill: colorList[index],stroke: "#c1e0cd","stroke-width":4,}, 200);
            });
            linkImg = paper.image("img/nodeLink.png",(this.attrs.path[0][1]+this.attrs.path[1][1]-20)/2,(this.attrs.path[0][2]+this.attrs.path[1][2]-20)/2,20,20);
            linkImg.hover(function(){
                imgHover = true;
            },function(){
                var lineNum = _this_.data("lineNum");
                $.each(lines[lineNum],function(index,item){
                    item.animate({stroke: "#9B9B9B","stroke-width": 1}, 200);
                });
                $.each(cycles[lineNum],function(index,item){
                    item.animate({fill: "#9B9B9B", stroke: "#D8D8D8", "stroke-width": 4}, 200);
                });
                imgHover = false;
                linkImg.remove();
            })
            linkImg.click(function(e){
                getAlert(e.pageX,e.pageY);
                $(".bottom-choice").show();
                var startText = _this_.data("startText");
                var endText = _this_.data("endText");
                $(document).find(".dialog-head div:eq(0)").text(startText);
                $(document).find(".dialog-head div:eq(2)").text(endText);
                getPathInfo(startText,endText);
            })
          },function(){
            var _this_ = this;
            setTimeout(function(){
                if(imgHover == false){
                    var lineNum = _this_.data("lineNum");
                    $.each(lines[lineNum],function(index,item){
                        item.animate({stroke: "#9B9B9B","stroke-width": 1},200);
                    });
                    $.each(cycles[lineNum],function(index,item){
                        item.animate({fill: "#9B9B9B", stroke: "#D8D8D8", "stroke-width": 4},200);
                    });
                    linkImg.remove();
                    imgHover = false;
                }
            },10)                    
          });
          currentLine.push(line);
       }
       lines.push(currentLine);
       cycles.push(currentCycle);
       // var c = paper.path(raphaelList[i]);
       
    }
    
    paper.circle(startPoint[0], startPoint[1], 35).animate({fill: "#23A095","stroke-width":0}, 300);
    var textStart = paper.text(startPoint[0],startPoint[1],keyWord1).attr({"font-size":"20px","font-family":'微软雅黑',"fill":"#fff"});
    textStart.attr({"cursor":"pointer","title":keyWord}).data("keyWord",keyWord);
    textStart.hover(function(){
//        alert(this.data("keyWord"));
    },function(){});
    paper.circle(endPoint[0],endPoint[1], 35).animate({fill: "#69C668","stroke-width":0}, 300);
    var textEnd = paper.text(endPoint[0],endPoint[1],hotTopic1).attr({"font-size":"20px","font-family":'微软雅黑',"fill":"#fff"});
    textEnd.attr({"cursor":"pointer","title":hotTopic}).data("hotTopic",hotTopic);
    textEnd.hover(function(){
//        alert(this.data("hotTopic"));
    },function(){});
    
    function getAlert(x,y){
        if(x-130<endPoint[0]/2){
          $(".path-dialog").css({"position":"absolute","left":x+10,"top":y});
          $(".path-dialog").show();
        }else{
           $(".path-dialog").css({"position":"absolute","left":x-340,"top":y});
           $(".path-dialog").show();
        }
     }
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
    if($(this).hasClass("disstate")){
        return;
    }
    pageChange(false);
})
$("#prev-next").click(function(){
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