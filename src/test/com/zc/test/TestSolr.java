package zc.test;/**
 * Created by xyzhuzhou on 2017/1/17 0017 11:32:03.
 */

import com.alibaba.fastjson.JSON;
import com.zc.BaseTest;
import com.zc.enumeration.PublishDateEnum;
import com.zc.model.solrmodel.ArticleModel;
import com.zc.model.solrmodel.ArticleSearchModel;
import com.zc.utility.SolrSearchHelper;
import com.zc.utility.page.Page;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by xyzhuzhou on 2017/1/17 0017 11:32:03.
 */
public class TestSolr extends BaseTest {

    @Test
    public void test() {


        SolrQuery solrQuery = new SolrQuery();

        solrQuery
                .setQuery("*:*")
                .setStart(0)
                .setRows(10)
                .set("fl", "id,title,articleTags,articleType" +
                        ",structure_type,relative_score,keywords,content,raw_content," +
                        "publish_time,articleTags,score");


        List<ArticleModel> query = SolrSearchHelper.query(solrQuery, new ArticleModel());


        System.out.println(JSON.toJSONString(query));

//        SolrSearchHelper.ConvertModel(null, ArticleModel.class);

    }

    @Test
    public void testSearch() throws Exception {


        ArticleSearchModel searchModel = new ArticleSearchModel();

        searchModel.setKeywords("春节 回家 过年");
        searchModel.setPublishDate(PublishDateEnum.INSEVENDAYS);
        //searchModel.setStructTypes(Arrays.asList("互动类,视频类".split(",")));
        searchModel.setTags(Arrays.asList("时事,民生,美体".split((","))));

        Objects.requireNonNull(searchModel.getKeywords());


        String searchKeys = "(title:" + String.join(" OR title:", searchModel.getKeywords().split(" " +
                "")) + ")";


        if (Objects.nonNull(searchModel.getTags()) && searchModel.getTags().size() > 0) {

            searchKeys += "AND (articleTags:" + String.join(" OR articleTags:", searchModel.getTags()) + ")";
        }

        if (Objects.nonNull(searchModel.getStructTypes()) && searchModel.getStructTypes().size() > 0) {

            searchKeys += "AND (structure_type:" + String.join(" OR structure_type:", searchModel.getStructTypes()) +
                    ")";
        }

        SolrQuery solrQuery = new SolrQuery();

        solrQuery
                .setQuery(searchKeys)
                .setStart(searchModel.getStartIndex())
                .setRows(searchModel.getPageSize())
                .set("fl", "id,title_mmseg,title,titleStruct,account_id,account_name,read_num,articleTags,articleType" +
                        ",structure_type,relative_score,keywords," +
                        //"content,raw_content," +
                        "publish_time,articleTags,score");

        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00'Z'");

        String dateStr1 = null;
        String dateStr2 = "NOW";

        switch (searchModel.getPublishDate()) {
            case TODAY:
                dateStr1 = simpleDateFormat.format(new Date(System.currentTimeMillis() - (1 * 1000 * 60 * 60 * 24)));
                break;
            case INSEVENDAYS:
                dateStr1 = simpleDateFormat.format(new Date(System.currentTimeMillis() - (7 * 1000 * 60 * 60 * 24)));
                break;
            case INTHIRTYDAYS:
                dateStr1 = simpleDateFormat.format(new Date(System.currentTimeMillis() - (30 * 1000 * 60 * 60 * 24)));
                break;
        }

        solrQuery.set("fq", "publish_time:[" + dateStr1 + " TO " + dateStr2 + " ]");

        SolrDocumentList query = SolrSearchHelper.query(solrQuery);

        List<ArticleModel> articleModels = SolrSearchHelper.ConvertModelList(query, new ArticleModel());

        Page page = new Page();
        page.setPageSize(searchModel.getPageSize());
        page.setTotalCount(Integer.parseInt(query.getNumFound() + ""));
        page.setPageNumber(searchModel.getPageNumber());
        page.data(articleModels);

        return;
    }

    @Test
    public void testSearchByKeys() {


        List<String> keys = new ArrayList<>();
        keys.add("春节");
        keys.add("过年");

        String searchKeys = "title_mmseg:" + String.join(" or title_mmseg:", keys);


        SolrQuery solrQuery = new SolrQuery();

        solrQuery
                .setQuery(searchKeys)
                .setStart(0)
                .setRows(1000)
                .setSort("product(relative_score,query($q))", SolrQuery.ORDER.desc)
                .set("fl", "id,title_mmseg,title,titleStruct,account_id,account_name,read_num,articleTags,articleType" +
                        ",structure_type,relative_score,keywords," +
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


        System.out.println(JSON.toJSONString(result));


    }


}
