package com.zc.service;

import com.zc.bean.Topic;
import com.zc.bean.UserFavoriteSearchItem;
import com.zc.bean.UserRecommendedTopics;
import com.zc.dao.UserRecommentedTopicsMapper;
import com.zc.model.TopicModel;
import com.zc.model.UserRecommendedTopicModel;
import com.zc.utility.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userRecommendedTopicsService")
public class UserRecommendedTopicsServiceImpl implements UserRecommendedTopicsService {

    @Autowired
    private TopicService topicService;

    @Autowired
    private SearchItemService searchItemService;

    @Autowired
    private UserRecommentedTopicsMapper userRecommentedTopicsMapper;

    @Autowired
    WordService wordService;

    @Override
    public boolean add(UserRecommendedTopics record) {
        return userRecommentedTopicsMapper.add(record) > 0;
    }

    @Override
    public boolean del(Integer id) {
        return userRecommentedTopicsMapper.del(id) > 0;
    }

    @Override
    public boolean update(UserRecommendedTopics record) {
        return userRecommentedTopicsMapper.update(record) > 0;
    }

    @Override
    public UserRecommendedTopics get(Integer id) {
        return userRecommentedTopicsMapper.get(id);
    }

    @Override
    public void updateUserRecommendedTopics() {
        //get hot topic list
        List<Topic> hotTopicList = topicService.getHotTopic(null, 100);
        Set<Integer> hotTopicIdSet = new HashSet<Integer>();
        hotTopicList.forEach(item -> hotTopicIdSet.add(item.getId()));

        //get users and theirs' keywords
        List<UserFavoriteSearchItem> allUserFavoriteKeywordList =
                searchItemService.getAllUserFavoriteSearchItems();

        Map<String, Set<Integer>> kwResultMap = new HashMap<String, Set<Integer>>();

        for (int i = 0; i < allUserFavoriteKeywordList.size(); i++) {

            try {

                UserFavoriteSearchItem userFavoriteSearchItem = allUserFavoriteKeywordList.get(i);
                String keyword = userFavoriteSearchItem.getWords();
                Integer userId = userFavoriteSearchItem.getUserId();

                if (!kwResultMap.keySet().contains(keyword)) {// this keyword hasn't been searched
                    Page page = topicService.getListExt(keyword, null, 1, 20);
                    List<TopicModel> topicModels = (List<TopicModel>) page.getData();
                    if (topicModels.size() > 0) {
                        Set<Integer> topicIdSet = new HashSet<Integer>();
                        topicModels.forEach(item -> topicIdSet.add(item.getId()));
                        topicIdSet.retainAll(hotTopicIdSet);
                        kwResultMap.put(keyword, topicIdSet);
                    }
                }

                Set<Integer> topicIdSet = kwResultMap.get(keyword);
                if (topicIdSet != null && topicIdSet.size() > 0) {// has been searched
                    for (Integer topicId : topicIdSet) {
                        //insert into db
                        UserRecommendedTopics recommentedTopics = new UserRecommendedTopics();
                        recommentedTopics.setUserId(userId);
                        recommentedTopics.setKeyword(keyword);
                        recommentedTopics.setTopicId(topicId);
                        userRecommentedTopicsMapper.add(recommentedTopics);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<UserRecommendedTopicModel> getUserHotTopicMessageList(Integer userId, Integer count) {

        return userRecommentedTopicsMapper.getUserHotTopicMessageList(userId, count);

    }

    @Override
    public TopicModel getTopicMessageDetail(Integer id) {
        UserRecommendedTopics userRecommendedTopics = userRecommentedTopicsMapper.get(id);
        if (userRecommendedTopics == null) {
            return null;
        }
        String keyword = userRecommendedTopics.getKeyword();
        float[] sourceVectors = wordService.getWordVectorsByCache(keyword);
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(userRecommendedTopics.getTopicId());
        List<TopicModel> topicModels = topicService.getTopicByIdList(ids, sourceVectors);
        if (topicModels.size() > 0) {
            return topicModels.get(0);
        }
        return null;
    }

    @Override
    public boolean delAll(List<String> idList) {
        return userRecommentedTopicsMapper.delAll(idList) > 0;
    }
}
