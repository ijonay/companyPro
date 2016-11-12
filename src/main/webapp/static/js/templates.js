/**
 *文件用途说明：提供模板
 *作者姓名:于莎
 *制作日期：2016/4/15
 **/
var templates = templates || {};
templates.design = {};
(function() {
    tmpl = [];
    tmpl.push('{{for data.sheets}}');
    tmpl.push('<li>');
    tmpl.push('    <input id="radio{{:sheetid}}" type="radio" name="rdiSheet" value="{{:sheetid}}" data-index="{{:sheetindex}}" {{if error.code!=0}}disabled="disabled"{{/if}}>');
    tmpl.push('    <label class="sheetName" title="{{:sheetname}}" for="radio{{:sheetid}}">{{>sheetname}}</label>');
    tmpl.push('</li>');
    tmpl.push('{{/for}}');
    templates.design['tmplSheetList'] = tmpl.join('\r\n');

    
})();
(function() {
    tmpl = [];
    tmpl.push('{{for data}}');
    tmpl.push('<li data-id="{{:id}}">');
    tmpl.push('    <ul class="all_hot_list_top">');
    tmpl.push('       <li><span  class="hot_circle_num">{{:#index+1}}</span></li>');
    tmpl.push('       <li class="all_hot_top_topic"><p>{{:title}}</p>{{:~addTag(eventClass)}}</li>');
    tmpl.push('       <li class="all_hot_list_top_source"><div class="weiboIcon"></div>{{if wechatAvgReadNum}}<div class="weixinIcon"></div>{{/if}}{{if baiduHitNum}}<div class="baiduinIcon"></div>{{/if}}{{if zhihuAvgAnswerNumber}}<div class="zhihuIcon"></div>{{/if}}</li>');
    tmpl.push('       <li>{{:prevailingTrend}}</li>');
    tmpl.push('       <li data-index={{:#index}} data-id={{:id}} data-topic={{:title}} class="hot_relation">关联此热点</li>');
    tmpl.push('       <li class="hot_arrow"></li>');
    tmpl.push('     </ul>');
    tmpl.push('    <ul id="ulBottom{{:id}}" class="all_hot_list_bot">');
    tmpl.push('       <li class="li-first">');
    tmpl.push('           <div class="type-img fl"><div class="logoImageCon" style="background-image:url({{if logoImgUrl}}{{:logoImgUrl}}{{/if}})"></div></div>');
    tmpl.push('           <div class="type-content fl">');
    tmpl.push('               <div style="line-height:18px;"><span class="content-article">热点概述</span></div>');
    tmpl.push('               <div class="content-desc">{{:introduction}}</div>')
    tmpl.push('           </div>')
    tmpl.push('       </li>');
    tmpl.push('       <li>');
    tmpl.push('           <div class="type-icon gray fl" style="background-image:url(img/hot_weibo.png)"></div>');
    tmpl.push('           <span class="type-title weibo-color fl">微博</span>');
    tmpl.push('           <span class="type-article fl">微博话题：<a target="_blank" href={{:topicUrl}}>{{:title}}</a></span>');
    tmpl.push('           <span class="type-num fl">阅读量：<b>{{:readNum}}</b></span>');
    tmpl.push('       </li>');
    tmpl.push('   {{if wechatAvgReadNum}}');
    tmpl.push('       <li>');
    tmpl.push('           <div class="type-icon gray fl"></div>');
    tmpl.push('            <span class="type-title weixin-color fl">微信</span>');
    tmpl.push('           <span class="type-article fl">推荐文章：<a target="_blank" href={{:wechatUrl}}>{{:wechatTitle}}</a></span>');
    tmpl.push('           <span class="type-num fl">前10篇文章平均阅读量：<b>{{:wechatAvgReadNum}}</b></span>');
    tmpl.push('       </li>');
    tmpl.push('   {{/if}}');
    tmpl.push('   {{if baiduHitNum}}');
    tmpl.push('       <li>');
    tmpl.push('           <div class="type-icon gray fl" style="background-image:url(img/hot_baidu.png)"></div>');
    tmpl.push('           <span class="type-title baidu-color fl">百度</span>');
    tmpl.push('           <span class="type-article fl">搜索结果：<a target="_blank" href={{:baiduUrl}}>{{:baiduTitle}}</a></span>');
    tmpl.push('           <span class="type-num fl">搜索结果数：<b>{{:baiduHitNum}}</b></span>');
    tmpl.push('       </li>');
    tmpl.push('   {{/if}}');
    tmpl.push('   {{if zhihuAvgAnswerNumber}}');
    tmpl.push('       <li>');
    tmpl.push('           <div class="type-icon gray fl" style="background-image:url(img/hot_zhihu.png)"></div>');
    tmpl.push('           <span class="type-title zhihu-color fl">知乎</span>');
    tmpl.push('           <span class="type-article fl">推荐回答：<a target="_blank" href={{:zhihuUrl}}>{{:zhihuTitle}}</a></span>');
    tmpl.push('           <span class="type-num fl">前10个问题的平均回答：<b>{{:zhihuAvgAnswerNumber}}</b></span>');
    tmpl.push('       </li>');
    tmpl.push('   {{/if}}');
    tmpl.push('     </ul>');
    tmpl.push('</li>');
    tmpl.push('{{/for}}');
    templates.design['tmplAllHotList'] = tmpl.join('\r\n');
    $.views.helpers({
        "addTag": function(eventClass) {
            var tagArray = eventClass.split(",");
            var str = "";
            $.each(tagArray,function(index,item){
            	str += "<div>"+item+"</div>"
            })
            return str;
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