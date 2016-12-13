var urlLabel = GetRequestLabel();//标签信息
var word = GetRequest().clueWord;//关键词
var nowPage = GetRequest().currentPage;//页码
$('#nav_ser').val(word);
console.log(urlLabel);

function resSer() {
	$('#result_evet_con').addClass('hidecommon');
	$('#result_evet_persn').addClass('hidecommon');
	$('#result_label_even i').remove();
	$('#result_evet_persn i').remove();
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
	$('#hot_age1').val('');
	$('#hot_age2').val('');
    var newWord=$.trim($('#nav_ser').val());
    if(newWord && word!=newWord){
        word=newWord;
        nowPage = 1;
        urlLabel=null;
        var url="hotresult?clueWord=" + escape(word)+"&pageSize=20&currentPage=1";
        history.pushState && history.pushState({title: word,pagenumber:1}, word, url);
        getResult(word, 20, nowPage,null);
    }
}
$('.head-search').click(function() {//搜索按钮
    //resSer();
	
	resSer() ;
});
$('#nav_ser').keyup(function(event) {//搜索框回车	
    if(event.keyCode == "13") {
        resSer();
    };
});
$("#nav_ser").focus(function(){
	$(".alertCon").hide();
})
//换一批
$(document).delegate('.hot-next','click', function() {//下一页
    var pageCount=$(this).attr("data-pageCount")?parseInt($(this).attr("data-pageCount")):0;
    if(nowPage<pageCount){
        nowPage++;
        var url="hotresult?clueWord=" + escape(word)+"&pageSize=20&currentPage="+nowPage;
        if(urlLabel) url=url+"#"+JSON.stringify(urlLabel);
        history.pushState && history.pushState({title: word,pagenumber:nowPage}, word, url);
        getResult(word, 20, nowPage,urlLabel);
    }
}).delegate('.hot-prev','click', function() {//上一页
    if(nowPage>1){
        nowPage--;
        var url="hotresult?clueWord=" + escape(word)+"&pageSize=20&currentPage="+nowPage;
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
        urlLabel = GetRequestLabel();//标签信息
    if(currWord&&currPage){
        word=currWord;
        nowPage=currPage;
        $('#nav_ser').val(currWord);
        getResult(currWord, 20, currPage,urlLabel);
    };
    	resultLabel();
    	labelList();
    	resultDia();
    
    
}, false);

getResult(word, 20, nowPage,urlLabel);
function getResult(clueWord, pageSize, currentPage,labeInfo) {
    var data={}
    if(labeInfo){
        data={
            age:labeInfo.Age?labeInfo.Age:[],
            gender:labeInfo.Gender?_.pluck(labeInfo.Gender, 'id'):[],
            education:labeInfo.Education?_.pluck(labeInfo.Education, 'id'):[],
            area:labeInfo.Area?_.pluck(labeInfo.Area, 'id'):[],
            eventClass:labeInfo.Even?_.pluck(labeInfo.Even, 'id'):[],
            userClass:labeInfo.UserClass?_.pluck(labeInfo.UserClass, 'id'):[]   
        };
    } 
    $(".result-loading").height($(document).height()).css("display","block");
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: dataUrl.util.getResultList(clueWord, pageSize, currentPage),
        data:JSON.stringify(data),
        success: function(returnData) {
            $(".result-loading").css("display","none");
            if(returnData.error.code == 0) {
            	console.log(returnData.data.data)
            	if(returnData.data.data.length == 0){
            		if(labeInfo){
                        $(".result-error").find(".content-title").text("无探索结果，请尝试更换关键词或筛选条件");
                    }else{
                        $(".result-error").find(".content-title").text("无探索结果，请尝试更换关键词重新探索");
                    }
            		 $(".result-content").css("display","none");
                     $(".result-error").css("display","block");
            	}else{
	                $(".result-content").css("display","block");
	                $(".result-error").css("display","none");
	                $("#canvas .topic").remove();
	                $(".word").remove();
	                $("<div class='word wordwidth'>"+word+"</div>").appendTo($("#canvas"));
	                var pageCount=returnData.data&&returnData.data.pageCount?returnData.data.pageCount:0;
	                $(".hot-next").attr("data-pageCount",pageCount);
	                if(nowPage<pageCount){
	                    $(".hot-next").removeClass("disabled").addClass("abled");
	                }else{
	                    $(".hot-next").removeClass("abled").addClass("disabled");
	                }
	                if(nowPage>1){
                        $(".hot-prev").removeClass("disabled").addClass("abled");
                    }else{
                        $(".hot-prev").removeClass("abled").addClass("disabled");
                    }
	                result = _.sortBy(returnData.data.data, function(item) {
	                    return -item.score
	                });
	                drawWord(result);
            	};   
            } else {
                if(labeInfo){
                    $(".result-error").find(".content-title").text("无探索结果，请尝试更换关键词或筛选条件");
                }else{
                    $(".result-error").find(".content-title").text("无探索结果，请尝试更换关键词重新探索");
                }
                $(".result-content").css("display","none");
                $(".result-error").css("display","block");
            }
            
        },
        error: function() {
            console.log('获取热点失败');
        }
    });
}
    resultLabel();
	labelList();
	resultDia();
	function resultDia(){
		if(urlLabel){
		var evenSelect = urlLabel.Even;
		$('#inp_data_event').find('i').remove();
		if(evenSelect.length <=0 && evenSelect){
			$('#inp_data_event').addClass('hidecommon');
		}else{
			var titleEven = '';
			$.each(evenSelect,function(i,item){
				titleEven += item.name;
				$('#inp_data_event').attr('title',titleEven)
		    	$('#inp_data_event').append('<i data-id="'+item.id+'">'+item.name+'</i>');
			});
			$('#inp_data_event').removeClass('hidecommon');
			$('.dialog_inp_c').removeClass('hidecommon');
		};	
		var genderSelect = urlLabel.Gender;
		$('.person_sec').find('i').remove();
		if(genderSelect.length<=0){
			$('.person_sec').addClass('hidecommon');
		}else{
			var titleGender = '';
			$.each(genderSelect,function(i,item){
				titleGender += item.name;
				$('.person_sec').attr('title',titleGender)
		    	$('.person_sec').append('<i data-id="'+item.id+'">'+item.name+'</i>');
			});
			$('.person_sec').removeClass('hidecommon');
			$('#inp_data_person1').removeClass('hidecommon');
			$('.dialog_inp_c').removeClass('hidecommon');
		};
		
		
		var educationSelect = urlLabel.Education;
		$('.person_education').find('i').remove();
		if(educationSelect.length<=0){
			$('.person_education').addClass('hidecommon');
		}else{
			var titleEducation = '';
			$.each(educationSelect,function(i,item){
				titleEducation += item.name;
				$('.person_education').attr('title',titleEducation)
		    	$('.person_education').append('<i data-id="'+item.id+'">'+item.name+'</i>');
			});
			$('.person_education').removeClass('hidecommon');
			$('#inp_data_person1').removeClass('hidecommon');
			$('.dialog_inp_c').removeClass('hidecommon');
		};
		
		var areaSelect = urlLabel.Area;
		$('.person_area').find('i').remove();
		if(areaSelect<=0){
			$('.person_area').addClass('hidecommon');
		}else{
			var titleArea = '';
			$.each(areaSelect,function(i,item){
				titleArea += item.name;
				$('.person_area').attr('title',titleArea)
		    	$('.person_area').append('<i data-id="'+item.id+'">'+item.name+'</i>');
			});
			$('.person_area').removeClass('hidecommon');
			$('#inp_data_person1').removeClass('hidecommon');
			$('.dialog_inp_c').removeClass('hidecommon');
		};
		
		var UserClassSelect = urlLabel.UserClass;
		$('.person_interest').find('i').remove();
		if(UserClassSelect.length<=0){
			$('.person_interest').addClass('hidecommon');
		}else{
			var titleuser = '';
			$.each(UserClassSelect,function(i,item){
				titleuser += item.name;
				$('.person_interest').attr('title',titleuser)
		    	$('.person_interest').append('<i data-id="'+item.id+'">'+item.name+'</i>');
			});
			$('.person_interest').removeClass('hidecommon');
			$('#inp_data_person1').removeClass('hidecommon');
			$('.dialog_inp_c').removeClass('hidecommon');
		};
		
		}else{
			$('#ser_dialog').find('input').prop('checked',false);
			$('#ser_dialog').find('.dialog_inp_num').text('0');
			$('#ser_dialog').find('.dialog_inp_num').css('display','none'); 
			$('#ser_dialog .dialog_tab li').removeClass('cor389b9f');
			$('#ser_dialog .dialog_tab li').removeClass('hot_arrow_up');
			$('.dialog_inp_c_data').find('i').remove();
			$('.dialog_inp_c').addClass('hidecommon');
		};
//		if($('#result_evet_persn').find('i').length<=0){
//			$('#result_evet_persn').addClass('hidecommon');
//		}else{
//			$('#result_evet_persn').removeClass('hidecommon');
//		};
	
	};
	
	function resultLabel(){
		if(urlLabel){
		var evenSelect = urlLabel.Even;
		$('#result_label_even').find('i').remove();
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
			$('.dialog_inp_c').removeClass('hidecommon');
		};	
		var genderSelect = urlLabel.Gender;
		$('#result_label_gender').find('i').remove();
		if(genderSelect.length<=0){
			$('#result_label_gender').addClass('hidecommon');
		}else{
			var titleGender = '';
			$.each(genderSelect,function(i,item){
				titleGender += item.name;
				$('#result_label_gender').attr('title',titleGender)
		    	$('#result_label_gender').append('<i data-id="'+item.id+'">'+item.name+'</i>');
			});
			$('#result_label_gender').removeClass('hidecommon');
		};
		
		var ageSelect = urlLabel.Age;
		$('#result_label_age').find('i').remove();
		if(ageSelect.length<=0){
			$('#result_label_age').addClass('hidecommon');
		}else{
			if(ageSelect.length ==1){
				$('#result_label_age').append('<i>'+ageSelect[0]+'岁</i>');
			}
			if(ageSelect.length ==2){
				$('#result_label_age').append('<i>'+ageSelect[0]+'岁-</i>');
				$('#result_label_age').append('<i>'+ageSelect[1]+'岁</i>');
			}
			$('#result_label_age').removeClass('hidecommon');
		};
		
		var educationSelect = urlLabel.Education;
		$('#result_label_education').find('i').remove();
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
			$('#inp_data_person1').removeClass('hidecommon');
		};
		
		var areaSelect = urlLabel.Area;
		$('#result_label_area').find('i').remove();
		if(areaSelect.length<=0){
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
		
		var UserClassSelect = urlLabel.UserClass;
		$('#result_label_userClass').find('i').remove();
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
			$('#result_t_r').find('i').remove();
			$('#result_evet_persn').addClass('hidecommon');
			$('#result_evet_con').addClass('hidecommon');
		};
		if($('#result_evet_persn').find('i').length<=0){
			$('#result_evet_persn').addClass('hidecommon');
		}else{
			$('#result_evet_persn').removeClass('hidecommon');
		};
	};
	
	function resNewSer() {
	    
		if(urlLabel) {
	        nowPage = 1;
	        var url="hotresult?clueWord=" + escape(word)+"&pageSize=20&currentPage=1";
	        if(urlLabel) url=url+"#"+JSON.stringify(urlLabel);
	        history.pushState && history.pushState({title: word,pagenumber:1}, word, url);
	        getResult(word, 20, nowPage,urlLabel);
	    }

	}
	//top 删除事件标签
	$('#result_label_even').delegate('span','click',function(){
		urlLabel.Even = [];
		resNewSer();
		resultLabel();
		resultDia();
		$('.eventDialogTab').find('.dialog_inp_num').text('0');
		$('.eventDialogTab').find('.dialog_inp_num').css('display','none');
		$('.eventDialogTab2').find('.dialog_inp_num').text('0');
		$('.eventDialogTab2').find('.dialog_inp_num').css('display','none');
		$('.dialog_tab_event').find('input').prop('checked',false);
	});
	//top 删除性别标签
	$('#result_label_gender').delegate('span','click',function(){
		urlLabel.Gender = [];
		resNewSer();
		resultLabel();
		resultDia();
		$('.userDialogTab .dialog_inp_num').eq(1).text(0);
		$('.userDialogTab .dialog_inp_num').eq(1).css('display','none');
		$('.dialog_tab_person ul').eq(1).find('input').prop('checked',false);
		$('.dialog_tab_person ul').eq(1).addClass('hidecommon');
		$('.userDialogTab li').eq(1).removeClass('cor389b9f');
		$('.userDialogTab li').eq(1).find('.dialog_inp_num').text('0');
		$('.userDialogTab li').eq(1).find('.dialog_inp_num').css('display','none');
		$('.userDialogTab li').eq(1).removeClass('hot_arrow_up');
		if($('#result_evet_persn').find('i').length<=0){
			$('#result_evet_persn').addClass('hidecommon');
		};
		
	});
	//top 删除教育标签
	$('#result_label_education').delegate('span','click',function(){
		urlLabel.Education = [];
		resNewSer();
		resultLabel();
		resultDia();
		$('.userDialogTab .dialog_inp_num').eq(2).text(0);
		$('.userDialogTab .dialog_inp_num').eq(2).css('display','none');
		$('.dialog_tab_person ul').eq(2).find('input').prop('checked',false);
		$('.dialog_tab_person ul').eq(2).addClass('hidecommon');
		$('.userDialogTab li').eq(2).removeClass('cor389b9f');
		$('.userDialogTab li').eq(2).removeClass('hot_arrow_up');
		$('.userDialogTab li').eq(2).find('.dialog_inp_num').text('0');
		$('.userDialogTab li').eq(2).find('.dialog_inp_num').css('display','none');
		if($('#result_evet_persn').find('i').length<=0){
			$('#result_evet_persn').addClass('hidecommon');
		};
		
	});
	
	//top 删除地区标签
	$('#result_label_area').delegate('span','click',function(){
		urlLabel.Area = [];
		resNewSer();
		resultLabel();
		resultDia();
		$('.userDialogTab .dialog_inp_num').eq(3).text(0);
		$('.userDialogTab .dialog_inp_num').eq(3).css('display','none');
		$('.dialog_tab_person ul').eq(3).find('input').prop('checked',false);
		$('.dialog_tab_person ul').eq(3).addClass('hidecommon');
		$('.userDialogTab li').eq(3).removeClass('cor389b9f');
		$('.userDialogTab li').eq(3).removeClass('hot_arrow_up');
		$('.userDialogTab li').eq(3).find('.dialog_inp_num').text('0');
		$('.userDialogTab li').eq(3).find('.dialog_inp_num').css('display','none');
		if($('#result_evet_persn').find('i').length<=0){
			$('#result_evet_persn').addClass('hidecommon');
		};
		
	});
	//top 删除兴趣爱好标签
	$('#result_label_userClass').delegate('span','click',function(){
		urlLabel.UserClass = [];
		resNewSer();
		resultLabel();
		resultDia();
		$('.userDialogTab .dialog_inp_num').eq(4).text(0);
		$('.userDialogTab .dialog_inp_num').eq(4).css('display','none');
		$('.dialog_tab_person ul').eq(4).find('input').prop('checked',false);
		$('.dialog_tab_person ul').eq(4).addClass('hidecommon');
		$('.userDialogTab li').eq(4).removeClass('cor389b9f');
		$('.userDialogTab li').eq(4).removeClass('hot_arrow_up');
		$('.userDialogTab li').eq(4).find('.dialog_inp_num').text('0');
		$('.userDialogTab li').eq(4).find('.dialog_inp_num').css('display','none');
		if($('#result_evet_persn').find('i').length<=0){
			$('#result_evet_persn').addClass('hidecommon');
		};
		
	});
	//top 删除年龄标签
	$('#result_label_age').delegate('span','click',function(){
		urlLabel.Age = [];
		resNewSer();
		resultLabel();
		resultDia();
		$('#hot_age1').val('');
		$('#hot_age2').val('');
		$('.dialog_tab_person ul').eq(0).addClass('hidecommon');
		$('.userDialogTab li').eq(0).removeClass('cor389b9f');
		$('.userDialogTab li').eq(0).removeClass('hot_arrow_up');
		if($('#result_evet_persn').find('i').length<=0){
			$('#result_evet_persn').addClass('hidecommon');
		};
		
	});
	//弹窗确定按钮事件
	$('#dislog_btn_sure').on('click',function(){
	console.log(urlLabel)
	if(urlLabel){
		urlLabel.Even=[];
		urlLabel.Area=[];
		urlLabel.Age=[];
		urlLabel.Education=[];
		urlLabel.Gender=[];
		urlLabel.UserClass=[];
	}else{
	    urlLabel = {
				Even:[],
				Area:[],
				Age:[],
				Education:[],
				Gender:[],
				UserClass:[]
		};
		var hash = JSON.stringify(urlLabel);
	};	
		
		if($('#inp_data_event').is('.hidecommon')){
		}else{
			var list = $('#inp_data_event').find('i');
			$(list).each(function(i,item){
				var dataId = $(this).attr('data-id');
				var dataText = $(this).text();
				urlLabel.Even.push({id:dataId,name:dataText})
			});
		};
		if($('.person_sec').is('.hidecommon')){
		}else{
			var list = $('.person_sec').find('i');
			$(list).each(function(i,item){
				var dataId = $(this).attr('data-id');
				var dataText = $(this).text();
				urlLabel.Gender.push({id:dataId,name:dataText})
			});
		};
		
		if($('.person_area').is('.hidecommon')){
		}else{
			var list = $('.person_area').find('i');
			$(list).each(function(i,item){
				var dataId = $(this).attr('data-id');
				var dataText = $(this).text();
				urlLabel.Area.push({id:dataId,name:dataText})
			});
		};
		if($('.person_education').is('.hidecommon')){
		}else{
			var list = $('.person_education').find('i');
			$(list).each(function(i,item){
				var dataId = $(this).attr('data-id');
				var dataText = $(this).text();
				urlLabel.Education.push({id:dataId,name:dataText})
			});
		};
		if($('.person_interest').is('.hidecommon')){
		}else{
			var list = $('.person_interest').find('i');
			$(list).each(function(i,item){
				var dataId = $(this).attr('data-id');
				var dataText = $(this).text();
				urlLabel.UserClass.push({id:dataId,name:dataText})
			});
		};
		
		var ageVal1 = $('#hot_age1').val(),
			ageVal2 = $('#hot_age2').val();
		if(ageVal1){
			urlLabel.Age.push(ageVal1);
		}
		if(ageVal2){
			urlLabel.Age.push(ageVal2);
		}
		resNewSer();
		resultLabel();
		labelList();
		resultDia();
		$('#ser_dialog').addClass('hidecommon');
	});
	
	$('#result_filter').on('click',function(){
		$('#ser_dialog').find('input').prop('checked',false);
		$('#ser_dialog').find('.dialog_inp_num').text('0');
		$('#ser_dialog').find('.dialog_inp_num').css('display','none'); 
		$('#ser_dialog .dialog_tab li').removeClass('cor389b9f');
		$('#ser_dialog .dialog_tab li').removeClass('hot_arrow_up');
		$('.dialog_inp_c_data').find('i').remove();
		$('.dialog_inp_c').addClass('hidecommon');
		//resultLabel();
    	labelList();
    	resultDia();
//		var  inpflag = 0;
//		setTimeout(function(){
//			$('.dialog_inp_num').each(function(index,item){
//			
//				if($(this).text()!=='0'){
//					inpflag++;
//					if(inpflag == 1){
//						var ele1 = $(this).parent().parent();
//						var ele2 = $(this).parent();
//						ele2.addClass('cor389b9f'); 
//						ele2.addClass('hot_arrow_up'); 
//						var currentIndex = ele2.index();
//						ele1.next().find("ul:eq("+currentIndex+")").removeClass('hidecommon');
//					}			
//					$(this).css('display','block');
//				}
//			})
//			
//		},5000);
		
		$('#ser_dialog').removeClass('hidecommon');
	});
	
	$('.dialog_area .ser_dialog_close').on('click',function(){
		//dialogInit();
		$('#ser_dialog').addClass('hidecommon');
		$('.dislog_inp_con').find('ul').addClass('hidecommon');
		$('.dialog_tab').find('li').removeClass('cor389b9f');
		$('.dialog_tab').find('li').removeClass('hot_arrow_up'); 
//		resultLabel();
//		resultDia();
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
					if(urlLabel){
						var ageSelect = urlLabel.Age;
						$('#hot_dia_age').find('i').remove();
						if(ageSelect.length<=0){
							$('#hot_dia_age').addClass('hidecommon');
						}else if(ageSelect==1){
								$('#hot_age1').val(ageSelect[0]);
							}else{
								console.log(ageSelect[0])
								$('#hot_age1').val(ageSelect[0]);
								$('#hot_age2').val(ageSelect[1]);
						}
					};
					var  inpflag = 0;
						$('.dialog_inp_num').each(function(index,item){
						
							if($(this).text()!=='0'){
								inpflag++;
								if(inpflag == 1){
									var ele1 = $(this).parent().parent();
									var ele2 = $(this).parent();
									ele2.addClass('cor389b9f'); 
									ele2.addClass('hot_arrow_up'); 
									var currentIndex = ele2.index();
									ele1.next().find("ul:eq("+currentIndex+")").removeClass('hidecommon');
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
				str += '</li> <li class="inp_select_all fr"> <label><input type="checkbox" style="margin-top:2px;">全选</label> </li> </ul>';
			}else{
				var str = '<ul class="hidecommon"> <li class="inp_ch_list fl">';
				str += '</li> <li class="inp_select_all fr"> <label><input type="checkbox"  style="margin-top:2px;">全选</label> </li> </ul>';
			}
			selector.append('<li class="pst"><em  data-id="'+item.id+'" >'+item.name+'</em><span class="pos dialog_inp_num">'+lenNum+'</span></li>');
			
			selector2.append(str);
		})
		
		
		};
		
		function fillDataBot(selector,selector2,data){
			selector.append('<li>年龄</li>');
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
					str += '</li> <li class="inp_select_all fr"> <label><input type="checkbox"  style="margin-top:2px;">全选</label> </li> </ul>';
				}else{
					var str = '<ul class="hidecommon"> <li class="inp_ch_list fl">';
					str += '</li> <li class="inp_select_all fr"> <label><input type="checkbox"  style="margin-top:2px;">全选</label> </li> </ul>';
				}
				selector.append('<li class="pst"><em  data-id="'+item.id+'" >'+item.name+'</em><span class="pos dialog_inp_num">'+lenNum+'</span></li>');
				
				selector2.append(str);
			})
			selector2.prepend('<ul class="hot_dia_age hidecommon" id="hot_dia_age"><div class="age_dia_con">'+
					'<input onkeyup="clearChat(this)" maxlength="3" id="hot_age1" type="text"><b>岁</b><b>至</b>'+
					'~<input  onkeyup="clearChat(this)" maxlength="3" id="hot_age2" type="text"><b>岁</b>'+
					'</div></ul>')
			
			};

	

	
	//事件标签点击
	$('.dialog_tab_event').delegate('.inp_ch_list input','click',function(){
		var dataId = $(this).attr('data-id');
		var num = Number($('.cor389b9f').find('span').text());
		//console.log(num)
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
		dialogInit();
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
		$('#hot_age1').val('');
		$('#hot_age2').val('');
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
        var prevailingTrend=item.prevailingTrend?parseInt(item.prevailingTrend):0;
        if(prevailingTrend<= 50) {
            fontClass = "fontClass1";
            sizeClass = "sizeClass1";
        } else if(prevailingTrend<= 80) {
            fontClass = "fontClass2";
            sizeClass = "sizeClass2";
        } else if(prevailingTrend<= 90) {
            fontClass = "fontClass3";
            sizeClass = "sizeClass3";
        } else if(prevailingTrend<= 100) {
            fontClass = "fontClass4";
            sizeClass = "sizeClass4";
        }
        $item = $("<div class='topic' data-id="+item.id+"></div>")
            .data("info",item).appendTo($("#canvas"));
        $("<span class='icon " + sizeClass + "'><div class='iconCircle'></div></span><span class='link " + fontClass + "'>" + item.title + "</span>").appendTo($item);
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
    if(!$(this).hasClass("active")){
        $(this).find(".icon").css("background-color","#389b9f").find(".iconCircle").css("display","block");
        $(this).find(".link").css("font-weight","bold");
        if($(this).find('.link').hasClass("word-ellipsis")){
            $(this).css("z-index","9").find('.link').removeClass("word-ellipsis").css({"width":"auto"});
            $(this).css("left",$(this).position().left-($(this).width()-180)/2)
        }
    }
}).delegate('.topic','mouseout',function(){
    if(!$(this).hasClass("active")){
        $(this).find(".icon").css("background-color","#a3a3a3").find(".iconCircle").css("display","none");
        $(this).find(".link").css("font-weight","normal");
        if($(this).find('.link').width()>180){
            $(this).css("left",$(this).position().left+($(this).width()-180)/2)
            $(this).css("z-index","0").find('.link').css("width","180px").addClass("word-ellipsis");
        }
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
                $(".popMask").remove();
            }
        }]
    });
}).delegate(".topic", "click", function(e) {/*点击显示弹窗*/
    e ? e.stopPropagation() : event.cancelBubble = true;
    var $activeItem=$(this).siblings("div.active");
    if($activeItem.length>0){
        $activeItem.removeClass("active")
        $activeItem.find(".icon").css("background-color","#a3a3a3").find(".iconCircle").css("display","none");
        $activeItem.find(".link").css("font-weight","normal");
        if($activeItem.find('.link').width()>180){
            $activeItem.css("left",$activeItem.position().left+($activeItem.width()-180)/2)
            $activeItem.css("z-index","0").find('.link').css("width","180px").addClass("word-ellipsis");
        }
    }
    $(this).addClass("active");
    var hotInfo=$(this).data("info");
    var _left=$(this).position().left;
    var _top=$(this).position().top;
    var width=$(this).width();
    if(_left+width/2>1070/2){
        left = _left-$(".alertCon").width()-10;
    }else{
        left = _left+width+10;
    }
    var top = (_top+$(this).height()/2) - $(".alertCon").height()/2;
    $(".alertCon").data("info",hotInfo);
    $(".alertCon").find(".infoTitle").text(hotInfo.title?hotInfo.title:"");
    $(".alertCon").find(".infoConnect").attr("data-id",hotInfo.id?hotInfo.id:"");
    $(".alertCon").find(".infoText").text(hotInfo.introduction?hotInfo.introduction:"").attr("title",hotInfo.introduction?hotInfo.introduction:"");
    $(".alertCon").find(".hotValue").text(hotInfo.prevailingTrend?hotInfo.prevailingTrend:0);
    $(".alertCon").find(".weibo-link").attr("href",hotInfo.topicUrl?hotInfo.topicUrl:"#");
    if(hotInfo.wechatUrl){
        $(".alertCon").find(".weixin-link").attr("href",hotInfo.wechatUrl).css("display","inline-block");
    }else{
        $(".alertCon").find(".weixin-link").css("display","none");
    }
    if(hotInfo.zhihuUrl){
        $(".alertCon").find(".zhihu-link").attr("href",hotInfo.zhihuUrl).css("display","inline-block");
    }else{
        $(".alertCon").find(".zhihu-link").css("display","none");
    }
    if(hotInfo.baiduUrl){
        $(".alertCon").find(".baidu-link").attr("href",hotInfo.baiduUrl).css("display","inline-block");
    }else{
        $(".alertCon").find(".baidu-link").css("display","none");
    }
    if(hotInfo.logoImgUrl){
        $(".alertCon").find(".portrait").css("background-image","url("+hotInfo.logoImgUrl+")");
    }else{
        $(".alertCon").find(".portrait").css("background-image","url(img/defaultIcon.png)");
    }
    var eventClass=hotInfo.eventClass;
    $(".alertCon").find(".hotLabel").html("");
    if(eventClass){
        var typeArr=$.trim(eventClass).split(",");
        $.each(typeArr,function(idx,val){
            if(idx>2) return false;
            if(idx==2){
                $(".alertCon").find(".hotLabel").append("<div style='margin-right:0px;'>"+val+"</div>")
            }else{
                $(".alertCon").find(".hotLabel").append("<div>"+val+"</div>");
            }
        });
    }
    $(".alertCon").css({
        'position': 'absolute',
        'top': top,
        'left': left,
        'z-index': 10,
        'display': 'block'
    });
    $(".planText").css("margin-left",(262-75-72-$(".hotLeft").width())/2);
}).delegate(".alertCon", "click", function(e) {//弹窗内部防止冒泡
    e ? e.stopPropagation() : event.cancelBubble = true;
}).delegate(".all_hot_list", "click", function(e) {//弹窗内部防止冒泡
    e ? e.stopPropagation() : event.cancelBubble = true;
});
$(document).on('click', function(e) {//点击任意地方隐藏弹窗
	if($(e.target).hasClass("ser_dialog")){
		$(".ser_dialog_close").click();
	}
    $('.alertCon').css('display', 'none');
    var $activeItem=$(".topic.active");
    if($activeItem.length>0){
        $activeItem.removeClass("active")
        $activeItem.find(".icon").css("background-color","#a3a3a3").find(".iconCircle").css("display","none");
        $activeItem.find(".link").css("font-weight","normal");
        if($activeItem.find('.link').width()>180){
            $activeItem.css("left",$activeItem.position().left+($activeItem.width()-180)/2)
            $activeItem.css("z-index","0").find('.link').css("width","180px").addClass("word-ellipsis");
        }
    }
    $('.all_hot_list').css('display', 'none');
});
//关联热点
$(".infoConnect").on("click",function(){
    var id=parseInt($(this).data("id"));
    var topic=$(this).siblings(".infoTitle").text();
    if(topic.substr(0,1) == "#" && topic.substr(-1) == "#"){
        topic = topic.split("#");
        topic = topic[1];
    }
    window.location.href='newPath#query='+word+'&topicId='+id+"&hotTopic="+topic;
});

