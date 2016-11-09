var urlLabel = GetRequestLabel();//标签信息
var word = GetRequest().clueWord;//关键词
var nowPage = GetRequest().currentPage;//页码
$('#nav_ser').val(word);

function resSer() {
    word = $.trim($('#nav_ser').val());
    if(word) {
        nowPage = 1;
        var url="result?clueWord=" + escape(word)+"&pageSize=20&currentPage=1";
        if(urlLabel) url=url+"#"+JSON.stringify(urlLabel);
        history.pushState && history.pushState({title: word,pagenumber:1}, word, url);
        getResult(word, 20, nowPage,urlLabel);
    }

}
$('.head-search').click(function() {//搜索按钮
    resSer();
});
$('#nav_ser').keyup(function(event) {//搜索框回车
    if(event.keyCode == "13") {
        resSer();
    };
});
//换一批
$('.hot-next').bind('click', function() {//下一页
    nowPage++;
    var url="result?clueWord=" + escape(word)+"&pageSize=20&currentPage="+nowPage;
    if(urlLabel) url=url+"#"+JSON.stringify(urlLabel);
    history.pushState && history.pushState({title: word,pagenumber:nowPage}, word, url);
    getResult(word, 20, nowPage,urlLabel);
});
$('.hot-prev').bind('click', function() {//上一页
    if(nowPage>1){
        nowPage--;
        var url="result?clueWord=" + escape(word)+"&pageSize=20&currentPage="+nowPage;
        if(urlLabel) url=url+"#"+JSON.stringify(urlLabel);
        history.pushState && history.pushState({title: word,pagenumber:nowPage}, word, url);
        getResult(word, 20, nowPage,urlLabel);
    }       
});

// 监听popstate事件
history.pushState && window.addEventListener("popstate", function(e) {
    // 获取history.state对象中的状态信息
    // 在这里state将自动成为event的子对象，可直接通过event.state访问
    var currWord = GetRequest().clueWord; //拆分url得到”=”后面的参数
    var currPage = GetRequest().currentPage; //拆分url得到”=”后面的参数 ;
    var currLabel = GetRequestLabel();//标签信息
    if(currWord&&currPage){
        $('#nav_ser').val(currWord);
        getResult(currWord, 20, currPage,currLabel);
    }
}, false);

