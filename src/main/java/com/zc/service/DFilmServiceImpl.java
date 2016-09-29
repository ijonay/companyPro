package com.zc.service;

import com.zc.bean.DFilm;
import com.zc.dao.DFilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DFilmServiceImpl implements DFilmService {

    @Autowired
    private DFilmMapper dFilmMapper;
    
    @Override
    public boolean add(DFilm record) {
        return dFilmMapper.add(record) > 0;
    }
    
    @Override
    public boolean del(Integer id) {
        return dFilmMapper.del(id) > 0;
    }
    
    @Override
    public boolean update(DFilm record) {
       return dFilmMapper.update(record) > 0;
    }

    @Override
    public DFilm get(Integer id) {
        return dFilmMapper.get(id);
    }

}
