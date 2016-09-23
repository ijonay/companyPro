package com.zc.model.weibo;

import com.zc.bean.Weibo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 张镇强 on 2016/9/19 17:33.
 */
public class WeiboCollection extends ArrayList<WeiboItemModel> {
    public WeiboCollection(Collection<Weibo> entity) {
        entity.forEach(e->{
            this.add(new WeiboItemModel(e));
        });
    }
}
