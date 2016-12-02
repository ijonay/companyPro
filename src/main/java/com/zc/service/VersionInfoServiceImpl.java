package com.zc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zc.bean.VersionInfo;
import com.zc.dao.VersionInfoMapper;

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
