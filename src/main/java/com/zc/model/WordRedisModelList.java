package com.zc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by 张镇强 on 2016/8/30 12:01.
 */
public class WordRedisModelList extends ArrayList<WordRedisModel> implements Serializable {
    public WordRedisModelList(Set<WordEntry> entity) {
//        entity.forEach(n -> this.add(new WordRedisModel() {{
//            setName(n.name);
//            setScore(n.score);
//        }}));
    }
}
