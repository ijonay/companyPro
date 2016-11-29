<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        %>
        <base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h2>用户数据 </h2>
<ul>
<c:forEach items="${users}"
var="user"
begin="0"
end="9"
step="1"
varStatus="var">
  <li >${user.id}</li>
  <li >${user.name}</li>
  <li >${user.password}</li>
  <br/>
  </c:forEach>
</ul>
</body>
</html>
