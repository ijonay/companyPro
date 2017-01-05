//头部。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
var hotsystem_flag=true;
$('#nav-head-search').on('click',function(){
    var val = $.trim($('#nav_ser').val());
    if(val){
        if(val.match(/\d+/g)||val.search(/[a-zA-Z]+/)!==-1||/[\u4E00-\u9FA5]/g.test(val)){
            window.location.href='hotresult?clueWord='+escape(val)+'&pageSize=20&currentPage=1';
        }else{
            return;
        };
    }
});
$('#nav_ser').keyup(function(event) {//搜索框回车
    var val = $.trim($('#nav_ser').val());
    if(event.keyCode == "13") {
        if(val){
            if(val.match(/\d+/g)||val.search(/[a-zA-Z]+/)!==-1||/[\u4E00-\u9FA5]/g.test(val)){
                window.location.href='hotresult?clueWord='+escape(val)+'&pageSize=20&currentPage=1';
            }else{
                return;
            }
        };
    };
});



//搜索。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
//设置为常用
$('#ser_text').on('input',function(e){
    e ? e.stopPropagation() : event.cancelBubble = true;
    var val = $.trim($('#ser_text').val());
    if(val){
        $('#favorite_set_btn').removeClass('hidecommon');
    }else{
        $('#ser_hint').addClass('hidecommon');
        $('#favorite_set_btn').addClass('hidecommon');
    }

    $('#cook_ul').addClass('hidecommon');
});
$(document).on('click',function(e){
    if($(".right-bar").css("right") == "0px"){
        if($(window).width() - e.clientX > 277)
            $(".right-bar-close").trigger("click");
    }
    $('#cook_ul').addClass('hidecommon');
    $('#favorite_set_btn').addClass('hidecommon');
});
//添加常用
function deleteRepetion(arr)
{
    var n = []; //一个新的临时数组
    for(var i = 0; i < arr.length; i++) //遍历当前数组
    {
        //如果当前数组的第i已经保存进了临时数组，那么跳过，
        //否则把当前项push到临时数组里面
        var hasExit = false;
        $.each(n,function(index,item){
            if(item.name == unescape(arr[i].name)){
                hasExit = true
            }
        })
        if(!hasExit){
            n.push({id:arr[i].id,name:unescape(arr[i].name)});
        }
    }
    return n;
}
function getCommon(){
    $("#favorite_ul").empty();
    $.ajax({
        type:"post",
        contentType: 'application/json',
        dataType:"json",
        url:dataUrl.util.getCommon(),
        success:function(returnData){
            $('.favorite_div').removeClass('hidecommon');
            if(returnData.data != null && returnData.error.code == 0){

                var str = "";
                var arr = [];
                $.each(returnData.data,function(index,item){
                    if(index > 4) return;
                    arr.push({id:item.id,name:item.words});
                });
                var newArr = deleteRepetion(arr);
                $.each(newArr,function(index,item){
                    str += "<li data-id='"+item.id+"' title='"+ unescape(item.name) +"'>"+unescape(item.name)+"<span></span></li>"

                });
                $("#favorite_ul").html(str);
                if(returnData.data.length == 0){
                    $('.favorite_div').addClass('hidecommon');
                }
            }
        },
        error:function(){
            console.log('获取常用失败');
        }
    });
}
getCommon();
//设为常用
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
    var data = {searchWords:val};
    $.ajax({
        type:"post",
        url:dataUrl.util.addCommon(),
        data:data,
        success:function(returnData){
            if(returnData.error.code == 0){
                $('.favorite_div').removeClass('hidecommon');
                if(len>=5){
                    $('#favorite_ul').find('li').eq(4).remove();
                    $('#favorite_ul').prepend('<li data-id="'+returnData.data+'" title='+val+'>'+val+'<span></span></li>');
                }else{
                    $('#favorite_ul').prepend('<li data-id="'+returnData.data+'" title='+val+'>'+val+'<span></span></li>');
                };
            }
        },
        error:function(){
            console.log('添加常用失败');
        }
    });
});
$('#favorite_ul').delegate('li','click',function(){
    var val = $(this).text();
    $('#ser_text').val(val);
});
//删除常用
$('#favorite_ul').delegate('li span','click',function(e){
    var $this = $(this);
    e ? e.stopPropagation() : event.cancelBubble = true;
    var id = $(this).parent().data("id");
    var data = {"id":id};
    $.ajax({
        type:"post",
        url:dataUrl.util.cancleCommon(),
        data:data,
        success:function(returnData){
            if(returnData.error.code == 0){
                $this.parent().remove();
                if($('#favorite_ul li').length == 0){
                    $('.favorite_div').addClass('hidecommon');
                }
            }
        },
        error:function(){
            console.log('获取常用失败');
        }
    });
});

//设置为历史记录

$('#ser_text').focus(function(e){
    alertCon.hide();
    e ? e.stopPropagation() : event.cancelBubble = true;
    var width = $(".ser_section input").width();
    var scrollY = window.scrollY;
    if(scrollY == undefined){
        scrollY = window.pageYOffset
    }
    var scrollX = window.scrollX;
    if(scrollX == undefined){
        scrollX = window.pageXOffset
    }
    var top = $(".ser_section input").get(0).getBoundingClientRect().top + scrollY + 38;
    var left = $(".ser_section input").get(0).getBoundingClientRect().left + scrollX;
    $('#cook_ul').css({width:width+12,top:top,left:left});
    $('#cook_ul').removeClass('hidecommon');
});
$('#ser_text').blur(function(){
    //$('#cook_ul').addClass('hidecommon');
});
//获取历史记录

getSetHistory()
function getSetHistory(){
    $("#cook_ul").empty();
    $.ajax({
        type:"post",
        contentType: 'application/json',
        dataType:"json",
        url:dataUrl.util.getSerHistory(),
        success:function(returnData){
            if(returnData.data != null && returnData.error.code == 0){
                var str = "";
                var arr = [];
                $.each(returnData.data,function(index,item){
                    arr.push({id:item.id,name:item.keyword});
                    //str += "<li data-id='"+item.id+"'>"+unescape(item.keyword)+"<span></span></li>"
                });
                var newArr = deleteRepetion(arr);
                $.each(newArr,function(index,item){
                    //str += "<li data-id='"+item.id+"' title='"+ unescape(item.name) +"'>"+unescape(item.name)+"<span></span></li>"
                    str += "<li data-id='"+item.id+"'>"+unescape(item.name)+"<span></span></li>"
                });
                $("#cook_ul").html(str);
            }
        },
        error:function(){
            console.log('获取历史记录失败');
        }
    });
};

$('#ser_text').keyup(function(event) {
    var val = $.trim($('#ser_text').val());
    if(event.keyCode == "13") {
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
            window.location.href='hotresult?clueWord='+escape(val)+'&pageSize=20&currentPage=1';
            return;
        };
        if(val.match(/\d+/g)||val.search(/[a-zA-Z]+/)!==-1||/[\u4E00-\u9FA5]/g.test(val)){
            $('#ser_hint').addClass('hidecommon');
            var data = {keyword:val};
            $.ajax({
                type:"post",
                url:dataUrl.util.addSerHistory(),
                data:data,
                success:function(returnData){
                    if(returnData.error.code == 0){
                        var len = $('#cook_ul li').length;


                        if(!val){
                            return;
                        }else{
                            if(len>=5){
                                $('#cook_ul li').eq(4).remove();
                                $('#cook_ul').prepend('<li data-id="'+returnData.data+'">'+val+'<span></span></li>');
                            }else{
                                $('#cook_ul').prepend('<li data-id="'+returnData.data+'">'+val+'<span></span></li>');
                            };
                            window.location.href='hotresult?clueWord='+escape(val)+'&pageSize=20&currentPage=1';
                        };
                    }
                },
                error:function(){
                    console.log('获取常用失败');
                }
            });

        }else{
            $('#ser_hint').removeClass('hidecommon');
            return;
        };

    }
});


$('#ser_btn').on('click',function(){
    var val = $.trim($('#ser_text').val());
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
        window.location.href='hotresult?clueWord='+escape(val)+'&pageSize=20&currentPage=1';
        return;
    };
    if(val.match(/\d+/g)||val.search(/[a-zA-Z]+/)!==-1||/[\u4E00-\u9FA5]/g.test(val)){
        $('#ser_hint').addClass('hidecommon');
        var data = {keyword:escape(val)};
        $.ajax({
            type:"post",
            url:dataUrl.util.addSerHistory(),
            data:data,
            success:function(returnData){
                if(returnData.error.code == 0){
                    var len = $('#cook_ul li').length;


                    if(!val){
                        return;
                    }else{
                        if(len>=5){
                            $('#cook_ul li').eq(4).remove();
                            $('#cook_ul').prepend('<li data-id="'+returnData.data+'">'+val+'<span></span></li>');
                        }else{
                            $('#cook_ul').prepend('<li data-id="'+returnData.data+'">'+val+'<span></span></li>');
                        };
                        window.location.href='hotresult?clueWord='+escape(val)+'&pageSize=20&currentPage=1';
                    };
                }
            },
            error:function(){
                console.log('获取常用失败');
            }
        });

    }else{
        $('#ser_hint').removeClass('hidecommon');
        return;
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
    var $this = $(this);
    var id = $(this).parent().data("id");
    e ? e.stopPropagation() : event.cancelBubble = true;
    var data = {"id":id};
    $.ajax({
        type:"post",
        url:dataUrl.util.cancleSerHistory(),
        data:data,
        success:function(returnData){
            if(returnData.error.code == 0){
                $this.parent().remove();
            }
        },
        error:function(){
            console.log('获取常用失败');
        }
    });

});

//高级搜索弹窗。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
$('#ser_btn_high').on('click',function(){
    labelList();
    $('#ser_dialog').removeClass('hidecommon');
});

$('.dialog_area .ser_dialog_close').on('click',function(){
    dialogInit();
    $('#ser_dialog').addClass('hidecommon');
});


$('.dialog_tab').delegate('li','click',function(){
    var $t = $(this).index();
    var $ul = $(this).parent().next().find('ul');

    if($ul.eq($t).css('display')=='block'){
        $(this).removeClass('cor389b9f');
        $(this).removeClass('hot_arrow_up');
        $ul.eq($t).addClass('hidecommon')
    }else{
        $('.dislog_inp_con').find('ul').addClass('hidecommon');
        $ul.eq($t).removeClass('hidecommon');
        $('.dialog_tab li').removeClass('cor389b9f');
        $(this).addClass('cor389b9f');
        $('.dialog_tab li').removeClass('hot_arrow_up')
        $(this).addClass('hot_arrow_up');
    }
});
//获取更新
var recordList = $.templates(templates.design["tmplRecordList"]);
var recordList2 = $.templates(templates.design["tmplRecordList2"]); 

function recordLog(){
    $.ajax({
        type:"get",
        contentType: 'application/json',
        dataType:"json",
        url:dataUrl.util.getVersionsInfo,
        success:function(returnData){
            var str = '';
            returndata = returnData;
            if(returndata == null){
                console.log('数据为空');
            }else{
                $("#record-ul-con").html(recordList.render(returndata));
                $("#record-ul-2").html(recordList2.render(returndata));
            }
        },
        error:function(){
            console.log('获取标签列表失败');
        }
    });
}

function labelList(){
    $.ajax({
        type:"get",
        contentType: 'application/json',
        dataType:"json",
        url:dataUrl.util.getInpList(),
        success:function(returnData){
            returnData = returnData.data;
            if(returnData == null){
                console.log('数据为空');
            }else{
                var eventData = returnData.EventClass;
                var eventTemp = eventData.slice(0,9);
                var eventTemp2 = eventData.slice(9);
                var userData = [];
                var child1 = JSON.stringify(returnData.Gender);
                child1 = JSON.parse(child1);                
                var obj1 = {
                        id:'Gender',
                        name:"性别",
                        childs:child1
                }
                var child2 = JSON.stringify(returnData.Education);
                child2 = JSON.parse(child2);                
                var obj2 = {
                        id:'Education',
                        name:"学历",
                        childs:child2
                }
                var child3 = JSON.stringify(returnData.Area);
                child3 = JSON.parse(child3);                
                var obj3 = {
                        id:'Area',
                        name:"地区",
                        childs:child3
                }
                var child4 = JSON.stringify(returnData.UserClass);
                child4 = JSON.parse(child4);                
                var obj4 = {
                        id:'UserClass',
                        name:"兴趣",
                        childs:child4
                }
                userData.push(obj1);
                userData.push(obj2);
                userData.push(obj3);
                userData.push(obj4);
                $(".eventDialogTab").empty();
                $(".eventTab").empty();
                $(".eventDialogTab2").empty();
                $(".eventTab2").empty();
                $(".userDialogTab").empty();
                $(".personTab").empty();
                $('#userDialog_tag').empty();
                fillData($(".eventDialogTab"),$(".eventTab"),eventTemp);
                fillData($(".eventDialogTab2"),$(".eventTab2"),eventTemp2);
                fillTagData($("#userDialog_tag"),returnData.Circle);
             
                fillDataBot($(".userDialogTab"),$(".personTab"),userData);
            }
            
        },
        error:function(){
            console.log('获取标签列表失败');
        }
    });
};

//高级探索弹窗搜索
$('#dialog_ser_text').keyup(function(event){
    if(event.keyCode == "13") {
        $('#dialog_ser_to').click();
    };  
});

