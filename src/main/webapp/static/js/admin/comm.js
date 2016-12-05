/**
 * Created by xyzhuzhou on 2016/8/5 0005.
 */
!function (e) {
    this.Comm = {
        checkActive: function (name) {
            $("#leftContainer > ul > li[name='" + name + "']").addClass("active");
        },//批量替换显示状态，创建时间
        ReplaceFields: function (JsonData) {
            if (JsonData.length > 0) {
                for (var i = 0; i < JsonData.length; i++) {
                    Comm.ReplaceJsonItem(JsonData[i]);
                }
            }
        },
        ReplaceJsonItem: function (jsonItem) {

            //替换CreateTime
            try {
                jsonItem.createTime = KD.Date.format(jsonItem.createTime, 'yyyy-MM-dd');
            } catch (e) {
            }
            //ModifyTime
            try {
                jsonItem.modifyTime = KD.Date.format(jsonItem.modifyTime, 'yyyy-MM-dd');
            } catch (e) {
            }

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
        Comm.checkActive(item);
    }

    $(checkActive);

}(window);


