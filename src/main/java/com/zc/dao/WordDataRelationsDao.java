package com.zc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zc.bean.WordDataRelations;

public interface WordDataRelationsDao {
    Integer add(WordDataRelations bean);

    Integer del(Integer id);

    Integer update(WordDataRelations bean);

    List<WordDataRelations> get(@Param("datatype") Integer datatype,@Param("dataid") Integer dataid);
    
    Integer getItemCount(@Param("datatype")Integer datatype);

    List<WordDataRelations> getList(@Param("datatype")Integer datatype,@Param("pageSize") Integer pageSize, @Param("rowStart")Integer rowStart);
}
