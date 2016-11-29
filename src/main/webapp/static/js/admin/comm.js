/**
 * Created by xyzhuzhou on 2016/8/5 0005.
 */
!function (e) {
    this.comm = {
        checkActive: function (name) {
            $("#leftContainer > ul > li[name='" + name + "']").addClass("active");
        }

    };

    function checkActive() {
        var items = location.href.split("/");
        var item = items[items.length - 1];
        if (item.indexOf("?") > -1) {
            item = item.split("?")[0];
        }
        if (item.indexOf("#") > -1) {
            item = item.split("#")[0];
        }
        comm.checkActive(item);
    }

    $(checkActive);

}(window);


