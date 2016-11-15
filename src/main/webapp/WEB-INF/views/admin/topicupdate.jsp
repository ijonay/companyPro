<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/admin"
        %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script>
  function closePage(){
    window.close();
  }

  if(window.location.href.indexOf("success=false") > 0){
    alert("操作失败，请重试!");
  }

</script>
<t:template>
  <jsp:attribute name="script"></jsp:attribute>
  <jsp:body>
    <div class="container">

      <form id="topicForm" action="/admin/submittopic" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">

        <input type="hidden" name="id" value="${topic.id}"/>
        <input type="hidden" name="action" value="update"/>

        <div class="form-group">
          <label for="topicTitle" class="col-sm-2 control-label">热点名称</label>
          <div class="col-sm-10">
            <input type="text" name="title" class="form-control" id="topicTitle" value="${topic.title}"  >
          </div>
        </div>

        <div class="form-group">
          <label for="prevailingTrend" class="col-sm-2 control-label">热点热度</label>
          <div class="col-sm-10">
            <input type="text" name="prevailingTrend" class="form-control" id="prevailingTrend"
                   value="${topic.prevailingTrend}"  >
          </div>
        </div>

        <div class="form-group">
          <label for="topicEventClass" class="col-sm-2 control-label">热点标签</label>
          <div class="col-sm-3">
            <input type="text"  class="form-control" id="topicEventClass"
                   value="${topic.eventClass}"  >
          </div>
        </div>

        <div class="form-group">
          <label for="topicIntroducation" class="col-sm-2 control-label">热点描述</label>
          <div class="col-sm-10">
              <textarea class="form-control" name="introducation" id="topicIntroducation" rows="3"  >
                  ${topic.introduction}
              </textarea>
          </div>
        </div>

        <div class="form-group">
          <label for="topicLogoImgUrlLocal" class="col-sm-2 control-label">主照片</label>
          <div class="col-sm-10">
            <input type="file" id="topicLogoImgUrlLocal" name="file">
          </div>
        </div>


        <div class="form-group">
          <label class="col-sm-2 control-label">来源</label>
          <div class="col-sm-10">
            <label class="col-sm-4 control-label">(以下链接至少填写1个)</label>
          </div>
        </div>

        <div class="form-group">
          <label for="topicSourceWeibo" class="col-sm-2 control-label">微博</label>
          <div class="col-sm-10">
            <input type="text" name="topicUrl" class="form-control" id="topicSourceWeibo" value="${topic.topicUrl}"  >
          </div>
        </div>

        <div class="form-group">
          <label for="topicSourceWeChat" class="col-sm-2 control-label">微信</label>
          <div class="col-sm-10">
            <input type="text" name="wechatUrl" class="form-control" id="topicSourceWeChat" value="${topic.wechatUrl}"  >
          </div>
        </div>

        <div class="form-group">
          <label for="topicSourceZhihu" class="col-sm-2 control-label">知乎</label>
          <div class="col-sm-10">
            <input type="text" name="zhihuUrl" class="form-control" id="topicSourceZhihu" value="${topic.zhihuUrl}"  >
          </div>
        </div>

        <div class="form-group">
          <label for="topicSourceBaidu" class="col-sm-2 control-label">百度</label>
          <div class="col-sm-10">
            <input type="text" name="baiduUrl" class="form-control" id="topicSourceBaidu" value="${topic.baiduUrl}"  >
          </div>
        </div>

        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
          </div>
        </div>



      </form>


    </div>
  </jsp:body>
</t:template>

