package com.zc.service;

import com.zc.bean.VersionInfo;
import com.zc.dao.VersionInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VersionInfoServiceImpl implements VersionInfoService {

    @Autowired
    private VersionInfoMapper versionInfoMapper;
    
    @Override
    public boolean add(VersionInfo record) {
        return versionInfoMapper.add(record) > 0;
    }
    
    @Override
    public boolean del(Integer id) {
        return versionInfoMapper.del(id) > 0;
    }
    
    @Override
    public boolean update(VersionInfo record) {
       return versionInfoMapper.update(record) > 0;
    }

    @Override
    public VersionInfo get(Integer id) {
        return versionInfoMapper.get(id);
    }

    @Override
    public List<VersionInfo> getColl(Integer top) {
        return versionInfoMapper.getColl(top);
    }
//    @Override
//    public int delAll() { return versionInfoMapper.delAll(); }

}
