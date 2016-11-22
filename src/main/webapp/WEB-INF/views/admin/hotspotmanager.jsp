<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/admin"
%>
<t:template>
    <jsp:attribute name="script">
        <script src="../lib/bootstrap-datepicker.min.js"></script>
        <script src="../js/admin/hotspot.js"></script>
        <link href="../css/bootstrap-datepicker.min.css"/>
    </jsp:attribute>
    <jsp:body>
        <div class="container col-md-12">
            <h1 class="page-header">Dashboard</h1>
            <div class="row">
                <div class="col-md-1">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default addModel">
                            添加预告
                        </button>
                    </div>

                </div>

            </div>
            <br/>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>名称</th>
                        <th>开始时间</th>
                        <th>结束时间</th>
                        <th class="col-md-4">操作</th>
                    </tr>
                    </thead>
                    <tbody id="temp_Container" style="display: none;">
                    <tr>
                        <td>{index}</td>
                        <td>{name}</td>
                        <td>{startDate}</td>
                        <td>{endDate}</td>
                        <td>
                            <a href="#" class="edit" data-id="{id}">编辑</a> |
                            <a href="#" class="del" data-id="{id}">删除</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>


        <div class="modal fade" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                                class="sr-only">Close</span></button>
                        <h4 class="modal-title">预告管理</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" role="form" id="userForm">
                            <div class="form-group">
                                <label for="name" class="col-sm-3 control-label">预告名称：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="name" data-role="val"
                                           placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">起止时间：</label>
                                <div class="col-sm-9">
                                    <div class="input-group input-daterange">
                                        <input type="text" id="startDate" data-role="val" class="form-control"
                                               value="2016-11-22">
                                        <span class="input-group-addon">to</span>
                                        <input type="text" id="endDate" data-role="val" class="form-control"
                                               value="2016-11-30">
                                    </div>
                                </div>
                            </div>
                                <%--<div class="form-group">--%>
                                <%--<label for="startDate" class="col-sm-3 control-label">开始时间：</label>--%>
                                <%--<div class="col-sm-9">--%>
                                <%--<input type="text" class="form-control" id="startDate" data-role="val"--%>
                                <%--placeholder="">--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                <%--<label class="col-sm-3 control-label">结束时间：</label>--%>
                                <%--<div class="col-sm-9">--%>
                                <%--<input type="text" class="form-control" id="endDate" data-role="val"--%>
                                <%--placeholder="">--%>
                                <%--</div>--%>
                                <%--</div>--%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">内容：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="note" data-role="val"
                                           placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">微博标题：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="weiboTitle" data-role="val"
                                           placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">微博链接：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="weiboUrl" data-role="val"
                                           placeholder="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">微博阅读数：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="weiboReadNum" data-role="val"
                                           placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">微博转发量：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="weiboForwardNum" data-role="val"
                                           placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">微博评论数：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="weiboCommentsNum" data-role="val"
                                           placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">微博点赞数：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="weiboLoveNum" data-role="val"
                                           placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">微信标题：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="weixinTitle" data-role="val"
                                           placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">微信链接：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="weixinUrl" data-role="val"
                                           placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">微信阅读数：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="weixinReadNum" data-role="val"
                                           placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">知乎标题：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="zhihuTitle" data-role="val"
                                           placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">知乎链接：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="zhihuUrl" data-role="val"
                                           placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">知乎回答数：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="zhihuAnswerNum" data-role="val"
                                           placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">百度关键词：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="baiduSearchKeyword" data-role="val"
                                           placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">百度链接：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="baiduUrl" data-role="val"
                                           placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">百度结果量：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="baiduSearchNum" data-role="val"
                                           placeholder="">
                                </div>
                            </div>

                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary save">Save changes</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->


        <script type="text/javascript">

            $(function () {

                $('.input-daterange input').each(function () {
                    $(this).datepicker({
                        format: 'yyyy-mm-dd'
                    });
                });

                var temp_Container = $("#temp_Container").html();
                var curObj = null;

                function initData() {
                    $("#temp_Container").hide();
                    $.when(Hotspot.getAll(function (data) {
                        if (data.data == null || data.data.length < 1) return;
                        KD.Json.each(data.data, function (item) {

                            try {
                                item.createDate = KD.Date.format(item.createDate, 'yyyy-MM-dd');
                            } catch (ex) {
                            }

                            try {
                                item.startDate = KD.Date.format(item.startDate, 'yyyy-MM-dd');
                            } catch (ex) {
                            }
                            try {
                                item.endDate = KD.Date.format(item.endDate, 'yyyy-MM-dd');
                            } catch (ex) {
                            }

                            item["activeName"] = (item.isActive == 0) ? "启用" : "停用";
                        })
                        var result = KD.Json.binderJson(data.data, temp_Container);
                        $("#temp_Container").html(result).show();
                    })).then(function () {
                        $("a.state").click(function (e) {
                            e.preventDefault();
                            var id = $(this).attr("data-id");
                            var val = ("1" == $(this).attr("data-val")) ? 0 : 1;
                            Hotspot.updateState(id, val, initData);
                        })
                        $("a.del").click(function (e) {
                            e.preventDefault();
                            var id = $(this).attr("data-id");
                            if (confirm("确定要删除吗？"))
                                Hotspot.updateState(id, 0, initData);
//                                Hotspot.del(id, initData);
                        });
                        $("a.edit").click(function (e) {
                            e.preventDefault();
                            var id = $(this).attr("data-id");
                            Hotspot.get(id, function (data) {
                                try {
                                    data.data.startDate = KD.Date.format(data.data.startDate, 'yyyy-MM-dd');
                                } catch (ex) {
                                }
                                try {
                                    data.data.endDate = KD.Date.format(data.data.endDate, 'yyyy-MM-dd');
                                } catch (ex) {
                                }

                                KD.Form.bindJsonToNode(data.data);

                                curObj = data.data;

                                $('#myModal').modal();
                            });
                        })
                    });

                    $(".addModel").click(function (e) {
                        e.preventDefault();
                        if (curObj != null) {
                            $("input[data-role='val']").val("");
                        }

                        curObj = null;

                        $('#myModal').modal();
                    });


                    $(".save").click(function (e) {
                        e.preventDefault();
//                        var vailData = [
//                            {objId: "userName", defSubject: "用户名"}
//                        ];
//                        var flag = KD.Form.validateField(vailData, KD.ShowDialog.showWarning);
//                        if (flag) {
                        var jsonData = KD.Form.getParams();
                        jsonData = $.extend({}, curObj, jsonData);
                        Hotspot.edit(KD.Json.getString(jsonData), function (data) {
                            if (data.error.code == 0) {
                                initData();
                                KD.showWarning("操作成功！");
                                $('#myModal').modal("hide");
                            } else {
                                KD.showWarning(data.error.message);
                            }
                        })
//                        }
                    });

                }

                initData();

            });

        </script>
    </jsp:body>
</t:template>

