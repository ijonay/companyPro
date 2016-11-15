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
    <jsp:attribute name="title">-Reids设置管理</jsp:attribute>
    <jsp:attribute name="script">
        <script src="../lib/KDUpload.js"></script>
        <script src="../js/admin/redisset.js"></script>
    </jsp:attribute>
    <jsp:attribute name="footScript">
        <script type="text/javascript">
            $(function () {
                $("a.connId").click(function () {
                    $("span.connId").text($(this).text());
                    var id = $(this).attr("data-id");
                    $("#connId").val(id);
                });
//                KDUpload.BinderUpload({
//                    ".uploadFile": {
//                        "url": "../api/uploadXml?caseId=1",
//                        "type": ["xml"],
//                        flag: true
//                    }
//                }).Success(function (result) {
//                    $("input[data-bindId='" + result.id + "']").val(result.savePath);
//                    $("a[data-bindId='" + result.id + "']").attr("href", result.savePath).html(result.savePath);
//                });//flag：是否Id绑定

                $("button.submit1").click(function () {

                    Model.updateWordsToRedis(KD.Json.getString(KD.Form.getParams()), function (msg) {
                        if (msg.error.code == 0) {
                            KD.showWarning("更新语料库键值对到redis执行成功！");
                        } else {
                            KD.showWarning(msg.error.message);
                        }
                    });
                });
                $("button.submit2").click(function () {
                    Model.updateTopicVectorCollToRedis(KD.Json.getString(KD.Form.getParams()), function (msg) {
                        if (msg.error.code == 0) {
                            KD.showWarning("更新Topic坐标集合到redis单个键值中执行成功！");
                        } else {
                            KD.showWarning(msg.error.message);
                        }
                    });
                });
                $("button.submit3").click(function () {
                    Model.updateTopicVertorsToRedis(KD.Json.getString(KD.Form.getParams()), function (msg) {
                        if (msg.error.code == 0) {
                            KD.showWarning("更新Topic坐标到redis执行成功！");
                        } else {
                            KD.showWarning(msg.error.message);
                        }
                    });
                });

                $("button.submit4").click(function () {

                    Model.updateWordsCollToRedis(KD.Json.getString(KD.Form.getParams()), function (msg) {
                        if (msg.error.code == 0) {
                            KD.showWarning("更新语料库集合到redis执行成功！");
                        } else {
                            KD.showWarning(msg.error.message);
                        }
                    });
                });

            });
        </script>
    </jsp:attribute>
    <jsp:body>

        <div class="jumbotron">
            <div class="container">
                <form class="form-horizontal">
                    <div class="form-group">
                            <%--<div class="col-sm-offset-2 col-sm-10">--%>
                        <button type="button" class="btn btn-default submit1">更新语料库键值对到redis</button>
                            <%--</div>--%>
                    </div>
                    <div class="form-group">
                            <%--<div class="col-sm-offset-2 col-sm-10">--%>
                        <button type="button" class="btn btn-default submit4">更新语料库集合到redis</button>
                            <%--</div>--%>
                    </div>
                    <div class="form-group">
                            <%--<div class="col-sm-offset-2 col-sm-10">--%>
                        <button type="button" class="btn btn-default submit2">更新Topic坐标集合到redis单个键值中</button>
                            <%--</div>--%>
                    </div>
                    <div class="form-group">
                            <%--<div class="col-sm-offset-2 col-sm-10">--%>
                        <button type="button" class="btn btn-default submit3">更新Topic坐标集合到redis</button>
                            <%--</div>--%>
                    </div>
                </form>
            </div>
        </div>

    </jsp:body>

</t:template>

