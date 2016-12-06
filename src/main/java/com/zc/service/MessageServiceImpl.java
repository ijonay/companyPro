package com.zc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zc.utility.page.Page;

import com.zc.bean.Message;
import com.zc.dao.MessageMapper;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public boolean add(Message record) {
        return messageMapper.add(record) > 0;
    }

    @Override
    public boolean del(Integer id) {
        return messageMapper.del(id) > 0;
    }

    @Override
    public boolean update(Message record) {
       return messageMapper.update(record) > 0;
    }

    @Override
    public Message get(Integer id) {
        return messageMapper.get(id);
    }
   @Override
   public List<Message> getColl(Integer top) {
       return messageMapper.getColl(top);
   }
//  @Override
//  public Page getCollByPage(Page page) {
//
//  return page.data(messageMapper.getCollByPage(page));
//
//  }

//    @Override
//    public int delAll() { return messageMapper.delAll(); }

}