$('#dialog_ser_to').on('click',function(){
    var val = $.trim($('#dialog_ser_text').val());
        if(val&&val.match(/\d+/g)||val.search(/[a-zA-Z]+/)!==-1||/[\u4E00-\u9FA5]/g.test(val)){
            var dataObj = {
                    Even:[],
                    newtag:[],
                    Area:[],
                    Age:[],
                    Education:[],
                    Gender:[],
                    UserClass:[]
            };
            
            if($('#person_new_tag').is('.hidecommon')){
            }else{
                var list = $('#person_new_tag').find('i');
                $(list).each(function(i,item){
                    var dataId = $(this).attr('data-id');
                    var dataText = $(this).text();
                    dataObj.newtag.push({id:dataId,name:dataText})
                });
                var nextagtext = JSON.parse(JSON.parse($('#person_new_tag').find('div').text()));
                dataObj.Gender = nextagtext.gender;
                dataObj.Education = nextagtext.education;
                dataObj.UserClass = nextagtext.userClass;
//                var ageVal3 = $('#hot_age1').val(),
//                ageVal4 = $('#hot_age2').val();
//                dataObj.Age.push(ageVal3);
//                dataObj.Age.push(ageVal4);
            };
            
            if($('#inp_data_event').is('.hidecommon')){
            }else{
                var list = $('#inp_data_event').find('i');
                $(list).each(function(i,item){
                    var dataId = $(this).attr('data-id');
                    var dataText = $(this).text();
                    dataObj.Even.push({id:dataId,name:dataText})
                });
            };
            if($('.person_sec').is('.hidecommon')){
            }else{
                var list = $('.person_sec').find('i');
                $(list).each(function(i,item){
                    var dataId = $(this).attr('data-id');
                    var dataText = $(this).text();
                    dataObj.Gender.push({id:dataId,name:dataText})
                });
            };
            
            if($('.person_area').is('.hidecommon')){
            }else{
                var list = $('.person_area').find('i');
                $(list).each(function(i,item){
                    var dataId = $(this).attr('data-id');
                    var dataText = $(this).text();
                    dataObj.Area.push({id:dataId,name:dataText})
                });
            };
            if($('.person_education').is('.hidecommon')){
            }else{
                var list = $('.person_education').find('i');
                $(list).each(function(i,item){
                    var dataId = $(this).attr('data-id');
                    var dataText = $(this).text();
                    dataObj.Education.push({id:dataId,name:dataText})
                });
            };
            if($('.person_interest').is('.hidecommon')){
            }else{
                var list = $('.person_interest').find('i');
                $(list).each(function(i,item){
                    var dataId = $(this).attr('data-id');
                    var dataText = $(this).text();
                    dataObj.UserClass.push({id:dataId,name:dataText})
                });
            };
            var ageVal1 = $('#hot_age1').val(),
                ageVal2 = $('#hot_age2').val();
            if(ageVal1){
                dataObj.Age.push(ageVal1)
            }
            if(ageVal2){
                dataObj.Age.push(ageVal2)
            }
            var hash = JSON.stringify(dataObj);
            window.location.href='hotresult?clueWord='+escape(val)+'&pageSize=20&currentPage=1#'+hash;
        }else{
            return;
        };
});
function  fillTagData(selectortag,data){
	 $.each(data,function(index,item){
		 selectortag.append('<li><label>'+item.name+'<input type="checkbox" data-id="'+item.id+'"></label><div style="display:none">'+JSON.stringify(item.rule)+'</div></li>');
	 })
}
function fillData(selector,selector2,data){
    //selector.append('<li>年龄</li>');
    $.each(data,function(index,item){
        selector.append('<li class="pst"><em  data-id="'+item.id+'" >'+item.name+'</em><span class="pos dialog_inp_num">0</span></li>');
        var childs = item.childs;
        if(childs){
            var str = '<ul class="hidecommon"> <li class="inp_ch_list fl">'
            $.each(childs,function(index,item){
                str += '<label><input type="checkbox" data-id="'+item.id+'">'+item.name+'</label>'
            })
            str += '</li> <li class="inp_select_all fr"> <label><input type="checkbox" style="margin-top:2px;">全选</label> </li> </ul>';
        }else{
            var str = '<ul class="hidecommon"> <li class="inp_ch_list fl">';
            str += '</li> <li class="inp_select_all fr"> <label><input type="checkbox" style="margin-top:2px;">全选</label> </li> </ul>';
        }
        selector2.append(str);
    });
};

function fillDataBot(selector,selector2,data){
    
    selector.append('<li>年龄</li>');
    $.each(data,function(index,item){
        selector.append('<li class="pst"><em  data-id="'+item.id+'" >'+item.name+'</em><span class="pos dialog_inp_num">0</span></li>');
        var childs = item.childs;
        if(childs){
            var str = '<ul class="hidecommon"> <li class="inp_ch_list fl">'
            $.each(childs,function(index,item){
                str += '<label><input type="checkbox" data-id="'+item.id+'">'+item.name+'</label>'
            })
            str += '</li> <li class="inp_select_all fr"> <label><input type="checkbox" style="margin-top:2px;">全选</label> </li> </ul>';
        }else{
            var str = '<ul class="hidecommon"> <li class="inp_ch_list fl">';
            str += '</li> <li class="inp_select_all fr"> <label><input type="checkbox" style="margin-top:2px;">全选</label> </li> </ul>';
        }
        selector2.append(str);
    });
    selector2.prepend('<ul class="hot_dia_age hidecommon" id="hot_dia_age"><div class="age_dia_con">'+
            '<input onkeyup="clearChat(this)" maxlength="3" id="hot_age1" type="text"><b>岁</b><b>至</b>'+
            '<input  onkeyup="clearChat(this)" maxlength="3" id="hot_age2" type="text"><b>岁</b>'+
            '</div></ul>')
};

//受众新标签点击
$('#userDialog_tag').delegate('li input','click',function(){
    var dataId = $(this).attr('data-id');
    var distext = $(this).parent().next().text();
    if($(this).is(':checked')){
        
        $(this).parent().parent().parent().find('label').css('background-color','#c5c5c5');
        $(this).parent().css('background-color','#389b9f');
        $('.person_new_tag').html('');
        
        $('#inp_data_event i').remove();
        $('#inp_data_event').addClass('hidecommon');
        $('.person_sec i').remove();
        $('.person_sec').addClass('hidecommon');
        $('.person_education i').remove();
        $('.person_education').addClass('hidecommon');
        $('.person_area i').remove();
        $('.person_area').addClass('hidecommon');
        $('.person_interest i').remove();
        $('.person_interest').addClass('hidecommon');
        var input1 = $('#ser_dialog').find('input');
        var input2 = $('#userDialog_tag').find('input');
        $(input1).not(input2).prop('checked',false);
        $('.dialog_inp_num').text(0);
        $('.dialog_inp_num').css('display','none');
		$('.person_new_tag').append('<i data-id="'+dataId+'">'+$(this).parent().text()+'<span></span></i><div style="display:none;">'+distext+'</div>');
		
		var newText = JSON.parse(JSON.parse(distext));
		var lensex = newText.gender.length;
		if(lensex>0){
			$('.userDialogTab li').eq(1).find('.dialog_inp_num').text(lensex);
			$('.userDialogTab li').eq(1).find('.dialog_inp_num').css('display','block')
		}
		
		$.each(newText.gender,function(i,item){
			if(item == 208){
				$('#dialog-bottom-new ul').eq(1).find('input').eq(0).prop('checked',true);
			}else{
				$('#dialog-bottom-new ul').eq(1).find('input').eq(1).prop('checked',true);
			}
		})
		var leneducation = newText.education.length;
		if(leneducation>0){
			$('.userDialogTab li').eq(2).find('.dialog_inp_num').text(leneducation);
			$('.userDialogTab li').eq(2).find('.dialog_inp_num').css('display','block');
		};
		var bot2 = $('#dialog-bottom-new ul').eq(2).find('input');
		var _that = $(this);
		$.each(newText.education,function(i,item){
			$(bot2).each(function(){
				if($(this).attr('data-id') == item){
					$(this).prop('checked',true);
				}
			})
		});
		
		var leninterest = newText.userClass.length;
		if(leninterest>0){
			$('.userDialogTab li').eq(4).find('.dialog_inp_num').text(leninterest);
			$('.userDialogTab li').eq(4).find('.dialog_inp_num').css('display','block');
		};
		var bot4 = $('#dialog-bottom-new ul').eq(4).find('input');
		$.each(newText.userClass,function(i,item){
			$(bot4).each(function(){
				if($(this).attr('data-id') == item){
					$(this).prop('checked',true);
				}
			})
		});
		$.each(newText.age,function(i,item){
			
		});
		$('#hot_age1').val(newText.age[0]);
		$('#hot_age2').val(newText.age[1]);
		
		$(".dislog_inp_con").find('input').attr('disabled',true);
        $(this).parent().parent().parent().find('input').prop('checked',false);
        $(this).prop('checked',true);
		$('.person_new_tag').removeClass('hidecommon');
        $('#inp_data_person1').removeClass('hidecommon');
        $('.dialog_inp_c').removeClass('hidecommon');
    }else{
    	$('#hot_age1').val('');
		$('#hot_age2').val('');
    	$('#dialog-bottom-new').find('input').prop('checked',false);
    	$('.dialog_inp_num').text(0);
    	$('.dialog_inp_num').css('display','none');
        $(".dislog_inp_con").find('input').attr('disabled',false);
        var len = $('#person_new_tag').find('i').length;
        $(this).parent().css('background-color','#c5c5c5');
        $('#person_new_tag i').each(function(i,item){
            if($(this).attr('data-id') == dataId){
                $(this).remove();
            }
        });
        if(len<=1){
            $('.person_new_tag').addClass('hidecommon');
        };
        var len1 = $('#inp_data_event').find('i').length;
        
        var len2 = $('#inp_data_person1').find('i').length;
        if(len2<1){
            $('#inp_data_person1').addClass('hidecommon');
        }
        if(len1<1&&len2<1){
            $('.dialog_inp_c').addClass('hidecommon');
        };
        
    }
    
});
//删除受众新标签
$('#person_new_tag').delegate('span','click',function(){
	$('#dialog-bottom-new').find('input').prop('checked',false);
	$('.dialog_inp_num').text(0);
	$('.dialog_inp_num').css('display','none');
	$('#hot_age1').val('');
	$('#hot_age2').val('');
    $(".dislog_inp_con").find('input').attr('disabled',false);
 
    var dataId = $(this).parent().attr('data-id');
    $(this).parent().remove();
    $('#userDialog_tag li input').each(function(){
        
        var _this = $(this);
        if(_this.attr('data-id') == dataId){
            _this.prop("checked", false);
            _this.parent().css('background-color','#c5c5c5');
        }
    })
    var len = $('#person_new_tag').find('i').length;
    if(len<1){
        $('#person_new_tag').addClass('hidecommon');
    }
    var len1 = $('#inp_data_event').find('i').length;
    var len2 = $('#inp_data_person1').find('i').length;
    if(len2<1){
        $('#inp_data_person1').addClass('hidecommon');
    }
    if(len1<1&&len2<1){
        $('.dialog_inp_c').addClass('hidecommon');
    };
    
    
});

//事件标签点击
$('.dialog_tab_event').delegate('.inp_ch_list input','click',function(){
    var dataId = $(this).attr('data-id');
    var num = Number($('.cor389b9f').find('span').text());
    var textCon = $(this).parent().text();
    
    if($(this).is(':checked')){
        $('.cor389b9f').find('span').css('display','block');
        $('.cor389b9f').find('span').text(num+1);
        $('#inp_data_event').prepend('<i data-id='+dataId+'>'+textCon+'、</i>');
        var textStr = $('#inp_data_event i:last').text();
        if(textStr.indexOf('、')>-1){
            var newStr = textStr.substring(0,textStr.length-1);
            $('#inp_data_event i:last').text(newStr);
        }
        
        
        $('.dialog_inp_c').removeClass('hidecommon');
        $('#inp_data_event').removeClass('hidecommon');
    }else{
        var len = $('#inp_data_event').find('i').length;
        if(num==1){
            $('.cor389b9f').find('span').css('display','none');
        };
        $('.cor389b9f').find('span').text(num-1);
        if(len <=1){
            $('#inp_data_event').addClass('hidecommon');
        }
        
        $('#inp_data_event i').each(function(i,item){
            if($(this).attr('data-id') == dataId){
                $(this).remove();
            }
        });
        var textStr = $('#inp_data_event i:last').text();
        if(textStr.indexOf('、')>-1){
            var newStr = textStr.substring(0,textStr.length-1);
            $('#inp_data_event i:last').text(newStr);
        }
        
        var len1 = $('#inp_data_event').find('i').length;
        var len2 = $('#inp_data_person1').find('i').length;
        if(len1<1&&len2<1){
            $('.dialog_inp_c').addClass('hidecommon');
        };
    };
});
$('.dialog_tab_event').delegate('.inp_select_all input','click',function(){
    var num = Number($('.cor389b9f').find('span').text());
    var len = $(this).parent().parent().prev().find('input').length;
    var inList = $(this).parent().parent().prev().find('input');
    if($(this).is(':checked')){
        $('.dialog_inp_c').removeClass('hidecommon');
        $('#inp_data_event').removeClass('hidecommon');
        var lenList = $('#inp_data_event').find('i').length;
        $(inList).each(function(i,item){
            if($(this).prop("checked")==false){
                $('#inp_data_event').removeClass('hidecommon');
                $('#inp_data_event').prepend('<i data-id='+$(this).attr('data-id')+'>'+$(this).parent().text()+'、</i>');
                
            }
        });
        var textStr = $('#inp_data_event i:last').text();
        if(textStr.indexOf('、')>-1){
            var newStr = textStr.substring(0,textStr.length-1);
            $('#inp_data_event i:last').text(newStr);
        }
        
        $(this).parent().parent().prev().find('input').prop("checked", true);
        $('.cor389b9f').find('span').css('display','block')
        $('.cor389b9f').find('span').text(len);
    }else{
        $(this).parent().parent().prev().find('input').prop("checked", false);
        $('.cor389b9f').find('span').text(0);
        $('.cor389b9f').find('span').css('display','none');
        $(inList).each(function(i,item){
            var dataid = $(this).attr('data-id');
            $('#inp_data_event i').each(function(j,item){
                if($(this).attr('data-id')==dataid){
                    $(this).remove();
                }
            })
        });
        if($('#inp_data_event i').length<1){
            $('#inp_data_event').addClass('hidecommon');
        };
        var len1 = $('#inp_data_person1').find('i').length;
        var len2 = $('#inp_data_event').find('i').length;
        if(len1<1&&len2<1){
            $('.dialog_inp_c').addClass('hidecommon');
        };
    }
});

