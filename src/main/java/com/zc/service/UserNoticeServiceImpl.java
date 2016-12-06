package com.zc.service;

import com.zc.bean.UserNotice;
import com.zc.dao.UserNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserNoticeServiceImpl implements UserNoticeService {

    @Autowired
    private UserNoticeMapper userNoticeMapper;

    @Override
    public boolean add(UserNotice record) {
        return userNoticeMapper.add(record) > 0;
    }

    @Override
    public boolean del(Integer id) {
        return userNoticeMapper.del(id) > 0;
    }

    @Override
    public boolean update(UserNotice record) {
        return userNoticeMapper.update(record) > 0;
    }

    @Override
    public UserNotice get(Integer id) {
        return userNoticeMapper.get(id);
    }

    @Override
    public List<UserNotice> getColl(Integer top) {
        return userNoticeMapper.getColl(top);
    }

    @Override
    public List<UserNotice> getByUserAndNotice(Integer userId, Integer noticeId) {
        return userNoticeMapper.getByUserAndNotice(userId, noticeId);
    }
//  @Override
//  public Page getCollByPage(Page page) {
//
//  return page.data(userNoticeMapper.getCollByPage(page));
//
//  }

//    @Override
//    public int delAll() { return userNoticeMapper.delAll(); }

}
