package com.zc.service;

import com.zc.dao.WxArticleFieldMapper;
import com.zc.dao.WxArticleInfoMapper;
import com.zc.model.WxArticleField;
import com.zc.model.WxArticleInfoModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangchengli on 2017/1/17.
 */
@Service
public class WxArticleServiceImpl implements WxArticleService {

    @Autowired
    public WxArticleInfoMapper wxArticleInfoMapper;

    @Override
    public List<WxArticleInfoModel> getWxWxArticleInfoList(
            @Param("pageSize")Integer pageSize,
            @Param("rowStart")Integer rowStart ){
            return wxArticleInfoMapper.getWxArticleList(pageSize, rowStart);
    }


    @Autowired
    public WxArticleFieldMapper wxArticleFieldMapper;

    @Override
    public List<WxArticleField> getWxArticleFields(){
        return wxArticleFieldMapper.getWxArticleFields();
    }


}
