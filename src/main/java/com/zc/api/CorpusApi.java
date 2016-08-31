/**
 * @title TestApi.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月16日 下午12:02:40
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.zc.utility.WordSeg;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.zc.utility.ZCFile;
import com.zc.utility.ZC_SFTPClient;

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
            String relativePath =resourceRootPath+ corpusDir + "/" + item.getId()+".txt";
            File file=new File(relativePath);
            writeCorpus(contents,file);
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

    private void writeCorpus(List<String>topicContents,File fileOut) throws Exception{
        if(topicContents==null||topicContents.size()==0) return;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileOut), "UTF-8"));
        for(String content:topicContents){
            StringBuilder sb=new StringBuilder();
            List<Term> terms = NlpAnalysis.parse(content).getTerms();
            for (Term term : terms) {
                sb.append(term.getName() + " ");
            }
            sb=sb.append("\n");
            bw.write(sb.toString());
        }
        bw.close();
    }
}
