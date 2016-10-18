/**
 * Created by xyzhuzhou on 2016/8/9 0009.
 */
(function (a, e) {
    var fs = this.Model = new Object();
    fs.submit = function (jsonData, func) {
        /// <summary>提交Model数据</summary>
        /// <param name="jsonData" type="JSON">提交数据</param>
        /// <param name="func" type="Function">回调方法</param>
        return KD.ajax("../api/addModel?Load=" + Math.random(), "POST", jsonData, func, null, "application/json");
    };
})(window);