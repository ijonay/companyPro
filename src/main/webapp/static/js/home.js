/**
 *文件用途说明：一览表显示卡片并对卡片进行编辑
 *作者姓名:于莎
 *制作日期：2016/4/15
 **/
var home = home || {};
home.util = {
    cardsInOrder: [],
    isLinkDetail: true,
    setCardSize: function(info) {
        $.ajax({
            type: "put",
            url: dataUrl.util.setCardSort,
            data: JSON.stringify(info),
            dataType: "json",
            cache: false,
            contentType: 'application/json',
            success: function(returnData) {
                if (returnData.error.code != 0) {
                    alert(returnData.error.msg);
                }
            },
            error: function(err) {
                alert("卡片设置信息保存失败");
            },
            complete: function(data) {
                home.util.isLinkDetail = true;
            }
        });
    }
};
$(function() {
    chartsAttr.templates.registerTmpl("card", "tmplCard"); //卡片列表模板
    var cardsOrder = []; //卡片排序号集合
    defCharts.util.getColor(); //获取配色方案
    getCardIds(); // 调用获取卡片id集合方法
    // dialog
    var dlgOptions = {
        show: false,
        backdrop: "static",
        keyboard: false
    };
    var $dlgProcess = $("#dlgProcessing").modal(dlgOptions), //处理模态框
        $dlgDel = $("#dlgDel").modal(dlgOptions); //删除模态框
    // dialog functions
    function showProcessingDlg() { //显示处理模态框
        $dlgProcess.find(".text-center").removeClass("text-danger").text("请稍后……");
        $dlgProcess.find(".modal-footer").hide();
        $dlgProcess.find('.modal-dialog').css('margin-top', (window.innerHeight - 94) / 2);
        $dlgProcess.modal("show");
    }

    function showErrorInfoInProcessingDlg(errorInfo) { //在处理模态框中显示错误信息
        $dlgProcess.find(".text-center").addClass("text-danger").text(errorInfo);
        $dlgProcess.find(".modal-footer").show();
    }

    function closeProcessingDlg() { //隐藏处理模态框
        $dlgProcess.modal("hide");
    }
    
    function showDelDlg(info) {
        if (info.head) {
            $dlgDel.find("#dlgDelTitle").text(info.head);
        }

        if (info.title) {
            $dlgDel.find(".body-title").text(info.title).show();
        } else {
            $dlgDel.find(".body-title").hide();
        }

        if (info.desc) {
            $dlgDel.find(".body-desc").text(info.desc).show();
            $dlgDel.find('.modal-dialog').css('margin-top', (window.innerHeight - 121) / 2);
        } else {
            $dlgDel.find(".body-desc").hide();
            $dlgDel.find('.modal-dialog').css('margin-top', (window.innerHeight - 94) / 2);
        }
        $dlgDel.modal("show");
    }
    // 新建卡片弹窗显示隐藏
    $("#add_select").on("click", function() {
        $('#newCardModal .modal-dialog').css('margin-top', (window.innerHeight - 370) / 2);
    });

    //确认删除卡片
    $("#del-ok").click(function() {
        $dlgDel.modal("hide");
        showProcessingDlg();
        var data = {
            rmType: 1,
            cards: [parseInt(window.localStorage.getItem("delPrevId"))]
        };
        $.ajax({
            dataType: "json",
            url: dataUrl.util.delCard,
            type: "DELETE",
            data: JSON.stringify(data),
            contentType: 'application/json',
            cache: false,
            success: function(returnData) {
                if (returnData.error.code != 0) {
                    showErrorInfoInProcessingDlg(returnData.error.msg);
                } else {
                    $("li[data-index=" + window.localStorage.getItem("delCardId") + "]").remove();
                    $("#cardList").trigger('ss-rearrange');
                    home.util.cardsInOrder = _.reject(home.util.cardsInOrder, function(num) {
                        return num.cardId == window.localStorage.getItem("delCardId");
                    });
                    cardsOrder = _.sortBy(_.pluck(home.util.cardsInOrder, 'order'));
                    closeProcessingDlg();
                }
            },
            error: function(xhr) {
                alert("删除卡片失败");
            }
        });
    });
    $(".select_list>li").hover(function() {//选择数据源列表绑定事件
        var type = $(this).data("type");
        if (type === "select_excel") {
            $(this).css("border", "1px solid #7a7a7a");
        }
    }, function() {
        var type = $(this).data("type");
        if (type === "select_excel") {
            $(this).css("border", "1px solid #ececec");
        }
    });

    //获取用户卡片列表方法开始
    function getCardIds() {
        // 获取用户一览表id
        $.ajax({
            type: 'get',
            url: dataUrl.util.getUserCardIds,
            cache: false,
            success: function(returnData) {
                if (returnData.error.code != 0) {
                    alert(returnData.error.msg);
                } else {
                    if (returnData.data != null) {
                        // 生成图表容器
                        home.util.cardsInOrder = _.sortBy(returnData.data,
                            function(item) {
                                return item.sortNo;
                            });
                        cardsOrder = _
                            .sortBy(_.pluck(returnData.data, 'sortNo'));
                        returnData.data = home.util.cardsInOrder;
                        generateCard(returnData);
                    } else {
                        alert("数据为空");
                    }
                }
            },
            error: function(err) {
                alert("失败了");
            }
        });
    }
    //获取用户卡片列表方法结束

    // 生成图表容器方法开始
    function generateCard(returnData) {
        // 生成卡片列表
        $("#cardList").html(chartsAttr.templates.card.render(returnData));
        // 显示调整卡片大小图标
        $("#cardList > li").hover(function() {
            $(this).find(".item-bottom-left,.item-bottom-right").css("display", "block");
        }, function() {
            $(this).find(".item-bottom-left,.item-bottom-right").css("display", "none");
            $(this).find(".item-top-set").css("display", "none");
        });
        // 为卡片列表绑定拖拽事件
        $("#cardList").shapeshift({
            handle: ".item-top",
            minColumns: 2,
            align: 'left',
            enableCrossDrop: true,
            enableResize: true,
            colWidth: 195,
            minHeight: 220,
            gutterX: 5,
            gutterY: 5,
            animated: false
        });
        $("#cardList").on("ss-drop-complete", function(e) {// 拖拽完成之后将卡片排序结果保存到库
            var finalCardsInOrder = []; // 卡片的最终排序
            $.each($(this).children(".ss-active-child"), function(idx, ele) {
                var cardId = $(this).data("index");
                $.each(home.util.cardsInOrder, function(itemIdx, item) {
                    if (cardId === item.cardId) {
                        item.order = cardsOrder[idx];
                        var singleItem = {
                            type: item.type,
                            id: item.id,
                            order: item.order,
                            size: parseInt(item.size)
                        };
                        finalCardsInOrder.push(singleItem);
                    }
                });
            });
            // 将卡片排序保存到数据库
            home.util.setCardSize(finalCardsInOrder);
        });
        // 拖拽改变卡片大小
        $(".item-bottom-right,.item-bottom-left").resizable({
            minW: 195,
            minH: 220,
            maxW: 395,
            maxH: 445
        });
        // 获取每一个卡片的图表数据与设置信息
        try {
            $.each($("#cardList > li"), function(idx) {
                var cardId = $(this).data("index");
                var cardSize = $(this).data("size");
                var $title = $(this).find(".fl-title");
                var $total = $(this).find(".fl-total");
                var $desc = $(this).find(".fl-desc");
                var $chartPnl = $(this).find("#main" + cardId);
                var $tabId = $(this).find(".tableId");
                $.ajax({
                    type: 'get',
                    url: dataUrl.util.getCardInfo(cardId),
                    cache: false,
                    success: function(returnData) {
                        if (returnData.error.code != 0) {
                            $chartPnl.find(".error").html(returnData.error.msg).css("display", "block");
                        } else {
                            if (returnData.data != null) {
                                if (returnData.data.cardInfo) {
                                    if (returnData.data.cardInfo.title) {
                                        $title.text(returnData.data.cardInfo.title).css("display", "block");
                                    }
                                    if(idx==-1&&returnData.data.cardInfo.alertUnreadNotify+returnData.data.cardInfo.alertCount>0){
                                        $("<img class='linkDetail dispAlert' style='vertical-align:top;margin-right:5px;cursor:pointer;' src='img/bell-invalid.png'>").prependTo($title);
                                    }else if (returnData.data.cardInfo.alertUnreadNotify && returnData.data.cardInfo.alertUnreadNotify > 0) {
                                        $("<img class='linkDetail dispAlert' style='vertical-align:top;margin-right:5px;cursor:pointer;' src='img/bell-readed.png'>").prependTo($title);
                                    }else if (returnData.data.cardInfo.alertCount && returnData.data.cardInfo.alertCount > 0) {
                                        $("<img class='linkDetail dispAlert' style='vertical-align:top;margin-right:5px;cursor:pointer;' src='img/bell-unread.png'>").prependTo($title);
                                    } 
                                    
                                    if (returnData.data.cardInfo.focusValue) {
                                        var focusValue = returnData.data.cardInfo.focusValue;
                                        if (returnData.data.cardInfo.unit) focusValue += returnData.data.cardInfo.unit;
                                        $total.text(focusValue).css("display", "block");
                                    }
                                    if (returnData.data.cardInfo.subTitle) {
                                        $desc.text(returnData.data.cardInfo.subTitle).css("display", "block");
                                    }
                                    if (returnData.data.cardInfo.tableId) {
                                        $tabId.text(returnData.data.cardInfo.tableId);
                                    }
                                }
                                // 生成图表
                                defCharts.util.generateChart("main" + cardId, returnData.data, false, cardId, cardSize);
                            } else {
                                $chartPnl.find(".error").html("卡片数据为空").css("display", "block");
                            }
                        }
                    },
                    error: function(err) {
                        $chartPnl.find(".error").html("获取卡片数据失败了").css("display", "block");
                    }
                });
            });
        } catch (e) {
            alert(e.toString());
        }
    }
    // 生成图表容器方法结束
    $(document).delegate(".item-top-right li", "click", function(e) { //卡片右上角菜单栏绑定点击事件
        var ev = e || window.event;
        if (ev.stopPropagation) {
            ev.stopPropagation();
        } else if (window.event) {
            window.event.cancelBubble = true; // 兼容IE
        }
        var type = $(this).data("type");
        if (type === "set") {
            $set = $(this).parents(".item-top").children(".item-top-set");
            if ($set.css("display") === "none") {
                $(".item-top-set").css("display", "none");
                $set.css("display", "block");
            } else {
                $set.css("display", "none");
            }
        }
    }).delegate(".item-top-right li", "mouseover", function(e) { //卡片右上角菜单栏绑定鼠标到达事件
        var type = $(this).data("type"),
            $img = $(this).find("img");
        if (type === "datasource")
            $img.attr("src", "img/card_datasource_excel_hover.png");
        if (type === "comment")
            $img.attr("src", "img/card_comments_hover.png");
        if (type === "set")
            $img.attr("src", "img/card_set_hover.png");
    }).delegate(".item-top-right li", "mouseout", function(e) { //卡片右上角菜单栏绑定鼠标离开事件
        var type = $(this).data("type"),
            $img = $(this).find("img");
        if (type === "datasource")
            $img.attr("src", "img/card_datasource_excel_normal.png");
        if (type === "comment")
            $img.attr("src", "img/card_comments_normal.png");
        if (type === "set")
            $img.attr("src", "img/card_set_normal.png");
    }).delegate(".item-top-set li", "click", function(e) { //卡片设置列表绑定点击事件
        var e = e || event;
        if (e.stopPropagation) {
            e.stopPropagation();
        } else if (event) {
            event.cancelBubble = true; // 兼容IE
        }
        var type = $(this).data("type");
        var cardId = $(this).parents("li").data("index");
        var tabId = $(this).parents("li").find(".tableId").text();
        var prevId = $(this).parents("li").data("previd");
        if (type === "edit") {
            window.location.href = "showtabledata?cardId=" + cardId + "&tableId=" + tabId;
        } else if (type === "del") {
            window.localStorage.setItem("delPrevId", prevId);
            window.localStorage.setItem("delCardId", cardId);
            var info = {
                    head: "删除卡片",
                    title: "确认要把所选卡片放入回收站吗？",
                    desc: "删除的卡片可在30天内通过回收站还原"
                };
            showDelDlg(info);
            $("#operater-type").text("del");
        }
    }).delegate(".linkDetail","click",function() {//图表点击跳转至详情页
        var cardId = $(this).parents("li").data("index"),
            tabId = $(this).parents("li").find(".tableId").text();
        if (home.util.isLinkDetail) {
            window.location.href = "maindata?cardId=" + cardId + "&tableId=" + tabId + "&dispAlert=" + $(this).hasClass("dispAlert");
        }
    }).delegate(".pnl-operate","mouseover",function(){
        $(this).find(".btn-operate").css("display","block");
    }).delegate(".pnl-operate","mouseout",function(){
        $(this).find(".btn-operate").css("display","none");
    }).delegate(".btn-operate","mouseover",function(){
        $(this).css("background","#818181").find("img").css("opacity","1");
    }).delegate(".btn-operate","mouseout",function(){
        $(this).css("background","#bfbfbf").find("img").css("opacity","0.8");
    }).delegate(".btn-operate>img","click",function(){
        var cardId = $(this).parents("li").data("index");
        var tabId = $(this).parents("li").find(".tableId").text();
        var prevId = $(this).parents("li").data("previd");
        if($(this).parent(".btn-operate").hasClass("edit-card")){
            window.location.href = "showtabledata?cardId=" + cardId + "&tableId=" + tabId;
        }else if($(this).parent(".btn-operate").hasClass("del-card")){
            window.localStorage.setItem("delPrevId", prevId);
            window.localStorage.setItem("delCardId", cardId);
            var info = {
                    head: "删除卡片",
                    title: "确认要删除选中的卡片吗？",
                    desc: "删除的卡片将无法找回"
                };
            showDelDlg(info);
            $("#operater-type").text("destroy");
        }
    });
});