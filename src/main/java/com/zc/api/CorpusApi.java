/**
 * @title TestApi.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月16日 下午12:02:40
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zc.bean.Topic;
import com.zc.bean.Weibo;
import com.zc.model.ClusterModel;
import com.zc.service.TopicService;
import com.zc.service.WeiboService;
import com.zc.service.WordService;
import com.zc.service.ZCRedisService;
import com.zc.tempbean.TopicCleanContent;
import com.zc.tempbean.TopicInfo;
import com.zc.tempbean.WeiboCleanContent;
import com.zc.tempbean.WeiboInfo;
import com.zc.tempservice.TopicCleanContentService;
import com.zc.tempservice.TopicInfoService;
import com.zc.tempservice.WeiboCleanContentService;
import com.zc.tempservice.WeiboInfoService;
import com.zc.utility.ResourceDict;
import com.zc.utility.WordVectorHelper;
import com.zc.utility.response.ApiResultModel;

@RestController
@RequestMapping("/api/")
public class CorpusApi {
    @Autowired
    private TopicCleanContentService topicCleanContentService;
    @Autowired
    private WeiboCleanContentService weiboCleanContentService;
    @Autowired
    private TopicInfoService topicInfoService;
    @Autowired
    private WeiboInfoService weiboInfoService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private WeiboService weiboService;

    @Autowired
    private ZCRedisService redisService;
    @Autowired
    private WordService wordService;

    private String corpusDir = "topiccorpus";
    private String weiboCorpusDir = "weibocorpus";
    private String resourceRootPath = this.getClass().getResource("/")
            .getPath();
    private String weiboTempDir = "tempweibocorpus";

    @RequestMapping("Corpus")
    public ApiResultModel generateCorpus() throws Exception {
        // 生成文件
        List<TopicInfo> list = getTopicInfoList(1000);
        // 插入数据库
        //insertTopic(list);
        for (TopicInfo item : list) {
            List<String> contents = generateSingleTopicText(item.getId(), 1000);
            String relativePath = resourceRootPath + corpusDir + "/"
                    + item.getId() + ".txt";
            File file = new File(relativePath);
            writeCorpus(contents, file);
        }
        return new ApiResultModel(null);
    }

    @RequestMapping("Weibo")
    public ApiResultModel generateWeibo() throws Exception {
        /*
         * moveToDirectory(resourceRootPath + weiboCorpusDir,resourceRootPath +
         * weiboTempDir); return new ApiResultModel(null);
         */
        insertWeiboRedis();
      // String s= redisService.getCacheObject("weibo_comment_corpus:weibo:1").toString();
        return new ApiResultModel(null);
        // Integer weiboCount = weiboInfoService.getItemCount();
        // int pageCount = (int) Math.ceil(weiboCount / 1000d);
        // if (weiboCount == null || weiboCount.intValue() == 0)
        // return new ApiResultModel(null);
        // // 根据微博数量 遍历所有微博
        // for (int i = 0; i < weiboCount.intValue(); i++) {
        // // 分页取出微博
        // List<WeiboInfo> weiboList = getWeiboList(i + 1, 1000);
        // if (weiboList != null && weiboList.size() > 0) {
        // // insertWeibo(weiboList);
        // String rootPath = resourceRootPath + weiboCorpusDir;
        // File dir = new File(rootPath);
        // if (!dir.exists() || !dir.isDirectory())
        // dir.mkdir();
        // // 遍历微博 取出评论内容 进行分词
        // for (WeiboInfo wi : weiboList) {
        // List<String> contents = new LinkedList<String>();
        // contents.add(wi.getWeiboContent() + "\n");
        // contents.addAll(generateSingleWeiboText(wi.getId(), 1000));
        // String relativePath = rootPath + "/" + wi.getId() + ".txt";
        // File file = new File(relativePath);
        // writeCorpus(contents, file);
        // }
        // }
        // }
        // return new ApiResultModel(null);
    }

    private void insertWeiboRedis() throws Exception {
        Integer weiboCount = weiboInfoService.getItemCount();
        if (weiboCount == null || weiboCount.intValue() == 0)
            return;
        int pageCount = (int) Math.ceil(weiboCount / 1000d);
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        // 根据微博数量 遍历所有微博
        for (int i = 0; i < pageCount; i++) {
            // 分页取出微博
            List<WeiboInfo> weiboList = getWeiboList(i + 1, 1000);
            threadPool.execute(new ExecuteCorpus(weiboList));
            
           /* if (weiboList != null && weiboList.size() > 0) {
               
                // 遍历微博 取出评论内容 进行分词
                for (WeiboInfo wi : weiboList) {
                    List<String> contents = new LinkedList<String>();
                    contents.add(wi.getWeiboContent() + "\n");
                    contents.addAll(generateSingleWeiboText(wi.getId(), 1000));
                    String commentsCorpus = generateCorpus(contents);
                    try{
                        redisService.setCacheObject("weibo_comment_corpus:weibo:"
                                + wi.getId(), commentsCorpus);
                    }
                    catch(Exception e){
                        System.out.print(wi.getId());
                        redisService.setCacheObject("weibo_comment_corpus:weibo:"
                                + wi.getId(), commentsCorpus);
                    }
                    
                }
                //insertWeibo(weiboList);
            }*/
        }
        
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        }

    }
    private class ExecuteCorpus implements Runnable {
        private List<WeiboInfo> weiboList=null;
        public ExecuteCorpus(List<WeiboInfo> weiboList){
            this.weiboList=weiboList;
        }
        @Override
        public void run() {
            try {
                if(weiboList != null && weiboList.size() > 0)
                        Execute(weiboList);
            } catch (Exception e) {
                System.out.print(e.getMessage());
                System.out.print(e.getStackTrace());
            }
        }
        private void Execute(List<WeiboInfo> weiboList) throws Exception{
            if (weiboList != null && weiboList.size() > 0) {
                
                // 遍历微博 取出评论内容 进行分词
                for (WeiboInfo wi : weiboList) {
                    List<String> contents = new LinkedList<String>();
                    contents.add(wi.getWeiboContent() + "\n");
                    contents.addAll(generateSingleWeiboText(wi.getId(), 1000));
                    String commentsCorpus = generateCorpus(contents);
                    try{
                        redisService.setCacheObject("weibo_comment_corpus:weibo:"
                                + wi.getId(), commentsCorpus);
                    }
                    catch(Exception e){
                        System.out.print(wi.getId());
                        redisService.setCacheObject("weibo_comment_corpus:weibo:"
                                + wi.getId(), commentsCorpus);
                    }
                    
                }
                //insertWeibo(weiboList);
            }
        }
    }
    private List<TopicInfo> getTopicInfoList(int pageSize) {
        List<TopicInfo> list = new LinkedList<TopicInfo>();
        Integer topicCount = topicInfoService.getItemCount();
        int pageCount = (int) Math.ceil(topicCount / (double) pageSize);
        for (int i = 0; i < pageCount; i++) {
            List<TopicInfo> pageList = topicInfoService
                    .getList(pageSize, i + 1);
            if (pageList != null && pageList.size() > 0)
                list.addAll(pageList);
        }
        return list;
    }

    private void insertTopic(List<TopicInfo> list) {
        if (list == null)
            return;
        List<Topic> tlist = new LinkedList<Topic>();
        for (TopicInfo ti : list) {
            Topic t = new Topic() {
                {
                    setId(ti.getId());
                    setTitle(ti.getTitle());
                }
            };
            tlist.add(t);
        }
        topicService.batchInsert(tlist);
    }

    private List<String> generateSingleTopicText(int topic_id,
            int contentPageSize) throws Exception {
        List<String> results = new LinkedList<String>();
        Integer contentCount = topicCleanContentService.getItemCount(topic_id);
        int pageCount = (int) Math
                .ceil(contentCount / (double) contentPageSize);
        List<Integer>weiboExsits=new LinkedList<Integer>();
        for (int i = 0; i < pageCount; i++) {
            List<TopicCleanContent> list = topicCleanContentService.getList(
                    contentPageSize, i + 1, topic_id);
            for (TopicCleanContent content : list) {
                Integer weibo_id=content.getWeibo_id();
                if (StringUtils
                        .isNoneEmpty(content.getWeibo_content_cleansed())&&!weiboExsits.contains(weibo_id)){
                    weiboExsits.add(weibo_id);
                    results.add(content.getWeibo_content_cleansed() + "\n");
                }
                if (StringUtils.isNoneEmpty(content
                        .getComment_content_cleansed()))
                    results.add(content.getComment_content_cleansed() + "\n");
            }
        }
        return results;
    }

    private void writeCorpus(List<String> topicContents, File fileOut)
            throws Exception {
        if (topicContents == null || topicContents.size() == 0)
            return;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileOut), "UTF-8"));
        for (String content : topicContents) {
            StringBuilder sb = new StringBuilder();
            List<Term> terms = NlpAnalysis.parse(content).getTerms();
            for (Term term : terms) {
                sb.append(term.getName() + " ");
            }
            sb = sb.append("\n");
            bw.write(sb.toString());
        }
        bw.close();
    }

    private String generateCorpus(List<String> contents) {
        if (contents == null || contents.size() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (String content : contents) {
            List<Term> terms = NlpAnalysis.parse(content).getTerms();
            for (Term term : terms) {
                sb.append(term.getName() + " ");
            }
            sb = sb.append("\n");
        }
        return sb.toString();
    }

    @RequestMapping("kmeans")
    public ApiResultModel generateKMeans(
            @RequestParam(value = "topicid", required = true) int topicid,
            @RequestParam(value = "clusterNum", required = false, defaultValue = "10") int clusterNum)
            throws Exception {
        if (topicid <= 0 || clusterNum <= 0)
            return new ApiResultModel(null);
        List<List<ClusterModel>> result = null;
        List<String> contents = generateSingleTopicText(topicid, 1000);
        if (contents != null && contents.size() > 0) {
            String relativePath = "C:\\Users\\huyulinhome\\Desktop\\document_arff\\"
                    + topicid + ".arff";
            File file = new File(relativePath);
            if (!file.exists())
                file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "UTF-8"));

            Map<String, float[]> maps = loadMap();
            List<String> listWords = new LinkedList<String>();

            String header = generateHeader();
            bw.write(header);

            String body = generateBody(maps, contents, listWords);
            bw.write(body);

            bw.flush();
            bw.close();
            result = wordService.KMeans(file, null, clusterNum, listWords);

        }
        return new ApiResultModel(result);
    }

    private String generateHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("@relation contact-lenses\n");
        sb.append("\n");
        for (int i = 0; i < 200; i++) {
            sb.append("@attribute " + i + " numeric\n");
        }
        sb.append("\n");
        sb.append("@data\n");
        return sb.toString();
    }

    private String generateBody(Map<String, float[]> maps,
            List<String> contents, List<String> listWords) {
        Map<String, float[]> corpusMap = new LinkedHashMap<String, float[]>();
        List<String> results = new LinkedList<String>();
        for (String content : contents) {
            List<Term> terms = NlpAnalysis.parse(content).getTerms();
            for (Term term : terms) {
                String word = term.getName();
                if (!corpusMap.containsKey(word) && maps.containsKey(word)) {
                    float[] vector = maps.get(word);
                    corpusMap.put(word, vector);
                    listWords.add(word);
                    String lineVector = trim(
                            trim(Arrays.toString(vector), '['), ']');
                    results.add(lineVector);
                }
            }
        }

        return StringUtils.join(results, "\n");
    }

    private Map<String, float[]> loadMap() throws Exception {
        Map<String, float[]> wordMap = WordVectorHelper
                .loadModel(ResourceDict.Topic_Dict.get("cbow0"));
        return wordMap;
    }

    public static String trim(String source, char c) {
        String beTrim = String.valueOf(c);

        source = source.trim(); // 循环去掉字符串首的beTrim字符
        String beginChar = source.substring(0, 1);
        while (beginChar.equalsIgnoreCase(beTrim)) {
            source = source.substring(1, source.length());
            beginChar = source.substring(0, 1);
        }

        // 循环去掉字符串尾的beTrim字符
        String endChar = source.substring(source.length() - 1, source.length());
        while (endChar.equalsIgnoreCase(beTrim)) {
            source = source.substring(0, source.length() - 1);
            endChar = source.substring(source.length() - 1, source.length());
        }
        return source;
    }

    private List<WeiboInfo> getWeiboList(int currentPage, int pageSize) {
        List<WeiboInfo> pageList = weiboInfoService.getList(pageSize,
                currentPage);
        return pageList;
    }

    private void insertWeibo(List<WeiboInfo> list) {
        if (list == null)
            return;
        List<Weibo> tlist = new LinkedList<Weibo>();
        for (WeiboInfo wi : list) {
            Weibo t = new Weibo() {
                {
                    setId(wi.getId());
                    setTopicid(wi.getTopicid());
                    setWeiboContent(wi.getWeiboContent());
                    setReadNum(wi.getReadNum());
                    setWeiboUrl(wi.getWeibourl());
                }
            };
            tlist.add(t);
        }
        weiboService.batchInsert(tlist);
    }

    private List<String> generateSingleWeiboText(int weibo_id,
            int contentPageSize) throws Exception {
        List<String> results = new LinkedList<String>();
        Integer contentCount = weiboCleanContentService.getItemCount(weibo_id);
        int pageCount = (int) Math
                .ceil(contentCount / (double) contentPageSize);
        for (int i = 0; i < pageCount; i++) {
            List<WeiboCleanContent> list = weiboCleanContentService.getList(
                    contentPageSize, i + 1, weibo_id);
            for (WeiboCleanContent content : list) {
                if (StringUtils.isNoneEmpty(content
                        .getComment_content_cleansed()))
                    results.add(content.getComment_content_cleansed() + "\n");
            }
        }
        return results;
    }

    private void moveToDirectory(String sourcePath, String targetPath) {
        File file = new File(sourcePath);
        // get the folder list
        File[] array = file.listFiles();
        int itemCount = array.length;
        int pageCount = (int) Math.ceil(itemCount / 5000.0d);
        File rootdir = new File(targetPath);
        if (!rootdir.exists() || !rootdir.isDirectory())
            rootdir.mkdir();
        for (int i = 0; i < pageCount; i++) {
            int arrayLength = (itemCount - (i * 5000)) > 5000 ? 5000
                    : (itemCount - (i * 5000));
            File[] fileArray = new File[arrayLength];
            System.arraycopy(array, i * 5000, fileArray, 0, arrayLength);
            File dir = new File(targetPath + "\\" + i);
            if (!dir.exists() || !dir.isDirectory())
                dir.mkdir();
            for (int j = 0; j < arrayLength; j++) {
                fileArray[j].renameTo(new File(targetPath + "\\" + i + "\\"
                        + fileArray[j].getName()));
            }
        }

    }
}
