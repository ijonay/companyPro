$('.hotspotsNav').attr("disabled",true);   
$('.hotspotsNav').removeAttr('href'); 
//搜索
//设置为常用 
if($.cookie('the_cookie_2')){
	var string2 = $.cookie('the_cookie_2').split('|');
	//console.log(string2);
$(string2).each(function(i,item){
	//console.log(item)
	$('#favorite_ul').append('<li>'+item+'</li>');
});

}
if(string2){
	var arr2 = string2;	
}else{
	var arr2 = [];
};
//历史记录
if($.cookie('the_cookie_1')){
	var string1 = $.cookie('the_cookie_1').split('|');
	//console.log(string1);
	$(string1).each(function(i,item){
	//console.log(item)
	$('#cook_ul').append('<li>'+item+'</li>');
	});
		var arr1 = string1;	
	}else{
		var arr1 = [];
	};
//历史或者常用点击事件	
$('#cook_ul,#favorite_ul').delegate('li','click',function(){
	var val = $(this).text();
	$('#search-input').val(val);
});
	
$('#search-input').on('focus',function(){
	$('#search-hint').addClass('hidecommon');
	var len1 = $('#cook_ul li').length,
		len2 = $('#favorite_ul li').length;
	if(len1==0&&len2==0){
		$('#dialog-alert').removeClass('hidecommon');
		$('#showlabel').removeClass('hidecommon');
	}else{
		$('#showlabel').addClass('hidecommon');
		$('#dialog-alert').removeClass('hidecommon');
	}
	
});
$('#search-input').on('blur',function(){
	setTimeout(function () { 
		$('#dialog-alert').addClass('hidecommon');
    }, 200);
	//$('#dialog-alert').addClass('hidecommon');
});
$('#search-input').on('input',function(){
	//$('#dialog-alert').addClass('hidecommon');
	var val = $.trim($('#search-input').val());
	if(!val){
		$('#set-favorite').css('display','none');
	}else{
		$('#set-favorite').css('display','inline');
	};
	
});

$('#set-favorite').on('click',function(){
	var val = $.trim($('#search-input').val()),
		len = $('#favorite_ul li').length;
	if(!val){
		return;
	};
	var arrCon = [];
	$('#favorite_ul li').each(function(i,item){
		arrCon.push($(item).text());
		return arrCon;
	});
	//console.log(arrCon);
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
	
	arr2.unshift(val);
	if(arr2.length > 5){
		var str2 = arr2.slice(0,5).join('|');
	}else{
		var str2 = arr2.join('|');
	};
	//创建一个cookie并设置有效时间为365天:
	$.cookie('the_cookie_2', str2, { expires:365 });

	if(!val){
		return;
	}else{
		if(len<5){
			$('#favorite_ul').prepend('<li>'+val+'</li>')
		}else{
			$('#favorite_ul li').eq(4).remove();
			$('#favorite_ul').prepend('<li>'+val+'</li>')
		}
	}
});
function hotSer(){

	var val = $.trim($('#search-input').val()),
	len = $('#cook_ul li').length;
	if(!val){
		return;
	}
	var arrCon = [];
	$('#cook_ul li').each(function(i,item){
		arrCon.push($(item).text());
		return arrCon;
	});
	//console.log(arrCon);
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
		$.ajax({
			type:"get",
			contentType: 'application/json',
		    dataType:"json",
			url:dataUrl.util.getResultList(val, 20, 1),
			success:function(returnData){
				//console.log(returnData)
				if(returnData.data!==null){
					window.location.href='result?word='+escape(val)+'?pagenumber=1';
					$('#search-hint').addClass('hidecommon');
				}else{
					$('#search-hint').removeClass('hidecommon');
				};
			},
			error:function(){
				console.log('获取热点话题失败');
			}
		});
	}else{
		arr1.unshift(val);
		if(arr1.length > 5){
			var str1 = arr1.slice(0,5).join('|');
		}else{
			var str1 = arr1.join('|');
		};
		//创建一个cookie并设置有效时间为365天:
		$.cookie('the_cookie_1', str1, { expires:365 });
		if(!val){
			return;
		}else{
			if(len<5){
				$('#cook_ul').prepend('<li>'+val+'</li>')
			}else{
				$('#cook_ul li').eq(4).remove();
				$('#cook_ul').prepend('<li>'+val+'</li>');
			};
			$.ajax({
				type:"get",
				contentType: 'application/json',
			    dataType:"json",
				url:dataUrl.util.getResultList(val, 20, 1),
				success:function(returnData){
					//console.log(returnData);
					if(returnData.data!==null){
						window.location.href='result?word='+escape(val)+'?pagenumber=1';
						$('#search-hint').addClass('hidecommon');
					}else{
						$('#search-hint').removeClass('hidecommon');
					};
				},
				error:function(){
					console.log('获取热点话题失败');
				}
			});
		}
	}
	

};
$('#search-btn').click(function(){
	hotSer()
});
$('#search-input').keyup(function(event){
	if (event.keyCode == "13") {
		hotSer()
	};
});
$('.close_hint').bind('click',function(){
	$('#search-hint').addClass('hidecommon');
});


//主体
chartsAttr.templates.registerTmpl("DataCenterList", "tmplHotsports"); //获取热点话题列表
var NowNumber = 1;
hotspotsListMain(NowNumber);
function hotspotsListMain(NowNum){
	$.ajax({
		type:"get",
		contentType: 'application/json',
	    dataType:"json",
		url:dataUrl.util.getHostportsList(NowNum),
		success:function(returnData){
			//console.log(returnData);
			$("#hotspotsMain").html(chartsAttr.templates.DataCenterList.render(returnData));
		},
		error:function(){
			console.log('获取热点话题列表失败');
		}
	});
}

//换一批#change-c
$('#change-c').on('click',function(){
	NowNumber ++;
	hotspotsListMain(NowNumber);
});

$(document).on("click",".related",function(){
    var topicId = $(this).find("span").data("id");
    var topic = $(this).prev().text();
    topic = topic.split("#");
    topic = topic[1]
	var content = "";
	content += '<input class="releateTag" placeholder="请输入关键字" />';
	content += '<img style="padding:0 10px;position: relative;bottom: 17px;" src="img/link3.png">';
	content += '<div class="selectTag" title='+topic+'>'+topic+'</div>';
	var pop = new Pop({
        width:"380px",
        header:"请输入需要与该热点关联的信息",
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
//热点预测日历
$(".pnl-calendar").calendar({
    width: 220,
    height: 180,
    
    data: [{
        date: '2016/01/01',
        value: 'Happy New Year'
    }],
    onSelected: function(view, date, data) {
        console.log('date:' + date);
    }
});

$('#search-hint').on('click',function(e){
	e?e.stopPropagation():event.cancelBubble = true;
});
$(document).on('click',function(){
	$('#search-hint').addClass('hidecommon')
})
