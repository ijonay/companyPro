package com.zc.model;

import java.util.HashMap;

/**
 * Created by xyzhuzhou on 2016/11/28 0028.
 */
public class MapModel<K, V> extends HashMap<K, V> {

    public MapModel<K, V> addModel(K key, V val) {

        this.put(key, val);

        return this;

    }

}
