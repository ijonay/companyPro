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
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.core.util.StringUtils;
import com.zc.bean.Topic;
import com.zc.model.ApiResultModel;
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
                if (!StringUtils.isNullOrEmpty(content
                        .getWeibo_content_cleansed()))
                    results.add(content.getWeibo_content_cleansed() + "\n");
                if (!StringUtils.isNullOrEmpty(content
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
            @RequestParam(value = "topicid") Integer topicid) throws Exception {
        List<String> contents = generateSingleTopicText(topicid, 1000);
        if (contents != null && contents.size() > 0) {
            String relativePath = "C:\\Users\\huyulinhome\\Desktop\\document_arff\\"
                    + topicid + ".arff";
            File file = new File(relativePath);
            if (!file.exists())
                file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "UTF-8"));
            
            writeHeader(bw);
            Map<String, float[]> maps=loadMap();
            Map<String,Boolean>corpusExist=new LinkedHashMap<String,Boolean>();
            for (String content : contents) {
                List<Term> terms = NlpAnalysis.parse(content).getTerms();
                for(Term term:terms){
                    String word=term.getName();
                    if(!corpusExist.containsKey(word)&&maps.containsKey(word)){
                        corpusExist.put(word, true);
                        float[]vector=maps.get(word);
                    }
                }
            }
        }
        return new ApiResultModel(null);
    }
    
    private void writeHeader(BufferedWriter bw) throws IOException{
        bw.write("@relation contact-lenses\n");
        bw.write("\n");
        for (int i = 0; i < 200; i++) {
            bw.write("@attribute "+i+" numeric\n");  
        }
        bw.write("\n");
        bw.write("@data\n");
    }
    private Map<String, float[]> loadMap() throws Exception{
        Map<String, float[]> wordMap = WordVectorHelper.loadModel(ResourceDict.Topic_Dict.get("cbow0"));
        return wordMap;
    }
    
    private String join(float[] vector,String separator){
        if(vector==null||vector.length==0)return "";
        StringBuilder sb=new StringBuilder();
        for(float val:vector){
            sb.append(val);
            sb.append(separator);
        }
        return sb.toString().trim();
    }
    private String trim(String sourceString,String[]trimString){
        if(trimString==null||trimString.length==0)return sourceString;
        for(String ts:trimString){
            if(sourceString.indexOf(ts)==0){
                
            }
        }
        return "";
    }
}
