//头部。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。



//搜索。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
	//设置为常用
	$('#ser_text').on('input',function(e){
		e ? e.stopPropagation() : event.cancelBubble = true;	
		$('#favorite_set_btn').removeClass('hidecommon');
	});
	$('#ser_text').on('input',function(e){
		e ? e.stopPropagation() : event.cancelBubble = true;	
		$('#cook_ul').removeClass('hidecommon');
	});
	$(document).on('click',function(){
		$('#cook_ul').addClass('hidecommon');
		$('#favorite_set_btn').addClass('hidecommon');
	})

	$('#favorite_set_btn').on('click',function(){
		var val = $.trim($('#ser_text').val());
		var len = $('#favorite_ul li').length;
		if(len>=5){
			$('#favorite_ul').find('li').eq(5).remove();
			$('#favorite_ul').prepend('<li>'+val+'<span></span></li>');
		}else{
			$('#favorite_ul').prepend('<li>'+val+'<span></span></li>');
		};
	});

	$('#favorite_ul').delegate('li span','click',function(){
		$(this).parent().remove();
	});
//曲线。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
var paper;
var base;
var change1;
var change2;
var rectArray = [];
var hotArray = [];
var textArray = [];
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
        	var textArrayItem = paper.text(xArray[i],yArray[i]+30,returndata[i].title).attr({"fill":'#fff',"font-size":"14",opacity:0}).animate({opacity:1},700,"ease").click(function(){alert("111")})
           
            var rectArrayItem = paper.rect(xArray[i] - 12,yArray[i] - 12,0,0).attr({fill:"#389b9f",opacity:0,transform:"r45",width:24,height:24,"stroke-width":0,r:2,opacity:0}).animate({"opacity":1,transform:"r45"},700,"ease");
        	var hotArrayItem = paper.text(xArray[i],yArray[i],returndata[i].weight).attr({"fill":'#fff',"font-size":"16",opacity:0}).animate({opacity:1},700,"ease").click(function(){alert("aaa")});
        	textArrayItem.hover(function(){},function(){this.animate({transform:"s1.2"})})
        	var st = paper.setFinish();
//        	st.forEach(function(item,index){
//        		console.log(item)
//        		if(item.node.nodeName != "rect"){
//        			item.hover(function(){
//        				this.animate({transform:"s1.2"})
//        			},function(){
//        				
//        			})
//        		}else{
//        			console.log(2)
//        		}
//        	})

        	textArray.push(textArrayItem);
        	rectArray.push(rectArrayItem);
        	hotArray.push(hotArrayItem);
//        	rectArrayItem.hover(function(){
//        		textArrayItem.animate({"font-size":"25"},700,"ease");
//        		this.animate({width:"30",height:"30"},700,"ease");
//        		hotArrayItem.animate({"font-size":"16"},700,"ease");
//        	},function(){
//        		textArrayItem.animate({"font-size":"16"},700,"ease");
//        		this.animate({width:"24",height:"24"},700,"ease");
//        		hotArrayItem.animate({"font-size":"14"},700,"ease");
//        	});
        }
        console.log(rectArray)
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
    window.onresize=function(){
        $("#papersvg").html('');
        loadSvg();
    };



//热点详细信息。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。