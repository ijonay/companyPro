<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/admin"
        %>
<script src="/static/lib/jquery.form.js"></script>
<script>

    function submitForm(){

      $("form#data").submit(function(){

        var formData = new FormData($(this)[0]);

        $.ajax({
          url: window.location.pathname,
          type: 'POST',
          data: formData,
          async: false,
          success: function (data) {
            alert(data)
          },
          cache: false,
          contentType: false,
          processData: false
        });

        return false;
      });
    }

</script>
<t:template>
  <jsp:attribute name="script"></jsp:attribute>
  <jsp:body>
      <div class="container">

        <form id="topicForm" action="/admin/submittopic" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">

          <div class="form-group">
            <label for="topicTitle" class="col-sm-2 control-label">热点名称</label>
            <div class="col-sm-8">
              <input type="text" name="title" class="form-control" id="topicTitle" >
            </div>
          </div>

          <div class="form-group">
            <label for="prevailingTrend" class="col-sm-2 control-label">热点热度</label>
            <div class="col-sm-8">
              <input type="text" name="prevailingTrend" class="form-control" id="prevailingTrend" >
            </div>
          </div>

          <div class="form-group">
            <label for="topicEventClass" class="col-sm-2 control-label">热点标签</label>
            <div class="col-sm-8">
              <input type="text" name="eventClass" class="form-control" id="topicEventClass" >
            </div>
          </div>

          <div class="form-group">
            <label for="topicIntroducation" class="col-sm-2 control-label">热点描述</label>
            <div class="col-sm-8">
              <textarea class="form-control" name="introducation" id="topicIntroducation" rows="3"></textarea>
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
              <input type="text" name="logoImgUrl" class="form-control" id="topicLogoImgUrl" >
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
              <input type="text" name="topicUrl" class="form-control" id="topicSourceWeibo" >
            </div>
          </div>

          <div class="form-group">
            <label for="topicSourceWeChat" class="col-sm-2 control-label">微信</label>
            <div class="col-sm-8">
              <input type="text" name="wechatUrl" class="form-control" id="topicSourceWeChat" >
            </div>
          </div>

          <div class="form-group">
            <label for="topicSourceZhihu" class="col-sm-2 control-label">知乎</label>
            <div class="col-sm-8">
              <input type="text" name="zhihuUrl" class="form-control" id="topicSourceZhihu" >
            </div>
          </div>

          <div class="form-group">
            <label for="topicSourceBaidu" class="col-sm-2 control-label">百度</label>
            <div class="col-sm-8">
              <input type="text" name="baiduUrl" class="form-control" id="topicSourceBaidu" >
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

