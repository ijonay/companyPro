<%--
  Created by IntelliJ IDEA.
  User: xyzhuzhou
  Date: 2016/8/5 0005
  Time: 15:57:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

foot1

<% String path = request.getContextPath(); %>

<script type="text/javascript">

    function bindUrl() {
        var contextPath = "<%=path%>/";
        $("a.hasUrl").each(function () {
            var href = $(this).attr("href");
            $(this).attr("href", contextPath + href);
        })

    }
    function getPath() {
        return "<%=path%>/";
    }

    $(bindUrl);

</script>
