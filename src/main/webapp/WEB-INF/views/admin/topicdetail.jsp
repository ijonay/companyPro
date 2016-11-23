<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/admin"
        %>

<script>
  function closePage(){
    window.close();
  }
  if(window.location.href.indexOf("success=true") > 0){
    alert("操作成功!");
  }
</script>
<t:template>
  <jsp:attribute name="script"></jsp:attribute>
  <jsp:body>
      <div class="container">

        <form id="topicForm" action="/api/topic/add" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">

          <div class="form-group">
            <label for="topicTitle" class="col-sm-2 control-label">热点名称</label>
            <div class="col-sm-8">
              <input type="text" name="title" class="form-control" id="topicTitle" value="${topic.manualTitle}" readonly>
            </div>
          </div>

          <div class="form-group">
            <label for="prevailingTrend" class="col-sm-2 control-label">热点热度</label>
            <div class="col-sm-8">
              <input type="text" name="prevailingTrend" class="form-control" id="prevailingTrend"
                     value="${topic.manualPrevailingTrend}" readonly>
            </div>
          </div>

          <div class="form-group">
            <label for="topicEventClass" class="col-sm-2 control-label">热点标签</label>
            <div class="col-sm-8">
              <input type="text"  class="form-control" id="topicEventClass"
                     value="${topic.manualEventClass}" readonly>
            </div>
          </div>

          <div class="form-group">
            <label for="topicIntroduction" class="col-sm-2 control-label">热点描述</label>
            <div class="col-sm-8">
              <textarea class="form-control" name="introduction" id="topicIntroduction" rows="3" readonly>
                  ${topic.manualIntroduction}
              </textarea>
            </div>
          </div>

          <div class="form-group">
            <label for="topicLogoImgUrlLocal" class="col-sm-2 control-label">本地照片</label>
            <div class="col-sm-8">
              <img src="${topic.logoImgUrlLocal}" id="topicLogoImgUrlLocal">
            </div>
          </div>

          <div class="form-group">
            <label for="topicLogoImgUrl" class="col-sm-2 control-label">主照片URL</label>
            <div class="col-sm-8">
              <img src="${topic.manualLogoImgUrl}" id="topicLogoImgUrl">
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-2 control-label">来源</label>
            <div class="col-sm-8">
            </div>
          </div>

          <div class="form-group">
            <label for="topicSourceWeibo" class="col-sm-2 control-label">微博</label>
            <div class="col-sm-8">
              <input type="text" name="topicUrl" class="form-control" id="topicSourceWeibo" value="${topic.manualTopicUrl}" readonly>
            </div>
          </div>

          <div class="form-group">
            <label for="topicSourceWeChat" class="col-sm-2 control-label">微信</label>
            <div class="col-sm-8">
              <input type="text" name="wechatUrl" class="form-control" id="topicSourceWeChat" value="${topic.manualWechatUrl}" readonly>
            </div>
          </div>

          <div class="form-group">
            <label for="topicSourceZhihu" class="col-sm-2 control-label">知乎</label>
            <div class="col-sm-8">
              <input type="text" name="zhihuUrl" class="form-control" id="topicSourceZhihu" value="${topic.manualZhihuUrl}" readonly>
            </div>
          </div>

          <div class="form-group">
            <label for="topicSourceBaidu" class="col-sm-2 control-label">百度</label>
            <div class="col-sm-8">
              <input type="text" name="baiduUrl" class="form-control" id="topicSourceBaidu" value="${topic.manualBaiduUrl}" readonly>
            </div>
          </div>

          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-8">
              <button type="button" class="btn btn-default" onclick="closePage();">关闭</button>
            </div>
          </div>



    </form>


      </div>
  </jsp:body>
</t:template>

