package com.zc.service;

import com.zc.WordRedisModel;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by 张镇强 on 2016/8/29 16:56.
 */
@Service
public class RedisServiceImpl implements RedisService {
    private static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);


    @Autowired
    private RedisTemplate<String, WordRedisModel> redisTemplate;
    @Resource(name = "redisTemplate")
    private ListOperations<String, WordRedisModel> listOps;
    @Resource(name = "redisTemplate")
    private ListOperations<String, String> testListOps;

    public void add() {
        throw new NotImplementedException("不运行");
//        try {
//
//            Map<String, float[]> wordMap =
//                    WordVectorHelper.loadModel(
//                    PropertyHelper.getValue(Constant.CONFIG_PROPERTIES, Constant.MODEL_BIN));
//
//            Set<Map.Entry<String, float[]>> wordSet = wordMap.entrySet();
//            int count = 1;
//            long start = System.currentTimeMillis();
//            for (Map.Entry<String, float[]> entry : wordSet) {
//                String name = entry.getKey();
//
//                if (StringHelper.isEmpty(name)) {
//                    continue;
//                }
//                Set<WordEntry> neighbors =
//                        WordVectorHelper.getDistance(name, wordMap, 25, 0.4f);
//
//                if (neighbors.size() == 0) {
//                    continue;
//                }
//
//                neighbors.forEach(n -> {
//                    WordRedisModel w = new WordRedisModel(n.name, n.score);
//                    redisTemplate.boundZSetOps(Constant.WORDR_EDISKEY_PREFIX_KEY + name).add(w, n.score);
//                });
//
//
//                if (count++ % 100 == 0) {
//                    System.out.println("-----------:" + count + "||" + (System.currentTimeMillis() - start));
//                    logger.info("-----------:" + count + "||" + (System.currentTimeMillis() - start));
//                }
//            }
//
//            System.out.println("耗时:" + (System.currentTimeMillis() - start));
//            logger.info("耗时:" + (System.currentTimeMillis() - start));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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

    public void testadd() {
//        listOps.leftPush(key, word);
        int count = 1;
        long start = System.currentTimeMillis();
        testListOps.rightPush("test1", "张镇强");

        System.out.println("耗时:" + (System.currentTimeMillis() - start));
    }
}
