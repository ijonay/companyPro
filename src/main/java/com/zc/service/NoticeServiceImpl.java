package com.zc.service;

import com.zc.bean.Notice;
import com.zc.dao.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public boolean add(Notice record) {
        return noticeMapper.add(record) > 0;
    }

    @Override
    public boolean del(Integer id) {
        return noticeMapper.del(id) > 0;
    }

    @Override
    public boolean update(Notice record) {
       return noticeMapper.update(record) > 0;
    }

    @Override
    public Notice get(Integer id) {
        return noticeMapper.get(id);
    }
   @Override
   public List<Notice> getColl(Integer top) {
       return noticeMapper.getColl(top);
   }
//  @Override
//  public Page getCollByPage(Page page) {
//
//  return page.data(noticeMapper.getCollByPage(page));
//
//  }

//    @Override
//    public int delAll() { return noticeMapper.delAll(); }

}
