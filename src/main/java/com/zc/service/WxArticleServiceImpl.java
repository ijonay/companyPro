package com.zc.service;

import com.zc.bean.Topic;
import com.zc.dao.TopicDao;
import com.zc.dao.WxArticleFieldMapper;
import com.zc.dao.WxArticleInfoMapper;
import com.zc.model.TopicModel;
import com.zc.model.WxArticleField;
import com.zc.model.WxArticleInfoModel;
import com.zc.model.solrmodel.ArticleModel;
import com.zc.model.solrmodel.ArticleSearchModel;
import com.zc.utility.CommonHelper;
import com.zc.utility.SolrSearchHelper;
import com.zc.utility.WordVectorHelper;
import com.zc.utility.page.Page;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.ibatis.annotations.Param;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhangchengli on 2017/1/17.
 */
@Service
public class WxArticleServiceImpl implements WxArticleService {

    @Autowired
    public WxArticleInfoMapper wxArticleInfoMapper;

    @Autowired
    public TopicService topicService;

    @Autowired
    public WordService wordService;

    @Autowired
    private TopicDao topicDao;

    @Override
    public List<WxArticleInfoModel> getWxArticleInfoList(
            @Param("pageSize") Integer pageSize,
            @Param("rowStart") Integer rowStart) {
        return wxArticleInfoMapper.getWxArticleList(pageSize, rowStart);
    }


    @Override
    public List<LinkedHashMap<String, Object>> getStructSearch(List<String> keys) {

        Objects.requireNonNull(keys);

        String searchKeys = "title_mmseg:" + String.join(" or title_mmseg:", keys);

        SolrQuery solrQuery = new SolrQuery();
        solrQuery
                .setQuery(searchKeys)
                .setStart(0)
                .setRows(1000)
                .setSort("product(relative_score,query($q))", SolrQuery.ORDER.desc)
                .set("fl", "id,title_mmseg,title,article_url,titleStruct,account_id,account_name,read_num," +
                        "articleTags,articleType" +
                        ",structure_type,relative_score,keywords," +
                        "topicId,topicTitle," +
                        //"content,raw_content," +
                        "publish_time,articleTags,score");

        List<ArticleModel> articles = SolrSearchHelper.query(solrQuery, new ArticleModel());

        LinkedHashMap<String, List<Float>> structMap = new LinkedHashMap<>();

        //统计标题结构数量
        articles.forEach(p -> {

            if (Objects.isNull(p.getTitleStruct())) return;

            List<Float> structCountList = new ArrayList<>();

            for (String s : (p.getTitleStruct() + "").split(",")) {

                if (structMap.containsKey(s)) {
                    structCountList = structMap.get(s);
                }
                structCountList.add(p.getScore() * p.getRelative_score());
                structMap.put(s, structCountList);
            }

        });

        LinkedHashMap<String, Float> structAvgMap = new LinkedHashMap<>();

        structMap.forEach((k, v) -> {
            Float total = (float) 0;
            for (Float i : v) {
                total += i;
            }
            structAvgMap.put(k, total / v.size());
        });

        List<Map.Entry<String, Float>> structTopN = structAvgMap.entrySet().stream().collect(Collectors.toList());

        structTopN.sort((s1, s2) -> s2.getValue().compareTo(s1.getValue()));

        List<LinkedHashMap<String, Object>> result = new ArrayList<>();

        //取出平均值最高的3种结构
        int topN = 3;
        for (int i = 0; i < structTopN.size() && i < topN; i++) {

            Map.Entry<String, Float> structItem = structTopN.get(i);
            LinkedHashMap<String, Object> structResult = new LinkedHashMap<>();

            List<ArticleModel> collect = articles.stream().filter(p -> p.getTitleStruct().contains(structItem.getKey
                    ()))
                    .limit(50).collect(Collectors.toList());

            //默认前3个进行分词操作
            for (int j = 0; j < collect.size(); j++) {//&& j < 3
                collect.get(j).setTitle_participle(ToAnalysis.parse(collect.get(j).getTitle()).getTerms()
                        .stream().map(p -> p.getName()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));

            }

            structResult.put("name", structItem.getKey());
            structResult.put("data", collect);

            result.add(structResult);


        }
        return result;
    }

