package com.zc.test;

import com.zc.BaseTest;
import com.zc.service.PathService;
import com.zc.service.RedisService;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void testElapse() {
        String str = "此次更新，对tree-split中潜伏了n久的偏移量错误进行了修正。之所以修改这个是要作关键字标红。所以理所当然我把摘要做了，同时对关键词抽取作了一些补充了规则。目前摘要还处于alaph阶段。说白了，光一个摘要都能写一篇博士论文，对于开放场景的摘要其实未必需要高深的算法，这个可以在工程中用，能用，简单，但是无法做到行业顶级，每一个应用如果要做细作精，必须去结合自己的需求，以及业务来作。所以这些基础件理念是用20%的工作完成80%的功能。突然想起一句广告词。好不好看疗效。俄。。。写到这里发现走题了。总结下。这次新增了 。摘要 ，基于query的摘要 ，文章标红，优化了关键词抽取。等功能。很想做一件事情，做一个开源的nlp处理工具包。包括摘要，关键词抽取，倾向性分析，主题发现等功能，不在这里做了另起一个项目。有兴趣的可以联系我。加油";
        System.out.println(ToAnalysis.parse(str));

        redisService.add();
    }
}
