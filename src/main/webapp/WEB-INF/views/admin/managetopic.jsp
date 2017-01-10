<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/admin"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

         function updateTopicStatus(id) {
        var status = $.trim($("#deleteLink_" + id).html());
        var url = "<%=request.getContextPath()%>";
        if ($.trim(status) == '取消删除') {
            url += "/api/topic/active"
        } else {
            url += "/api/topic/inactive";
        }
        url += "?id=" + id;
        $.ajax({
            url: url,
            dataType: 'json',
            success: function (data) {
                if (data.error.code == '0') {
                    alert('操作成功.');
                    if (status == '取消删除') {
                        $("#deleteLink_" + id).html("删除");
                    } else {
                        $("#deleteLink_" + id).html("取消删除");
                    }

                } else {
                    alert('操作失败.');
                }
            }
        })
    }

    function updateManualStatus(id) {
        var status = $.trim($("#manual_" + id).html());
        var url = "<%=request.getContextPath()%>";
        if (status == '应用干预') {
            url += "/api/topic/applymanual"
        } else {
            url += "/api/topic/cancelmanual";
        }
        url += "?id=" + id;
        $.ajax({
            url: url,
            dataType: 'json',
            success: function (data) {
                if (data.error.code == '0') {
                    alert('操作成功.');
                    if (status == '应用干预') {
                        $("#manual_" + id).html("取消干预");
                    } else {
                        $("#manual_" + id).html("应用干预");
                    }

                } else {
                    alert('操作失败.');
                }
            }
        })
    } 

</script>
<t:template>
    <jsp:attribute name="script"></jsp:attribute>
    <jsp:body>

        <form class="form-inline" role="form"
              action="<c:out value="${pageContext.request.contextPath}"/>/admin/managetopic">
            <div class="form-group">
                <input type="text" class="form-control" value="${keyword}" name="keyword" placeholder="请输入Title搜索关键字">
            </div>
             <div class="form-group">
                <input type="text" class="form-control" value="${keyword_title}" name="keyword_title" placeholder="请输入ManualTitle关键字">
            </div>
            <button type="submit" class="btn btn-default">搜索</button>
        </form>

        <br/>

        <div class="bs-example">

            <button type="button" onclick="window.open('<c:out value="${pageContext.request.contextPath}"/>/admin/toaddtopic','_blank')"

                    class="btn btn-default">添加热点
            </button>

        </div>

        <c:if test="${not empty keyword}">

            <c:choose>
                <c:when test="${topicModelList.size() > 0}">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>热点名称</th>
                            <th>热度</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="topic" items="${topicModelList}">
                            <tr>
                                <td>${topic.title}</td>
                                <td>${topic.prevailingTrend}</td>
                                <td>
                                    <a href="/admin/topicdetail/${topic.id}" target="_blank">详情</a>

                                    <a href="/admin/toupdatetopic/${topic.id}" target="_blank">修改</a>
                                    <a href="javascript:void(0)" onclick="updateTopicStatus('${topic.id}')"
                                       id="deleteLink_${topic.id}">
                                        <c:if test="${not empty topic.isActive && topic.isActive == 1}">
                                            删除
                                        </c:if>
                                        <c:if test="${not empty topic.isActive && topic.isActive == 0}">
                                            取消删除
                                        </c:if>
                                    </a>
                                    <a href="javascript:void(0)" onclick="updateManualStatus('${topic.id}')"
                                       id="manual_${topic.id}">
                                        <c:if test="${not empty topic.manualIsApplied && topic.manualIsApplied == 1}">
                                            取消干预
                                        </c:if>
                                        <c:if test="${empty topic.manualIsApplied || topic.manualIsApplied == 0}">
                                            应用干预
                                        </c:if>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
            </c:choose>
        </c:if>


        <!--
        <div class="bs-example">
        <button type="button" class="btn btn-default">撤销全部修改</button>
        <button type="button" class="btn btn-default">撤销全部删除</button>
        </div>
        -->

    </jsp:body>
</t:template>

