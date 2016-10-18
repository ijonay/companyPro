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

    tmpl = [];
    tmpl.push('{{for data}}');
    tmpl.push('<li class="notSelect pnlHasTabId" data-prevId="{{:#data.id}}" data-index="{{:#data.cardId}}" data-size={{:#data.size}} {{if size>1}}data-ss-colspan="2"{{/if}} {{if size==4}}data-ss-rowspan="2"{{/if}}>');
    tmpl.push('    <span class="tableId" style="display:none;"></span>');
    tmpl.push('    <div class="item-top">');
    tmpl.push('        <div class="item-top-left">');
    tmpl.push('            <span class="fl-title"></span>');
    tmpl.push('            <span class="fl-total"></span>');
    tmpl.push('            <span class="fl-desc"></span>');
    tmpl.push('        </div>');
    tmpl.push('        <ul class="item-top-right">');
    tmpl.push('        {{if #parent.parent.data.type=="recyclebin"}}');
    tmpl.push('        <li><img src="img/selectNormal.png"/></li>');
    tmpl.push('        {{else}}');
    tmpl.push('        <li data-type="datasource"><img src="img/card_datasource_excel_normal.png"/></li>');
    tmpl.push('        <li data-type="comment"><img src="img/card_comments_normal.png"/></li>');
    tmpl.push('        <li data-type="set"><img src="img/card_set_normal.png"/></li>');
    tmpl.push('        {{/if}}');
    tmpl.push('        </ul>');
    tmpl.push('        <div class="item-top-set">');
    tmpl.push('            <span class="glyphicon glyphicon-triangle-top triangle-up"></span>');
    tmpl.push('            <ul class="set-list">');
    tmpl.push('                <li data-type="edit"><a hre="#">编辑</a></li>');
    tmpl.push('                <li data-type="collect"><a hre="#">收藏</a></li>');
    tmpl.push('                <li data-type="copy"><a hre="#">复制</a></li>');
    tmpl.push('                <li data-type="newWarning" class="warningrightedit"><a hre="#">新建预警</a></li>');
    tmpl.push('                <li class="item-top-set-last" data-type="del"><a hre="#">删除</a></li>');
    tmpl.push('            </ul>');
    tmpl.push('        </div>');
    tmpl.push('    </div>');
    tmpl.push('    {{if #parent.parent.data.type=="recyclebin"}}<div class="item" id="main{{:#data.cardId}}"><span class="error" style="display:none;vertical-align:middle">获取数据失败</span></div>')
    tmpl.push('    {{else}}<a style="{{if cardId==0}}opacity:0;{{/if}}" class="linkDetail"><div class="item{{if size==4}} item-lg{{/if}}{{if size==2}} item-md{{/if}}{{if size==1||size==null}} item-sm"{{/if}}" id="main{{:#data.cardId}}"><span class="error" style="display:none;vertical-align:middle">获取数据失败</span></div></a>');
    tmpl.push('    {{/if}}')
    tmpl.push('    {{if cardId!=0}}');
    tmpl.push('    <div class="item-bottom-left">');
    tmpl.push('	       <img src="img/card_zoom_hover_left.png"/>');
    tmpl.push('	   </div>');
    tmpl.push('    <div class="item-bottom-right">');
    tmpl.push('	       <img src="img/card_zoom_hover_right.png"/>');
    tmpl.push('	   </div>');
    tmpl.push('    {{/if}}');
    tmpl.push('    {{if #parent.parent.data.type!="recyclebin"}}<div class="pnl-operate" style="{{if cardId==0}}display:table;{{/if}}">');
    tmpl.push('        <div class="operate-content">');
    tmpl.push('            <img src="img/invalidCard.png">');
    tmpl.push('            <div class="operate-title">{{if cardId==0}}数据源已修改，请更新卡片{{else}}数据源被删除，卡片失效{{/if}}</div>');
    tmpl.push('            <div class="btn-operate {{if cardId==0}}edit-card{{else}}del-card{{/if}}"><img src="{{if cardId==0}}img/editCard.png{{else}}img/delCard.png{{/if}}" style="opacity:0.8"></div>');
    tmpl.push('        </div>');
    tmpl.push('    </div>{{/if}}');
    tmpl.push('</li>');
    tmpl.push('{{/for}}');
    templates.design['tmplCard'] = tmpl.join('\r\n');

    tmpl = [];
    tmpl.push('{{for alert}}');
    tmpl.push('<div class="list-item" data-index="{{>alertId}}" data-isRead="{{>alertUser.isRead}}">');
    tmpl.push('<div id="triggerStatus" class="dispNone">');
    tmpl.push('<span>')
    tmpl.push('{{if alertName}}{{:alertName}}{{else}}{{:alertField.fieldName}}{{/if}}{{:alertField.algorName}}{{if alertCond.cond=="EQUAL"}}等于{{/if}}{{if alertCond.cond=="NOTEQUAL"}}不等于{{/if}}' +
        '{{if alertCond.cond=="GREATERTHAN"}}大于{{/if}}{{if alertCond.cond=="LESSTHAN"}}小于{{/if}}{{if alertCond.cond=="GREATERTHANOREQUAL"}}大于等于{{/if}}{{if alertCond.cond=="LESSTHANOREQUAL"}}小于等于{{/if}}' +
        '{{if alertCond.cond=="BETWEEN"}}介于{{/if}}{{if alertCond.cond=="NOTBETWEEN"}}不介于{{/if}}{{:alertCond.value1}}{{if alertCond.value2}}~{{:alertCond.value2}}{{/if}}{{:unit}}');
    tmpl.push('</span>')
    tmpl.push('<span>')
    tmpl.push('{{if alertName}}{{:alertName}}{{else}}{{:alertField.fieldName}}{{/if}}{{:alertField.algorName}}');
    tmpl.push('</span>')
    tmpl.push('<span>')
    tmpl.push('{{:curValue}}{{:unit}}');
    tmpl.push('</span>')
    tmpl.push('</div>')
    tmpl.push('    <div class="list-item-top">');
    tmpl.push('        <div class="top-icon {{if alertLastTime&&!alertUser.isRead}}top-icon-unread{{else}}top-icon-readed{{/if}}"></div>');
    tmpl.push('        <span class="item-condition">');
    tmpl.push('        预警{{:#index+1}}：{{if alertName}}{{:alertName}}{{else}}{{:alertField.fieldName}}{{/if}}{{:alertField.algorName}}{{if alertCond.cond=="EQUAL"}}等于{{/if}}{{if alertCond.cond=="NOTEQUAL"}}不等于{{/if}}' +
        '{{if alertCond.cond=="GREATERTHAN"}}大于{{/if}}{{if alertCond.cond=="LESSTHAN"}}小于{{/if}}{{if alertCond.cond=="GREATERTHANOREQUAL"}}大于等于{{/if}}{{if alertCond.cond=="LESSTHANOREQUAL"}}小于等于{{/if}}' +
        '{{if alertCond.cond=="BETWEEN"}}介于{{/if}}{{if alertCond.cond=="NOTBETWEEN"}}不介于{{/if}}{{:alertCond.value1}}{{if alertCond.value2}}~{{:alertCond.value2}}{{/if}}{{:unit}}');
    tmpl.push('         </span>');
    tmpl.push('        <span class="item-triggered" {{if !alertLastTime}}style="display:none"{{/if}}>已触发</span>');
    tmpl.push('        {{if alertLastTime}}<span class="item-date">{{:alertLastTime}}</span>{{/if}}');
    tmpl.push('        <ul class="item-operate fr">');
    tmpl.push('            <li class="warning-alert"><img src="img/warning-alert.png">提醒周期<img class="alert-down" src="img/alert-down.png"/>');
    tmpl.push('                <ul class="alert-way">');
    tmpl.push('                    <li data-index="0"><input type="radio" id="alertNow{{>alertId}}" name="rdiAlert{{>alertId}}" {{if alertUser.alertNotifyPeriod==0}}checked="checked"{{/if}}><label for="alertNow{{>alertId}}">立即提醒</label></li>');
    tmpl.push('                    <li data-index="1"><input type="radio" id="alertDay{{>alertId}}" name="rdiAlert{{>alertId}}" {{if alertUser.alertNotifyPeriod==1}}checked="checked"{{/if}}><label for="alertDay{{>alertId}}">每天提醒</label></li>');
    tmpl.push('                    <li data-index="2"><input type="radio" id="alertWeek{{>alertId}}" name="rdiAlert{{>alertId}}" {{if alertUser.alertNotifyPeriod==2}}checked="checked"{{/if}}><label for="alertWeek{{>alertId}}">每周提醒</label></li>');
    tmpl.push('                </ul>');
    tmpl.push('            </li>');
    tmpl.push('            <li><div class="splitline"></div></li>');
    tmpl.push('            <li class="warning-detail warningrightdetail" data-alertid="{{>alertId}}">查看详情</li>');
    tmpl.push('            <li><div class="splitline"></div></li>');
    tmpl.push('            {{if alertUser.isOwner}}<li class="warning-edit warningrightedit">编辑</li>{{/if}}');
    tmpl.push('            <li><div class="splitline"></div></li>');
    tmpl.push('            <li class="warning-del">删除</li>');
    tmpl.push('        </ul>');
    tmpl.push('    </div>');
    tmpl.push('</div>');
    tmpl.push('{{/for}}');
    templates.design['tmplWarning'] = tmpl.join('\r\n');

    $.views.helpers({
        "isShow": function(timestamp3) {
            if(!timestamp3) {
                return '';
            }
            var newDate = new Date();
            newDate.setTime(timestamp3);
            return newDate.toLocaleDateString();
        }
    });
	$.views.helpers({
        "isShowRefreshInterval": function(timestamp){
        	if(!timestamp){
        		return '';
        	}
        	var sel ='/';
        	arr = timestamp.split(',');
        	for (var i in arr) {
	        	if(arr[i].indexOf(sel)>-1){
//	        	   console.log(i)
//	        	   console.log(arr[i]) 
//	        	   console.log(typeof(arr[i]))
	        	   arr2 = arr[i].split('/');
//	        	   console.log(arr2);
//	        	   console.log(arr2[arr2.length-1]);
	        	   switch (i){
	        	   	case "0":return '每'+arr2[arr2.length-1]+'分钟';
	        	   		break;
	        	   	case "1":
	        	   		if(Number(arr2[arr2.length-1]) == 0){
	        	   			return '每24小时';
	        	   		}else{
	        	   			return '每'+arr2[arr2.length-1]+'小时';
	        	   		};
                        break;
                    case "2":return '每'+arr2[arr2.length-1]+'天';
                        break;
                    case "3":return '每'+arr2[arr2.length-1]+'个月';
                        break;
                    case "4":
                            if(Number(arr2[arr2.length-1]) == 0){
                            	return '每星期天';
                            }else{
                            	return '每星期'+(Number(arr2[arr2.length-1]));
                            };
                            break;
                        case "2":
                            return '每' + arr2[arr2.length - 1] + '天执行';
                            break;
                        case "3":
                            return '每' + arr2[arr2.length - 1] + '个月执行';
                            break;
                        case "4":
                            if(Number(arr2[arr2.length - 1]) == 0) {
                                return '每星期天执行';
                            } else {
                                return '每星期' + (Number(arr2[arr2.length - 1])) + '执行';
                            };
                            break;
                        default:
                            break;
                    };
                };
            };
        }
    });
    tmpl = [];
    tmpl.push('{{for datas}}');
    tmpl.push('<li>');
    tmpl.push('<div class="datacenterul1_div">');
    tmpl.push('    <div class="fl marginright65 datacenterhead word-ellipsis" style="width:134px;">');
	tmpl.push('    	<div class="fl datacenterheadleft" style="margin-right:8px;">');
	tmpl.push('    	</div>');
	tmpl.push('    	<div class="fl" style="width:86px;">');
	tmpl.push('    		<div class="ac fontweight600 word-ellipsis" title="{{>dataset.dsName}}" style="height:17px;">{{:dataset.dsName}}</div>');
	tmpl.push('        	<div class="ac color81 fileType">{{:conn.connName}}</div>');
	tmpl.push('    	</div>');
	tmpl.push('    </div>');
	tmpl.push('    <div class="fl marginright65">');
    tmpl.push('        <div class="ac fontweight600">所有人</div>');
    tmpl.push('        <div class="ac color81">{{:creator.name}}</div>');
    tmpl.push('    </div>');

    tmpl.push('    <div class="fl marginright65">');
    tmpl.push('        <div class="ac fontweight600">字段数</div>');
    tmpl.push('        <div class="ac color81">{{:dataset.columnCount}}</div>');
    tmpl.push('    </div>');

    tmpl.push('    <div class="fl marginright65">');
    tmpl.push('        <div class="ac fontweight600">卡片数 / 浏览量</div>');
    tmpl.push('        <div class="ac color81">{{:dataset.cardCount}}/{{:dataset.pgView}}</div>');
    tmpl.push('    </div>');

    tmpl.push('    <div class="fl marginright65 refreshTime">');
    tmpl.push('        <div class="ac fontweight600">最新更新时间</div>');
    //    tmpl.push('        <div class="ac color81">{{newDate.setTime(:conn.refreshTime*1000)}}</div>');
    tmpl.push('        <div class="ac color81">{{:~isShow(conn.refreshTime)}}</div>');
    tmpl.push('    </div>');

    tmpl.push('    <div class="fl marginright65">');
    tmpl.push('        <div class="ac fontweight600">更新周期</div>');
    tmpl.push('        <div class="ac color81">{{:~isShowRefreshInterval(conn.refreshInterval)}}</div>');
    tmpl.push('    </div>');
    tmpl.push('    <div class="fr datacenterrightcon">');
    tmpl.push('    	   <div class="marginLeft20 fl datacenterdetail pointer" data-dsId="{{>dataset.dsId}}">数据详情</div>');
    tmpl.push('        <div class="marginLeft20 fl datacenternewitem pointer" data-dsId="{{>dataset.dsId}}">新建卡片</div>');
    tmpl.push('        <div class="fl datacentereditsource"><a href="edit?dsId={{>dataset.dsId}}" data-dsId="{{>dataset.dsId}}">编辑数据源</a></div>');
    tmpl.push('    </div>');
    tmpl.push('    </div>');
    tmpl.push('    <div class="clearfix"></div>');
    tmpl.push('</li>');
    tmpl.push('{{/for}}');
    templates.design['tmplDataCenterList'] = tmpl.join('\r\n');

    tmpl = [];
    tmpl.push('<div style="border:1px solid #000;padding-bottom:10px;">');
    tmpl.push('<div class="clearfix"></div>');
	tmpl.push('<div class="datacenterdetailalerttop">');
	tmpl.push('	<div class="fl datacenteralerttopname fontweight600">数据详情({{if module.moduleName!=null&& module.moduleName!=undefined}}{{:module.moduleName}}{{/if}})</div>');
	tmpl.push('	<div class="fr datacenterdetailalertdel pointer">X</div>');
	tmpl.push('	<div class="clearfix"></div>');
	tmpl.push('</div>');
	
	tmpl.push('<div class="datacenterdetailalertbottom">');
	tmpl.push('	<ul class="fl dcdb1 width100 marginLeft35 paddingbot10">');
	tmpl.push('		<li class="borbot1 marginbot20 paddingbot10">');
	tmpl.push('			<p class="color81">所属模块</p>');
	tmpl.push('			<p class="fontweight600 f14 textindent20" style="height:20px;">{{:dataset.dsName}}</p>');
	tmpl.push('		</li>');
	tmpl.push('		<li class="borbot1 marginbot20 paddingbot10">');
	tmpl.push('			<p class="color81">创建日期</p>');
	tmpl.push('			<p class="fontweight600 f14 textindent20" style="height:20px;">{{:dataset.createDate}}</p>');
	tmpl.push('		</li>');
	tmpl.push('		<li>');
	tmpl.push('			<p class="color81">最近更新</p>');
	tmpl.push('			<p class="fontweight600 f14 textindent20" style="height:20px;">{{:~isShow(conn.refreshTime)}}</p>');
	tmpl.push('		</li>');
	tmpl.push('	</ul>');
	tmpl.push('	<ul class="fl dcdb2 width100 marginLeft35 paddingbot10">');
	tmpl.push('		<li class="borbot1 marginbot20 paddingbot10">');
	tmpl.push('			<p class="color81">数据类型</p>');
	tmpl.push('			<p class="fontweight600 f14 textindent20" style="height:20px;">{{:conn.connName}}</p>');
	tmpl.push('		</li>');
	tmpl.push('		<li class="borbot1 marginbot20 paddingbot10">');
	tmpl.push('			<p class="color81">更新周期</p>');
	tmpl.push('			<p class="fontweight600 f14 textindent20" style="height:20px;">{{:~isShowRefreshInterval(conn.refreshInterval)}}</p>');
	tmpl.push('		</li>');
	tmpl.push('		<li>');
	tmpl.push('			<p class="color81">数据行数</p>');
	tmpl.push('			<p class="fontweight600 f14 textindent20" style="height:20px;"></p>');
	tmpl.push('		</li>');
	tmpl.push('	</ul>');
	tmpl.push('	<ul class="fl dcdb3 width100 marginLeft35">');
	tmpl.push('		<li class="borbot1 marginbot20 paddingbot10">');
	tmpl.push('			<p class="color81">字段数量</p>');
	tmpl.push('			<p class="fontweight600 f14 textindent20" style="height:20px;">{{:dataset.columnCount}}</p>');
	tmpl.push('		</li>');
	tmpl.push('		<li>');
	tmpl.push('			<p class="color81">字段列表</p>');
	tmpl.push('			<ol class="fontweight600 f14">');
	tmpl.push('				{{for dataset.columns}}');
	tmpl.push('				<li>{{:#data}}</li>');
	tmpl.push('				{{/for}}');
	tmpl.push('			</ol>');
	tmpl.push('		</li>');
	tmpl.push('	</ul>');
		
	tmpl.push('	<ul class="fr dcdb5">');
	tmpl.push('		<li class="borbot1 marginbot20 paddingbot10">');
	tmpl.push('			<p class="color81">所有人</p>');
	tmpl.push('			<p class="fontweight600 f14 textindent20" style="height:20px;">{{:creator.nickName}}</p>');
	tmpl.push('		</li>');
	tmpl.push('		<li class="borbot1 marginbot20 paddingbot10">');
	tmpl.push('			<p class="color81">管理员</p>');
	tmpl.push('			<p class="fontweight600 f14 textindent20" style="height:20px;"></p>');
	tmpl.push('		</li>');
	tmpl.push('		<li>');
	tmpl.push('			<p class="color81">32人使用</p>');
	tmpl.push('			<p class="fontweight600 f14">所有模块</p>');
	tmpl.push('		</li>');
	tmpl.push('	</ul>');
	tmpl.push('	<div class="fr dcdb4 marginLeft160">');
	tmpl.push('		<p class="color81">使用该数据源的卡片</p>');
	tmpl.push('		<ul class="fontweight600 f14 source-card-ul">');
				
	tmpl.push('		</ul>');
	tmpl.push('	</div>');
	tmpl.push('</div>');
    tmpl.push('<div class="clearfix"></div>	');		
    tmpl.push('</div>');
    templates.design['tmplDataCenterDeltail'] = tmpl.join('\r\n');
    
    tmpl = [];
    tmpl.push('{{for active}}'); 
    tmpl.push('<li class="pointer" data-connId="{{:connId}}" data-type="{{:connType}}" onclick='+'{{if connType=="Excel"}}window.location.href="addtable"{{else}}oncli(this){{/if}}'+'>');
