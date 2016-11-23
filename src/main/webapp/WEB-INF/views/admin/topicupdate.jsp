<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/admin"
%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script>
    function closePage() {
        window.close();
    }

    if (window.location.href.indexOf("success=false") > 0) {
        alert("操作失败，请重试!");
    }

</script>
<t:template>
    <jsp:attribute name="script"></jsp:attribute>
    <jsp:body>
        <div class="container">

            <form id="topicForm" action="<c:out value="${pageContext.request.contextPath}"/>/admin/updatetopic" class="form-horizontal"
                  role="form"
                  enctype="multipart/form-data" method="post">

                <input type="hidden" name="id" value="${topic.id}"/>

                <div class="form-group">
                    <label for="topicTitle" class="col-sm-2 control-label">热点名称</label>
                    <div class="col-sm-8">
                        <input type="text" name="manualTitle" class="form-control" id="topicTitle"
                               value="${topic.manualTitle}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="prevailingTrend" class="col-sm-2 control-label">热点热度</label>
                    <div class="col-sm-8">
                        <input type="text" name="manualPrevailingTrend" class="form-control" id="prevailingTrend"
                               value="${topic.manualPrevailingTrend}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="topicEventClass" class="col-sm-2 control-label">热点标签</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="topicEventClass"
                               value="${topic.manualEventClass}" name="manualEventClass">
                    </div>
                </div>

                <div class="form-group">
                    <label for="topicIntroducation" class="col-sm-2 control-label">热点描述</label>
                    <div class="col-sm-8">
              <textarea class="form-control" name="manualIntroduction" id="topicIntroducation" rows="3">
                      ${topic.manualIntroduction}
              </textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label for="topicLogoImgUrlLocal" class="col-sm-2 control-label">本地照片</label>
                    <div class="col-sm-8">
                        <input type="file" id="topicLogoImgUrlLocal" name="file">
                    </div>
                </div>

                <div class="form-group">
                    <label for="topicLogoImgUrl" class="col-sm-2 control-label">主照片URL</label>
                    <div class="col-sm-8">
                        <input type="text" name="manualLogoImgUrl" value="${topic.manualLogoImgUrl}"
                               class="form-control" id="topicLogoImgUrl">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">来源</label>
                    <div class="col-sm-8">
                        <label class="col-sm-4 control-label">(以下链接至少填写1个)</label>
                    </div>
                </div>

                <div class="form-group">
                    <label for="topicSourceWeibo" class="col-sm-2 control-label">微博</label>
                    <div class="col-sm-8">
                        <input type="text" name="manualTopicUrl" class="form-control" id="topicSourceWeibo"
                               value="${topic.manualTopicUrl}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="topicSourceWeChat" class="col-sm-2 control-label">微信</label>
                    <div class="col-sm-8">
                        <input type="text" name="manualWechatUrl" class="form-control" id="topicSourceWeChat"
                               value="${topic.manualWechatUrl}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="topicSourceZhihu" class="col-sm-2 control-label">知乎</label>
                    <div class="col-sm-8">
                        <input type="text" name="manualZhihuUrl" class="form-control" id="topicSourceZhihu"
                               value="${topic.manualZhihuUrl}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="topicSourceBaidu" class="col-sm-2 control-label">百度</label>
                    <div class="col-sm-8">
                        <input type="text" name="manualBaiduUrl" class="form-control" id="topicSourceBaidu"
                               value="${topic.manualBaiduUrl}">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-8">
                        <button type="submit" class="btn btn-default">提交</button>
                    </div>
                </div>


            </form>


        </div>
    </jsp:body>
</t:template>

