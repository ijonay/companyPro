/**
 * 文件用途说明：提供公用设置信息 作者姓名:于莎 制作日期：2016/4/15
 */
var dataUrl = dataUrl || {}, localhostUrl = "http://localhost:8080/websvr/", localhostNewUrl = "http://localhost:8080/", serverUrl = "http://192.168.1.101/websvrdev/", virtualDataUrl = "http://192.168.1.121:3000/websvrdev/";

baseUrl = "";
dataUrl.util = {
   
    getResultList: function (clueWord, pageSize, currentPage) {//获取话题列表
        return encodeURI(localhostNewUrl + "api/topic/getlist?clueWord=" + clueWord + "&pageSize=" + pageSize + "&currentPage=" + currentPage);
    },
    getPathInfo: function (startNode, endNode) {
        return localhostNewUrl + "api/paths/nodeRelations?startNode=" + startNode + "&endNode=" + endNode;
    },
    getPath: function (topicId, query) {
        return encodeURI(localhostNewUrl + 'api/paths/' + topicId + '?query=' + query);
    },
    getCommon:function(){
    	return encodeURI("api/searchItem/list");
    }
}

var chartsAttr = chartsAttr || {};
chartsAttr.templates = {
    registerTmpl: function (tmplId, scriptTagId) {
        chartsAttr.templates[tmplId] = $
            .templates(templates.design[scriptTagId]);
    },
    colorTheme: [],
    myChart: [],
    bodyHeight: 0
};

//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}