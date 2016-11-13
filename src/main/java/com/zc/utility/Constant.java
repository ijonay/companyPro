package com.zc.utility;

/**
 * Created by polun on 2016/9/12.
 */
public interface Constant {
    String CONFIG_PROPERTIES = "config.properties";
    String SIMILARITY_THRESHOLD = "similarity_threshold";
    String TOP_NSIZE = "topnsize";
    String DISSIMILARITY_THRESHOLD = "dissimilarity_threshold";
    String MAX_PATHLENGTH = "max_pathlength";
    String SOLR_URL = "solr_url";
    String MODEL_BIN = "model_bin";

    String SOLR_SEARCH_LENGTH_KEY = "solr_search_length";
    /**
     * 更新词周边25个点（词）向量前缀
     */
    String WORDR_EDISKEY_PREFIX_KEY = "redis_word_key_prefix";

    /**
     * 语料库词向量键前缀
     */
    String WORD_VECTORS_KEY = "redis_word_vectors_key";

    /**
     * 语料库词向量键前缀
     */
    String WORD_VECTORS_KEY_PREFIX = "redis_word_vectors_key_prefix";

    /**
     * 话题集合键
     */
    String TOPICS_VECTORS_KEY = "redis_topics_vectors_key";
    /**
     * 单个话题键前缀
     */
    String TOPICS_VECTORS_KEY_PREFIx = "redis_topics_vectors_key_prefix";

}