getResult(word, 20, nowPage,urlLabel);
function getResult(clueWord, pageSize, currentPage,labeInfo) {
    var data={}
    if(labeInfo){
        data={
                age:labeInfo.Age?_.pluck(labeInfo.Age, 'id'):[],
                gender:labeInfo.Gender?_.pluck(labeInfo.Gender, 'id'):[],
                education:labeInfo.Education?_.pluck(labeInfo.Education, 'id'):[],
                area:labeInfo.Area?_.pluck(labeInfo.Area, 'id'):[],
                eventClass:labeInfo.Even?_.pluck(labeInfo.Even, 'id'):[],
                userClass:labeInfo.UserClass?_.pluck(labeInfo.UserClass, 'id'):[]   
        };
    } 
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: dataUrl.util.getResultList(clueWord, pageSize, currentPage),
        data:JSON.stringify(data),
        success: function(returnData) {
            if(returnData.error.code == 0) {
                $("#canvas .topic").remove();
                $(".word").remove()
                var clueWord1 = decodeURI(clueWord);
                $("<div class='word wordwidth'>"+getSubstr(word)+"</div>").appendTo($("#canvas"));
                result = _.sortBy(returnData.data, function(item) {
                    return -item.score
                });
                drawWord(result);
            } else {
                $("<span class='word wordblack'>获取话题列表失败</span>").appendTo($("#canvas"));
            }
        },
        error: function() {
            console.log('获取话题列表失败');
        }
    });
}
//高级搜索弹窗。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
	
	if(location.hash){
		console.log(location.hash);
		console.log(typeof(location.hash));
		var str = location.hash;
		var objString = str.substr(1);
		console.log(objString);
		//console.log(JSON.parse(objString).Even);
		
		var evenSelect = JSON.parse(objString).Even;
		$('#result_label_even').innerHTML = '';
		if(evenSelect.length <=0 && evenSelect){
			$('#result_evet_con').addClass('hidecommon');
		}else{
			var titleEven = '';
			$.each(evenSelect,function(i,item){
				titleEven += item.name;
				$('#result_label_even').attr('title',titleEven)
		    	$('#result_label_even').append('<i data-id="'+item.id+'">'+item.name+'</i>');
			});
			$('#result_evet_con').removeClass('hidecommon');
		};	
		
		
		var genderSelect = JSON.parse(objString).Gender;
		$('#result_label_gender').innerHTML = '';
		if(genderSelect.length<=0){
			$('#result_label_gender').addClass('hidecommon');
		}else{
			var titleGender = '';
			$.each(genderSelect,function(i,item){
				titleGender += item.name;
				$('#result_label_gender').attr('title',titleGender)
		    	$('#result_label_gender').append('<i data-id="'+item.id+'">'+item.name+'</i>');
			});
			$('#result_label_gender').removeClass('hidecommon')
		};
		
		var ageSelect = JSON.parse(objString).Age;
		$('#result_label_age').innerHTML = '';
		if(ageSelect.length<=0){
			$('#result_label_age').addClass('hidecommon');
		}else{
		var titleAge = '';
		$.each(ageSelect,function(i,item){
			titleAge += item.name;
			$('#result_label_age').attr('title',titleAge)
	    	$('#result_label_age').append('<i data-id="'+item.id+'">'+item.name+'</i>');
		});
			$('#result_label_age').removeClass('hidecommon');
		};
		
		var educationSelect = JSON.parse(objString).Education;
		$('#result_label_education').innerHTML = '';
		if(educationSelect.length<=0){
			$('#result_label_education').addClass('hidecommon');
		}else{
			var titleEducation = '';
			$.each(educationSelect,function(i,item){
				titleEducation += item.name;
				$('#result_label_education').attr('title',titleEducation)
		    	$('#result_label_education').append('<i data-id="'+item.id+'">'+item.name+'</i>');
			});
			$('#result_label_education').removeClass('hidecommon');
		};
		
		var areaSelect = JSON.parse(objString).Area;
		$('#result_label_area').innerHTML = '';
		if(areaSelect<=0){
			$('#result_label_area').addClass('hidecommon');
		}else{
			var titleArea = '';
			$.each(areaSelect,function(i,item){
				titleArea += item.name;
				$('#result_label_area').attr('title',titleArea)
		    	$('#result_label_area').append('<i data-id="'+item.id+'">'+item.name+'</i>');
			});
			$('#result_label_area').removeClass('hidecommon');
		};
		
		var UserClassSelect = JSON.parse(objString).UserClass;
		$('#result_label_userClass').innerHTML = '';
		if(UserClassSelect.length<=0){
			$('#result_label_userClass').addClass('hidecommon');
		}else{
			var titleuser = '';
			$.each(UserClassSelect,function(i,item){
				titleuser += item.name;
				$('#result_label_userClass').attr('title',titleuser)
		    	$('#result_label_userClass').append('<i data-id="'+item.id+'">'+item.name+'</i>');
			});
			$('#result_label_userClass').removeClass('hidecommon');
		};
		
	}else{
		$('#result_evet_con').addClass('hidecommon');
	};
	if($('#result_evet_persn').find('i').length<=0){
		$('#result_evet_persn').addClass('hidecommon');
	}else{
		$('#result_evet_persn').removeClass('hidecommon');
	};
	
	labelList();
	

	
		
	$('#result_filter').on('click',function(){
		$('#ser_dialog').removeClass('hidecommon');
	});
	
	$('.dialog_area .ser_dialog_close').on('click',function(){
		//dialogInit();
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
			url:'api/topicclass/getsearchitem',
			success:function(returnData){
				console.log(returnData);
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
					fillData($(".eventDialogTab"),$(".eventTab"),eventTemp);
					fillData($(".eventDialogTab2"),$(".eventTab2"),eventTemp2);
					fillData($(".userDialogTab"),$(".personTab"),userData);
//					//渲染弹窗。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。ser_dialog
//					//var result_t_r = $('#result_t_r').find('i')
//					if($('#result_t_r').find('i').length>=0){
//						$('#result_t_r i').each(function(i,item){
//							var dataid = $(this).attr('data-id');
//							$('#inp'+dataid).prop("checked",true);
//						})
//					};
					var  inpflag = 0;
					$('.dialog_inp_num').each(function(index,item){
						if($(this).text()!=='0'){
							inpflag++;
							if(inpflag == 1){
								$(this).parent().click();
							}			
							$(this).css('display','block');
						}
					})
					
				}
				
			},
			error:function(){
				console.log('获取标签列表失败');
			}
		});
	};
	
	//高级探索弹窗搜索
	$('#dialog_ser_to').on('click',function(){
		var val = $.trim($('#dialog_ser_text').val());
		var data={
			clueWord:'北京',
			pageSize:20,
			currentPage:1
		}
			$.ajax({
				type:"post",
				data:JSON.stringify(data),
				contentType: 'application/json',
//			    dataType:"json",
				url:'api/topic/getlist',
				success:function(result){
					console.log(result)					
				},
				error:function(){
					alert('失败了')
				}
			});
		
	});
	
	function fillData(selector,selector2,data){
		$.each(data,function(index,item){
			var childs = item.childs;
			if(childs){
				var str = '<ul class="hidecommon"> <li class="inp_ch_list fl">';
				var lenNum = 0;
				$.each(childs,function(index,item){
					//渲染弹窗。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。ser_dialog
					//var result_t_r = $('#result_t_r').find('i')
					var flag="";
					if($('#result_t_r').find('i').length>=0){
						$('#result_t_r i').each(function(){
							var dataid = $(this).attr('data-id');
							if(item.id==dataid){
								flag="checked";
								lenNum++;
								return false;
							}
						})
					};
					str += '<label><input type="checkbox" data-id="'+item.id+'" id="inp'+item.id+'" '+flag+'>'+item.name+'</label>'
				})
				str += '</li> <li class="inp_select_all fr"> <label><input type="checkbox">全选</label> </li> </ul>';
			}else{
				var str = '<ul class="hidecommon"> <li class="inp_ch_list fl">';
				str += '</li> <li class="inp_select_all fr"> <label><input type="checkbox">全选</label> </li> </ul>';
			}
			selector.append('<li class="pst"><em  data-id="'+item.id+'" >'+item.name+'</em><span class="pos dialog_inp_num">'+lenNum+'</span></li>');
			
			
			
			selector2.append(str);
		})
		
		
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
			
			
			console.log($('.cor389b9f').find('em').text())
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
//			var textStr = $('#inp_data_person1 i:last').text();
//			if(textStr.indexOf('、')>-1){
//				var newStr = textStr.substring(0,textStr.length-1)
//				$('#inp_data_person1 i:last').text(newStr);
//			}
//			
			
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
		$(this).parent().find('i').remove();
		//console.log($(this).parent().attr('class'));
		if($(this).parent().attr('class').indexOf('person_sec')>=0){
			$('.userDialogTab li').eq(0).find('.dialog_inp_num').css('display','none');
			$('.userDialogTab li').eq(0).find('.dialog_inp_num').text(0);
			$('.personTab').find('ul').eq(0).find('input').prop("checked",false);
		}
		if($(this).parent().attr('class').indexOf('person_education')>=0){
			$('.userDialogTab li').eq(1).find('.dialog_inp_num').css('display','none');
			$('.userDialogTab li').eq(1).find('.dialog_inp_num').text(0);
			$('.personTab').find('ul').eq(1).find('input').prop("checked",false);
		}
		if($(this).parent().attr('class').indexOf('person_area')>=0){
			$('.userDialogTab li').eq(2).find('.dialog_inp_num').css('display','none');
			$('.userDialogTab li').eq(2).find('.dialog_inp_num').text(0);
			$('.personTab').find('ul').eq(2).find('input').prop("checked",false);
		}
		if($(this).parent().attr('class').indexOf('person_interest')>=0){
			$('.userDialogTab li').eq(3).find('.dialog_inp_num').css('display','none');
			$('.userDialogTab li').eq(3).find('.dialog_inp_num').text(0);
			$('.personTab').find('ul').eq(3).find('input').prop("checked",false);
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
		//dialogInit()
	});
	//dialogInit();
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
	};

