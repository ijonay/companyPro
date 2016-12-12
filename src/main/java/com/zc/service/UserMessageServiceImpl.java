package com.zc.service;

import com.zc.bean.Message;
import com.zc.bean.UserMessage;
import com.zc.bean.Users;
import com.zc.dao.MessageMapper;
import com.zc.dao.UserMessageMapper;
import com.zc.enumeration.StatusCodeEnum;
import com.zc.enumeration.UserMessageTypeEnum;
import com.zc.utility.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private UserMessageMapper userMessageMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UsersService usersService;

    @Override
    public boolean add(UserMessage record) {
        return userMessageMapper.add(record) > 0;
    }

    @Override
    public boolean del(Integer id) {
        return userMessageMapper.del(id) > 0;
    }

    @Override
    public boolean update(UserMessage record) {
        return userMessageMapper.update(record) > 0;
    }

    @Override
    public UserMessage get(Integer id) {
        return userMessageMapper.get(id);
    }

    @Override
    public List<UserMessage> getColl(Integer top) {
        return userMessageMapper.getColl(top);
    }

    @Override
    public boolean getUserInitState(Integer userId) {
        Message lastMsgByTypeAndCreateTime = getLastMsgByTypeAndCreateTime
                (UserMessageTypeEnum.UserGuide, null);

        if (Objects.isNull(lastMsgByTypeAndCreateTime)) return false;
        UserMessage byUserAndMsg = userMessageMapper.getByUserAndMsg(lastMsgByTypeAndCreateTime.getId(), userId);

        return (Objects.isNull(byUserAndMsg) || Objects.isNull(byUserAndMsg.getId()));

    }

    @Override
    public boolean changeUserInitState(Integer userId) {

        Message lastMsgByTypeAndCreateTime = getLastMsgByTypeAndCreateTime
                (UserMessageTypeEnum.UserGuide, null);

        if (Objects.isNull(lastMsgByTypeAndCreateTime)) return false;

        boolean flag = getUserInitState(userId);

        if (flag) {

            UserMessage model = new UserMessage();
            model.setCreateTime(new Date());
            model.setMessageId(getLastMsgByTypeAndCreateTime(UserMessageTypeEnum.UserGuide, null).getId());
            model.setUserId(userId);

            userMessageMapper.add(model);
        }
        return flag;
    }

    @Override
    public boolean getProjUpdateMsg(Integer userId) {

        Users user = usersService.get(userId);

        Message message = getLastMsgByTypeAndCreateTime(UserMessageTypeEnum.SystemUpdate, user
                .getCreateTime());

        if (Objects.nonNull(message)) {

            UserMessage byUserAndMsg = userMessageMapper.getByUserAndMsg(message.getId(), userId);

            return (Objects.isNull(byUserAndMsg) || Objects.isNull(byUserAndMsg.getId()));

        }

        return false;
    }

    @Override
    public boolean changeProjUpdateState(Integer userId) {

        Objects.requireNonNull(userId);

        Users user = usersService.get(userId);

        Message message = getLastMsgByTypeAndCreateTime(UserMessageTypeEnum.SystemUpdate, user
                .getCreateTime());

        if (Objects.isNull(message))
            throw new ServiceException(StatusCodeEnum.FAILED, "无项目更新信息！");

        UserMessage byUserAndMsg = userMessageMapper.getByUserAndMsg(message.getId(), userId);

        if (Objects.nonNull(byUserAndMsg) && byUserAndMsg.getId() > 0) return false;


        UserMessage model = new UserMessage();
        model.setCreateTime(new Date());
        model.setMessageId(message.getId());
        model.setUserId(userId);

        return userMessageMapper.add(model) > 0;


    }

    private Message getLastMsgByTypeAndCreateTime(UserMessageTypeEnum type, Date createTime) {
        List<Message> msgList = messageMapper.getByType(type.getValue(), createTime);
        if (msgList.size() > 0)
            return msgList.get(0);
        return null;
    }

//  @Override
//  public Page getCollByPage(Page page) {
//
//  return page.data(userMessageMapper.getCollByPage(page));
//
//  }

//    @Override
//    public int delAll() { return userMessageMapper.delAll(); }

}