//人群标签点击
$('.dialog_tab_person').delegate(' .inp_ch_list input','click',function(){
    
    var dataId = $(this).attr('data-id');
    var num = Number($('.cor389b9f').find('span').text());
    var textCon = $(this).parent().text();
    var textPar = $('.cor389b9f').find('em').text();
    
    if($(this).is(':checked')){
        $('.cor389b9f').find('span').css('display','block');
        $('.cor389b9f').find('span').text(num+1);
        if(textPar=='性别'){
            $('#inp_data_person1 .person_sec').prepend('<i data-id='+dataId+'>'+textCon+'、</i>');

            var textStr = $('.person_sec i:last').text();
            if(textStr.indexOf('、')>-1){
                var newStr = textStr.substring(0,textStr.length-1);
                $('.person_sec i:last').text(newStr);
            }
            $('.person_sec').removeClass('hidecommon');
        }else if(textPar=='学历'){
            $('#inp_data_person1 .person_education').prepend('<i data-id='+dataId+'>'+textCon+'、</i>');
            var textStr = $('.person_education i:last').text();
            if(textStr.indexOf('、')>-1){
                var newStr = textStr.substring(0,textStr.length-1);
                $('.person_education i:last').text(newStr);
            }
            $('.person_education').removeClass('hidecommon');
        }else if(textPar=='地区'){
            $('#inp_data_person1 .person_area').prepend('<i data-id='+dataId+'>'+textCon+'、</i>');
            var textStr = $('.person_area i:last').text();
            if(textStr.indexOf('、')>-1){
                var newStr = textStr.substring(0,textStr.length-1);
                $('.person_area i:last').text(newStr);
            }
            $('.person_area').removeClass('hidecommon');
        }else if(textPar=='兴趣'){
            $('#inp_data_person1 .person_interest').prepend('<i data-id='+dataId+'>'+textCon+'、</i>');
            var textStr = $('.person_interest i:last').text();
            if(textStr.indexOf('、')>-1){
                var newStr = textStr.substring(0,textStr.length-1);
                $('.person_interest i:last').text(newStr);
            }
            $('.person_interest').removeClass('hidecommon');
        }
        
        
        $('.dialog_inp_c').removeClass('hidecommon');
        $('#inp_data_person1').removeClass('hidecommon');
    }else{
        var len = $('#inp_data_person1').find('i').length;
        if(num==1){
            $('.cor389b9f').find('span').css('display','none');
        };
        $('.cor389b9f').find('span').text(num-1);
        if(len <=1){
            $('#inp_data_person1').addClass('hidecommon');
        }
        $('#inp_data_person1 i').each(function(i,item){
            if($(this).attr('data-id') == dataId){
                $(this).remove();
            }
        });
        var textStr = $('#inp_data_person1 i:last').text();
        if(textStr.indexOf('、')>-1){
            var newStr = textStr.substring(0,textStr.length-1)
            $('#inp_data_person1 i:last').text(newStr);
        };
        var lensex = $('.person_sec').find('i').length;
        if(lensex<1){
            $('.person_sec').addClass('hidecommon');
        };
        var leneducation = $('.person_education').find('i').length;
        if(leneducation<1){
            $('.person_education').addClass('hidecommon');
        }
        var lenarea = $('.person_area').find('i').length;
        if(lenarea<1){
            $('.person_area').addClass('hidecommon');
        }
        var leninterest = $('.person_interest').find('i').length;
        if(leninterest<1){
            $('.person_interest').addClass('hidecommon');
        }
        
        var len1 = $('#inp_data_event').find('i').length;
        var len2 = $('#inp_data_person1').find('i').length;
        if(len1<1&&len2<1){
            $('.dialog_inp_c').addClass('hidecommon');
        };
    };
});
$('.dialog_tab_person').delegate('.inp_select_all input','click',function(){
    var num = Number($('.cor389b9f').find('span').text());
    var len = $(this).parent().parent().prev().find('input').length;
    var inList = $(this).parent().parent().prev().find('input');
    var textPar = $('.cor389b9f').find('em').text();
    if($(this).is(':checked')){
        $('.dialog_inp_c').removeClass('hidecommon');
        $('#inp_data_person1').removeClass('hidecommon');
        var lenList = $('#inp_data_person1').find('i').length;
        
        
        $(inList).each(function(i,item){
            if($(this).prop("checked")==false){
                //console.log($(this).parent().text());
                $('#inp_data_person1').removeClass('hidecommon');
                if(textPar=='性别'){
                    $('#inp_data_person1 .person_sec').prepend('<i data-id='+$(this).attr('data-id')+'>'+$(this).parent().text()+'、</i>');
                    var textStr = $('.person_sec i:last').text();
                    if(textStr.indexOf('、')>-1){
                        var newStr = textStr.substring(0,textStr.length-1)
                        $('.person_sec i:last').text(newStr);
                    };
                    $('.person_sec').removeClass('hidecommon');
                }else if(textPar=='学历'){
                    $('#inp_data_person1 .person_education').prepend('<i data-id='+$(this).attr('data-id')+'>'+$(this).parent().text()+'、</i>');
                    var textStr = $('.person_education i:last').text();
                    if(textStr.indexOf('、')>-1){
                        var newStr = textStr.substring(0,textStr.length-1)
                        $('.person_education i:last').text(newStr);
                    };
                    $('.person_education').removeClass('hidecommon');
                }
                else if(textPar=='地区'){
                    $('#inp_data_person1 .person_area').prepend('<i data-id='+$(this).attr('data-id')+'>'+$(this).parent().text()+'、</i>');
                    var textStr = $('.person_area i:last').text();
                    if(textStr.indexOf('、')>-1){
                        var newStr = textStr.substring(0,textStr.length-1)
                        $('.person_area i:last').text(newStr);
                    };
                    $('.person_area').removeClass('hidecommon');
                }else if(textPar=='兴趣'){
                    $('#inp_data_person1 .person_interest').prepend('<i data-id='+$(this).attr('data-id')+'>'+$(this).parent().text()+'、</i>');
                    var textStr = $('.person_interest i:last').text();
                    if(textStr.indexOf('、')>-1){
                        var newStr = textStr.substring(0,textStr.length-1)
                        $('.person_interest i:last').text(newStr);
                    };
                    $('.person_interest').removeClass('hidecommon');
                }
                
                
            }
        });

        
        $(this).parent().parent().prev().find('input').prop("checked", true);
        $('.cor389b9f').find('span').css('display','block')
        $('.cor389b9f').find('span').text(len);
    }else{
        $(this).parent().parent().prev().find('input').prop("checked", false);
        $('.cor389b9f').find('span').text(0);
        $('.cor389b9f').find('span').css('display','none');
        $(inList).each(function(i,item){
            var dataid = $(this).attr('data-id');
            $('#inp_data_person1 i').each(function(j,item){
                if($(this).attr('data-id')==dataid){
                    $(this).remove();
                }
            })
        });
        if($('#inp_data_person1 i').length<1){
            $('#inp_data_person1').addClass('hidecommon');
        };
        var textStr = $('#inp_data_person1 i:last').text();
        if(textStr.indexOf('、')>-1){
            var newStr = textStr.substring(0,textStr.length-1)
            $('#inp_data_person1 i:last').text(newStr);
        };
        var lensex = $('.person_sec').find('i').length;
        if(lensex<1){
            $('.person_sec').addClass('hidecommon');
        };
        var leneducation = $('.person_education').find('i').length;
        if(leneducation<1){
            $('.person_education').addClass('hidecommon');
        }
        var lenarea = $('.person_area').find('i').length;
        if(lenarea<1){
            $('.person_area').addClass('hidecommon');
        };
        var leninterest = $('.person_interest').find('i').length;
        if(leninterest<1){
            $('.person_interest').addClass('hidecommon');
        }
        
        var len1 = $('#inp_data_event').find('i').length;
        var len2 = $('#inp_data_person1').find('i').length;
        if(len1<1&&len2<1){
            $('.dialog_inp_c').addClass('hidecommon');
        };
    }
});
//删除事件标签
$('#inp_data_event span').on('click',function(){
    $('#inp_data_event').addClass('hidecommon');
    $('#inp_data_event').find('i').remove();
    $('.dialog_tab_event').find('input').prop("checked",false);
    $('.dialog_tab_event').prev().find('.dialog_inp_num').text(0);
    $('.dialog_tab_event').prev().find('.dialog_inp_num').css('display','none');
    var len1 = $('#inp_data_event').find('i').length;
    var len2 = $('#inp_data_person1').find('i').length;
    if(len1<1&&len2<1){
        $('.dialog_inp_c').addClass('hidecommon');
    };
})
//删除人群标签
$('#inp_data_person1 span').on('click',function(){
    $(this).parent().addClass('hidecommon');
    $(this).parent().find('i').remove();;
    if($(this).parent().attr('class').indexOf('person_sec')>=0){
        $('.userDialogTab li').eq(1).find('.dialog_inp_num').css('display','none');
        $('.userDialogTab li').eq(1).find('.dialog_inp_num').text(0);
        $('.personTab').find('ul').eq(1).find('input').prop("checked",false);
    }
    if($(this).parent().attr('class').indexOf('person_education')>=0){
        $('.userDialogTab li').eq(2).find('.dialog_inp_num').css('display','none');
        $('.userDialogTab li').eq(2).find('.dialog_inp_num').text(0);
        $('.personTab').find('ul').eq(2).find('input').prop("checked",false);
    }
    if($(this).parent().attr('class').indexOf('person_area')>=0){
        $('.userDialogTab li').eq(3).find('.dialog_inp_num').css('display','none');
        $('.userDialogTab li').eq(3).find('.dialog_inp_num').text(0);
        $('.personTab').find('ul').eq(3).find('input').prop("checked",false);
    }
    if($(this).parent().attr('class').indexOf('person_interest')>=0){
        $('.userDialogTab li').eq(4).find('.dialog_inp_num').css('display','none');
        $('.userDialogTab li').eq(4).find('.dialog_inp_num').text(0);
        $('.personTab').find('ul').eq(4).find('input').prop("checked",false);
    }
    
//  $('.dialog_tab_person').find('input').prop("checked",false);
//  $('.dialog_tab_person').prev().find('.dialog_inp_num').text(0);
//  $('.dialog_tab_person').prev().find('.dialog_inp_num').css('display','none');
    var len1 = $('#inp_data_event').find('i').length;
    var len2 = $('#inp_data_person1').find('i').length;
    if(len1<1&&len2<1){
        $('.dialog_inp_c').addClass('hidecommon');
    };
});
//清空标签
$('#dialog_inp_del').on('click',function(){
    dialogInit()
});
dialogInit();
function dialogInit(){
    $(".dislog_inp_con").find('input').attr('disabled',false);
    $('#userDialog_tag').find('input').prop('checked',false);
    $('#userDialog_tag').find('label').css('background-color','#c5c5c5');
    $('#inp_data_person1').find('i').remove();
    $('#inp_data_event').find('i').remove();
    $('#inp_data_person1 div').addClass('hidecommon');
    $('#inp_data_person1').addClass('hidecommon');
    $('#inp_data_event').addClass('hidecommon');
    $('#ser_dialog').find('.dialog_inp_num').text(0);
    $('#ser_dialog').find('.dialog_inp_num').css('display','none');
    $('#ser_dialog').find('input').prop('checked',false);
    $('.dialog_inp_c').addClass('hidecommon');
    $('.dislog_inp_con ul').addClass('hidecommon');
    $('.dialog_tab').find('li').removeClass('cor389b9f');
    $('.dialog_tab').find('li').removeClass('hot_arrow_up');
    $('#dialog_ser_text').val('');
    $('#hot_dia_age').find('input').val('');
    $('#person_new_tag').html('');
    $('#person_new_tag').addClass('hidecommon');
};

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
var canClick = true;
var viewCount = 0;
var maxNode = 0;
function loadSvg(){
    viewCount++;
    var width = $("#papersvg").css("width");
    width = width.split("px")[0];
    paper = Raphael("papersvg",width,160);
    //paper.clear()
    var xArray = [];
    var yArray = [];
    var step = width/11;
    var yMin = 200;
    var yMax = 0;
    for(var i=1;i<11;i++){
        xArray.push(step*i)
    };
    for(var i=0;i<10;i++){
        if(yMin > (scoreArray[i]-0)){
            yMin = scoreArray[i];
        }
        //console.log(yMin);
        if(yMax < (scoreArray[i]-0)){
            yMax = scoreArray[i];
            maxNode = i;
        }
    }
    var step = yMax - yMin;
    step += 1;
    for(var i=0;i<10;i++){
        yArray.push(80 - (60/step)*(scoreArray[i] - yMin))
    }



    var baseLine = "M 0 65 R ";
    for(var i=0;i<xArray.length;i++){
        baseLine += xArray[i] + " 65 ";
    }
    baseLine += " " + width + " 65";
    base = paper.path(baseLine).attr({stroke:"#22c0c6","stroke-width":4,"opacity":0.7});
    var changeLine = "M 0 65 R ";
    for(var i=0;i<xArray.length;i++){
        changeLine += xArray[i] + " " + (yArray[i] + 15) + " ";
    }
    changeLine += " " + width + " 65";
    base.animate({path:changeLine},700,"ease",shadom);

    function shadom(){
//          var cloneEle = base.clone().attr({"opacity":0});
//          var cloneEle2 = base.clone().attr({"opacity":0});
//          cloneEle.attr({"opacity":0.5}).animate({transform:"T 0 15"});
//          cloneEle2.attr({"opacity":0.3}).animate({transform:"T 0 30"})
        var changeLine1 = "M 0 74.5 R ";
        var changeLine2 = "M 0 83 R ";
        for(var i=0;i<xArray.length;i++){
            changeLine1 += xArray[i] + " " + (yArray[i] + 24.5 + Math.random()*1) + " ";
            changeLine2 += xArray[i] + " " + (yArray[i] + 33 + Math.random()*1) + " ";
        }
        changeLine1 += " " + width + " 74.5";
        changeLine2 += " " + width + " 83";

        change1 = paper.path(changeLine1).attr({stroke:"#22c0c6","stroke-width":3,opacity:0.5});
        change2 = paper.path(changeLine2).attr({stroke:"#22c0c6","stroke-width":2,opacity:0.3});

        for(var i=0;i<xArray.length;i++){
//           paper.image("img/apple.png", xArray[i]-10, yArray[i]-10, 20, 20).attr({"opacity":0}).animate({"opacity":1,r:10},700,"easeInOut").click(function(){
//                alert("aaa")
//            });
            var titleSub = "";
            if(titleArray[i].length>12){
                titleSub = titleArray[i].substr(0,12)+"...";
            }else{
                titleSub = titleArray[i]
            }
            var textArrayItem = paper.text(xArray[i],yArray[i]+45,titleSub).attr({"fill":'#fff',"font-family":'微软雅黑',"font-size":"14",title:titleArray[i],opacity:0,cursor:"pointer"}).data("index",i).animate({opacity:1},700,"ease").click(function(e){nodeClick(e,this)});

            var rectArrayItem = paper.rect(xArray[i] - 12,yArray[i] + 3,0,0).attr({fill:"#389b9f",opacity:0,transform:"r45",width:24,height:24,"stroke-width":0,r:2,opacity:0,cursor:"pointer"}).data("index",i).animate({"opacity":1,transform:"r45"},700,"ease").click(function(e){nodeClick(e,this)});
            var hotArrayItem = paper.text(xArray[i],yArray[i] + 15,scoreArray[i]).attr({"fill":'#fff',"font-family":'微软雅黑',"font-size":"16",opacity:0,cursor:"pointer"}).data("index",i).animate({opacity:1},700,"ease").click(function(e){nodeClick(e,this)});
            textArray[i] = textArrayItem;
            rectArray[i] = rectArrayItem;
            hotArray[i] = hotArrayItem;
            if(maxNode == i){
                maxNode = rectArrayItem;
            }
        }
        rectArray.forEach(function(item,index){
            item.hover(function(){
                if(canClick){
                    rectArray[index].attr({fill:"#2ad3da"}).animate({transform:"r45s1.2"})
                    textArray[index].animate({transform:"s1.2"})
                    hotArray[index].animate({transform:"s1.2"})
                }
            },function(){
                if(canClick){
                    rectArray[index].attr({fill:"#389b9f"}).animate({transform:"r45"})
                    textArray[index].animate({transform:"s1"})
                    hotArray[index].animate({transform:"s1"})
                }
            })
        })
        textArray.forEach(function(item,index){
            item.hover(function(){
                if(canClick){
                    rectArray[index].attr({fill:"#2ad3da"}).animate({transform:"r45s1.2"});
                    textArray[index].animate({transform:"s1.2"});
                    hotArray[index].animate({transform:"s1.2"});
                }
            },function(){
                if(canClick){
                    rectArray[index].attr({fill:"#389b9f"}).animate({transform:"r45"});
                    textArray[index].animate({transform:"s1"});
                    hotArray[index].animate({transform:"s1"});
                }
            })
        })
        hotArray.forEach(function(item,index){
            item.hover(function(){
                if(canClick){
                    rectArray[index].attr({fill:"#2ad3da"}).animate({transform:"r45s1.2"});
                    textArray[index].animate({transform:"s1.2"});
                    hotArray[index].animate({transform:"s1.2"});
                }
            },function(){
                if(canClick){
                    rectArray[index].attr({fill:"#389b9f"}).animate({transform:"r45"});
                    textArray[index].animate({transform:"s1"});
                    hotArray[index].animate({transform:"s1"});
                }
            })
        })
        if(viewCount == 1){
            showAlert(maxNode)
        }
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
var setTime;
window.onresize=function(){
    $(".alertCon").hide();
    if(!$('#cook_ul').hasClass("hidecommon")){
        var width = $(".ser_section input").width();
        $('#cook_ul').css("width",width+12);
    }
    clearTimeout(setTime);
    setTime = setTimeout(function(){
        $("#papersvg").html('');
        viewCount = 0;
        loadSvg();
    },500)
};
function showAlert(t){
    if(canClick){
        var index = t.data("index");
        var scrollY = window.scrollY;
        if(scrollY == undefined){
            scrollY = window.pageYOffset
        }
        var scrollX = window.scrollX;
        if(scrollX == undefined){
            scrollX = window.pageXOffset
        }
        var X = rectArray[index].node.getBoundingClientRect().left + document.documentElement.scrollLeft;
        var Y = rectArray[index].node.getBoundingClientRect().top + document.documentElement.scrollTop;
        if(viewCount == 1){
            Y -= 8;
            X += 9;
        }
        var trianglePos = triangleStep * (index + 1);
        $(".triangle").css("left",trianglePos);
        $(".hotValue").html(scoreArray[index]);
        $(".infoTitle").html(titleArray[index]);

        $(".portrait").css("background-image","url("+imageArray[index]+")");
        $(".infoText").html(introArray[index]);
        $(".infoText").attr("title",introArray[index]);
        $(".infoConnect").attr("data-id",hotIdArray[index]);
        $(".infoConnect").attr("data-index",index);
        $(".infoConnect").attr("data-topic",titleArray[index]);
        $(".infoIcon").hide();
        $(".hotAlertTag").html(tagArray[hotIdArray[index]]);
        if(alertCon.css("display") != "none"){
            alertCon.animate({left:X - trianglePos + 12 + scrollX,top:Y - 160 + scrollY},450);
        }else{
            alertCon.css({left:X - trianglePos + 12 + scrollX,top:Y - 160 + scrollY,opacity:0});
            alertCon.show();
            alertCon.animate({opacity:1},500);
        }
        $(".planText").css("margin-left",(262-75-72-$(".hotLeft").width())/2);
        viewCount++;
    }
}
function nodeClick(e,t){
    $('#cook_ul').addClass('hidecommon');
    if(canClick){
        e ? e.stopPropagation() : event.cancelBubble = true;
        showAlert(t);
//            var index = t.data("index");
//            var scrollY = window.scrollY;
//            if(scrollY == undefined){
//                scrollY = window.pageYOffset
//            }
//            var scrollX = window.scrollX;
//            if(scrollX == undefined){
//                scrollX = window.pageXOffset
//            }
//            var jqObj = $("rectArray[index].node");
//            var offset = jqObj.offset();
//            var X = rectArray[index].node.getBoundingClientRect().left + document.documentElement.scrollLeft;
//            var Y = rectArray[index].node.getBoundingClientRect().top + document.documentElement.scrollTop;
//            var trianglePos = triangleStep * (index + 1);
//            $(".triangle").css("left",trianglePos);
//            $(".hotValue").html(scoreArray[index]);
//            $(".infoTitle").html(titleArray[index]);
////            var divH = $(".hotInfo").height();
////            var $p = $(".infoConnect");
////            while ($p.outerHeight() > divH) {
////                $p.text($p.text().replace(/(\s)*([a-zA-Z0-9]+|\W)(\.\.\.)?$/, "..."));
////            };
//            $(".portrait").css("background-image","url("+imageArray[index]+")");
//            $(".infoText").html(introArray[index]);
//            $(".infoText").attr("title",introArray[index]);
//            $(".infoConnect").attr("data-id",hotIdArray[index]);
//            $(".infoConnect").attr("data-index",index);
//            $(".infoConnect").attr("data-topic",titleArray[index]);
//            $(".infoIcon").hide();
////            $(".iconCon a").hide();
////            $.each(formArray[index],function(index,item){
////            	$("#icon"+item).show();
////            })
//            $(".hotAlertTag").html(tagArray[hotIdArray[index]]);
//            if(alertCon.css("display") != "none"){
//            	alertCon.animate({left:X - trianglePos + 12 + scrollX,top:Y - 160 + scrollY},450);
//            }else{
//            	alertCon.css({left:X - trianglePos + 12 + scrollX,top:Y - 160 + scrollY,opacity:0});
//            	alertCon.show();
//            	alertCon.animate({opacity:1},500);
//            }
//            $(".planText").css("margin-left",(262-75-72-$(".hotLeft").width())/2);
    }else{
//        	$("#comeback_hot").click();
    }
}
//    $(".papersvg").click(function(){
//    	if(!canClick){
//    		$("#comeback_hot").click()
//    	}
//    })
$(document).on("click",".hot_relation,.infoConnect,.near_infoConnect",function(e){
    e ? e.stopPropagation() : event.cancelBubble = true;
    var $this = $(this);
    var index = $this.attr("data-index");
//    	if(index < 10){
//    		var topic = titleArray[index];
//    		var hotTopId = hotIdArray[index];
//    	}else{
    var topic = $this.attr("data-topic");
    var hotTopId = $this.attr("data-id");
//    	}
    topic = topic.replace(/#/g,"");
    if($(".selectTag")){
        $(".selectTag").attr("title",topic);
        $(".selectTag").html(topic)
    }
    var content = "";
    content += '<input class="releateTag" autofocus placeholder="请输入关键字" />';
    content += '<div style="display:inline-block;position:relative;left:2px;width:52px;height: 40px;background: url(img/link3.png) center center no-repeat;"></div>';
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
                var hotTopic = titleArray[index];
                if(query.trim() == ""){
                }else{
//                        window.location.href="path?query="+escape(query)+"&topicId="+topicId+"&hotTopic="+escape(topic);
                    window.location.href="newPath?index=0#query="+query+"&topicId="+hotTopId+"&hotTopic="+topic;
                }
            }
        }]
    });
    if($(".selectTag")){
        $(".selectTag").attr("title",topic);
        $(".selectTag").html(topic)
    }
    setTimeout(function(){
        $(".releateTag").focus();
    },10)
})
$(document).on("click",function(e){
    if($(e.target).hasClass("ser_dialog")){
        $(".ser_dialog_close").click();
    }
    if($(e.target).hasClass("alertCon")||$(e.target).hasClass("portrait")||$(e.target).hasClass("infoTitle")||$(e.target).hasClass("hotAlertTag")||$(e.target).hasClass("triangle")||$(e.target).hasClass("triangle")||$(e.target).hasClass("infoTop")||$(e.target).hasClass("info"))return;
    alertCon.hide();
})
$(".infoBottom").on("click",function(e){
    e ? e.stopPropagation() : event.cancelBubble = true;
})
//热点详细信息。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
$('#allHot').on('click',function(){
    $("#papersvg").addClass("pointer");
    for(var i=0,j=rectArray.length;i<j;i++){
//            rectArray[i].attr({cursor:"default"});
//            hotArray[i].attr({cursor:"default"});
//            textArray[i].attr({cursor:"default"});
    }
    $('#ser_section').css('min-height',0);
    $('#ser_section').css('opacity',0);
    canClick = false;
    $(".all_hot_list .hot_echart_list").addClass('hidecommon');
    $('#allHot').addClass('hidecommon');
    $("#circle_hot_section").hide();
    $("#all_hot_section").removeClass("hidecommon");
    $(".all_hot_btn").addClass('all-circle-active');
    $('.circle_btn').css({'background-image':'url(img/hot-all-icon.png)','background-color':''});
    $('#all_hot').removeClass('hidecommon');
    $('#all_hot').animate({
        opacity:1
    },500);
    $('#ser_section').css("height",0);
    $('.notify-list').css('display',"none");
    $('.nav_ser').delay("fast").fadeIn();
    $(".all_hot_list .all_hot_list_bot").hide();
    $('.all_hot_list .all_hot_list_top_source:first').find('.hot_img_arrow').css('transform','rotate(180deg)');
    $('.all_hot_list .all_hot_list_top_source:first').find('em').css('color','#389b9f');
    $('.all_hot_list .all_hot_list_top_source:first').find('.hot_look_detail').css("background-image","url(img/card-detail-hover.png)");;
    if(hotsystem_flag) $('.all_hot_list .all_hot_list_top_source:first').click();
    $('.type-article').each(function(){
        var str = $(this).text();
        if(str.length>20){
            $(this).attr('title',str);
        }
    });
});
//返回首页
$('#comeback_hot').on('click',function(){
    canClick = true;
    hotsystem_flag=true;
    for(var i=0,j=rectArray.length;i<j;i++){
        rectArray[i].attr({cursor:"pointer"});
        hotArray[i].attr({cursor:"pointer"});
        textArray[i].attr({cursor:"pointer"});
    }
    $('#ser_section').css('opacity',1);
    $('#ser_section').css('min-height','330px');
    $('#allHot').removeClass('hidecommon');
    $('#all_hot').addClass('hidecommon');
    $('#all_hot').animate({
        opacity:0
    },500);
    $('#ser_section').css("height",'calc(100% - 272px)');
    if($('.notify-list li').length >0){
        $('.notify-list').css('display',"block");
    };

    $('.nav_ser').delay("fast").fadeOut();
    $("#papersvg").removeClass("pointer");
    $(".all_hot_list_top_look").css("color",'rgb(74, 74, 74)');
    $(".hot_look_eye").css("background-image",'url("img/card-chart.png")');
    $(".hot_look_arrow").css("transform","rotate(0deg)");
    $(".hot_img_arrow").css("transform","rotate(0deg)");
    $(".hot_look_detail").css("background-image",'url("img/card-detail.png")');
    $(".all_hot_list_top_source").find('em').css("color",'#4a4a4a');
    $(".all_hot_list_top_look").find('em').css("color",'#4a4a4a');
})
var circleOption = {
    title: {
        text: '',
        left: 'center',
        top:15,
        textStyle:{
            color:'#4a4a4a',
            fontFamily:'微软雅黑',
            fontSize:'16',
            fontWeight:'400'
        }
    },
    backgroundColor:"#fff",
    tooltip: {
        trigger: 'item',
        formatter: function (obj) {
            return obj.name.split(" ")[0] + ": " + obj.percent + "%"
        },
//                backgroundColor:"rgba(255,255,255,0.5)",
//                borderColor:"#5ccfcd",
//                borderWidth:2,
        textStyle:{
            color:"#fff",
            fontFamily:"微软雅黑"
        }
    },
    legend: {
        orient: 'horizontal',
        bottom:20,
        data:[],
        textStyle:{
            fontFamily:"微软雅黑"
        },
        formatter: function (name) {
            return name.split(" ")[0];
        }
    },
    series: [
        {
            name:'',
            type:'pie',
            hoverAnimation:false,
            label: {
                normal: {
                    show: true,
                    position: 'center',
                    textStyle: {
                        fontSize: '0',
                        fontWeight: 'bold'
                    }
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '0',
                        fontWeight: 'bold'
                    }
                }
            },
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            data:[]
        }
    ]
}

    //切换效果
    $('.all_hot_list_bot:not(":first")').css('display','none');
    $(document).on('click','.all_hot_list_top',function(e){
    	e ? e.stopPropagation() : event.cancelBubble = true;
    	var $this = $(this);
    	var child = $this.children();
    	child[4].click();
    })
     //var hotTopicSimilar = $.templates(templates.design["tmplHotTopticSimilar"]); 
    	// var recordList2 = $.templates(templates.design["tmplRecordList2"]);
    $(document).on('click','.all_hot_list_top_source',function(e){
    	e ? e.stopPropagation() : event.cancelBubble = true;
    	$(this).parent().parent().parent().find('.all_hot_list_top_look').css('color','#4a4a4a');
    	$(this).parent().parent().parent().find('.all_hot_list_top_look').find('.hot_look_arrow').css("transform","rotate(0deg)");
    	//$('.all_hot_list_top_look').find('em').css("color",'#389b9f');
    	$(this).parent().parent().parent().find('.all_hot_list_top_look').find('.hot_look_eye').css('background-image','url(img/card-chart.png)');
    	$(this).parent().parent().parent().find('.hot_echart_list').addClass('hidecommon');
    	if($(this).parent().next().css('display') == 'block'){
    		$(this).parent().next().hide();
    		$(this).find(".hot_img_arrow").css("transform","rotate(0deg)");
    		$(this).find('.hot_look_detail').css('background-image','url(img/card-detail.png)');
    		$(this).find('em').css('color','#4a4a4a');
    	}else{
    		$(this).parent().parent().parent().find(".all_hot_list_bot").hide();
        	$(this).parent().next().show();
        	$(this).parent().parent().parent().find(".hot_img_arrow").css("transform","rotate(0deg)");
        	$(this).find(".hot_img_arrow").css("transform","rotate(180deg)");
        	$(this).parent().parent().parent().find(".hot_look_detail").css("background-image","url(img/card-detail.png)");
    		$(this).find('.hot_look_detail').css('background-image','url(img/card-detail-hover.png)');
    		$(this).parent().parent().parent().find('em').css("color","#4a4a4a");
    		$(this).find('em').css('color','#389b9f');
    	};  
    	var _this = $(this);
    	var Dataids = _this.data("id");
    	if(_this.parent().next().find(".bot_right .Prend").length <= 0){
       	 _this.parent().next().find(".bot_right").html("");
       	    $.ajax({
       	        type:"get",
       	        url:dataUrl.util.getHotTrend(Dataids),
       	        success:function(returndata){
       	            if(returndata && returndata.data.length > 0){
       	                var ageNewCon = $("<div class='Prend' style='display:inline-block;width:100%;height:100%;background:#fff;'></div>");
       	                _this.parent().next().find(".bot_right").append(ageNewCon);
       	                //console.log(_this.parent().next().find(".bot_right").height())
       	               // console.log(ageNewCon.get(0).width())
       	                var prendNewCharts = echarts.init(ageNewCon.get(0));
       	                var names = _.pluck(returndata.data, 'createDate');
       	                var vals = _.pluck(returndata.data, 'prevailingTrend');
       	                var option = {
       	                        backgroundColor:"#fff",
       	                        title: {
       	                            text: '热点热度走势',
       	                            left:'center',
       	                            top:15,
       	                            textStyle:{
       	                            color:'#4a4a4a',
       	                            fontFamily:'微软雅黑',
       	                            fontSize:'16',
       	                            fontWeight:'400'
       	                          }
       	                        },
       	                        color: ['#3398DB'],
       	                        tooltip : {
       	                            trigger: 'axis',
       	                            padding:[5,10],
       	                            formatter:function(obj){
       	                            	return '热度：'+obj.value+'</br>'+obj.name.substr(0,16)
       	                            },
       	                            axisPointer:{
       	                            	type:'cross'
       	                            }
       	                        },
       	                        grid: {
       	                            left: '3%',
       	                            right: '4%',
       	                            bottom: '40',
       	                            containLabel: true
       	                        },
       	                        dataZoom: [
       	                            {
       	                                show: true,
       	                                realtime: true,
       	                                start:100-(Math.floor(8/names.length*100)),
       	                                end: 100,
       	                                height:20,
       	                                fillerColor:'rgba(91, 206, 205,0.8)',
       	                                handleStyle: {
       	                                 color: '#00b1c5'
       	                                }
       	                            },
       	                            {
       	                                type: 'inside',
       	                                realtime: true,
       	                                start: 100-(Math.floor(8/names.length*100)),
       	                                end: 100,
       	                            }
       	                        ],
       	                        xAxis : [
       	                            {
       	                                type : 'category',
       	                                name : "时间",
       	                                nameLocation:"middle",
       	                                nameGap: -17,
       	                                scale:true,
       	                                axisTick: {
       	                                    alignWithLabel: true
       	                                },
       	                                splitLine:false,
       	                                axisLine:{
       	                                    lineStyle:{color:'#ccc'},
       	                                    onZero:true
       	                                },
       	                                axisTick:{
       	                                    show:true
       	                                },
       	                                data : names.map(function (str) {
       	                                    return str.replace(' ', '\n')
       	                                })
       	                            }
       	                        ],
       	                        yAxis : [
       	                            {
       	                                type : 'value',
       	                                nameGap: 0,
       	                                splitLine:false,
       	                                axisLine:{
       	                                    lineStyle:{color:'#ccc'}
       	                                },
       	                                axisLabel : {
       	                                    formatter: '{value}'
       	                                },
       	                                axisTick:{
       	                                    show:false
       	                                }
       	                            }
       	                        ],
       	                        series: [
       	                            {
       	                                name:'时间',
       	                                type:'line',
       	                                lineStyle: {
       	                                    normal: {
       	                                        width: 1
       	                                    }
       	                                },
       	                                symbol:'circle',
       	                                symbolSize:6,
       	                                itemStyle:{
       	                                	normal:{
       	                                		color:'#00b1c5'
       	                                	}
       	                                },
       	                                areaStyle:{
       	                                	normal:{
       	                                		color:'rgba(91, 206, 205,0.8)'
       	                                	}
       	                                },
       	                                data:vals
       	                            }
       	                        ]
       	                    };
       	                prendNewCharts.setOption(option);
       	                window.onresize=prendNewCharts.resize;
       	            }else{
       	                var ageNewCon = $("<div class=Prend style='position:relative;display:inline-block;width:100%;height:100%;background:#fff;text-align:center'></div>");
       	                var a = $("<span style='position:absolute;display:inline-block;top:15px;width:97px;color:#4a4a4a;font-family:微软雅黑;font-size:16px;left:50%;transform:translate(-50%,0);font-weight:400;'>热点热度走势</span>")
       	                ageNewCon.append(a);
       	                ageNewCon.append($("<span style=position:absolute;color:#000;display:inline-block;top:50%;font-size:14px;width:56px;left:50%;transform:translate(-50%,-50%);>暂无数据</span>"))
       	                _this.parent().next().find(".bot_right").append(ageNewCon);
       	            }
       	        }
       	    });
    	 };
    	
    	if(_this.parent().next().find(".hot_near_con .pnlNear").length <= 0){
    	    $.ajax({
                type:"get",
                url:dataUrl.util.getHotNearTrend(Dataids),
                success:function(returndata){
                    if(returndata.length == 0){
                        _this.parent().next().find(".hot_near_con").css("display","none");
                        _this.parent().next().find(".near_error").css("display","block");
                        _this.parent().next().find(".hot_near_refresh").css("display","none");
                    }else{
                        _this.parent().next().find(".hot_near_con").css("display","block");
                        _this.parent().next().find(".near_error").css("display","none");
                        _this.parent().next().find(".hot_near_refresh").css("display","block");
                        $.each(returndata,function(idx,item){
                            var typeArr=[];
                            if(item.eventClass){
                                typeArr=item.eventClass.split(",");
                            }
                            item.eventClass=typeArr
                        })
                        var $item = $.templates(templates.design["tmplHotNear"]);
                        _this.parent().next().find(".hot_near_con").html($item.render({data:returndata}));
                    }
                }
    	    });
 	    }
	    
    });  
    //用户画像颜色
	var color = ['#1f81c5','#15a9e0','#49c4d1','#3cbca0','#8eca6d','#54e6a0'];
    $(document).on('click','.all_hot_list_top_look',function(e){
    	e ? e.stopPropagation() : event.cancelBubble = true;
    	var $this = $(this)
    	var index = $this.attr("data-id");
    	$(this).parent().parent().parent().find('.all_hot_list_bot').css('display','none');
    	$(this).parent().parent().parent().find('.hot_img_arrow').css("transform","rotate(0deg)");
    	$(this).parent().parent().parent().find('.all_hot_list_top_source').find('em').css('color','#4a4a4a');
    	$(this).parent().parent().parent().find('.all_hot_list_top_source').find('.hot_look_detail').css('background-image','url(img/card-detail.png)');
    	if($(this).parent().next().next().is('.hidecommon')){
    	    $(this).parent().parent().parent().find('.all_hot_list_top_look').find('em').css('color','#4a4a4a');
    		$(this).find('em').css('color','#389b9f');
//    		$(this).parent().parent().parent().find('.all_hot_list_top_look em').text('查看画像');
//    		$(this).find('em').text('收起画像');
        $(this).parent().parent().parent().find(".hot_look_eye").css("background-image","url(img/card-chart.png)");
        $(this).find('.hot_look_eye').css('background-image','url(img/card-chart-hover.png)');
        $(this).parent().parent().parent().find(".hot_echart_list").addClass('hidecommon');
        $(this).parent().next().next().removeClass('hidecommon');
        $(this).parent().parent().parent().find(".hot_look_arrow").css("transform","rotate(0deg)");
        $(this).find(".hot_look_arrow").css("transform","rotate(180deg)");
        var rectTop = $(this).parent().next().next().get(0).getBoundingClientRect();
        if(rectTop.top < 0){
            var a = $(this).parent().next().offset().top;
            a -= rectTop.top + 450;
            $("html,body").animate({scrollTop:a},"slow");
        }

    }else{
        $(this).parent().next().next().addClass('hidecommon');
        $(this).parent().next().next().addClass('hidecommon');
        $(this).find(".hot_look_arrow").css("transform","rotate(0deg)");
        $(this).parent().parent().parent().find('.all_hot_list_top_look').find('em').css('color','#4a4a4a');
//    		$(this).find('em').text('查看画像');
    		$(this).find('.hot_look_eye').css('background-image','url(img/card-chart.png)');
    	};
    	var $this = $(this);
    	if($this.parent().parent().find(".Personas").length > 0){
			return;
		}
    	$this.parent().parent().find(".hot_echart_list").append($(".loadingcon").css("display","inline-block"));
    	$.ajax({
    		type:"get",
    		url:dataUrl.util.getPercentData($this.attr("data-id")),
    		success:function(data){
    			$(".loadingcon").css("display","none")
    			var data = data.data;
    			var str = "";
    			if(data == null){
    				str = "<p class='Personas' style='position:relative;font-size:16px;color:#000;text-align:center;top:50%;left:50%;transform:translate(-50%,-50%)'>获取数据错误</p>";
    					$this.parent().parent().find(".hot_echart_list").append($(str));
    					return;
    				}
    				var dataLen = data.gender.length + data.interest.length + data.education.length + data.area.length + data.age.length;
    				if(dataLen < 1){
    					$this.parent().parent().find(".hot_echart_list").empty();
    					str = "<p class='Personas' style='position:absolute;font-size:16px;text-align:center;color:#000;top:50%;left:50%;transform:translate(-50%,-50%)'>暂无热点受众画像</p>";
    					$this.parent().parent().find(".hot_echart_list").append($(str));
    					return;
    				}
    				$(".newPicCon").show();
    			//受众年龄画像
    			if(data && data.gender.length > 0){
    				var ele = $this.parent().parent().find(".hot_echart_list").find(".sexCon").get(0);
    				$this.parent().parent().find(".hot_echart_list").find(".newPicCon").addClass('Personas');
    				var genderCharts = echarts.init(ele);
    				var genderOption = {
    				        title: {
    				            text: '受众性别分布',
    				            left: 'center',
    				            top:15,
    				            textStyle:{
    			                	color:'#4a4a4a',
    			                	fontFamily:'微软雅黑',
    			                	fontSize:'16',
    			                	fontWeight:'400'
    			                }
    				        },
    				        color:color,
            			backgroundColor:"#fff",
    				    tooltip : {
    				        trigger: 'axis',
    				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
    				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
    				        },
    				        textStyle:{
			                	color:"#fff",
			                	fontFamily:"微软雅黑"
			                }
    				    },
    				    legend: {
    				        data:[],
    				        bottom:20,
			                textStyle:{
			                	fontFamily:"微软雅黑"
			                }
    				    },
    				    calculable : true,
    				    label:{
    				        normal:{
    				            testStyle:{                
    				            }
    				        }
    				    },
    				    xAxis : [
    				        {
    				            type : 'value',
    				            show:false
    				        }
    				    ],
    				    itemStyle:{
    				        normal:{
    				            barBorderRadius:6
    				        }
    				    },
    				    barGap:0,
    				    barCategoryGap:0,
    				    yAxis : [
    				        {
    				            type : 'category',
    				            data : ['性别比例'],
    				            show:false
    				        }
    				    ],
    				    series : [

    				    ]
    				};
    				$.each(data.gender,function(index,item){
    					genderOption.legend.data.push(item.name)
    					var tempObj = {
    						name:item.name,
    						type:'bar',
    						stack:'性别',
    						itemStyle : { normal: {label : {show: true, position: 'inside',formatter:function(obj){ var a = obj.value;return a+'%' }}}},
				            data:[item.value.toFixed(2)]
    					}
    					genderOption.series.push(tempObj)				
       				})      				

    				genderCharts.setOption(genderOption);
    				window.onresize = genderCharts.resize;
    			}else{
        			var genderCon = $this.parent().parent().find(".hot_echart_list").find(".sexCon");
        			var a = $("<span style='position:absolute;display:inline-block;top:15px;width:97px;color:#4a4a4a;font-family:微软雅黑;font-size:16px;left:50%;transform:translate(-50%,0);font-weight:400;'>受众性别分布</span>")
        			genderCon.append(a);
                    genderCon.append($("<span style=position:absolute;color:#000;display:inline-block;top:50%;font-size:14px;width:56px;left:50%;transform:translate(-50%,-50%);>暂无数据</span>"))
//        			$this.parent().parent().find(".hot_echart_list").append(genderCon);
    			}
    			if(data && data.education.length > 0){
    				//受众学历分布
//    				var educationCon = $("<div  class='Personas' style='display:inline-block;width:14%;height:279px;;background:#fff;'></div>");
    				var ele = $this.parent().parent().find(".hot_echart_list").find('.eduCon').get(0);
    				$this.parent().parent().find(".hot_echart_list").find(".newPicCon").addClass('Personas');
    				var educationCharts = echarts.init(ele);
    				var educationOption = $.extend(true,{},circleOption);
    				educationOption.title.text = "受众学历分布";
    				educationOption.color = ['#1f81c5','#15a9e0','#49c4d1','#3cbca0','#8eca6d','#54e6a0'];
    				var educationMax = 0;
    				var educationMaxIndex = 0;
    				educationOption.series[0].name = "学历分布";
    				$.each(data.education,function(index,item){
    					educationOption.legend.data.push({name:item.name+" "+item.value.toFixed(2)+"%",icon:"circle"});
    					var tempItem = JSON.stringify(item);
    					tempItem = JSON.parse(tempItem);
    					tempItem.name = item.name+" "+item.value.toFixed(2)+"%";
    					educationOption.series[0].data.push(tempItem);
    					if(educationMax > item.value){    						
    					}else{
    						educationMax = item.value;
    						educationMaxIndex = index;
    					}
    				})
//    				educationOption.legend.data.push(data.education[0].name+" "+data.education[0].value+"%");
//    				educationOption.legend.data.push(data.education[1].name+" "+data.education[1].value+"%");
//    				educationOption.series[0].name = "性别";
//    				var educationJson0 = JSON.stringify(data.education[0]);
//    				educationOption.series[0].data.push(JSON.parse(educationJson0));
//    				var educationJson1 = JSON.stringify(data.education[1]);
//    				educationOption.series[0].data.push(JSON.parse(educationJson1));
//    				genderOption.series[0].data[0].name = data.gender[0].name+" "+data.gender[0].value+"%";
//    				genderOption.series[0].data[1].name = data.gender[1].name+" "+data.gender[1].value+"%";
                var label =  {
                    normal: {
                        show: true,
                        position: 'center',
                        textStyle: {
                            color:"#4a4a4a",
                            fontSize: '14',
                            fontWeight: '400',
                            fontFamily:'微软雅黑'
                        }
                    }
                }
                educationOption.series[0].data[educationMaxIndex].label = label;
//    				if(data.gender[0].value > data.gender[1].value){
//    					genderOption.series[0].data[0].label = label;
//    				}else{
//    					genderOption.series[0].data[1].label = label;
//    				}
    				if(data.education.length>2){
    					educationOption.legend.bottom = 8;
    				}
    				educationCharts.setOption(educationOption);
    				window.onresize=educationCharts.resize;
    			}else{
    				var educationCon = $this.parent().parent().find(".hot_echart_list").find('.eduCon');
    				var a = $("<span style='position:absolute;display:inline-block;top:15px;width:97px;color:#4a4a4a;left:50%;transform:translate(-50%,0);font-family:微软雅黑;font-size:16px;font-weight:400;'>受众学历分布</span>")
    				educationCon.append(a);
                    educationCon.append($("<span style=position:absolute;color:#000;display:inline-block;top:50%;font-size:14px;width:56px;left:50%;transform:translate(-50%,-50%);>暂无数据</span>"))
//    			    $this.parent().parent().find(".hot_echart_list").append(educationCon);
    			}
    				//兴趣雷达图
    				if(data && data.userInterest.length > 0){
//						var interestCon = $("<div class='Personas' style='display:inline-block;width:25%;height:279px;background:#fff;'></div>");
	    				var ele = $this.parent().parent().find(".hot_echart_list").find(".chartsRightCon").get(0);
	    				$this.parent().parent().find(".hot_echart_list").find(".newPicCon").addClass('Personas');
	    				$this.parent().parent().find(".hot_echart_list").find(".tgiInt").css("display","inline-block");
	    				var interestCharts = echarts.init(ele);
	    				var interestvals = [];
	    	        	var interestnames = [];
	    	        	var interest = data.userInterest;
	    	        	var max = 0;
	    	        	var yData = [];
	    	        	var persentData = [];
	    	        	var tgiData = [];
	    	        	var strongData = [];
	    	        	$.each(interest,function(i,item){
	    	        		if(max < item.value){
	    	        			max = item.value
	    	        		}
	    	        	});
	    	        	$.each(interest,function(i,item){
	    	        		interestvals.push(item.value);
	    	        		interestnames.push({name:item.name,max:max});
	    	        	});
	    	        	$.each(interest,function(i,item){
	    	        		yData.push(item.className);
	    	        		persentData.push((item.percentage*100).toFixed(2));
	    	        		tgiData.push(item.tgi);
	    	        		strongData.push(item.interestStrength);
	    	        	});
						var interestOption = {
							color:['#ccc'],
	    	        	    title: {
	    	        	        text: '受众兴趣偏好',
	    	        	        left:'center',
	    	        	        top:15,
	    	        	        textStyle:{
	    	                    	color:'#4a4a4a',
	    	                    	fontFamily:'微软雅黑',
	    	                    	fontSize:'16',
	    	                    	fontWeight:'400'
	    	                    }
	    	        	    },
	    	        	    color:['#1f81c5','#15a9e0','#3cbca0','#8eca6d','#54e6a0'],
	    	        	    backgroundColor:"#fff",
						    tooltip: {
						        trigger: 'axis',
						        axisPointer: {
									type: 'shadow'
								},
						        textStyle:{
	    	        	    		fontFamily:"微软雅黑"
	    	        	    	},
	    	        	    	formatter:function(obj){
	    	        	    		var itemName;
	    	        	    		var str="";
	    	        	    		$.each(obj,function(index,item){
	    	        	    			if(index == 0){
	    	        	    				itemName = item.name;
	    	        	    				str += item.seriesName+':'+item.data+'%'+' <br>'	
	    	        	    			}
	    	        	    			if(index == 1){
	    	        	    				str += item.seriesName+':'+item.data+' <br>'	
	    	        	    			}
	    	        	    			if(index == 2){
	    	        	    				str += item.seriesName+':'+item.data	
	    	        	    			}
	    	        	    		})
	    	        	    		str = ' ' + itemName + '<br>' + str;
	    	        	    		return str;
	    	        	    	}
						    },
						    dataZoom: [
   	                            {
   	                                show: true,
   	                                realtime: true,
   	                                start:0,
   	                                end: 100,
   	                                width:20,
   	                                height:'390px',
   	                                right:20,
   	                                orient:'vertical',
   	                                fillerColor:'rgba(91, 206, 205,0.8)',
   	                                handleStyle: {
   	                                 color: '#00b1c5'
   	                                }
   	                            }
   	                        ],
						    legend: {
						        data:['占比','TGI','强度'],
						        bottom:15,
						        left:30
						    },
						    yAxis:[{
						            type: 'category',
						            data: yData
						        }],
						    xAxis: [
						        {
						            type: 'value',
						            name: '比例',
						            axisLabel: {
						                formatter: '{value}'
						            }
						        },
						        {
						            type: 'value',
						            name: 'TGI',
						            show:false,
						            axisLabel: {
						                formatter: '{value}'
						            }
						        }
						    ],
						    series: [
						        {
						            name:'占比',
						            type:'bar',
						            xAxisIndex: 1,
						            animation:false,
						            data:persentData
						        },
						        {
						            name:'TGI',
						            type:'line',
						            smooth:true,
						            animation:false,
						            data:tgiData
						        },
						        {
						            name:'强度',
						            type:'line',
						            animation:false,
						            smooth:true,
						            data:strongData
						        }
						    ]
						};
						interestCharts.setOption(interestOption)

// 	    	        	interestCharts.setOption({
// 	    	        		color:['#ccc'],
// 	    	        	    title: {
// 	    	        	        text: '受众兴趣偏好',
// 	    	        	        left:'center',
// 	    	        	        top:15,
// 	    	        	        textStyle:{
// 	    	                    	color:'#4a4a4a',
// 	    	                    	fontFamily:'微软雅黑',
// 	    	                    	fontSize:'16',
// 	    	                    	fontWeight:'400'
// 	    	                    }
// 	    	        	    },
// 	    	        	    backgroundColor:"#fff",
// 	    	        	    tooltip: {
// 	    	        	    	formatter:function(a,b){
// 	    	        	    		var array = [];
// 	    	        	    		$.each(interest,function(i,item){
// 	    	        	    			if(i<1){
// 	    	        	    				array.push(item.name+":"+item.value.toFixed(2))
// 	    	        	    			}else{
// 	    	        	    				array.push("<br>"+item.name+":"+item.value.toFixed(2))
// 	    	        	    			}	    		    	        		
// 	    		    	        	});
// 	    	        	    		return array.toString();
// 	    	        	    	},
// 	    	        	    	textStyle:{
// 	    	        	    		fontFamily:"微软雅黑"
// 	    	        	    	}
// 	    	        	    },
	    	        	    
// 	    	        	    radar: {
// 	    	        	    	radius:'60%',
// 	    	        	    	center:['50%','57.5%'],
// 	    	        	    	splitArea: {
// 	    	        	            areaStyle: {
// 	    	        	                color: ['#fff', '#fff', '#fff', '#fff']
// 	    	        	            }
// 	    	        	        },
// 	    	        	        name:{
// 	    	        	        	textStyle:{
// 	    	        	        		fontFamily:"微软雅黑"
// 	    	        	        	}
// 	    	        	        },
// 	    	        	        nameGap:10,	    	        	        
// 	    	        	        // shape: 'circle',
// 	    	        	        indicator: interestnames,
// 	    	        	        splitLine: {
// 	        	                    lineStyle: {
// 	        	                        color: '#ccc'
// 	        	                    }
// 	        	                },
// 	        	                axisLine: {
// 	        	                    show:false
// 	        	                },
// 	    	        	    },
// 	    	        	    series: [{
// 	    	        	        type: 'radar',
// 	    	        	        label:{
// 	    	        	        	normal:{
// //	    	        	        		formatter:function(obj){
// //	    	    	        	    		console.log(obj)
// //	    	    	        	    		var array = [];
// //	    	    	        	    		$.each(obj.value,function(index,item){
// //	    	    	        	    			array.push(item.toFixed(2)+"%");
// //	    	    	        	    		})
// //	    	    	        	    		return array.toString();
// //	    	    	        	    	}
// 	    	        	        	}
// 	    	        	        },
// 	    	        	        data : [
// 	    	        	            {
// 	    	        	                value : interestvals,
// 	    	        	                itemStyle: {normal: {areaStyle: {type: 'default',color:'#5ccfcd'}}},
// 	//    	        	                areaStyle: {
// 	//    	                                normal: {
// 	//    	                                    color: '#fff'
// 	//    	                                }
// 	//    	                            }
// 	    	        	            }
// 	    	        	        ]
// 	    	        	    }]
// 	    	        	});
	    	        	window.onresize=interestCharts.resize;
    				}else{
            			var interestCon = $this.parent().parent().find(".hot_echart_list").find(".chartsRightCon");
            			var a = $("<span style='position:absolute;display:inline-block;top:15px;width:97px;color:#4a4a4a;font-family:微软雅黑;left:50%;transform:translate(-50%,0);font-size:16px;font-weight:400;'>受众兴趣偏好</span>")
            			interestCon.append(a);
            			interestCon.append($("<span style=position:absolute;color:#000;display:inline-block;top:50%;font-size:14px;width:56px;left:50%;transform:translate(-50%,-50%);>暂无数据</span>"))
//            			$this.parent().parent().find(".hot_echart_list").append(interestCon);
            			
        			}
    	        	
    	        	//年龄柱状图
    	        if(data && data.age.length > 0){
//    	        	var ageNewCon = $("<div class='Personas' style='display:inline-block;width:17%;height:279px;background:#fff;'></div>");
    	        	var ele = $this.parent().parent().find(".hot_echart_list").find(".ageCon").get(0);
    	        	$this.parent().parent().find(".hot_echart_list").find(".newPicCon").addClass('Personas');
    	        	var ageNewCharts = echarts.init(ele);
    	        	var ageNames = [];
    	        	var ageVals = [];
    	        	var age =data.age;
    	        	var trueData = [];
    	        	$.each(age,function(i,item){
    	        		var tempArray = [];
    	        		
    	        		if(item.name - 0 < 0){
    	        			tempArray.push(0);
    	        		}else if(item.name - 0 > 100){
    	        			tempArray.push(100);
    	        		}else{
    	        			tempArray.push(item.name - 0);
    	        		}
    	        		tempArray.push(item.value);
    	        		if((tempArray[0] - 0) > 12 && (tempArray[0] - 0)< 60){
        	        		trueData.push(tempArray);    	        			
    	        		}
    	        	});
    	        	trueData.sort(function(x,y){return x[0] - y[0]})
    	        	ageNewCharts.setOption({
    	        			backgroundColor:"#fff",
	    	        		title: {
	     	        	        text: '受众年龄分布',
	     	        	        left:'center',
	     	        	        top:15,
	     	        	        textStyle:{
	     	                  	color:'#4a4a4a',
	     	                  	fontFamily:'微软雅黑',
	     	                  	fontSize:'16',
	     	                  	fontWeight:'400'
	     	                  }
	     	        	    },
	     	        	   color:color,
    	            	    tooltip : {
    	            	        trigger: 'axis',
    	            	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
    	            	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
    	            	        },
    	            	        formatter:function(obj){
    	            	        	return obj[0].data[0] + "岁:"+obj[0].data[1].toFixed(2)+"%"
    	            	        },
    	            	        textStyle:{
        	        	        	fontFamily:"微软雅黑"
        	        	        }
//    	            	        formatter:'{c[0]}'
    	            	    },
    	            	    grid: {
    	            	        left: '3%',
    	            	        right: '4%',
    	            	        bottom: '3%',
    	            	        containLabel: true
    	            	    },
    	            	    xAxis : [
    	            	        {
    	            	            type : 'value',
    	            	            name : "年龄",
    	            	            min:12,
    	            	            max:60,
    	            	            nameLocation:"middle",
    	            	            nameGap: -17,
    	            	            scale:true,
    	            	            axisTick: {
    	            	                alignWithLabel: true
    	            	            },
    	            	            splitLine:false,
    	            	            axisLine:{
    	            	            	lineStyle:{color:'#ccc'}
    	            	            },
    	            	            axisTick:{
    	            	            	show:false
    	            	            }
    	            	        }
    	            	    ],
    	            	    yAxis : [
    	            	        {
    	            	            type : 'value',
    	            	            nameGap: 0,
    	            	            top:35,
    	            	            splitLine:false,
    	            	            axisLine:{
    	            	            	lineStyle:{color:'#ccc'}
    	            	            },
    	            	            axisLabel : {
    	            	                formatter: '{value}%'
    	            	            },
    	            	            axisTick:{
    	            	            	show:false
    	            	            }
    	            	        }
    	            	    ],
    	            	    series : [
    	            	        {
    	            	            name:'年龄',
    	            	            type:'line',
    	            	            data:trueData
    	            	        }
    	            	    ]
    	        	});
    	        	window.onresize=ageNewCharts.resize;
    	        }else{
        			var ageNewCon = $this.parent().parent().find(".hot_echart_list").find(".ageCon");
        			var a = $("<span style='position:absolute;display:inline-block;top:15px;width:97px;color:#4a4a4a;font-family:微软雅黑;font-size:16px;left:50%;transform:translate(-50%,0);font-weight:400;'>受众年龄分布</span>")
        			ageNewCon.append(a);
                    ageNewCon.append($("<span style=position:absolute;color:#000;display:inline-block;top:50%;font-size:14px;width:56px;left:50%;transform:translate(-50%,-50%);>暂无数据</span>"))
//        			$this.parent().parent().find(".hot_echart_list").append(ageNewCon);
    			}
    	        	//地图
    	        if(data && data.area.length > 0){
//    	        	var mapCon = $("<div class='Personas' style='margin-right:0;display:inline-block;width:28%;height:279px;background:#fff;'></div>");
    	        	
    	        	var ele = $this.parent().parent().find(".hot_echart_list").find(".areaCon").get(0);
    	        	$this.parent().parent().find(".hot_echart_list").find(".newPicCon").addClass('Personas');
    	        	var mapCharts = echarts.init(ele);
    	        	var mapNames = [];
    	        	var mapVals = [];
    	        	var mapChina = data.area;
    	        	var map = {
    	        			安徽省:'安徽',
    	        			澳门特别行政区:'澳门',
    	        			北京市:'北京',
    	        			福建省:'福建',
    	        			甘肃省:'甘肃',
    	        			广东省:'广东',
    	        			广西壮族自治区:'广西',
    	        			贵州省:'贵州',
    	        			海南省:'海南',
    	        			河北省:'河北',
    	        			河南省:'河南',
    	        			黑龙江省:'黑龙江',
    	        			湖北省:'湖北',
    	        			湖南省:'湖南',
    	        			吉林省:'吉林',
    	        			江苏省:'江苏',
    	        			江西省:'江西',
    	        			辽宁省:'辽宁',
    	        			内蒙古自治区:'内蒙古',
    	        			宁夏回族自治区:'宁夏',
    	        			青海省:'青海',
    	        			山东省:'山东',
    	        			山西省:'山西',
    	        			陕西省:'陕西',
    	        			上海市:'上海',
    	        			四川省:'四川',
    	        			台湾省:'台湾',
    	        			天津市:'天津',
    	        			西藏自治区:'西藏',
    	        			香港特别行政区:'香港',
    	        			新疆维吾尔自治区:'新疆',
    	        			云南省:'云南',
    	        			浙江省:'浙江',
    	        			重庆市:'重庆',
    	        	};
    	        	var mapMax = 0;
    	        	var mapMin = 100;
    	        	$.each(mapChina,function(i,item){
    	        		mapVals.push({name:map[item.name],value:item.value});
    	        		if(mapMax < item.value){
    	        			mapMax = item.value
    	        		}
    	        		if(mapMin > item.value){
    	        			mapMin = item.value
    	        		}
    	        	});
    	        	mapCharts.setOption({
    	        		backgroundColor:"#fff",
    	        	    title : {
    	        	    	top:15,
    	        	        text: '受众地区分布',
    	        	        left: 'center',
    	        	        textStyle:{
    	                    	color:'#4a4a4a',
    	                    	fontFamily:'微软雅黑',
    	                    	fontSize:'16',
    	                    	fontWeight:'400'
    	                    }
    	        	    },
    	        	    color:color,
//    	        	    legend:{
//    	        	    	orient: 'vertical',
//    	        	    	right: 'right',
//    	        	    	bottom:25,
//    	        	    	data:['占比']
//    	        	    },
    	        	    tooltip : {
    	        	        trigger: 'item',
    	        	        formatter:function(obj){
    	        	        	var a = "";
    	        	        	if(obj.value){
    	        	        		a += obj.value.toFixed(2) + "%";
    	        	        	}
    	        	        	if(isNaN(obj.value)){
    	        	        		return obj.name + ":" + "0";
    	        	        	}
    	        	        	return "占比<br/>"+obj.name + ":" + a;
    	        	        },
    	        	        textStyle:{
    	        	        	fontFamily:"微软雅黑"
    	        	        }
    	        	    },
//    	        	    legend: {
//    	        	        orient: 'vertical',
//    	        	        left: 'left',
//    	        	    },
    	        	    visualMap: {
    	        	    	show:false,
    	        	    	min:0,
    	        	    	max:mapMax,
    	                    inRange: {
    	                        color: ['#6ab6e0','#1d73a2']
    	                    },
    	                    left:'right'
    	                },
    	        	    toolbox: {
    	        	        show: true,
    	        	        orient : 'vertical',
    	        	        left: 'right',
    	        	        top: 'center',
    	        	    },
    	        	    series : [
    	        	        {
    	        	        	name: '占比',
    	        	            type: 'map',
    	        	            mapType: 'china',
    	        	            roam: false,
    	        	            top:35,
    	        	            scaleLimit:{
    	        	            	min:1.1
    	        	            },
    	        	            label: {
    	        	                normal: {
    	        	                    show: false
    	        	                },
    	        	                emphasis: {
    	        	                    show: false
    	        	                }
    	        	            },
//    	        	           
    	        	            itemStyle: {
    	                            normal: {
//    	                                borderWidth: 2,
                                    borderColor: '#fff',
                                    areaColor: '#dbedf7',
                                    label: {
                                        show: false
                                    }
                                },
                                emphasis: { // 选中样式
                                    show: false,
//    	                                borderWidth: 1,
//    	                                borderColor: '#000',
    	                                areaColor: '#166591',
    	                                //color: '#f00',
    	                                label: {
    	                                    textStyle: {
    	                                    	font_size:'0',
    	                                    	show:false,
    	                                        color: '#fff'
    	                                    }
    	                                }
    	                            }
    	                        },
    	        	            data:mapVals
    	        	        },
    	        	    
    	        	       
    	        	    ]
    	        	})
    	        	//mapOption.series[0].data = mapVals;
    	        	//mapCharts.setOption(mapOption);
    	        	window.onresize=mapCharts.resize;
    			}else{
        			var mapCon = $this.parent().parent().find(".hot_echart_list").find(".areaCon");
        			var a = $("<span style='position:absolute;display:inline-block;top:15px;width:97px;color:#4a4a4a;font-family:微软雅黑;font-size:16px;left:50%;transform:translate(-50%,0);font-weight:400;'>受众地区分布</span>")
        			mapCon.append(a);
                    mapCon.append($("<span style=position:absolute;display:inline-block;color:#000;top:50%;font-size:14px;width:56px;left:50%;transform:translate(-50%,-50%);>暂无数据</span>"))
//        			$this.parent().parent().find(".hot_echart_list").append(mapCon);
    			}
    	        //地图TGI
    	        if(data && data.areaTgi.length > 0){
    	        	var ele = $this.parent().parent().find(".hot_echart_list").find(".areaTGICon").get(0);
    	        	var mapTgiCharts = echarts.init(ele);
    	        	var mapNames = [];
    	        	var mapVals = [];
    	        	var mapChina = data.areaTgi;
    	        	var map = {
    	        			安徽省:'安徽',
    	        			澳门特别行政区:'澳门',
    	        			北京市:'北京',
    	        			福建省:'福建',
    	        			甘肃省:'甘肃',
    	        			广东省:'广东',
    	        			广西壮族自治区:'广西',
    	        			贵州省:'贵州',
    	        			海南省:'海南',
    	        			河北省:'河北',
    	        			河南省:'河南',
    	        			黑龙江省:'黑龙江',
    	        			湖北省:'湖北',
    	        			湖南省:'湖南',
    	        			吉林省:'吉林',
    	        			江苏省:'江苏',
    	        			江西省:'江西',
    	        			辽宁省:'辽宁',
    	        			内蒙古自治区:'内蒙古',
    	        			宁夏回族自治区:'宁夏',
    	        			青海省:'青海',
    	        			山东省:'山东',
    	        			山西省:'山西',
    	        			陕西省:'陕西',
    	        			上海市:'上海',
    	        			四川省:'四川',
    	        			台湾省:'台湾',
    	        			天津市:'天津',
    	        			西藏自治区:'西藏',
    	        			香港特别行政区:'香港',
    	        			新疆维吾尔自治区:'新疆',
    	        			云南省:'云南',
    	        			浙江省:'浙江',
    	        			重庆市:'重庆',
    	        	};
    	        	var mapMax = 0;
    	        	var mapMin = 100;
    	        	$.each(mapChina,function(i,item){
    	        		mapVals.push({name:map[item.name],value:item.value});
    	        		if(mapMax < item.value){
    	        			mapMax = item.value
    	        		}
    	        		if(mapMin > item.value){
    	        			mapMin = item.value
    	        		}
    	        	});
    	        	mapTgiCharts.setOption({
    	        		backgroundColor:"#fff",
    	        	    title : {
    	        	    	top:15,
    	        	        text: '受众地区分布',
    	        	        left: 'center',
    	        	        textStyle:{
    	                    	color:'#4a4a4a',
    	                    	fontFamily:'微软雅黑',
    	                    	fontSize:'16',
    	                    	fontWeight:'400'
    	                    }
    	        	    },
    	        	    color:color,
//    	        	    legend:{
//    	        	    	orient: 'vertical',
//    	        	    	right: 'right',
//    	        	    	bottom:25,
//    	        	    	data:['占比']
//    	        	    },
    	        	    tooltip : {
    	        	        trigger: 'item',
    	        	        formatter:function(obj){
    	        	        	return "TGI<br/>"+obj.name + ":" + obj.value;
    	        	        },
    	        	        textStyle:{
    	        	        	fontFamily:"微软雅黑"
    	        	        }
    	        	    },
//    	        	    legend: {
//    	        	        orient: 'vertical',
//    	        	        left: 'left',
//    	        	    },
    	        	    visualMap: {
    	        	    	show:false,
    	        	    	min:0,
    	        	    	max:mapMax,
    	                    inRange: {
    	                        color: ['#f9a46b','#c7602a']
    	                    },
    	                    left:'right'
    	                },
    	        	    toolbox: {
    	        	        show: true,
    	        	        orient : 'vertical',
    	        	        left: 'right',
    	        	        top: 'center',
    	        	    },
    	        	    series : [
    	        	        {
    	        	        	name: '占比',
    	        	            type: 'map',
    	        	            mapType: 'china',
    	        	            roam: false,
    	        	            top:35,
    	        	            scaleLimit:{
    	        	            	min:1.1
    	        	            },
    	        	            label: {
    	        	                normal: {
    	        	                    show: false
    	        	                },
    	        	                emphasis: {
    	        	                    show: false
    	        	                }
    	        	            },
//    	        	           
    	        	            itemStyle: {
    	                            normal: {
//    	                                borderWidth: 2,
                                    borderColor: '#fff',
                                    areaColor: '#dbedf7',
                                    label: {
                                        show: false
                                    }
                                },
                                emphasis: { // 选中样式
                                    show: false,
//    	                                borderWidth: 1,
//    	                                borderColor: '#000',
    	                                areaColor: '#c7602a',
    	                                //color: '#f00',
    	                                label: {
    	                                    textStyle: {
    	                                    	font_size:'0',
    	                                    	show:false,
    	                                        color: '#fff'
    	                                    }
    	                                }
    	                            }
    	                        },
    	        	            data:mapVals
    	        	        },
    	        	    
    	        	       
    	        	    ]
    	        	})
    	        	window.onresize=mapTgiCharts.resize;
    			}else{
        			var mapCon = $this.parent().parent().find(".hot_echart_list").find(".areaTGICon");
        			var a = $("<span style='position:absolute;display:inline-block;top:15px;width:97px;color:#4a4a4a;font-family:微软雅黑;font-size:16px;left:50%;transform:translate(-50%,0);font-weight:400;'>受众地区分布</span>")
        			mapCon.append(a);
                    mapCon.append($("<span style=position:absolute;display:inline-block;color:#000;top:50%;font-size:14px;width:56px;left:50%;transform:translate(-50%,-50%);>暂无数据</span>"))
//        			$this.parent().parent().find(".hot_echart_list").append(mapCon);
    			}
    	        $this.parent().parent().find(".hot_echart_list").find(".areaTGICon").hide();
    			
    		},
    		error:function(){
    			console.log("获取受众画像失败");
    		}
    	})
    });
    var hotIdArray=[];
    var imageArray=[];
    var titleArray=[];
    var scoreArray=[];
    var introArray=[];
    var formArray=[];
    var tagArray={};
