<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
    ul {
        float:left;
        clear:right;
        padding:5px;
    }
    li {
        float:left;
        clear:left;
    }


</style>
<script type="text/javascript">

  function searchZhihuTopics() {
    var keyword = $.trim( $("#keyword").val() );
    var url = "https://www.zhihu.com/search?type=topic&q=%E5%9F%8E%E5%B8%82%E8%A7%84%E5%88%92";
    $.ajax({
          url: url,
          dataType: 'html',
          success: function (data) {
            $('#topicHtml').html( data );
          }
    })
  }


</script>
<t:template>
  <jsp:attribute name="script"></jsp:attribute>
  <jsp:body>

      <form action="${pageContext.servletContext.contextPath}/admin/zhihupath/result">

          <input type="text" name="keyword" placeholder="请输入搜索关键字" value="${keyword}">

          <input type="text" name="title" placeholder="请输入topic的标题" value="${title}">

                <button type="submit">搜索</button>


      </form>

      <br/>

      <div class="table-responsive">
          <table class="table table-striped">
              <thead>
                  <tr>
                      <th>关键字</th>
                      <th>知乎topics</th>
                      <th>[<c:out value="${zhiHuFirstTopic}" default="知乎topics"/>]的子topics</th>
                      <th>话题keywords</th>
                      <th>相同keywords</th>
                      <th>相似keywords</th>
                      <th>目标话题</th>
                  </tr>
              </thead>
              <tr>
                  <td>${keyword}</td>
                  <td><c:out value="${zhiHuTopicsList}" default="无"/></td>
                  <td><c:out value="${childrenTopicNames}" default="无"/></td>
                  <td><c:out value="${topicKeywords}" default="无"/></td>
                  <td><c:out value="${contentRepeatedWordList}" default="无"/></td>
                  <td><c:out value="${contentSimilarWordList}" default="无"/></td>
                  <td>${topicTitle}</td>
              </tr>
              <tr>
                  <td>--</td>
                  <td>--</td>
                  <td>--</td>
                  <td><c:out value="${titleWordsList}" default="无"/></td>
                  <td><c:out value="${titleRepeatedWordList}" default="无"/></td>
                  <td><c:out value="${titleSimilarWordList}" default="无"/></td>
                  <td>--</td>
              </tr>
              <tr>
                  <td>--</td>
                  <td>--</td>
                  <td>--</td>
                  <td><c:out value="${neighborWordsList}" default="无"/></td>
                  <td><c:out value="${neighborRepeatedWordList}" default="无"/></td>
                  <td><c:out value="${neighborSimilarWordList}" default="无"/></td>
                  <td>--</td>
              </tr>
              </tbody>
          </table>
      </div>

  </jsp:body>
</t:template>

