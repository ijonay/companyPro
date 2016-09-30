package com.zc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.bean.WordDataRelations;
import com.zc.dao.WordDataRelationsDao;

@Service
public class WordDataRelationsServiceImpl implements WordDataRelationsService {

    @Autowired
    private WordDataRelationsDao dao;

    @Override
    public Integer add(WordDataRelations bean) {
        if (bean == null)
            return 0;
        return dao.add(bean);
    }

    @Override
    public List<WordDataRelations> get(Integer datatype, Integer dataid) {
        return dao.get(datatype, dataid);
    }

    @Override
    public Integer update(WordDataRelations bean) {
        if (bean == null)
            return 0;
        return dao.update(bean);
    }

    @Override
    public Integer del(Integer id) {
        return dao.del(id);
    }

    @Override
    public Integer getItemCount(Integer datatype) {
        return dao.getItemCount(datatype);
    }

    @Override
    public List<WordDataRelations> getList(Integer datatype,Integer pageSize, Integer rowStart) {
        return dao.getList(datatype, pageSize, rowStart);
    }

}
