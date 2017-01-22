/**
 * 文件用途说明：提供公用设置信息 作者姓名:于莎 制作日期：2016/4/15
 */
var dataUrl = dataUrl || {}, localhostUrl = "http://localhost:8080/websvr/", localhostNewUrl = "http://localhost:8080/", serverUrl = "http://192.168.1.101/websvrdev/", virtualDataUrl = "http://192.168.1.121:3000/websvrdev/";

baseUrl = "";
dataUrl.util = {
	getInpList:function(){
    	return encodeURI("api/topicclass/getsearchitem");
    },
    getResultList: function (clueWord, pageSize, currentPage) {//获取话题列表
        return encodeURI("api/topic/getlist?clueWord="+clueWord+"&pageSize=" + pageSize + "&currentPage=" + currentPage);
    },
    getPathInfo: function (startNode, endNode) {
        return localhostNewUrl + "api/paths/nodeRelations?startNode=" + startNode + "&endNode=" + endNode;
    },
    getPath: function (topicId, query) {
        return encodeURI(localhostNewUrl + 'api/paths/' + topicId + '?query=' + query);
    },
    getCommon:function(){
    	return encodeURI("api/searchItem/list");
    },
    getSerHistory:function(){
    	return encodeURI("api/searchLog/list");
    },
    cancleCommon:function(){
    	return encodeURI("api/searchItem/cancel");
    },
    cancleSerHistory:function(){
    	return encodeURI("api/searchLog/delete");
    },
    addCommon:function(){
    	return encodeURI("api/searchItem/add");
    },
    addSerHistory:function(){
    	return encodeURI("api/searchLog/add");
    },
    getNewPath:function(topicId,query){
    	return 'api/paths/' + topicId + '?query=' + query;
    },
    getHotTopic:function(count){
    	return encodeURI('api/topic/hottopic/'+count);
    },
    getHotPred:function(nowDate){
        return encodeURI("api/predict/list?dateStr="+nowDate);        
    },
    getTenHot:function(){
    	return encodeURI('api/topic/hottopic/10');
    },
    getNotify:function(count){//获取通知列表
        return encodeURI('api/hotTopicMessage/list?count='+count);
    },
    getNotifyDetail:function(id){//获取通知详细信息列表
        return encodeURI('api/hotTopicMessage/detail?id='+id);
    },
    getPercentData:function(id){
    	return 'api/topic/percentage/' + id;
    },
    getLogOut:function(){
    	return 'api/account/loginout' 
    },
    delNotify:function(id){//单个删除通知
        return encodeURI('api/hotTopicMessage/del?id='+id);
    },
    getHotTrend:function(id){//热点走势
        return 'api/topicTrend/history/'+id;
    },
    getHotNearTrend:function(id){//相似热点
        return 'api/topic/getSimilarTopicList?id='+id;
    },
    delAllNotify:function(id){
        return encodeURI('api/hotTopicMessage/delAll?ids='+id)//删除所有通知
    },
    updatePwd:"api/account/changep"
    ,
    getSerHotInfo:function(val){
    	return "api/topic/hottopic/100/"+val//获取搜索热点信息
    },
    getUserInfo:"api/account/info",//获取用户信息
    getVersionsInfo:"api/proinfo/versions",//获取版本信息
    updateStateChange:'api/proinfo/updatestate',
    getCircleHots:function(size){
        return "api/topic/getBySearchModel?size="+size
    },
    getArticalFileds:function(){
    	return "api/wechat/wxArticleFields"
    },
    getArticalList:function(){
    	return "api/wechat/wxArticleList"
    },
    getArticlesearch:function(){
    	return "api/wechat/searchArticle"
    },
    getSimilarTopic:function(){
    	return "api/wechat/wxTopicList"
    },
    getStructSearch:function(){
    	return "api/wechat/structSearch"
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