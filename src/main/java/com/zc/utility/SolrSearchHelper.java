package com.zc.utility;/**
 * Created by xyzhuzhou on 2017/1/17 0017 11:23:37.
 */

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by xyzhuzhou on 2017/1/17 0017 11:23:37.
 */
public class SolrSearchHelper {


    public static <T> List<T> query(SolrQuery query, T t) {


        HttpSolrClient solrClient = new HttpSolrClient(getServerUrl());

        solrClient.setRequestWriter(new BinaryRequestWriter());

        try {

            QueryResponse response = solrClient.query(query);

            SolrDocumentList results = response.getResults();

            List<T> result = ConvertModelList(results, t);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static <T> List<T> ConvertModelList(SolrDocumentList docs, T t) {

        List<T> result = new ArrayList<>();

        docs.forEach(p -> {
            T model = ConvertModel(p, t);
            if (Objects.nonNull(model)) result.add(model);
        });

        return result;
    }


    public static <T> T ConvertModel(SolrDocument doc, T t) {

        try {

            t = (T) t.getClass().newInstance();

        } catch (InstantiationException | IllegalAccessException e1) {

        }
        boolean hasResult = false;
        for (Field field : t.getClass().getDeclaredFields()) {

            if (!doc.containsKey(field.getName())) continue;

            Object val = doc.get(field.getName());

            try {
                ReflectHelper.setValueByFieldName(t, field.getName(), val);
                hasResult = true;
            } catch (Exception ex) {
            }

        }
        return hasResult ? t : null;
    }

    private static String getServerUrl() {
        return PropertyHelper.getValue(Constant.CONFIG_PROPERTIES, Constant.SOLR_URL);

    }


}
