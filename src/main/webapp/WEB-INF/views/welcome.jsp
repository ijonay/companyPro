<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        %>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<li><a href="./getdata/?data=中国">获取自定义数据</a></li>
	<li><a href="./user/getusers">获取全部用户</a></li>
	<li><a href="./user/getuser?id=2">获取用户2</a></li>
	<li><a href="./user/countuser">获取用户数</a></li>
	<li><a href="pbd/user/login/home.html">index</a></li>
	<!-- 上传文件 表单 post提交 -->
	<form action="upload/upfile" method="post" enctype="multipart/form-data">
		<input type='file' name="excelFile" /> <input type='submit' value="上传" />
	</form>
	<br>
	<form action="user/loginauthc"
		method="post">
		<input type="text" name="username" /> <input type='text'
			name="password" /> <input type="submit" value="登陆">
	</form>
	<a href="addtable">222</a>
	<h2>${registInfo}</h2>
	<h2>${loginerror}</h2>
	<br>
	<a href="logout">退出</a>
	<br>
	<form action="user/regist"
		method="post">
		<input type="text" name="username" /> <input type='text'
			name="password" /> <input type="submit" value="注册">
	</form>
</body>
</html>