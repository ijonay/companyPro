package zc.test;

import com.zc.BaseTest;
import com.zc.WordRedisModel;
import com.zc.service.RedisServiceImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * Created by xyzhuzhou on 2016/10/26 0026 16:29:12.
 */
public class Test1 extends BaseTest {

    private static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);


    @Resource
    private RedisTemplate<String, WordRedisModel> redisTemplate;
    @Resource
    ApplicationContext app;

    @Test
    public void test2() {

        String star = "小黄人";
        String end = "警察";





//        System.out.println(String.join(",", app.getBeanDefinitionNames()));

//        ArrayList<Integer> item = new ArrayList<>();
//        item.add(1);
//        item.add(2);
//
//
//        String string = Arrays.toString(item.toArray()).replaceAll("[\\[\\]]", "");

//        topicFilterMapper.getTopicIds(null);
        System.out.println("aaaaaaaaaaaaaaa");
    }

//    @Test
//    public void test1(){
//
//        try {
//
//            Map<String, float[]> wordMap =
//                    WordVectorHelper.loadModel(
//                            PropertyHelper.getValue(Constant.CONFIG_PROPERTIES, Constant.MODEL_BIN));
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
//
//    }
}
