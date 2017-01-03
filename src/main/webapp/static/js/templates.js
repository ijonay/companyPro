/**
 *文件用途说明：提供模板
 *作者姓名:于莎
 *制作日期：2016/4/15
 **/
var templates = templates || {};
templates.design = {};
(function () {
    tmpl = [];
    tmpl.push('{{for data.sheets}}');
    tmpl.push('<li>');
    tmpl.push('    <input id="radio{{:sheetid}}" type="radio" name="rdiSheet" value="{{:sheetid}}" data-index="{{:sheetindex}}" {{if error.code!=0}}disabled="disabled"{{/if}}>');
    tmpl.push('    <label class="sheetName" title="{{:sheetname}}" for="radio{{:sheetid}}">{{>sheetname}}</label>');
    tmpl.push('</li>');
    tmpl.push('{{/for}}');
    templates.design['tmplSheetList'] = tmpl.join('\r\n');

    tmpl = [];
    tmpl.push('{{for data}}');
    tmpl.push('<li data-topicId={{:topicId}} data-id={{:id}}>');
    tmpl.push('     <a>');
    tmpl.push('         <span class="dot-icon"></span>');
    tmpl.push('         <span class="pnl"><span class="hot-word">{{>keyword}}</span>－');
    tmpl.push('         <span class="hot-spot">{{:title}}</span></span>');
    tmpl.push('         <span class="time fr">{{>createDate}}</span>');
    tmpl.push('         <span class="notify-close fr" style="display:none;"><img style="vertical-align:top;margin-top:11px;" src="img/del-notify.png"></span>');
    tmpl.push('     </a>');
    tmpl.push('     <div class="notify-info" style="display:none;">');
    tmpl.push('         <div class="notify-infoTop">');
    tmpl.push('             <div class="notify-infoTitle"></div>');
    tmpl.push('             <div class="notify-infoConnect">关联此热点</div>');
    tmpl.push('</div>');
    tmpl.push('         <div class="notify-hotLabel">');
    tmpl.push('             <div class="notify-hotLabel0"></div>');
    tmpl.push('             <div class="notify-hotLabel1"></div>');
    tmpl.push('             <div class="notify-hotLabel2"></div>');
    tmpl.push('         </div>');
    tmpl.push('         <div class="notify-hotInfo">');
    tmpl.push('             <div class="notify-infoText"></div>');
    tmpl.push('         </div>');
    tmpl.push('         <div class="notify-infoBottom">');
    tmpl.push('             <div class="notify-hotIcon fl"></div>');
    tmpl.push('             <div class="fl">热度:</div><div class="notify-hotValue fl"></div>');
    tmpl.push('             <div class="notify-iconCon fr">');
    tmpl.push('                 <a target=" _blank" class="weibo-link" href="#" style="background-image:url(img/hot_weibo.png);"></a>');
    tmpl.push('                 <a target=" _blank" class="weixin-link" href="#" style="background-image:url(img/hot_weixin.png);display:none;"></a>');
    tmpl.push('                 <a target=" _blank" class="zhihu-link" href="#" style="background-image:url(img/hot_zhihu.png);display:none;"></a>');
    tmpl.push('                 <a target=" _blank" class="baidu-link" href="#" style="background-image:url(img/hot_baidu.png);display:none;"></a>');
    tmpl.push('             </div>');
    tmpl.push('             <div class="font14 fr">来源:</div>');
    tmpl.push('         </div>');
    tmpl.push('     </div>');
    tmpl.push('</li>');
    tmpl.push('{{/for}}');
    templates.design['tmplNotifyList'] = tmpl.join('\r\n');
    
    tmpl=[];
    tmpl.push('{{for data}}');
    tmpl.push('<div class="pnlNear hot_near_pnl">');
    tmpl.push('    <div class="near_info">');
    tmpl.push('         <div class="near_portrait" style="background-image: url({{if logoImgUrl}}{{>logoImgUrl}}{{else}}img/neardefault.png{{/if}});"></div>');
    tmpl.push('         <div class="near_topRight">');
    tmpl.push('             <div class="near_infoTop">');
    tmpl.push('                 <div class="near_infoTitle">{{:title}}</div>');
    tmpl.push('                 <div class="near_infoConnect" data-id="{{>id}}" data-topic="{{>title}}">关联此热点</div>');
    tmpl.push('             </div>');
    tmpl.push('             <div class="near_hotLabel">');
    tmpl.push('             {{for eventClass}}');
    tmpl.push('                 <div>{{:#data}}</div>');
    tmpl.push('             {{/for}}')
    tmpl.push('             </div>');
    tmpl.push('         </div>');
    tmpl.push('     </div>');
    tmpl.push('     <div class="near_hotInfo">');
    tmpl.push('         <div class="near_infoText">{{:introduction}}</div>');          
    tmpl.push('     </div>');
    tmpl.push('     <div class="near_infoBottom">');
    tmpl.push('         <div class="near_hotIcon fl"></div>');
    tmpl.push('         <div class="fl font14">热度:</div><div class="near_hotValue font14 fl">{{if prevailingTrend}}{{:prevailingTrend}}{{else}}0{{/if}}</div>');
    tmpl.push('         <div class="near_iconCon fr">');
    tmpl.push('             {{if topicUrl}}<a target=" _blank" class="weibo-link" href="{{>topicUrl}}" style="background-image:url(img/hot_weibo.png);"></a>{{/if}}');
    tmpl.push('             {{if wechatUrl}}<a target=" _blank" class="weixin-link" href="{{>wechatUrl}}" style="background-image:url(img/hot_weixin.png);"></a>{{/if}}');
    tmpl.push('             {{if zhihuUrl}}<a target=" _blank" class="zhihu-link" href="{{>zhihuUrl}}" style="background-image:url(img/hot_zhihu.png);"></a>{{/if}}');
    tmpl.push('             {{if baiduUrl}}<a target=" _blank" class="baidu-link" href="{{>baiduUrl}}" style="background-image:url(img/hot_baidu.png);"></a>{{/if}}');
    tmpl.push('         </div>');
    tmpl.push('         <div class="font14 fr">来源:</div>');
    tmpl.push('     </div>');
    tmpl.push('</div>')
    tmpl.push('{{/for}}');
    templates.design['tmplHotNear'] = tmpl.join('\r\n');
})();
(function(){
	tmpl = [];
    tmpl.push('{{for data}}');
	tmpl.push('{{if #index == 0}}<ul class="record-ul">{{else}}<ul class="record-ul hidecommon">{{/if}}');
	tmpl.push('<div>');
	tmpl.push('<p>{{:name}}</p>');
	tmpl.push('<p>{{:~addTag2(createTime)}}</p>');
	tmpl.push('</div>');
	tmpl.push('<li>');
	tmpl.push('<p>{{:version}}</p>');
	tmpl.push('<p>{{:description}}</p>');
	tmpl.push('</li>');
	tmpl.push('</ul>');
	tmpl.push('{{/for}}');
	templates.design['tmplRecordList'] = tmpl.join('\r\n');
	  $.views.helpers({
	        "addTag2": function (createTime) {
	            if (createTime) {
	            	 return new Date(parseInt(createTime)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");      
	            }
	        }
	    });
})();
(function(){
	tmpl = [];
    tmpl.push('{{for data}}');
	tmpl.push('<li>');
	tmpl.push('<p>{{:name}}<i class="fr f14 corlor4a">{{:~addTag2(createTime)}}</i></p>');
	tmpl.push('<p>{{:introduction}}</p>');
	tmpl.push('</li>');
	tmpl.push('{{/for}}');
	templates.design['tmplRecordList2'] = tmpl.join('\r\n');
})();
(function () {
    tmpl = [];
    tmpl.push('{{for data}}');
    tmpl.push('<li data-id="{{:id}}">');
    tmpl.push('    <ul class="all_hot_list_top">');
    tmpl.push('       <li class="hot_sort"><span  class="hot_circle_num" style="{{if #index > 9 && #index < 99}}width:24px;height:24px;line-height:24px{{else #index ==99}}width:32px;height:32px;line-height:32px{{/if}}">{{:#index+1}}</span></li>');
    tmpl.push('       <li class="all_hot_top_topic"><p title={{:title}}>{{:title}}</p>{{:~addTag(eventClass)}}</li>');

    tmpl.push('       <li class="hot_trend">{{:prevailingTrend}}</li>');
    tmpl.push('       <li class="all_hot_list_top_source1"><a target="_blank" href={{:topicUrl}}><div class="weiboIcon"></div></a>{{if wechatUrl}}<a target="_blank" href={{:wechatUrl}}><div class="weixinIcon"></div></a>{{/if}}{{if baiduUrl}}<a target="_blank" href={{:baiduUrl}}><div class="baiduinIcon"></div></a>{{/if}}{{if zhihuUrl}}<a target="_blank" href={{:zhihuUrl}}><div class="zhihuIcon"></div></a>{{/if}}<div class="hot_img_arrow"></div></li>');
    tmpl.push('       <li class="all_hot_list_top_source" data-id={{:id}}><span class="hot_look_detail"></span><em>热点详情</em><span class="hot_img_arrow"></span></li>');

    tmpl.push('       <li class="all_hot_list_top_look" data-id={{:id}}><span class="hot_look_eye"></span><em>受众画像</em><span class="hot_look_arrow"></span></li>');
    tmpl.push('       <li data-index={{:#index}} data-id={{:id}} data-topic={{:title}} class="hot_relation" style="float:right;margin-right:20px;"><span>关联此热点</span></li>');
//    tmpl.push('       <li class="hot_arrow"></li>');
    tmpl.push('     </ul>');
    tmpl.push('    <ul class="all_hot_list_bot ulBottom{{:id}}" data-id="ulBottom{{:id}}" style="display:none">');
    tmpl.push('	   <div class="bot_left fl">');
    tmpl.push('       <li class="li-first">');
    tmpl.push('           <div class="type-img fl"><div class="logoImageCon" style="background-image:url({{if logoImgUrl}}{{:logoImgUrl}}{{else}}img/defaultIcon.png{{/if}})"></div></div>');
    tmpl.push('           <div class="type-content fl">');
    tmpl.push('               <div style="line-height:18px;"><span class="content-article">热点概述</span></div>');
    tmpl.push('               <div class="content-desc" title="{{:introduction}}">{{:introduction}}</div>')
    tmpl.push('           </div>')
    tmpl.push('       </li>');
    tmpl.push('       <li>');
    tmpl.push('           <div class="type-icon fl" style="background-image:url(img/hot_weibo.png)"></div>');
    tmpl.push('           <span class="type-title weibo-color fl">微博</span>');
    tmpl.push('           <span class="type-article fl word-ellipsis">微博话题：<a target="_blank" href={{:topicUrl}}>{{:title}}</a></span>');
    tmpl.push('           {{if readNum}}<span class="type-num fl">阅读量：<b>{{:readNum}}</b></span>{{/if}}');
    tmpl.push('       </li>');
    tmpl.push('   {{if wechatUrl}}');
    tmpl.push('       <li>');
    tmpl.push('           <div class="type-icon fl"></div>');
    tmpl.push('            <span class="type-title weixin-color fl">微信</span>');
    tmpl.push('           <span class="type-article fl word-ellipsis">推荐文章：<a target="_blank" href={{:wechatUrl}}>{{:wechatTitle}}</a></span>');
    tmpl.push('           {{if wechatAvgReadNum}}<span class="type-num fl">相关文章数：<b>{{:wechatAvgReadNum}}</b></span>{{/if}}');
    tmpl.push('       </li>');
    tmpl.push('   {{else}}');
    tmpl.push('       <li>');
    tmpl.push('           <div class="type-icon fl"></div>');
    tmpl.push('            <span class="type-title weixin-color fl">微信</span>');
    tmpl.push('           <span class="type-article fl">暂无数据</span>');
   // tmpl.push('           {{if wechatAvgReadNum}}<span class="type-num fl">相关文章数：<b>{{:wechatAvgReadNum}}</b></span>{{/if}}');
    tmpl.push('       </li>');
    tmpl.push('   {{/if}}');
    tmpl.push('   {{if baiduUrl}}');
    tmpl.push('       <li>');
    tmpl.push('           <div class="type-icon fl" style="background-image:url(img/hot_baidu.png)"></div>');
    tmpl.push('           <span class="type-title baidu-color fl">百度</span>');
    tmpl.push('           <span class="type-article fl word-ellipsis">搜索结果：<a target="_blank" href={{:baiduUrl}}>{{:baiduTitle}}</a></span>');
    tmpl.push('           {{if baiduHitNum}}<span class="type-num fl">搜索结果数：<b>{{:baiduHitNum}}</b></span>{{/if}}');
    tmpl.push('       </li>');
    tmpl.push('   {{else}}');
    tmpl.push('       <li>');
    tmpl.push('           <div class="type-icon fl" style="background-image:url(img/hot_baidu.png)"></div>');
    tmpl.push('           <span class="type-title baidu-color fl">百度</span>');
    tmpl.push('           <span class="type-article fl">暂无数据</span>');
    //tmpl.push('           {{if baiduHitNum}}<span class="type-num fl">搜索结果数：<b>{{:baiduHitNum}}</b></span>{{/if}}');
    tmpl.push('       </li>');
    tmpl.push('   {{/if}}');
    tmpl.push('   {{if zhihuUrl}}');
    tmpl.push('       <li>');
    tmpl.push('           <div class="type-icon fl" style="background-image:url(img/hot_zhihu.png)"></div>');
    tmpl.push('           <span class="type-title zhihu-color fl">知乎</span>');
    tmpl.push('           <span class="type-article fl word-ellipsis">推荐回答：<a target="_blank" href={{:zhihuUrl}}>{{:zhihuTitle}}</a></span>');
    tmpl.push('           {{if zhihuAvgAnswerNumber}}<span class="type-num fl">前10个问题的平均回答：<b>{{:zhihuAvgAnswerNumber}}</b></span>{{/if}}');
    tmpl.push('       </li>');
    tmpl.push('   {{else}}');
    tmpl.push('       <li>');
    tmpl.push('           <div class="type-icon fl" style="background-image:url(img/hot_zhihu.png)"></div>');
    tmpl.push('           <span class="type-title zhihu-color fl">知乎</span>');
    tmpl.push('           <span class="type-article fl word-ellipsis">暂无数据</a></span>');
    //tmpl.push('           {{if zhihuAvgAnswerNumber}}<span class="type-num fl">前10个问题的平均回答：<b>{{:zhihuAvgAnswerNumber}}</b></span>{{/if}}');
    tmpl.push('       </li>');
    tmpl.push('   {{/if}}');
    tmpl.push('	   </div>');
    tmpl.push('	   <div class="bot_right fr">');
    tmpl.push('	   </div>');
    tmpl.push('	   <div class="clearfix"></div>');
    tmpl.push('     <div class="hot_near_list">');
    tmpl.push('         <div class="hot_near_list_head f16">');
    tmpl.push('     	   <div class="hot_near_title fl">相似热点推荐：</div>');
    tmpl.push('            <div class="pnlNear near_error fl" style="display:none;">暂无数据</div>');
    tmpl.push('     	   <div class="hot_near_refresh fr" style="display:none;"></div>');
    tmpl.push('         </div>');
    tmpl.push('         <div class="hot_near_con"></div>');
    tmpl.push('     </div>');
    tmpl.push('     </ul>');
    tmpl.push('     <div class="hot_echart_list hidecommon">');
    tmpl.push('<div class="newPicCon"><div class="chartsLeftCon"><div class="leftTopCon"><div class="sexCon"></div><div class="ageCon"></div></div><div class="leftBottomCon"><div class="eduCon"></div><div class="areaCon"></div></div></div><div class="chartsRightCon"></div></div>')
    tmpl.push('<img class="tgiInt" src="img/info.png" />')
    tmpl.push('     </div>');
    tmpl.push('</li>');
    tmpl.push('{{/for}}');
    templates.design['tmplAllHotList'] = tmpl.join('\r\n');
    $.views.helpers({
        "addTag": function (eventClass) {
            if (eventClass) {
                var tagArray = eventClass.split(",");
                var str = "";
                $.each(tagArray, function (index, item) {
                    str += '<div title="' + item + '">' + item + '</div>'
                })
                return str;
            } else {
                return "";
            }
        },
        "circleReset": function (index) {
        }
    });
})();