package com.zc.utility;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangchengli on 2016/12/20.
 */
public class HttpClientHelper {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String ZHIHU_SEARCH_URL = "https://www.zhihu.com/search?type=topic&q=";

    public static List<String> searchZhiHuTopics(String keyword){
        List<String> list = new ArrayList<String>();
        try{

            String url = ZHIHU_SEARCH_URL + URLEncoder.encode(keyword, "UTF-8");

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            // add request header
            request.addHeader("User-Agent", USER_AGENT);

            HttpResponse response = client.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == 200){
                BufferedReader rd = new BufferedReader(
                            new InputStreamReader( response.getEntity().getContent() ,"UTF-8")
                );
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                String htmlStr  =  result.toString();
                Document doc = Jsoup.parse(htmlStr);
                Elements links = doc.select(".name-link");
                for(int i=0; i<links.size(); i++){
                    Element topic = links.get(i);
                    list.add( topic.html() ) ;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }


}
