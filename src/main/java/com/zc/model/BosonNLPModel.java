package com.zc.model;

public class BosonNLPModel {
 private Double similarDegree;
    
    private String keyWord;

    public Double getSimilarDegree() {
        return similarDegree;
    }

    public void setSimilarDegree(Double similarDegree) {
        this.similarDegree = similarDegree;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public BosonNLPModel() {
        super();
        // TODO Auto-generated constructor stub
    }

    public BosonNLPModel(Double similarDegree, String keyWord) {
        super();
        this.similarDegree = similarDegree;
        this.keyWord = keyWord;
    }

    @Override
    public String toString() {
        return "BosonNLPModel [similarDegree=" + similarDegree + ", keyWord=" + keyWord + "]";
    }
    
}