/*显示热点图*/
function drawWord(data) {
    var pointArr = [];
    $.each(data, function(idx, item) {
        if(idx%2!=0){
            var r = 163 + (idx-1) * 16;
        }else{
            var r = 163 + idx * 16;
        }
        var fontClass = "fontClass1",
            sizeClass = "sizeClass1";
        if(item.readNum<= 50) {
            fontClass = "fontClass1";
            sizeClass = "sizeClass1";
        } else if(item.readNum<= 80) {
            fontClass = "fontClass2";
            sizeClass = "sizeClass2";
        } else if(item.readNum<= 90) {
            fontClass = "fontClass3";
            sizeClass = "sizeClass3";
        } else if(item.readNum<= 100) {
            fontClass = "fontClass4";
            sizeClass = "sizeClass4";
        }
        $item = $("<div class='topic'>" +
                "<span class='icon " + sizeClass + "'>"+item.readNum+"</span><span class='link " + fontClass + "'>" + item.title + "</span></div>").appendTo($("#canvas"));
        var itemWidth = $item.width();
        var itemHeight = $item.height();
        if(itemWidth > 180) {
            itemWidth = 180;
            $item.find(".link").css({
                "display": "block",
                "width": "180px"
            }).addClass("word-ellipsis");
        }
        var itemPos = checkao(idx, r, itemWidth, itemHeight, pointArr);
        pointArr.push({
            height: itemHeight,
            width: itemWidth,
            left: itemPos.left,
            top: itemPos.top
        });
        $item.css({
            "left": itemPos.left,
            "top": itemPos.top
        }).animate({
            "opacity": 1
        }, 300 + idx * 100);
    });
}
/*获得显示位置*/
function checkao(idx, r, itemWidth, itemHeight, pointArr) {
    var ao = 0;
    var elem = {
        width: itemWidth,
        height: itemHeight,
        left: 0,
        top: 0
    };
    do {
        if(idx % 2 == 0) {
            ao = (Math.floor(Math.random() * 10) % 2 == 0) ? Math.random() * (90 - 0 + 1) + 0 : Math.random() * (360 - 270 + 1) + 270;
        } else {
            ao = Math.random() * (270 - 90 + 1) + 90;
        }
        elem.left = 535 + r * Math.cos(ao * 3.14 / 180) - itemWidth / 2;
        elem.top = 238 + r * Math.sin(ao * 3.14 / 180) - itemHeight / 2;
    } while (hitTest(elem, pointArr) || elem.top + itemHeight > 476 || elem.top < 0)
    return elem;
}
/*检查重叠*/
function hitTest(elem, pointArr) {
    var overlapping = function(a, b) {
        if(Math.abs(2 * a.left + a.width - 2 * b.left - b.width) < a.width + b.width) {
            if(Math.abs(2 * a.top + a.height - 2 * b.top - b.height) < a.height + b.height) {
                return true;
            }
        }
        return false;
    };
    var i = 0;
    for(i = 0; i < pointArr.length; i++) {
        if(overlapping(elem, pointArr[i])) {
            return true;
        }
    }
    return false;
};
//hover事件
$(document).delegate('.topic','mouseover',function(){
    if($(this).find('.link').hasClass("word-ellipsis")){
        $(this).css("z-index","9").find('.link').removeClass("word-ellipsis").css({"width":"auto"});
        $(this).css("left",$(this).position().left-($(this).width()-180)/2)
    }
});

