<%@page language="java" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp" />
<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="css/warnAlert.css">
<title>123</title>
</head>
<body>
    <jsp:include page="nav.jsp" />
    <jsp:include page="warnAlert.jsp" />
    <form action="" id="loginForm">
        <input id="cardid" type="text" value="" /><span id="msg"></span></p> <br> <input
            id="follow" type="button" value="关注" /> <input id="cancelButton"
            type="button" value="取消" />
    </form>
    <div id="output"></div>
    <jsp:include page="foot.jsp" />
    <script>
	    $(function(){
	        var options = { 
	            beforeSubmit:  showRequest,  //提交前处理
	            success:       showResponse,  //处理完成
	            resetForm: true, 
	            dataType:  'json' 
	        }; 
	     
	        $('#loginForm').submit(function() { 
	            $(this).ajaxSubmit("api/card/"+$('#cardid').val()+"/follow"); 
	        }); 
	    });
	
	    function showRequest(formData, jqForm, options) { 
	        var uname = $("#cardid").val();
	        if(cardid==""){
	            $("#msg").html("姓名不能为空！");
	            return false;
	        }
	        
	        return true; 
	    } 
	     
	    function showResponse(responseText, statusText)  { 
	        $("#msg").html('提交成功');
	        //var sex = responseText.sex==1?"男":"女";
	        $("#output").html("res："+responseText);
	    } 
	</script>
    </script>
</body>
</html>