/**
 *文件用途说明：提供模板
 *作者姓名:于莎
 *制作日期：2016/4/15
 **/
var templates = templates || {};
templates.design = {};
(function() {
    tmpl = [];
    tmpl.push('{{for data.sheets}}');
    tmpl.push('<li>');
    tmpl.push('    <input id="radio{{:sheetid}}" type="radio" name="rdiSheet" value="{{:sheetid}}" data-index="{{:sheetindex}}" {{if error.code!=0}}disabled="disabled"{{/if}}>');
    tmpl.push('    <label class="sheetName" title="{{:sheetname}}" for="radio{{:sheetid}}">{{>sheetname}}</label>');
    tmpl.push('</li>');
    tmpl.push('{{/for}}');
    templates.design['tmplSheetList'] = tmpl.join('\r\n');

    
})();