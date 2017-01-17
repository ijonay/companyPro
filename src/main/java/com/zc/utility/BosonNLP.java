package com.zc.utility;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.zc.model.BosonNLPModel;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class BosonNLP {

    private final static Logger logger = LoggerFactory.getLogger(BosonNLP.class);


    public List<BosonNLPModel> suggestAnalysis(String keyword) throws Exception {

        String SENTIMENT_URL = "http://api.bosonnlp.com/suggest/analysis";

//        JSONArray objects = new JSONArray();
//        objects.add(keyword);

        String body = new JSONArray(new String[]{keyword}).toString();

//        String body = objects.toJSONString();

        body = body.substring(1, body.length() - 1);

//        String keyStr = Request.Post(SENTIMENT_URL)
//                .addHeader("Accept", "application/json")
//                .addHeader("X-Token", "dQV9NL2m.11364.qlyiwcqi8S9P")
//                .bodyString(body, ContentType.DEFAULT_TEXT)
//                .execute().returnContent().asString();


        HttpResponse<JsonNode> jsonResponse = Unirest.post(SENTIMENT_URL)
                .header("Accept", "application/json")
                .header("X-Token", "dQV9NL2m.11364.qlyiwcqi8S9P")
                .body(body)
                .asJson();
        String keyStr = jsonResponse.getBody().toString();


        com.alibaba.fastjson.JSONArray jsonArray = JSON.parseArray(keyStr);

        List<BosonNLPModel> listboson = new ArrayList();

        if (Objects.nonNull(jsonArray))
            for (int i = 0; i < jsonArray.size(); i++) {
                com.alibaba.fastjson.JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                String s = jsonArray1.get(0).toString();
                String s1 = jsonArray1.get(1).toString();
                listboson.add(new BosonNLPModel(Double.parseDouble(s), s1));
            }

//        keyStr = keyStr.substring(1, keyStr.length() - 1);
//
//        String[] split = keyStr.split("],");
//        List<BosonNLPModel> listboson = new ArrayList();
//        for (int i = 0; i < split.length; i++) {
//            BosonNLPModel model = new BosonNLPModel();
//            split[i] = split[i].substring(1);
//            String[] spkey = split[i].split(",");
//            model.setSimilarDegree(Double.parseDouble(spkey[0]));
//            model.setKeyWord(spkey[1].substring(1, spkey[1].length() - 3));
//            listboson.add(model);
//        }
        return listboson;
    }
}
