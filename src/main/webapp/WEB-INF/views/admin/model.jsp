<%--
  Created by IntelliJ IDEA.
  User: xyzhuzhou
  Date: 2016/8/8 0008
  Time: 11:53:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template>
    <jsp:attribute name="title">-模型管理</jsp:attribute>
    <jsp:attribute name="script">
        <script src="../lib/KDUpload.js"></script>
        <script src="../js/admin/model.js"></script>
    </jsp:attribute>
    <jsp:attribute name="footScript">
        <script type="text/javascript">
            $(function () {
                comm.checkActive("model");
                $("a.connId").click(function () {
                    $("span.connId").text($(this).text());
                    var id = $(this).attr("data-id");
                    $("#connId").val(id);
                });
                KDUpload.BinderUpload({
                    ".uploadFile": {
                        "url": "../api/uploadXml?caseId=1",
                        "type": ["xml"],
                        flag: true
                    }
                }).Success(function (result) {
                    $("input[data-bindId='" + result.id + "']").val(result.savePath);
                    $("a[data-bindId='" + result.id + "']").attr("href", result.savePath).html(result.savePath);
                });//flag：是否Id绑定

                $("button.submit").click(function () {
                    var vailData = [{
                        objId: "connId",
                        defSubject: "模型类别"
                    }, {
                        objId: "xmlPath",
                        defSubject: "模型"
                    }
                    ];
                    var flag = KD.Form.validateField(vailData, KD.ShowDialog.showWarning);
                    if (flag) {
                        Model.submit(KD.Json.getString(KD.Form.getParams()), function (msg) {
                            if (msg.error.code == 0) {
                                KD.showWarning("上传成功！", function () {
                                    location.reload();
                                });
                            } else {
                                KD.showWarning(msg.error.msg);
                            }
                        });
                    }
                })
            });
        </script>
    </jsp:attribute>
    <jsp:body>

        <div class="jumbotron">
            <div class="container">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="dropdownMenu1" class="col-sm-3 control-label">请选择模型类别：</label>
                        <div class="col-sm-4">
                            <div class="dropdown">
                                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <span class="connId">模型类别</span>
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                    <c:forEach var="item" items="${types}">
                                        <li><a href="#" data-id="${item.id}" class="connId">${item.name}</a></li>
                                    </c:forEach>
                                </ul>
                                <input type="hidden" id="connId" data-role="val"/>
                            </div>
                        </div>
                        <div class="col-sm-5"></div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">请输入模型名称：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" placeholder="模型名称" data-role="val">
                        </div>
                        <div class="col-sm-5"></div>
                    </div>
                    <div class="form-group">
                        <label for="modelFile" class="col-sm-3 control-label">请选择要上传的模型：</label>
                        <div class="col-sm-4">
                            <label for="modelFile" class="btn btn-default">
                                点击上传
                            </label>
                            <div style=" overflow: hidden;height: 0px; ">
                                <input type="file" class="uploadFile" id="modelFile" style="display: block;padding:8px 0
                             0 0;">

                            </div>
                            <input type="hidden" id="xmlPath" data-bindId="modelFile" data-role="val"/> <a href="#"
                                                                                                           data-bindId="modelFile"></a>
                        </div>
                        <div class="col-sm-5"></div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="button" class="btn btn-default submit">上传模型</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </jsp:body>

</t:template>

