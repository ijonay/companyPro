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

    @RequestMapping("/addtopic")
    public String addTopic(){
        return "admin/addtopic";
    }

    @RequestMapping(value = "/submittopic", method= RequestMethod.POST)
    public String addTopic(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "introducation",required = false, defaultValue = "")
            String introducation,
            @RequestParam(value = "action",required = false, defaultValue = "") String action,
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
                String logoImageUrl = imageDir + fileName;
                topic.setLogoImgUrl(logoImageUrl);
            }

            int flag = 0;
            if(StringUtils.equals("update", action)){
                Topic topicDb = topicService.get(topic.getId());
                if(StringUtils.isNotBlank(topic.getTitle())){
                    topicDb.setTitle(topic.getTitle());
                }
                if(StringUtils.isNotBlank(topic.getPrevailingTrend())){
                    topicDb.setPrevailingTrend(topic.getPrevailingTrend());
                }
                if(StringUtils.isNotBlank(topic.getEventClass())){
                    topicDb.setEventClass(topic.getEventClass());
                }
                if(StringUtils.isNotBlank(introducation)){
                    topicDb.setIntroduction(introducation);
                }
                if(StringUtils.isNotBlank(topic.getLogoImgUrl())){
                    topicDb.setLogoImgUrl(topic.getLogoImgUrl());
                }
                if(StringUtils.isNotBlank(topic.getTopicUrl())){
                    topicDb.setTopicUrl(topic.getTopicUrl());
                }
                if(StringUtils.isNotBlank(topic.getWechatUrl())){
                    topicDb.setWechatUrl(topic.getWechatUrl());
                }
                if(StringUtils.isNotBlank(topic.getZhihuUrl())){
                    topicDb.setZhihuUrl(topic.getZhihuUrl());
                }
                if(StringUtils.isNotBlank(topic.getBaiduUrl())){
                    topicDb.setBaiduUrl(topic.getBaiduUrl());
                }
                if( topicService.update(topicDb) ){
                    flag = 1;
                }
            }else{
                if(StringUtils.isNotBlank(introducation)){
                    topic.setIntroduction(introducation);
                }
                flag = topicService.add(topic);
            }

            if(flag > 0){//update or add successfully
                model.addAttribute("topic", topic);
                model.addAttribute("success", "true");
                return "redirect:/admin/topicdetail/" + topic.getId(); //redirect to the detail page
            }else{//update or add failed
                model.addAttribute("success", "false");
                if(StringUtils.equals(action,"update")){
                    return "redirect:/admin/topicdetail/" + topic.getId() + "?action=update";
                }else{
                    return "redirect:/admin/topicupdate";//to the add page
                }
            }

        }catch (Exception e){
            model.addAttribute("success", "error");
            e.printStackTrace();
        }

        if(StringUtils.equals(action,"update")){
            return "redirect:/admin/topicdetail/" + topic.getId() + "?action=update";
        }else{
            return "redirect:/admin/topicupdate";//to the add page
        }
    }

    @RequestMapping(value = "/topicdetail/{id}")
    public String showTopicDetail(
            @PathVariable("id") int id,
            @RequestParam(value = "action",required = false, defaultValue = "") String action,
            Model model
    ){
        Topic topic = topicService.get(id);
        if(topic == null){
            model.addAttribute("success","false");
            model.addAttribute("msg","没找到id为" + id + "的topic");
        }
        model.addAttribute("topic", topic);
        if(StringUtils.equals(action,"update")){
            return "admin/topicupdate";
        }
        return "admin/topicdetail";
    }

}
