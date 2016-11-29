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
    tmpl.push('             <div class="planText fl">热度:</div><div class="notify-hotValue fl"></div>');
    tmpl.push('             <div class="notify-iconCon fr">');
    tmpl.push('                 <a target=" _blank" class="weibo-link" href="#" style="background-image:url(img/hot_weibo.png);"></a>');
    tmpl.push('                 <a target=" _blank" class="weixin-link" href="#" style="background-image:url(img/hot_weixin.png);display:none;"></a>');
    tmpl.push('                 <a target=" _blank" class="zhihu-link" href="#" style="background-image:url(img/hot_zhihu.png);display:none;"></a>');
    tmpl.push('                 <a target=" _blank" class="baidu-link" href="#" style="background-image:url(img/hot_baidu.png);display:none;"></a>');
    tmpl.push('             </div>');
    tmpl.push('             <div class="planText font14 fr">来源:</div>');
    tmpl.push('         </div>');
    tmpl.push('     </div>');
    tmpl.push('</li>');
    tmpl.push('{{/for}}');
    templates.design['tmplNotifyList'] = tmpl.join('\r\n');

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
    tmpl.push('       <li class="all_hot_list_top_source"><span class="hot_look_detail"></span><em>热点详情</em><span class="hot_img_arrow"></span></li>');

    tmpl.push('       <li class="all_hot_list_top_look" data-id={{:id}}><span class="hot_look_eye"></span><em>受众画像</em><span class="hot_look_arrow"></span></li>');
    tmpl.push('       <li data-index={{:#index}} data-id={{:id}} data-topic={{:title}} class="hot_relation" style="float:right;margin-right:20px;"><span>关联此热点</span></li>');
//    tmpl.push('       <li class="hot_arrow"></li>');
    tmpl.push('     </ul>');
    tmpl.push('    <ul id="ulBottom{{:id}}" class="all_hot_list_bot" style="display:none">');
    tmpl.push('       <li class="li-first">');
    tmpl.push('           <div class="type-img fl"><div class="logoImageCon" style="background-image:url({{if logoImgUrl}}{{:logoImgUrl}}{{else}}img/defaultIcon.png{{/if}})"></div></div>');
    tmpl.push('           <div class="type-content fl">');
    tmpl.push('               <div style="line-height:18px;"><span class="content-article">热点概述</span></div>');
    tmpl.push('               <div class="content-desc">{{:introduction}}</div>')
    tmpl.push('           </div>')
    tmpl.push('       </li>');
    tmpl.push('       <li>');
    tmpl.push('           <div class="type-icon fl" style="background-image:url(img/hot_weibo.png)"></div>');
    tmpl.push('           <span class="type-title weibo-color fl">微博</span>');
    tmpl.push('           <span class="type-article fl">微博话题：<a target="_blank" href={{:topicUrl}}>{{:title}}</a></span>');
    tmpl.push('           {{if readNum}}<span class="type-num fl">阅读量：<b>{{:readNum}}</b></span>{{/if}}');
    tmpl.push('       </li>');
    tmpl.push('   {{if wechatAvgReadNum}}');
    tmpl.push('       <li>');
    tmpl.push('           <div class="type-icon fl"></div>');
    tmpl.push('            <span class="type-title weixin-color fl">微信</span>');
    tmpl.push('           <span class="type-article fl">推荐文章：<a target="_blank" href={{:wechatUrl}}>{{:wechatTitle}}</a></span>');
    tmpl.push('           {{if wechatAvgReadNum}}<span class="type-num fl">前10篇文章平均阅读量：<b>{{:wechatAvgReadNum}}</b></span>{{/if}}');
    tmpl.push('       </li>');
    tmpl.push('   {{/if}}');
    tmpl.push('   {{if baiduHitNum}}');
    tmpl.push('       <li>');
    tmpl.push('           <div class="type-icon fl" style="background-image:url(img/hot_baidu.png)"></div>');
    tmpl.push('           <span class="type-title baidu-color fl">百度</span>');
    tmpl.push('           <span class="type-article fl">搜索结果：<a target="_blank" href={{:baiduUrl}}>{{:baiduTitle}}</a></span>');
    tmpl.push('           {{if baiduHitNum}}<span class="type-num fl">搜索结果数：<b>{{:baiduHitNum}}</b></span>{{/if}}');
    tmpl.push('       </li>');
    tmpl.push('   {{/if}}');
    tmpl.push('   {{if zhihuAvgAnswerNumber}}');
    tmpl.push('       <li>');
    tmpl.push('           <div class="type-icon fl" style="background-image:url(img/hot_zhihu.png)"></div>');
    tmpl.push('           <span class="type-title zhihu-color fl">知乎</span>');
    tmpl.push('           <span class="type-article fl">推荐回答：<a target="_blank" href={{:zhihuUrl}}>{{:zhihuTitle}}</a></span>');
    tmpl.push('           {{if zhihuAvgAnswerNumber}}<span class="type-num fl">前10个问题的平均回答：<b>{{:zhihuAvgAnswerNumber}}</b></span>{{/if}}');
    tmpl.push('       </li>');
    tmpl.push('   {{/if}}');

    // tmpl.push('     <div class="hot_near_list">');
    // tmpl.push('     	<div class="hot_near f16">相似热点推荐：</div>');
    // tmpl.push('    	    <div class="hot_near_con">');
    // tmpl.push('    	    <p><em class="word-ellipsis" title="热点名称1">热点名称1</em><i>88</i></p>');
    // tmpl.push('    	    <p><em class="word-ellipsis" title="热点名称1">热点名称2热点名称2</em><i>818</i></p>');
    // tmpl.push('    	    <p><em class="word-ellipsis" title="热点名称1">热点名称3称2</em><i>100</i></p>');
    // tmpl.push('     	</div>');
    // tmpl.push('     	<div class="hot_near_all">查看全部<span>></span></div>');
    // tmpl.push('     </div>')

    tmpl.push('     </ul>');
    tmpl.push('     <div class="hot_echart_list hidecommon">');
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
            console.log(index);
            console.log(typeof index)
        }
    });
})();

