var urlinfo = window.location.href; //获取url
var word = unescape(urlinfo.split('?')[1].split('=')[1]); //拆分url得到”=”后面的参数
var nowPage = unescape(urlinfo.split('?')[2].split('=')[1]); //拆分url得到”=”后面的参数 ;
$('#search-input').val(word);

function resSer() {
    word = $.trim($('#search-input').val());
    if(word) {
        nowPage = 1;
        history.pushState && history.pushState({title: word,pagenumber:1}, word, 'result?word=' + escape(word)+'?pagenumber=1');
        getResult(word, 20, nowPage);
    }

}
$('#search-btn').click(function() {
    resSer();
});
$('#search-input').keyup(function(event) {
    if(event.keyCode == "13") {
        resSer();
    };
});

$('.close_hint').on('click', function() {
    $('#search-hint').addClass('hidecommon');
});
$('#search-input').on('focus', function() {
    $('#search-hint').addClass('hidecommon');
});

// 监听popstate事件
history.pushState && window.addEventListener("popstate", function(e) {
    // 获取history.state对象中的状态信息
    // 在这里state将自动成为event的子对象，可直接通过event.state访问
    var currWord = unescape(window.location.href.split('?')[1].split('=')[1]); //拆分url得到”=”后面的参数
    var currPage = unescape(window.location.href.split('?')[2].split('=')[1]); //拆分url得到”=”后面的参数 ;
    if(currWord&&currPage){
        $('#search-input').val(currWord);
        getResult(currWord, 20, currPage);
    }
}, false);

