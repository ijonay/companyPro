package com.zc.service;

import java.io.IOException;
import java.util.List;

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
}
