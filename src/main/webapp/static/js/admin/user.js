/**
 * Created by xyzhuzhou on 2016/8/9 0009.
 */
(function (a, e) {
    var fs = this.User = new Object();
    fs.updateState = function (id, jsonData, func) {
        return KD.ajax("../api/user/" + id + "?Load=" + Math.random(), "PUT", jsonData, func, null, "application/json");
    };
    fs.getUsers = function (func) {
        return KD.get("../api/user/users?Load=" + Math.random(), null, func);
    };
})(window);