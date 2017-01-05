<%@ page contentType="text/html;charset=UTF-8"  language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/admin"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

</script>
<t:template>
    <jsp:attribute name="script"></jsp:attribute>
    <jsp:body>

        <form class="form-inline" role="form"
              action="<c:out value="${pageContext.request.contextPath}"/>/admin/bosonNlpKeyWord" method="post">
            <div class="form-group">
                <input type="text" class="form-control" value="${bosonkeyword}" name="bosonkeyword" placeholder="请输入联想语义关键词">
            </div>
            <button type="submit" class="btn btn-default">搜索</button>
        </form>

        <br/>
       <%--  <c:if test="${empty param.suggestKeys}">

           <label id="weibolabel" Style="color:red;font-size:20px;" >很抱歉，导入失败</label>
        </c:if> --%>
        <c:if test="${not empty suggestKeys}">
<style>
textarea
{
width:100%;
height:50%;
}
</style>
          <textarea id="txtcontent" name="txtcontent"  value="${suggestKeys} " ><c:out value="${suggestKeys}"></c:out></textarea> 
          
        </c:if>


        <!--
        <div class="bs-example">
        <button type="button" class="btn btn-default">撤销全部修改</button>
        <button type="button" class="btn btn-default">撤销全部删除</button>
        </div>
        -->

    </jsp:body>
</t:template>

