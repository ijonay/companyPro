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

    private String contentDir = "topictext";
    private String corpusDir = "topiccorpus";
    private String resourceRootPath = this.getClass().getResource("/")
            .getPath();

    @RequestMapping("Corpus")
    public ApiResultModel generateCorpus() throws Exception {
        // 生成文件
        List<TopicInfo> list = getTopicInfoList(1000);
        //插入数据库
        insertTopic(list);
        for (TopicInfo item : list) {
            generateSingleTopicText(item.getId(), 1000);
        }

        TestWordSeg seg = new TestWordSeg();
        List<File> files = ZCFile.readResourceFile(contentDir, "txt");
        for (File file : files) {
            String relativePath = corpusDir + "/" + file.getName();
            seg.parse_inputfile(file.getAbsolutePath(), resourceRootPath
                    + relativePath);
            // uploadFile(resourceRootPath + relativePath, file.getName());
        }
        // 清空文件夹
        cleanTopicText();
        return new ApiResultModel(null);
    }
    
//    @RequestMapping("loadmapping")
//    public ApiResultModel LoadMapping() throws Exception {
//        
//        return new ApiResultModel(null);
//    }
//    private  void loadTopicMap() {
//        try {
//            List<Topic> list =getTopicList(1000);
//            for (Topic topic : list) {
//                float[] topic_vectors =wordService.getTopicVector(file);
//                topicMap.put(file.getName(), topic_vectors);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    private void cleanTopicText() {
        try {
            List<File> list = ZCFile.readResourceFile(contentDir, "txt");
            list.stream().forEach(f -> f.deleteOnExit());
        } catch (IOException e) {
            e.printStackTrace();
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

    private void generateSingleTopicText(int topic_id, int contentPageSize)
            throws Exception {
        Integer contentCount = cleanContentService.getItemCount(topic_id);
        int pageCount = (int) Math
                .ceil(contentCount / (double) contentPageSize);
        String relativePath = contentDir + "/" + topic_id + ".txt";
        File file = new File(resourceRootPath + relativePath);
        if (file.exists())
            file.delete();
        file.createNewFile();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "UTF-8"));
        for (int i = 0; i < pageCount; i++) {
            List<CleanContent> list = cleanContentService.getList(
                    contentPageSize, i + 1, topic_id);
            for (CleanContent content : list) {
                if (!StringUtils.isNullOrEmpty(content
                        .getWeibo_content_cleansed()))
                    bw.write(content.getWeibo_content_cleansed() + "\n");
                if (!StringUtils.isNullOrEmpty(content
                        .getComment_content_cleansed()))
                    bw.write(content.getComment_content_cleansed() + "\n");
            }
        }
        bw.close();
    }

    private void uploadFile(String filePath, String fileName) {
        String targetPath = "topiccorpus/" + fileName;
        ZC_SFTPClient.uploadFile(filePath, targetPath);
    }
    private  List<Topic> getTopicList(int pageSize) {
        List<Topic> list = new LinkedList<Topic>();
        Integer topicCount = topicService.getItemCount();
        int pageCount = (int) Math.ceil(topicCount / (double) pageSize);
        for (int i = 0; i < pageCount; i++) {
            List<Topic> pageList = topicService
                    .getList(pageSize, i + 1);
            if (pageList != null && pageList.size() > 0)
                list.addAll(pageList);
        }
        return list;
    }
    public class TestWordSeg {
        public List<String> input_lines, output_lines;

        public void read_input_file(String input_file_name) {
            File file = new File(input_file_name);
            String temp_str = "";

            input_lines = new ArrayList<String>();

            try {

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file), "UTF-8"));

                // dis.available() returns 0 if the file does not have more
                // lines.
                while ((temp_str = br.readLine()) != null) {
                    // this statement reads the line from the file and print it
                    // to the console.
                    // temp_str = dis.readLine();
                    input_lines.add(temp_str);
                    // System.out.println(temp_str);
                }

                // dispose all the resources after using them.
                System.out.println("Total number of lines: "
                        + input_lines.size());
                br.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void write_output_file(String output_file_name) {
            File outfile = new File(output_file_name);
            String temp_str = "";

            try {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(outfile), "UTF-8"));
                for (String str : output_lines) {
                    bw.write(str);
                    bw.write("\n");
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void parse_inputfile(String input_file_name,
                String output_file_name) {
            File infile = new File(input_file_name);
            File outfile = new File(output_file_name);
            String temp_str = "";
            String parsed_comment = "";

            try {

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(infile), "UTF-8"));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(outfile), "UTF-8"));

                // dis.available() returns 0 if the file does not have more
                // lines.
                while ((temp_str = br.readLine()) != null) {
                    // this statement reads the line from the file and print it
                    // to the console.
                    // temp_str = dis.readLine();
                    // input_lines.add(temp_str);
                    // System.out.println(temp_str);
                    parsed_comment = "";

                    List<Term> terms = NlpAnalysis.parse(temp_str).getTerms();
                    for (Term term : terms) {
                        parsed_comment = parsed_comment + term.getName() + " ";
                    }

                    bw.write(parsed_comment);
                    bw.write("\n");

                }

                // dispose all the resources after using them.
                // System.out.println("Total number of lines: " +
                // input_lines.size());
                br.close();
                bw.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // public static void main(String[] args) {
        // // TODO Auto-generated method stub
        // // 1. read input file name and output file name
        //
        // // String input_file_name = "����Ӱ��_ָ����3.txt";
        // // String output_file_name = "Douban.Ring3.parsed.txt";
        //
        // String input_file_name = args[0];
        // String output_file_name = args[1];
        //
        // /***
        // // 2. read input file content
        // read_input_file(input_file_name);
        //
        // // 3. parse the input file and write the result to the output file.
        // output_lines = new ArrayList<String>();
        //
        // for (String one_comment : input_lines){
        // String parsed_comment = "";
        // List<Term> terms = NlpAnalysis.parse(one_comment).getTerms();
        // for (Term term : terms){
        // parsed_comment = parsed_comment + term.getName() + " ";
        // }
        // output_lines.add(parsed_comment);
        // //System.out.println(output_lines.get(output_lines.size()-1));
        // }
        //
        // write_output_file(output_file_name);
        // ***/
        // parse_inputfile(input_file_name, output_file_name);
        //
        // /******
        // String one_line =
        // "����ģʽ���Ǵ�ͳ����ʽ����������Ϸ���ƽ������ĸ߳����ڶ��θ������������⣬�õ�Ӱ�ھ��ʵĹ����ﻹ�������˿����ھ����ȣ���Ȼ���ֵķ�ʽ�����⣬���㵽��ֹ����Ȼ�����붯��������������8�ַǳ��٣��ⲿ���ĳɹ����������������ں����빤ҵ��������˻��Ƕ�������ҫ�ü����ˡ� ";
        // List<Term> terms = NlpAnalysis.parse(one_line).getTerms();
        // for (Term one_term : terms){
        // System.out.print(one_term.getName() + " ");
        // }
        // System.out.println();
        //
        //
        // UserDefineLibrary.insertWord("����ʽ", "userDefine", 1000);
        //
        // String str =
        // "��������Ͻ���������ë�� ���ǿ���Ĥ���ʹ�������ܳ�һ������� ����ë���޸��Ŀ������� ��ݮ����ʷ��������û�� ���Ͳ��Ӳ����ɫ��Ƥ�����ǽ����� ����ʹ�ð�ȫ�����ı�ͬ������С�嵽ʮ�� 28������ӿ������ǵ���β��"
        // ;
        //
        // System.out.println(BaseAnalysis.parse(str));
        // System.out.println(ToAnalysis.parse(str));
        // System.out.println(DicAnalysis.parse(str));
        // System.out.println(IndexAnalysis.parse(str));
        // System.out.println(NlpAnalysis.parse(str));
        // *******/
        // }
    }
}