//获取渲染全部热点
var hotList2 = $.templates(templates.design["tmplAllHotList"]);
function getAllHot(){
    $.ajax({
        type: "get",
        contentType: 'application/json',
        dataType: "json",
        url: dataUrl.util.getHotTopic(100),
        success: function(returnData) {
            if(returnData.error.code != 0 || returnData.data == null || returnData.data.length == 0){
                console.log("获取全部热点异常");
                return
            }
            
            $(".all_hot_list").html(hotList2.render(returnData));

            $('.all_hot_list_top_source:first').find('.hot_img_arrow').css('transform','rotate(180deg)');
            $('.all_hot_list_top_source:first').find('.hot_look_detail').css("background-image","url(img/card-detail-hover.png)");
        },
        error: function() {
            console.log('获取热点失败');
        }
    });
}
getTenHot();
function getTenHot(){
    $.ajax({
        type: "get",
        contentType: 'application/json',
        dataType: "json",
        url: dataUrl.util.getTenHot(),
        success: function(returnData) {
            if(returnData.error.code != 0 || returnData.data == null || returnData.data.length == 0) {
                getAllHot();
                console.log("获取曲线热点异常");
                return;
            }
            hotIdArray.length = 0;
            imageArray.length = 0;
            titleArray.length = 0;
            scoreArray.length = 0;
            introArray.length = 0;
            formArray.length = 0;
            tagArray.length = 0;
            $.each(returnData.data,function(index,item){
                if(index < 10){
                    hotIdArray.push(item.id);
                    if(item.logoImgUrl){
                        imageArray.push(item.logoImgUrl);
                    }else{
                        imageArray.push("img/defaultIcon.png");
                    }
                    titleArray.push(item.title);
                    scoreArray.push(item.prevailingTrend);
                    introArray.push(item.introduction);
                    if(item.eventClass){
                        var str = "";
                        var eventClass = item.eventClass.split(",");
                        $.each(eventClass,function(index2,item2){
                            str += "<div>"+item2+"</div>"
                        })
                    }
                    tagArray[item.id] = str;
                    var tempArray = [];
                    if(item.baiduHitNum){
                        tempArray.push("baidu");
                    }
                    if(item.zhihuAvgAnswerNumber){
                        tempArray.push("zhihu");
                    }
                    if(item.wechatAvgReadNum){
                        tempArray.push("wechat");
                    }
                    tempArray.push("weibo");
                    formArray.push(tempArray);
                }
            });
            loadSvg();
            getAllHot();
        },
        error: function() {
            console.log('获取热点失败');
        }
    });
}
var updateState = true;
function getUpdateInfo(){
    var data = {
        first:true
    }
    $.ajax({
        type: "get",
        url: dataUrl.util.getUserInfo,
        data:data,
        success: function(returnData) {
            if(returnData.error.code == 0 && returnData.data){
                var  res=returnData.data;
                if(res.firstUserAccount){
                    //用户引导/
                    $('.hot-user-guide').on('click',function(e){
                        e ? e.stopPropagation() : event.cancelBubble = true;
                    });
                    $('.hot-user-guide').show();
                    $('.hot-user-tep1').show();
                    $('.hot-user-tep1').on('click',function(e){
                        e ? e.stopPropagation() : event.cancelBubble = true;
                        $('.hot-user-tep1').hide();
                        $('.hot-user-tep2').show();
                    });
                    $('.hot-user-tep2').on('click',function(e){
                        e ? e.stopPropagation() : event.cancelBubble = true;
                        $('.hot-user-tep2').hide();
                        $('.hot-user-tep3').show();
                    });
                    $('.hot-user-tep3').on('click',function(e){
                        e ? e.stopPropagation() : event.cancelBubble = true;
                        var left = parseInt($('.alertCon').css('left')),
                            top = parseInt($('.alertCon').css('top'));
                        $('.hot-user-tep3').hide();
                        $('.hot-user-tep4').css({'left':left+170,'top':top-170});
                        $('.hot-user-tep4').show();
                    });
                    $('.hot-user-tep4').on('click',function(e){
                        e ? e.stopPropagation() : event.cancelBubble = true;
                        var left = parseInt($('.alertCon').css('left')),
                            top = parseInt($('.alertCon').css('top'));
                        $('.hot-user-tep5').css({'left':left+65,'top':top+100});
                        $('.hot-user-tep4').hide();
                        $('.hot-user-tep5').show();
                    });
                    $('.hot-user-tep5').on('click',function(e){
                        e ? e.stopPropagation() : event.cancelBubble = true;
                        $('.hot-user-tep5').hide();
                        $('.hot-user-tep6').show();
                    });
                    $('.hot-user-tep6').on('click',function(e){
                        e ? e.stopPropagation() : event.cancelBubble = true;
                        $('.hot-user-tep6').hide();
                        $('.hot-user-tep7').show();
                    });
                    $('.hot-user-tep7').on('click',function(e){
                        e ? e.stopPropagation() : event.cancelBubble = true;
                        $('.hot-user-tep7').hide();
                        $('.hot-user-guide').hide();
                    });
                }else if(res.hasProjUpdate){
                    $('#record-btn-index').click();
                }
                updateState = res.hasProjUpdate;
            }
        }
    });
}

