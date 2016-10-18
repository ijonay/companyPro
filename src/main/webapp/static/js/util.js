/**
 * 文件用途说明：提供公用设置信息 作者姓名:于莎 制作日期：2016/4/15
 */
var dataUrl = dataUrl || {}, localhostUrl = "http://localhost:8080/websvr/", localhostNewUrl = "http://127.0.0.1:8080/hotspot", serverUrl = "http://192.168.1.101/websvrdev/", virtualDataUrl = "http://192.168.1.121:3000/websvrdev/";

baseUrl = "";
dataUrl.util = {
    uploadFile: baseUrl + "api/uploadexcel",// 上传文件
    getSheetDataUrl: baseUrl + "api/table/",// 获取预览数据100行
    createCardUrl: baseUrl + "api/cards/",// 创建卡片
    updateCardNameUrl: function (cardid) {// 设置卡片名称
        return baseUrl + "api/cards/" + cardid + "/name";
    },
    getFieldsUrl: baseUrl + "api/fields/",
    addAxisUrl: baseUrl + "api/axisrecords/",
    setCardAttrUrl: baseUrl + "api/chartsets/",
    getChartDataUrl: baseUrl + "api/datasource/carddata/",
    addXYUrl: baseUrl + "api/axisrecords/",
    gitCardPro: baseUrl + "api/chartsets/card/",
    setCardPro: baseUrl + "api/chartsets/card/",
    gitCoordStr: "api/fields/",
    getAllTypeAttrUrl: baseUrl + "api/defcharts/",// 获取图形列表
    getCardsAfterCharttype: function (cardId) { // 获取设置后的图形
        return baseUrl + "api/cards/" + cardId + "/charttype"
    },
    setChartTypeUrl: function (cardId) {// 设置卡片图表类型
        return baseUrl + "api/cards/" + cardId + "/chart";
    },
    getCardTypeUrl: baseUrl + "api/CardsChartShow/",
    getAllConfigUrl: function (cardId) {// 所有配置获取接口
        return baseUrl + "api/cards/" + cardId + "/conf";
    },
    getCardInfo: function (cardId) {// 获取用户一览表页面一个卡片数据，包括配置信息
        return baseUrl + "api/cards/" + cardId + "/prev";
    },
    getUserCardIds: baseUrl + "api/cards/prevlist",// 获取用户一览表ids
    cancelCard: baseUrl + "api/cards/",// 取消卡片制作
    completeCardUrl: baseUrl + "api/cards/",// 完成卡片制作
    getColors: baseUrl + "api/clrpattens",// 获取配色方案
    setColors: function (cardid) {
        return baseUrl + "api/cards/" + cardid + "/color";
    },
    setCardSort: baseUrl + "api/cards/prevlist/",// 设置用户一览表信息
    getCardDataInfo: function (cardid) {// 获取用户卡片数据
        return baseUrl + "api/cards/" + cardid + "/data"
    },
    putCardMaxMinValUrl: function (cardid) {// 设置卡片最大最小值
        return baseUrl + "api/cards/" + cardid + "/conf/ymaxmin"
    },
    getRecycleCardIds: baseUrl + "api/cards/recyclebin/",// 获取回收站卡片
    delCard: baseUrl + "api/cards",// 回收站删除卡片
    restoreCard: baseUrl + "api/cards/recovery/",// 恢复卡片
    getKeyInfo: function (cardId) {//获取关键信息
        return "api/cards/" + cardId + "/keyinfo";
    },
    getAlertInfo: baseUrl + "api/cards/alert", //获取卡片预警列表
    delAlert: function (alertId) {
        return baseUrl + "api/alert/" + alertId;
    },
    setAlertNotify: function (alertId) {//设置预警提醒周期
        return baseUrl + "api/alert/" + alertId + "/notifyperiod";
    },
    setAlertReaded: function (alertId) {//设置预警已读
        return baseUrl + "api/alert/" + alertId + "/isread";
    },
    getSocial: function (alertId) {//获取关注者列表
        return baseUrl + "api/alert/" + alertId + "/social";
    },
    getDataCenterList: baseUrl + "api/dataset",//获取数据中心数据集
    getDataCenterDeltail: function (dsId) {//获取数据中心详情
        return baseUrl + "api/dataset/" + dsId;
    },
    getAlertConf: function (alertId) {//获取预警配置信息
        return baseUrl + "api/alert/" + alertId + "/conf";
    },
    getConnDatasource: function (connId) {//获取使用连接器的数据源列表
        return baseUrl + "api/conn/" + connId + "/sysdataset";
    },
    getHostportsList: function (NowNumber) {//获取热点话题列表
        return localhostNewUrl + "api/hottopic/getlist?pageSize=6&currentPage=" + NowNumber;
    },
    getResultList: function (clueWord, pageSize, currentPage) {//获取话题列表
        return encodeURI(localhostNewUrl + "api/topic/getlist?clueWord=" + clueWord + "&pageSize=" + pageSize + "&currentPage=" + currentPage);
    },
    getPathInfo: function (startNode, endNode) {
        return localhostNewUrl + "api/paths/nodeRelations?startNode=" + startNode + "&endNode=" + endNode;
    },
    getPath: function (topicId, query) {
        return encodeURI(localhostNewUrl + 'api/paths/' + topicId + '?query=' + query);
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