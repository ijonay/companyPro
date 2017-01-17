/**
 * Created by xyzhuzhou on 2016/12/15 0015 12:17:19.
 */
(function (e) {
    var zhu = this.UA = {};
    var monitorConf = {
        monitorNodes: [
            {
                node: "#loginBtn",
                note: "登录",
                relationLinkedNodes: [{
                    name: "用户名",
                    selector: "#username$val"
                }, {
                    name: "密码",
                    selector: "input[name='password']$val"
                }],
                pathName: ["user/login"]
            },
            {
                node: "div.user-logout",
                note: "退出系统"
            },
            {
                node: "div.user-set",
                note: "账号设置"
            },
            {
                node: "#record-btn-index",
                note: "查看系统更新通知",
                pathName: ["hotsystem"]
            },
            {
                node: "li.head-pred,li.pred-tab",
                note: "查看热点预告"
            },
            {
                node: "li.head-notify,li.notify-tab",
                note: "查看探索通知"
            },
            {
                node: "#ser_btn",
                note: "探索-首页探索",
                relationLinkedNodes: [{
                    name: "搜索框",
                    selector: "#ser_text$val"
                }],
                pathName: ["hotsystem"]
            },
            {
                node: "#nav-head-search",
                note: "探索-顶部搜索框",
                relationLinkedNodes: [{
                    name: "搜索框",
                    selector: "#nav_ser$val"
                }]
                //,pathName: ["hotsystem", "hotresult"]
            },
            {
                node: "#dialog_ser_to",
                note: "探索-高级探索",
                relationLinkedNodes: [{
                    name: "搜索框",
                    selector: "#dialog_ser_text$val",
                    require: true
                }, {
                    name: "搜索条件ID集合",
                    selector: "ol.dialog_inp_c_data$find.i$attr.data-id",
                    require: true
                }, {
                    name: "搜索条件集合",
                    selector: "ol.dialog_inp_c_data$find.i$text"
                }
                ],
                pathName: ["hotsystem"]
            },
            {
                node: "#favorite_set_btn",
                note: "设置常用关键词	",
                relationLinkedNodes: [{
                    name: "搜索框",
                    selector: "#ser_text$val"
                }],
                pathName: ["hotsystem"]
            },
            {
                delegete: "#favorite_ul",
                node: "li > span",
                note: "删除常用关键词	",
                relationLinkedNodes: [{
                    name: "关键词ID",
                    selector: "_$parent$attr.data-id"
                }, {
                    name: "关键词名称",
                    selector: "_$parent$attr.title"
                }],
                pathName: ["hotsystem"]
            },
            {
                node: "li.all_hot_list_top_source",
                note: "查看热点详情",
                relationLinkedNodes: [{
                    name: "话题ID",
                    selector: "_$attr.data-id"

                }, {
                    name: "话题标题",
                    selector: "_$parent$parent$find.li$eq.1$find.p$text"
                }],
                pathName: ["hotsystem"]
            },
            {
                node: "li.all_hot_list_top_look",
                note: "查看受众画像",
                relationLinkedNodes: [{
                    name: "话题ID",
                    selector: "_$attr.data-id"

                }, {
                    name: "话题标题",
                    selector: "_$parent$parent$find.li$eq.1$find.p$text"
                }],
                pathName: ["hotsystem"]
            },
            {
                node: "li.hot_relation",
                note: "关联热点",
                relationLinkedNodes: [{
                    name: "话题ID",
                    selector: "_$attr.data-id"

                }, {
                    name: "话题标题",
                    selector: "_$parent$parent$find.li$eq.1$find.p$text"
                }],
                pathName: ["hotsystem"]
            },
            {
                node: "#dislog_btn_sure",
                note: "热点筛选",
                relationLinkedNodes: [{
                    name: "检索词",
                    selector: "#canvas$find.div.word$text",
                    require: true
                }, {
                    name: "搜索条件ID集合",
                    selector: "ol.dialog_inp_c_data$find.i$attr.data-id",
                    require: true
                }, {
                    name: "搜索条件集合",
                    selector: "ol.dialog_inp_c_data$find.i$text"
                }
                ],
                pathName: ["hotresult"]
            },
            {
                node: "#prev-next",
                note: "换一批路径方案",
                pathName: ["newPath"]
            }
        ],
        inPage: true,
        uniqueId: "test-user",
        sendUrl: "../api/userActionLog/"
    };
    zhu.containerPath = function (item) {
        var pathName = location.pathname.toLowerCase();
        if (item.pathName != null && item.pathName.length > 0) {
            var flag = false;
            for (var j = 0; j < item.pathName.length; j++) {
                var name = item.pathName[j].toLowerCase();
                var index = pathName.lastIndexOf(name);
                if (index > -1 && (index + name.length) == pathName.length) {
                    flag = true;
                    break;
                }
            }
            return flag;
        }
    }
    zhu.getMonitorNode = function (ele) {
        var pathName = location.pathname.toLowerCase();
        for (var i = 0; i < monitorConf.monitorNodes.length; i++) {
            var item = monitorConf.monitorNodes[i];
            if (!zhu.containerPath(item)) continue;
            var nodeStr = item.delegete ? (item.delegete + " " + item.node) : item.node;
            var node = $(nodeStr);
            if (node != null && node.length > 0) {
                if (node.length == 1) {
                    if (node[0] == ele) {
                        item["relationNode"] = node;
                        return item;
                    }
                } else {
                    var flag = false;
                    node.each(function () {
                        if ($(this)[0] == ele) {
                            flag = true;
                            item["relationNode"] = $(this);
                            return;
                        }
                    });
                    if (flag == false) {
                        var parentNode = $(ele).parents(item.node);
                        if (parentNode != null && parentNode.length > 0) {
                            flag = !flag;
                            item["relationNode"] = parentNode.eq(0);
                        }
                    }
                    if (flag) {
                        return item;
                    }
                }
            }
        }
        return null;
    };
    zhu.doClick = function (e) {
        e = e || window.event;
        var node = e.target || e.srcElement;
        var text = node.textContent || node.innerText;
        var item = zhu.getMonitorNode(node);
        if (item == null) return;
        var linkedVals = zhu.getLinkedVals(item);
        var name = item.relationNode.val() || item.relationNode.text();
        linkedVals = KD.Json.getString(linkedVals);
        if (linkedVals != "noVal") {
            console.log(text);
            console.log(linkedVals);
            sendData("user_action", name, linkedVals);
        }
    };
    zhu.getLinkedVals = function (item) {
        var node = item.relationNode;
        var linked = item.relationLinkedNodes;
        if (linked == null)return;
        var result = [];
        for (var i = 0; i < linked.length; i++) {
            var operator = linked[i];
            var val = zhu.explainOperator(node, operator);
            if (operator.require) {
                if (val == null || val == "")
                    return "noVal";
            }
            result.push({
                name: operator.name,
                selector: operator.selector,
                value: val
            });
        }
        return result;

    };
    zhu.explainOperator = function (curNode, operator) {
        var str = operator.selector;
        var isCur = str.indexOf("_") == 0;
        if (isCur) {
            str = str.substr(1, str.length);
        }
        var selector = str.split("$");
        var node = isCur ? curNode : null;
        var i = 0;
        for (var i = 0; i < selector.length; i++) {
            if (selector[i] != null && selector[i] != "") {
                if (node == null) node = $(selector[i])
                else {
                    if (node == null) return null;
                    else {
                        var args = selector[i].split("\.");
                        var val = selector[i].substr(selector[i].indexOf(".") + 1, selector[i].length);
                        var hasArg = args.length > 1;
                        var isLast = i == selector.length - 1;
                        if (hasArg) {
                            if (isLast && node.length > 1) {
                                var result = [];
                                node.each(function () {
                                    result.push($(this)[args[0]](val));
                                });
                                node = result;
                            } else {
                                node = node[args[0]](val) || null;
                            }
                        }
                        else {
                            if (isLast && node.length > 1) {
                                var result = [];
                                node.each(function () {
                                    result.push($(this)[args[0]]());
                                });
                                node = result;
                            } else {
                                node = node[args[0]]() || null;
                            }
                        }
                    }
                }
            }
        }
        return node;
    }
    function init() {
        for (var i = 0; i < monitorConf.monitorNodes.length; i++) {
            var item = monitorConf.monitorNodes[i];
            if (!zhu.containerPath(item)) continue;
            var dele = item.delegete || document;
            $(dele).delegate(item.node, "click", UA.doClick);
        }
        if (monitorConf.inPage)
            sendData("page_load");

        $(document).ajaxSend(function (evt, request, settings) {
            if (settings.url != monitorConf.sendUrl) {
                var data = {
                    url: settings.url,
                    data: settings.data,
                    type: settings.type
                }
                sendData("ajax", "", JSON.stringify(data))
            }
        });
        // $(document.body).on("click", UA.docClick);
    }

    init();
    function sendData(type, name, data) {
        var pathName = location.pathname
        url = location.href;
        data = {
            operaName: name,
            info: data,
            pathName: pathName,
            url: url,
            type: type
        };
        data = JSON.stringify(data);

        //console.log(data);
        $.ajax({
            url: monitorConf.sendUrl,
            data: data,
            type: "POST",
            contentType: "application/json",
            success: function (data) {
            }
        });
    }
})(window);