    @Override
    public Page getBySearch(ArticleSearchModel searchModel) throws Exception {

//        searchModel.setKeywords("春节 回家 过年");
//        searchModel.setPublishDate(PublishDateEnum.INSEVENDAYS);
//        //searchModel.setStructTypes(Arrays.asList("互动类,视频类".split(",")));
//        searchModel.setTags(Arrays.asList("时事,民生,美体".split((","))));

        List<String> searchList = new ArrayList<>();

        if (Objects.nonNull(searchModel.getKeywords())) {
            searchList.add("(title:" + String.join(" OR title:", searchModel.getKeywords().split(" " +
                    "")) + ")");
        }

        if (Objects.nonNull(searchModel.getTags()) && searchModel.getTags().size() > 0) {

            searchList.add(" (articleTags:" + String.join(" OR articleTags:", searchModel.getTags()) + ")");
        }

        if (Objects.nonNull(searchModel.getStructTypes()) && searchModel.getStructTypes().size() > 0) {

            searchList.add(" (structure_type:" + String.join(" OR structure_type:", searchModel.getStructTypes()) +
                    ")");
        }

        String searchKeys = String.join(" AND ", searchList);

        SolrQuery solrQuery = new SolrQuery();


        if (Objects.nonNull(searchModel.getPublishDate())) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00'Z'");

            Calendar calendar = Calendar.getInstance();

            switch (searchModel.getPublishDate()) {
                case TODAY:

                    break;
                case INSEVENDAYS:
                    calendar.add(Calendar.DATE, -7);
                    break;
                case INTHIRTYDAYS:
                    calendar.add(Calendar.DATE, -30);
                    break;
            }

            solrQuery.set("fq", "publish_time:[" + simpleDateFormat.format(calendar.getTime()) + " TO NOW ]");
        }

        if (Objects.nonNull(searchKeys)) {
            solrQuery.setQuery(searchKeys);
        }

        solrQuery
                .setStart(searchModel.getStartIndex())
                .setStart(searchModel.getStartIndex())
                .setRows(searchModel.getPageSize())
                .set("fl", "id,title_mmseg,title,article_url,titleStruct,account_id,account_name,read_num," +
                        "articleTags," +
                        "articleType" +
                        ",structure_type,relative_score,keywords," +
                        "topicId,topicTitle," +
                        //"content,raw_content," +
                        "publish_time,articleTags,score");


        SolrDocumentList query = SolrSearchHelper.query(solrQuery);

        List<ArticleModel> articleModels = SolrSearchHelper.ConvertModelList(query, new ArticleModel());

        Page page = new Page();
        page.setPageSize(searchModel.getPageSize());
        page.setTotalCount(Integer.parseInt(query.getNumFound() + ""));
        page.setPageNumber(searchModel.getPageNumber());
        page.data(articleModels);

        return page;
    }

    @Autowired
    public WxArticleFieldMapper wxArticleFieldMapper;

    @Override
    public List<WxArticleField> getWxArticleFields() {
        return wxArticleFieldMapper.getWxArticleFields();
    }

    @Override
    public List<TopicModel> getSimilarTopicList(List<String> kwList,Integer count){
        /**
         * algorithm: construct each word's topic similarity queue, then pop in turn to get enough topics
         */
        if(Objects.isNull(kwList) || kwList.isEmpty() || count < 1){
            return null;
        }

        float word_topic_similarity_threshold = 0.4f;
        Map<String, LinkedList> wordqueueMap = new HashMap<String, LinkedList>();
        kwList.stream().forEach(kw -> {
            float[] wordVector = wordService.getWordVectorsByCache(kw);
            if (Objects.nonNull(wordVector)) {
                //computing similarity
                Map<Integer, Float> result = topicService.getCoordinatesByCache().entrySet()
                        .stream()
                        .collect(Collectors.toMap(p -> p.getKey(), p -> WordVectorHelper.getSimilarity(wordVector, p.getValue())));
                //filtering
                Map<Integer, Float> filteredResult = result.entrySet().parallelStream()
                        .filter(entry -> entry.getValue() >= word_topic_similarity_threshold)
                        .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
                //sorting
                LinkedList queue = filteredResult.entrySet().stream().sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                        .map(Map.Entry::getKey).collect(Collectors.toCollection(LinkedList::new));
                //saving
                wordqueueMap.put(kw, queue);
            }
        });
        
        List<Integer> idList = new ArrayList<Integer>();
        Map<Integer,String> idKwMap = new HashMap<Integer,String>();
        int counter = 0;
        boolean running = true;
        while( running && !wordqueueMap.isEmpty() ){
            for(String key : wordqueueMap.keySet()){
                if(counter < count) {
                    Integer topicId =  (Integer)wordqueueMap.get(key).poll();
                    idList.add(topicId);
                    idKwMap.put(topicId, key);
                    counter++;
                }else{
                    running = false;
                    break;
                }
            }
        }

        List<Topic> topicList = topicDao.getByIdList(idList);

        List<TopicModel> result = topicList.stream().map(t -> {
            TopicModel item = t.getModel();
            item.setScore(WordVectorHelper
                    .getSimilarity(
                            wordService.getWordVectorsByCache(idKwMap.get(item.getId()) ),
                            CommonHelper.stringToFloatArray( t.getCoordinate() ) ) );
            return item;
        }).collect(Collectors.toList());
        
        return result;
    }

}
