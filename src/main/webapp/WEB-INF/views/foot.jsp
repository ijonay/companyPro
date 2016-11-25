<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!-- 正在处理弹窗 -->
<div class="modal moadal-tooltip" id="dlgProcessing" tabindex="-1" role="dialog"
	aria-labelledby="dlgProcessingTitle" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="dlgProcessingTitle">正在处理</h4>
			</div>
			<div class="modal-body">
				<div class="text-center">请稍候……</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn-cancel" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
<!-- 一览表删除卡片弹窗 -->
<div class="modal moadal-tooltip" id="dlgDel" tabindex="-1" role="dialog" aria-labelledby="dlgDelTitle" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="dlgDelTitle">删除卡片</h4>
			</div>
			<div class="modal-body">
			    <div id="operater-id" style="display:none;"></div>
			    <div id="operater-type" style="display:none;"></div>
				<div class="text-center body-title">确认要把所选卡片放入回收站吗？</div>
				<div class="text-center body-desc">删除的卡片可在30天内通过回收站还原</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn-ok" id="del-ok">确定</button>
				<button type="button" class="btn-cancel" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
<script src="lib/jquery-1.12.0.min.js"></script>
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