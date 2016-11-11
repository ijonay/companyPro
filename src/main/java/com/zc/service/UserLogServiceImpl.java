package com.zc.service;

import com.zc.bean.UserLog;
import com.zc.dao.UserLogMapper;
import com.zc.enumeration.UserBehaviorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class UserLogServiceImpl implements UserLogService {

    @Autowired
    private UserLogMapper userLogMapper;

    @Autowired
    HttpServletRequest request;

    @Override
    public boolean add(UserLog record) {
        return userLogMapper.add(record) > 0;
    }

    @Override
    public boolean del(Integer id) {
        return userLogMapper.del(id) > 0;
    }

    @Override
    public boolean update(UserLog record) {
        return userLogMapper.update(record) > 0;
    }

    @Override
    public UserLog get(Integer id) {
        return userLogMapper.get(id);
    }

    @Override
    public void log(Integer userId, UserBehaviorEnum behaviorEnum, String logInfo) {

        UserLog userLog = new UserLog();

        userLog.setCreateDate(new Date());
        userLog.setUserId(userId);
        logInfo = request.getSession().getId() + ":" + logInfo;
        userLog.setLogInfo(logInfo);
        userLog.setLogType(behaviorEnum.getValue());
        userLog.setIp(request.getRemoteAddr());
        userLog.setExploreType(request.getHeader("User-Agent"));

        userLogMapper.add(userLog);

    }
//    @Override
//    public List<UserLog > getAll() { return userLogMapper.getAll();}
//
//    @Override
//    public int delAll() { return userLogMapper.delAll(); }

}
