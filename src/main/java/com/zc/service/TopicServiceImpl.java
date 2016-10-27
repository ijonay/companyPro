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
import com.zc.utility.Constant;
import com.zc.utility.PropertyHelper;
import com.zc.utility.WordVectorHelper;
import com.zc.utility.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao dao;
    @Autowired
    private WordService wordService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ZCRedisService<float[]> redisService;

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

        long time1 = System.currentTimeMillis();

        float[] sourceVectors = wordService.getWordVectorsByCache(clueWord);
        Objects.requireNonNull(sourceVectors, "没有找到线索词的坐标,线索词:" + clueWord);

        long time2 = System.currentTimeMillis();

//        HashMap<Integer, float[]> allCoordinates = getAllCoordinates();

        HashMap<Integer, float[]> allCoordinates = getCoordinatesByCache();

        long time3 = System.currentTimeMillis();

        LinkedList<Integer> idList = new LinkedList<>();
        idList.addAll(allCoordinates.keySet());

        Collections.sort(idList, (left, right) ->
                CommonHelper.compare(WordVectorHelper.getSimilarity(sourceVectors, allCoordinates.get(right)),
                        WordVectorHelper.getSimilarity(sourceVectors, allCoordinates.get(left)))
        );
        long time4 = System.currentTimeMillis();
        List<Integer> ids = idList.stream().skip((currentPage - 1) * pageSize).limit(pageSize).collect(Collectors
                .toList());
        long time5 = System.currentTimeMillis();

        List<TopicModel> topicModels = getTopicByIdList(ids, sourceVectors);

        long time6 = System.currentTimeMillis();


        Collections.sort(topicModels, (left, right) ->
                CommonHelper.compare(right.getScore(), left.getScore()));

        long time7 = System.currentTimeMillis();

        System.out.println("time1:" + time1 + "毫秒");
        System.out.println("time2：" + time2 + "毫秒" + "，与上一个相差" + (time2 - time1) + "毫秒");
        System.out.println("time2：" + time3 + "毫秒" + "，与上一个相差" + (time3 - time2) + "毫秒");
        System.out.println("time2：" + time4 + "毫秒" + "，与上一个相差" + (time4 - time3) + "毫秒");
        System.out.println("time2：" + time5 + "毫秒" + "，与上一个相差" + (time5 - time4) + "毫秒");
        System.out.println("time2：" + time6 + "毫秒" + "，与上一个相差" + (time6 - time5) + "毫秒");
        System.out.println("time2：" + time7 + "毫秒" + "，与上一个相差" + (time7 - time6) + "毫秒");
        System.out.println("总耗时：" + (time7 - time1) + "毫秒");
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

        long time1 = System.currentTimeMillis();

        List<HashMap<Integer, String>> coordinatesMapList = dao.getAllCoordinates();
        Objects.requireNonNull(coordinatesMapList);
        long time2 = System.currentTimeMillis();

        LinkedHashMap<Integer, float[]> result = new LinkedHashMap<>();
        coordinatesMapList.forEach(c -> {
            result.put(Integer.parseInt(String.valueOf(c.get("id"))),
                    CommonHelper.stringToFloatArray(c.get("coordinate")));
        });

        long time3 = System.currentTimeMillis();

        System.out.println("time1：" + time1 + "毫秒");
        System.out.println("time2：" + time2 + "毫秒" + "，与上一个相差" + (time2 - time1) + "毫秒");
        System.out.println("time2：" + time3 + "毫秒" + "，与上一个相差" + (time3 - time2) + "毫秒");
        System.out.println("总耗时：" + (time3 - time1) + "毫秒");
        return result;
    }

    @Override
    public HashMap<Integer, float[]> getCoordinatesByCache() {

        String key = PropertyHelper.getValue(Constant
                        .CONFIG_PROPERTIES,
                Constant.TOPICS_VECTORS_KEY);

        return redisService.getCacheObject(key);
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

    @Override
    public void cache_UpdateTopics() {

        HashMap<Integer, float[]> result = getAllCoordinates();

        if (Objects.nonNull(result)) {

            String key = PropertyHelper.getValue(Constant
                            .CONFIG_PROPERTIES,
                    Constant.TOPICS_VECTORS_KEY);

            redisTemplate.execute(new SessionCallback() {
                @Override
                public Object execute(RedisOperations operations) throws DataAccessException {

                    operations.multi();

                    operations.delete(key);

                    operations.opsForValue().set(key, result);

                    return operations.exec();

                }
            });

        }
    }

}
