package com.zc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zc.bean.Word;

public interface WordDao {
    Integer add(Word bean);

    Integer del(Integer id);

    Integer update(Word bean);

    Word get(Integer id);
    
    Integer getItemCount();

    List<Word> getList(@Param("pageSize") Integer pageSize, @Param("rowStart")Integer rowStart);
}
