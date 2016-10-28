//头部。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。



//搜索。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。




//曲线。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
function loadSvg(){
        var width = $("#papersvg").css("width");
    width = width.split("px")[0]-5;
    var paper = Raphael("papersvg",width,200);
    //paper.clear()
    var xArray = [];
    var yArray = [];
    var step = parseInt(width/11);
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
    var base = paper.path(baseLine).attr({"stroke-width":4,"opacity":0.7});
    var changeLine = "M 0 100 R ";
    for(var i=0;i<xArray.length;i++){
        changeLine += xArray[i] + " " + yArray[i] + " ";            
    }
    changeLine += " " + width + " 100";
    base.animate({path:changeLine},1000,"ease",shadom);
    
    function shadom(){
//          var cloneEle = base.clone().attr({"opacity":0});
//          var cloneEle2 = base.clone().attr({"opacity":0});            
//          cloneEle.attr({"opacity":0.5}).animate({transform:"T 0 15"});
//          cloneEle2.attr({"opacity":0.3}).animate({transform:"T 0 30"})
        var changeLine1 = "M 0 100 R ";
        var changeLine2 = "M 0 100 R ";
        for(var i=0;i<xArray.length;i++){
            changeLine1 += xArray[i] + " " + (yArray[i] + Math.random()*5) + " ";
            changeLine2 += xArray[i] + " " + (yArray[i] + Math.random()*5) + " "; 
        }
        changeLine1 += " " + width + " 100";
        changeLine2 += " " + width + " 100";

        var change1 = paper.path(changeLine1).attr({"stroke-width":4,opacity:0.5}).animate({transform:"T 0 15"});
        var change2 = paper.path(changeLine2).attr({"stroke-width":4,opacity:0.3}).animate({transform:"T 0 30"});

        for(var i=0;i<xArray.length;i++){
//           paper.image("img/apple.png", xArray[i]-10, yArray[i]-10, 20, 20).attr({"opacity":0}).animate({"opacity":1,r:10},700,"easeInOut").click(function(){
//                alert("aaa")
//            });
           paper.text(xArray[i],yArray[i]+20,returndata[i].title).attr({"fill":'red'}).animate({transform:50}).click(function(){alert("111")})
           
           paper.rect(xArray[i]-5,yArray[i]-7,0,0).attr({"fill":"#fff","opacity":0,transform:"r45"}).animate({"opacity":1,width:10,height:10},700,"elastic");
           paper.text(xArray[i]-5,yArray[i],returndata[i].weight).attr({"fill":'#f0f'}).animate({transform:50}).click(function(){alert("aaa")});
        }
}
};
    loadSvg();
    window.onresize=function(){
        $("#papersvg").html('');
        loadSvg();
    };



//热点详细信息。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。