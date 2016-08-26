<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'testsocket.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="node_modules/jquery/dist/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		if (window.WebSocket != undefined) {
			var connection = new WebSocket(
					'ws://localhost:8088/zhicang/api/websocket');
			connection.onopen = wsOpen;
			connection.onmessage = wsMessage;

		}

		function wsMessage(event) {
			console.log(event.data);
		}
		function wsOpen(event) {
			connection.send("xixiix")
			console.log('Connected to: ' + event.currentTarget.URL);
		}
	})
</script>
</head>

<body>
	This is my JSP page.
	<br>
</body>
</html>
