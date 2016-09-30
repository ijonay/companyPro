package com.zc.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zc.bean.Word;
import com.zc.model.ClusterModel;
import com.zc.model.TopicModel;
import com.zc.model.VertexEdgeModel;

/**
 * Created by polun on 2016/7/8.
 */
public interface WordService {
    VertexEdgeModel getWords(String modelPath, String clueWord,
                             int topN, float relevancy, int length) throws IOException;

    List<TopicModel> getTopicSimilarity(String sourceWord);

    float[] getWordVector(String word);

    List<List<ClusterModel>> KMeans(File arfffile, File outFile, Integer clusterNum,
                                    List<String> listWords) throws Exception;

    Map<String, float[]> getModelMap();
    
    Integer add(Word bean);

    Integer del(Integer id);

    Integer update(Word bean);

    Word get(Integer id);
    
    Integer getItemCount();

    List<Word> getList(Integer pageSize, Integer rowStart);
}
