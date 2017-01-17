package zc.test;/**
 * Created by xyzhuzhou on 2017/1/17 0017 11:32:03.
 */

import com.alibaba.fastjson.JSON;
import com.zc.BaseTest;
import com.zc.model.solrmodel.ArticleModel;
import com.zc.utility.SolrSearchHelper;
import org.apache.solr.client.solrj.SolrQuery;
import org.junit.Test;

import java.util.List;

/**
 * Created by xyzhuzhou on 2017/1/17 0017 11:32:03.
 */
public class TestSolr extends BaseTest {

    @Test
    public void test() {


        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*")
                .setStart(0)
                .setRows(10);

        List<ArticleModel> query = SolrSearchHelper.query(solrQuery, new ArticleModel());


        System.out.println(JSON.toJSONString(query));

//        SolrSearchHelper.ConvertModel(null, ArticleModel.class);

    }
}
