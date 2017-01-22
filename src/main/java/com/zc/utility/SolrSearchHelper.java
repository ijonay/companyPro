package com.zc.utility;/**
 * Created by xyzhuzhou on 2017/1/17 0017 11:23:37.
 */

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.AnalysisParams;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.common.util.SimpleOrderedMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by xyzhuzhou on 2017/1/17 0017 11:23:37.
 */
public class SolrSearchHelper {


    public static SolrDocumentList query(SolrQuery query) throws Exception {

        HttpSolrClient solrClient = new HttpSolrClient(getServerUrl());

        solrClient.setRequestWriter(new BinaryRequestWriter());

        QueryResponse response = solrClient.query(query);

        return response.getResults();

    }

    public static <T> List<T> query(SolrQuery query, T t) {

        try {

            SolrDocumentList results = query(query);

            List<T> result = ConvertModelList(results, t);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> List<T> ConvertModelList(SolrDocumentList docs, T t) {

        List<T> result = new ArrayList<>();

        for (int i = 0; i < docs.size(); i++) {
            SolrDocument p = docs.get(i);
            T model = ConvertModel(p, t);
            if (Objects.nonNull(model)) result.add(model);
        }
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

    public static List<String> getSolrTerms(String queryTerm){
        try{
            List<String> termList = new ArrayList<String>();
            String url = getServerUrl();
            HttpSolrClient solrClient = new HttpSolrClient(url);
            SolrQuery query = new SolrQuery();
            query.add(CommonParams.QT, "/analysis/field");
            query.add(AnalysisParams.FIELD_VALUE, queryTerm);
            query.add(AnalysisParams.FIELD_NAME, "title_ik");
            QueryResponse response = solrClient.query(query);
            NamedList<Object> analysis =  (NamedList<Object>) response.getResponse().get("analysis");
            NamedList<Object> field_names =  (NamedList<Object>) analysis.get("field_names");
            NamedList<Object> title_ik = (NamedList<Object>) field_names.get("title_ik");
            NamedList<Object> index = (NamedList<Object>) title_ik.get("index");
            List<SimpleOrderedMap<String>> list =  (ArrayList<SimpleOrderedMap<String>>) index.get("org.wltea.analyzer.lucene.IKTokenizer");
            list.forEach( obj ->  termList.add( obj.get("text") ) );
            return termList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
