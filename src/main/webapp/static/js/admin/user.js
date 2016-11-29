/**
 * Created by xyzhuzhou on 2016/8/9 0009.
 */
(function (a, e) {
    var fs = this.User = new Object();
    fs.updateState = function (id, state, func) {
        return KD.ajax("../api/user/" + id + "/state?state=" + state + "&Load=" + Math.random(), "PUT", null, func, null, "application/json");
    };
    fs.del = function (id, func) {
        return KD.ajax("../api/user/" + id + "?Load=" + Math.random(), "DELETE", null, func, null, "application/json");
    };
    fs.getUsers = function (func) {
        return KD.get("../api/user/users?Load=" + Math.random(), null, func);
    };
    fs.getUser = function (id, func) {
        return KD.get("../api/user/" + id + "?Load=" + Math.random(), null, func);
    };
    fs.addUser = function (jsonData, func) {
        return KD.ajax("../api/account/register?Load=" + Math.random(), "POST", jsonData, func, null, "application/json");
    };
    fs.updateUser = function (id, jsonData, func) {
        return KD.ajax("../api/user/" + id + "?Load=" + Math.random(), "POST", jsonData, func, null, "application/json");
    };

})(window);