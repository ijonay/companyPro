package com.zc.service;

import com.zc.bean.Message;
import com.zc.bean.VersionInfo;
import com.zc.dao.VersionInfoMapper;
import com.zc.enumeration.UserMessageTypeEnum;
import com.zc.utility.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service

public class VersionInfoServiceImpl implements VersionInfoService {

    @Autowired
    private VersionInfoMapper versionInfoMapper;
    @Autowired
    private MessageService messageService;

    @Override
    @Transactional
    public boolean add(VersionInfo record) {

        boolean flag = versionInfoMapper.add(record) > 0;

        Message message = new Message();
        message.setCreateTime(new Date());
        message.setSender(UserMessageTypeEnum.SystemUpdate.name());
        message.setReceiver("all");
        message.setType(UserMessageTypeEnum.SystemUpdate.getValue());
        messageService.add(message);

        return flag;
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

    @Override
    public Page getCollByPage(Page page) {

        return page.data(versionInfoMapper.getCollByPage(page));

    }
//    @Override
//    public int delAll() { return versionInfoMapper.delAll(); }

}
