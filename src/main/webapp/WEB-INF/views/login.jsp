<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
    %>
    <%@ include file="taglibs.jsp" %>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录页面</title>
    <!--[if lt IE 9]>
    <script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/login.css"/>
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
<form id="loginForm" action="user/login" method="post"
      class="login-form" autocomplete="off">
    <ul class="list-group">
        <li class="list-group-item">
            <input id="username" type="text" name="username" placeholder="用户名"/></li>
        <li class="list-group-item">
            <input type='password' name="password" placeholder="密码"/></li>
        <!--<li class="list-group-item"><input type='text' name="checkCode" placeholder="验证码" /></li>-->
    </ul>
    <input id="loginBtn" type="submit" class="btn-login" value="登录Heptax">
    <a href="#" class="forget">忘记密码</a>
</form>
<div class="login-footer">Copyright © 北京知藏云道科技有限公司 Heptax Product
    Beijing China
</div>
<script src="lib/jquery-1.12.0.min.js"></script>
<script>
    $('#username').focus();
    function loginFailed(data) {
        showLoginMessage(data);
    }

    function loginWrongParam(data) {
        console.log(data);
        var i = 0, len = data.length, msg = '';

        for (; i < len; i++) {
            msg += data[i].message + '<br/>';
        }

        showLoginMessage(msg);
    }

    function loginError(data) {
        showLoginMessage("登录失败！");
    }

    // TODO:显示错误临时方案
    function showLoginMessage(message) {
        var $error = $('#error');

        if ($error) {
            $error.remove();
        }

        $('#loginBtn').before(
                '<p id="error" style="color:red">' + message + '</p>');

        resetLoginButton();
    }

    function resetLoginButton() {
        $('#loginBtn').attr('disabled', false).val('登录Heptax');
    }

    function setLoginButton() {
        $('#loginBtn').attr('disabled', 'disabled').val('正在登录...');
    }

    $('#loginBtn').click(function (e) {
        window.localStorage.clear();
        e.preventDefault();
        setLoginButton();
        $.ajax({
            url: '${ctx}/api/account/login',
            type: 'POST',
            data: $('#loginForm').serialize(),
            success: function (res) {
                var returnUrl = window.location.search;
                if (res.error.code === 0) {
                    if (returnUrl.match("returnUrl")) {
                        returnUrl = returnUrl.split("returnUrl=");
                        console.log(returnUrl[1]);
                        window.location = returnUrl[1];
                    } else {
                        location.href = "${ctx}/home";
                    }
                } else if (res.error.code === 4001) {
                    // 参数错误
                    loginWrongParam(res.data);
                } else {
                    // 其它错误
                    loginFailed(res.data);
                }
            },
            error: loginError
        });
    });
</script>
<SCRIPT>
    if (!$.support.leadingWhitespace) {
        alert("浏览器版本太低，请下载chrome或者IE10以上版本浏览器")
    }


</SCRIPT>
</body>
</html>