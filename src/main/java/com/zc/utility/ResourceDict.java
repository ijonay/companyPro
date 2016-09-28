package com.zc.utility;

import com.zc.model.WordModel;

import java.util.HashMap;

/**
 * Created by polun on 2016/7/15.
 */
public interface ResourceDict {
    HashMap<String, WordModel> MODEL_DICT = new HashMap<String, WordModel>() {{
        put("warcraft", new WordModel(
                "warcraft",
                "魔兽世界",
                "classpath:dataset/vectors.bin.Douban.WarCraft",
                "classpath:keyword/Douban.WarCraft.Keyword.txt"
        ));
        put("ring", new WordModel(
                "ring",
                "指环王",
                "classpath:dataset/vectors.bin.Douban.Ring",
                "classpath:keyword/Douban.Ring3.Keyword.txt"
        ));
        put("crazyanimal", new WordModel("crazyanimal",
                "疯狂动物城",
                "classpath:dataset/vectors.bin.Douban.CrazyAnimal",
                "classpath:keyword/Douban.CrazyAnimal.Keyword.txt"
        ));
        put("weibo", new WordModel("weibo",
                "微博",
                "classpath:dataset/vectors.bin.Sample.Douban.Weibo",
                "classpath:keyword/Douban.Weibo.Keyword.txt"
        ));
        put("doubanweibo1", new WordModel("doubanweibo1",
                "豆瓣微博1",
                "classpath:dataset/vectors.bin.DoubanWeibo.sample.cbow0",
                ""
        ));
        put("doubanweibo2", new WordModel("doubanweibo2",
                "豆瓣微博2",
                "classpath:dataset/vectors.bin.DoubanWeibo.sample.cbow1",
                ""
        ));


    }};
    HashMap<String,String> Topic_Dict= new HashMap<String, String>() {{
        put("weibo", "classpath:dataset/vectors.bin.Sample.Douban.Weibo");
        put("cbow0", "classpath:dataset/vectors.bin.DoubanWeibo.sample.cbow0");
        put("cbow1", "classpath:dataset/vectors.bin.DoubanWeibo.sample.cbow1");
    }};
    HashMap<String,String> Topic_DIRECTORY= new HashMap<String, String>() {{
        put("dict", "topicword");
    }};
}
