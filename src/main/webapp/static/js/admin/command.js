/**
 * Created by xyzhuzhou on 2016/12/2 0002 15:48:18.
 */

(function (a, e) {
    var fs = this.CMD = new Object();
    fs.pu = function () {
        var json = doOptions(arguments);
        return KD.ajax("../api/" + json.name, "PUT", json.jsonData, json.success, json.error, "application/json");
    };
    fs.d = function () {
        var json = doOptions(arguments);
        return KD.ajax("../api/" + json.name, "DELETE", json.jsonData, json.success, json.error, "application/json");
    };
    fs.g = function () {
        var json = doOptions(arguments);
        return KD.get("../api/" + json.name, json.jsonData, json.success);
    };
    fs.po = function () {
        var json = doOptions(arguments);
        return KD.ajax("../api/" + json.name, "POST", json.jsonData, json.success, json.error, "application/json");
    };
    function doOptions(arg) {
        var options = {name: "", jsonData: null, success: null, error: null};
        options.name = getlink(arg[0]);
        if (arg.length > 1) {
            if (typeof  arg[1] == typeof function () {
                }) {
                options.success = arg[1];
                if (arg.length > 2) {
                    options.error = arg[2];
                }
            } else {
                options.jsonData = arg[1];
                if (arg.length > 2) {
                    options.success = arg[2]
                    if (arg.length > 3) {
                        options.error = arg[3];
                    }
                }

            }
        }
        return options;
    }

    function getlink(name) {
        name = name.replace(/\./g, "/");
        return name + (name.indexOf("?") > -1 ? "&" : "?") + "Load=" + Math.random();
    }
})(window);