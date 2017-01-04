<%@ tag language="java" pageEncoding="utf-8" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="script" fragment="true" %>
<%@attribute name="style" fragment="true" %>
<%@attribute name="footScript" fragment="true" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>
        知几后台管理
        <jsp:invoke fragment="title"/>
    </title>
    <jsp:include page="../../views/admin/templates/resource.jsp"></jsp:include>
    <jsp:invoke fragment="script"/>
    <style type="text/css">

        /* Move down content because we have a fixed navbar that is 50px tall */
        body {
            padding-top: 50px;
        }

        /*
     * Global add-ons
     */

        .sub-header {
            padding-bottom: 10px;
            border-bottom: 1px solid #eee;
        }

        /*
         * Top navigation
         * Hide default border to remove 1px line.
         */
        .navbar-fixed-top {
            border: 0;
        }

        /*
         * Sidebar
         */

        /* Hide for mobile, show later */
        .sidebar {
            display: none;
        }

        @media (min-width: 768px) {
            .sidebar {
                position: fixed;
                top: 51px;
                bottom: 0;
                left: 0;
                z-index: 1000;
                display: block;
                padding: 20px;
                overflow-x: hidden;
                overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
                background-color: #f5f5f5;
                border-right: 1px solid #eee;
            }
        }

        /* Sidebar navigation */
        .nav-sidebar {
            margin-right: -21px; /* 20px padding + 1px border */
            margin-bottom: 20px;
            margin-left: -20px;
        }

        .nav-sidebar > li > a {
            padding-right: 20px;
            padding-left: 20px;
        }

        .nav-sidebar > .active > a,
        .nav-sidebar > .active > a:hover,
        .nav-sidebar > .active > a:focus {
            color: #fff;
            background-color: #428bca;
        }

        /*
         * Main content
         */

        .main {
            padding: 20px;
        }

        @media (min-width: 768px) {
            .main {
                padding-right: 40px;
                padding-left: 40px;
            }
        }

        .main .page-header {
            margin-top: 0;
        }

        /*
         * Placeholder dashboard ideas
         */

        .placeholders {
            margin-bottom: 30px;
            text-align: center;
        }

        .placeholders h4 {
            margin-bottom: 0;
        }

        .placeholder {
            margin-bottom: 20px;
        }

        .placeholder img {
            display: inline-block;
            border-radius: 50%;
        }
    </style>
    <jsp:invoke fragment="style"/>

</head>
<body>
<jsp:include page="../../views/admin/templates/header.jsp"></jsp:include>
<jsp:include page="../../views/admin/templates/nav.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../../views/admin/templates/left.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <jsp:doBody/>

        </div>
    </div>
</div>
<jsp:include page="../../views/admin/templates/foot.jsp"></jsp:include>
<jsp:invoke fragment="footScript"/>
</body>
</html>