//<ul class="all_hot_list_top">
//<li>
//	<span  class="hot_circle_num">{{:#index}}</span>
//</li>
//<li class="all_hot_top_topic">
//	<p>热地名称热地名称</p>
//	<div>执法</div>
//	<div>刑事犯罪</div>
//</li>
//<li class="all_hot_list_top_source">
//	<div></div><div></div><div></div><div></div>
//</li>
//<li>99</li>
//<li class="hot_relation">关联此热点</li>
//<li class="hot_arrow"></li>
//</ul>
//<ul class="all_hot_list_bot">
//   <li class="li-first">
//       <div class="type-img fl"><div style="width:48px;height:48px;background:#fff;margin-top:18px;"></div></div>
//       <div class="type-content fl">
//           <div style="line-height:18px;"><span class="content-article">河北邢台-商贩捅伤3城管致1死，警方正全力追捕。</span><span class="content-sum">阅读量：<b>30004万</b></span></div>
//           <div class="content-desc">河北邢台-商贩捅伤3城管致1死，警方正全力追捕。</div>
//       </div>
//   </li>
//	<li>
//	   <div class="type-icon fl"></div>
//	   <span class="type-title weixin-color fl">微信</span>
//	   <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
//	   <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
//	</li>
//	<li>
//   <div class="type-icon fl" style="background-image:url(img/hot_baidu.png)"></div>
//   <span class="type-title baidu-color fl">百度</span>
//   <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
//   <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
//</li>
//<li>
//   <div class="type-icon fl" style="background-image:url(img/hot_zhihu.png)"></div>
//   <span class="type-title zhihu-color fl">知乎</span>
//   <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
//   <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
//</li>
//<li>
//   <div class="type-icon fl" style="background-image:url(img/hot_weibo.png)"></div>
//   <span class="type-title weibo-color fl">微博</span>
//   <span class="type-article fl">推荐文章：河北商贩与城管发生争执捅死三城管！</span>
//   <span class="type-num fl">前10篇文章平均阅读量：<b>100000+</b></span>
//</li>
//</ul>