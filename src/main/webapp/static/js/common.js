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
//            window.location.href = "user/login?returnUrl="+window.location;
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
    var url = location.hash; //获取url中"?"符后的字串 
    var theRequest=null;
    if (url.indexOf("#") != -1) { 
        var str = url.substr(1);
        theRequest=JSON.parse(str);
    }
    return theRequest; 
} 

/**
 * [弹出窗口组件]
 * @author :liuqi
 */

;(function($){
	$.fn.pop = function(opt){
		opt = $.extend({
			bgColor:'#000',
			bgAlpha:.75,
			popId:"pop",
			popWidth:400,
			popHeight:253,
			popCon:'',
			popBg:'',
			close:'',      //关闭按钮文本
			cancel: '',    //取消按钮文本
            confirm: '',   //确认按钮文本
            cancelCallBack: null, //取消按钮回调
            confirmCallback: null //确认按钮回调
            
		},opt||{});
		return this.each(function(){
		    
			var btn = $(this);
			var _width = $(document).width();
			var _height =  907;
			btn.click(function(){
				if($('#popBg').length < 0) {
					return;
				}
				var html = "";
				html += '<div id="popBg" style="position:absolute;width:';
				html += _width + 'px; height:' + _height + 'px; top:0; left:0; background:';
				html += opt.bgColor + '; display:none; z-index:9000"></div><div id="' + opt.popId; 
				html += '" class="pop" style="width:' + opt.popWidth + 'px; height:' + opt.popHeight; 
				html += 'px; margin-top:-' + opt.popHeight/2 + 'px; margin-left:-' + opt.popWidth/2;
				html += 'px; top:50%; left:50%; position:fixed; _position:absolute; display:none;';
				html += 'z-index:9100; background:'+ opt.popBg + '">' + opt.popCon ;
				html += '<div class="popup-btn">';
				if(opt.close){
                    html += '<a href="javascript:;" class="closeAlert" style="position:absolute;right:4px;top:4px;">' + opt.close + '</a>';
                }
                if(opt.cancel){
                    html += '<a href="javascript:;" class="cancel">' + opt.cancel + '</a>';
                }
                if(opt.confirm){
                    html += '<a href="javascript:;" class="confirm">' + opt.confirm + '</a>';
                }
                html += '</div></div>';

				$('body').append(html);
				$('#popBg').fadeTo(500,opt.bgAlpha);
				var popBox = $('.pop');
				popBox.fadeIn();

				//按钮
				closeBtn = popBox.find(".closeAlert");
                cancelBtn = popBox.find(".cancel");
                confirmBtn = popBox.find(".confirm");
                
                //回调函数
                if(opt.close){
					closeBtn.click(function() {
						closePop();
					});
				}
				if(opt.cancel){
					cancelBtn.click(function() {
						if(opt.cancelCallBack && typeof opt.cancelCallBack == 'function'){
							opt.cancelCallBack();
						}
						closePop();
					});
				}
				if(opt.confirm){
					confirmBtn.click(function() {
						if(opt.confirmCallBack && typeof opt.confirmCallBack == 'function'){
							opt.confirmCallBack();
						}
						closePop();
					});
				}
				return false;	
			});

			closePop = function (){
			    
				$('.pop, #popBg').fadeOut();
				
			};
			
		});
	}
})(jQuery)

//
function logOut(){
	$.ajax({
		type:"get",
		url:dataUrl.util.getLogOut(),
		success:function(data){
			console.log("logoutData: " + data);
		}
	   
	})
}
 
  
    


  


  