package com.zc.service;

import com.zc.bean.DFilm;

public interface DFilmService {
    
    
    boolean add(DFilm record);
    
    boolean del(Integer id);

    boolean update(DFilm record);
    
    DFilm get(Integer id);


}