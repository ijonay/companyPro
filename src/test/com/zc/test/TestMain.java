package com.zc.test;

import com.zc.BaseTest;
import com.zc.service.PathService;
import com.zc.service.RedisService;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.cloud.SolrZkClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.annotation.SystemProfileValueSource;

import java.util.List;

/**
 * Created by 张镇强 on 2016/9/19 12:19.
 */
public class TestMain extends BaseTest {
    @Autowired
    PathService pathService;
    @Autowired
    RedisService redisService;

    @Test
    public void testSolrJ() {
        String url = "http://192.168.1.101/solr/topic_analysis";
        HttpSolrServer server = new HttpSolrServer(url);
        server.setRequestWriter(new BinaryRequestWriter());
        SolrQuery query = new SolrQuery();
        query.setQuery("王宝强")
                .addFilterQuery("唐人")
                .setStart(0)
                .setRows(3);
        try {
            QueryResponse response = server.query(query);
            System.out.println(response.getResults().getNumFound());
            int index = 1;
            for (SolrDocument r : response.getResults()) {
                System.out.println("index:" + index++);
                System.out.println("id:" + r.getFieldValue("id"));
                System.out.println("weibo_content:" + r.getFieldValue("weibo_content"));
                System.out.println("----------------");
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Rollback(false)
    public void testImpotDatatoRedis() {
        System.out.println("------------------------testtesttest-----------------");
        redisService.add();
    }
}
