package com.zc.model.path;

import com.zc.model.weibo.WeiboItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张镇强 on 2016/9/18 18:51.
 */
public class NodeRelations {
    private List<WeiboItemModel> weiboItemModels;

    public NodeRelations() {
        this.weiboItemModels = new ArrayList<>(); // 防止该类的使用者忘记初始化该字段
    }

    public List<WeiboItemModel> getWeiboItemModels() {
        return weiboItemModels;
    }

    public void setWeiboItemModels(List<WeiboItemModel> weiboItemModels) {
        this.weiboItemModels = weiboItemModels;
    }
}
