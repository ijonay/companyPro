/**
 * @title TopicServiceImpl.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月11日 下午3:19:10
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.service;

import com.zc.bean.Topic;
import com.zc.dao.TopicDao;
import com.zc.enumeration.StatusCodeEnum;
import com.zc.model.TopicModel;
import com.zc.model.TopicWordModel;

import com.zc.utility.CommonHelper;
import com.zc.utility.WordVectorHelper;
import com.zc.utility.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao dao;
    @Autowired
    private WordService wordService;

    /**
     * 获取词汇与热点话题的相似度
     *
     * @param list
     * @return
     * @see com.zc.service.TopicService#getSimilarity(java.util.List)
     */
    public float getSimilarity(List<TopicWordModel> list) {
        if (list == null || list.size() == 0)
            return 0;
        float totalWeight = 0f;
        for (TopicWordModel model : list) {
            totalWeight += model.getWordSimilarity() * model.getTopicWeight();
        }
        return totalWeight / list.size();
    }

    /**
     * 方法说明
     *
     * @param topic
     * @return
     * @see com.zc.service.TopicService#add(com.zc.bean.Topic)
     */
    @Override
    public Integer add(Topic topic) {
        return dao.add(topic);
    }

    /**
     * 方法说明
     *
     * @param list
     * @return
     * @see com.zc.service.TopicService#batchInsert(java.util.List)
     */
    @Override
    public Integer batchInsert(List<Topic> list) {
        if (list == null || list.size() == 0) return 0;
        return dao.batchInsert(list);
    }

    /**
     * 方法说明
     *
     * @param pageSize
     * @param currentPage
     * @return
     * @see com.zc.service.TopicService#getList(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<Topic> getList(Integer pageSize, Integer currentPage) {
        return dao.getList(pageSize, currentPage);
    }

    @Override
    public List<Topic> getTopicWordList(Integer pageSize, Integer currentPage) {
        return dao.getTopicWordList(pageSize, currentPage);
    }

    @Override
    public List<TopicModel> getListExt(String clueWord, Integer currentPage, Integer pageSize) {
        if (pageSize < 1 || currentPage < 1) {
            throw new ServiceException(StatusCodeEnum.CLIENT_ERROR, "分页参数错误");
        }

        float[] sourceVectors = wordService.getModelMap().get(clueWord);
        Objects.requireNonNull(sourceVectors, "没有找到线索词的坐标");

        HashMap<Integer, float[]> allCoordinates = getAllCoordinates();
        LinkedList<Integer> idList = new LinkedList<>();
        idList.addAll(allCoordinates.keySet());

        Collections.sort(idList, (left, right) ->
                CommonHelper.compare(WordVectorHelper.getSimilarity(sourceVectors, allCoordinates.get(right)),
                        WordVectorHelper.getSimilarity(sourceVectors, allCoordinates.get(left)))
        );

        List<Integer> ids = idList.stream().skip((currentPage - 1) * pageSize).limit(pageSize).collect(Collectors.toList());

        List<TopicModel> topicModels = getTopicByIdList(ids,sourceVectors);
        Collections.sort(topicModels, (left, right) ->
                CommonHelper.compare(right.getScore(), left.getScore()));

        return topicModels;
    }

    /**
     * 方法说明
     *
     * @return
     * @see com.zc.service.TopicService#getItemCount()
     */
    @Override
    public Integer getItemCount() {
        return dao.getItemCount();
    }

    /**
     * 方法说明
     *
     * @param list
     * @return
     * @see com.zc.service.TopicService#batchUpdate(java.util.List)
     */
    @Override
    public Integer batchUpdate(List<Topic> list) {
        if (list == null || list.size() == 0) return 0;
        return dao.batchUpdate(list);
    }

    @Override
    public Topic get(Integer topicId) {
        Topic topic = dao.get(topicId);
        Objects.requireNonNull(topic);

        return topic;
    }

    @Override
    public HashMap<Integer, float[]> getAllCoordinates() {
        List<HashMap<Integer, String>> coordinatesMapList = dao.getAllCoordinates();
        Objects.requireNonNull(coordinatesMapList);

        LinkedHashMap<Integer, float[]> result = new LinkedHashMap<>();
        coordinatesMapList.forEach(c -> {
            result.put(Integer.parseInt(String.valueOf(c.get("id"))),
                    CommonHelper.stringToFloatArray(c.get("coordinate")));
        });

        return result;
    }

    @Override
    public List<TopicModel> getTopicByIdList(List<Integer> idList, float[] sourceVectors) {
        List<Topic> topics = dao.getByIdList(idList);

        List<TopicModel> result = topics.stream().map(t -> {

            TopicModel item = t.getModel();
            item.setScore(WordVectorHelper
                    .getSimilarity(sourceVectors, CommonHelper.stringToFloatArray(t.getCoordinate())));
            return item;
        }).collect(Collectors.toList());

        return result;
    }

}
