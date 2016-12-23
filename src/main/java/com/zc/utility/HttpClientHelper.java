package com.zc.utility;/**
 * Created by xyzhuzhou on 2016/12/22 0022 19:11:20.
 */

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;

/**
 * Created by xyzhuzhou on 2016/12/22 0022 19:11:20.
 */
public class HttpClientHelper {

    public static HttpEntity get(String url) throws IOException {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            return response.getEntity();
        }

    }

    public static HttpEntity post(String url) throws IOException {
        return post(url, null);
    }

    public static HttpEntity post(String url, List<NameValuePair> nvps) throws IOException {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPost httpPost = new HttpPost(url);

            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            CloseableHttpResponse response = httpClient.execute(httpPost);

            return response.getEntity();
        }
    }

}

