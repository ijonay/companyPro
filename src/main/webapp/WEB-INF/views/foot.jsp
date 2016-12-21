<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<script src="lib/jquery-1.12.0.min.js"></script>
<script src="lib/KD1.3.2.min.js"></script>
<script src="js/userAction.js"></script>
<SCRIPT>
    if (!$.support.leadingWhitespace) {
        alert("浏览器版本太低，请下载chrome或者IE10以上版本浏览器")
    }
</SCRIPT>

<script src="lib/jsrender.min.js"></script>
<script src="lib/underscore-min.js"></script>
<script src="lib/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/templates.js"></script>
<script src="js/util.js"></script>
<script src="lib/echarts.js"></script>
<script>
    $.get('js/china.json', function (chinaJson) {
        echarts.registerMap('china', chinaJson);
    });
</script>
<script src="lib/jquery.mousewheel.js"></script>
<script src="lib/perfect-scrollbar.js"></script>
<script src="js/calendar.js"></script>
<script src="js/nav.js"></script>