//只可以输入数字
function clearChat(a){
	a.value=a.value.replace(/[^\d]/g,'')
}

//查看热点详情
$(".portrait,.infoTitle,.hotInfo,.hotLeft,.planText").on("click",function(){
    initData($(this));
    $(".all_hot_list_top_source").trigger("click");
});
//查看受众画像
$(".iconCon").on("click",function(){
    initData($(this));
    $(".all_hot_list_top_look").trigger("click");
});
$(document).on('click','.all_hot_list_top_source',function(){//点击详情中热点详情按钮
    $('.all_hot_list_top_look').css('color','#4a4a4a');
    $('.all_hot_list_top_look').find('.hot_look_arrow').css("transform","rotate(0deg)");
    $('.all_hot_list_top_look').find('.hot_look_eye').css('background-image','url(img/card-chart.png)');
    $('.hot_echart_list').addClass('hidecommon');
    if($(this).parent().next().css('display') == 'block'){
        $(this).parent().next().hide();
        $(this).find(".hot_img_arrow").css("transform","rotate(0deg)");
        $(this).find('.hot_look_detail').css('background-image','url(img/card-detail.png)');
        $(this).css('color','#4a4a4a');
    }else{
        $(this).parent().next().show();
        $(this).find(".hot_img_arrow").css("transform","rotate(180deg)");
        $(this).find('.hot_look_detail').css('background-image','url(img/card-detail-hover.png)');
        $(this).css('color','#389b9f');
        $('.type-article').each(function(){
			var str = $(this).text();
			if(str.length>20){
				$(this).attr('title',str);
			}
        });
    };
    var Dataids = $(this).data("id");
    if($(".bot_right").find(".Prend").length <= 0){
    	$(".bot_right").html("");
        $.ajax({
            type:"get",
            url:dataUrl.util.getHotTrend(Dataids),
            success:function(returndata){
                if(returndata && returndata.data.length > 0){
                    var ageNewCon = $("<div class='Prend' style='display:inline-block;width:100%;height:100%;background:#fff;'></div>");
                    $(".bot_right").append(ageNewCon);
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
   	                            	return '热度：'+obj[0].value+'</br>'+obj[0].name.substr(0,16)
   	                            },
   	                            axisPointer:{
   	                            	type:'line',
   	                            	lineStyle:{
   	                            		color:'#00b1c5'
   	                            	}
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
                    ageNewCon.append($("<span style=position:absolute;color:#000;display:inline-block;top:132px;font-size:14px;width:56px;left:50%;transform:translate(-50%,-50%);>暂无数据</span>"))
                    $(".bot_right").append(ageNewCon);
                }
            }
        });
    };
    
    if($(".hot_near_con").find("p").length <= 0){
    	 $(".hot_near_con").html('');
         $.ajax({
    	        type:"get",
    	        url:dataUrl.util.getHotNearTrend(Dataids),
    	        success:function(returndata){
    	        	var str = '';
    	        	if(returndata.length == 0){
    	        		str+= '暂无数据';
    	        		$(".hot_near_con").html(str);
    	        	}else{
    	        		//str += '<div class="hot_near_list"><div class="hot_near f16">相似热点推荐：</div><div class="hot_near_con">';
    	        		$.each(returndata,function(i,item){
    	        		str+= '<p><em class="word-ellipsis" title="'+item.title+'">'+item.title+'</em><i>'+item.prevailingTrend+'</i></p>'
    	        		});
    	        		//str+= '</div><div class="hot_near_all">查看全部<span>></span></div></div>';
    	        		$(".hot_near_con").html(str);
    	        		}
    	        	
    	        	}
    	 });
	 };
   
});
$(document).on('click','.all_hot_list_top_look',function(){//点击详情中受众画像按钮
    $('.all_hot_list_bot').css('display','none');
    $('.hot_img_arrow').css("transform","rotate(0deg)");
    $('.all_hot_list_top_source').find('.hot_look_detail').css('background-image','url(img/card-detail.png)');
    $('.all_hot_list_top_source').css("color","#4a4a4a");
    if($(this).parent().next().next().is('.hidecommon')){
        $(this).css('color','#389b9f');
        $(this).find('.hot_look_eye').css('background-image','url(img/card-chart-hover.png)');
        $(this).parent().next().next().removeClass('hidecommon');
        $(this).find(".hot_look_arrow").css("transform","rotate(180deg)");
    }else{
        $(this).parent().next().next().addClass('hidecommon');
        $(this).find(".hot_look_arrow").css("transform","rotate(0deg)");
        $(this).css('color','#4a4a4a');
        $(this).find('.hot_look_eye').css('background-image','url(img/card-chart.png)');
    };
    if($(this).parent().parent().find(".Personas").length > 0){
        return;
    }
    $loadingchart=$('<div class="loadingcon loadingchart"><div class="result-loading1"><span></span><span></span><span></span><span></span>'+
           '<span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span></div><p class="title">受众画像加载中，请稍后……</p></div>')
    $(".hot_echart_list").append($loadingchart);
    $.ajax({
        type:"get",
        url:dataUrl.util.getPercentData($(this).data("id")),
        success:function(data){
            $(".loadingchart").remove();
            var data = data.data;
            var str = "";
            if(data == null){
                str = "<p class='Personas' style='position:relative;font-size:16px;color:ccc;text-align:center;color:#000;top:50%;left:50%;transform:translate(-50%,-50%)'>获取数据错误</p>";
                    $(".hot_echart_list").append($(str));
                    return;
                }
                var dataLen = data.gender.length + data.interest.length + data.education.length + data.area.length + data.age.length;
                if(dataLen < 1){
                    str = "<p class='Personas' style='position:relative;font-size:16px;color:ccc;text-align:center;color:#000;top:50%;left:50%;transform:translate(-50%,-50%)'>暂无热点受众画像</p>";
                    $(".hot_echart_list").append($(str));
                    return;
                }
            //受众年龄画像
            if(data && data.gender.length > 0){
                var genderCon = $("<div  class='Personas' style='display:inline-block;width:14%;height:279px;background:#fff;'></div>");                    
                $(".hot_echart_list").append(genderCon);                 
                var genderCharts = echarts.init(genderCon.get(0));
                
                var genderOption = $.extend(true,{},circleOption);
                genderOption.title.text = "受众性别分布";
                genderOption.color = ['#6faef5','#5bcecd'];
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
                genderOption.series[0].data[currentIndex].label = label;
                genderCharts.setOption(genderOption);
                window.onresize = genderCharts.resize;
            }else{
                var genderCon = $("<div class=Personas style='position:relative;display:inline-block;width:14%;height:279px;background:#fff;text-align:center'></div>");
                var a = $("<span style='position:absolute;display:inline-block;top:15px;width:97px;color:#4a4a4a;font-family:微软雅黑;font-size:16px;left:50%;transform:translate(-50%,0);font-weight:400;'>受众性别分布</span>")
                genderCon.append(a);
                genderCon.append($("<span style=position:absolute;color:#000;display:inline-block;top:132px;font-size:14px;width:56px;left:50%;transform:translate(-50%,-50%);>暂无数据</span>"))
                $(".hot_echart_list").append(genderCon);
            }
            if(data && data.education.length > 0){
                //受众学历分布
                var educationCon = $("<div  class='Personas' style='display:inline-block;width:14%;height:279px;;background:#fff;'></div>");
                $(".hot_echart_list").append(educationCon);
                var educationCharts = echarts.init(educationCon.get(0));
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
                if(data.education.length>2){
                    educationOption.legend.bottom = 8;
                }
                educationCharts.setOption(educationOption);
                window.onresize=educationCharts.resize;
            }else{
                var educationCon = $("<div class=Personas style='position:relative;display:inline-block;width:14%;height:279px;background:#fff;text-align:center'></div>");
                var a = $("<span style='position:absolute;display:inline-block;top:15px;width:97px;color:#4a4a4a;left:50%;transform:translate(-50%,0);font-family:微软雅黑;font-size:16px;font-weight:400;'>受众学历分布</span>")
                educationCon.append(a);
                educationCon.append($("<span style=position:absolute;color:#000;display:inline-block;top:132px;font-size:14px;width:56px;left:50%;transform:translate(-50%,-50%);>暂无数据</span>"))
                $(".hot_echart_list").append(educationCon);
            }
                //兴趣雷达图
                if(data && data.interest.length > 0){
                    var interestCon = $("<div class='Personas' style='display:inline-block;width:25%;height:279px;background:#fff;'></div>");
                    $(".hot_echart_list").append(interestCon);
                    var interestCharts = echarts.init(interestCon.get(0));
                    var interestvals = [];
                    var interestnames = [];
                    var interest = data.interest;
                    var max = 0;
                    $.each(interest,function(i,item){
                        if(max < item.value){
                            max = item.value
                        }
                    });
                    $.each(interest,function(i,item){
                        interestvals.push(item.value);
                        interestnames.push({name:item.name,max:max});
                    });
                    interestCharts.setOption({
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
                        backgroundColor:"#fff",
                        tooltip: {
                            formatter:function(a,b){
                                var array = [];
                                $.each(interest,function(i,item){
                                    if(i<1){
                                        array.push(item.name+":"+item.value.toFixed(2))
                                    }else{
                                        array.push("<br>"+item.name+":"+item.value.toFixed(2))
                                    }                                       
                                });
                                return array.toString();
                            },
                            textStyle:{
                                fontFamily:"微软雅黑"
                            }
                        },
                        
                        radar: {
                            radius:'60%',
                            center:['50%','57.5%'],
                            splitArea: {
                                areaStyle: {
                                    color: ['#fff', '#fff', '#fff', '#fff']
                                }
                            },
                            name:{
                                textStyle:{
                                    fontFamily:"微软雅黑"
                                }
                            },
                            nameGap:10,
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
                            label:{
                                normal:{
                                }
                            },
                            data : [
                                {
                                    value : interestvals,
                                    itemStyle: {normal: {areaStyle: {type: 'default',color:'#5ccfcd'}}}
                                }
                            ]
                        }]
                    });
                    window.onresize=interestCharts.resize;
                }else{
                    var interestCon = $("<div class=Personas style='position:relative;display:inline-block;width:25%;height:279px;background:#fff;text-align:center'></div>");
                    var a = $("<span style='position:absolute;display:inline-block;top:15px;width:97px;color:#4a4a4a;font-family:微软雅黑;left:50%;transform:translate(-50%,0);font-size:16px;font-weight:400;'>受众兴趣偏好</span>")
                    interestCon.append(a);
                    interestCon.append($("<span style=position:absolute;color:#000;display:inline-block;top:132px;font-size:14px;width:56px;left:50%;transform:translate(-50%,-50%);>暂无数据</span>"))
                    $(".hot_echart_list").append(interestCon);
                    
                }
                
                //年龄柱状图
            if(data && data.age.length > 0){
                var ageNewCon = $("<div class='Personas' style='display:inline-block;width:17%;height:279px;background:#fff;'></div>");
                $(".hot_echart_list").append(ageNewCon);
                var ageNewCharts = echarts.init(ageNewCon.get(0));
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
                    trueData.push(tempArray);
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
                        color: ['#3398DB'],
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
                var ageNewCon = $("<div class=Personas style='position:relative;display:inline-block;width:17%;height:279px;background:#fff;text-align:center'></div>");
                var a = $("<span style='position:absolute;display:inline-block;top:15px;width:97px;color:#4a4a4a;font-family:微软雅黑;font-size:16px;left:50%;transform:translate(-50%,0);font-weight:400;'>受众年龄分布</span>")
                ageNewCon.append(a);
                ageNewCon.append($("<span style=position:absolute;color:#000;display:inline-block;top:132px;font-size:14px;width:56px;left:50%;transform:translate(-50%,-50%);>暂无数据</span>"))
                $(".hot_echart_list").append(ageNewCon);
            }
                //地图
            if(data && data.area.length > 0){
                var mapCon = $("<div class='Personas' style='margin-right:0;display:inline-block;width:28%;height:279px;background:#fff;'></div>");
                
                $(".hot_echart_list").append(mapCon);
                var mapCharts = echarts.init(mapCon.get(0));
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
                            return obj.name + ":" + a;
                        },
                        textStyle:{
                            fontFamily:"微软雅黑"
                        }
                    },
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
                            name: '',
                            type: 'map',
                            mapType: 'china',
                            roam: false,
                            top:55,
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
                            itemStyle: {
                                normal: {
                                    borderColor: '#fff',
                                    areaColor: '#dbedf7',
                                    label: {
                                        show: false
                                    }
                                },
                                emphasis: { // 选中样式
                                    show: false,
                                    areaColor: '#166591',
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
                window.onresize=mapCharts.resize;
            }else{
                var mapCon = $("<div style='position:relative;margin-right:0.5%;display:inline-block;width:28%;height:279px;background:#fff;text-align:center'></div>");
                var a = $("<span style='position:absolute;display:inline-block;top:15px;width:97px;color:#4a4a4a;font-family:微软雅黑;font-size:16px;left:50%;transform:translate(-50%,0);font-weight:400;'>受众地区分布</span>")
                mapCon.append(a);
                mapCon.append($("<span style=position:absolute;display:inline-block;color:#000;top:132px;font-size:14px;width:56px;left:50%;transform:translate(-50%,-50%);>暂无数据</span>"))
                $(".hot_echart_list").append(mapCon);
            }
            
        },
        error:function(){
            console.log("获取受众画像失败");
        }
    });
});

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
};

function initData($detail){//初始化详情弹窗
    var info=$detail.parents(".alertCon").data("info");
    var $li = $.templates(templates.design["tmplAllHotList"]);
    var hotNum=info.prevailingTrend?info.prevailingTrend:0;
    $(".all_hot_list").html($li.render({data:[info]}));
    $("<sapn class='hot_num'>"+hotNum+"</span>").prependTo($(".all_hot_list_top li.all_hot_top_topic"));
    $(".all_hot_list_top li.hot_relation").after("<li class='close_detail'></li>");
    $(".all_hot_list").css("display","block");   
}
$(document).delegate(".close_detail","click",function(){//关闭热点详情
    $(".all_hot_list").css("display","none");
}).delegate(".all_hot_list_top .hot_relation","click",function(){//关联该热点
    var id=parseInt($(this).data("id"));
    var topic=$(this).data("topic");
    if(topic.substr(0,1) == "#" && topic.substr(-1) == "#"){
        topic = topic.split("#");
        topic = topic[1];
    }
    window.location.href='newPath#query='+word+'&topicId='+id+"&hotTopic="+topic;
});