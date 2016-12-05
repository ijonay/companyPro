<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/admin"
%>
<t:template>
    <jsp:attribute name="script">
    </jsp:attribute>
    <jsp:body>
        <div class="container col-md-12">
            <h1 class="page-header">版本管理</h1>
            <div class="row">
                <div class="col-md-1">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default addModel">
                            添加版本
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
                        <th>版本</th>
                        <th>创建时间</th>
                        <th class="col-md-4">操作</th>
                    </tr>
                    </thead>
                    <tbody id="temp_Container" style="display: none;">
                    <tr>
                        <td>{index}</td>
                        <td>{name}</td>
                        <td>{version}</td>
                        <td>{createTime}</td>
                        <td>
                            <a href="#" class="editModel" data-id="{id}">编辑</a> |
                            <a href="#" class="delModel" data-id="{id}">删除</a>
                        </td>
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
                        <h4 class="modal-title">信息管理</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" role="form" id="modelForm">

                            <div class="form-group">
                                <label for="name" class="col-sm-3 control-label">名称：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="name" data-role="val"
                                           placeholder="名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="version" class="col-sm-3 control-label">版本号：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="version" data-role="val"
                                           placeholder="0.0.0">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="createTime" class="col-sm-3 control-label">创建时间：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="createTime" data-role="val"
                                           placeholder="创建时间">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="introduction" class="col-sm-3 control-label">介绍：</label>
                                <div class="col-sm-9">
                                    <textarea class="form-control" id="introduction" data-role="val"
                                              placeholder="介绍" rows="10" cols="20"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="description" class="col-sm-3 control-label">描述：</label>
                                <div class="col-sm-9">
                                    <textarea class="form-control" id="description" data-role="val"
                                              placeholder="描述" rows="10" cols="20"></textarea>
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
                var temp_Container = $("#temp_Container").html();
                var curObj = null;

                var NetPage = new SimplePage("../api/proinfo/versions/page", temp_Container, "temp_Container");

                function initData() {

                    bindPage();

                    $(".addModel").click(function () {
                        //$("#modelModal input:text,textarea").val("");
                        $("#modelForm")[0].reset();
                        curObj = null;
                        $('#modelModal').modal();
                    });

                    $(".save").click(function () {
                        var vailData = [
                            {objId: "name", defSubject: "名称"}
                        ];
                        var flag = KD.Form.validateField(vailData, KD.ShowDialog.showWarning);
                        if (flag) {
                            var jsonData = KD.Form.getParams();
                            jsonData = $.extend({}, curObj, jsonData);
                            CMD.po("proinfo.version", KD.Json.getString(jsonData), function (data) {
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
                            CMD.d("proinfo.version." + id, NetPage.DoLoadPage);
                        }
                    });
                    $("a.editModel").click(function () {
                        var id = $(this).attr("data-id");
                        CMD.g("proinfo.version." + id, function (data) {
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

