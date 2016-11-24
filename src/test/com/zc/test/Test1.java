package zc.test;

import com.alibaba.fastjson.JSON;
import com.zc.BaseTest;
import com.zc.WordRedisModel;
import com.zc.model.path.ValModel;
import com.zc.service.RedisServiceImpl;
import com.zc.service.WordService;
import com.zc.utility.CommonHelper;
import com.zc.utility.EffectLog;
import com.zc.utility.WordVectorHelper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by xyzhuzhou on 2016/10/26 0026 16:29:12.
 */
public class Test1 extends BaseTest {

    private static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);


    @Resource
    private RedisTemplate<String, WordRedisModel> redisTemplate;
    @Resource
    private WordService wordService;

    @Resource
    ApplicationContext app;

    @Test
    public void test2() {

        String start = "王宝强";
        String end = "马蓉";

        Integer maxSize = 1000;

        EffectLog effectLog = new EffectLog("BBBBBBBBBBBBBB");

        float[] startVectors = wordService.getWordVectorsByCache(start);
        float[] endVectors = wordService.getWordVectorsByCache(end);

        effectLog.add("loadVectors");

        if (Objects.isNull(startVectors)) {

            System.out.println("当前节点值无效！");
            return;
        }
        if (Objects.isNull(endVectors)) {

            System.out.println("输入目标值无效！");
            return;
        }

        Map<String, float[]> modelMap = wordService.getModelMap();

        Map<String, ValModel> mapResult = new HashMap<>();

        modelMap.forEach((k, v) -> mapResult.put(k, new ValModel(v, WordVectorHelper.getSimilarity(startVectors,
                v))));

        effectLog.add("getSimilarity");

        List<Map.Entry<String, ValModel>> list = new ArrayList<>(mapResult.entrySet());

        Collections.sort(list, (o1, o2) ->
                CommonHelper.compare(o2.getValue().getSimilarity(),
                        o1.getValue().getSimilarity())
        );
        effectLog.add("sort by similarity");

        Map.Entry<String, ValModel> endItem = list.stream().filter(p -> p.getKey().equals(end)).findFirst().get();

        effectLog.add("get endItem similarity");

        List<Map.Entry<String, ValModel>> result = new ArrayList<>();

        if (Objects.nonNull(endItem)) {

            for (int i = 1; i < list.size(); i++) {

                Map.Entry<String, ValModel> item = list.get(i);

                if (item.getValue().getSimilarity() >= endItem.getValue().getSimilarity() && item.getValue()
                        .getSimilarity
                                () > 0) {

                    item.getValue().setVal(null);

                    result.add(item);

                } else {
                    break;
                }

                if (item.getKey().equals(end)) break;

            }


//            result = list.stream().filter(p -> (p.getValue().getSimilarity() >= endItem.getValue().getSimilarity()) &&
//                    (p.getValue().getSimilarity() > 0)
//            )
//                    .collect(Collectors.toList());

            effectLog.add("get last result");

        }

        System.out.println("*******************************");

        if (result.size() > 0) {

            System.out.println(result.size());

            System.out.println(JSON.toJSONString(result));

            if (result.size() > maxSize) {

                System.out.println("当前结果超出" + maxSize);


            }

        } else {

            System.out.println("无结果！");

        }

        effectLog.writeToConsole();


//        final ValModel similarity = new ValModel();
//        similarity.setSimilarity(0);
//
//        list.stream().filter(p -> {
//
//            if (end.equals(p.getKey())) {
//
//                similarity.setSimilarity(p.getValue().getSimilarity());
//
//            }
//            if (similarity.getSimilarity() != 0) {
//
//                return p.getValue().getSimilarity() >= similarity.getSimilarity();
//
//            }
//
//            return true;
//
//        }).collect(Collectors.toList());


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