function clearChat(a){
    a.value=a.value.replace(/[^\d]/g,'')
}
$(".hotInfo").on("click",function(e){
    e ? e.stopPropagation() : event.cancelBubble = true;
    hotsystem_flag=false;
//    	var index = $(".infoConnect").attr("data-index");
    var index = $(".infoConnect").attr("data-id");
    $("#allHot").click();
    $(".all_hot_list .all_hot_list_bot").hide();
    $(".all_hot_list .hot_echart_list").addClass('hidecommon');
    $(".all_hot_list .ulBottom"+index).prev().find('.all_hot_list_top_source').click();
    setTimeout(function(){
        var a = $(".all_hot_list .ulBottom"+index).offset().top;
        a -= 450;
        $("html,body").animate({scrollTop:a},"slow");
    },150)
})
//    $(".alertCon").on("click",function(e){
//    	if($(e.target).hasClass("infoConnect")){
//    		e ? e.stopPropagation() : event.cancelBubble = true;
//    	}
//
//    })
//滚动的时候固定热点详情头部
$(window).scroll(function(){

    if($(window).scrollTop()>222){
        $('#all_hot_bar').addClass('all_hot_bar_scroll');
    }else{
        $('#all_hot_bar').removeClass('all_hot_bar_scroll');
    };
    
    if($(window).scrollTop()>480){
        $('.circle_hot_bar').addClass('all_hot_bar_scroll');
    }else{
        $('.circle_hot_bar').removeClass('all_hot_bar_scroll');
    };


    if($(window).scrollTop()>1000){
        $('#comeback_hot_home').removeClass('hidecommon');
    }else{
        $('#comeback_hot_home').addClass('hidecommon');
    }
});
$('#comeback_hot_home').on('click',function(){
    $('body').animate({scrollTop:"0px"},500)
});
$("#papersvg").on("click",function(e){
    if(canClick){
        return
    }else{
        $(this).removeClass("pointer");
        $("#comeback_hot").click();
    }
})
$(".upPage").on("click",function(){
    $("#comeback_hot").click();
})
$(".hotDetailInfo").on("click",function(){
    $(".hotInfo").click();
})
$(".userProfile").on("click",function(){
    hotsystem_flag=false;
    var itemIndex = $(".infoConnect").attr("data-id");
    $("#allHot").click();
    $(".all_hot_list .all_hot_list_bot").hide();
    $(".all_hot_list .hot_echart_list").addClass('hidecommon');
    $.each($(".all_hot_list .all_hot_list_top_look"),function(index,item){
        var a = $(item).parent().next().data("id");
        if(a == ("ulBottom"+itemIndex)){
            $(item).click();
            setTimeout(function(){
                var a = $(".all_hot_list .ulBottom"+itemIndex).next().offset().top;
                a -= 450;
                $("html,body").animate({scrollTop:a},"slow");
            },250)
        }
    })
});
//更新记录

