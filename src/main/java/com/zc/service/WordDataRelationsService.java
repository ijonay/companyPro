package com.zc.service;

import java.util.List;

import com.zc.bean.WordDataRelations;

public interface WordDataRelationsService {
    Integer add(WordDataRelations bean);

    List<WordDataRelations> get(Integer datatype,Integer dataid);

    Integer update(WordDataRelations bean);

    Integer del(Integer id);

    Integer getItemCount(Integer datatype);

    List<WordDataRelations> getList(Integer datatype,Integer pageSize, Integer rowStart);
}
