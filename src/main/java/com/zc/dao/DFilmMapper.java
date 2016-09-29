package com.zc.dao;

import com.zc.bean.DFilm;

/**
 * 
 */
public interface DFilmMapper {


    int add(DFilm record);

    DFilm get(Integer id);

    int update(DFilm record);

    int del(Integer id);


}