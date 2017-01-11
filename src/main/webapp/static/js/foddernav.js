/*头部菜单栏*/
$(".header-right>li").on("click",function(e){
    if($(this).hasClass("head-userinfo")){//用户信息
        e ? e.stopPropagation() : event.cancelBubble = true;
        if($(".pnl-user").css("display")=="none"){
            $(this).find(".company").css("background-image","url(img/up-arrow.png)");
            $(".pnl-user").css("display","block");
        }else{
            $(this).find(".company").css("background-image","url(img/down-arrow.png)");
            $(".pnl-user").css("display","none");
        }
        
    }
});
$(".user-set").on("click",function(){
    var company=$(".head-userinfo .company").text();
    var content = $("<div class='pnl-info'><div class='set-info company-name'>公司名称：<input type='text' class='txt-companyname' value='"+company+"' disabled/></div>"+
        "<div class='set-info set-pwd'>修改密码：<input type='password' class='txt-oldpwd' placeholder='请输入原密码'/><div class='info-erro' style='display:none'>原密码输入错误<div class='del'></div></div></div>"+
        "<div class='set-info new-pwd'><input type='password' class='txt-newpwd' placeholder='请输入新密码'/><div class='info-erro' style='display:none'>密码格式错误<div class='del'></div></div></div>"+
        "<div class='set-info conf-pwd'>确认密码：<input type='password' class='txt-confpwd' placeholder='再次输入新密码'/><div class='info-erro' style='display:none'>两次密码输入不一致<div class='del'></div></div></div></div>");
    var pop = new Pop({
        width:"396px",
        header:"账号设置",
        content:content,
        buttons:[{
            type:"popCancle",
            text:"取消"
        },{
            type:"popOk",
            text:"确定",
            callback:function(){
                var companyName=$.trim($('.txt-companyname').val());
                var oldPwd=$.trim($('.txt-oldpwd').val());
                var newPwd=$.trim($('.txt-newpwd').val());
                var confPwd=$.trim($('.txt-confpwd').val());
                if(oldPwd==""){
                    $('.txt-oldpwd').addClass("error");
                    return;
                }else{
                    $('.txt-oldpwd').removeClass("error");
                }
                if(newPwd==""){
                    $('.txt-newpwd').addClass("error");
                    return;
                }else{
                    $('.txt-newpwd').removeClass("error");
                }
                if(confPwd==""){
                    $('.txt-confpwd').addClass("error");
                    return;
                }else{
                    $('.txt-confpwd').removeClass("error");
                }
                if(newPwd!=""&&confPwd!=""){
                    if(newPwd!=confPwd){
                        $('.conf-pwd').find(".info-erro").css("display","block");
                        return;
                    }else{
                        $('.conf-pwd').find(".info-erro").css("display","none");
                    }  
                }
                var data={
                        "password":newPwd,
                        "passwordConfirm":confPwd,
                        "passwordOrig":oldPwd
                };
                $.ajax({
                    type:"post",
                    url: dataUrl.util.updatePwd,
                    data:data,
                    success: function(returnData) {
                        if(returnData.error.code==0){
                            window.location.href="user/login";
                        }else{
                            if(returnData.error.message.indexOf("密码格式不正确")!=-1){
                                $('.new-pwd').find(".info-erro").css("display","block");
                            }else{
                                $('.new-pwd').find(".info-erro").css("display","none");
                            }
                            if(returnData.error.message.indexOf("原始密码不正确，请重新输入！")!=-1){
                                $('.set-pwd').find(".info-erro").css("display","block");
                            }else{
                                $('.set-pwd').find(".info-erro").css("display","none");
                            }
                        }
                    },
                    error: function() {
                        console.log('修改密码失败');
                    }
                });
            }
        }]
    })
    $(".popContent").css({"padding-top":"9px","padding-bottom":"6px"});
})

//点击任意地方关闭弹窗
$(document).on('click',function(e){
    $(".pnl-user").css("display","none");
    $(".userName").find(".company").css("background-image","url(img/down-arrow.png)");
});

//点击logo返回首页
$(".header-logo").on("click",function(){
    var url=window.location.href;
    if(url.indexOf("hotsystem")==-1){
        window.location.href="hotsystem";
    }
});

//获取当前用户信息
$.ajax({
    type: "get",
    url: dataUrl.util.getUserInfo,
    success: function(returnData) {
        if(returnData.error.code == 0&&returnData.data){
            res=returnData.data;
            var userName=res.userName?res.userName:"";
            var company=res.company?res.company:"";
            $(".head-userinfo .name").text(userName);
            $(".head-userinfo .company").text(company);
            $(".pnl-user .name").text(userName);
            $(".pnl-user .company").text(company);
        }
    }
});