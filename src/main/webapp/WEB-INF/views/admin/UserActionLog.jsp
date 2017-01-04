<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/admin"
%>
<t:template>
    <jsp:attribute name="script">
    </jsp:attribute>
    <jsp:body>
        <div class="container col-md-12">
            <h1 class="page-header">UserActionLog信息管理</h1>
            <div class="row">
                <div class="col-md-1">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default addModel">
                            添加UserActionLog
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
                        <td>操作类型</td>
                        <td>用户名</td>
                        <td>操作名称</td>
                        <td>详细</td>
                        <td>路径名称</td>
                        <%--<td>全路径</td>--%>
                        <td>创建时间</td>
                        <%--<th class="col-md-4">操作</th>--%>
                    </tr>
                    </thead>
                    <tbody id="temp_Container" style="display: none;">
                    <tr>
                        <td>{index}</td>
                        <td>{type}</td>
                        <td>{userName}</td>
                        <td>{operaName}</td>
                        <td>{info}</td>
                        <td>{pathName}</td>
                        <%--<td>{url}</td>--%>
                        <td>{createTime}</td>
                            <%--<td>--%>
                            <%--<a href="#" class="editModel" data-id="{id}">编辑</a> |--%>
                            <%--<a href="#" class="delModel" data-id="{id}">删除</a>--%>
                            <%--</td>--%>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="modal fade" id="modelModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                                class="sr-only">Close</span></button>
                        <h4 class="modal-title">UserActionLog管理</h4>
                    </div>
                    <form class="modal-body">
                        <form class="form-horizontal" role="form" id="modelForm">

                            <div class="form-group">
                                <label for="id" class="col-sm-3 control-label">id：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="id" data-role="val"
                                           placeholder="id">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="type" class="col-sm-3 control-label">type：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="type" data-role="val"
                                           placeholder="type">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="userId" class="col-sm-3 control-label">user_id：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="userId" data-role="val"
                                           placeholder="user_id">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="userName" class="col-sm-3 control-label">user_name：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="userName" data-role="val"
                                           placeholder="user_name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="operaName" class="col-sm-3 control-label">opera_name：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="operaName" data-role="val"
                                           placeholder="opera_name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="info" class="col-sm-3 control-label">info：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="info" data-role="val"
                                           placeholder="info">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="pathName" class="col-sm-3 control-label">path_name：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="pathName" data-role="val"
                                           placeholder="path_name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="url" class="col-sm-3 control-label">url：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="url" data-role="val"
                                           placeholder="url">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="createTime" class="col-sm-3 control-label">create_time：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="createTime" data-role="val"
                                           placeholder="create_time">
                                </div>
                            </div>


                        </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary save">Save changes</button>
                </div>
            </div><!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->


        <script type="text/javascript">

            $(function () {
                var temp_Container = $("#temp_Container").html();
                var curObj = null;

                var NetPage = new SimplePage("../api/userActionLog/page", temp_Container, "temp_Container");

                function initData() {

                    bindPage();

                    $(".addModel").click(function () {
                        $('#modelModal').modal();
                        curObj = null;
                        $("#modelForm")[0].reset();
                    });

                    $(".save").click(function () {
                        var vailData = [
                            {objId: "id", defSubject: "id"},
                            {objId: "type", defSubject: "type"},
                            {objId: "userId", defSubject: "user_id"},
                            {objId: "userName", defSubject: "user_name"},
                            {objId: "operaName", defSubject: "opera_name"},
                            {objId: "info", defSubject: "info"},
                            {objId: "pathName", defSubject: "path_name"},
                            {objId: "url", defSubject: "url"},
                            {objId: "createTime", defSubject: "create_time"},
                        ];
                        var flag = KD.Form.validateField(vailData, KD.ShowDialog.showWarning);
                        if (flag) {
                            var jsonData = KD.Form.getParams();
                            jsonData = $.extend({}, curObj, jsonData);
                            CMD.po("userActionLog", KD.Json.getString(jsonData), function (data) {
                                if (data.error.code == 0) {
                                    NetPage.DoLoadPage();
                                    KD.showWarning("操作成功！");
                                    $('#modelModal').modal("hide");
                                } else {
                                    KD.showWarning(data.error.message);
                                }
                            })
                        }
                    });

                }

                initData();

                function bindPage() {
                    NetPage.Params = "caseId=1"; //参数传递
                    NetPage.PageSize = 15; //每页显示量
                    NetPage.FilterResult(function (data) {
                        return data.data;
                    });
                    NetPage.FilterData(function (data) {
                        Comm.ReplaceFields(data);
                    });
                    NetPage.PageLoadReady(function (JsonData) {
                        bindFunc();
                    });
                    NetPage.DoLoadPage();
                }

                function bindFunc() {
                    $("a.delModel").click(function () {
                        if (confirm("确定要删除吗？")) {
                            var id = $(this).attr("data-id");
                            CMD.d("userActionLog." + id, NetPage.DoLoadPage);
                        }
                    });
                    $("a.editModel").click(function () {
                        var id = $(this).attr("data-id");
                        CMD.g("userActionLog." + id, function (data) {
                            curObj = data.data;
                            Comm.ReplaceJsonItem(curObj);
                            KD.Form.bindJsonToNode(curObj);
                            $('#modelModal').modal();
                        });
                    })

                }
            });

        </script>
    </jsp:body>
</t:template>

