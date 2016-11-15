if (!$.support.leadingWhitespace) {
         alert("浏览器版本太低，请下载chrome或者IE10以上版本浏览器")
}
function GetQueryString(name) {
    if(cardId){
        return cardId;
    }else{
        var reg = new RegExp("(^|&)" + "cardId" + "=([^&]*)(&|$)", "i");  
        var r = window.location.search.substr(1).match(reg);  //获取url中"?"符后的字符串并正则匹配
        var context = "";  
        if (r != null)  
             context = r[2];  
        reg = null;  
        r = null;
//        console.log(window.location.href.indexOf("edit"));
//        if(window.location.href.indexOf("edit")){
//            cardId = context;
//            return context;
//        }else{
            $.ajax({
                dataType: "json",
                url: "api/cards/"+context+"/edit",
                type: "get",
                contentType: 'application/json',
                success: function(data) {
                    var response = data;
                    if(response.code == -1){
                        //alert(response.msg);
                        return ;
                    }
                    cardId = response.data.newCardId;
                    console.log(1+"d")
                    return cardId;
//                    window.localStorage.setItem("cardId", response.data.newCardId);            
                },
                error: function(xhr) {
                   
                }
            })  
//        }
        
    }
//	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");  
//	    var r = window.location.search.substr(1).match(reg);  //获取url中"?"符后的字符串并正则匹配
//	    var context = "";  
//	    if (r != null)  
//	         context = r[2];  
//	    reg = null;  
//	    r = null;  
//	    if(context && window.localStorage.getItem("card"+context)){
//	        return window.localStorage.getItem("card"+context);
//	    }else if(context){
//	        $.ajax({
//	            dataType: "json",
//	            url: "api/cards/"+context+"/edit",
//	            type: "get",
//	            contentType: 'application/json',
//	            success: function(data) {
//	                var response = data;
//	                if(response.error.code != 0){
//	                    console.log("获取新的卡片ID失败")
//	                }else{
//	                    window.localStorage.setItem("card"+context, response.data.newCardId);
//	                    return data.data.newCardId;
//	                }
//	            },
//	            error: function(xhr) {
//	                console.log('get Color failed');
//	            }
//	        })
//	    }
//	    return context == null || context == "" || context == "undefined" ? "" : context;  
//        return cardId;
//        if(window.localStorage.getItem("cardId")){
//            return window.localStorage.getItem("cardId")
//        }
}
	

//事件冒泡兼容
function stopPropagation(e) {
	if (e.stopPropagation) 
	e.stopPropagation(); 
	else 
	e.cancelBubble = true; 
} ;

//未授权401及errorCode4005
var alertStatus = true;
$(document).ajaxError(function(event,xhr,options,exc){
    if(alertStatus){
        var code = xhr.responseText;
        code  = JSON.parse(code);
        if(code.error.code == 4005){
            console.log(code.error.msg);
            window.location.href = "user/login?returnUrl="+window.location; 
            alertStatus = false;
            return
        }else if(code.error.code != 0){
            console.log(code.error.msg);
            alertStatus = false;
        } 
    }else{
        return ;
    }	
})
$(document).ajaxSuccess(function(event,xhr,options,exc){
    var code = xhr.responseText;
    code  = JSON.parse(code);
    if(code.error){
       if(code.error.code != 0){
           console.log(code.error.msg)
       }
    }    
});


//回到顶部

$(function(){
        //当滚动条的位置处于距顶部0像素以下时，跳转链接出现，否则消失
        $(function () {
            $(window).scroll(function(){
                if ($(window).scrollTop()>0){
                    $(".scrollbartop").fadeIn(500);
                }
                else
                {
                    $(".scrollbartop").fadeOut(500);
                }
            });

            //当点击跳转链接后，回到页面顶部位置

            $(".scrollbartop").click(function(){
               
                $('body,html').animate({scrollTop:0},1000);
                return false;
            });
        });
});

//获取url中"?"符后的字符串 
function GetRequest() { 
	var url = location.search; //获取url中"?"符后的字串 
	var theRequest = new Object(); 
	if (url.indexOf("?") != -1) { 
	var str = url.substr(1); 
	strs = str.split("&"); 
	for(var i = 0; i < strs.length; i ++) { 
	theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
	} 
	} 
	return theRequest; 
} 
//获取url中"#"符后的字符串
function GetRequestLabel() { 
    var url = decodeURIComponent(location.hash);
    var theRequest=null;
    if (url.indexOf("#") != -1) { 
        var str = url.substr(1);
        theRequest=JSON.parse(str);
    }
    console.log(theRequest);
    return theRequest; 
} 



//退出登陆
function logOut(){
	$.ajax({
		type:"get",
		url:dataUrl.util.getLogOut(),
		success:function(data){
			window.location.href="user/login";
		}
	   
	})
}
 
  
    


  


  