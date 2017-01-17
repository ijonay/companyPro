package com.zc.service;

import com.zc.dao.WxArticleFieldMapper;
import com.zc.model.WxArticleField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangchengli on 2017/1/17.
 */
@Service
public class WxArticleFieldServiceImpl
        implements WxArticleFieldService {

    @Autowired
    public WxArticleFieldMapper wxArticleFieldMapper;

    @Override
    public List<WxArticleField> getWxArticleFields(){
        return wxArticleFieldMapper.getWxArticleFields();
    }

}
