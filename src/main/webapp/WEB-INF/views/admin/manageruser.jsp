<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/admin"
%>
<t:template>
    <jsp:attribute name="script">
         <script src="../js/admin/user.js"></script>
    </jsp:attribute>
    <jsp:body>
        <div class="">
            <div class="container">
                <div class="row">
                    <div class="col-md-1">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default">
                                添加用户
                            </button>
                        </div>
                    </div>
                </div>
                <br/>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>公司名称</th>
                        <th>账户名称</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="temp_Container">
                    <tr>
                        <td>{index}</td>
                        <td>{company}</td>
                        <td>{username}</td>
                        <td>删除|禁用</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <script type="text/javascript">

            $(function () {
                function initData() {
                    User.getUsers(function (data) {
                        console.log(data);
                        var result = KD.Json.binderJson(data.data, "temp_Container");
                        console.log(result);
                        $("#temp_Container").html(result);

                    });
                }

                initData();

            });

        </script>
    </jsp:body>
</t:template>

