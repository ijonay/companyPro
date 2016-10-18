<%@page language="java" contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="head.jsp"/>
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/warnAlert.css">
    <title>知藏首页</title>
</head>
<body>
<jsp:include page="nav.jsp"/>
<jsp:include page="warnAlert.jsp"/>
<!--主体section--------------------------------------------------------------------------------->
<div>
    <div class="section-table">
        <span>一览表</span>
        <input type="button" value="新建卡片" class="newcard" id="add_select" data-toggle="modal"
               data-target="#newCardModal">
        <input type="text" class="tab-search" placeholder="搜索卡片"/>
    </div>
    <div class="item_content">
        <ul id="cardList" style="position: relative;">

        </ul>

    </div>
</div>


<div class="bottomline">
    <span>Hpetax</span>
</div>
<div class="scrollbartop"></div>
<!-- 选择数据源弹窗 -->
<div class="modal fade" id="newCardModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog select-dialog" role="document">
        <div class="modal-content select-content">
            <div class="modal-header">
                <img class="select-close" src="img/close_icon.png" onclick="$('#newCardModal').modal('hide')"/>
                <h2 class="select-title" id="myModalLabel">选择一个数据源</h2>
            </div>
            <div class="modal-body">
                <ul class="select_list">
                    <li data-type="select_excel"
                        onclick="window.location.href='addtable?isCreateCard=true';"><img
                            src="img/select_excel.png" style="width: 41px;">
                        <div class="select-title">Excel</div>
                        <div class="select-desc">直接上传Excel文件</div>
                    </li>
                    <li class="select-disabled" data-type="select_data"><img src="img/select_data.png">
                        <div class="select-title">已有数据</div>
                        <div class="select-desc">
                            从数据中心里已<br>有的数据导入
                        </div>
                    </li>
                    <li class="select-left select-disabled" data-type="select_cloud"><img src="img/select_cloud.png">
                        <div class="select-title">云存储</div>
                        <div class="select-desc">
                            从云存储里已有<br>的数据导入
                        </div>
                    </li>
                    <li class="select-disabled" data-type="select_form"><img src="img/select_form.png">
                        <div class="select-title">在线表单</div>
                        <div class="select-desc">填写在线表单上传</div>
                    </li>
                    <li class="select-disabled" data-type="select_survey"><img
                            src="img/select_survey.png" style="margin-left: 9px;">
                        <div class="select-title">在线调研</div>
                        <div class="select-desc">上传Word、PPT文档</div>
                    </li>
                    <li class="select-left select-disabled" data-type="select_img"><img src="img/select_img.png">
                        <div class="select-title">上传图片</div>
                        <div class="select-desc">
                            上传JPG、GIF、<br>PNG等图片文件
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include page="foot.jsp"/>
<script src="js/home.js"></script>
<script src="js/warnAlert.js"></script>
</body>
</html>