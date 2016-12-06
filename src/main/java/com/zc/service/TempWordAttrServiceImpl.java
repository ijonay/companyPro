package com.zc.service;

import com.zc.bean.TempWordAttr;
import com.zc.dao.TempWordAttrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TempWordAttrServiceImpl implements TempWordAttrService {

    @Autowired
    private TempWordAttrMapper tempWordAttrMapper;

    @Override
    public boolean add(TempWordAttr record) {
        return tempWordAttrMapper.add(record) > 0;
    }

    @Override
    public boolean del(Integer id) {
        return tempWordAttrMapper.del(id) > 0;
    }

    @Override
    public boolean update(TempWordAttr record) {
        return tempWordAttrMapper.update(record) > 0;
    }

    @Override
    public TempWordAttr get(Integer id) {
        return tempWordAttrMapper.get(id);
    }


//   @Override
//   public List<TempWordAttr> getColl(Integer top) {
//       return tempWordAttrMapper.getColl(top);
//   }
//    @Override
//    public int delAll() { return tempWordAttrMapper.delAll(); }

}
