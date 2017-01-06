package com.zc.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.json.JSONArray;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.zc.model.BosonNLPModel;
import com.zc.model.usermodel.UserSessionModel;


public class BosonNLP {

    private final static Logger logger = LoggerFactory.getLogger(BosonNLP.class);
   
 
    public  List<BosonNLPModel> suggestAnalysis(String keyword) throws UnirestException, IOException{
      String  SENTIMENT_URL ="http://api.bosonnlp.com/suggest/analysis";
        String body = new JSONArray(new String[]{keyword}).toString();
        body=body.substring(1, body.length()-1);
        HttpResponse<com.mashape.unirest.http.JsonNode> jsonResponse = Unirest.post(SENTIMENT_URL)
                .header("Accept", "application/json")
                .header("X-Token", "dQV9NL2m.11364.qlyiwcqi8S9P")
                .body(body)
                .asJson();
         String keyStr = jsonResponse.getBody().toString();
         keyStr=keyStr.substring(1, keyStr.length()-1);
         String[] split = keyStr.split("],");
         List<BosonNLPModel> listboson=new ArrayList();
         for (int i = 0; i < split.length; i++) {
             BosonNLPModel model=new BosonNLPModel();
            split[i]=split[i].substring(1);
            String[] spkey = split[i].split(",");
            model.setSimilarDegree(Double.parseDouble(spkey[0]));
            model.setKeyWord(spkey[1].substring(1, spkey[1].length()-3));
            listboson.add(model);
        }
        return listboson;
    }
}
