/**
 * @title AdminController.java
 * @author xyzhuzhou/zhuzz@heptax.com
 * @date：2016年8月3日 下午6:41:45
 * @Tag 类说明
 * @Copyright 2016 知藏. All right reserved.
 */
package com.zc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.zc.bean.Topic;
import com.zc.model.BosonNLPModel;
import com.zc.model.TopicModel;
import com.zc.service.TopicService;
import com.zc.service.VersionInfoService;
import com.zc.utility.BosonNLP;
import com.zc.utility.HttpClientHelper;
import com.zc.utility.response.ApiResultModel;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Request;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ApplicationContext context;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    TopicService topicService;

    @Autowired
    private VersionInfoService versionInfoService;

    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }
    @RequestMapping("/bosonNLP")
    public String retuBoson() {

        return "admin/bosonNLP";
    }
    @RequestMapping(value = "/bosonNlpKeyWord")
    public String getAssociateKey(@RequestParam(value = "bosonkeyword")
    String bosonkeyword,Model model) throws UnirestException, IOException {
//        System.out.println("bosonkeyword========" + bosonkeyword);
        if ("".equals(bosonkeyword)) {
            return "";
        }
        List<BosonNLPModel> suggestKeys=null;
             BosonNLP boson=new BosonNLP();
         
                suggestKeys = boson.suggestAnalysis(bosonkeyword);
            
        model.addAttribute("suggestKeys", suggestKeys);

        return "admin/bosonNLP";


    }
    @RequestMapping("/redisset")
    public String redisset(ModelMap model) {

        return "admin/redisset";
    }

    @RequestMapping("/pathsearch")
    public String pathsearch(ModelMap model) {

        return "admin/pathsearch";
    }

    @RequestMapping("/manageruser")
    public String manageruser(ModelMap model) {

        return "admin/manageruser";
    }

    @RequestMapping("/hotspotmanager")
    public String hotspotManager(ModelMap model) {

        return "admin/hotspotmanager";
    }

    @RequestMapping("/UserActionLog")
    public String UserActionLog(ModelMap model) {

        return "admin/UserActionLog";
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public Object test() throws Exception {
        Integer topicId = 66;

        String keyword = "科比";


        String labels_url = String.format("https://www.zhihu.com/r/search?q=%s&type=topic", keyword);

        String s = Request.Get(labels_url).execute().returnContent().asString();

        JSONObject jsonObject = JSON.parseObject(s);
        JSONArray htmls = jsonObject.getJSONArray("htmls");

        LinkedHashMap<String, String> result = new LinkedHashMap<>();

        if (htmls.size() > 0) {

            Pattern pattern = Pattern.compile("<a href=\"/topic/(\\d*)\" class=\"name-link\" data-highlight>(.*?)</a>");

            htmls.forEach(p -> {
                Matcher matcher = pattern.matcher(p.toString());
                if (matcher.find()) {
                    result.put(matcher.group(1), matcher.group(2));
                }
            });

        }


        return result;
    }

    /**
     * Created by zhuzhzh .
     */
    @RequestMapping("version")
    public String version(Model model) {

        model.addAttribute("versions", versionInfoService.getColl(null));

        return "admin/version";

    }

    @RequestMapping("/managetopic")
    public String manageTopic(
            @RequestParam(value = "keyword", required = false, defaultValue = "")
                    String keyword, @RequestParam(value = "keywordTitle", required = false, defaultValue = "")
                    String keywordTitle, Model model
    ) {
        if (StringUtils.isNoneBlank(keyword)) {
            model.addAttribute("keyword", keyword);
            model.addAttribute("keywordTitle", keywordTitle);
            List<TopicModel> topicModelList = topicService.getTopicsByKeyword(keyword, keywordTitle);
            if (topicModelList != null && topicModelList.size() > 0) {
                model.addAttribute("topicModelList", topicModelList);
            }
        }

        return "admin/managetopic";
    }

    @RequestMapping("/toaddtopic")
    public String addTopic() {
        return "admin/addtopic";
    }

    @RequestMapping(value = "/addtopic", method = RequestMethod.POST)
    public String addTopic(
            @RequestParam("file") MultipartFile file,
            Topic topic, Model model,
            HttpSession session) {


        try {

            if (!file.isEmpty()) {
                String orignalName = file.getOriginalFilename();
                String extension = orignalName.substring(orignalName.indexOf("."));
                String fileName = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
                fileName += extension;
                String imageDir = "/upload/topic/images/";
                String savedPath = session.getServletContext().getRealPath(imageDir);
                File savedPathDir = new File(savedPath);
                if (!savedPathDir.exists()) {
                    savedPathDir.mkdirs();
                }
                String filePath = savedPath + fileName;
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream =
                        new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                buffStream.write(bytes);
                buffStream.close();
                String logoImageUrlLocal = imageDir + fileName;
                topic.setLogoImgUrlLocal(logoImageUrlLocal);
            }
            topic.setManualIsApplied("1");
            String initTitle = topic.getManualTitle();

            topic.setTitle(initTitle);
            topic.setTopicUrl(topic.getManualTopicUrl());

            topic.setZhihuTitle(initTitle);
            topic.setZhihuUrl(topic.getManualZhihuUrl());

            topic.setWechatTitle(initTitle);
            topic.setWechatUrl(topic.getManualWechatUrl());

            topic.setBaiduTitle(initTitle);
            topic.setBaiduUrl(topic.getManualBaiduUrl());

            topic.setPrevailingTrend(topic.getManualPrevailingTrend());
            topic.setIntroduction(topic.getManualIntroduction());
            topic.setEventClass(topic.getManualEventClass());
            topic.setLogoImgUrl(topic.getManualLogoImgUrl());

            int topicId = topicService.syncInsertTopic(topic);
            if (topicId > 0) {
                model.addAttribute("success", "true");
                return "redirect:/admin/topicdetail/" + topicId; //redirect to the detail page
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("topic", topic);
        model.addAttribute("success", "false");
        return "admin/edittopicadd";
    }

    @RequestMapping(value = "/toupdatetopic/{id}")
    public String toUpdateTopic(
            @PathVariable("id") int id, Model model
    ) {
        Topic topic = topicService.get(id);
        if (topic == null) {
            model.addAttribute("success", "false");
            model.addAttribute("msg", "没找到id为" + id + "的topic");
        }
        model.addAttribute("topic", topic);
        return "/admin/topicupdate";
    }

    @RequestMapping(value = "/updatetopic", method = RequestMethod.POST)
    public String updateTopic(
            @RequestParam("file") MultipartFile file,
            Topic topic, Model model, HttpSession session) {

        try {

            if (!file.isEmpty()) {
                String orignalName = file.getOriginalFilename();
                String extension = orignalName.substring(orignalName.indexOf("."));
                String fileName = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
                fileName += extension;
                String imageDir = "/upload/topic/images/";
                String savedPath = session.getServletContext().getRealPath(imageDir);
                File savedPathDir = new File(savedPath);
                if (!savedPathDir.exists()) {
                    savedPathDir.mkdirs();
                }
                String filePath = savedPath + fileName;
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream =
                        new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                buffStream.write(bytes);
                buffStream.close();
                String logoImageUrlLocal = imageDir + fileName;
                topic.setLogoImgUrlLocal(logoImageUrlLocal);
            }

            int flag = 0;
            Topic topicDb = topicService.get(topic.getId());
            if (StringUtils.isNotBlank(topic.getManualTitle())) {
                topicDb.setManualTitle(topic.getManualTitle());
            }
            if (StringUtils.isNotBlank(topic.getManualPrevailingTrend())) {
                topicDb.setManualPrevailingTrend(topic.getManualPrevailingTrend());
            }
            if (StringUtils.isNotBlank(topic.getManualEventClass())) {
                topicDb.setManualEventClass(topic.getManualEventClass());
            }
            if (StringUtils.isNotBlank(topic.getManualIntroduction())) {
                topicDb.setManualIntroduction(topic.getManualIntroduction());
            }
            if (StringUtils.isNotBlank(topic.getManualLogoImgUrl())) {
                topicDb.setManualLogoImgUrl(topic.getManualLogoImgUrl());
            }
            if (StringUtils.isNotBlank(topic.getManualTopicUrl())) {
                topicDb.setManualTopicUrl(topic.getManualTopicUrl());
            }
            if (StringUtils.isNotBlank(topic.getManualWechatUrl())) {
                topicDb.setManualWechatUrl(topic.getManualWechatUrl());
            }
            if (StringUtils.isNotBlank(topic.getManualZhihuUrl())) {
                topicDb.setManualZhihuUrl(topic.getManualZhihuUrl());
            }
            if (StringUtils.isNotBlank(topic.getManualBaiduUrl())) {
                topicDb.setManualBaiduUrl(topic.getManualBaiduUrl());
            }
            if (StringUtils.isNotBlank(topic.getLogoImgUrlLocal())) {
                topicDb.setLogoImgUrlLocal(topic.getLogoImgUrlLocal());
            }
            if (topicService.update(topicDb)) {
                flag = 1;
            }

            if (flag > 0) {//update successfully
                model.addAttribute("success", "true");
                model.addAttribute("topic", topic);
                return "redirect:/admin/topicdetail/" + topic.getId(); //redirect to the detail page
            }

        } catch (Exception e) {
            model.addAttribute("success", "error");
            e.printStackTrace();
        }

        model.addAttribute("success", "false");
        model.addAttribute("topic", topic);
        return "redirect:/admin/topicupdate";

    }

    @RequestMapping(value = "/topicdetail/{id}")
    public String showTopicDetail(
            @PathVariable("id") int id,
            Model model) {
        Topic topic = topicService.get(id);
        if (topic == null) {
            model.addAttribute("success", "false");
            model.addAttribute("msg", "没找到id为" + id + "的topic");
        }
        model.addAttribute("topic", topic);
        return "admin/topicdetail";
    }

    @RequestMapping(value = "/zhihupath/search")
    public String toZhihuPathSearch() {
        return "admin/zhihupath";
    }

    @RequestMapping(value = "/zhihupath/result")
    public String searchZhiHuResult(
            @RequestParam(value = "keyword", defaultValue = "")
                    String keyword,
            @RequestParam(value = "title", defaultValue = "")
                    String title, Model model
    ) {
        Objects.requireNonNull(keyword);
        Objects.requireNonNull(title);

        model.addAttribute("keyword", keyword);
        model.addAttribute("title", title);

        Topic topic = topicService.getTopicByTitle(title);
        if (topic == null || StringUtils.isBlank(topic.getKeywords())) {
            model.addAttribute("topicKeywords", "无");
            model.addAttribute("topicTitle", "未找到");
        } else {
            model.addAttribute("topicKeywords", topic.getKeywords());
            model.addAttribute("topicTitle", topic.getTitle());
        }

        List<String> zhiHuTopicsList = HttpClientHelper.searchZhiHuTopics(keyword);

        List<String> childrenTopicNameList = new ArrayList<String>();
        if(!zhiHuTopicsList.isEmpty()){
            model.addAttribute("zhiHuTopicsList", zhiHuTopicsList.toString());
            String theFirstTopic = zhiHuTopicsList.get(0);
            model.addAttribute( "zhiHuFirstTopic",  theFirstTopic);
            childrenTopicNameList = topicService.getChildrenTopicNames(theFirstTopic);
            if(childrenTopicNameList.isEmpty()){
                childrenTopicNameList.add( theFirstTopic );
            }
        }

        if(!childrenTopicNameList.contains(keyword)){
            childrenTopicNameList.add(keyword);
        }
        model.addAttribute("childrenTopicNames", childrenTopicNameList.toString());


        if( !childrenTopicNameList.isEmpty() && Objects.nonNull(topic)  ){

            childrenTopicNameList = childrenTopicNameList.stream().map(String :: trim).collect(Collectors.toList());

            String topicKeywordStr = topic.getKeywords();
            String[] tkwArray = topicKeywordStr.split(",");
            List<String> tkwList = new ArrayList<String>(Arrays.asList(tkwArray));

            List<String> contentRepeatedWordList = topicService.getRepeatedWordList(tkwList, childrenTopicNameList);

            List<String> contentSimilarWordList = topicService.getSimilarWords(tkwList, childrenTopicNameList);

            if( Objects.nonNull(contentSimilarWordList) && !contentSimilarWordList.isEmpty() ){
                model.addAttribute("contentSimilarWordList", contentSimilarWordList.toString());
            }

            if ( !contentRepeatedWordList.isEmpty() ) {
                model.addAttribute("contentRepeatedWordList", contentRepeatedWordList.toString());
            }

        }

        List<String> neighborWordsList = topicService.getTopicNeighborWords(topic, 20);
        model.addAttribute("neighborWordsList", neighborWordsList.toString());

        List<String> neighborRepeatedWordList = topicService.getRepeatedWordList(neighborWordsList, childrenTopicNameList);
        model.addAttribute("neighborRepeatedWordList", neighborRepeatedWordList.toString());

        List<String> neighborSimilarWordList = topicService.getSimilarWords(neighborWordsList, childrenTopicNameList);
        model.addAttribute("neighborSimilarWordList", neighborSimilarWordList.toString());

        List<String> titleWordsList = topicService.getTopicTitleKeywords(topic.getId());
        model.addAttribute("titleWordsList", titleWordsList.toString());

        List<String> titleRepeatedWordList = topicService.getRepeatedWordList(titleWordsList, childrenTopicNameList);
        model.addAttribute("titleRepeatedWordList", titleRepeatedWordList.toString());

        List<String> titleSimilarWordList = topicService.getSimilarWords(titleWordsList, childrenTopicNameList);
        model.addAttribute("titleSimilarWordList", titleSimilarWordList.toString());

        return "admin/zhihupath";
    }

}
