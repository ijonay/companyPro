package com.zc.model.path;

/**
 * Created by xyzhuzhou on 2016/11/24 0024.
 */
public class ValModel {
    public ValModel() {
    }

    public ValModel(float[] val) {
        this.val = val;
    }

    public ValModel(float[] val, float similarity) {
        this.val = val;
        this.similarity = similarity;
    }

    private float[] val;
    private float similarity;

    public float[] getVal() {
        return val;
    }

    public void setVal(float[] val) {
        this.val = val;
    }

    public float getSimilarity() {
        return similarity;
    }

    public void setSimilarity(float similarity) {
        this.similarity = similarity;
    }
}
