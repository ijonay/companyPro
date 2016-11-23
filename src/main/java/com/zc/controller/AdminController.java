/**
 * @title AdminController.java
 * @author xyzhuzhou/zhuzz@heptax.com
 * @date：2016年8月3日 下午6:41:45
 * @Tag 类说明
 * @Copyright 2016 知藏. All right reserved.
 */
package com.zc.controller;

import com.zc.bean.Topic;
import com.zc.model.TopicModel;
import com.zc.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    TopicService topicService;

    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }

    @RequestMapping("/redisset")
    public String redisset(ModelMap model) {

        return "admin/redisset";
    }

    @RequestMapping("/manageruser")
    public String manageruser(ModelMap model) {

        return "admin/manageruser";
    }

    @RequestMapping("/managetopic")
    public String manageTopic(
            @RequestParam(value = "keyword", required = false, defaultValue = "")
            String keyword,Model model
    ){
        if(StringUtils.isNoneBlank(keyword)){
            model.addAttribute("keyword", keyword);
            List<TopicModel> topicModelList = topicService.getTopicsByKeyword(keyword);
            if( topicModelList!=null && topicModelList.size() > 0 ){
                model.addAttribute("topicModelList", topicModelList);
            }
        }
        return "admin/managetopic";
    }

    @RequestMapping("/toaddtopic")
    public String addTopic(){
        return "admin/addtopic";
    }

    @RequestMapping(value = "/addtopic", method= RequestMethod.POST)
    public  String addTopic(
            @RequestParam("file") MultipartFile file,
            Topic topic,    Model model,
            HttpSession session ){

        try{

            if (!file.isEmpty()) {
                String orignalName = file.getOriginalFilename();
                String extension = orignalName.substring(orignalName.indexOf("."));
                String fileName = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
                fileName += extension;
                String imageDir = "/upload/topic/images/";
                String savedPath = session.getServletContext().getRealPath(imageDir);
                File savedPathDir = new File(savedPath);
                if(!savedPathDir.exists()){
                    savedPathDir.mkdirs();
                }
                String filePath = savedPath + fileName;
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream =
                        new BufferedOutputStream(new FileOutputStream(new File( filePath )));
                buffStream.write(bytes);
                buffStream.close();
                String logoImageUrlLocal = imageDir + fileName;
                topic.setLogoImgUrlLocal(logoImageUrlLocal);
            }
            topic.setManualIsApplied("1");
            String initTitle = topic.getManualTitle();

            topic.setTitle( initTitle );
            topic.setTopicUrl(topic.getManualTopicUrl());

            topic.setZhihuTitle(initTitle);
            topic.setZhihuUrl(topic.getManualZhihuUrl());

            topic.setWechatTitle(initTitle);
            topic.setWechatUrl(topic.getManualWechatUrl());

            topic.setBaiduTitle(initTitle);
            topic.setBaiduUrl(topic.getManualBaiduUrl());

            topic.setPrevailingTrend(topic.getManualPrevailingTrend());
            topic.setIntroduction( topic.getManualIntroduction() );
            topic.setEventClass( topic.getManualEventClass() );
            topic.setLogoImgUrl( topic.getManualLogoImgUrl() );

            int topicId = topicService.syncInsertTopic(topic);
            if( topicId > 0 ){
                model.addAttribute("success", "true");
                return "redirect:/admin/topicdetail/" + topicId; //redirect to the detail page
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        model.addAttribute( "topic", topic );
        model.addAttribute( "success", "false" );
        return "admin/edittopicadd";
    }

    @RequestMapping(value = "/toupdatetopic/{id}")
    public String toUpdateTopic(
            @PathVariable("id") int id, Model model
    ){
        Topic topic = topicService.get(id);
        if(topic == null){
            model.addAttribute("success","false");
            model.addAttribute("msg","没找到id为" + id + "的topic");
        }
        model.addAttribute("topic", topic);
        return "/admin/topicupdate";
    }

    @RequestMapping(value = "/updatetopic", method= RequestMethod.POST)
    public String updateTopic(
            @RequestParam("file") MultipartFile file,
            Topic topic,Model model,HttpSession session){

        try{

            if (!file.isEmpty()) {
                String orignalName = file.getOriginalFilename();
                String extension = orignalName.substring(orignalName.indexOf("."));
                String fileName = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
                fileName += extension;
                String imageDir = "/upload/topic/images/";
                String savedPath = session.getServletContext().getRealPath(imageDir);
                File savedPathDir = new File(savedPath);
                if(!savedPathDir.exists()){
                    savedPathDir.mkdirs();
                }
                String filePath = savedPath + fileName;
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream =
                        new BufferedOutputStream(new FileOutputStream(new File( filePath )));
                buffStream.write(bytes);
                buffStream.close();
                String logoImageUrlLocal = imageDir + fileName;
                topic.setLogoImgUrlLocal(logoImageUrlLocal);
            }

            int flag = 0;
            Topic topicDb = topicService.get(topic.getId());
            if(StringUtils.isNotBlank(topic.getManualTitle())){
                topicDb.setManualTitle(topic.getManualTitle());
            }
            if(StringUtils.isNotBlank(topic.getManualPrevailingTrend())){
                topicDb.setManualPrevailingTrend(topic.getManualPrevailingTrend());
            }
            if(StringUtils.isNotBlank(topic.getManualEventClass())){
                topicDb.setManualEventClass(topic.getManualEventClass());
            }
            if(StringUtils.isNotBlank(topic.getManualIntroduction())){
                topicDb.setManualIntroduction(topic.getManualIntroduction());
            }
            if(StringUtils.isNotBlank(topic.getManualLogoImgUrl())){
                topicDb.setManualLogoImgUrl(topic.getManualLogoImgUrl());
            }
            if(StringUtils.isNotBlank(topic.getManualTopicUrl())){
                topicDb.setManualTopicUrl(topic.getManualTopicUrl());
            }
            if(StringUtils.isNotBlank(topic.getManualWechatUrl())){
                topicDb.setManualWechatUrl(topic.getManualWechatUrl());
            }
            if(StringUtils.isNotBlank(topic.getManualZhihuUrl())){
                topicDb.setManualZhihuUrl(topic.getManualZhihuUrl());
            }
            if(StringUtils.isNotBlank(topic.getManualBaiduUrl())){
                topicDb.setManualBaiduUrl(topic.getManualBaiduUrl());
            }
            if(StringUtils.isNotBlank(topic.getLogoImgUrlLocal())){
                topicDb.setLogoImgUrlLocal(topic.getLogoImgUrlLocal());
            }
            if( topicService.update(topicDb) ){
                flag = 1;
            }

            if(flag > 0){//update successfully
                model.addAttribute("success", "true");
                model.addAttribute("topic", topic);
                return "redirect:/admin/topicdetail/" + topic.getId(); //redirect to the detail page
            }

        }catch (Exception e){
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
            Model model ){
        Topic topic = topicService.get(id);
        if(topic == null){
            model.addAttribute("success","false");
            model.addAttribute("msg","没找到id为" + id + "的topic");
        }
        model.addAttribute("topic", topic);
        return "admin/topicdetail";
    }

}
