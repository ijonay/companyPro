<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/admin"
%>
<t:template>
    <jsp:attribute name="script">
         <script src="../js/admin/user.js"></script>
    </jsp:attribute>
    <jsp:body>
        <div class="container col-md-12">
            <h1 class="page-header">Dashboard</h1>
            <div class="row">
                <div class="col-md-1">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default addUser">
                            添加用户
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
                        <th>公司名称</th>
                        <th>账户名称</th>
                        <th class="col-md-4">操作</th>
                    </tr>
                    </thead>
                    <tbody id="temp_Container" style="display: none;">
                    <tr>
                        <td>{index}</td>
                        <td>{company}</td>
                        <td>{userName}</td>
                        <td>
                            <a href="#" class="state" data-id="{id}" data-val="{isactive}">{activeName}</a> |
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
                        <h4 class="modal-title">用户管理</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" role="form" id="userForm">
                            <div class="form-group">
                                <label for="nickname" class="col-sm-2 control-label">公司：</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="company" data-role="val"
                                           placeholder="公司名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">账户名：</label>
                                <div class="col-sm-10">
                                    <input type="email" class="form-control" id="userName" data-role="val"
                                           placeholder="邮箱">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="nickname" class="col-sm-2 control-label">昵称：</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="nickname" data-role="val"
                                           placeholder="昵称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-2 control-label">Password</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="password" data-role="val"
                                           placeholder="Password" value="1234567">
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

                function initData() {
                    $("#temp_Container").hide();
                    $.when(User.getUsers(function (data) {
                        if (data.data == null || data.data.length < 1) return;
                        KD.Json.each(data.data, function (item) {
                            item["activeName"] = (item.isactive == false) ? "启用" : "停用";
                        })
                        var result = KD.Json.binderJson(data.data, temp_Container);
                        $("#temp_Container").html(result).show();
                    })).then(function () {
                        $("a.state").click(function () {
                            var id = $(this).attr("data-id");
                            var val = !("true" == $(this).attr("data-val"));
                            User.updateState(id, val, initData);
                        })
                        $("a.del").click(function () {
                            var id = $(this).attr("data-id");
                            User.del(id, initData);
                        });
                        $("a.edit").click(function () {
                            var id = $(this).attr("data-id");
                            User.getUser(id, function (data) {

                                KD.Form.bindJsonToNode(data.data);

                                curObj = data.data;

                                $('#myModal').modal();
                            });
                        })
                    });

                    $(".addUser").click(function () {
                        curObj = null;
                        $("input[data-role='val']").val("");
                        $('#myModal').modal();
                    });


                    $(".save").click(function () {
                        var vailData = [
                            {objId: "userName", defSubject: "用户名"}
                        ];
                        if (curObj == null) {
                            vailData.push({objId: "password", defSubject: "密码", minLength: 8, maxLength: 25});
                            var flag = KD.Form.validateField(vailData, KD.ShowDialog.showWarning);
                            if (flag) {
                                var jsonData = KD.Form.getParams();
                                jsonData = $.extend({}, curObj, jsonData);
                                jsonData["confirmPassword"] = jsonData.password;
                                User.addUser(KD.Json.getString(jsonData), function (data) {
                                    if (data.error.code == 0) {
                                        initData();
                                        KD.showWarning("操作成功！");
                                        $('#myModal').modal("hide");
                                    } else {
                                        KD.showWarning(data.error.message);
                                    }
                                })
                            }
                        } else {
                            var flag = KD.Form.validateField(vailData, KD.ShowDialog.showWarning);
                            if (flag) {
                                var jsonData = KD.Form.getParams();
                                jsonData = $.extend({}, curObj, jsonData);
                                User.updateUser(curObj.id, KD.Json.getString(jsonData), function (data) {
                                    if (data.error.code == 0) {
                                        initData();
                                        KD.showWarning("操作成功！");
                                        $('#myModal').modal("hide");
                                    } else {
                                        KD.showWarning(data.error.message);
                                    }
                                })
                            }
                        }
                    });

                }

                initData();

            });

        </script>
    </jsp:body>
</t:template>

