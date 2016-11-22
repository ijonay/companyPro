package com.zc.model;

import com.zc.bean.TopicAgeStatistics;
import com.zc.bean.TopicFilterClass;

import java.util.*;

/**
 * 通用Key,Value模板
 * Created by xyzhuzhou on 2016/11/11 0011 16:24:31.
 */
public class KeyValueCollection extends ArrayList<KeyValue> {


    public KeyValueCollection() {

    }

    public KeyValueCollection(List<TopicFilterClass> list) {

        if (Objects.isNull(list)) return;

        //list.forEach(p -> this.add(new KeyValue(p.getName(), p.getTopicPercentageTotal() * 100)));
        list.forEach(p -> this.add(new KeyValue(p.getName(), p.getPercentage() * 100)));

    }

    public KeyValueCollection(List<TopicAgeStatistics> list, String temp) {

        if (Objects.isNull(list)) return;


        Integer year = Calendar.getInstance().get(Calendar.YEAR);

        list.forEach(p -> this.add(new KeyValue((year - p.getYearOfBirth()) + "", p
                .getPercentage() * 100)));


    }
}
