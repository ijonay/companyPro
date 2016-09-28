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

import com.zc.utility.Constant;
import com.zc.utility.PropertyHelper;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zc.bean.Topic;
import com.zc.utility.response.ApiResultModel;
import com.zc.model.ClusterModel;
import com.zc.service.TopicService;
import com.zc.service.WordService;
import com.zc.tempbean.CleanContent;
import com.zc.tempbean.TopicInfo;
import com.zc.tempservice.CleanContentService;
import com.zc.tempservice.TopicInfoService;
import com.zc.utility.ResourceDict;
import com.zc.utility.WordVectorHelper;

@RestController
@RequestMapping("/api/")
public class CorpusApi {
    @Autowired
    private CleanContentService cleanContentService;
    @Autowired
    private TopicInfoService topicInfoService;
    @Autowired
    private TopicService topicService;

    @Autowired
    private WordService wordService;

    private String corpusDir = "topiccorpus";
    private String resourceRootPath = this.getClass().getResource("/")
            .getPath();

    @RequestMapping("Corpus")
    public ApiResultModel generateCorpus() throws Exception {
        // 生成文件
        List<TopicInfo> list = getTopicInfoList(1000);
        // 插入数据库
        insertTopic(list);
        for (TopicInfo item : list) {
            List<String> contents = generateSingleTopicText(item.getId(), 1000);
            String relativePath = resourceRootPath + corpusDir + "/"
                    + item.getId() + ".txt";
            File file = new File(relativePath);
            writeCorpus(contents, file);
        }
        return new ApiResultModel(null);
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
        Integer contentCount = cleanContentService.getItemCount(topic_id);
        int pageCount = (int) Math
                .ceil(contentCount / (double) contentPageSize);
        for (int i = 0; i < pageCount; i++) {
            List<CleanContent> list = cleanContentService.getList(
                    contentPageSize, i + 1, topic_id);
            for (CleanContent content : list) {
                if (StringUtils.isNoneEmpty(content
                        .getWeibo_content_cleansed()))
                    results.add(content.getWeibo_content_cleansed() + "\n");
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

    @RequestMapping("kmeans")
    public ApiResultModel generateKMeans(
            @RequestParam(value = "topicid", required = true) int topicid,
            @RequestParam(value = "clusterNum", required = false, defaultValue = "10") int clusterNum) throws Exception {
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

    private String generateBody(Map<String, float[]> maps, List<String> contents, List<String> listWords) {
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
                    String lineVector = trim(trim(Arrays.toString(vector), '['), ']');
                    results.add(lineVector);
                }
            }
        }

        return StringUtils.join(results, "\n");
    }

    private Map<String, float[]> loadMap() throws Exception {
//        Map<String, float[]> wordMap = WordVectorHelper.loadModel(ResourceDict.Topic_Dict.get("cbow0"));
        Map<String, float[]> wordMap = WordVectorHelper
                .loadModel(PropertyHelper.getValue(Constant.CONFIG_PROPERTIES, Constant.MODEL_BIN));
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


}
