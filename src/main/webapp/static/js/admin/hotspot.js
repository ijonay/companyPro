/**
 * Created by xyzhuzhou on 2016/8/9 0009.
 */
(function (a, e) {
    var fs = this.Hotspot = new Object();
    fs.updateState = function (id, state, func) {
        return KD.ajax("../api/hotspot/" + id + "/state?state=" + state + "&Load=" + Math.random(), "PUT", null, func, null, "application/json");
    };
    // fs.del = function (id, func) {
    //     return KD.ajax("../api/hotspot/" + id + "?Load=" + Math.random(), "DELETE", null, func, null, "application/json");
    // };
    fs.get = function (id, func) {
        return KD.get("../api/hotspot/" + id + "?Load=" + Math.random(), null, func);
    };
    fs.edit = function (jsonData, func) {
        return KD.ajax("../api/hotspot/?Load=" + Math.random(), "POST", jsonData, func, null, "application/json");
    };
    fs.getAll = function (func) {
        return KD.get("../api/hotspot/getall?Load=" + Math.random(), null, func);
    };

})(window);