$('#record-btn-index').on('click',function(){
    if($("#record-ul-2").children().length == 0){
        recordLog();
    }
    $('.record-div').css({'width':'100%','height':'100%','top':0});
    $('.record-con2').hide();
    $('.record-con1').show();
    $('.record-con1').find('ul').addClass('hidecommon');
    $('.record-con1').find('ul:eq(0)').removeClass('hidecommon');
    $('.record-div').show();

});
$('#record-btn-log').on('click',function(){
    $('.record-con1').hide();
    $('.record-con2').show();
});
$('#record-btn-near').on('click',function(){
    $('.record-con2').hide();
    $('.record-con1').show();
});

$('.record-con2 .record-ul').delegate('li','click',function(){
    var index = $(this).index();
    $('.record-con1').find('ul').addClass('hidecommon');
    $('.record-con1').find('ul').eq(index).removeClass('hidecommon');
    $('.record-con2').hide();
    $('.record-con1').show();
})

$('.record-btn-b-r').on('click',function(){
    var top = $("#record-btn-index").offset().top;
    var eleHeight = $('.record-con1').height();
    $('.record-div').animate({'left': 0,'top': top+10,'width':0,'height':0},500);
    $('.record-div').delay(500).hide(0);
})
window.onload = function(){
    getUpdateInfo();
}
function loadJS(src, callback){
    var script = document.createElement('script');
    var head = document.getElementsByTagName('head')[0];
    var loaded;
    script.src = src;
    script.onload = script.onreadystatechange = function(){
        if(!loaded && (!script.readyState || /loaded|complete/.test(script.readyState))){
            script.onload = script.onreadystatechange = null;
            loaded = true;
            callback();
        }
    }
    head.appendChild(script);
}

