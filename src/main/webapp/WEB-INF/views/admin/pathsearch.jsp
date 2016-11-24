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
    <jsp:attribute name="script">
         <script src="../js/admin/user.js"></script>
    </jsp:attribute>
    <jsp:attribute name="style">
        <style type="text/css">
            .path {
                padding: 8px 15px;
                margin-bottom: 20px;
                list-style: none;
                background-color: #f5f5f5;
                border-radius: 4px;
            }

            .path > li {
                display: inline-block;
            }

            .path > li:before, :after {
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
            }

            .path > li + li:before {
                padding: 0 5px;
                color: #ccc;
                content: "->";
            }

        </style>
    </jsp:attribute>
    <jsp:body>
        <div class="container col-md-12">
            <h1 class="page-header">路径探索</h1>

            <br/>
            <div class="form-horizontal">
                <div class="col-sm-12">
                    <div class="form-group col-sm-4">
                        <label for="startWord" class="col-sm-5 control-label">开始项：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="startWord">
                        </div>
                    </div>
                    <div class="form-group col-sm-4">
                        <label for="endWord" class="col-sm-5 control-label">结束项：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="endWord">
                        </div>
                    </div>

                </div>
                <div class="col-sm-12" style="margin: 10px 0 20px;border-bottom: 1px solid #eee;"></div>
                <div class="col-sm-12" style="display: none">
                    <h4>已探索路径：</h4>
                    <ol class="path">

                    </ol>
                </div>
                <div class="col-sm-12">
                    <div class="form-group col-sm-4">
                        <label for="start" class="col-sm-5 control-label">当前节点：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="start" data-role="val" readonly>
                        </div>
                    </div>
                    <div class="form-group col-sm-4">
                        <label for="end" class="col-sm-5 control-label">下一个目标：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="end" data-role="val">
                        </div>
                    </div>
                    <div class="form-group col-sm-4">
                        <div class="col-sm-4">
                            <button type="submit" id="explore" class="btn btn-default explore">
                                explore
                            </button>
                        </div>
                            <%--<div class="col-sm-4">--%>
                            <%--<button type="button" class="btn btn-default savePath">--%>
                            <%--保存到当前路径--%>
                            <%--</button>--%>
                            <%--</div>--%>
                    </div>

                </div>
                <div class="col-sm-12">
                    <div class="col-sm-5" style="height: 500px;overflow: hidden;overflow-y:auto;">
                        <table class="table table-striped" style="display: none;" id="result">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>词名称</th>
                                <th>相似度</th>
                            </tr>
                            </thead>
                            <tbody id="temp_Container">
                            <tr>
                                <td>{index}</td>
                                <td>{name}</td>
                                <td>{value}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">

            $(function () {
                KD.Global.setEnterBtn("#explore");
                var flag = true;
                var temp_Container = $("#temp_Container").html();
                var array = [];
                $("#startWord").keyup(function () {
                    endFlag = false;
                    start = "";
                    end = "";
                    array = [];
                    var val = $(this).val();
                    var val1 = $("#start").val();
                    if (KD.isNull(val1) || flag == true) {
                        $("#start").val(val);
                        next = "";
                    }
                }).blur(function () {
                    flag = true;

                });
                var endFlag = false;
                var start = "";
                var end = "";
                $("#end").focus(function () {
                    var val = $(this).val();
                    if (KD.isNull(val)) {
                        return;
                    }
                    if (!endFlag)return;
                    start = $("#start").val();
                    end = $(this).val();
                    $("#start").val(end);
                    $(this).val("");
                }).blur(function () {
                    var val = $(this).val();
                    if (KD.isNull(val)) {
                        if (KD.isNotNull(start))
                            $("#start").val(start);
                        if (KD.isNotNull(end))
                            $(this).val(end);
                    }
                });



                $(".savePath").click(bindPath);

                $(".explore").click(function () {

                    getResult();
                });
                function getResult() {
                    var vailData = [
                        {objId: "start", defSubject: "当前节点"},
                        {objId: "end", defSubject: "下一个目标"}
                    ];
                    var flag = KD.Form.validateField(vailData, KD.ShowDialog.showWarning);
                    if ($("#start").val() == $("#end").val()) {

                        KD.showWarning("当前节点与下一个目标不能相同！");
                        return;
                    }
                    if (flag) {

                        var jsonData = KD.Form.getParams();
                        console.log(jsonData);
                        KD.get("../api/paths/pathsearch", jsonData, function (data) {
                            if (data.error.code != 0) {
                                KD.showWarning(data.error.message);
                                return;
                            }
                            endFlag = true;
                            console.log(data);
                            if (data.data.vals.length > 0) {
                                var result = KD.Json.binderJson(data.data.vals, temp_Container);
                                $("#temp_Container").html(result).parent().show();
                            } else {
                                $("#temp_Container").html("");
                            }
                            if (data.data.error) {
                                KD.showWarning(data.data.error);
                            }

                            bindPath();
                        });
                    }
                }

                function bindPath() {

                    $(".path").html("");

                    var next = $("#end").val();
                    if (KD.isNotNull(next) && array.indexOf(next) == -1) {
                        array.push(next);
                    }

                    var str = '<li><a href="#" class="pathNode" data-index="0">' + $("#startWord").val() + '</a></li>';

                    if (array.length < 1)return;

                    for (i = 0; i < array.length; i++) {

                        str += '<li ><a href="#" class="pathNode" data-index="' + (i + 1) + '">' + array[i] + '</a></li>';
                    }

                    $(".path").html(str).parent().show();

                    $(".pathNode").click(function () {

                        var index = parseInt($(this).attr("data-index"));
                        var curVal = $(this).text();
                        if (index == 0) {
                            $("#start").val(curVal);
                            $("#end").val("");
                            array = [];
                            $("#temp_Container").html("");
                            bindPath();
                            endFlag = false;
                            start = "";
                            end = "";
                            return;
                        }

                        var prevVal = $(this).parent().prev().text();
                        $("#start").val(prevVal);
                        $("#end").val(curVal);

                        var newArray = [];
                        for (i = 0; i <= index - 1; i++) {
                            newArray.push(array[i]);
                        }
                        array = newArray;
                        getResult();
                    });
                }

            });

        </script>

    </jsp:body>
</t:template>