$(document).delegate('.topic','mouseout',function(){
    if($(this).find('.link').width()>180){
        $(this).css("left",$(this).position().left+($(this).width()-180)/2)
        $(this).css("z-index","0").find('.link').css("width","180px").addClass("word-ellipsis");
    }
});

//编辑关键字
$(document).delegate(".edit-word","click",function(){
    var content = $("<input type='text' class='txt-word' placeholder='请输入关键词' value="+word+">");
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
                resSer();
                $(".popMask").hide();
            }
        }]
    });
}).delegate(".topic", "click", function(e) {/*点击显示弹窗*/
    e ? e.stopPropagation() : event.cancelBubble = true;
    var left = $(this).position().left + $(this).width() / 2 - 207;
    var top = $(this).position().top + $(this).find(".icon").height() + 16;
    $(".alertCon").css({
        'position': 'absolute',
        'top': top,
        'left': left,
        'z-index': 9999,
        'display': 'block'
    });
}).delegate(".alertCon", "click", function(e) {//弹窗内部防止冒泡
    e ? e.stopPropagation() : event.cancelBubble = true;
});
$(document).on('click', function() {//点击任意地方隐藏弹窗
    $('.alertCon').css('display', 'none');
});
//截取字符串
function getSubstr(str){
    if(str.length>8){
        return str.substring(0,7)+"…";
    }else{
        return str;
    }
}

