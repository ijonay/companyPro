<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!--[if lt IE 9]>
    <script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->


<link rel="stylesheet" href="css/bootstrap.min.css"/>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/nav.css">