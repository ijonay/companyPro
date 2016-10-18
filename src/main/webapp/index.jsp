<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<!--[if lt IE 9]>
    	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
		<![endif]-->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/login.css" />
</head>
<body>
	<div class="login-background">
		<img src="img/loginbg.png">
	</div>
	<div class="about">
		<img src="img/about.png"><a href="#">关于Heptax</a>
	</div>
	<div class="login-logo">
		<img src="img/vi.png">
	</div>
	<form action="user/login" method="post" class="login-form">
		<div class="list-group">
  			<li class="list-group-item"><input type="text" name="username" placeholder="用户名" /></li>
  			<li class="list-group-item"><input type='password' name="password" placeholder="密码" /></li>
  			<!--<li class="list-group-item"><input type='text' name="checkCode" placeholder="验证码" /></li>-->
  			
		</div>
		${loginerror}<br /> <input type="submit" class="btn-login"
			value="登录Heptax"> <a href="#" class="forget">忘记密码</a>
	</form>
	<div class="login-footer">Copyright © 北京知藏云道科技有限公司 Heptax Product
		Beijing China</div>
</body>
</html>