getResult(word, 20, nowPage);
function getResult(clueWord, pageSize, currentPage) {
    $.ajax({
        type: "get",
        contentType: 'application/json',
        dataType: "json",
        url: dataUrl.util.getResultList(clueWord, pageSize, currentPage),
        success: function(returnData) {
            //console.log(returnData)
            if(returnData.error.code == 0) {
                $("#canvas .topic").remove();
                $(".word").remove()
                var clueWord1 = decodeURI(clueWord);
                $("<span class='word word-ellipsis wordwidth'>" + clueWord1 + "</span>").appendTo($("#canvas"));
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

function drawWord(data) {
    var pointArr = [];
    $.each(data, function(idx, item) {
        var r = 133 + idx * 20;
        var fontClass = "fontClass1",
            sizeClass = "sizeClass1";
        if(item.score<= 0.25) {
            fontClass = "fontClass1";
            sizeClass = "sizeClass1";
        } else if(item.score<= 0.5) {
            fontClass = "fontClass2";
            sizeClass = "sizeClass2";
        } else if(item.score<= 0.75) {
            fontClass = "fontClass3";
            sizeClass = "sizeClass3";
        } else if(item.score<= 1) {
            fontClass = "fontClass4";
            sizeClass = "sizeClass4";
        }
        var typeColor = item.readNumTrendGrowth == -1 ? "circle-down" : "circle-up";
        $item = $("<div class='topic " + typeColor + "' title='" + item.title + "'>" +
            "<span style='display:none;' class='dialogid'>" + item.id + "</span>" +
            "<span style='display:none;' class='scorce'>" + item.score + "</span>" +
            "<span style='display:none;' class='readNum'>" + item.readNum + "</span>" +
            "<span style='display:none;' class='topicUrl'>" + item.topicUrl + "</span>" +
            "<span style='display:none;' class='logoUrl'>" + item.logoUrl + "</span>" +
            "<span style='display:none;' class='topicType'>" + item.topicType + "</span>" +
            "<span style='display:none;' class='readNumTrendGrowth'>" + item.readNumTrendGrowth + "</span>" +
            "<span class='icon " + sizeClass + "'></span><span class='link " + fontClass + "'>" + item.title + "</span></div>").appendTo($("#canvas"));
        var itemWidth = $item.width();
        var itemHeight = $item.height();
        if(itemWidth > 180) {
            itemWidth = 180;
            $item.find(".link").css({
                "display": "block",
                "width": "180px"
            });
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
        elem.left = 494.5 + r * Math.cos(ao * 3.14 / 180) - itemWidth / 2;
        elem.top = 210 + r * Math.sin(ao * 3.14 / 180) - itemHeight / 2;
    } while (hitTest(elem, pointArr) || elem.top + itemHeight > 420 || elem.top < 0)
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

//换一批
$('#changeviewpoints').bind('click', function() {
    nowPage++;
    history.pushState && history.pushState({title: word,pagenumber:nowPage}, word, 'result?word=' + escape(word)+'?pagenumber='+nowPage);
    getResult(word, 20, nowPage);
});

/*点击显示弹窗*/
$(document).delegate(".topic", "click", function(e) {
    e ? e.stopPropagation() : event.cancelBubble = true;
    var left = $(this).position().left + $(this).width() / 2 - 155;
    var top = $(this).position().top + $(this).find(".icon").height() + 5;
    var title = $(this).attr("title");
    var id = $(this).find('.dialogid').text();
    var scorce = $(this).find(".scorce").text();
    var readNum = $(this).find(".readNum").text();
    var topicUrl = $(this).find(".topicUrl").text();
    var logoUrl = $(this).find(".logoUrl").text();
    var topicType = $(this).find(".topicType").text();
    var readNumTrendGrowth = $(this).hasClass("circle-up");
    $(".cardContainer").find(".hotTitle").attr("title", title).text(title);
    //$(".cardContainer").find(".cardTag").text(topicType != "null" ? topicType : "");
    $(".cardContainer").find(".relevance span").text(scorce != "null" ? Math.round(scorce * 100) + "%" : "");
    $(".cardContainer").find(".talkTime span").text(readNum != "null" ? readNum : "");
    $(".cardContainer").find(".hot-source").attr("href", topicUrl != "null" ? topicUrl : "#");
    readNumTrendGrowth ? $(".cardContainer").find(".talkTime .tag").attr("src", "img/up3.png") : $(".cardContainer").find(".talkTime .tag").attr("src", "img/down4.png");
    $(".cardContainer").find(".hotImg").css({
        "background": logoUrl != 'null' ? "url(" + logoUrl + ")" : "#456" + " no-repeat center",
        "background-size": "100%"
    });
    $(".cardContainer").find(".viewSource").attr('data-id', id);
    $(".cardContainer").find(".viewSource").attr('data-name', title);
    $(".cardContainer").css({
        'position': 'absolute',
        'top': top,
        'left': left,
        'z-index': 9999,
        'display': 'block'
    });
});

//关闭窗口
$('.discard').on('click', function() {
    $(this).parent().parent().parent().css('display', 'none');
});
//关联热度
$(document).on('click', '.viewSource', function() {
    var hotTopic = $(".word").text();
    var topic = $(this).attr('data-name');
    var topicId = $(this).attr('data-id');
    if(topic.substr(0,1) == "#" && topic.substr(-1) == "#"){
    	topic = topic.split("#");
        topic = topic[1]
    }    
    window.location.href = "path?query=" + escape(hotTopic) + "&topicId=" + topicId + "&hotTopic=" + escape(topic);

});
$(document).on('click', function() {
    $('.cardContainer').css('display', 'none')
})
$(document).delegate(".cardContainer", "click", function(e) {
    e ? e.stopPropagation() : event.cancelBubble = true;
});

//hover事件
$(document).delegate('.circle-down','mouseover',function(){
	$(this).css('margin-left',-5+'px')
	var fsEle = parseInt($(this).find('.link').css("font-size")); 
	$(this).find('.link').css('font-size',fsEle+3 +'px');
	var wdEle = parseInt($(this).find('.icon').width()); 
	$(this).find('.icon').css('width',wdEle+5 +'px')
	$(this).find('.icon').css('height',wdEle+5 +'px')
});

$(document).delegate('.circle-down','mouseout',function(){
	$(this).css('margin-left',0+'px')
	var fsEle = parseInt($(this).find('.link').css("font-size")); 
	$(this).find('.link').css('font-size',fsEle-3 +'px');
	var wdEle = parseInt($(this).find('.icon').width()); 
	$(this).find('.icon').css('width',wdEle-5 +'px')
	$(this).find('.icon').css('height',wdEle-5 +'px')
});

$(document).delegate('.circle-up','mouseover',function(){
	$(this).css('margin-left',-5+'px')
	var fsEle = parseInt($(this).find('.link').css("font-size")); 
	$(this).find('.link').css('font-size',fsEle+3 +'px');
	var wdEle = parseInt($(this).find('.icon').width()); 
	$(this).find('.icon').css('width',wdEle+5 +'px')
	$(this).find('.icon').css('height',wdEle+5 +'px')
});

$(document).delegate('.circle-up','mouseout',function(){
	$(this).css('margin-left',0+'px')
	var fsEle = parseInt($(this).find('.link').css("font-size")); 
	$(this).find('.link').css('font-size',fsEle-3 +'px');
	var wdEle = parseInt($(this).find('.icon').width()); 
	$(this).find('.icon').css('width',wdEle-5 +'px')
	$(this).find('.icon').css('height',wdEle-5 +'px')
});