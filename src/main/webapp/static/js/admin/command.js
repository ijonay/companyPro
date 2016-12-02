/**
 * Created by xyzhuzhou on 2016/12/2 0002 15:48:18.
 */

(function (a, e) {
    var fs = this.CMD = new Object();
    fs.pu = function () {

        var arg = arguments;

        


        return KD.ajax("../api/" + getlink(name), "PUT", jsonData, success, error, "application/json");
    };
    fs.d = function (name, jsonData, success, error) {
        return KD.ajax("../api/" + getlink(name), "DELETE", jsonData, func, error, "application/json");
    };
    fs.g = function (name, success) {
        return KD.get("../api/" + getlink(name), null, success);
    };
    fs.po = function (name, jsonData, success, error) {
        return KD.ajax("../api/" + getlink(name), "POST", jsonData, success, error, "application/json");
    };
    function doOptions() {
        
    }
    function getlink(name) {
        return name + (name.indexOf("?") > -1 ? "&" : "?") + "Load=" + Math.random();
    }
})(window);