//    tmpl.push('<a href="{{:activateUrl}}">');
    tmpl.push('    <div class="fl" style="background:url(img/{{>connIcon}});width:30px;height:30px;background-size:contain"></div>');
    tmpl.push('    <div class="fr">');
    tmpl.push('        <p>{{:connType}}</p>');
    tmpl.push('        <p>{{:connName}}</p>');
    tmpl.push('    </div>');
//    tmpl.push('</a>');
    tmpl.push('</li>');
    tmpl.push('{{/for}}');
    templates.design['tmplDataCenterNewLinker'] = tmpl.join('\r\n');
    
    tmpl = [];
    tmpl.push('{{for active}}');
    tmpl.push('<li class="list-item" data-index="{{:connId}}">');
    tmpl.push('    <div class="conn-icon" style="background-image:url(img/{{>connIcon}})"></div>');
    tmpl.push('    <div class="conn-name">');
    tmpl.push('        <div class="pnl-context">');
    tmpl.push('            <div>{{:connName}}</div>');
    tmpl.push('            <div class="color-theme">{{if state==2}}连接器初始化中……{{else}}授权已失效{{/if}}</div>');
    tmpl.push('        </div>');
    tmpl.push('    </div>');
    tmpl.push('    <div>');
    tmpl.push('        <div class="pnl-context">');
    tmpl.push('            <div>所有人</div>');
    tmpl.push('            <div class="color-theme">{{:activeUserName}}aaa</div>');
    tmpl.push('        </div>');
    tmpl.push('    </div>');
    tmpl.push('    <div>');
    tmpl.push('        <div class="pnl-context">');
    tmpl.push('            <div>创建时间</div>');
    tmpl.push('            <div class="color-theme">{{:activeTime}}</div>');
    tmpl.push('        </div>');
    tmpl.push('    </div>');
    tmpl.push('    <div>');
    tmpl.push('        <div class="pnl-context">');
    tmpl.push('            <div>最新更新时间</div>');
    tmpl.push('            <div class="color-theme">{{:activeTime}}</div>');
    tmpl.push('        </div>');
    tmpl.push('    </div>');
    tmpl.push('    <div>');
    tmpl.push('        <div class="pnl-context">');
    tmpl.push('            <div>更新周期</div>');
    tmpl.push('            <div class="color-theme">1星期</div>');
    tmpl.push('        </div>');
    tmpl.push('    </div>');
    tmpl.push('    <div>');
    tmpl.push('        <div class="pnl-context">');
    tmpl.push('            <ul>');
    tmpl.push('                <li class="del">删除</li>');
    tmpl.push('                <li class="detail">连接器详情</li>');
    tmpl.push('                <li class="authorize">授权</li>');
    tmpl.push('            </ul>');
    tmpl.push('        </div>');
    tmpl.push('    </div>');
    tmpl.push('</li>');
    tmpl.push('<li class="item-detail">');
    tmpl.push('    <div class="detail-head">');
    tmpl.push('        <span>连接器详情</span>');
    tmpl.push('        <img src="img/close-warning.png">');
    tmpl.push('    </div>');
    tmpl.push('    <div class="detail-content">');
    tmpl.push('        <ul class="content-left">');
    tmpl.push('            <li>');
    tmpl.push('                <p class="color-theme">连接器类型</p>');
    tmpl.push('                <p>新浪微博</p>');
    tmpl.push('            </li>');
    tmpl.push('            <li>');
    tmpl.push('                <p class="color-theme">激活方式</p>');
    tmpl.push('                <p>登录授权</p>');
    tmpl.push('            </li>');
    tmpl.push('            <li>');
    tmpl.push('                <p class="color-theme">剩余授权时间</p>');
    tmpl.push('                <p>14天</p>');
    tmpl.push('            </li>');
    tmpl.push('        </ul>');
    tmpl.push('        <ul class="content-middle1">');
    tmpl.push('            <li>');
    tmpl.push('                <p class="color-theme">创建时间</p>');
    tmpl.push('                <p>2016.07.10 13：07</p>');
    tmpl.push('            </li>');
    tmpl.push('            <li>');
    tmpl.push('                <p class="color-theme">最近更新时间</p>');
    tmpl.push('                <p>2016.07.10 13：07</p>');
    tmpl.push('            </li>');
    tmpl.push('            <li>');
    tmpl.push('                <p class="color-theme">更新周期</p>');
    tmpl.push('                <p>1星期</p>');
    tmpl.push('            </li>');
    tmpl.push('        </ul>');
    tmpl.push('        <ul class="content-middle2">');
    tmpl.push('            <li>');
    tmpl.push('                <p class="color-theme">字段总数</p>');
    tmpl.push('                <p>57个</p>');
    tmpl.push('            </li>');
    tmpl.push('            <li>');
    tmpl.push('                <p class="color-theme">字段分组（根据不同主题，将字段分成不同的组）</p>');
    tmpl.push('                <ul>');
    tmpl.push('                    <li>主题一：粉丝分析（32个字段）</li>');
    tmpl.push('                    <li>主题二：博文分析（27个字段）</li>');
    tmpl.push('                </ul>');
    tmpl.push('                <p class="field-detail" style="float:right;cursor:pointer;">详情</p>');
    tmpl.push('            </li>');
    tmpl.push('        </ul>');
    tmpl.push('        <ul class="content-middle3">');
    tmpl.push('            <li>');
    tmpl.push('                <p class="color-theme">使用该连接器的数据源（11个）</p>');
    tmpl.push('                <ul>');
    tmpl.push('                    <li>数据源名称1</li>');
    tmpl.push('                    <li>数据源名称2</li>');
    tmpl.push('                    <li>数据源名称3</li>');
    tmpl.push('                </ul>');
    tmpl.push('                <p class="datasource-detail" style="float:right;cursor:pointer;">详情</p>');
    tmpl.push('            </li>');
    tmpl.push('        </ul>');
    tmpl.push('        <ul class="content-right fr">');
    tmpl.push('            <li>');
    tmpl.push('                <p class="color-theme">所有人<span class="operate edit-owner">修改</span></p>');
    tmpl.push('                <p>查尔斯</p>');
    tmpl.push('            </li>');
    tmpl.push('            <li>');
    tmpl.push('                <p class="color-theme">管理员<span class="operate edit-owner">编辑</span></p>');
    tmpl.push('                <p>戴梦全</p>');
    tmpl.push('            </li>');
    tmpl.push('            <li>');
    tmpl.push('                <p class="color-theme">可用范围<span class="operate edit-owner">编辑</span></p>');
    tmpl.push('                <p>等人</p>');
    tmpl.push('            </li>');
    tmpl.push('            <li>');
    tmpl.push('                <p class="color-theme">可见范围<span class="operate edit-owner">编辑</span></p>');
    tmpl.push('                <p>等人</p>');
    tmpl.push('            </li>');
    tmpl.push('        </ul>');
    tmpl.push('    </div>');
    tmpl.push('</li>');
    tmpl.push('{{/for}}');
    templates.design['tmplConn'] = tmpl.join('\r\n');

    tmpl = [];
    tmpl.push('{{for all}}');
    tmpl.push('<li><img src="img/weibo.png" style="height:35px;vertical-align:top;margin:8.5px 8px 0 0;">{{:connName}}</li>');
    tmpl.push('{{/for}}');
    templates.design['tmplConnType'] = tmpl.join('\r\n');
    
    $.views.helpers({
        "topicType": function(topicType) {
            if(!topicType) {
                return '';
            }
            var topicTypeNew = $.trim(topicType).split(' ').join('、');
           
            return topicTypeNew;
        }
    });
    
    //获取热点话题列表
    tmpl = [];
    tmpl.push('{{for data}}');
    tmpl.push('<div class="cardContainer">');
    tmpl.push('<div class="cardTitle">');
    tmpl.push('   {{if logoUrl}}<div class="hotImg" style="background-image:url({{:logoUrl}})"></div>{{else}}<div class="hotImg"></div>{{/if}}');
    tmpl.push('   <div class="hotTitle" title="{{:title}}">{{:title}}</div>');
    tmpl.push('   <div class="related">');
    tmpl.push('       <img src="img/circle---ok.png">');
    tmpl.push('       <span data-id="{{:id}}">关联</span>');
    tmpl.push('   </div>');
    tmpl.push('</div>');
    tmpl.push('<div class="cardTag labelColor1">{{:relationDesc}}</div>');
    tmpl.push('<div class="hotInfo">');
    tmpl.push('    <div class="talkTime">');
    tmpl.push('        <img src="img/progress.png"> 话题量:');
    tmpl.push('        <span>{{:readNum}}</span>');
    tmpl.push('       {{if readNumTrendGrowth == 1}}<img src="img/up3.png">{{else}}<img src="img/down4.png">{{/if}}');
    tmpl.push('    </div>');
    tmpl.push('    <div class="relevance">');
    tmpl.push('        <img src="img/link1.png"> 标签:');
    tmpl.push('        <span>{{:~topicType(topicType)}}</span>');
    tmpl.push('    </div>');
    tmpl.push('</div>');
    tmpl.push('<div class="hotOp">');
    tmpl.push('    <div class="concern opa04">');
    tmpl.push('        <img src="img/star.png">');
    tmpl.push('        关注热点');
    tmpl.push('    </div>');
    tmpl.push('    <div class="viewSource opa04">');
    tmpl.push('        <img src="img/viewon.png">');
    tmpl.push('         {{if topicUrl}}<a href={{:topicUrl}} target="_blank">查看源</a>{{else}}<a href="javascript:void(0)">查看源</a>{{/if}}');
    tmpl.push('    </div>');
    tmpl.push('    <div class="discard opa04">');
    tmpl.push('        <img src="img/viewoff.png">');
    tmpl.push('        不感兴趣');
    tmpl.push('    </div>');
    tmpl.push('</div>');
    tmpl.push('</div>');
    tmpl.push('{{/for}}');
    templates.design['tmplHotsports'] = tmpl.join('\r\n');
})();