function updateStateChange(){
    if(updateState){
        $.ajax({
            type:"post",
            //		contentType: 'application/json',
            //	    dataType:"json",
            url:dataUrl.util.updateStateChange,
            success:function(returnData){

            },
            error:function(){
                console.log('更改更新通知状态失败');
            }
        });
    }
}
$(".circle_btn").on("click",function(){
	$('#all-hot-btn-div').hide();
	$(this).css('background-image','url(img/hot-all-hover.png)');
	$(this).css('background-color','#399b9f');
	$('.all_hot_btn').removeClass('all-circle-active');
//	$('.all_hot_btn').css('background-image','url(img/hot-all-icon.png)');
//	$('.all_hot_btn').css('background-color','');
	$("#all_hot_section").addClass('hidecommon');
	$("#circle_hot_section").show();
	var len=$(".circleTagCon>li").length;
	if(len<=0){
	    $.ajax({
	        type:"get",
	        contentType: 'application/json',
	        dataType:"json",
	        url:dataUrl.util.getInpList(),
	        success:function(returnData){
	            returnData = returnData.data;
	            if(returnData == null||returnData.Circle==null){
	                console.log('数据为空');
	            }else{
	                $(".circleTagCon").data("info",returnData);
	                $.each(returnData.Circle,function(idx,item){
	                    $("<li></li>").text(item.name).data("id",item.id).data('info',item).appendTo(".circleTagCon");
	                });
	                $(".circleTagCon>li:first").trigger("click");
	            } 
	        },
	        error:function(){
	            console.log('获取标签列表失败');
	        }
	    });
	}else{
	    $(".circleTagCon>li:first").trigger("click");
	}
})
$(".all_hot_btn").on("click",function(){
	$('#all-hot-btn-div').show();
	$(this).addClass('all-circle-active');
	$('#allhot-change').removeClass('allhot-changeactive');
	$(".all_hot_list").show();
	$(".all_hot_list_ser").hide();
//	$(this).css('background-image','url(img/hot-all-hover.png)');
//	$(this).css('background-color','#399b9f');
	$('.circle_btn').css('background-image','url(img/hot-all-icon.png)');
	$('.circle_btn').css('background-color','');
	$("#circle_hot_section").hide();
	$("#all_hot_section").removeClass("hidecommon");
})

