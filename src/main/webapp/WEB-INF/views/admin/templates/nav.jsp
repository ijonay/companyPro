<%--
  Created by IntelliJ IDEA.
  User: xyzhuzhou
  Date: 2016/8/5 0005
  Time: 15:57:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath(); %>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluider">
        <div class="navbar-header">
            <button class="navbar-toggle collapsed"><span class="sr-only">后台管理</span><span
                    class="icon-bar"></span><span
                    class="icon-bar"></span><span class="icon-bar"></span></button>
            <a href="index" class="navbar-brand">后台管理 </a></div>
        <div id="navbar" class="navbar-collapse collapse" style="background: #222;">
            <ul class="nav navbar-nav navbar-right" style="margin-right: 20px;">
                <%--<li class="active"><a href="#">Home </a></li>--%>
                <%--<li><a href="">Item 1</a></li>--%>
                <li><a href="/" target="_blank">Home</a></li>
            </ul>
        </div>
    </div>
</nav>
