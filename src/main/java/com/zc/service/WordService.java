package com.zc.service;

import com.zc.bean.Word;
import com.zc.model.ClusterModel;
import com.zc.model.TopicModel;
import com.zc.model.VertexEdgeModel;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by polun on 2016/7/8.
 */
public interface WordService {


    VertexEdgeModel getWords(String modelPath, String clueWord,
                             int topN, float relevancy, int length) throws IOException;

    List<TopicModel> getTopicSimilarity(String sourceWord);

    @Deprecated
    float[] getWordVector(String word);

    List<List<ClusterModel>> KMeans(File arfffile, File outFile, Integer clusterNum,
                                    List<String> listWords) throws Exception;

    Map<String, float[]> getModelMap();

    /**
     * 获取词向量
     *
     * @param key
     * @return
     */
    float[] getWordVectorsByCache(String key);

    Map<String, float[]> getWordVectorsCollByCache();

    /**
     * 更新redis中的语料库集合信息 每调用一次更新一次redis
     */
    void cache_UpdateWordVerctorsToColl();

    /**
     * 更新redis中的语料库信息 每调用一次更新一次redis
     */
    void cache_UpdateWordVectors();

    /**
     * 更新redis中的词周围25个点
     */
    void cache_UpdateWordRoundPoints();


    Integer add(Word bean);

    Integer del(Integer id);

    Integer update(Word bean);

    Word get(Integer id);

    Integer getItemCount();

    List<Word> getList(Integer pageSize, Integer rowStart);
}
