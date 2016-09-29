package com.zc.dao;

import com.zc.bean.WordDataRelations;

public interface WordDataRelationsDao {
    Integer add(WordDataRelations topic);

    Integer del(Integer id);

    Integer update(WordDataRelations topic);

    WordDataRelations get(Integer id);
}
