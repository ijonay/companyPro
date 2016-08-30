package com.zc.service;

import com.zc.model.WordEntry;
import com.zc.model.WordRedisModel;
import com.zc.model.WordRedisModelList;
import com.zc.utility.ResourceDict;
import com.zc.utility.WordVectorHelper;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by 张镇强 on 2016/8/29 16:56.
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String, WordRedisModel> redisTemplate;
    @Resource(name = "redisTemplate")
    private ListOperations<String, WordRedisModel> listOps;
    private final String WORDREDISKEY = "topicanalysis:word:";

    public void add() {
//        listOps.leftPush(key, word);

        String modelName = "doubanweibo1";
        try {
            Map<String, float[]> wordMap = WordVectorHelper.loadModel(ResourceDict.MODEL_DICT.get(modelName).getModelPath());
            Set<Map.Entry<String, float[]>> wordSet = wordMap.entrySet();
            int count = 1;
            long start = System.currentTimeMillis();
            for (Map.Entry<String, float[]> entry : wordSet) {
                String name = entry.getKey();
                if (StringUtils.isEmpty(name)) {
                    continue;
                }
                Set<WordEntry> neighbors =
                        WordVectorHelper.getDistance(name, wordMap, 25, 0.4f);

                if (neighbors.size() == 0) {
                    continue;
                }

                neighbors.forEach(n -> {
                    WordRedisModel w = new WordRedisModel(n.name, n.score);
//                    listOps.rightPush(WORDREDISKEY + name, w);
                    redisTemplate.boundZSetOps(WORDREDISKEY + name).add(w, n.score);
                });


                if (count++ % 100 == 0) {
                    System.out.println("-----------:" + count + "||" + (System.currentTimeMillis() - start));
                }
            }

            System.out.println("耗时:" + (System.currentTimeMillis() - start));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void get(String word) {
        Set<byte[]> keys = ((RedisTemplate) redisTemplate).getConnectionFactory().getConnection().keys("*".getBytes());
        Iterator<byte[]> iterator = keys.iterator();
        while (iterator.hasNext()) {
            byte[] data = (byte[]) iterator.next();
            String name = new String(data, 0, data.length);
            long s = System.currentTimeMillis();
//            List<WordRedisModel> values = listOps.range(name, -1, -10);
            Set<WordRedisModel> values = redisTemplate.boundZSetOps(name).reverseRange(0, 9);
//            Set<WordRedisModel> value1 = redisTemplate.boundZSetOps(name).range(0, 9);
            long span = System.currentTimeMillis() - s;
            System.out.println(span);
        }
    }
}
