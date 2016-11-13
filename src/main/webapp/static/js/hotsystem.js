//头部。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
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
	function getCommon(){
		$("#favorite_ul").empty();
		$.ajax({
			type:"post",
			contentType: 'application/json',
		    dataType:"json",
			url:dataUrl.util.getCommon(),
			success:function(returnData){
				console.log(returnData.data)
				if(returnData.data != null && returnData.error.code == 0){
					var str = "";
					$.each(returnData.data,function(index,item){
						if(index > 4) return;
						str += "<li data-id='"+item.id+"' title='"+ unescape(item.words) +"'>"+unescape(item.words)+"<span></span></li>"
					})
					$("#favorite_ul").html(str);
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
		console.log(val);
		var data = {searchWords:val};
		$.ajax({
			type:"post",
			url:dataUrl.util.addCommon(),
			data:data,
			success:function(returnData){
				console.log(returnData.data.id);
				if(returnData.error.code == 0){
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
				console.log(returnData);
				if(returnData.error.code == 0){
					$this.parent().remove();
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
				console.log(returnData.data)
				if(returnData.data != null && returnData.error.code == 0){
					var str = "";
					$.each(returnData.data,function(index,item){
						str += "<li data-id='"+item.id+"'>"+unescape(item.keyword)+"<span></span></li>"
					})
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
		console.log(id)
		e ? e.stopPropagation() : event.cancelBubble = true;	
		var data = {"id":id};
		$.ajax({
			type:"post",
			url:dataUrl.util.cancleSerHistory(),
			data:data,
			success:function(returnData){
				console.log(returnData);
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
	function labelList(){
		$.ajax({
			type:"get",
			contentType: 'application/json',
		    dataType:"json",
			url:dataUrl.util.getInpList(),
			success:function(returnData){
				console.log(returnData);
				returnData = returnData.data;
				if(returnData == null){
					console.log('数据为空');
				}else{
					var eventData = returnData.EventClass;
					var eventTemp = eventData.slice(0,8);
					var eventTemp2 = eventData.slice(8);
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
					fillData($(".eventDialogTab"),$(".eventTab"),eventTemp);
					fillData($(".eventDialogTab2"),$(".eventTab2"),eventTemp2);
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
			var val = $.trim($('#dialog_ser_text').val());
				if(val.match(/\d+/g)||val.search(/[a-zA-Z]+/)!==-1||/[\u4E00-\u9FA5]/g.test(val)){
					var dataObj = {
							Even:[],
							Area:[],
							Age:[],
							Education:[],
							Gender:[],
							UserClass:[]
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
					console.log(dataObj)
					var hash = JSON.stringify(dataObj);
					window.location.href='hotresult?clueWord='+escape(val)+'&pageSize=20&currentPage=1#'+hash;
				}else{
					return;
				};
		};	
	});
	
	$('#dialog_ser_to').on('click',function(){
		var val = $.trim($('#dialog_ser_text').val());
			if(val&&val.match(/\d+/g)||val.search(/[a-zA-Z]+/)!==-1||/[\u4E00-\u9FA5]/g.test(val)){
				var dataObj = {
						Even:[],
						Area:[],
						Age:[],
						Education:[],
						Gender:[],
						UserClass:[]
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
				console.log(dataObj)
				var hash = JSON.stringify(dataObj);
				window.location.href='hotresult?clueWord='+escape(val)+'&pageSize=20&currentPage=1#'+hash;
			}else{
				return;
			};
	});
	
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
				str += '</li> <li class="inp_select_all fr"> <label><input type="checkbox">全选</label> </li> </ul>';
			}else{
				var str = '<ul class="hidecommon"> <li class="inp_ch_list fl">';
				str += '</li> <li class="inp_select_all fr"> <label><input type="checkbox">全选</label> </li> </ul>';
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
				str += '</li> <li class="inp_select_all fr"> <label><input type="checkbox">全选</label> </li> </ul>';
			}else{
				var str = '<ul class="hidecommon"> <li class="inp_ch_list fl">';
				str += '</li> <li class="inp_select_all fr"> <label><input type="checkbox">全选</label> </li> </ul>';
			}
			selector2.append(str);
		});
		selector2.prepend('<ul class="hot_dia_age hidecommon" id="hot_dia_age"><div class="age_dia_con">'+
				'<input onkeyup="clearChat(this)" maxlength="3" id="hot_age1"><b>岁</b><b>至</b>'+
				'<input  onkeyup="clearChat(this)" maxlength="3" id="hot_age2"><b>岁</b>'+
				'</div></ul>')
	};



	
	//事件标签点击
	$('.dialog_tab_event').delegate('.inp_ch_list input','click',function(){
		var dataId = $(this).attr('data-id');
		var num = Number($('.cor389b9f').find('span').text());
		console.log(num)
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
					console.log($(this).parent().text());
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
		console.log(dataId);
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
						console.log(textStr)
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
		
//		$('.dialog_tab_person').find('input').prop("checked",false);
//		$('.dialog_tab_person').prev().find('.dialog_inp_num').text(0);
//		$('.dialog_tab_person').prev().find('.dialog_inp_num').css('display','none');
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
    for(var i=0;i<10;i++){
        yArray.push(100-scoreArray[i])
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
        	var textArrayItem = paper.text(xArray[i],yArray[i]+45,titleArray[i]).attr({"fill":'#fff',"font-family":'微软雅黑',"font-size":"14",opacity:0,cursor:"pointer"}).data("index",i).animate({opacity:1},700,"ease").click(function(e){nodeClick(e,this)});
           
            var rectArrayItem = paper.rect(xArray[i] - 12,yArray[i] + 3,0,0).attr({fill:"#389b9f",opacity:0,transform:"r45",width:24,height:24,"stroke-width":0,r:2,opacity:0,cursor:"pointer"}).data("index",i).animate({"opacity":1,transform:"r45"},700,"ease").click(function(e){nodeClick(e,this)});
        	var hotArrayItem = paper.text(xArray[i],yArray[i] + 15,scoreArray[i]).attr({"fill":'#fff',"font-family":'微软雅黑',"font-size":"16",opacity:0,cursor:"pointer"}).data("index",i).animate({opacity:1},700,"ease").click(function(e){nodeClick(e,this)});
        	textArray[i] = textArrayItem;
        	rectArray[i] = rectArrayItem;
        	hotArray[i] = hotArrayItem;
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
            loadSvg();
    	},500)
    };
    function nodeClick(e,t){
    	$('#cook_ul').addClass('hidecommon');
    	if(canClick){
    		e ? e.stopPropagation() : event.cancelBubble = true;
            var index = t.data("index");
            var scrollY = window.scrollY;
            if(scrollY == undefined){
                scrollY = window.pageYOffset
            }
            var scrollX = window.scrollX;
            if(scrollX == undefined){
                scrollX = window.pageXOffset
            }
            var jqObj = $("rectArray[index].node");
            var offset = jqObj.offset();
            var X = rectArray[index].node.getBoundingClientRect().left + document.documentElement.scrollLeft;
            var Y = rectArray[index].node.getBoundingClientRect().top + document.documentElement.scrollTop;
            var trianglePos = triangleStep * (index + 1);
            $(".triangle").css("left",trianglePos);
            alertCon.css({left:X - trianglePos + 12 + scrollX,top:Y - 144 + scrollY});
            $(".hotValue").html(scoreArray[index]);
            $(".infoTitle").html(titleArray[index]);
//            var divH = $(".hotInfo").height();
//            var $p = $(".infoConnect");
//            while ($p.outerHeight() > divH) {
//                $p.text($p.text().replace(/(\s)*([a-zA-Z0-9]+|\W)(\.\.\.)?$/, "..."));
//            };
            $(".portrait").css("background-image","url("+imageArray[index]+")");
            $(".infoText").html(introArray[index]);
            $(".infoText").attr("title",introArray[index]);
            $(".infoConnect").attr("data-id",hotIdArray[index]);
            $(".infoConnect").attr("data-index",index);
            $(".infoConnect").attr("data-topic",titleArray[index]);
            $(".infoIcon").hide();
            $.each(formArray[index],function(index,item){            	
            	$("#icon"+item).show();
            })
            $(".hotAlertTag").html(tagArray[hotIdArray[index]]);
            alertCon.show();
    	}else{
        	$("#comeback_hot").click();
        }
    }
//    $(".papersvg").click(function(){
//    	if(!canClick){
//    		$("#comeback_hot").click()
//    	}    	
//    })
    $(document).on("click",".hot_relation,.infoConnect",function(){
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
    	console.log(topic);
    	if($(".selectTag")){
    		$(".selectTag").attr("title",topic);
    		$(".selectTag").html(topic)
    	}
        var content = "";
        content += '<input class="releateTag" autofocus placeholder="请输入关键字" />';
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
                    var hotTopic = titleArray[index];
                    if(query.trim() == ""){                    
                    }else{
//                        window.location.href="path?query="+escape(query)+"&topicId="+topicId+"&hotTopic="+escape(topic);
                    	  window.location.href="newPath#query="+query+"&topicId="+hotTopId+"&hotTopic="+topic;
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
    	if($(e.target).hasClass("alertCon")||$(e.target).hasClass("portrait")||$(e.target).hasClass("infoTitle"))return;
    	alertCon.hide();
    })
    $(".infoBottom").on("click",function(e){
    	e ? e.stopPropagation() : event.cancelBubble = true;
    })
//热点详细信息。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
   $('#allHot').on('click',function(){
        for(var i=0,j=rectArray.length;i<j;i++){
            rectArray[i].attr({cursor:"default"});
            hotArray[i].attr({cursor:"default"});
            textArray[i].attr({cursor:"default"});
        }
	   $('#ser_section').css('min-height',0);
	   $('#ser_section').css('opacity',0);
	   canClick = false;
	   $('#allHot').addClass('hidecommon');
	   $('#all_hot').removeClass('hidecommon');
	   $('#all_hot').animate({
		   opacity:1
	   },500);
	   $('#ser_section').css("height",0);
	   $('.notify-list').css('display',"none");
	   $('.nav_ser').delay("fast").fadeIn();
	   $(".all_hot_list_bot").hide();
	   $(".all_hot_list_bot:eq(0)").show();
   }); 
   //返回首页
    $('#comeback_hot').on('click',function(){
       canClick = true;
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
	   $('.notify-list').css('display',"block");
	   $('.nav_ser').delay("fast").fadeOut();
	  
    }) 
    //切换效果
    $('.all_hot_list_bot:not(":first")').css('display','none');
  
    $(document).on('click','.all_hot_list_top_source',function(){
    	$('.all_hot_list_top_look').css('color','#4a4a4a');
    	$('.all_hot_list_top_look').find('.hot_look_arrow').css("transform","rotate(0deg)");
    	$('.all_hot_list_top_look').find('.hot_look_eye').css('background-image','url(img/hot_look_eye.png)');
    	$('.hot_echart_list').addClass('hidecommon');
    	if($(this).parent().next().css('display') == 'block'){
    		$(this).parent().next().hide();
    		$(this).find(".hot_img_arrow").css("transform","rotate(0deg)")
    	}else{
    		$(this).parent().parent().parent().find(".all_hot_list_bot").hide();
        	$(this).parent().next().show();
        	$(this).parent().parent().parent().find(".hot_img_arrow").css("transform","rotate(0deg)");
        	$(this).find(".hot_img_arrow").css("transform","rotate(180deg)")
    	};    	
    });
    var circleOption = {
    		title: {
                text: '',
                left: 'center',
                top:15
            },
            color:["#5ccfcd","#6faff4"],
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
                formatter: function (name) {
                    return name.split(" ")[0];
                }
            },
            series: [
                {
                    name:'',
                    type:'pie',
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
    var option1 = {
            title: {
                text: '受众性别分布',
                left: 'center'
            },
            backgroundColor:"#fff",
            tooltip: {
                trigger: 'item',
                formatter: function (obj) {
                    return obj.name.split(":")[0] + ": " + obj.percent + "%" 
                },
                backgroundColor:"rgba(255,255,255,0.5)",
                borderColor:"#5ccfcd",
                borderWidth:2,
                textStyle:{
                	color:"#000",
                	fontFamily:"微软雅黑"
                }
            },
            legend: {
                orient: 'horizontal',
                bottom:10,
                data:['男 97%','女 3%'],
                formatter: function (name) {
                    return name.substr(0,1);
                }
            },
            series: [
                {
                    name:'性别',
                    color:["#5ccfcd","#6faff4"],
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,                    
                    data:[
                        {value:97, name:'男 97%',label: {
                            normal: {
                                show: true,
                                position: 'center',
                                textStyle: {
                                    fontSize: '20',
                                    fontWeight: 'bold'
                                }
                            }
                        }},
                        {value:3, name:'女 3%'}
                    ]
                }
            ]
    };
  var mapOption = {

  		backgroundColor:"#fff",
  	    title : {
  	        text: '受众地区分布',
  	        left: 'center'
  	    },
  	    tooltip : {
  	        trigger: 'item'
  	    },
//  	    legend: {
//  	        orient: 'vertical',
//  	        left: 'left',
//  	    },
  	    visualMap: {
  	    	show:false,
              inRange: {
                  color: ['#a9d6fe','#619edd']
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
  	            name: '地区',
  	            type: 'map',
  	            mapType: 'china',
  	            roam: false,
  	            markPoint:{itemStyle:{normal:{color:'#0ff'}}
  	            	},
  	        
  	            label: {
  	                normal: {
  	                    show: false
  	                },
  	                emphasis: {
  	                    show: false
  	                }
  	            },//    	        	           
  	            itemStyle: {
                      normal: {
                          //borderWidth: 2,
                         // borderColor: 'lightgreen',
                      	areaColor: '#a9d6fe',
                          label: {
                              show: false
                          }
                      },
                      emphasis: { // 也是选中样式
                      	show: false,
                          borderWidth: 1,
                          borderColor: '#fff',
                          areaColor: '#a9d6fe',
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
  	            data:[],
  	        },
  	    
  	       
  	    ]
  	
  }
 
    $(document).on('click','.all_hot_list_top_look',function(){
    	$('.all_hot_list_bot').css('display','none');
    	$('.hot_img_arrow').css("transform","rotate(0deg)");
    	
    	if($(this).parent().next().next().is('.hidecommon')){
    		$('.all_hot_list_top_look').css('color','#4a4a4a');
    		$(this).css('color','#389b9f');
    		$(this).parent().parent().parent().find('.all_hot_list_top_look em').text('点击查看');
    		$(this).find('em').text('点击收起');
    		$(this).parent().parent().parent().find(".hot_look_eye").css("background-image","url(img/hot_look_eye.png)");
    		$(this).find('.hot_look_eye').css('background-image','url(img/hot_look_eye_hover.png)');
    		$(this).parent().parent().parent().find(".hot_echart_list").addClass('hidecommon');
    		$(this).parent().next().next().removeClass('hidecommon');
    		$(this).parent().parent().parent().find(".hot_look_arrow").css("transform","rotate(0deg)");
    		$(this).find(".hot_look_arrow").css("transform","rotate(180deg)")
    	}else{
    		$(this).parent().next().next().addClass('hidecommon');
        	$(this).parent().next().next().addClass('hidecommon');
        	$(this).find(".hot_look_arrow").css("transform","rotate(0deg)");
    		$(this).css('color','#4a4a4a');
    		$(this).find('em').text('点击查看');
    		$(this).find('.hot_look_eye').css('background-image','url(img/hot_look_eye.png)');
    	};
    	var $this = $(this);
    	if($this.parent().parent().find(".Personas").length > 0){
			console.log("已添加用户画像");
			return;
		}
    	$.ajax({
    		type:"get",
    		url:dataUrl.util.getPercentData($(this).attr("data-id")),
    		success:function(data){
    			console.log(data);
    			var data = data.data;
    			if(data == null){
    				str = "<h1 style='position:relative;text-align:center;color:#000;top:50%;left:50%;transform:translate(-50%,-50%)'>获取数据错误</h1>";
    					$this.parent().parent().find(".hot_echart_list").html(str);
    					return;
    				}
    				var dataLen = data.gender.length + data.interest.length + data.education.length + data.area.length + data.age.length;
    				if(dataLen < 1){
    					str = "<h1 style='position:relative;text-align:center;color:#000;top:50%;left:50%;transform:translate(-50%,-50%)'>暂无热点受众画像</h1>";
    					$this.parent().parent().find(".hot_echart_list").html(str);
    					return;
    				}
    			//受众年龄画像
    			if(data && data.gender.length > 0){
    				var genderCon = $("<div  class='Personas' style='display:inline-block;width:17%;height:279px;background:#fff;'></div>");    				
    				$this.parent().parent().find(".hot_echart_list").append(genderCon);    				
    				var genderCharts = echarts.init(genderCon.get(0));
    				
    				var genderOption = $.extend(true,{},circleOption);
    				genderOption.title.text = "受众性别分布";
    				var max = 0;
    				var currentIndex = 0;
       				$.each(data.gender,function(index,item){
                        genderOption.legend.data.push({name:item.name+" "+item.value.toFixed(2)+"%",icon:"circle"});
       					var tempItem = JSON.stringify(item);
                        tempItem = JSON.parse(tempItem);
                        tempItem.name = item.name+" "+item.value.toFixed(2)+"%";
                        genderOption.series[0].data.push(tempItem);
                        if(max > item.value){                          
                        }else{
                            max = item.value;
                            currentIndex = index;
                        }  					
       				})                    
    				// genderOption.legend.data.push({name:data.gender[0].name+" "+data.gender[0].value.toFixed(2)+"%",icon:'circle'});
    				// genderOption.legend.data.push({name:data.gender[1].name+" "+data.gender[1].value.toFixed(2)+"%",icon:'circle'});
    				// genderOption.series[0].name = "性别";
    				// var genderJson0 = JSON.stringify(data.gender[0]); 
    				// genderOption.series[0].data.push(JSON.parse(genderJson0));
    				// var genderJson1 = JSON.stringify(data.gender[1]); 
    				// genderOption.series[0].data.push(JSON.parse(genderJson1));
    				// genderOption.series[0].data[0].name = data.gender[0].name+" "+data.gender[0].value.toFixed(2)+"%";
    				// genderOption.series[0].data[1].name = data.gender[1].name+" "+data.gender[1].value.toFixed(2)+"%";
    				var label =  {
                        normal: {
                            show: true,
                            position: 'center',
                            textStyle: {
                            	color:"#000",
                                fontSize: '16',
                                fontWeight: 'bold'
                            }
                        }
                    }
       				genderOption.series[0].data[currentIndex].label = label;
    				// if(data.gender[0].value > data.gender[1].value){
    				// 	genderOption.series[0].data[0].label = label;
    				// }else{
    				// 	genderOption.series[0].data[1].label = label;
    				// }
    				genderCharts.setOption(genderOption);
    				window.onresize = genderCharts.resize;
    				//受众学历分布
    				var educationCon = $("<div  class='Personas' style='display:inline-block;width:17%;height:279px;;background:#fff;'></div>");
    				$this.parent().parent().find(".hot_echart_list").append(educationCon);
    				var educationCharts = echarts.init(educationCon.get(0));
    				var educationOption = $.extend(true,{},circleOption);
    				educationOption.title.text = "受众学历分布";
    				var educationMax = 0;
    				var educationMaxIndex = 0;
    				educationOption.series[0].name = "学历分布";
    				$.each(data.education,function(index,item){
    					educationOption.legend.data.push({name:item.name+" "+item.value+"%",icon:"circle"});
    					var tempItem = JSON.stringify(item);
    					tempItem = JSON.parse(tempItem);
    					tempItem.name = item.name+" "+item.value+"%";
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
                            	color:"#000",
                                fontSize: '16',
                                fontWeight: 'bold'
                            }
                        }
                    }
                    educationOption.series[0].data[educationMaxIndex].label = label;
//    				if(data.gender[0].value > data.gender[1].value){
//    					genderOption.series[0].data[0].label = label;
//    				}else{
//    					genderOption.series[0].data[1].label = label;
//    				}
    				educationCharts.setOption(educationOption);
    				window.onresize=educationCharts.resize;
    				//兴趣雷达图
    				if(data && data.interest.length > 0){
						var interestCon = $("<div class='Personas' style='display:inline-block;width:17%;height:279px;background:#fff;'></div>");
	    				$this.parent().parent().find(".hot_echart_list").append(interestCon);
	    				var interestCharts = echarts.init(interestCon.get(0));
	    				var interestvals = [];
	    	        	var interestnames = [];
	    	        	var interest =data.interest;
	    	        	$.each(interest,function(i,item){
	    	        		interestvals.push(item.value);
	    	        		interestnames.push({name:item.name});
	    	        	});
	    	        	
	    	        	interestCharts.setOption({
	    	        		color:['#ccc'],
	    	        	    title: {
	    	        	        text: '受众兴趣偏好',
	    	        	        left:'center',
	    	        	        top:15
	    	        	    },
	    	        	    backgroundColor:"#fff",
	    	        	    tooltip: {},
	    	        	    
	    	        	    radar: {
	    	        	    	radius:'50%',
	    	        	    	center:['50%','60%'],
	    	        	    	splitArea: {
	    	        	            areaStyle: {
	    	        	                color: ['#fff', '#fff', '#fff', '#fff']
	    	        	            }
	    	        	        },
	    	        	        // shape: 'circle',
	    	        	        indicator: interestnames,
	    	        	        splitLine: {
	        	                    lineStyle: {
	        	                        color: '#ccc'
	        	                    }
	        	                },
	        	                axisLine: {
	        	                    show:false
	        	                },
	    	        	    },
	    	        	    series: [{
	    	        	        type: 'radar',
	    	        	        data : [
	    	        	            {
	    	        	                value : interestvals,
	    	        	                itemStyle: {normal: {areaStyle: {type: 'default',color:'#5ccfcd'}}},
	//    	        	                areaStyle: {
	//    	                                normal: {
	//    	                                    color: '#fff'
	//    	                                }
	//    	                            }
	    	        	            }
	    	        	        ]
	    	        	    }]
	    	        	});
    				}
    	        	window.onresize=interestCharts.resize;
    	        	//年龄柱状图
    	        	var ageNewCon = $("<div class='Personas' style='display:inline-block;width:17%;height:279px;background:#fff;'></div>");
    	        	$this.parent().parent().find(".hot_echart_list").append(ageNewCon);
    	        	var ageNewCharts = echarts.init(ageNewCon.get(0));
    	        	var ageNames = [];
    	        	var ageVals = [];
    	        	var age =data.age;
    	        	$.each(age,function(i,item){
    	        		ageVals.push(item.value);
    	        		ageNames.push(item.name);
    	        	});
    	        	
    	        	ageNewCharts.setOption({
    	        			backgroundColor:"#fff",
	    	        		title: {
	     	        	        text: '受众年龄分布',
	     	        	        left:'center',
	     	        	        top:15,
	     	        	    },
    	            	    color: ['#3398DB'],
    	            	    tooltip : {
    	            	        trigger: 'axis',
    	            	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
    	            	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
    	            	        }
    	            	    },
    	            	    grid: {
    	            	        left: '3%',
    	            	        right: '4%',
    	            	        bottom: '3%',
    	            	        containLabel: true
    	            	    },
    	            	    xAxis : [
    	            	        {
    	            	            type : 'category',
    	            	            data :ageNames ,
    	            	            axisTick: {
    	            	                alignWithLabel: true
    	            	            },
    	            	            splitLine:false,
    	            	            axisLine:{
    	            	            	lineStyle:{color:'#ccc'}
    	            	            }
    	            	        }
    	            	    ],
    	            	    yAxis : [
    	            	        {
    	            	            type : 'value',
    	            	            splitLine:false,
    	            	            axisLine:{
    	            	            	lineStyle:{color:'#ccc'}
    	            	            }
    	            	        }
    	            	    ],
    	            	    series : [
    	            	        {
    	            	            name:'年龄',
    	            	            type:'bar',
    	            	            barWidth: '60%',
    	            	            data:ageVals
    	            	        }
    	            	    ]
    	        	});
    	        	window.onresize=ageNewCharts.resize;
    	        	//地图
    	        	var mapDiv = $("<div class='pst Personas'  style='display:inline-block;width:27%;height:279px;background:#fff;'><p class='pos' style='background:#f00;z-index:9000;'>海外</p></div>");
    	        	var mapCon = $("<div style='width:100%;height:279px;'></div>");
    	        	
    	        	$this.parent().parent().find(".hot_echart_list").append(mapDiv);
    	        	mapDiv.append(mapCon);
    	        	var mapCharts = echarts.init(mapCon.get(0));
    	        	var mapNames = [];
    	        	var mapVals = [];
    	        	var mapChina = data.area;
    	        	console.log(mapChina)
    	        	$.each(mapChina,function(i,item){
    	        		//ageVals.push(item.value);
    	        		
    	        		mapVals.push({name:item.name,value:666});
    	        	});
    	        	console.log(mapVals)
    	        	mapCharts.setOption({
    	        		backgroundColor:"#fff",
    	        	    title : {
    	        	        text: '受众地区分布',
    	        	        left: 'center'
    	        	    },
    	        	    tooltip : {
    	        	        trigger: 'item'
    	        	    },
//    	        	    legend: {
//    	        	        orient: 'vertical',
//    	        	        left: 'left',
//    	        	    },
    	        	    visualMap: {
    	        	    	show:false,
    	                    inRange: {
    	                        color: ['#a9d6fe','#619edd']
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
    	        	            name: '地区',
    	        	            type: 'map',
    	        	            mapType: 'china',
    	        	            roam: false,
    	        	            markPoint:{itemStyle:{normal:{color:'#0ff'}}
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
    	                                //borderWidth: 2,
    	                               // borderColor: 'lightgreen',
    	                            	areaColor: '#a9d6fe',
    	                                label: {
    	                                    show: false
    	                                }
    	                            },
    	                            emphasis: { // 也是选中样式
    	                            	show: false,
    	                                borderWidth: 1,
    	                                borderColor: '#fff',
    	                                areaColor: '#a9d6fe',
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
    	        	            data:[ {name: '北京',value: 1000},
    	                        {name: '天津',value: 300},
    	                        {name: '上海',value: 500},
    	                        {name: '重庆',value: 600}]
    	        	        },
    	        	    
    	        	       
    	        	    ]
    	        	})
    	        	mapOption.series[0].data = mapVals;
    	        	mapCharts.setOption(mapOption);
    	        	window.onresize=mapCharts.resize;
    			}
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
            	if(returnData.error.code != 0) return;
            	$(".all_hot_list").html(hotList2.render(returnData));
            	$('.all_hot_list_top_source:first').find('.hot_img_arrow').css('transform','rotate(180deg)');
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
            	if(returnData.error.code != 0) return;
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
                		var eventClass = item.eventClass.split(",");
                        var str = "";
                        $.each(eventClass,function(index2,item2){
                        	str += "<div>"+item2+"</div>"
                        })
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
    function clearChat(a){
    	a.value=a.value.replace(/[^\d]/g,'')
    }
    $(".hotInfo").on("click",function(e){
    	e ? e.stopPropagation() : event.cancelBubble = true;
//    	var index = $(".infoConnect").attr("data-index");
    	var index = $(".infoConnect").attr("data-id");
    	$("#allHot").click();
    	$(".all_hot_list_bot").hide();
    	$("#ulBottom"+index).show();
    	setTimeout(function(){
    		console.log(index)
    		var a = $("#ulBottom"+index).offset().top;
    		a -= 300;
    		$("html,body").animate({scrollTop:a},"slow");
    	},150)
    })
//    $(".alertCon").on("click",function(e){    	
//    	if($(e.target).hasClass("infoConnect")){
//    		e ? e.stopPropagation() : event.cancelBubble = true;
//    	}
//    	
//    })