//切换圈层
$(document).on("click",".circleTagCon li",function(){
    var $this = $(this)
    if($this.hasClass("circletagactive")){
        return;
    }else{
        var id=$this.data("id");
        $this.siblings().removeClass("circletagactive");
        $this.addClass("circletagactive");
        $(".circleCon").hide();
        var len=$(".circleCon[data-id='"+id+"']").length;
        if(len<=0){
            var info=$this.data("info");
            var rule=JSON.parse(info.rule);
            var data={
                    age:rule.age,
                    gender:rule.gender,
                    education:rule.education,
                    userClass:rule.userClass
            };
           var $dom=$("body>.circleCon").clone();
           $dom.attr("data-id",id);
           $dom.find(".circle_Des_Icon").css("background-image","url("+rule.img_url+")");
           $dom.find(".circleDesText").text(info.description);
           $dom.find(".circleDesTitle,.circle_Tag_Circle>span").text(info.name);
           var tagData=formatTagData($(".circleTagCon").data("info"),rule);
           createTag($dom.find(".circle_Tag_Circle"),tagData);
           $dom.appendTo("#circle_hot_section");
           $dom.show();
           $.ajax({
               type: "post",
               contentType: 'application/json',
               dataType: "json",
               url: dataUrl.util.getCircleHots(100),
               data:JSON.stringify(data),
               success: function(returnData) {
                   if(returnData.error.code != 0 || returnData.data == null || returnData.data.length == 0){
                       console.log("获取圈层热点异常");
                       return
                   }
                   $dom.find(".circle_hot_list").html(hotList2.render(returnData));
               },
               error: function() {
                   console.log('获取圈层热点失败');
               }
           });
        }else{
            $(".circleCon[data-id='"+id+"']").show();
        }
    }
});

//处理圈层标签数据
function formatTagData(data,rule){
    var Education=data.Education;
    var Gender=data.Gender;
    var UserClass=data.UserClass;
    var Circle=data.Circle;
    var data=[];
    
    $.each(rule.education,function(idx,item){//学历
        data.push(_.findWhere(Education, {id:item}).name);
    })
    
    $.each(rule.gender,function(idx,item){//性别
        data.push(_.findWhere(Gender, {id:item}).name);
    })
    
    $.each(rule.userClass,function(idx,item){//兴趣爱好
        data.push(_.findWhere(UserClass, {id:item}).name);
    })
    
    var ageLen=rule.age.length;
    if(ageLen==1){
        data.push(rule.age[0]+"");
    }else if(ageLen==2){
        data.push(rule.age[0]+" - "+rule.age[1]);
    }
    return data;
}

function createTag(circleTagCon,circleTagArray){
	if(screen.availWidth>1400){
		if(circleTagArray.length > 10){
			circleTagArray.length = 10
		}
	}else{
		if(circleTagArray.length > 6){
			circleTagArray.length = 6
		}
	}
	console.log(circleTagCon)
	var len = circleTagArray.length;
	var leftStart = 140;
	var degTrans = Math.PI / 180;
	var stepLeft = 80/(Math.ceil(len/2));
	var stepRight = 80/(parseInt(len/2))
	console.log(stepLeft,stepRight);
	var leftAngle = [];
	var rightAngle = [];
	$.each(circleTagArray,function(index,item){
		if(index%2 == 0){
			var tempEle = $("<div class='circleTagLeft'><span class='textCut'></span></div>");
			var angle = 140 + stepLeft*(parseInt(index/2)+0.5);
			var tempLeft = 0;
			if(index==6){
				tempLeft = -108
				angle=(leftAngle[0]+leftAngle[1])/2;
			}
			if(index==8){
				tempLeft = -108
				angle=(leftAngle[1]+leftAngle[2])/2
			}
			tempEle.find("span").html(item);
			var left = tempLeft + 23+96*Math.cos(angle*degTrans);
            var top = 43+96*Math.sin(angle*degTrans);
			tempEle.css({left:left,top:top,display:'inline-block'});
			leftAngle.push(angle);
			circleTagCon.append(tempEle);
		}else{
			var tempEle = $("<div class='circleTagRight'><span class='textCut'></span></div>");
			rightAngle.push(angle);
			var angle = 320 + stepRight*(parseInt(index/2)+0.5);
			var tempRight = 0;
			if(index == 7){
				tempRight = 108;
				angle=(rightAngle[0]+rightAngle[1])/2;
			}
			if(index == 9){
				tempRight = 108;
				angle=(rightAngle[1]+rightAngle[2])/2
			}
			if(angle>360){
				angle -= 360
			}
			tempEle.find("span").html(item);
			var left = tempRight + 63+96*Math.cos(angle*degTrans);
            var top = 43+96*Math.sin(angle*degTrans);
			tempEle.css({left:left,top:top,display:'inline-block'});
			console.log(tempEle);
			circleTagCon.append(tempEle);
		}
	})
}
$(document).on('mouseenter',".tgiInt",function(e){
	console.log(e)
	var left = e.pageX - 125;
	var top = e.pageY - 100;
	$(".tgiInfoDialog").css({left:left,top:top});
	$(".tgiInfoDialog").show();
})
$(document).on('mouseleave',".tgiInt",function(){
	$(".tgiInfoDialog").hide();
})
$(document).on("click",".defBtn",function(){
	var $this = $(this);
	if($this.hasClass("tgiSelect")){		
	}else{
		$this.siblings().removeClass("tgiSelect");
		$this.addClass("tgiSelect");
		if($this.html() == "占比"){
			$this.parent().parent().find(".areaTGICon").hide();
			$this.parent().parent().find(".areaCon").show();
		}else{
			$this.parent().parent().find(".areaTGICon").show();
			$this.parent().parent().find(".areaCon").hide();
		}
	}
})
//createTag($circleTagCon,circleTagArray);


////* 搜索热点start */
$('#allhot-change').on('click',function(){
	if($(this).hasClass('allhot-changeactive')){
		$(this).removeClass('allhot-changeactive');
		$(".all_hot_list_ser").hide();
    	$(".all_hot_list").show();
	}else{
		var val = $.trim($('#all-hot-btn-ser').val());
		if(val){
			$(this).addClass('allhot-changeactive');
			$.ajax({
		        type:"get",
		        url:dataUrl.util.getSerHotInfo(val),
		        //data:data,
		        success:function(returnData){
		            //console.log(returnData);
		            if(returnData.data.length>0){
		            	$(".all_hot_list").hide();
		            	$(".all_hot_list_ser").show();
		            	$(".all_hot_list_ser").html('');
		 	            $(".all_hot_list_ser").html(hotList2.render(returnData));
		            }else{
		            	$(".all_hot_list").hide();
		            	$(".all_hot_list_ser").show();
		            	$(".all_hot_list_ser").html('');
		            	var addHeight = $(window).height();
		            	$(".all_hot_list_ser").css('height',addHeight-302);
		            	$(".all_hot_list_ser").append('<div style="color:#4a4a4a;width:300px;text-align:center;margin:0 auto;top: 50%;position: relative;">很抱歉，没有找到与“搜索关键词”相关的热点。</div>');
		            }
		           
		        },
		        error:function(){
		            console.log('搜索热地失败');
		        }
		    });
			
		}else{
			return;
		}
	}
});
$('#all-hot-btn-ser').on('input',function(){
	$('#allhot-change').removeClass('allhot-changeactive');
});

$('#all-hot-btn-ser').keyup(function(event) {
	if(event.keyCode == "13") {
		$('#allhot-change').click();
	}
});



///* 搜索热点end */
